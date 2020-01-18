package postgresql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgreSQLConnectorTest {

  @Container
  public PostgreSQLContainer postgres = new PostgreSQLContainer();

  @BeforeEach
  public void setUp() {
    String url = postgres.getJdbcUrl();
    String password = postgres.getPassword();
    String username = postgres.getUsername();
  }

  @Test
  public void testingTest(){

  }

}
