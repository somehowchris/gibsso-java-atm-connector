package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Api;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.interfaces.repositories.AccountRepository;
import ch.bbzsogr.bi.interfaces.repositories.PersonRepository;
import ch.bbzsogr.bi.interfaces.services.AccountServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.LoggingUtil;
import com.google.common.collect.Lists;

import java.util.logging.Logger;

@Service()
@Api(type = ApiType.DIRECT)
public class AccountService implements AccountServiceInterface {

  AccountRepository accountRepository = Container.getRepository(AccountRepository.class, DatabaseController.type);
  PersonRepository personRepository = Container.getRepository(PersonRepository.class, DatabaseController.type);

  Logger logger = new LoggingUtil(AccountService.class).getLogger();

  public Account getAccountByIBAN(String iban) {
    logger.info("Getting account of " + iban);
    return accountRepository.find(iban);
  }

  public Transaction transfer(String fromIban, Transaction transaction) {
    logger.info("Transacting " + transaction.getAmount() + " " + transaction.getCurrency().name() + " from " + fromIban + " to " + transaction.getTo().getIban());
    Account fromAccount = getAccountByIBAN(fromIban);

    if (fromAccount == null) {
      // TODO throw not found error
      return null;
    }

    if (transaction.getAmount() < 0.0) {
      // TODO sub 0
      return null;
    }

    if (fromAccount.getBalance() < transaction.getAmount() && transaction.getAmount() > 0) {
      // TODO too low balance error
      return null;
    }

    if (fromAccount.isLocked()) {
      // TODO source account locked;
      return null;
    }

    if (transaction.getTo() == null) {
      // TODO no receiving account specified
      return null;
    }

    Account toAccount = getAccountByIBAN(transaction.getTo().getIban());

    if (toAccount == null) {
      // TODO receiving account not found
      return null;
    }

    if (toAccount.isLocked()) {
      // TODO receiving account locked
      return null;
    }

    System.out.println(transaction.getAmount());
    Transaction finalTransaction = new Transaction(transaction.getAmount(), toAccount, fromAccount);
    fromAccount.setBalance(fromAccount.getBalance() - finalTransaction.getAmount());
    toAccount.setBalance(toAccount.getBalance() + finalTransaction.getAmount());
    finalTransaction.setCard(transaction.getCard());

    org.hibernate.Transaction dbTransaction = DatabaseController.session.beginTransaction();
    try {
      accountRepository.update(fromAccount, dbTransaction);
      accountRepository.update(toAccount, dbTransaction);
      dbTransaction.commit();
    } catch (Exception e) {
      dbTransaction.rollback();
      // TODO couldn't transact exception
    }

    return finalTransaction;
  }

  public Account createAccount(Person person) {
    logger.info("Creating an account for " + person.getFirstName() + " " + person.getLastName());
    Account account = new Account();
    account.setPerson(person);

    if (person.getAccounts() == null) {
      person.setAccounts(Lists.newArrayList(account));
    } else {
      person.getAccounts().add(account);
    }

    personRepository.update(person);

    return account;
  }

  public void lockAccount(String iban) {
    logger.info("Locking " + iban);

    Account account = getAccountByIBAN(iban);
    account.setLocked(true);

    accountRepository.update(account);
  }

  public void updateAccount(Account account) {
    accountRepository.update(account);
  }

  // TODO unlock via email
  public void unlockAccount(String iban, String code) {

  }
}
