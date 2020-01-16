package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Person;

public class AccountCreationException extends Exception {
  public AccountCreationException(Person person) {
    super("The newly created account couldn't be saved for " + person.getFirstName() + " " + person.getLastName());
  }
}
