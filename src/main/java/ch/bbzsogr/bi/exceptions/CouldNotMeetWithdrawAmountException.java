package ch.bbzsogr.bi.exceptions;

public class CouldNotMeetWithdrawAmountException extends Exception {

  private double requested;
  private double available;

  public CouldNotMeetWithdrawAmountException(double requestedAmount, double metAmount) {
    super("Could not meet current wanted " + requestedAmount + " with the current lowest met amount" + metAmount);
    this.requested = requestedAmount;
    this.available = metAmount;
  }

  public double getRequested() {
    return requested;
  }

  public double getAvailable() {
    return available;
  }
}
