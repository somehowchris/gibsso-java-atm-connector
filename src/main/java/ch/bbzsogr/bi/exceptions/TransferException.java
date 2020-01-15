package ch.bbzsogr.bi.exceptions;

public class TransferException extends Exception {
  public TransferException() {
    super("Could not save transfer. Rolling back");
  }
}
