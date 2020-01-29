package ch.bbzsogr.rest.models;

/**
 * The type Rest api config.
 */
public class RestAPIConfig {

  private static String username;
  private static String password;
  private static String baseUrl;

    /**
     * Instantiates a new Rest api config.
     */
    public RestAPIConfig() {
  }

    /**
     * Instantiates a new Rest api config.
     *
     * @param username the username
     * @param password the password
     * @param baseUrl  the base url
     */
    public RestAPIConfig(String username, String password, String baseUrl) {
    RestAPIConfig.username = username;
    RestAPIConfig.password = password;
    RestAPIConfig.baseUrl = baseUrl;
  }

    /**
     * Gets username.
     *
     * @return the username
     */
    public static String getUsername() {
    return username;
  }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public static void setUsername(String username) {
    RestAPIConfig.username = username;
  }

    /**
     * Gets password.
     *
     * @return the password
     */
    public static String getPassword() {
    return password;
  }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public static void setPassword(String password) {
    RestAPIConfig.password = password;
  }

    /**
     * Gets base url.
     *
     * @return the base url
     */
    public static String getBaseUrl() {
    return baseUrl;
  }

    /**
     * Sets base url.
     *
     * @param baseUrl the base url
     */
    public static void setBaseUrl(String baseUrl) {
    RestAPIConfig.baseUrl = baseUrl;
  }
}
