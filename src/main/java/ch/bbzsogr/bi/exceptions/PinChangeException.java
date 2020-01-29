package ch.bbzsogr.bi.exceptions;

/**
 * The Pin change Exception
 */
public class PinChangeException extends Exception {
    /**
     * Initiates a new Pin change Exception
     */
    public PinChangeException() {
    super("Could not save pin change");
  }
}
