package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.PersonRepository;
import bi.utils.LoggingUtil;
import bi.interfaces.repositories.AccountRepository;
import bi.models.Account;
import bi.models.Person;
import bi.models.Transaction;
import bi.utils.RepositoryUtil;
import com.google.common.collect.Lists;
import rest.PeopleRestAPI;

import java.util.List;
import java.util.logging.Logger;

public class AccountService {

  AccountRepository accountRepository = RepositoryUtil.getRepository(AccountRepository.class, DatabaseController.type);
  PersonRepository personRepository = RepositoryUtil.getRepository(PersonRepository.class, DatabaseController.type);
  Logger logger = LoggingUtil.newLogger(AccountService.class);

  public Account getAccountByIBAN(String iban){
    logger.info("Getting account of "+iban);
    return accountRepository.findByIban(iban);
  }

  public Transaction transfer(String fromIban, Transaction transaction){
    logger.info("Transacting " +transaction.getAmount()+" "+transaction.getCurrency().name()+" from "+fromIban +" to "+transaction.getTo().getIban());
    Account fromAccount = getAccountByIBAN(fromIban);

    if(fromAccount == null){
      // TODO throw not found error
    }



    return null;
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

  public void lockAccount(Account account){
    logger.info("Locking "+account.getIban());
  }
}
