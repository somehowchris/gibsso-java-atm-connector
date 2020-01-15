package bi.repositories.orm;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.CardRepository;
import bi.models.Card;
import bi.models.enums.DatabaseInterpreters;

@DatabaseType(type= DatabaseInterpreters.ORM)
public class ORMCardRepository extends ORMRepository<Card> implements CardRepository {
}
