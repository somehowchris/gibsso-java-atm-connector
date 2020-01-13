package bi.repositories.fs;

import bi.interfaces.repositories.CardRepository;
import bi.models.Card;
import org.hibernate.Transaction;

public class FSCardRepository implements CardRepository {
  @Override
  public Card find(Card obj) {
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
