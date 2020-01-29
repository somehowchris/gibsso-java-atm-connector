package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.BancomatRepository;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

/**
 * The type Orm bancomat repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMBancomatRepository extends ORMRepository<Bancomat> implements BancomatRepository {

    /**
     * Instantiates a new Orm bancomat repository.
     */
    public ORMBancomatRepository() {
    super();
  }

    /**
     * Instantiates a new Orm bancomat repository.
     *
     * @param session the session
     */
    public ORMBancomatRepository(Session session) {
    super(session);
  }

}
