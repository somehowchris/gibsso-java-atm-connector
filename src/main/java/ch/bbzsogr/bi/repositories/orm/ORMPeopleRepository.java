package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.PeopleRepository;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * The type Orm people repository.
 */
@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMPeopleRepository extends ORMRepository<Person> implements PeopleRepository {

  /**
   * Instantiates a new Orm people repository.
   */
  public ORMPeopleRepository() {
  }

  /**
   * Instantiates a new Orm people repository.
   *
   * @param session the session
   */
  public ORMPeopleRepository(Session session) {
    super(session);
  }

  public Person findPerson(String email) {
    CriteriaQuery<Person> personCriteriaQuery = getCriteriaBuilder().createQuery(Person.class);

    Root<Person> personRoot = personCriteriaQuery.from(Person.class);

    personCriteriaQuery.select(personRoot);
    personCriteriaQuery.where(getCriteriaBuilder().equal(personRoot.get("email"), email));

    Query personQuery = getLocalSession().createQuery(personCriteriaQuery);
    return (Person) personQuery.getSingleResult();
  }

  public Person checkIfPersonExists(String email, String password) {
    CriteriaQuery<Person> personCriteriaQuery = getCriteriaBuilder().createQuery(Person.class);

    Root<Person> personRoot = personCriteriaQuery.from(Person.class);

    personCriteriaQuery.select(personRoot);
    personCriteriaQuery
      .where(getCriteriaBuilder().equal(personRoot.get("email"), email))
      .where(getCriteriaBuilder().equal(personRoot.get("password"), password));

    Query personQuery = getLocalSession().createQuery(personCriteriaQuery);

    return (Person) personQuery.getSingleResult();
  }

  private CriteriaBuilder getCriteriaBuilder() {
    return getLocalSession().getCriteriaBuilder();
  }

}
