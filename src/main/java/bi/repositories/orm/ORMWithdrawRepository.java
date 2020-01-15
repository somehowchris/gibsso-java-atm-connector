package bi.repositories.orm;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.WithdrawRepository;
import bi.models.Withdraw;
import bi.models.enums.DatabaseInterpreters;

@DatabaseType(type= DatabaseInterpreters.ORM)
public class ORMWithdrawRepository extends ORMRepository<Withdraw> implements WithdrawRepository {
}
