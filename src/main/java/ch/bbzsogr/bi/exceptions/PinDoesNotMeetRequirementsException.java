package ch.bbzsogr.bi.exceptions;

public class PinDoesNotMeetRequirementsException extends Exception  {
  public PinDoesNotMeetRequirementsException() {
    super("Your pin does not seem to be 6 digits long");
  }
}
