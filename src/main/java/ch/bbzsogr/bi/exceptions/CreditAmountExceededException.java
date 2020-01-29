package ch.bbzsogr.bi.exceptions;

/**
 * The Credit amount exceeded Exception
 */
public class CreditAmountExceededException extends Exception {

  private double credit;
  private double balance;
  private double amount;

  /**
   * Initiates a new Credit amount exceeded Exception
   *
   * @param credit  the credit
   * @param balance the balance
   * @param amount  the amount
   */
  public CreditAmountExceededException(double credit, double balance, double amount) {
    super("Could not meet the requested amount of " + amount + " with a balance of " + balance + " and a credit of " + credit);
    this.credit = credit;
    this.balance = balance;
    this.amount = amount;
  }

  /**
   * Gets the Credit
   *
   * @return credit
   */
  public double getCredit() {
    return credit;
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
}
