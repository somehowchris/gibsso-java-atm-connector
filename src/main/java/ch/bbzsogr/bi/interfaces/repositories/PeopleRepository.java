package ch.bbzsogr.bi.interfaces.repositories;

import ch.bbzsogr.bi.models.Person;

public interface PeopleRepository extends Repository<Person> {

  Person findPerson(String email);
  Person checkIfPersonExists(String email, String password);

}
