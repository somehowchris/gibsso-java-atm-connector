package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.BillCollectionRepository;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMBillCollectionRepository extends ORMRepository<BillCollection> implements BillCollectionRepository {

  public ORMBillCollectionRepository() {
  }

  public ORMBillCollectionRepository(Session session) {
    super(session);
  }

}
