package ch.bbzsogr.bi.utils;

public class SystemUtil {

  public static String getUsername() {
    return System.getProperty("user.name");
  }

  public static String getHomeDirectory() {
    return System.getProperty("user.home");
  }

  public static String getWorkingDirectory() {
    return System.getProperty("user.dir");
  }
}
