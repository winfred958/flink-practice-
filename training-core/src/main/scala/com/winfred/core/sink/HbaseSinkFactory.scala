package com.winfred.core.sink

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

import java.util.HashMap

object HbaseSinkFactory {

  val HBASE_ZOOKEEPER_HOST = "cdh-172-16-1-28,cdh-172-16-1-30,cdh-172-16-1-32,cdh-172-16-1-35,cdh-172-16-1-39"

  var conn: Connection = null
  var tables: HashMap[String, Table] = new HashMap[String, Table]

  def initConn() {
    if (conn == null || conn.isClosed()) {
      val configuration: Configuration = HBaseConfiguration.create()
      configuration.set("hbase.zookeeper.quorum", HBASE_ZOOKEEPER_HOST)
      configuration.set("hbase.zookeeper.property.clientPort", "2181")
      conn = ConnectionFactory.createConnection(configuration)
    }
  }

  def getConn() = {
    initConn
    conn
  }

  def getTable(tableName: String) = {
    getConn().getTable(TableName.valueOf(tableName))
    //    tables.getOrElse(tableName, {
    //      initConn
    //      conn.getTable(TableName.valueOf(tableName))
    //    })
  }

  def put(tableName: String, put: Put) {
    val table = getTable(tableName)
    try {
      table.put(put)
    } finally {
      table.close()
    }
  }

  def get(tableName: String, get: Get): Result = {
    val table = getTable(tableName)
    try {
      val result: Result = table.get(get)
      result
    } finally {
      table.close()
    }
  }

  def main(args: Array[String]): Unit = {
    val tableName = "kevin_general_test"
    val rowKey = s"general_situation_all_201809112057"
    val columnFamily = "m5"

    val get = new Get(rowKey.getBytes)
    val result: Result = HbaseSinkFactory.get(
      tableName = tableName,
      get = get
    )

    for (cell <- result.rawCells()) {

      println(new String(cell.getRowArray).substring(cell.getRowOffset, cell.getRowOffset + cell.getRowLength))
      println(new String(cell.getFamilyArray).substring(cell.getFamilyOffset, cell.getFamilyOffset + cell.getFamilyLength))
      println(new String(cell.getQualifierArray).substring(cell.getQualifierOffset, cell.getQualifierOffset + cell.getQualifierLength))
      println(new String(cell.getValueArray).substring(cell.getValueOffset, cell.getValueOffset + cell.getValueLength))


      println("-----------------------------")


    }


  }
}