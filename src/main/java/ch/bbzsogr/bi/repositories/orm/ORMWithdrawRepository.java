package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.WithdrawRepository;
import ch.bbzsogr.bi.models.Withdraw;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

/**
 * The type Orm withdraw repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMWithdrawRepository extends ORMRepository<Withdraw> implements WithdrawRepository {

  /**
   * Instantiates a new Orm withdraw repository.
   */
  public ORMWithdrawRepository() {
  }

  /**
   * Instantiates a new Orm withdraw repository.
   *
   * @param session the session
   */
  public ORMWithdrawRepository(Session session) {
    super(session);
  }

}
