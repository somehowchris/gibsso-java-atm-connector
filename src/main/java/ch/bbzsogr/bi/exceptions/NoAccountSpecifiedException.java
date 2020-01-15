package ch.bbzsogr.bi.exceptions;

public class NoAccountSpecifiedException extends Exception {
  public NoAccountSpecifiedException() {
    super("There seems to be no account specified");
  }
}
