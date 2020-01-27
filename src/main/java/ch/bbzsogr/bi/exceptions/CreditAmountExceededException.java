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
   * @param credit
   * @param balance
   * @param amount
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
   * @return
   */
  public double getCredit() {
    return credit;
  }

  /**
   * Gets the balance
   *
   * @return
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Gets the amount
   *
   * @return
   */
  public double getAmount() {
    return amount;
  }
}
