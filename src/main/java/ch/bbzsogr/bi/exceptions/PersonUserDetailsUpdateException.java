package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Person;

public class PersonUserDetailsUpdateException extends Exception {

  private Person person;

  public PersonUserDetailsUpdateException(Person person) {
    super("Failed to save personal details for " + person.getFirstName() + " " + person.getLastName());
    this.person = person;
  }

  public Person getPerson() {
    return person;
  }
}
