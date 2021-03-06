package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.CardRepository;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * The type Orm card repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMCardRepository extends ORMRepository<Card> implements CardRepository {

  /**
   * Instantiates a new Orm card repository.
   */
  public ORMCardRepository() {
  }

  /**
   * Instantiates a new Orm card repository.
   *
   * @param session the session
   */
  public ORMCardRepository(Session session) {
    super(session);
  }

  @Override
  public Card find(String cardNr, String pin) {
    CriteriaQuery<Card> personCriteriaQuery = getCriteriaBuilder().createQuery(Card.class);

    Root<Card> cardRoot = personCriteriaQuery.from(Card.class);

    personCriteriaQuery.select(cardRoot);

    personCriteriaQuery
      .where(getCriteriaBuilder().equal(cardRoot.get("cardNumber"), cardNr))
      .where(getCriteriaBuilder().equal(cardRoot.get("pin"), pin));

    Query personQuery = getLocalSession().createQuery(personCriteriaQuery);

    return (Card) personQuery.getSingleResult();
  }

  private CriteriaBuilder getCriteriaBuilder() {
    return getLocalSession().getCriteriaBuilder();
  }
}
