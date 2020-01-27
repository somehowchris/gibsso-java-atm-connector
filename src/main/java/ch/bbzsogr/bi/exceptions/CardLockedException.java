package ch.bbzsogr.bi.exceptions;

/**
 * The Card locked Exception
 */
public class CardLockedException extends Exception {

  private String cardNr;

  /**
   * Initiates a new Card locked Exception
   *
   * @param cardNr
   */
  public CardLockedException(String cardNr) {
    super("The card Nr. " + cardNr + " is locked");
    this.cardNr = cardNr;
  }

  /**
   * Gets the CardNr
   *
   * @return
   */
  public String getCardNr() {
    return cardNr;
  }
}
