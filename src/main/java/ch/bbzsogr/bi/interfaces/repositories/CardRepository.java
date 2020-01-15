package ch.bbzsogr.bi.interfaces.repositories;

import ch.bbzsogr.bi.models.Card;

public interface CardRepository extends Repository<Card> {
  Card find(String cardNr, String pin);
}
