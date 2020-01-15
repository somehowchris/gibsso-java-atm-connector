package bi.services;

import bi.controllers.DatabaseController;
import bi.decorators.Service;
import bi.interfaces.repositories.BancomatRepository;
import bi.models.Bancomat;
import bi.models.BillCollection;
import bi.utils.Container;

import java.util.List;

@Service()
public class BancomatService {

  BancomatRepository bancomatRepository = Container.getRepository(BancomatRepository.class, DatabaseController.type);

  public Bancomat getBancomat(String id){
    return bancomatRepository.find(id);
  }

  public List<BillCollection> getAvailableBills(String bancomatId){
    return this.getBancomat(bancomatId).getBillCollections();
  }

  public Bancomat updateBancomat(Bancomat bancomat){
    this.bancomatRepository.update(bancomat);
    return this.getBancomat(bancomat.getId());
  }

  public Bancomat registerBancomat(String location){
    Bancomat bancomat = new Bancomat(location, null);
    return this.bancomatRepository.save(bancomat);
  }
}
