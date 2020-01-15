package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.repositories.BancomatRepository;
import ch.bbzsogr.bi.interfaces.services.BancomatServiceInterface;
import ch.bbzsogr.bi.models.Bancomat;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;

import java.util.List;

@Service(api = ApiType.DIRECT)
public class BancomatService implements BancomatServiceInterface {

  private BancomatRepository bancomatRepository = Container.getRepository(BancomatRepository.class, DatabaseController.type);

  public Bancomat getBancomat(String id) {
    return bancomatRepository.find(id);
  }

  public List<BillCollection> getAvailableBills(String bancomatId) {
    return this.getBancomat(bancomatId).getBillCollections();
  }

  public Bancomat updateBancomat(Bancomat bancomat) throws EntityUpdateException {
    this.bancomatRepository.update(bancomat);
    return this.getBancomat(bancomat.getId());
  }

  public Bancomat registerBancomat(String location) throws EntitySaveException {
    Bancomat bancomat = new Bancomat(location, null, null);
    return this.bancomatRepository.save(bancomat);
  }
}
