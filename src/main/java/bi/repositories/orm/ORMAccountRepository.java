package bi.repositories.orm;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.AccountRepository;
import bi.models.Account;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ORMAccountRepository implements AccountRepository {


  @Override
  public Account findByIban(String iban) {
    CriteriaBuilder criteriaBuilder = DatabaseController.session.getCriteriaBuilder();
    CriteriaQuery<Account> accountCriteriaQuery = criteriaBuilder.createQuery(Account.class);

    Root<Account> accountRoot = accountCriteriaQuery.from(Account.class);

    accountCriteriaQuery.select(accountRoot);
    accountCriteriaQuery.where(criteriaBuilder.equal(accountRoot.get("iban"), iban));

    Query personQuery = DatabaseController.session.createQuery(accountCriteriaQuery);
    return (Account) personQuery.getSingleResult();
  }

  @Override
  public Account find(Account obj) {
    return null;
  }

  @Override
  public Account save(Account obj) {
    Transaction transaction = DatabaseController.session.beginTransaction();
    try {
      String iban = (String) DatabaseController.session.save(obj);
      transaction.commit();
      return findByIban(iban);
    } catch (Exception e){
      // TODO cloudn't save exception
      transaction.rollback();
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void update(Account obj) {

  }

  @Override
  public void delete(Account obj) {

  }
}
