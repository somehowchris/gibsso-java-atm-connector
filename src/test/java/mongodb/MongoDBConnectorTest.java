package mongodb;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Mongo db connector test.
 */
@Testcontainers
public class MongoDBConnectorTest {

    /**
     * The Mongodb.
     */
    @Container
  public GenericContainer mongodb = new GenericContainer<>("mongo:4.2.2-bionic").withExposedPorts(27017);

    /**
     * Sets up.
     */
    @BeforeEach
  public void setUp() {
    String address = mongodb.getContainerIpAddress();
    Integer port = mongodb.getFirstMappedPort();
  }

}
