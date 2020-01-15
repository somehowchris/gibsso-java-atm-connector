package ch.bbzsogr.bi.decorators;

import ch.bbzsogr.bi.models.enums.DatabaseInterpreters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseType {
  DatabaseInterpreters type();
}
