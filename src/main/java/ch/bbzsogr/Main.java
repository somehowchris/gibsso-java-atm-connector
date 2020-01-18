package ch.bbzsogr;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.services.AccountServiceInterface;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.services.AccountService;
import ch.bbzsogr.bi.services.CardService;
import ch.bbzsogr.bi.services.PeopleService;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.rest.models.RestAPIConfig;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws OGMNotYetSupportedException, OGMDatabaseTypeNotFoundException, ConnectionRefusedException, IOException, AccessNotGrantedException, UrlDialectNotSupportedException, AccountLockException, AccountNotFoundException, EntitySaveException, AccountCreationException, CardCreationException {
    RestAPIConfig config = new RestAPIConfig();
    DatabaseController db = new DatabaseController();
    PeopleService peopleService = new PeopleService();
    AccountService accountService = new AccountService();
    CardService cardService = new CardService();

    Person person = peopleService.getPerson("402882f66fb9f9de016fb9f9ed900000");//peopleService.createPerson(new Person("chweicki@icloud.com","meins","zwei","IamStrong", null));
    Account account = accountService.createAccount(person);
    System.out.println(account.getCards().size());
    Card card = cardService.createCard(account);
  }
}
