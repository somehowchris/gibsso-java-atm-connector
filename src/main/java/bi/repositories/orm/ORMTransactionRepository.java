package bi.repositories.orm;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.TransactionRepository;
import bi.models.Transaction;
import bi.models.enums.DatabaseInterpreters;

@DatabaseType(type= DatabaseInterpreters.ORM)
public class ORMTransactionRepository extends ORMRepository<Transaction> implements TransactionRepository {
}
