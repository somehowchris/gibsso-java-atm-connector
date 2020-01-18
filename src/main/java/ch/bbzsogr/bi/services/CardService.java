package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.repositories.CardRepository;
import ch.bbzsogr.bi.interfaces.repositories.WithdrawRepository;
import ch.bbzsogr.bi.interfaces.services.CardServiceInterface;
import ch.bbzsogr.bi.models.*;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.models.enums.Currency;
import ch.bbzsogr.bi.models.enums.EnvKeys;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.DotEnvUtil;
import ch.bbzsogr.bi.utils.HashUtil;
import ch.bbzsogr.bi.utils.LoggingUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.lucene.util.NumericUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service(api = ApiType.DIRECT)
public class CardService implements CardServiceInterface {

  private CardRepository cardRepository = Container.getRepository(CardRepository.class, DatabaseController.type);
  private PeopleService peopleService = Container.getService(PeopleService.class, ApiType.DIRECT);
  private BancomatService bancomatService = Container.getService(BancomatService.class, ApiType.DIRECT);
  private AccountService accountService = Container.getService(AccountService.class, ApiType.DIRECT);
  private WithdrawRepository withdrawRepository = Container.getRepository(WithdrawRepository.class, DatabaseController.type);

  private Logger logger = new LoggingUtil(CardService.class).getLogger();

  public void lockCard(String cardNr) throws CardLockException, CardNotFoundException {
    logger.info("Locking the card "+cardNr);
    Card card = this.cardRepository.find(cardNr);

    if (card == null) throw new CardNotFoundException(cardNr);

    card.setLocked(true);

    try {
      this.cardRepository.update(card);
    } catch (EntityUpdateException e) {
      logger.info("Could not save the lock update of the card "+cardNr);
      throw new CardLockException(cardNr);
    }
  }

  public void removeCard(Card card) {
    logger.info("Removing the card "+card.getCardNumber());
    this.cardRepository.delete(card);
  }

  public Withdraw withdraw(String cardNr, double amount, Currency currency, String bancomatId) throws WithdrawException, CardNotFoundException, CardLockedException, BancomatNotFoundException, CouldNotMeetWithdrawAmountException, CreditAmountExceededException {
    logger.info("Withdrawing "+amount+" "+currency.name()+" with the card "+cardNr+" from the bancomat "+bancomatId);
    Card card = cardRepository.find(cardNr);
    Bancomat bancomat = bancomatService.getBancomat(bancomatId);

    if (card == null) throw new CardNotFoundException(cardNr);
    if (card.isLocked()) throw new CardLockedException(cardNr);
    if (bancomat == null) throw new BancomatNotFoundException(bancomatId);

    List<BillCollection> bills = bancomat.getBillCollections().stream().filter(o1 -> o1.getCurrency() == currency).sorted((o1, o2) -> (int) ((o1.getWorth() - o2.getWorth()))).collect(Collectors.toList());
    HashMap<Double, Integer> amountOfBills = new HashMap<>(); // Worth, Times

    double total = 0.0;
    while (total < amount) {
      Optional<BillCollection> optionalNextBill = bills.stream().filter(billCollection -> ((int) (amount / billCollection.getWorth())) > 0 && billCollection.getAmount() > 0).findFirst();
      if (optionalNextBill.isPresent()) {
        BillCollection nextBill = optionalNextBill.get();
        int timesThatBill = ((int) (amount / nextBill.getWorth()));
        total += nextBill.getWorth() * timesThatBill;
        nextBill.setAmount(nextBill.getAmount() - timesThatBill);

        amountOfBills.put(nextBill.getWorth(), timesThatBill);
      } else {
        throw new CouldNotMeetWithdrawAmountException(amount, total);
      }
    }

    Withdraw withdraw = new Withdraw();

    List<BillCollection> billsToExpel = new ArrayList<>();
    final Withdraw finalWithdraw = withdraw;
    amountOfBills.forEach((o1, o2) -> {
      BillCollection originalBill = bills.stream().filter(o3 -> o3.getWorth() == o1).findFirst().get();
      BillCollection bill = new BillCollection(originalBill, finalWithdraw);

      bill.setAmount(o2);
      billsToExpel.add(bill);
    });

    withdraw.setBills(billsToExpel);
    withdraw.setCard(card);
    withdraw.setTotalAmount(total);
    withdraw.setBancomat(bancomat);

    Account bankAccount = peopleService.getPersonByMail(new DotEnvUtil().get(EnvKeys.BANK_MAIL.getKey())).getAccounts().get(0);
    Account cardAccount = card.getAccount();

    if (cardAccount.isLocked()) return null;
    if (bankAccount.isLocked()) return null;

    withdraw.setAccount(cardAccount);

    if (card.getCredit() + cardAccount.getBalance() < amount) {
      throw new CreditAmountExceededException(card.getCredit(), cardAccount.getBalance(), amount);
    }

    Transaction transaction = new Transaction();
    transaction.setTo(bankAccount);
    transaction.setAmount(amount > cardAccount.getBalance() ? (cardAccount.getBalance() > 0 ? cardAccount.getBalance() : 0) : amount);
    transaction.setCard(card);
    transaction.setCurrency(currency);
    transaction.setFrom(cardAccount);

    Session session = DatabaseController.getSession();
    org.hibernate.Transaction hibernateTransaction = session.beginTransaction();

    try {
      transaction = accountService.transfer(cardAccount.getIban(), transaction, hibernateTransaction);

      if (transaction == null) {
        throw new WithdrawException();
      }
      withdraw.setTransaction(transaction);
      transaction.setWithdraw(withdraw);

      cardAccount.setBalance(cardAccount.getBalance() - amount);

      if (card.getCredit() + cardAccount.getBalance() - amount == 0) card.setLocked(true);

      withdraw = withdrawRepository.save(withdraw, hibernateTransaction);
      hibernateTransaction.commit();
      return withdraw;
    } catch (Exception e) {
      logger.warning("Could transfer nor withdraw");
      hibernateTransaction.rollback();
      throw new WithdrawException();
    } finally {
      session.close();
    }
  }

  public Card getCard(String cardNr) {
    logger.info("Getting the card "+cardNr);
    return cardRepository.find(cardNr);
  }

  public Card createCard(Account account) throws CardCreationException {
    logger.info("Creating a card for "+account.getIban());
    Card card = new Card();

    String pin = card.getPin();
    card.setPin(HashUtil.hash(pin));

    account.getCards().add(card);
    card.setAccount(account);

    try {
      card = cardRepository.save(card);
      card.setPin(pin);
      return card;
    } catch (EntitySaveException e) {
      logger.warning("Could not save the new account for "+account.getIban());
      throw new CardCreationException(account);
    }
  }

  public void changePin(String cardNr, String pin) throws PinChangeException, CardNotFoundException, PinDoesNotMeetRequirementsException {
    logger.info("Updating the pin for "+cardNr);
    Card card = cardRepository.find(cardNr);
    if (card == null) throw new CardNotFoundException(cardNr);
    if(card.getPin().length() != 6 && NumberUtils.isCreatable(pin)) throw new PinDoesNotMeetRequirementsException();

    card.setPin(HashUtil.hash(pin));

    try {
      cardRepository.save(card);
    } catch (EntitySaveException e) {
      logger.warning("Could not save pin update");
      throw new PinChangeException();
    }
  }

  public Card authenticate(String cardNr, String pin) {
    logger.info("Authenticating "+cardNr);
    return cardRepository.find(cardNr, HashUtil.hash(pin));
  }

}
