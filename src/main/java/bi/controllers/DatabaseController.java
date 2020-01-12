package bi.controllers;

import bi.connectors.FSConnector;
import bi.connectors.OGMConnector;
import bi.connectors.ORMConnector;
import bi.exceptions.AccessNotGrantedException;
import bi.exceptions.ConnectionRefusedException;
import bi.exceptions.OGMDatabaseTypeNotFoundException;
import bi.exceptions.UrlDialectNotSupported;
import bi.interfaces.Config;
import bi.interfaces.Connector;
import bi.models.configs.FSConfig;
import bi.models.configs.OGMConfig;
import bi.models.configs.ORMConfig;
import bi.models.enums.DatabaseType;
import bi.utils.DotEnvUtil;
import bi.utils.SystemUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class DatabaseController {

  private Config config;
  private Connector connector;
  private SessionFactory sessionFactory;
  public static Session session;
  public static DatabaseType type;

  public DatabaseController() throws OGMDatabaseTypeNotFoundException, UrlDialectNotSupported, AccessNotGrantedException, IOException, ConnectionRefusedException {
    DotEnvUtil envUtil = new DotEnvUtil();
    if (envUtil.get("DATABASE_OGM_TYPE") != null && !envUtil.get("DATABASE_OGM_TYPE").isEmpty()) {
      config = new OGMConfig();
      connector = new OGMConnector((OGMConfig) this.config);
      type = DatabaseType.OGM;
      sessionFactory = ((OGMConnector) connector).connect();
      session = sessionFactory.openSession();
    } else if (envUtil.get("DATABASE_URL") != null && !envUtil.get("DATABASE_URL").isEmpty()) {
      config = new ORMConfig();
      connector = new ORMConnector((ORMConfig) config);
      type = DatabaseType.ORM;
      sessionFactory = ((ORMConnector) connector).connect();
      session = sessionFactory.openSession();
    } else {
      config = new FSConfig(envUtil.get("DATABASE_DIRECTORY_PATH") != null && !envUtil.get("DATABASE_DIRECTORY_PATH").isEmpty() ? envUtil.get("DATABASE_DIRECTORY_PATH") : SystemUtil.getWorkingDirectory());
      connector = new FSConnector((FSConfig) config);
      type = DatabaseType.FS;
    }
  }
}
