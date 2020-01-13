package bi.repositories.orm;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.BancomatRepository;
import bi.models.Account;
import bi.models.Bancomat;
import bi.models.Person;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ORMBancomatRepository implements BancomatRepository {
  @Override
  public Bancomat find(Bancomat obj) {
    return null;
  }

  @Override
  public Bancomat save(Bancomat obj) {
    return  null;
  }

  @Override
  public void update(Bancomat obj) {

  }

  @Override
  public void delete(Bancomat obj) {

  }
}
