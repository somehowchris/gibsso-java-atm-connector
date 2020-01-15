package bi.repositories.orm;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.BillCollectionRepository;
import bi.models.BillCollection;
import bi.models.enums.DatabaseInterpreters;

@DatabaseType(type= DatabaseInterpreters.ORM)
public class ORMBillCollectionRepository extends ORMRepository<BillCollection> implements BillCollectionRepository {
}
