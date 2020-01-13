package bi;

import bi.controllers.DatabaseController;
import bi.exceptions.AccessNotGrantedException;
import bi.exceptions.ConnectionRefusedException;
import bi.exceptions.OGMDatabaseTypeNotFoundException;
import bi.exceptions.UrlDialectNotSupported;
import bi.models.Account;
import bi.models.Person;
import bi.models.Transaction;
import bi.services.AccountService;
import bi.services.PeopleService;

import java.io.IOException;

public class main {

  public static void main(String[] args) throws AccessNotGrantedException, ConnectionRefusedException, UrlDialectNotSupported, OGMDatabaseTypeNotFoundException, IOException {
    DatabaseController databaseController = new DatabaseController();
    PeopleService peopleService = new PeopleService();
    AccountService accountService = new AccountService();

    Person person = peopleService.getPerson("402881016fa10f73016fa10f7d650000");
    //Person person = peopleService.createPerson(new Person("chweicki@gmail.com","Christof","Weickhardt","IamStrong", null));
    Account account1 = accountService.getAccountByIBAN("CH76 4478 16FG 1GES LRWF 0");
    Account account2 = accountService.getAccountByIBAN("CH92 7296 6CMH 7MHR 85YV Z");

    Transaction transaction = new Transaction();
    transaction.setAmount(20);
    transaction.setTo(account2);

    Transaction transaction1 = accountService.transfer(account1.getIban(), transaction);

    accountService.lockAccount(account1.getIban());
    System.out.println(0);
  }
}
