package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Account;

public class CardCreationException extends Exception {
  public CardCreationException(Account account) {
    super("Could not create a new card for " + account.getIban());
  }
}
