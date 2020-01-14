package bi.repositories.fs;

import bi.interfaces.repositories.WithdrawRepository;
import bi.models.Withdraw;
import org.hibernate.Transaction;

public class FSWithdrawRepository implements WithdrawRepository {

  @Override
  public Withdraw find(String identifier) {
    return null;
  }

  @Override
  public Withdraw save(Withdraw obj) {
    return null;
  }

  @Override
  public Withdraw save(Withdraw obj, Transaction transaction) {
    return null;
  }

  @Override
  public void update(Withdraw obj) {

  }

  @Override
  public void update(Withdraw obj, Transaction transaction) {

  }

  @Override
  public void delete(Withdraw obj) {

  }
}
