package bi.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.nio.file.Paths;

public class DotEnvUtil {

  private static Dotenv env;

  public DotEnvUtil(){
    if(env == null) this.reload();
  }

  public void reload(){
    File dotEnvFile = new File(SystemUtil.getWorkingDirectory()).canRead() && Paths.get(SystemUtil.getWorkingDirectory(), ".env").toFile().exists() ? Paths.get(SystemUtil.getWorkingDirectory(), ".env").toFile() : Paths.get(SystemUtil.getHomeDirectory(), ".env").toFile();
    env = Dotenv.configure().directory(dotEnvFile.getParent()).ignoreIfMalformed().load();
  }

  public String get(String key){
    return env.get(key);
  }

}
