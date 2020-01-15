package ch.bbzsogr.bi.exceptions;

public class EntityUpdateException extends Exception {
  public EntityUpdateException(Class type) {
    super("Could not save entity of " + type.getSimpleName());
  }
}
