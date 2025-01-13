package com.winfred.training.hive

import com.winfred.core.utils.ArgsHandler
import org.apache.flink.table.api.EnvironmentSettings

object FlinkHiveTest {

  def main(args: Array[String]): Unit = {

    val settings: EnvironmentSettings = EnvironmentSettings
      .newInstance()
      .inBatchMode()
      .build()

    //    val tableEnv = TableEnvironment
    //      .create(settings)

    var databaseName = ArgsHandler.getArgsParam(args, "database-name")
    if (databaseName == null) {
      databaseName = "default"
    }

    var catalogName = ArgsHandler.getArgsParam(args, "catalog-name")
    if (catalogName == null) {
      catalogName = "myhive"
    }

    var hiveConfigDir = ArgsHandler.getArgsParam(args, "hive-config-dir")
    if (hiveConfigDir == null) {
      hiveConfigDir = "/usr/local/service/hive/conf"
    }

    println(s"hiveConfigDir=${hiveConfigDir}")
    println(s"catalogName=${catalogName}")
    println(s"databaseName=${databaseName}")


    //    val hiveCatalog = new HiveCatalog(catalogName, databaseName, hiveConfigDir)
    //    tableEnv.registerCatalog(catalogName, hiveCatalog)
    //    tableEnv.useCatalog(catalogName)
    //
    //
    //    val table: Table = tableEnv
    //      .sqlQuery(
    //        s"""
    //           | SELECT * FROM ${catalogName} LIMIT 10
    //           |""".stripMargin)
    //
    //    val tableResult = table.execute()
    //
    //    tableResult.print()
  }
}
