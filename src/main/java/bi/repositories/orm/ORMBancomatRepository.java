package bi.repositories.orm;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.BancomatRepository;
import bi.models.Bancomat;
import bi.models.enums.DatabaseInterpreters;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMBancomatRepository extends ORMRepository<Bancomat> implements BancomatRepository {
}
