package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.WithdrawRepository;
import ch.bbzsogr.bi.models.Withdraw;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMWithdrawRepository extends ORMRepository<Withdraw> implements WithdrawRepository {

  public ORMWithdrawRepository() {
  }

  public ORMWithdrawRepository(Session session) {
    super(session);
  }

}
