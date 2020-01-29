package ch.bbzsogr.bi.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.nio.file.Paths;

/**
 * The type Dot env util.
 */
public class DotEnvUtil {

  private static Dotenv env;

  /**
   * Instantiates a new Dot env util.
   */
  public DotEnvUtil() {
    if (env == null) this.reload();
  }

  /**
   * Reload.
   */
  public void reload() {
    File dotEnvFile = new File(SystemUtil.getWorkingDirectory()).canRead() && Paths.get(SystemUtil.getWorkingDirectory(), ".env").toFile().exists() ? Paths.get(SystemUtil.getWorkingDirectory(), ".env").toFile() : Paths.get(SystemUtil.getHomeDirectory(), ".env").toFile();
    env = Dotenv.configure().directory(dotEnvFile.getParent()).ignoreIfMalformed().load();
  }

  /**
   * Get string.
   *
   * @param key the key
   * @return the string
   */
  public String get(String key) {
    return env.get(key);
  }

}
