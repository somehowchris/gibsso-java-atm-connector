package bi.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Logger;

public class TypeT<T> {

  private Class<T> typeOfT;

  @SuppressWarnings("unchecked")
  public TypeT() {
    Class tClass = getClass();
    try {
      for (Type name : ((ParameterizedType) tClass.getGenericSuperclass()).getActualTypeArguments()) {
        System.out.println(name.getTypeName());
      }
      this.typeOfT = (Class)
        ((ParameterizedType) tClass
          .getGenericSuperclass())
          .getActualTypeArguments()[0];
    } catch (Exception e) {
      Logger.getLogger("TypeT").warning("Couldn't find T");
    }
  }

  public Class<T> getTypeOfT() {
    return typeOfT;
  }

  public Object setTypeOfT(Class<T> tClass) {
    this.typeOfT = tClass;
    return tClass;
  }
}
