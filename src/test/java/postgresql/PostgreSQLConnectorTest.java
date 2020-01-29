package postgresql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Postgre sql connector test.
 */
@Testcontainers
public class PostgreSQLConnectorTest {

    /**
     * The Postgres.
     */
    @Container
  public PostgreSQLContainer postgres = new PostgreSQLContainer();

    /**
     * Sets up.
     */
    @BeforeEach
  public void setUp() {
    String url = postgres.getJdbcUrl();
    String password = postgres.getPassword();
    String username = postgres.getUsername();
  }

}
