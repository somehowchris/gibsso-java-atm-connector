package ch.bbzsogr.bi.exceptions;

/**
 * The Account not found Exception
 */
public class AccountNotFoundException extends Exception {

  private String iban;

  /**
   * Initiates a new Account not found Exception
   *
   * @param iban
   */
  public AccountNotFoundException(String iban) {
    super("Account with iban " + iban + " not found");
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
