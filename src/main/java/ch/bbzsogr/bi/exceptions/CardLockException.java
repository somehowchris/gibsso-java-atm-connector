package ch.bbzsogr.bi.exceptions;

public class CardLockException extends Exception {

  private String cardNr;

  public CardLockException(String nr) {
    super("Could not lock card Nr. " + nr);
    this.cardNr = nr;
  }
}
