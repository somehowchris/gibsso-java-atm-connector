package ch.bbzsogr.bi.exceptions;

/**
 * The Card locked Exception
 */
public class CardLockedException extends Exception {

  private String cardNr;

  /**
   * Initiates a new Card locked Exception
   *
   * @param cardNr the card nr
   */
  public CardLockedException(String cardNr) {
    super("The card Nr. " + cardNr + " is locked");
    this.cardNr = cardNr;
  }

  /**
   * Gets the CardNr
   *
   * @return card nr
   */
  public String getCardNr() {
    return cardNr;
  }
}
