package ch.bbzsogr.bi.interfaces.repositories;

import org.hibernate.Transaction;

public interface Repository<T> {
  T find(String identifier);

  T save(T obj);

  T save(T obj, Transaction transaction);

  void update(T obj);

  void update(T obj, Transaction transaction);

  void delete(T obj);
}