package ch.bbzsogr.bi.utils;

import ch.bbzsogr.bi.decorators.Api;
import ch.bbzsogr.bi.decorators.DatabaseType;
import ch.bbzsogr.bi.decorators.Service;
import ch.bbzsogr.bi.interfaces.ServiceInterface;
import ch.bbzsogr.bi.interfaces.repositories.Repository;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.stream.Stream;

public class Container {

  private static final ClassLoader loader = Thread.currentThread().getContextClassLoader();
  private static HashMap<String, Object> classInstances = new HashMap<>();

  public static <T> T getService(Class<T> tClass) {
    if (classInstances.containsKey(tClass.getSimpleName())) return (T) classInstances.get(tClass.getSimpleName());
    try {
      ClassPath.ClassInfo info = getTopLevelClasses(Container.class)
        .filter(classInfo -> classInfo.load().getAnnotation(Service.class) != null && tClass.isAssignableFrom(classInfo.load()))
        .findFirst().get();

      return getInstance(info);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  }

  public static <T extends Repository> T getRepository(Class<T> tClass, DatabaseInterpreters type) {
    try {
      if (classInstances.containsKey(tClass.getSimpleName())) return (T) classInstances.get(tClass.getSimpleName());
      ClassPath.ClassInfo info = getTopLevelClasses(Container.class)
        .filter(classInfo ->
          (classInfo.load().getAnnotation(DatabaseType.class) != null && classInfo.load().getAnnotation(DatabaseType.class).type() == type)
          && tClass.isAssignableFrom(classInfo.load()))
        .findFirst().get();

      return getInstance(info);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  }

  private static <T> T getInstance(ClassPath.ClassInfo info) throws IllegalAccessException, InstantiationException {
    if (classInstances.containsKey(info.getSimpleName())) return (T) classInstances.get(info.getSimpleName());
    T instance = (T) info.load().newInstance();
    classInstances.put(info.getSimpleName(), instance);
    return instance;
  }

  public static <T extends ServiceInterface> T getApi(Class<T> tClass, ApiType type) throws IOException {
    try {
      ClassPath.ClassInfo info = getTopLevelClasses(Container.class)
        .filter(classInfo ->tClass.isAssignableFrom(classInfo.load())
          && classInfo.load().getAnnotation(Api.class) != null && classInfo.load().getAnnotation(Api.class).type() == type)
        .findFirst().get();
      return getInstance(info);
    }catch (Exception e){
      // TODO throw not found exception
      e.printStackTrace();
      return null;
    }
  }

  public static Stream<ClassPath.ClassInfo> getTopLevelClasses(Class t) throws IOException {
      return ClassPath.from(loader).getTopLevelClassesRecursive(t.getPackage().getName().split("\\.")[0]).stream()
      .filter(classInfo -> {
        try{
          classInfo.load();
          return true;
        }catch (Exception e){
          e.printStackTrace();
          return false;
        }
      });
  }
}
