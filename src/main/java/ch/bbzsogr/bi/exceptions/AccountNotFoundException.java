package ch.bbzsogr.bi.exceptions;

public class AccountNotFoundException extends Exception {

  private String iban;

  public AccountNotFoundException(String iban) {
    super("Account with iban " + iban + " not found");
    this.iban = iban;
  }

  public String getIban() {
    return iban;
  }
}
