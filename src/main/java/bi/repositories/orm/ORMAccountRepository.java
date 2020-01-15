package bi.repositories.orm;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.AccountRepository;
import bi.models.Account;
import bi.models.enums.DatabaseInterpreters;

@DatabaseType(type= DatabaseInterpreters.ORM)
public class ORMAccountRepository extends ORMRepository<Account> implements AccountRepository {
}
