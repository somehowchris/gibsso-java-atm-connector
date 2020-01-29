package couchdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Couchbase connector test.
 */
@Testcontainers
public class CouchbaseConnectorTest {

    /**
     * The Couchbase.
     */
    @Container
  public CouchbaseContainer couchbase = new CouchbaseContainer();

    /**
     * Sets up.
     */
    @BeforeEach
  public void setUp() {
    String address = couchbase.getContainerIpAddress();
    Integer port = couchbase.getFirstMappedPort();
  }
}
