package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.CardRepository;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMCardRepository extends ORMRepository<Card> implements CardRepository {

  public ORMCardRepository() {
  }

  public ORMCardRepository(Session session) {
    super(session);
  }

}
