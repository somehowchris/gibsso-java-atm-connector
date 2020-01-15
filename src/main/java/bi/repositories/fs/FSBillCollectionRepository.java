package bi.repositories.fs;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.BillCollectionRepository;
import bi.models.BillCollection;
import bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

@DatabaseType(type = DatabaseInterpreters.FS)
public class FSBillCollectionRepository implements BillCollectionRepository {

  @Override
  public BillCollection find(String identifier) {
    return null;
  }

  @Override
  public BillCollection save(BillCollection obj) {
    return null;
  }

  @Override
  public BillCollection save(BillCollection obj, Transaction transaction) {
    return null;
  }

  @Override
  public void update(BillCollection obj) {

  }

  @Override
  public void update(BillCollection obj, Transaction transaction) {

  }

  @Override
  public void delete(BillCollection obj) {

  }
}
