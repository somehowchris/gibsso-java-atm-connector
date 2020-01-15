package ch.bbzsogr.bi.exceptions;

public class AccountLockException extends Exception {
  public AccountLockException(String account) {
    super("We were not able to lock the account " + account);
  }
}
