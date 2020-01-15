package bi.exceptions;

public class OGMDatabaseTypeNotFoundException extends Exception {

  public OGMDatabaseTypeNotFoundException(String type) {
    super("Couldn't find the ogm supported database type " + type);
  }
}
