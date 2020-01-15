package bi.utils;


import java.util.logging.Logger;


public class LoggingUtil<T> extends TypeT<T> {

  public LoggingUtil(Class<T> tClass) {
    this.setTypeOfT(tClass);
  }

  public Logger getLogger() {
    return Logger.getLogger(getTypeOfT().getName());
  }
}
