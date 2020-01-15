import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.Currency;
import ch.bbzsogr.bi.services.AccountService;
import ch.bbzsogr.bi.services.CardService;
import ch.bbzsogr.bi.services.PeopleService;

import java.io.IOException;

public class main {

  public static void main(String[] args) throws AccessNotGrantedException, ConnectionRefusedException, OGMDatabaseTypeNotFoundException, IOException, WithdrawException, CardCreationException, CardLockedException, BancomatNotFoundException, CouldNotMeetWithdrawAmountException, CardNotFoundException, CreditAmountExceededException, UrlDialectNotSupportedException, EntitySaveException {
    DatabaseController databaseController = new DatabaseController();
    AccountService accountService = new AccountService();
    CardService cardService = new CardService();
    PeopleService peopleService = new PeopleService();
    databaseController.seed();

    Person toCreate = new Person("chweicki@icloud.com", "Christof","Weickhardt","TheOne",null);
    //toCreate = peopleService.createPerson(toCreate);
    Person person = peopleService.authenticate("chweicki@icloud.com", "TheOne");
    System.out.println(person.getPassword());
  }
}
