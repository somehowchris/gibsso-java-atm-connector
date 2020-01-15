package ch.bbzsogr.bi.exceptions;

public class AccountLockedException extends Exception {

  private String iban;

  public AccountLockedException(String iban) {
    super("The account " + iban + " seems locked");
    this.iban = iban;
  }

  public String getIban() {
    return iban;
  }
}
