package bi.services;

import bi.controllers.DatabaseController;
import bi.decorators.Service;
import bi.interfaces.repositories.BillCollectionRepository;
import bi.models.BillCollection;
import bi.utils.Container;

@Service()
public class BillCollectionService {

  BillCollectionRepository billCollectionRepository = Container.getRepository(BillCollectionRepository.class, DatabaseController.type);

  public BillCollection updateBillCollection(BillCollection bills){
    this.billCollectionRepository.update(bills);
    return this.getBillCollection(bills.getId());
  }

  public BillCollection getBillCollection(String id){
    return this.billCollectionRepository.find(id);
  }
}
