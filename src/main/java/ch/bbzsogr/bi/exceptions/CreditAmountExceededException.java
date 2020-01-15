package ch.bbzsogr.bi.exceptions;

public class CreditAmountExceededException extends Exception {

  private double credit;
  private double balance;
  private double amount;

  public CreditAmountExceededException(double credit, double balance, double amount) {
    super("Could not meet the requested amount of " + amount + " with a balance of " + balance + " and a credit of " + credit);
    this.credit = credit;
    this.balance = balance;
    this.amount = amount;
  }

  public double getCredit() {
    return credit;
  }

  public double getBalance() {
    return balance;
  }

  public double getAmount() {
    return amount;
  }
}
