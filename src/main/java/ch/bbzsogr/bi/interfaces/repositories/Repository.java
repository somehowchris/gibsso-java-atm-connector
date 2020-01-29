package ch.bbzsogr.bi.interfaces.repositories;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import org.hibernate.Transaction;

/**
 * The interface Repository.
 *
 * @param <T> the type parameter
 */
public interface Repository<T> {

  /**
   * Find t.
   *
   * @param identifier the identifier
   * @return the t
   */
  T find(String identifier);

  /**
   * Save t.
   *
   * @param obj the obj
   * @return the t
   * @throws EntitySaveException the entity save exception
   */
  T save(T obj) throws EntitySaveException;

  /**
   * Save t.
   *
   * @param obj         the obj
   * @param transaction the transaction
   * @return the t
   */
  T save(T obj, Transaction transaction);

  /**
   * Update.
   *
   * @param obj the obj
   * @throws EntityUpdateException the entity update exception
   */
  void update(T obj) throws EntityUpdateException;

  /**
   * Update.
   *
   * @param obj         the obj
   * @param transaction the transaction
   */
  void update(T obj, Transaction transaction);

  /**
   * Delete.
   *
   * @param obj the obj
   */
  void delete(T obj);

}
