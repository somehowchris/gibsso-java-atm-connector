package ch.bbzsogr.bi.services;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.repositories.BillCollectionRepository;
import ch.bbzsogr.bi.interfaces.services.BillCollectionServiceInterface;
import ch.bbzsogr.bi.models.BillCollection;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.LoggingUtil;

import java.util.logging.Logger;

@Service(api = ApiType.DIRECT)
public class BillCollectionService implements BillCollectionServiceInterface {

  private BillCollectionRepository billCollectionRepository = Container.getRepository(BillCollectionRepository.class, DatabaseController.type);

  private Logger logger = new LoggingUtil(BillCollectionService.class).getLogger();

  // TODO move to void
  public BillCollection updateBillCollection(BillCollection bills) throws EntityUpdateException {
    logger.info("Updating the bill collection "+bills);
    this.billCollectionRepository.update(bills);
    return this.getBillCollection(bills.getId());
  }

  public BillCollection getBillCollection(String id) {
    logger.info("Getting the bill collection "+id);
    return this.billCollectionRepository.find(id);
  }
}
