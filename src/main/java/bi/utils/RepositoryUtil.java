package bi.utils;

import bi.models.enums.DatabaseType;
import com.google.common.reflect.ClassPath;

import java.io.IOException;

public class RepositoryUtil {

  public static <T> T getRepository(Class<T> tClass, DatabaseType type) {
    try {
      final ClassLoader loader = Thread.currentThread().getContextClassLoader();
      ClassPath.ClassInfo info = ClassPath.from(loader).getAllClasses().stream()
        .filter(classInfo -> classInfo.getSimpleName().startsWith(type.getPrefix())
          && classInfo.getPackageName().startsWith("bi")
          && classInfo.getSimpleName().endsWith(tClass.getSimpleName()))
        .filter(classInfo -> tClass.isAssignableFrom(classInfo.load()))
        .findFirst().get();
      return (T) info.load().newInstance();
    } catch (Exception e){
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  }
}
