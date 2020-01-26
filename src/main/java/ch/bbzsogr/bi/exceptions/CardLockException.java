package ch.bbzsogr.bi.exceptions;

/**
 * The Card lock Exception
 */
public class CardLockException extends Exception {

  private String cardNr;

  /**
   * Initiates a new Card lock Exception
   *
   * @param nr
   */
  public CardLockException(String nr) {
    super("Could not lock card Nr. " + nr);
    this.cardNr = nr;
  }
}
