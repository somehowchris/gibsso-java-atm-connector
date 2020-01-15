package ch.bbzsogr.bi.exceptions;

public class CardNotFoundException extends Exception {

  private String cardNr;

  public CardNotFoundException(String cardNr) {
    super("Could not find card with Nr. " + cardNr);
    this.cardNr = cardNr;
  }
}
