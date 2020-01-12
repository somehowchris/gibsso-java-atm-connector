package bi.interfaces;

import bi.exceptions.AccessNotGrantedException;
import bi.exceptions.ConnectionRefusedException;

import java.io.IOException;

/**
 * The interface Connector.
 */
public interface Connector {
  /**
   * Connect object.
   *
   * @return the object
   * @throws ConnectionRefusedException the connection refused exception
   * @throws AccessNotGrantedException  the access not granted exception
   * @throws IOException                the io exception
   */
  Object connect() throws ConnectionRefusedException, AccessNotGrantedException, IOException;

  /**
   * Sets up.
   *
   * @return the up
   * @throws IOException                the io exception
   * @throws ConnectionRefusedException the connection refused exception
   */
  boolean setUp() throws IOException, ConnectionRefusedException, AccessNotGrantedException;
}
