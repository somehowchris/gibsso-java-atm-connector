package bi.models.configs;

import bi.interfaces.Config;
import bi.utils.DotEnvUtil;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * The type Fs config.
 */
public class FSConfig implements Config {

  private String path;

  /**
   * Instantiates a new Fs config.
   *
   */
  public FSConfig() {
    super();
    DotEnvUtil dotEnvUtil = new DotEnvUtil();
    this.path = dotEnvUtil.get("DATABASE_DIRECTORY_PATH");
  }

  public FSConfig(String path) {
    super();
    this.path = path;
  }

  /**
   * Gets path.
   *
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * Sets path.
   *
   * @param path the path
   */
  public void setPath(String path) {
    this.path = path;
  }
}
