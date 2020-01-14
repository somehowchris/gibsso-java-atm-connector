package bi.repositories.fs;

import bi.interfaces.repositories.PersonRepository;
import bi.models.Person;
import org.hibernate.Transaction;

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
}
