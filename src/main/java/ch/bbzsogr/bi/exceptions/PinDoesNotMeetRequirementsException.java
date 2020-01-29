package ch.bbzsogr.bi.exceptions;

/**
 * The Pin does not meet requirements Exception
 */
public class PinDoesNotMeetRequirementsException extends Exception  {
    /**
     * Initiates a new Pin does not meet requirements Exception
     */
    public PinDoesNotMeetRequirementsException() {
    super("Your pin does not seem to be 6 digits long");
  }
}
