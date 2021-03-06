package ch.bbzsogr.bi.models.configs;

import ch.bbzsogr.bi.exceptions.OGMDatabaseTypeNotFoundException;
import ch.bbzsogr.bi.interfaces.Config;
import ch.bbzsogr.bi.models.enums.OGMSupportedDatabases;
import ch.bbzsogr.bi.utils.DotEnvUtil;

/**
 * The type Ogm config.
 */
public class OGMConfig implements Config {

  private String password;
  private String username;
  private String database;
  private String host;
  private int port;
  private OGMSupportedDatabases type;

    /**
     * Instantiates a new Ogm config.
     *
     * @throws OGMDatabaseTypeNotFoundException the ogm database type not found exception
     */
    public OGMConfig() throws OGMDatabaseTypeNotFoundException {
    DotEnvUtil envUtil = new DotEnvUtil();
    this.password = envUtil.get("DATABASE_PASSWORD");
    this.username = envUtil.get("DATABASE_USERNAME");
    this.database = envUtil.get("DATABASE_NAME");
    this.host = envUtil.get("DATABASE_HOST");
    this.port = Integer.valueOf(envUtil.get("DATABASE_PORT"));
    this.type = OGMSupportedDatabases.getTypeByName(envUtil.get("DATABASE_OGM_TYPE"));
  }

    /**
     * Instantiates a new Ogm config.
     *
     * @param password the password
     * @param username the username
     * @param database the database
     * @param host     the host
     * @param port     the port
     * @param type     the type
     */
    public OGMConfig(String password, String username, String database, String host, int port, OGMSupportedDatabases type) {
    this.password = password;
    this.username = username;
    this.database = database;
    this.host = host;
    this.port = port;
    this.type = type;
  }

    /**
     * Gets type.
     *
     * @return the type
     */
    public OGMSupportedDatabases getType() {
    return type;
  }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(OGMSupportedDatabases type) {
    this.type = type;
  }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
    return host;
  }

    /**
     * Sets host.
     *
     * @param host the host
     */
    public void setHost(String host) {
    this.host = host;
  }

    /**
     * Gets database.
     *
     * @return the database
     */
    public String getDatabase() {
    return database;
  }

    /**
     * Sets database.
     *
     * @param database the database
     */
    public void setDatabase(String database) {
    this.database = database;
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
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
    return port;
  }

    /**
     * Sets port.
     *
     * @param port the port
     */
    public void setPort(int port) {
    this.port = port;
  }


}
