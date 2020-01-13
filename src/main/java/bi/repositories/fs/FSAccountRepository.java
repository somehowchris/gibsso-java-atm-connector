package bi.repositories.fs;

import bi.interfaces.repositories.AccountRepository;
import bi.models.Account;
import org.hibernate.Transaction;

public class FSAccountRepository implements AccountRepository {

  @Override
  public Account findByIban(String iban) {
    return null;
  }

  @Override
  public Account find(Account obj) {
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
