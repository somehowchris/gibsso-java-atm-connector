package bi.models.configs;

import bi.exceptions.UrlDialectNotSupported;
import bi.interfaces.Config;
import bi.models.enums.ORMSupportedDatabases;
import bi.utils.DotEnvUtil;

/**
 * The type Orm config.
 */
public class ORMConfig implements Config {

  private String password;
  private String username;
  private String url;
  private ORMSupportedDatabases type;

  /**
   * Instantiates a new Orm config.
   *
   * @param url      the url
   * @param password the password
   * @param username the username
   * @throws UrlDialectNotSupported the url dialect not supported
   */
  public ORMConfig(String url, String password, String username) throws UrlDialectNotSupported {
    this.password = password;
    this.username = username;
    this.url = url;
    this.type = ORMSupportedDatabases.get(url);
  }

  public ORMConfig() throws UrlDialectNotSupported {
    DotEnvUtil envUtil = new DotEnvUtil();
    this.password = envUtil.get("DATABASE_PASSWORD");
    this.username = envUtil.get("DATABASE_USERNAME");
    this.url = envUtil.get("DATABASE_URL");
    this.type = ORMSupportedDatabases.get(this.url);
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets username.
   *
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets url.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets url.
   *
   * @param url the url
   * @throws UrlDialectNotSupported the url dialect not supported
   */
  public void setUrl(String url) throws UrlDialectNotSupported {
    this.url = url;
    this.type = ORMSupportedDatabases.get(url);
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public ORMSupportedDatabases getType() {
    return type;
  }

  // TODO add to readme
  //  jdbc:sqlite::memory:
}
