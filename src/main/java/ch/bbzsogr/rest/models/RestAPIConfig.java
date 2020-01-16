package ch.bbzsogr.rest.models;

public class RestAPIConfig {

  private static String username;
  private static String password;
  private static String baseUrl;

  public RestAPIConfig() {
  }

  public RestAPIConfig(String username, String password, String baseUrl) {
    RestAPIConfig.username = username;
    RestAPIConfig.password = password;
    RestAPIConfig.baseUrl = baseUrl;
  }

  public static String getUsername() {
    return username;
  }

  public static void setUsername(String username) {
    RestAPIConfig.username = username;
  }

  public static String getPassword() {
    return password;
  }

  public static void setPassword(String password) {
    RestAPIConfig.password = password;
  }

  public static String getBaseUrl() {
    return baseUrl;
  }

  public static void setBaseUrl(String baseUrl) {
    RestAPIConfig.baseUrl = baseUrl;
  }
}
