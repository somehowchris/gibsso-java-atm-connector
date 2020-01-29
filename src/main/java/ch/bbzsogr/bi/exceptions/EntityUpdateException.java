package ch.bbzsogr.bi.exceptions;

/**
 * The Entitiy update Exception
 */
public class EntityUpdateException extends Exception {
    /**
     * Initiates the Entitiy update Exception
     *
     * @param type the type
     */
    public EntityUpdateException(Class type) {
    super("Could not save entity of " + type.getSimpleName());
  }
}
