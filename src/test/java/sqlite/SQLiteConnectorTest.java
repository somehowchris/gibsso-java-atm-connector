package sqlite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Sq lite connector test.
 */
@Testcontainers
public class SQLiteConnectorTest {

  /**
   * The Redis.
   */
  @Container
  public GenericContainer redis = new GenericContainer<>("nouchka/sqlite3:latest").withExposedPorts(6379);

  /**
   * Sets up.
   */
  @BeforeEach
  public void setUp() {
    String address = redis.getContainerIpAddress();
    Integer port = redis.getFirstMappedPort();
  }

}
