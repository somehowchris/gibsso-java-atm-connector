package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.BillCollectionRepository;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

/**
 * The type Orm bill collection repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMBillCollectionRepository extends ORMRepository<BillCollection> implements BillCollectionRepository {

    /**
     * Instantiates a new Orm bill collection repository.
     */
    public ORMBillCollectionRepository() {
  }

    /**
     * Instantiates a new Orm bill collection repository.
     *
     * @param session the session
     */
    public ORMBillCollectionRepository(Session session) {
    super(session);
  }

}
