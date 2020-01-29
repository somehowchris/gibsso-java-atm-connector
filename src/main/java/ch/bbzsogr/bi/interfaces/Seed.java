package ch.bbzsogr.bi.interfaces;

import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.utils.DotEnvUtil;
import org.hibernate.Session;

import java.util.logging.Logger;

/**
 * The interface Seed.
 */
public interface Seed {

  /**
   * Run.
   *
   * @param session the session
   * @param dotEnv  the dot env
   * @param logger  the logger
   * @throws EntitySaveException the entity save exception
   */
  void run(Session session, DotEnvUtil dotEnv, Logger logger) throws EntitySaveException;

}
