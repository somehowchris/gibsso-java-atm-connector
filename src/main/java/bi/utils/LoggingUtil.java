package bi.utils;


import java.util.logging.Logger;


public class LoggingUtil {

  public static Logger newLogger(Class clazz){
    return Logger.getLogger(clazz.getName());
  }
}
