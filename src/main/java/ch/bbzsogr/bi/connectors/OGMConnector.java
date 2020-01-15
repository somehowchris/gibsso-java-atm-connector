package ch.bbzsogr.bi.connectors;

import ch.bbzsogr.bi.exceptions.AccessNotGrantedException;
import ch.bbzsogr.bi.exceptions.ConnectionRefusedException;
import ch.bbzsogr.bi.interfaces.Connector;
import ch.bbzsogr.bi.models.configs.OGMConfig;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

/**
 * The type Ogm connector.
 */
public class OGMConnector implements Connector {

  private OGMConfig config;

  /**
   * Instantiates a new Ogm connector.
   *
   * @param config the config
   */
  public OGMConnector(OGMConfig config) {
    this.config = config;
    this.setUp();
  }

  public SessionFactory connect() throws ConnectionRefusedException, AccessNotGrantedException {
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
    return config.buildSessionFactory(serviceRegistry);
  }

  public boolean setUp() {
    return false;
  }
}
