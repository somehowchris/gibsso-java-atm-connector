package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.repositories.AccountRepository;
import ch.bbzsogr.bi.interfaces.repositories.PersonRepository;
import ch.bbzsogr.bi.interfaces.repositories.TransactionRepository;
import ch.bbzsogr.bi.interfaces.services.AccountServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.LoggingUtil;
import com.google.common.collect.Lists;

import javax.security.auth.login.AccountLockedException;
import java.util.logging.Logger;

@Service(api = ApiType.DIRECT)
public class AccountService implements AccountServiceInterface {

  private AccountRepository accountRepository = Container.getRepository(AccountRepository.class, DatabaseController.type);
  private PersonRepository personRepository = Container.getRepository(PersonRepository.class, DatabaseController.type);
  private TransactionRepository transactionRepository = Container.getRepository(TransactionRepository.class, DatabaseController.type);

  private Logger logger = new LoggingUtil(AccountService.class).getLogger();

  public Account getAccountByIBAN(String iban) {
    logger.info("Getting account of " + iban);
    return accountRepository.find(iban);
  }

  public Transaction transfer(String fromIban, Transaction transaction, org.hibernate.Transaction dbTransaction) throws TransferException, AccountLockedException, AccountNotFoundException, TooLowCreditBalanceException, NoAccountSpecifiedException, NoCurrencySpecifiedException, SubZeroTransactionAmountException {
    logger.info("Transacting " + transaction.getAmount() + " " + transaction.getCurrency().name() + " from " + fromIban + " to " + transaction.getTo().getIban());
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
      throw new TransferException();
    }
  }

  public Account createAccount(Person person) throws AccountReactionException {
    logger.info("Creating an account for " + person.getFirstName() + " " + person.getLastName());
    Account account = new Account();
    account.setPerson(person);

    if (person.getAccounts() == null) {
      person.setAccounts(Lists.newArrayList(account));
    } else {
      person.getAccounts().add(account);
    }

    try {
      personRepository.update(person);
    } catch (EntityUpdateException couldNotUpdateEntity) {
      throw new AccountReactionException(person);
    }

    return account;
  }

  public void lockAccount(String iban) throws AccountLockException {
    logger.info("Locking " + iban);

    Account account = getAccountByIBAN(iban);
    account.setLocked(true);

    try {
      accountRepository.update(account);
    } catch (EntityUpdateException couldNotUpdateEntity) {
      throw new AccountLockException(iban);
    }
  }

  public void updateAccount(Account account) throws EntityUpdateException {
    accountRepository.update(account);
  }

  // TODO unlock via email
  public void unlockAccount(String iban, String code) {

  }
}
