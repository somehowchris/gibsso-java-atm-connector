package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.enums.Currency;

/**
 * The Sub zero transaction amount Exception
 */
public class SubZeroTransactionAmountException extends Exception {

  private double amount;
  private Currency currency;

  /**
   * Initiates a new Sub zero transaction amount Exception
   *
   * @param amount
   * @param currency
   */
  public SubZeroTransactionAmountException(double amount, Currency currency) {
    super("Could not process a transaction with " + amount + " " + currency.name());
    this.amount = amount;
    this.currency = currency;
  }

  /**
   * Gets the amount
   *
   * @return
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the currency
   *
   * @return
   */
  public Currency getCurrency() {
    return currency;
  }
}
