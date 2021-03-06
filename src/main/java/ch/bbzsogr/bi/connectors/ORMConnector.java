package ch.bbzsogr.bi.connectors;

import ch.bbzsogr.bi.exceptions.AccessNotGrantedException;
import ch.bbzsogr.bi.exceptions.ConnectionRefusedException;
import ch.bbzsogr.bi.interfaces.Connector;
import ch.bbzsogr.bi.models.configs.ORMConfig;
import ch.bbzsogr.bi.models.enums.ORMSupportedDatabases;
import ch.bbzsogr.bi.utils.LoggingUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Orm connector.
 */
public class ORMConnector implements Connector {

  private ORMConfig config;
  private SessionFactory sessionFactory;

  private Logger logger = new LoggingUtil(FSConnector.class).getLogger();

  /**
   * Instantiates a new Orm connector.
   *
   * @param config the config
   * @throws AccessNotGrantedException  the access not granted exception
   * @throws IOException                the io exception
   * @throws ConnectionRefusedException the connection refused exception
   */
  public ORMConnector(ORMConfig config) throws AccessNotGrantedException, IOException, ConnectionRefusedException {
    this.config = config;
    this.setUp();
  }

  /**
   * Connects to database
   *
   * @return
   * @throws ConnectionRefusedException
   * @throws AccessNotGrantedException
   */
  public SessionFactory connect() throws ConnectionRefusedException, AccessNotGrantedException {
    logger.info("Preparing to connect to the database");

    Reflections reflections = new Reflections("ch.bbzsogr.bi.models");

    Configuration config = new Configuration();
    config.setProperty("hibernate.connection.driver_class", this.config.getType().getDialect());
    config.setProperty("hibernate.connection.url", this.config.getUrl());
    if (this.config.getUsername() != null)
      config.setProperty("hibernate.connection.username", this.config.getUsername());
    if (this.config.getPassword() != null)
      config.setProperty("hibernate.connection.password", this.config.getPassword());
    config.setProperty("hibernate.connection.pool_size", "10");
    config.setProperty("hibernate.current_session_context_class", "thread");
    config.setProperty("hibernate.hbm2ddl.auto", "update");
    config.setProperty("hibernate.dbcp.initialSize", "5");
    config.setProperty("hibernate.dbcp.maxTotal", "20");
    config.setProperty("hibernate.dbcp.maxIdle", "10");
    config.setProperty("hibernate.dbcp.minIdle", "5");
    config.setProperty("hibernate.dbcp.maxWaitMillis", "-1");
    config.setProperty("hibernate.dialect", this.config.getType().getHibernateDialect());
    config.setProperty("hibernate.show_sql", "true");
    config.setProperty("hibernate.format_sql", "true");
    config.setProperty("hibernate.use_sql_comments", "true");

    reflections.getTypesAnnotatedWith(javax.persistence.Entity.class).forEach(clazz -> config.addAnnotatedClass(clazz));

    logger.info("About to connect to the database");

    return config.buildSessionFactory();
  }

  /**
   * Sets up the database
   *
   * @return
   * @throws IOException
   * @throws AccessNotGrantedException
   * @throws ConnectionRefusedException
   */
  public boolean setUp() throws IOException, AccessNotGrantedException, ConnectionRefusedException {
    logger.info("Setting the database up");

    if (this.config.getType() == ORMSupportedDatabases.SQLite && !this.config.getUrl().equals("jdbc:sqlite::memory:")) {
      File file = new File(this.config.getUrl().replace("jdbc:sqlite://", "").replace("jdbc:sqlite:", ""));
      if (!file.exists()) file.createNewFile();
    }

    SessionFactory sf = connect();
    sf.openSession().close();

    logger.info("Database successfully setup");

    return true;
  }
}
