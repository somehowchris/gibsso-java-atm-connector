package bi.repositories.fs;

import bi.interfaces.repositories.BancomatRepository;
import bi.models.Bancomat;
import org.hibernate.Transaction;

public class FSBancomatRepository implements BancomatRepository {

  @Override
  public Bancomat find(Bancomat obj) {
    return null;
  }

  @Override
  public Bancomat save(Bancomat obj) {
    return null;
  }

  @Override
  public Bancomat save(Bancomat obj, Transaction transaction) {
    return null;
  }

  @Override
  public void update(Bancomat obj) {

  }

  @Override
  public void update(Bancomat obj, Transaction transaction) {

  }

  @Override
  public void delete(Bancomat obj) {

  }
}
