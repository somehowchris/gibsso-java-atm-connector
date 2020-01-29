package redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Redis connector test.
 */
@Testcontainers
public class RedisConnectorTest {

    /**
     * The Redis.
     */
    @Container
  public GenericContainer redis = new GenericContainer<>("redis:6.0-rc-alpine").withExposedPorts(6379);

    /**
     * Sets up.
     */
    @BeforeEach
  public void setUp() {
    String address = redis.getContainerIpAddress();
    Integer port = redis.getFirstMappedPort();
  }

}
