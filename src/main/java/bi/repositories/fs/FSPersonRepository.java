package bi.repositories.fs;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.PersonRepository;
import bi.models.Person;
import bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

@DatabaseType(type = DatabaseInterpreters.FS)
public class FSPersonRepository implements PersonRepository {
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
