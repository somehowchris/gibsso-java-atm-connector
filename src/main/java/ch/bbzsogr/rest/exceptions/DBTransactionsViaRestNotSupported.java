package ch.bbzsogr.rest.exceptions;

public class DBTransactionsViaRestNotSupported extends Exception {
  public DBTransactionsViaRestNotSupported() {
    super("Database transaction can not be transmitted via the rest api. Consider checking out the direct api version.");
  }
}
