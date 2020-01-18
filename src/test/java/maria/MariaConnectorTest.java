package maria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MariaConnectorTest {

  @Container
  public MariaDBContainer maria = new MariaDBContainer();

  @BeforeEach
  public void setUp() {
    String url = maria.getJdbcUrl();
    String address = maria.getContainerIpAddress();
    Integer port = maria.getFirstMappedPort();
  }

}
