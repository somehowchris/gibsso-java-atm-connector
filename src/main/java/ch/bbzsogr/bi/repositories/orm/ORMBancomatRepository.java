package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.BancomatRepository;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMBancomatRepository extends ORMRepository<Bancomat> implements BancomatRepository {

  public ORMBancomatRepository() {
    super();
  }

  public ORMBancomatRepository(Session session) {
    super(session);
  }

}
