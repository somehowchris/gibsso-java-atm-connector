package bi.repositories.orm;

import bi.interfaces.repositories.WithdrawRepository;
import bi.models.Withdraw;
import org.hibernate.Transaction;

public class ORMWithdrawRepository implements WithdrawRepository {
  @Override
  public Withdraw find(Withdraw obj) {
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
