package com.winfred.core.sink;


import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class HbaseAsyncSink extends RichAsyncFunction<Put, Void> {

  private static final Logger log = LoggerFactory.getLogger(HbaseAsyncSink.class);

  private Connection connection = null;
  private Table table = null;

  private String zookeeperQuorum;
  private String tableName;

  public HbaseAsyncSink(String zookeeperQuorum, String tableName) {
    this.zookeeperQuorum = zookeeperQuorum;
    this.tableName = tableName;
  }

  @Override
  public void asyncInvoke(Put input, ResultFuture<Void> resultFuture) throws Exception {
    CompletableFuture
        .supplyAsync(new Supplier<Put>() {
          @Override
          public Put get() {
            try {
              // hbase put
              long start = System.currentTimeMillis();
              table.put(input);
              long end = System.currentTimeMillis();
              log.debug("[hbase] put took: {}", end - start);
            } catch (IOException e) {
              log.error("[hbase] put error.", e);
            }
            return input;
          }
        })
        .thenAccept(new Consumer<Put>() {
          @Override
          public void accept(Put put) {
            log.debug("[hbase] put success: {}", put.getId());
          }
        });
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
