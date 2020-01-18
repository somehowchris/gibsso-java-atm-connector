package cassandra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.junit.jupiter.Container;

public class CassandraConnectorTest {

  @Container
  public CassandraContainer cassandra = new CassandraContainer();

  @BeforeEach
  public void setUp() {
    cassandra.start();
    String address = cassandra.getContainerIpAddress();
    Integer port = cassandra.getFirstMappedPort();
  }

}
