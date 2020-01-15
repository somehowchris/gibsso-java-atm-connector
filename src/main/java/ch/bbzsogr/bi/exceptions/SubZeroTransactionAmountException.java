package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.enums.Currency;

public class SubZeroTransactionAmountException extends Exception {

  private double amount;
  private Currency currency;

  public SubZeroTransactionAmountException(double amount, Currency currency) {
    super("Could not process a transaction with " + amount + " " + currency.name());
    this.amount = amount;
    this.currency = currency;
  }

  public double getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }
}
