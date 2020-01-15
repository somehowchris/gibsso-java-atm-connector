package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Person;

public class AccountReactionException extends Exception {
  public AccountReactionException(Person person) {
    super("The newly created account couldn't be saved for " + person.getFirstName() + " " + person.getLastName());
  }
}
