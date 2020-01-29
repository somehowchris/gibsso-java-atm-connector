package ch.bbzsogr.bi.exceptions;

/**
 * The Bancomat not found Exception
 */
public class BancomatNotFoundException extends Exception {

  private String bancomatId;

  /**
   * Initiates a new Bancomat not found Exception
   *
   * @param bancomatId the bancomat id
   */
  public BancomatNotFoundException(String bancomatId) {
    super("Could not find a bancomat " + bancomatId);
    this.bancomatId = bancomatId;
  }

  /**
   * Gets the BancomatId
   *
   * @return bancomat id
   */
  public String getBancomatId() {
    return bancomatId;
  }
}
