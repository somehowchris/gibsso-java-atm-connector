package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.TransactionRepository;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMTransactionRepository extends ORMRepository<Transaction> implements TransactionRepository {

  public ORMTransactionRepository() {
  }

  public ORMTransactionRepository(Session session) {
    super(session);
  }

}
