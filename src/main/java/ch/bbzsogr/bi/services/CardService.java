package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Api;
import ch.bbzsogr.bi.decorators.Service;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service()
@Api(type = ApiType.DIRECT)
public class CardService implements CardServiceInterface {

  private CardRepository cardRepository = Container.getRepository(CardRepository.class, DatabaseController.type);
  private PeopleService peopleService = Container.getService(PeopleService.class);
  private BancomatService bancomatService = Container.getService(BancomatService.class);
  private AccountService accountService = Container.getService(AccountService.class);
  private WithdrawRepository withdrawRepository = Container.getRepository(WithdrawRepository.class, DatabaseController.type);

  public void lockCard(String cardNumber) {
    Card card = this.cardRepository.find(cardNumber);

    if (card == null) {
      // TODO card not found exception
      return;
    }

    card.setLocked(true);

    this.cardRepository.update(card);
  }

  public void removeCard(Card card) {
    this.cardRepository.delete(card);
  }

  public Withdraw withdraw(String cardNr, double amount, Currency currency, String bancomatId) {
    Card card = cardRepository.find(cardNr);
    Bancomat bancomat = bancomatService.getBancomat(bancomatId);
    if (card == null) {
      // TODO card not found;
      return null;
    }
    if (card.isLocked()) return null;

    if (bancomat == null) {
      // TODO bancomat not found;
      return null;
    }
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
        // TODO couldn't meet need;
        // TODO throw max
      }
    }

    Withdraw withdraw = new Withdraw();

    List<BillCollection> billsToExpel = new ArrayList<>();
    amountOfBills.forEach((o1, o2) -> {
      BillCollection originalBill = bills.stream().filter(o3 -> o3.getWorth() == o1).findFirst().get();
      BillCollection bill = new BillCollection(originalBill, withdraw);

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
      //TODO amount too high
      return null;
    }

    Transaction transaction = new Transaction();
    transaction.setTo(bankAccount);
    transaction.setAmount(amount > cardAccount.getBalance() ? (cardAccount.getBalance() > 0 ? cardAccount.getBalance() : 0) : amount);
    transaction.setCard(card);
    transaction.setCurrency(currency);
    transaction.setFrom(cardAccount);

    transaction = accountService.transfer(cardAccount.getIban(), transaction);

    if (transaction == null) {
      // TODO couldn't transfer
      return null;
    }
    withdraw.setTransaction(transaction);
    transaction.setWithdraw(withdraw);

    cardAccount.setBalance(cardAccount.getBalance() - amount);

    if (card.getCredit() + cardAccount.getBalance() - amount == 0) card.setLocked(true);

    return withdrawRepository.save(withdraw);
  }

  // TODO transfer like paying at the market
  public Transaction transfer(Card card, double amount, Currency currency) {
    return null;
  }

  public Card getCard(String cardNr) {
    return cardRepository.find(cardNr);
  }

  public Card createCard(Account account) {
    Card card = new Card();

    account.getCards().add(card);
    card.setAccount(account);

    return cardRepository.save(card);
  }

  public void changePin(String cardNr, String pin) {
    Card card = cardRepository.find(cardNr);
    if (card == null) {
      // TODO not found
      return;
    }
    card.setPin(HashUtil.hash(pin));
    cardRepository.save(card);
  }

  // TODO authenticate via card
  public Card authenticate(String cardNr, String pin) {
    return null;
  }

  // TODO unlock via mail
  public void unlockCard(String cardNr, String code) {

  }
}
