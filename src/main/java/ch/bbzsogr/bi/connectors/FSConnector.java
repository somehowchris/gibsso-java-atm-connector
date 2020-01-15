package ch.bbzsogr.bi.connectors;

import ch.bbzsogr.bi.exceptions.ConnectionRefusedException;
import ch.bbzsogr.bi.interfaces.Connector;
import ch.bbzsogr.bi.models.configs.FSConfig;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Fs connector.
 */
public class FSConnector implements Connector {

  private FSConfig config;

  /**
   * Instantiates a new Fs connector.
   *
   * @param config the config
   */
  public FSConnector(FSConfig config) throws IOException, ConnectionRefusedException {
    this.config = config;
    this.setUp();
  }

  /**
   * Gets file.
   *
   * @return the file
   */
  public File getFile() {
    return new File(config.getPath());
  }

  public File connect() throws ConnectionRefusedException {

    if (!getFile().canWrite()) {
      throw new ConnectionRefusedException("Can't read " + config.getPath());
    }

    if (!getFile().canRead()) {
      throw new ConnectionRefusedException("Can't read " + config.getPath());
    }

    if (!getFile().exists()) {
      try {
        this.setUp();
      } catch (Exception e) {
        throw new ConnectionRefusedException("Could not create setup the database");
      }
    }

    if (!getFile().isDirectory()) {
      throw new ConnectionRefusedException(config.getPath() + " doesn't seem to be a directory");
    }

    return getFile();
  }

  public boolean setUp() throws IOException, ConnectionRefusedException {
    getFile().mkdirs();
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();

    ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(loader).getTopLevelClasses();

    List<String> classNames = classes.stream().filter(classInfo -> classInfo.getPackageName().equals("ch.bbzsogr.bi.models")).map(classInfo -> classInfo.getSimpleName()).collect(Collectors.toList());
    for (String name : classNames) {
      File modelDirectory = Paths.get(getFile().getAbsolutePath(), name).toFile();
      if (!modelDirectory.exists()) {
        modelDirectory.mkdirs();
      } else {
        if (!modelDirectory.isDirectory()) {
          throw new ConnectionRefusedException(modelDirectory.getPath() + " doesn't seem to be a directory");
        }
      }
    }

    return getFile().exists();
  }
}
