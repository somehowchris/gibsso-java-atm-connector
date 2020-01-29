package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.Account;

/**
 * The Card creation Exception
 */
public class CardCreationException extends Exception {
    /**
     * Initiates a new Card creation Exception
     *
     * @param account the account
     */
    public CardCreationException(Account account) {
    super("Could not create a new card for " + account.getIban());
  }
}
