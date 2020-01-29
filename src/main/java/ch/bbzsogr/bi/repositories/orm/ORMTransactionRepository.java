package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.TransactionRepository;
import ch.bbzsogr.bi.models.Transaction;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

/**
 * The type Orm transaction repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMTransactionRepository extends ORMRepository<Transaction> implements TransactionRepository {

  /**
   * Instantiates a new Orm transaction repository.
   */
  public ORMTransactionRepository() {
  }

  /**
   * Instantiates a new Orm transaction repository.
   *
   * @param session the session
   */
  public ORMTransactionRepository(Session session) {
    super(session);
  }

}
