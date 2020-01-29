package ch.bbzsogr.bi.exceptions;

/**
 * The Account lock Exception
 */
public class AccountLockException extends Exception {
  /**
   * Instantiates a new Account lock Exception
   *
   * @param account the account
   */
  public AccountLockException(String account) {
    super("We were not able to lock the account " + account);
  }
}
