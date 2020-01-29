package ch.bbzsogr.bi.utils;

import java.lang.reflect.ParameterizedType;
import java.util.logging.Logger;

/**
 * The type Type t.
 *
 * @param <T> the type parameter
 */
public class TypeT<T> {

  private Class<T> typeOfT;

  /**
   * Instantiates a new Type t.
   */
  @SuppressWarnings("unchecked")
  public TypeT() {
    Class tClass = getClass();
    try {
      this.typeOfT = (Class)
        ((ParameterizedType) tClass
          .getGenericSuperclass())
          .getActualTypeArguments()[0];
    } catch (Exception e) {
      Logger.getLogger("TypeT").warning("Couldn't find T");
    }
  }

  /**
   * Gets type of t.
   *
   * @return the type of t
   */
  public Class<T> getTypeOfT() {
    return typeOfT;
  }

  /**
   * Sets type of t.
   *
   * @param tClass the t class
   * @return the type of t
   */
  public Object setTypeOfT(Class<T> tClass) {
    this.typeOfT = tClass;
    return tClass;
  }
}
