package com.winfred.core.sink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

/**
 * @author winfred958
 */
public class HbaseSink extends RichSinkFunction<Put> {

  private Connection connection = null;
  private Table table = null;

  private final String zookeeperQuorum;
  private final String tableName;

  public HbaseSink(String zookeeperQuorum, String tableName) {
    this.zookeeperQuorum = zookeeperQuorum;
    this.tableName = tableName;
  }

  @Override
  public void invoke(Put value, Context context) throws Exception {
    table.put(value);
  }

  @Override
  public void open(Configuration parameters) throws Exception {
    org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
    configuration.set("hbase.zookeeper.quorum", zookeeperQuorum);
    configuration.set("hbase.zookeeper.property.clientPort", "2181");
    connection = ConnectionFactory.createConnection(configuration);
    table = connection.getTable(TableName.valueOf(tableName));
  }

  @Override
  public void close() throws Exception {
    if (table != null) {
      table.close();
    }
    if (this.connection != null) {
      connection.close();
    }
  }
}
