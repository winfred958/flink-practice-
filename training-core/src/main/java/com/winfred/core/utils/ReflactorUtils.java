package com.winfred.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 */
public class ReflactorUtils {
  public static Object getFieldValue(Object obj, String fieldName) {
    if (null == obj) {
      return null;
    }
    try {
      Field field = obj.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(obj);
    } catch (IllegalAccessException e) {
    } catch (NoSuchFieldException e) {
    }
    return null;
  }

  public static Map<String, Field> getFieldMap(Class<?> clazz) {
    Map<String, Field> result = new HashMap<>(16);
    Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
      result.put(field.getName(), field);
    });
    Class<?> superclass = clazz.getSuperclass();
    if (null == superclass) {
      return result;
    } else {
      // 递归调用父类field
      result.putAll(getFieldMap(superclass));
    }
    return result;
  }

  public static void copyValue(Map<String, Object> data, Object target) {
    if (null == target) {
      return;
    }
    Class<?> clazz = target.getClass();
    Map<String, Field> targetFieldMap = getFieldMap(clazz);

    for (Map.Entry<String, Field> entry : targetFieldMap.entrySet()) {
      String fieldName = entry.getKey();
      Field targetField = entry.getValue();

      String targetFieldTypeName = targetField.getType().getTypeName();

      Object fieldValue = data.get(fieldName);
      targetField.setAccessible(true);

      try {
        if (StringUtils.containsAny(targetFieldTypeName, "java.lang.String", "String")) {
          String value = (isIllegalValue(fieldValue) ? null : String.valueOf(fieldValue));
          targetField.set(target, value);
        } else if (StringUtils.containsAny(targetFieldTypeName, "java.lang.Long", "long")) {
          Long value = StringUtils.isNumeric(String.valueOf(fieldValue)) ? Long.valueOf(String.valueOf(fieldValue)) : -1L;
          targetField.set(target, value);
        } else if (StringUtils.containsAny(targetFieldTypeName, "java.lang.Double", "double")) {
          Double value = isIllegalValue(fieldValue) ? 0.0 : Double.valueOf(String.valueOf(fieldValue));
          targetField.set(target, value);
        } else {
          String value = isIllegalValue(fieldValue) ? null : String.valueOf(fieldValue);
          targetField.set(target, value);
        }
      } catch (IllegalAccessException e) {
      }

    }
  }

  /**
   * 是非法数据?
   *
   * @param fieldValue
   * @return
   */
  public static Boolean isIllegalValue(Object fieldValue) {
    if (null == fieldValue) {
      return true;
    }
    return StringUtils.isBlank(String.valueOf(fieldValue));
  }
}
