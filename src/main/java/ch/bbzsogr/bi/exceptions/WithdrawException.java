package ch.bbzsogr.bi.exceptions;

public class WithdrawException extends Exception {
  public WithdrawException() {
    super("Withdraw exception. Rolling back.");
  }
}
