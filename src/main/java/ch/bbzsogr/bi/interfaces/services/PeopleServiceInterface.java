package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.PersonUserDetailsUpdateException;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Person;

import java.io.IOException;

public interface PeopleServiceInterface extends ServiceInterface {

  Person getPerson(String id) throws Exception;

  void deletePerson(Person person) throws Exception;

  Person getPersonByMail(String email) throws Exception;

  Person authenticate(String email, String password) throws Exception;

  Person createPerson(Person person) throws EntitySaveException, IOException;

  Person updatePersonalDetails(Person person) throws PersonUserDetailsUpdateException, IOException;

}
