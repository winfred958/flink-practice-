package com.winfred.core.annotation;

import java.lang.annotation.*;

/**
 * @author kevin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface MysqlTableName {

  String name() default "";

  String sql() default "";
}
