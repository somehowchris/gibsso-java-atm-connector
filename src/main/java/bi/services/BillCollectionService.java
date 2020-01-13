package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.AccountRepository;
import bi.interfaces.repositories.BillCollectionRepository;
import bi.models.BillCollection;
import bi.utils.RepositoryUtil;

public class BillCollectionService {

  BillCollectionRepository billCollectionRepository = RepositoryUtil.getRepository(BillCollectionRepository.class, DatabaseController.type);

  public BillCollection updateBillCollection(BillCollection bills){
    return null;
  }

  public BillCollection getBillCollection(String id){
    return null;
  }
}
