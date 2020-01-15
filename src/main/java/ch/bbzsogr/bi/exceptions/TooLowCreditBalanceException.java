package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.enums.Currency;

public class TooLowCreditBalanceException extends Exception {

  private double balance;
  private double amount;
  private Currency currency;

  public TooLowCreditBalanceException(double balance, double amount, Currency currency) {
    super("Could not transfer " + amount + " " + currency.name() + " from an account with a balance of" + balance);
    this.balance = balance;
    this.amount = amount;
    this.currency = currency;
  }

  public double getBalance() {
    return balance;
  }

  public double getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }
}
