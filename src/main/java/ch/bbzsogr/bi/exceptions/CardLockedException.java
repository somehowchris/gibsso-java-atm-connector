package ch.bbzsogr.bi.exceptions;

public class CardLockedException extends Exception {

  private String cardNr;

  public CardLockedException(String cardNr) {
    super("The card Nr. " + cardNr + " is locked");
    this.cardNr = cardNr;
  }

  public String getCardNr() {
    return cardNr;
  }
}
