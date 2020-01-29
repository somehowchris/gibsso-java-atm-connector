package ch.bbzsogr.bi.interfaces.repositories;

import ch.bbzsogr.bi.models.Person;

/**
 * The interface People repository.
 */
public interface PeopleRepository extends Repository<Person> {

  /**
   * Find person person.
   *
   * @param email the email
   * @return the person
   */
  Person findPerson(String email);

  /**
   * Check if person exists person.
   *
   * @param email    the email
   * @param password the password
   * @return the person
   */
  Person checkIfPersonExists(String email, String password);

}
