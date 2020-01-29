package maria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Maria connector test.
 */
@Testcontainers
public class MariaConnectorTest {

  /**
   * The Maria.
   */
  @Container
  public MariaDBContainer maria = new MariaDBContainer();

  /**
   * Sets up.
   */
  @BeforeEach
  public void setUp() {
    String url = maria.getJdbcUrl();
    String address = maria.getContainerIpAddress();
    Integer port = maria.getFirstMappedPort();
  }

}
