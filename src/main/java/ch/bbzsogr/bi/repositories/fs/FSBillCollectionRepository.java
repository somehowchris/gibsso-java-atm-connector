package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.BillCollectionRepository;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
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
