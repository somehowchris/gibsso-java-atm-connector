package bi.repositories.fs;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.AccountRepository;
import bi.models.Account;
import bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

@DatabaseType(type=DatabaseInterpreters.FS)
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
