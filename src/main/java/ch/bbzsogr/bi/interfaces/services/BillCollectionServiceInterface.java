package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.BillCollection;

import java.io.IOException;

/**
 * The interface Bill collection service interface.
 */
public interface BillCollectionServiceInterface extends ServiceInterface {

  /**
   * Gets bill collection.
   *
   * @param id the id
   * @return the bill collection
   * @throws Exception the exception
   */
  BillCollection getBillCollection(String id) throws Exception;

  /**
   * Update bill collection bill collection.
   *
   * @param bills the bills
   * @return the bill collection
   * @throws EntityUpdateException the entity update exception
   * @throws IOException           the io exception
   */
  BillCollection updateBillCollection(BillCollection bills) throws EntityUpdateException, IOException;

}
