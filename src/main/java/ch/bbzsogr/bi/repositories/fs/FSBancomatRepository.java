package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.BancomatRepository;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

/**
 * The type Fs bancomat repository.
 */
@DatabaseType(type = DatabaseInterpreters.FS)
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
