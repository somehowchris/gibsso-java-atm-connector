package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.AccountRepository;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMAccountRepository extends ORMRepository<Account> implements AccountRepository {

  public ORMAccountRepository() {
    super();
  }

  public ORMAccountRepository(Session session) {
    super(session);
  }

}
