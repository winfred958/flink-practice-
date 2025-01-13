package com.winfred.streamming.connector.fs

import com.alibaba.fastjson.JSON
import com.winfred.core.source.FlinkKafkaSource
import com.winfred.core.utils.ArgsHandler
import com.winfred.streamming.connector.fs.FileSystemConnector.LogEntity
import org.apache.commons.lang3.StringUtils
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}

object FileSystemConnectorTest {

  val jobName = "test_streaming_file_sink"
  val checkpointInterval = 60000

  val sourceTopics: String = "log_collect_web_v2,log_collect_app_v2"
  val groupId: String = "streaming_to_HdfsFile: " + this.getClass.getClasses

  def main(args: Array[String]): Unit = {
    val streamExecutionEnvironment = StreamExecutionEnvironment
      .getExecutionEnvironment

    streamExecutionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime)

    streamExecutionEnvironment.enableCheckpointing(checkpointInterval, CheckpointingMode.EXACTLY_ONCE)

    import org.apache.flink.streaming.api.scala._

    var targetPath = ArgsHandler.getArgsParam(args = args, "target-path")
    if (StringUtils.isBlank(targetPath)) {
      targetPath = "I:/tmp/ttt"
    }

    val kafkaStrDS: DataStream[String] = streamExecutionEnvironment
      .addSource(FlinkKafkaSource.getKafkaSource(sourceTopics, groupId))
      .flatMap(x => {
        for (i <- x.split("\n").toList) yield i
      }).filter(x => {
        StringUtils.isNotBlank(x)
      })
      .assignTimestampsAndWatermarks(new AscendingTimestampExtractor[String]() {
        override def extractAscendingTimestamp(element: String): Long = {
          return System.currentTimeMillis();
        }
      })

    val logDataStream: DataStream[LogEntity] = kafkaStrDS
      .map(new MapFunction[String, LogEntity] {
        override def map(value: String): LogEntity = {
          JSON.parseObject(value, classOf[LogEntity])
        }
      })

    //    logDataStream
    //      .addSink(new SinkFunction[LogEntity] {
    //
    //        override def invoke(value: LogEntity, context: SinkFunction.Context[_]): Unit = {
    //          println("========", value.server_time, value.event_name, value.uuid)
    //        }
    //      })

    FileSystemConnector
      .streamingFileSink(
        data = logDataStream,
        targetPath,
        30000L
      )

    streamExecutionEnvironment.execute(jobName)
  }
}
