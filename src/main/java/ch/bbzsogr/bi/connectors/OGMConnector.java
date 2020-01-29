package ch.bbzsogr.bi.connectors;

import ch.bbzsogr.bi.exceptions.AccessNotGrantedException;
import ch.bbzsogr.bi.exceptions.ConnectionRefusedException;
import ch.bbzsogr.bi.exceptions.OGMNotYetSupportedException;
import ch.bbzsogr.bi.interfaces.Connector;
import ch.bbzsogr.bi.models.configs.OGMConfig;
import ch.bbzsogr.bi.utils.LoggingUtil;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.util.logging.Logger;

/**
 * The type Ogm connector.
 */
public class OGMConnector implements Connector {

  private OGMConfig config;

  private Logger logger = new LoggingUtil(FSConnector.class).getLogger();

  /**
   * Instantiates a new Ogm connector.
   *
   * @param config the config
   * @throws OGMNotYetSupportedException the ogm not yet supported exception
   */
  public OGMConnector(OGMConfig config) throws OGMNotYetSupportedException {
    /***this.config = config;
     this.setUp();**/
    throw new OGMNotYetSupportedException();
  }

  /**
   * Connects to datasource
   *
   * @return
   * @throws ConnectionRefusedException
   * @throws AccessNotGrantedException
   */
  public SessionFactory connect() throws ConnectionRefusedException, AccessNotGrantedException {
    logger.info("Preparing to connect to the datasource");
    Reflections reflections = new Reflections("ch.bbzsogr.bi.models");

    Configuration config = new Configuration();
    config.setProperty("hibernate.ogm.datastore.provider", this.config.getType().getName());
    config.setProperty("hibernate.ogm.datastore.database", this.config.getDatabase());
    config.setProperty("hibernate.ogm.datastore.create_database", "true");
    config.setProperty("javax.persistence.provider", "org.hibernate.ogm.jpa.HibernateOgmPersistence");
    config.setProperty("javax.persistence.transactionType", "RESOURCE_LOCAL");
    config.setProperty("hibernate.ogm.datastore.host", this.config.getHost());
    config.setProperty("hibernate.archive.autodetection", "");

    reflections.getTypesAnnotatedWith(javax.persistence.Entity.class).forEach(clazz -> config.addAnnotatedClass(clazz));
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

    logger.info("About to connect to the datasource");

    return config.buildSessionFactory(serviceRegistry);
  }

  /**
   * Sets up datasource
   *
   * @return
   * @throws AccessNotGrantedException
   * @throws ConnectionRefusedException
   */
  public boolean setUp() throws AccessNotGrantedException, ConnectionRefusedException {
    logger.info("Setting the datasource up");

    SessionFactory sf = connect();
    sf.openSession().close();

    logger.info("Datasource successfully setup");
    return true;
  }
}
