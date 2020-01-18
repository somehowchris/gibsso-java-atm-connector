package ch.bbzsogr.bi.repositories.orm;

import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.exceptions.EntityUpdateException;
import ch.bbzsogr.bi.utils.LoggingUtil;
import ch.bbzsogr.bi.utils.TypeT;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.logging.Logger;

public class ORMRepository<T> extends TypeT<T> implements ch.bbzsogr.bi.interfaces.repositories.Repository<T> {

  private Logger logger = new LoggingUtil<ORMRepository>(ORMRepository.class).getLogger();
  private Session session = DatabaseController.session;

  public ORMRepository() {
    this.session.setCacheMode(CacheMode.IGNORE);
  }

  public ORMRepository(Session session) {
    this.session = session;
    this.session.setCacheMode(CacheMode.IGNORE);
  }

  @Override
  public T find(String identifier) {
    logger.info("Finding " + identifier + " of type " + getTypeOfT().getSimpleName());
    return getLocalSession().find(getTypeOfT(), identifier);
  }

  @Override
  public T save(T obj) throws EntitySaveException {
    Session session = getSession();
    Transaction transaction = session.beginTransaction();

    try {
      T saved = this.save(obj, transaction);
      transaction.commit();
      return saved;
    } catch (Exception e) {
      e.printStackTrace();
      logger.warning(e.getMessage());
      logger.warning("Couldn't save " + obj.toString() + " of type " + getTypeOfT().getSimpleName());
      transaction.rollback();
      throw new EntitySaveException(getTypeOfT());
    }
  }

  @Override
  public T save(T obj, Transaction transaction) {
    logger.info("Saving " + obj.toString() + " of type " + getTypeOfT().getSimpleName());

    if (transaction.getStatus() == TransactionStatus.NOT_ACTIVE) transaction.begin();
    Session session = getSession();

    String identifier = (String) session.save(obj);
    T object = session.find(getTypeOfT(), identifier);

    return object;
  }

  @Override
  public void update(T obj) throws EntityUpdateException {
    Session session = getSession();
    Transaction transaction = session.beginTransaction();

    try {
      this.update(obj, transaction);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      transaction.rollback();
      logger.warning(e.getMessage());
      logger.warning("Couldn't update " + obj.toString() + " of type " + getTypeOfT().getSimpleName());
      throw new EntityUpdateException(getTypeOfT());
    }
  }

  @Override
  public void update(T obj, Transaction transaction) {
    logger.info("Updating " + obj.toString() + " of type " + getTypeOfT().getSimpleName());
    if (transaction.getStatus() == TransactionStatus.NOT_ACTIVE) transaction.begin();
    session.update(obj);
  }

  @Override
  public void delete(T obj) {
    logger.info("Deleting " + obj.toString() + " of type " + getTypeOfT().getSimpleName());
    session.remove(obj);
  }

  public Session getSession() {
    return session;
  }

  public Session getLocalSession(){
    return this.session;
  }
}
