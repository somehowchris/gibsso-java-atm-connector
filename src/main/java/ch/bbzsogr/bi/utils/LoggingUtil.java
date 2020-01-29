package ch.bbzsogr.bi.utils;


import java.util.logging.Logger;


/**
 * The type Logging util.
 *
 * @param <T> the type parameter
 */
public class LoggingUtil<T> extends TypeT<T> {

    /**
     * Instantiates a new Logging util.
     *
     * @param tClass the t class
     */
    public LoggingUtil(Class<T> tClass) {
    this.setTypeOfT(tClass);
  }

    /**
     * Gets logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
    return Logger.getLogger(getTypeOfT().getName());
  }
}
