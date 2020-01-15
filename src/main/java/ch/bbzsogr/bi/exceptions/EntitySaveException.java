package ch.bbzsogr.bi.exceptions;

public class EntitySaveException extends Exception {
  public EntitySaveException(Class type) {
    super("Could not save entity of " + type.getSimpleName());
  }
}
