package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.AccountRepository;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

/**
 * The type Fs account repository.
 */
@DatabaseType(type = DatabaseInterpreters.FS)
public class FSAccountRepository implements AccountRepository {

  @Override
  public Account find(String identifier) {
    return null;
  }

  @Override
  public Account save(Account obj) {
    return null;
  }

  @Override
  public Account save(Account obj, Transaction transaction) {
    return null;
  }

  @Override
  public void update(Account obj) {

  }

  @Override
  public void update(Account obj, Transaction transaction) {

  }

  @Override
  public void delete(Account obj) {

  }
}
