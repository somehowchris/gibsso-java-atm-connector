package bi.utils;

import bi.decorators.DatabaseType;
import bi.decorators.Service;
import bi.models.enums.DatabaseInterpreters;
import com.google.common.reflect.ClassPath;

import java.util.HashMap;

public class Container {

  private static HashMap<String, Object> classInstances = new HashMap<>();
  private static final ClassLoader loader = Thread.currentThread().getContextClassLoader();

  public static <T> T getService(Class<T> tClass){
    if(classInstances.containsKey(tClass.getSimpleName())) return (T) classInstances.get(tClass.getSimpleName());
    try{
      ClassPath.ClassInfo info = ClassPath.from(loader).getAllClasses().stream()
        .filter(classInfo -> classInfo.getPackageName().startsWith("bi"))
        .filter(classInfo -> classInfo.load().getAnnotation(Service.class) != null && tClass.isAssignableFrom(classInfo.load()))
        .findFirst().get();

      return getInstance(info);
    }catch (Exception e){
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  }

  public static <T> T getRepository(Class<T> tClass, DatabaseInterpreters type) {
    try {
      if(classInstances.containsKey(tClass.getSimpleName())) return (T) classInstances.get(tClass.getSimpleName());

      ClassPath.ClassInfo info = ClassPath.from(loader).getAllClasses().stream()
        .filter(classInfo -> classInfo.getPackageName().startsWith("bi"))
        .filter(classInfo -> (classInfo.load().getAnnotation(DatabaseType.class) != null ? classInfo.load().getAnnotation(DatabaseType.class).type() == type : false)
                              && tClass.isAssignableFrom(classInfo.load()))
        .findFirst().get();

      return getInstance(info);
    } catch (Exception e){
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  }

  private static <T> T getInstance(ClassPath.ClassInfo info) throws IllegalAccessException, InstantiationException {
    if(classInstances.containsKey(info.getSimpleName())) return (T) classInstances.get(info.getSimpleName());
    T instance = (T) info.load().newInstance();
    classInstances.put(info.getSimpleName(), instance);
    return instance;
  }
}
