package ch.bbzsogr.bi.controllers;

import ch.bbzsogr.bi.connectors.FSConnector;
import ch.bbzsogr.bi.connectors.OGMConnector;
import ch.bbzsogr.bi.connectors.ORMConnector;
import ch.bbzsogr.bi.decorators.Seeder;
import ch.bbzsogr.bi.exceptions.*;
import ch.bbzsogr.bi.interfaces.Config;
import ch.bbzsogr.bi.interfaces.Connector;
import ch.bbzsogr.bi.interfaces.Seed;
import ch.bbzsogr.bi.models.configs.FSConfig;
import ch.bbzsogr.bi.models.configs.OGMConfig;
import ch.bbzsogr.bi.models.configs.ORMConfig;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.DotEnvUtil;
import ch.bbzsogr.bi.utils.LoggingUtil;
import ch.bbzsogr.bi.utils.SystemUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DatabaseController {

  public static Session session;
  public static DatabaseInterpreters type;
  private Config config;
  private Connector connector;
  private SessionFactory sessionFactory;
  private Logger logger;

  public DatabaseController() throws OGMDatabaseTypeNotFoundException, UrlDialectNotSupportedException, AccessNotGrantedException, IOException, ConnectionRefusedException, OGMNotYetSupportedException {
    logger = new LoggingUtil(DatabaseController.class).getLogger();

    logger.info("Initialized logger");
    logger.info("Initializing database configuration");

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

    logger.info("Connected and using "+type.getPrefix()+" repositories");
  }

  public void seed() throws IOException {
    List<Seed> seeds = Container.getTopLevelClasses(DatabaseController.class)
      .filter(classInfo -> classInfo.load().getAnnotation(Seeder.class) != null && Seed.class.isAssignableFrom(classInfo.load()))
      .map(classInfo -> {
        try {
          return Container.getInstance(classInfo);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InstantiationException e) {
          e.printStackTrace();

        }
        return (Seed) null;
      }).filter(obj -> obj instanceof Seed).collect(Collectors.toList());

    DotEnvUtil dotEnvUtil = new DotEnvUtil();

    seeds.forEach(seed -> {
      long start = System.currentTimeMillis();
      logger.info("Running seed " + seed.getClass().getSimpleName());
      seed.run(DatabaseController.session, dotEnvUtil, new LoggingUtil(seed.getClass()).getLogger());
      logger.info("Finished running " + seed.getClass().getSimpleName() + " after " + (((System.currentTimeMillis() - start) / 1000F)) + " seconds");
    });
  }
}
