package ch.bbzsogr.bi.exceptions;

/**
 * The Entity save Exception
 */
public class EntitySaveException extends Exception {
  /**
   * Initiates a new Entitiy save Exception
   *
   * @param type the type
   */
  public EntitySaveException(Class type) {
    super("Could not save entity of " + type.getSimpleName());
  }
}
