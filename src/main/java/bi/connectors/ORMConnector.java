package bi.connectors;

import bi.exceptions.AccessNotGrantedException;
import bi.exceptions.ConnectionRefusedException;
import bi.interfaces.Connector;
import bi.models.configs.ORMConfig;
import bi.models.enums.ORMSupportedDatabases;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;

/**
 * The type Orm connector.
 */
public class ORMConnector implements Connector {

  private ORMConfig config;
  private SessionFactory sessionFactory;

  /**
   * Instantiates a new Orm connector.
   *
   * @param config the config
   */
  public ORMConnector(ORMConfig config) throws AccessNotGrantedException, IOException, ConnectionRefusedException {
    this.config = config;
    this.setUp();
  }

  public SessionFactory connect() throws ConnectionRefusedException, AccessNotGrantedException {
    Reflections reflections = new Reflections("bi.models");

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

    return config.buildSessionFactory();
  }

  public boolean setUp() throws IOException, AccessNotGrantedException, ConnectionRefusedException {
    if (this.config.getType() == ORMSupportedDatabases.SQLite && !this.config.getUrl().equals("jdbc:sqlite::memory:")) {
      File file = new File(this.config.getUrl().replace("jdbc:sqlite://", "").replace("jdbc:sqlite:", ""));
      if (!file.exists()) file.createNewFile();
    }
    SessionFactory sf = connect();
    sf.openSession().close();
    return true;
  }
}
