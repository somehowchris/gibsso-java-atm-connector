package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.interfaces.repositories.PersonRepository;
import ch.bbzsogr.bi.interfaces.services.PeopleServiceInterface;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.HashUtil;

@Service(api = ApiType.DIRECT)
public class PeopleService implements PeopleServiceInterface {

  PersonRepository personRepository = Container.getRepository(PersonRepository.class, DatabaseController.type);

  public Person authenticate(String email, String password) {
    return personRepository.checkIfPersonExists(email, HashUtil.hash(password));
  }

  public Person updatePersonalDetails(Person person) {
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
      // TODO couldn't update exception
      return null;
    }
  }

  public Person createPerson(Person person) {
    person.setPassword(HashUtil.hash(person.getPassword()));
    return this.personRepository.save(person);
  }

  public void deletePerson(Person person) {

  }

  public Person getPerson(String id) {
    return this.personRepository.find(id);
  }

  public Person getPersonByMail(String email) {
    return this.personRepository.findPerson(email);
  }
}
