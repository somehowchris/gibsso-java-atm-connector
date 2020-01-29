package ch.bbzsogr.rest.exceptions;

/**
 * The type Db transactions via rest not supported.
 */
public class DBTransactionsViaRestNotSupported extends Exception {
    /**
     * Instantiates a new Db transactions via rest not supported.
     */
    public DBTransactionsViaRestNotSupported() {
    super("Database transaction can not be transmitted via the rest api. Consider checking out the direct api version.");
  }
}
