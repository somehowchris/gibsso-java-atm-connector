package ch.bbzsogr.bi.exceptions;

/**
 * The Transfer Exception
 */
public class TransferException extends Exception {
  /**
   * Initiates a new Transfer Exception
   */
  public TransferException() {
    super("Could not save transfer. Rolling back");
  }
}
