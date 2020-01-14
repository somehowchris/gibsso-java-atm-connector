package bi.services;

import bi.controllers.DatabaseController;
import bi.interfaces.repositories.BillCollectionRepository;
import bi.models.BillCollection;
import bi.utils.RepositoryUtil;

public class BillCollectionService {

  BillCollectionRepository billCollectionRepository = RepositoryUtil.getRepository(BillCollectionRepository.class, DatabaseController.type);

  public BillCollection updateBillCollection(BillCollection bills){
    this.billCollectionRepository.update(bills);
    return this.getBillCollection(bills.getId());
  }

  public BillCollection getBillCollection(String id){
    return this.billCollectionRepository.find(id);
  }
}
