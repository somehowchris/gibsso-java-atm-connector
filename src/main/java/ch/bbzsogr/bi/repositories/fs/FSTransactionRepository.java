package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.TransactionRepository;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;

/**
 * The type Fs transaction repository.
 */
@DatabaseType(type = DatabaseInterpreters.FS)
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
