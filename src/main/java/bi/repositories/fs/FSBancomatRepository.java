package bi.repositories.fs;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.BancomatRepository;
import bi.models.Bancomat;
import bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

@DatabaseType(type= DatabaseInterpreters.FS)
public class FSBancomatRepository implements BancomatRepository {

  @Override
  public Bancomat find(String identifier) {
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
