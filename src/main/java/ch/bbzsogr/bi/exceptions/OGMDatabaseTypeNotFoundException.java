package ch.bbzsogr.bi.exceptions;

/**
 * The OGM database type not found Exception
 */
public class OGMDatabaseTypeNotFoundException extends Exception {

  /**
   * Initiates a new OGM database type not found Exception
   *
   * @param type
   */
  public OGMDatabaseTypeNotFoundException(String type) {
    super("Couldn't find the ogm supported database type " + type);
  }
}
