package ch.bbzsogr.bi.exceptions;

import ch.bbzsogr.bi.models.enums.Currency;

/**
 * The Too low credit balance Exception
 */
public class TooLowCreditBalanceException extends Exception {

  private double balance;
  private double amount;
  private Currency currency;

  /**
   * Initiates a new Too low credit balance Exception
   *
   * @param balance  the balance
   * @param amount   the amount
   * @param currency the currency
   */
  public TooLowCreditBalanceException(double balance, double amount, Currency currency) {
    super("Could not transfer " + amount + " " + currency.name() + " from an account with a balance of" + balance);
    this.balance = balance;
    this.amount = amount;
    this.currency = currency;
  }

  /**
   * Gets the balance
   *
   * @return balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Gets the amount
   *
   * @return amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the currency
   *
   * @return currency
   */
  public Currency getCurrency() {
    return currency;
  }
}
