package com.winfred.core.annotation;

import java.lang.annotation.*;

/**
 * @author kevin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MysqlResult {

  String column() default "";

  boolean unique() default false;

  String property() default "";
}
