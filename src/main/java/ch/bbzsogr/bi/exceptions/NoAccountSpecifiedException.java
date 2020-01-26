package ch.bbzsogr.bi.exceptions;

/**
 * THe No account Specified Exception
 */
public class NoAccountSpecifiedException extends Exception {
  /**
   * Initiates a new No account Specified Exception
   */
  public NoAccountSpecifiedException() {
    super("There seems to be no account specified");
  }
}
