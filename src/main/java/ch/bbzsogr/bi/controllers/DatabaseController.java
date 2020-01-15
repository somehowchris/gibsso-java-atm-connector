package ch.bbzsogr.bi.controllers;

import ch.bbzsogr.bi.connectors.FSConnector;
import ch.bbzsogr.bi.connectors.OGMConnector;
import ch.bbzsogr.bi.connectors.ORMConnector;
import ch.bbzsogr.bi.exceptions.AccessNotGrantedException;
import ch.bbzsogr.bi.exceptions.ConnectionRefusedException;
import ch.bbzsogr.bi.exceptions.OGMDatabaseTypeNotFoundException;
import ch.bbzsogr.bi.exceptions.UrlDialectNotSupported;
import ch.bbzsogr.bi.interfaces.Config;
import ch.bbzsogr.bi.interfaces.Connector;
import ch.bbzsogr.bi.models.configs.FSConfig;
import ch.bbzsogr.bi.models.configs.OGMConfig;
import ch.bbzsogr.bi.models.configs.ORMConfig;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import ch.bbzsogr.bi.utils.DotEnvUtil;
import ch.bbzsogr.bi.utils.SystemUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class DatabaseController {

  public static Session session;
  public static DatabaseInterpreters type;
  private Config config;
  private Connector connector;
  private SessionFactory sessionFactory;

  public DatabaseController() throws OGMDatabaseTypeNotFoundException, UrlDialectNotSupported, AccessNotGrantedException, IOException, ConnectionRefusedException {
    DotEnvUtil envUtil = new DotEnvUtil();
    if (envUtil.get("DATABASE_OGM_TYPE") != null && !envUtil.get("DATABASE_OGM_TYPE").isEmpty()) {
      config = new OGMConfig();
      connector = new OGMConnector((OGMConfig) this.config);
      type = DatabaseInterpreters.OGM;
      sessionFactory = ((OGMConnector) connector).connect();
      session = sessionFactory.openSession();
    } else if (envUtil.get("DATABASE_URL") != null && !envUtil.get("DATABASE_URL").isEmpty()) {
      config = new ORMConfig();
      connector = new ORMConnector((ORMConfig) config);
      type = DatabaseInterpreters.ORM;
      sessionFactory = ((ORMConnector) connector).connect();
      session = sessionFactory.openSession();
    } else {
      config = new FSConfig(envUtil.get("DATABASE_DIRECTORY_PATH") != null && !envUtil.get("DATABASE_DIRECTORY_PATH").isEmpty() ? envUtil.get("DATABASE_DIRECTORY_PATH") : SystemUtil.getWorkingDirectory());
      connector = new FSConnector((FSConfig) config);
      type = DatabaseInterpreters.FS;
    }
  }

  public void seed() throws IOException {
      //List<Seed> seeds = Container.getTopLevelClasses(DatabaseController.class)
      //  .filter(classInfo -> classInfo.load().getAnnotation(Service.class) != null && t.isAssignableFrom(classInfo.load()))
  }
}
