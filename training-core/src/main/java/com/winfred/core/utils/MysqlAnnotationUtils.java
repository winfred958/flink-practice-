package com.winfred.core.utils;


import com.winfred.core.annotation.MysqlResult;
import com.winfred.core.annotation.MysqlTableName;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kevin
 */
public class MysqlAnnotationUtils {

  public static String getTableName(Object obj) {
    MysqlTableName mysqlTableName = obj.getClass().getAnnotation(MysqlTableName.class);
    if (null != mysqlTableName) {
      return mysqlTableName.name();
    }
    return null;
  }

  public static String getColumnName(Field field) {
    String column = null;
    MysqlResult mysqlResult = field.getDeclaredAnnotation(MysqlResult.class);
    if (null != mysqlResult) {
      column = mysqlResult.column();
    }
    return column;
  }

  public static String getUniqueColumnName(Field field) {
    String column = null;
    MysqlResult mysqlResult = field.getDeclaredAnnotation(MysqlResult.class);
    if (null != mysqlResult) {
      if (mysqlResult.unique()) {
        column = mysqlResult.column();
      }
    }
    return column;
  }

  public static List<Map<String, Field>> getColumnFieldMap(Class<?> clazz) {

    List<Map<String, Field>> result = new ArrayList<>();

    for (Field field : clazz.getDeclaredFields()) {
      String column = getColumnName(field);
      if (null == column) {
        continue;
      }
      Map<String, Field> map = new HashMap<>(1);
      map.put(column, field);

      result.add(map);
    }
    return result;
  }

  public static List<Map<String, Field>> getUniqueColumnFieldMap(Class<?> clazz) {

    List<Map<String, Field>> result = new ArrayList<>();

    for (Field field : clazz.getDeclaredFields()) {
      String column = getUniqueColumnName(field);
      if (null == column) {
        continue;
      }
      Map<String, Field> map = new HashMap<>(1);
      map.put(column, field);

      result.add(map);
    }
    return result;
  }


  public static Map<Field, Method> getFieldGetMethodMap(Class<?> clazz) {
    Map<Field, Method> map = new ConcurrentHashMap<>(16);


    Field[] fields = clazz.getDeclaredFields();

    Map<String, Method> methodMap = getMethodMap(clazz);

    for (Field field : fields) {
      String fieldName = field.getName();
      Method method = methodMap.get("get" + StringUtils.capitalize(fieldName));
      if (null != method) {
        map.put(field, method);
      }
    }
    return map;
  }

  public static Map<String, Method> getMethodMap(Class<?> clazz) {
    Map<String, Method> map = new HashMap<>(16);
    for (Method method : clazz.getDeclaredMethods()) {
      map.put(method.getName(), method);
    }
    return map;
  }

  public static Map<String, Method> getColumnMethodMap(Class<?> clazz) {
    Map<String, Method> result = new HashMap<>(16);
    List<Map<String, Field>> columnFieldMap = getColumnFieldMap(clazz);
    Map<Field, Method> fieldGetMethodMap = getFieldGetMethodMap(clazz);
    for (Map<String, Field> element : columnFieldMap) {
      for (Map.Entry<String, Field> entry : element.entrySet()) {
        String column = entry.getKey();
        Field field = entry.getValue();
        Method getMethod = fieldGetMethodMap.get(field);
        result.put(column, getMethod);
      }
    }
    return result;
  }


