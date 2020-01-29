package ch.bbzsogr.bi.interfaces.repositories;

import ch.bbzsogr.bi.models.Card;

/**
 * The interface Card repository.
 */
public interface CardRepository extends Repository<Card> {

  /**
   * Find card.
   *
   * @param cardNr the card nr
   * @param pin    the pin
   * @return the card
   */
  Card find(String cardNr, String pin);

}
