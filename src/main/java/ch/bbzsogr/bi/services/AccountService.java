package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.repositories.AccountRepository;
import ch.bbzsogr.bi.interfaces.repositories.PeopleRepository;
import ch.bbzsogr.bi.interfaces.repositories.TransactionRepository;
import ch.bbzsogr.bi.interfaces.services.AccountServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.LoggingUtil;
import org.hibernate.Session;

import javax.security.auth.login.AccountLockedException;
import java.util.ArrayList;
import java.util.logging.Logger;

@Service(api = ApiType.DIRECT)
public class AccountService implements AccountServiceInterface {

  private AccountRepository accountRepository = Container.getRepository(AccountRepository.class, DatabaseController.type);
  private PeopleRepository personRepository = Container.getRepository(PeopleRepository.class, DatabaseController.type);
  private TransactionRepository transactionRepository = Container.getRepository(TransactionRepository.class, DatabaseController.type);

  private Logger logger = new LoggingUtil(AccountService.class).getLogger();

  public Account getAccountByIBAN(String iban) throws AccountNotFoundException {
    logger.info("Getting account of " + iban);
    try {
      return accountRepository.find(iban);
    } catch (Exception e) {
      throw new AccountNotFoundException(iban);
    }
  }

  public Transaction transfer(String fromIban, Transaction transaction, org.hibernate.Transaction dbTransaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException {
    logger.info("Transacting " + transaction.getAmount() + " " + transaction.getCurrency() + " from " + fromIban + " to " + transaction.getTo().getIban());
    Account fromAccount = getAccountByIBAN(fromIban);

    if (fromAccount == null) throw new AccountNotFoundException(fromIban);
    if (transaction.getCurrency() == null) throw new NoCurrencySpecifiedException();
    if (transaction.getAmount() < 0.0)
      throw new SubZeroTransactionAmountException(transaction.getAmount(), transaction.getCurrency());
    if (fromAccount.getBalance() < transaction.getAmount() && transaction.getAmount() > 0)
      throw new TooLowCreditBalanceException(fromAccount.getBalance(), transaction.getAmount(), transaction.getCurrency());
    if (fromAccount.isLocked()) throw new AccountLockedException(fromAccount.getIban());
    if (transaction.getTo() == null) throw new NoAccountSpecifiedException();

    Account toAccount = getAccountByIBAN(transaction.getTo().getIban());

    if (toAccount == null) throw new AccountNotFoundException(transaction.getTo().getIban());
    if (toAccount.isLocked()) throw new AccountLockedException(toAccount.getIban());

    Transaction finalTransaction = new Transaction(transaction.getAmount(), toAccount, fromAccount);
    fromAccount.setBalance(fromAccount.getBalance() - finalTransaction.getAmount());
    toAccount.setBalance(toAccount.getBalance() + finalTransaction.getAmount());
    finalTransaction.setCard(transaction.getCard());

    try {
      accountRepository.update(fromAccount, dbTransaction);
      accountRepository.update(toAccount, dbTransaction);
      Transaction transaction1 = transactionRepository.save(transaction, dbTransaction);
      return transaction1;
    } catch (Exception e) {
      logger.warning("Could not save transaction");
      throw new TransferException();
    }
  }

  public Transaction transfer(String fromIban, Transaction transaction) throws TransferException, AccountNotFoundException, NoCurrencySpecifiedException, TooLowCreditBalanceException, AccountLockedException, NoAccountSpecifiedException, SubZeroTransactionAmountException {
    Session session = DatabaseController.getSession();
    org.hibernate.Transaction dbTransaction = session.beginTransaction();
    try {
      transaction = this.transfer(fromIban, transaction, dbTransaction);
      dbTransaction.commit();
      return transaction;
    } catch (Exception e) {
      logger.info("Rolling back that failed transaction");
      dbTransaction.rollback();
      throw e;
    }

  }

  public Account createAccount(Person person) throws AccountCreationException {
    logger.info("Creating an account for " + person.getFirstName() + " " + person.getLastName());
    Account account = new Account();

    account.setPerson(person);

    try {
      accountRepository.save(account);
      return account;
    } catch (EntitySaveException couldNotUpdateEntity) {
      couldNotUpdateEntity.printStackTrace();
      logger.warning("Could not create an account for "+person.getFirstName());
      throw new AccountCreationException(person);
    }
  }

  public void lockAccount(String iban) throws AccountLockException, AccountNotFoundException {
    logger.info("Locking " + iban);

    Account account = getAccountByIBAN(iban);

    if(account == null) throw new AccountNotFoundException(iban);

    account.setCards(null);
    account.setLocked(true);

    try {
      accountRepository.update(account);
    } catch (EntityUpdateException couldNotUpdateEntity) {
      logger.warning("Could not lock the account "+iban);
      throw new AccountLockException(iban);
    }
  }

  public void updateAccount(Account account) throws EntityUpdateException {
    logger.info("Updating account informations for "+account);
    accountRepository.update(account);
  }

}
