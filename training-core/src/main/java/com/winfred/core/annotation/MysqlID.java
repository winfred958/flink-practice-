package com.winfred.core.annotation;

import java.lang.annotation.*;

/**
 * @author kevin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MysqlID {

  String column() default "id";
}
