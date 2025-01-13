package com.winfred.core.sink;


import com.winfred.core.entity.MysqlObjectEntity;
import com.winfred.core.utils.MysqlAnnotationUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author kevin
 */
public class MysqlSinkDemon extends RichSinkFunction<MysqlObjectEntity> {

  private Connection connection = null;
  private PreparedStatement preparedStatement = null;

  @Override
  public void open(Configuration parameters) throws Exception {
    super.open(parameters);
    String driver = "com.mysql.jdbc.Driver";


    String url = "";
    String userName = "";
    String passwd = "";

    //1.加载驱动
    Class.forName(driver);
    //2.创建连接
    connection = DriverManager.getConnection(url, userName, passwd);
  }

  @Override
  public void close() throws Exception {
    super.close();
    if (null != preparedStatement) {
      preparedStatement.close();
    }
    if (null != connection) {
      connection.close();
    }
  }

  @Override
  public void invoke(MysqlObjectEntity value, Context context) throws Exception {
    MysqlAnnotationUtils.doUpdateMysql(value, connection);
  }

}
