package ch.bbzsogr.bi.interfaces;

import ch.bbzsogr.bi.utils.DotEnvUtil;
import org.hibernate.Session;

import java.util.logging.Logger;

public interface Seed {
  void run(Session session, DotEnvUtil dotEnv, Logger logger);
}
