package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Person;

/**
 * The Person user details update Exception
 */
public class PersonUserDetailsUpdateException extends Exception {

  private Person person;

  /**
   * Initiates a new Person user details update Exception
   *
   * @param person
   */
  public PersonUserDetailsUpdateException(Person person) {
    super("Failed to save personal details for " + person.getFirstName() + " " + person.getLastName());
    this.person = person;
  }

  /**
   * Gets the person
   *
   * @return
   */
  public Person getPerson() {
    return person;
  }
}
