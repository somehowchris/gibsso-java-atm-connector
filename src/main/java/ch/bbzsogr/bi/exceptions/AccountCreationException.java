package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Person;

/**
 * The Account creation Exception
 */
public class AccountCreationException extends Exception {
  /**
   * Instantiates a new Account creation Exception
   *
   * @param person
   */
  public AccountCreationException(Person person) {
    super("The newly created account couldn't be saved for " + person.getFirstName() + " " + person.getLastName());
  }
}
