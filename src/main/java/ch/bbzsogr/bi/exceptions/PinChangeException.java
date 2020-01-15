package ch.bbzsogr.bi.exceptions;

public class PinChangeException extends Exception {
  public PinChangeException() {
    super("Could not save pin change");
  }
}
