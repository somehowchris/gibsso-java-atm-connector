package mysql;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type My sql connector test.
 */
@Testcontainers
public class MySQLConnectorTest {

    /**
     * The Mysql.
     */
    @Container
  public MySQLContainer mysql = new MySQLContainer();

    /**
     * Sets up.
     */
    @BeforeEach
  public void setUp() {
    String url = mysql.getJdbcUrl();
    String password = mysql.getPassword();
    String username = mysql.getUsername();
  }

}
