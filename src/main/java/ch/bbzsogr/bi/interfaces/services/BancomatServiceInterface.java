package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.BillCollection;

import java.io.IOException;
import java.util.List;

/**
 * The interface Bancomat service interface.
 */
public interface BancomatServiceInterface extends ServiceInterface {

  /**
   * Gets available bills.
   *
   * @param bancomatId the bancomat id
   * @return the available bills
   * @throws Exception the exception
   */
  List<BillCollection> getAvailableBills(String bancomatId) throws Exception;

  /**
   * Register bancomat bancomat.
   *
   * @param location the location
   * @return the bancomat
   * @throws EntitySaveException the entity save exception
   * @throws IOException         the io exception
   */
  Bancomat registerBancomat(String location) throws EntitySaveException, IOException;

  /**
   * Update bancomat bancomat.
   *
   * @param bancomat the bancomat
   * @return the bancomat
   * @throws EntityUpdateException the entity update exception
   * @throws IOException           the io exception
   */
  Bancomat updateBancomat(Bancomat bancomat) throws EntityUpdateException, IOException;

}
