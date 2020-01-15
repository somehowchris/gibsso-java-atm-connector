package ch.bbzsogr.bi.interfaces.repositories;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import org.hibernate.Transaction;

public interface Repository<T> {
  T find(String identifier);

  T save(T obj) throws EntitySaveException;

  T save(T obj, Transaction transaction);

  void update(T obj) throws EntityUpdateException;

  void update(T obj, Transaction transaction);

  void delete(T obj);
}
