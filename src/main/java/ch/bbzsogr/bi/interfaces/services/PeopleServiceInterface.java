package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.PersonUserDetailsUpdateException;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Person;

import java.io.IOException;

/**
 * The interface People service interface.
 */
public interface PeopleServiceInterface extends ServiceInterface {

  /**
   * Gets person.
   *
   * @param id the id
   * @return the person
   * @throws Exception the exception
   */
  Person getPerson(String id) throws Exception;

  /**
   * Delete person.
   *
   * @param person the person
   * @throws Exception the exception
   */
  void deletePerson(Person person) throws Exception;

  /**
   * Gets person by mail.
   *
   * @param email the email
   * @return the person by mail
   * @throws Exception the exception
   */
  Person getPersonByMail(String email) throws Exception;

  /**
   * Authenticate person.
   *
   * @param email    the email
   * @param password the password
   * @return the person
   * @throws Exception the exception
   */
  Person authenticate(String email, String password) throws Exception;

  /**
   * Create person person.
   *
   * @param person the person
   * @return the person
   * @throws EntitySaveException the entity save exception
   * @throws IOException         the io exception
   */
  Person createPerson(Person person) throws EntitySaveException, IOException;

  /**
   * Update personal details person.
   *
   * @param person the person
   * @return the person
   * @throws PersonUserDetailsUpdateException the person user details update exception
   * @throws IOException                      the io exception
   */
  Person updatePersonalDetails(Person person) throws PersonUserDetailsUpdateException, IOException;

}
