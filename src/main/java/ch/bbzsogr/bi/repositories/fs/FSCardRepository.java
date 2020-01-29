package ch.bbzsogr.bi.repositories.fs;

import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.interfaces.repositories.CardRepository;
import ch.bbzsogr.bi.models.Card;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import org.hibernate.Transaction;

/**
 * The type Fs card repository.
 */
@DatabaseType(type = DatabaseInterpreters.FS)
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

  @Override
  public Card find(String cardNr, String pin) {
    return null;
  }
}
