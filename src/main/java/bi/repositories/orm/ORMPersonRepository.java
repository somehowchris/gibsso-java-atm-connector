package bi.repositories.orm;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.PersonRepository;
import bi.models.Person;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Database;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ORMPersonRepository implements PersonRepository {
  @Override
  public Person find(Person obj) {
    return null;
  }

  @Override
  public Person save(Person obj) {
    Transaction transaction = DatabaseController.session.beginTransaction();
    try {
      String id = (String) DatabaseController.session.save(obj);
      transaction.commit();
      return findById(id);
    } catch (Exception e){
      // TODO cloudn't save exception
      transaction.rollback();
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void update(Person obj) {
    Transaction transaction = DatabaseController.session.beginTransaction();
    try{
      DatabaseController.session.update(obj);
      transaction.commit();
    }catch(Exception e){
      e.printStackTrace();
      transaction.rollback();
    }
  }

  @Override
  public void delete(Person obj) {

  }

  @Override
  public Person findById(String id) {
    CriteriaBuilder criteriaBuilder = DatabaseController.session.getCriteriaBuilder();
    CriteriaQuery<Person> personCriteriaQuery = criteriaBuilder.createQuery(Person.class);

    Root<Person> personList = personCriteriaQuery.from(Person.class);

    personCriteriaQuery.select(personList);
    personCriteriaQuery.where(criteriaBuilder.equal(personList.get("id"), id));

    Query personQuery = DatabaseController.session.createQuery(personCriteriaQuery);
    return (Person) personQuery.getSingleResult();
  }
}
