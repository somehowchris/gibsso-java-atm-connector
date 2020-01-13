package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.AccountRepository;
import bi.interfaces.repositories.BancomatRepository;
import bi.models.Bancomat;
import bi.models.BillCollection;
import bi.utils.RepositoryUtil;

import java.util.ArrayList;

public class BancomatService {

  BancomatRepository bancomatRepository = RepositoryUtil.getRepository(BancomatRepository.class, DatabaseController.type);

  public Bancomat getBancomat(String id){
    return null;
  }

  public ArrayList<BillCollection> getAvailableBills(String bancomatId){
    return null;
  }
}
