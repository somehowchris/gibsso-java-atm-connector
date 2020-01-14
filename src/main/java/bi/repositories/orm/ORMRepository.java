package bi.repositories.orm;

import bi.controllers.DatabaseController;
import bi.utils.LoggingUtil;
import bi.utils.TypeT;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.lang.reflect.ParameterizedType;
import java.util.logging.Logger;

public class ORMRepository<T> extends TypeT<T> implements bi.interfaces.repositories.Repository<T> {

  private Logger logger = new LoggingUtil<ORMRepository>(ORMRepository.class).getLogger();

  @Override
  public T find(String identifier) {
    logger.info("Finding "+identifier+" of type "+getTypeOfT().getSimpleName());
    return DatabaseController.session.find(getTypeOfT(), identifier);
  }

  @Override
  public T save(T obj) {
    Transaction transaction = DatabaseController.session.beginTransaction();
    try {
      T saved = this.save(obj, transaction);
      transaction.commit();
      return saved;
    } catch (Exception e){
      logger.warning("Couldn't save "+obj.toString()+" of type "+getTypeOfT().getSimpleName());
      transaction.rollback();
      // TODO couldn't save exception
      return null;
    }
  }

  @Override
  public T save(T obj, Transaction transaction) {
    logger.info("Saving "+obj.toString()+" of type "+getTypeOfT().getSimpleName());

    if(transaction.getStatus() == TransactionStatus.NOT_ACTIVE) transaction.begin();
    String identifier = (String) DatabaseController.session.save(obj);
    return (T) DatabaseController.session.find(getTypeOfT(), identifier);
  }

  @Override
  public void update(T obj) {
    Transaction transaction = DatabaseController.session.beginTransaction();
    try {
      this.update(obj, transaction);
      transaction.commit();
    } catch (Exception e){
      transaction.rollback();
      logger.warning("Couldn't update "+obj.toString()+" of type "+getTypeOfT().getSimpleName());
      // TODO couldn't update exception
    }
  }

  @Override
  public void update(T obj, Transaction transaction) {
    logger.info("Updating "+obj.toString()+" of type "+getTypeOfT().getSimpleName());
    if(transaction.getStatus() == TransactionStatus.NOT_ACTIVE) transaction.begin();
    DatabaseController.session.update(obj);
  }

  @Override
  public void delete(T obj) {
    logger.info("Deleting "+obj.toString()+" of type "+getTypeOfT().getSimpleName());
    DatabaseController.session.remove(obj);
  }
}
