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

@Service(api = ApiType.DIRECT)
public class PeopleService implements PeopleServiceInterface {

  private PeopleRepository personRepository = Container.getRepository(PeopleRepository.class, DatabaseController.type);

  public Person authenticate(String email, String password) {
    return personRepository.checkIfPersonExists(email, HashUtil.hash(password));
  }

  public Person updatePersonalDetails(Person person) throws PersonUserDetailsUpdateException {
    org.hibernate.Transaction transaction = DatabaseController.session.beginTransaction();
    try {
      Person tempPerson = personRepository.find(person.getId());

      if (person.getEmail() != null) tempPerson.setEmail(person.getEmail());
      if (person.getLastName() != null) tempPerson.setLastName(person.getLastName());
      if (person.getFirstName() != null) tempPerson.setFirstName(person.getFirstName());
      if (person.getPassword() != null) tempPerson.setPassword(HashUtil.hash(person.getPassword()));

      DatabaseController.session.update(tempPerson);
      transaction.commit();

      tempPerson.setPassword(null);

      return tempPerson;
    } catch (Exception e) {
      throw new PersonUserDetailsUpdateException(person);
    }
  }

  public Person createPerson(Person person) throws EntitySaveException {
    person.setPassword(HashUtil.hash(person.getPassword()));
    return this.personRepository.save(person);
  }

  public void deletePerson(Person person) {
    this.personRepository.delete(person);
  }

  public Person getPerson(String id) {
    return this.personRepository.find(id);
  }

  public Person getPersonByMail(String email) {
    return this.personRepository.findPerson(email);
  }
}
