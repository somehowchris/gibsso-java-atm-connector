package ch.bbzsogr.bi.interfaces.services;

import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.models.BillCollection;

import java.io.IOException;

public interface BillCollectionServiceInterface extends ServiceInterface {

  BillCollection getBillCollection(String id) throws Exception;

  BillCollection updateBillCollection(BillCollection bills) throws EntityUpdateException, IOException;

}
