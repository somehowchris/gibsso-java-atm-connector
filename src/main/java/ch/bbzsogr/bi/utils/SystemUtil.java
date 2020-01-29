package ch.bbzsogr.bi.utils;

/**
 * The type System util.
 */
public class SystemUtil {

    /**
     * Gets username.
     *
     * @return the username
     */
    public static String getUsername() {
    return System.getProperty("user.name");
  }

    /**
     * Gets home directory.
     *
     * @return the home directory
     */
    public static String getHomeDirectory() {
    return System.getProperty("user.home");
  }

    /**
     * Gets working directory.
     *
     * @return the working directory
     */
    public static String getWorkingDirectory() {
    return System.getProperty("user.dir");
  }
}
