package bi.repositories.orm;

import bi.interfaces.repositories.BillCollectionRepository;
import bi.models.BillCollection;
import org.hibernate.Transaction;

public class ORMBillCollectionRepository implements BillCollectionRepository {

  @Override
  public BillCollection find(BillCollection obj) {
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
