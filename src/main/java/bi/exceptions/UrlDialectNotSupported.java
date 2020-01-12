package bi.exceptions;

/**
 * The type Url dialect not supported.
 */
public class UrlDialectNotSupported extends Exception {
  /**
   * Instantiates a new Url dialect not supported.
   *
   * @param dialect      the dialect
   * @param databaseType the database type
   */
  public UrlDialectNotSupported(String dialect, String databaseType) {
    super("The dialect "+dialect+" is not supported for "+databaseType);
  }
}
