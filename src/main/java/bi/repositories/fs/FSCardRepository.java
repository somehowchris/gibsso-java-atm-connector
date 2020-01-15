package bi.repositories.fs;

import bi.decorators.DatabaseType;
import bi.interfaces.repositories.CardRepository;
import bi.models.Card;
import bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

@DatabaseType(type= DatabaseInterpreters.FS)
public class FSCardRepository implements CardRepository {
  @Override
  public Card find(String identifier) {
    return null;
  }

  @Override
  public Card save(Card obj) {
    return null;
  }

  @Override
  public Card save(Card obj, Transaction transaction) {
    return null;
  }

  @Override
  public void update(Card obj) {

  }

  @Override
  public void update(Card obj, Transaction transaction) {

  }

  @Override
  public void delete(Card obj) {

  }
}
