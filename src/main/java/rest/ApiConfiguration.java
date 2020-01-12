package rest;

public class ApiConfiguration {

  static private String host;
  static private String port;
  static private String username;
  static private String password;

  public ApiConfiguration(String host, String port, String username, String password) {
    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;
  }

  public static String getHost() {
    return host;
  }

  public static void setHost(String host) {
    ApiConfiguration.host = host;
  }

  public static String getPort() {
    return port;
  }

  public static void setPort(String port) {
    ApiConfiguration.port = port;
  }

  public static String getUsername() {
    return username;
  }

  public static void setUsername(String username) {
    ApiConfiguration.username = username;
  }

  public static String getPassword() {
    return password;
  }

  public static void setPassword(String password) {
    ApiConfiguration.password = password;
  }
}
