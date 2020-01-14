package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.BancomatRepository;
import bi.models.Bancomat;
import bi.models.BillCollection;
import bi.utils.RepositoryUtil;

import java.util.List;

public class BancomatService {

  BancomatRepository bancomatRepository = RepositoryUtil.getRepository(BancomatRepository.class, DatabaseController.type);

  public Bancomat getBancomat(String id){
    return bancomatRepository.find(id);
  }

  public List<BillCollection> getAvailableBills(String bancomatId){
    return getBancomat(bancomatId).getBillCollections();
  }

  public Bancomat updateBancomat(Bancomat bancomat){
    bancomatRepository.update(bancomat);
    return getBancomat(bancomat.getId());
  }

  public Bancomat registerBancomat(String location){
    Bancomat bancomat = new Bancomat(location, null);
    return bancomatRepository.save(bancomat);
  }
}
