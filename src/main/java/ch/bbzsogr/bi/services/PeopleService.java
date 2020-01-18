package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.PersonUserDetailsUpdateException;
import ch.bbzsogr.bi.interfaces.repositories.PeopleRepository;
import ch.bbzsogr.bi.interfaces.services.PeopleServiceInterface;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.HashUtil;
import ch.bbzsogr.bi.utils.LoggingUtil;
import org.hibernate.Session;

import java.util.logging.Logger;

@Service(api = ApiType.DIRECT)
public class PeopleService implements PeopleServiceInterface {

  private PeopleRepository personRepository = Container.getRepository(PeopleRepository.class, DatabaseController.type);

  private Logger logger = new LoggingUtil(PeopleService.class).getLogger();

  public Person authenticate(String email, String password)
  {
    logger.info("Authenticating "+email);
    return personRepository.checkIfPersonExists(email, HashUtil.hash(password));
  }

  public Person updatePersonalDetails(Person person) throws PersonUserDetailsUpdateException {
    logger.info("Updating personal information for "+person.getId());
    Session session = DatabaseController.getSession();
    org.hibernate.Transaction transaction = session.beginTransaction();
    try {
      Person tempPerson = personRepository.find(person.getId());

      // TODO move to constructor
      if (person.getEmail() != null) tempPerson.setEmail(person.getEmail());
      if (person.getLastName() != null) tempPerson.setLastName(person.getLastName());
      if (person.getFirstName() != null) tempPerson.setFirstName(person.getFirstName());
      if (person.getPassword() != null) tempPerson.setPassword(HashUtil.hash(person.getPassword()));

      session.update(tempPerson);
      transaction.commit();

      tempPerson.setPassword(null);

      return tempPerson;
    } catch (Exception e) {
      logger.warning("Could not save personal details for "+person.getId());
      throw new PersonUserDetailsUpdateException(person);
    }
  }

  public Person createPerson(Person person) throws EntitySaveException {
    logger.info("We welcome "+person.getFirstName()+" "+person.getLastName()+" to the bank");
    person.setPassword(HashUtil.hash(person.getPassword()));
    return this.personRepository.save(person);
  }

  public void deletePerson(Person person) {
    logger.info("Deleting "+person.getId());
    this.personRepository.delete(person);
  }

  public Person getPerson(String id) {
    logger.info("Finding "+id);
    return this.personRepository.find(id);
  }

  public Person getPersonByMail(String email) {
    logger.info("Finding person by email "+email);
    return this.personRepository.findPerson(email);
  }
}
