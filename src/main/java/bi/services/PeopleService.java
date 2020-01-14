package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.PersonRepository;
import bi.models.Person;
import bi.utils.HashUtil;
import bi.utils.RepositoryUtil;

public class PeopleService {

  PersonRepository personRepository = RepositoryUtil.getRepository(PersonRepository.class, DatabaseController.type);

  public Person authenticate(String email, String password){
    return null;
  }

  public Person updatePersonalDetails(Person person){
    org.hibernate.Transaction transaction = DatabaseController.session.beginTransaction();
    try{
      Person tempPerson = personRepository.find(person.getId());

      if(person.getEmail() != null)tempPerson.setEmail(person.getEmail());
      if(person.getLastName() != null)tempPerson.setLastName(person.getLastName());
      if(person.getFirstName() != null)tempPerson.setFirstName(person.getFirstName());
      if(person.getPassword() != null)tempPerson.setPassword(HashUtil.hash(person.getPassword()));

      DatabaseController.session.update(tempPerson);
      transaction.commit();

      tempPerson.setPassword(null);

      return tempPerson;
    }catch(Exception e){
      // TODO couldn't update exception
      return null;
    }
  }

  public Person createPerson(Person person){
    person.setPassword(HashUtil.hash(person.getPassword()));
    return this.personRepository.save(person);
  }

  public void deletePerson(Person person){

  }

  public Person getPerson(String id){
    return this.personRepository.find(id);
  }
}
