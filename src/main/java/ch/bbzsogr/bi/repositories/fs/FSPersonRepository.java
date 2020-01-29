package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.PeopleRepository;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

/**
 * The type Fs person repository.
 */
@DatabaseType(type = DatabaseInterpreters.FS)
public class FSPersonRepository implements PeopleRepository {

  @Override
  public Person find(String identifier) {
    return null;
  }

  @Override
  public Person save(Person obj) {
    return null;
  }

  @Override
  public Person save(Person obj, Transaction transaction) {
    return null;
  }

  @Override
  public void update(Person obj) {

  }

  @Override
  public void update(Person obj, Transaction transaction) {

  }

  @Override
  public void delete(Person obj) {

  }

  @Override
  public Person findPerson(String email) {
    return null;
  }

  @Override
  public Person checkIfPersonExists(String email, String password) {
    return null;
  }
}
