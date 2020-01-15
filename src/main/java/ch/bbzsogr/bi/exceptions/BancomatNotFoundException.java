package ch.bbzsogr.bi.exceptions;

public class BancomatNotFoundException extends Exception {

  private String bancomatId;

  public BancomatNotFoundException(String bancomatId) {
    super("Could not find a bancomat " + bancomatId);
    this.bancomatId = bancomatId;
  }

  public String getBancomatId() {
    return bancomatId;
  }
}
