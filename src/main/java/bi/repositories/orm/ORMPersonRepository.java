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

@DatabaseType(type= DatabaseInterpreters.ORM)
public class ORMPersonRepository extends ORMRepository<Person> implements PersonRepository {

  public Person findPerson(String email){
    CriteriaBuilder criteriaBuilder = DatabaseController.session.getCriteriaBuilder();
    CriteriaQuery<Person> accountCriteriaQuery = criteriaBuilder.createQuery(Person.class);

    Root<Person> accountRoot = accountCriteriaQuery.from(Person.class);

    accountCriteriaQuery.select(accountRoot);
    accountCriteriaQuery.where(criteriaBuilder.equal(accountRoot.get("email"), email));

    Query personQuery = DatabaseController.session.createQuery(accountCriteriaQuery);
    return (Person) personQuery.getSingleResult();

  }
}
