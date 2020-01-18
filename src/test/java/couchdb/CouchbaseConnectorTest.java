package couchdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class CouchbaseConnectorTest {

  @Container
  public CouchbaseContainer couchbase = new CouchbaseContainer();

  @BeforeEach
  public void setUp() {
    String address = couchbase.getContainerIpAddress();
    Integer port = couchbase.getFirstMappedPort();
  }

  @Test
  public void testingTest(){

  }
}
