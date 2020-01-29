package ch.bbzsogr.bi.models.configs;

import ch.bbzsogr.bi.interfaces.Config;
import ch.bbzsogr.bi.utils.DotEnvUtil;

/**
 * The type Fs config.
 */
public class FSConfig implements Config {

  private String path;

    /**
     * Instantiates a new Fs config.
     */
    public FSConfig() {
    super();
    DotEnvUtil dotEnvUtil = new DotEnvUtil();
    this.path = dotEnvUtil.get("DATABASE_DIRECTORY_PATH");
  }

    /**
     * Instantiates a new Fs config.
     *
     * @param path the path
     */
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
