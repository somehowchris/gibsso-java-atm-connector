package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.BillCollection;

import java.io.IOException;
import java.util.List;

public interface BancomatServiceInterface extends ServiceInterface {

  List<BillCollection> getAvailableBills(String bancomatId) throws Exception;

  Bancomat registerBancomat(String location) throws EntitySaveException, IOException;

  Bancomat updateBancomat(Bancomat bancomat) throws EntityUpdateException, IOException;

}
