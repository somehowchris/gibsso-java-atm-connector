package ch.bbzsogr.bi.exceptions;

/**
 * The Could not meet withdraw amount Exception
 */
public class CouldNotMeetWithdrawAmountException extends Exception {

  private double requested;
  private double available;

  /**
   * Initiates a new Could not meet withdraw amount Exception
   *
   * @param requestedAmount the requested amount
   * @param metAmount       the met amount
   */
  public CouldNotMeetWithdrawAmountException(double requestedAmount, double metAmount) {
    super("Could not meet current wanted " + requestedAmount + " with the current lowest met amount" + metAmount);
    this.requested = requestedAmount;
    this.available = metAmount;
  }

  /**
   * Gets the requested withdraw amount
   *
   * @return requested
   */
  public double getRequested() {
    return requested;
  }

  /**
   * Gets the available withdraw amount
   *
   * @return available
   */
  public double getAvailable() {
    return available;
  }
}
