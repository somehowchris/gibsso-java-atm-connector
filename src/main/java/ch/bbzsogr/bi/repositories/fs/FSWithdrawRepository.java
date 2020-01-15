package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.WithdrawRepository;
import ch.bbzsogr.bi.models.Withdraw;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

@DatabaseType(type = DatabaseInterpreters.FS)
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
