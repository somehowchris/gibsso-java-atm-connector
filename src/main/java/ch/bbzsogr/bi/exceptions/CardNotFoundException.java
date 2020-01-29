package ch.bbzsogr.bi.exceptions;

/**
 * The Card not found Exception
 */
public class CardNotFoundException extends Exception {

  private String cardNr;

    /**
     * Initiates a new Card not Found Exception
     *
     * @param cardNr the card nr
     */
    public CardNotFoundException(String cardNr) {
    super("Could not find card with Nr. " + cardNr);
    this.cardNr = cardNr;
  }
}
