package mysql;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MySQLConnectorTest {

  @Container
  public MySQLContainer mysql = new MySQLContainer();

  @BeforeEach
  public void setUp() {
    String url = mysql.getJdbcUrl();
    String password = mysql.getPassword();
    String username = mysql.getUsername();
  }

}
