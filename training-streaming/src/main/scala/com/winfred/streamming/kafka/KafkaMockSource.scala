package com.winfred.streamming.kafka

import com.winfred.core.annotation.PassTest
import com.winfred.core.sink.FlinkKafkaSink
import com.winfred.core.source.DataMockSource
import com.winfred.core.utils.ArgsHandler
import org.apache.commons.lang3.StringUtils
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

@PassTest
object KafkaMockSource {
  var auto_offset_reset: String = "earliest"

  val groupId: String = this.getClass.getCanonicalName
  val sinkTopicName: String = "kafka_test_raw"


  def main(args: Array[String]): Unit = {
    val executionEnvironment: StreamExecutionEnvironment = StreamExecutionEnvironment
      .getExecutionEnvironment

    executionEnvironment.enableCheckpointing(60000, CheckpointingMode.EXACTLY_ONCE)
    import org.apache.flink.streaming.api.scala._
    var sinkTopic = ArgsHandler.getArgsParam(args, "topic-name")
    if (StringUtils.isBlank(sinkTopic)) sinkTopic = sinkTopicName
    val dataStream: DataStream[String] = executionEnvironment
      .addSource(new DataMockSource(500, 1000))
      .assignAscendingTimestamps(s => {
        System.currentTimeMillis()
      })

    dataStream
      .addSink(FlinkKafkaSink.getKafkaSink(topic = sinkTopic))

    executionEnvironment.execute(this.getClass.getName)
  }
}
