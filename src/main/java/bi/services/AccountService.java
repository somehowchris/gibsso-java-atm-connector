package bi.services;

import bi.controllers.DatabaseController;
import bi.decorators.Service;
import bi.interfaces.repositories.PersonRepository;
import bi.utils.Container;
import bi.utils.LoggingUtil;
import bi.interfaces.repositories.AccountRepository;
import bi.models.Account;
import bi.models.Person;
import bi.models.Transaction;
import com.google.common.collect.Lists;

import java.util.logging.Logger;

@Service()
public class AccountService {

  AccountRepository accountRepository = Container.getRepository(AccountRepository.class, DatabaseController.type);
  PersonRepository personRepository = Container.getRepository(PersonRepository.class, DatabaseController.type);
  Logger logger = new LoggingUtil(AccountService.class).getLogger();

  public Account getAccountByIBAN(String iban){
    logger.info("Getting account of "+iban);
    return accountRepository.find(iban);
  }

  public Transaction transfer(String fromIban, Transaction transaction){
    logger.info("Transacting " +transaction.getAmount()+" "+transaction.getCurrency().name()+" from "+fromIban +" to "+transaction.getTo().getIban());
    Account fromAccount = getAccountByIBAN(fromIban);

    if(fromAccount == null){
      // TODO throw not found error
      return null;
    }

    if(fromAccount.getBalance() < transaction.getAmount()){
      // TODO too low balance error
      return null;
    }

    if(fromAccount.isLocked()){
      // TODO source account locked;
      return null;
    }

    if(transaction.getTo() == null){
      // TODO no receiving account specified
      return null;
    }

    Account toAccount = getAccountByIBAN(transaction.getTo().getIban());

    if(toAccount == null){
      // TODO receiving account not found
      return null;
    }

    if(toAccount.isLocked()){
      // TODO receiving account locked;
      return null;
    }


    Transaction finalTransaction = new Transaction(transaction.getAmount(), toAccount, fromAccount);

    fromAccount.setBalance(fromAccount.getBalance()-finalTransaction.getAmount());
    toAccount.setBalance(toAccount.getBalance()+finalTransaction.getAmount());

    org.hibernate.Transaction dbTransaction = DatabaseController.session.beginTransaction();
    try{
      accountRepository.update(fromAccount, dbTransaction);
      accountRepository.update(toAccount, dbTransaction);
      dbTransaction.commit();
    }catch (Exception e){
      dbTransaction.rollback();
      // TODO couldn't transact exception
    }

    return finalTransaction;
  }

  public Account createAccount(Person person){
    logger.info("Creating an account for "+person.getFirstName()+" "+person.getLastName());
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

  public void lockAccount(String iban){
    logger.info("Locking "+iban);

    Account account = getAccountByIBAN(iban);
    account.setLocked(true);

    accountRepository.update(account);
  }
}