  public static List<Map<String, Object>> getColumnAndValue(Object obj) {

    Class<?> clazz = obj.getClass();
    List<Map<String, Object>> result = new ArrayList<>();

    List<Map<String, Field>> columnFieldMap = getColumnFieldMap(clazz);
    Map<Field, Method> fieldGetMethodMap = getFieldGetMethodMap(clazz);
    for (Map<String, Field> element : columnFieldMap) {
      for (Map.Entry<String, Field> entry : element.entrySet()) {
        String column = entry.getKey();
        Field field = entry.getValue();
        Method getMethod = fieldGetMethodMap.get(field);
        try {
          Object value = getMethod.invoke(obj);
          Map<String, Object> map = new HashMap<>(1);
          map.put(column, value);
          result.add(map);

        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }


  public static List<Map<String, Object>> getUniqueColumnAndValue(Object obj) {

    Class<?> clazz = obj.getClass();
    List<Map<String, Object>> result = new ArrayList<>();

    List<Map<String, Field>> columnFieldMap = getUniqueColumnFieldMap(clazz);
    Map<Field, Method> fieldGetMethodMap = getFieldGetMethodMap(clazz);
    for (Map<String, Field> element : columnFieldMap) {
      for (Map.Entry<String, Field> entry : element.entrySet()) {
        String column = entry.getKey();
        Field field = entry.getValue();
        Method getMethod = fieldGetMethodMap.get(field);
        try {
          Object value = getMethod.invoke(obj);
          Map<String, Object> map = new HashMap<>(1);
          map.put(column, value);
          result.add(map);

        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }


  public static String getReplaceSql(String tableName, List<String> columnList) {
    String columnsStr = StringUtils.join(columnList, ", ");

    List<String> placeholderList = new ArrayList<>(16);

    for (int i = columnList.size(); i > 0; i--) {
      placeholderList.add("?");
    }
    String placeholder = StringUtils.join(placeholderList, ",");

    return String.format("REPLACE INTO %s (%s) VALUES (%s)", tableName, columnsStr, placeholder);

  }


  public static PreparedStatement getReplacePreparedStatement(Object obj, Connection connection) throws SQLException {

    List<Map<String, Object>> columnAndValue = getColumnAndValue(obj);

    List<String> columnList = new ArrayList<>(16);
    List<Object> valueList = new ArrayList<>(16);

    for (Map<String, Object> element : columnAndValue) {
      for (Map.Entry<String, Object> entry : element.entrySet()) {
        String column = entry.getKey();
        Object value = entry.getValue();
        if (null == value) {
          continue;
        }
        columnList.add(column);
        valueList.add(value);
      }
    }

    String tableName = getTableName(obj);
    String sql = getReplaceSql(tableName, columnList);

    PreparedStatement prepareStatement = connection.prepareStatement(sql);


    int len = valueList.size();
    for (int i = 0; i < len; i++) {
      prepareStatement.setObject(i + 1, valueList.get(i));
    }
    return prepareStatement;

  }

  public static int doUpdateMysql(Object obj, Connection connection) throws SQLException, InvocationTargetException, IllegalAccessException {
    int row = 0;
    // 判断历史值, 防止窗口丢失覆盖
    if (isReplaceLongValue(obj, connection)) {
      connection.setAutoCommit(false);
      // update
      try (PreparedStatement replacePreparedStatement = MysqlAnnotationUtils.getReplacePreparedStatement(obj, connection);) {
        row = replacePreparedStatement.executeUpdate();
        connection.commit();
      } catch (Exception e) {
        connection.rollback();
      }
    }
    return row;
  }

  public static int deleteLessThan(Object obj, Connection connection, Long timestamp) throws SQLException {
    int row = 0;
    String deleteSql = getDeleteSql(obj, timestamp);
    try (PreparedStatement replacePreparedStatement = connection.prepareStatement(deleteSql);) {
      connection.setAutoCommit(true);
      row = replacePreparedStatement.executeUpdate();
    } catch (Exception e) {

    }
    return row;
  }


  public static Long getHistoryValue(Object obj, Connection connection) throws SQLException {

    String tableName = getTableName(obj);
    List<Map<String, Object>> uniqueColumnAndValue = getUniqueColumnAndValue(obj);

    List<String> columnList = new ArrayList<>(16);
    List<Object> valueList = new ArrayList<>(16);
    for (Map<String, Object> map : uniqueColumnAndValue) {
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        columnList.add(entry.getKey() + " = ?");
        valueList.add(entry.getValue());
      }
    }

    String condition = StringUtils.join(columnList, " AND ");

    String sql = String.format("SELECT * FROM %s WHERE %s", tableName, condition);

    Long value = null;

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
      int len = valueList.size();
      for (int i = 0; i < len; i++) {
        preparedStatement.setObject(i + 1, valueList.get(i));
      }

      ResultSet resultSet = preparedStatement.executeQuery();

      List<String> columnNameList = new ArrayList<>();
      ResultSetMetaData metaData = resultSet.getMetaData();
      int columnCount = metaData.getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        String columnName = metaData.getColumnName(i);
        columnNameList.add(columnName);
      }

      while (resultSet.next()) {
        value = parseToLong(resultSet.getString("value"));
      }
      preparedStatement.clearParameters();
    }
    return value;
  }

  public static Long parseToLong(Object obj) {
    Long value = null;
    String str = String.valueOf(obj);
    if (StringUtils.isNumeric(str)) {
      value = Long.valueOf(str);
    }
    return value;
  }

  public static boolean isReplaceLongValue(Object obj, Connection connection) throws SQLException, InvocationTargetException, IllegalAccessException {
    Long historyValue = getHistoryValue(obj, connection);
    if (null == historyValue) {
      return true;
    }

    Long value = parseToLong(getValueMethod(obj).invoke(obj));

    if (historyValue.compareTo(value) > 0) {
      return false;
    }
    return true;
  }


  public static Method getValueMethod(Object obj) {
    Map<String, Method> columnMethodMap = getColumnMethodMap(obj.getClass());
    return columnMethodMap.get("value");
  }

  public static String getDeleteSql(Object obj, Long timestamp) {
    String tableName = getTableName(obj);
    return String.format("DELETE FROM %s WHERE timestamp <= %s", tableName, timestamp);
  }
}
