package bi.repositories.fs;

import bi.interfaces.repositories.TransactionRepository;
import bi.models.Transaction;

public class FSTransactionRepository implements TransactionRepository {

  @Override
  public Transaction find(String identifier) {
    return null;
  }

  @Override
  public Transaction save(Transaction obj) {
    return null;
  }

  @Override
  public Transaction save(Transaction obj, org.hibernate.Transaction transaction) {
    return null;
  }

  @Override
  public void update(Transaction obj) {

  }

  @Override
  public void update(Transaction obj, org.hibernate.Transaction transaction) {

  }

  @Override
  public void delete(Transaction obj) {

  }
}
