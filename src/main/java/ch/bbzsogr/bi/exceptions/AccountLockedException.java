package ch.bbzsogr.bi.exceptions;

/**
 * The Account locked Exception
 */
public class AccountLockedException extends Exception {

  private String iban;

  /**
   * Instantiates a new Account locked Exception
   *
   * @param iban
   */
  public AccountLockedException(String iban) {
    super("The account " + iban + " seems locked");
    this.iban = iban;
  }

  /**
   * Gets the Iban
   *
   * @return
   */
  public String getIban() {
    return iban;
  }
}
