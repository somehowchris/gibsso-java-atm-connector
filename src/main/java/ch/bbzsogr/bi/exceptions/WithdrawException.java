package ch.bbzsogr.bi.exceptions;

/**
 * The Withdraw Exception
 */
public class WithdrawException extends Exception {
  /**
   * Initiates a new Withdraw Exception
   */
  public WithdrawException() {
    super("Withdraw exception. Rolling back.");
  }
}
