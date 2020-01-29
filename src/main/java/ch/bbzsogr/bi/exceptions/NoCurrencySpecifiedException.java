package ch.bbzsogr.bi.exceptions;

/**
 * The No currency specified Exception
 */
public class NoCurrencySpecifiedException extends Exception {
    /**
     * Initiates a new No currency specified Exception
     */
    public NoCurrencySpecifiedException() {
    super("There seems to be no currency specified");
  }
}
