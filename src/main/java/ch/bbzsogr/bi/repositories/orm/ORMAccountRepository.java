package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.AccountRepository;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

/**
 * The type Orm account repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMAccountRepository extends ORMRepository<Account> implements AccountRepository {

  /**
   * Instantiates a new Orm account repository.
   */
  public ORMAccountRepository() {
    super();
  }

  /**
   * Instantiates a new Orm account repository.
   *
   * @param session the session
   */
  public ORMAccountRepository(Session session) {
    super(session);
  }

}
