package ch.bbzsogr.bi.exceptions;

public class NoCurrencySpecifiedException extends Exception {
  public NoCurrencySpecifiedException() {
    super("There seems to be no currency specified");
  }
}
