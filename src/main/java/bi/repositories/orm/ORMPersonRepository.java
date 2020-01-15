package bi.repositories.orm;

import bi.controllers.DatabaseController;
import bi.decorators.DatabaseType;
import bi.interfaces.repositories.PersonRepository;
import bi.models.Person;
import bi.models.enums.DatabaseInterpreters;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@DatabaseType(type = DatabaseInterpreters.ORM)
public class ORMPersonRepository extends ORMRepository<Person> implements PersonRepository {

  public Person findPerson(String email) {
    CriteriaQuery<Person> personCriteriaQuery = getCriteriaBuilder().createQuery(Person.class);

    Root<Person> personRoot = personCriteriaQuery.from(Person.class);

    personCriteriaQuery.select(personRoot);
    personCriteriaQuery.where(getCriteriaBuilder().equal(personRoot.get("email"), email));

    Query personQuery = DatabaseController.session.createQuery(personCriteriaQuery);
    return (Person) personQuery.getSingleResult();
  }

  public Person checkIfPersonExists(String email, String password) {
    CriteriaQuery<Person> personCriteriaQuery = getCriteriaBuilder().createQuery(Person.class);

    Root<Person> personRoot = personCriteriaQuery.from(Person.class);

    personCriteriaQuery.select(personRoot);
    personCriteriaQuery.where(getCriteriaBuilder().equal(personRoot.get("email"), email));
    personCriteriaQuery.where(getCriteriaBuilder().equal(personRoot.get("password"), email));

    Query personQuery = DatabaseController.session.createQuery(personCriteriaQuery);
    return (Person) personQuery.getSingleResult();
  }

  private CriteriaBuilder getCriteriaBuilder() {
    return DatabaseController.session.getCriteriaBuilder();
  }

}
