package com.winfred.core.source

import com.winfred.core.config.KafkaConfig
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig

import java.util
import java.util.Properties
import java.util.regex.Pattern

/**
 * com.winfred.test.rt.source
 *
 * @author kevin
 * @since 2018/8/29 8:23
 */
object FlinkKafkaSource {

  /**
   * earliest
   * latest
   */
  var auto_offset_reset: String = "earliest"

  /** *
   *
   * @param topics 可以逗号分隔多个topic
   * @param groupId
   * @return
   */
  def getKafkaSource(topics: String, groupId: String): KafkaSource[String] = {
    val properties = getKafkaConsumerProperties()
    val topicList: util.List[String] = new util.ArrayList[String]()
    for (topic <- topics.split(",")) {
      topicList.add(topic)
    }
    val source = KafkaSource.builder[String]()
      .setProperties(properties)
      .setTopics(topicList)
      .setGroupId(groupId)
      .setValueOnlyDeserializer(new SimpleStringSchema())
      .build()
    source
  }

  /**
   *
   * @param topicRegex topic 正则表达式
   * @param groupId
   * @return
   */
  def getKafkaSourceFromTopicRegex(topicRegex: String, groupId: String): FlinkKafkaConsumer[String] = {
    val properties = getKafkaConsumerProperties()
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
    new FlinkKafkaConsumer[String](Pattern.compile(topicRegex), new SimpleStringSchema(), properties)
  }

  def getKafkaConsumerProperties(): Properties = {
    val kafkaConfigEntity = KafkaConfig.getConfigEntity()
    val consumer = kafkaConfigEntity.getKafka.getConsumer

    val properties = new Properties()
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumer.getBootstrapServers)
    properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500")
    properties.setProperty(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "30000")
    properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000")
    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000")
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumer.getKeySerializer)
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumer.getValueSerializer)
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumer.getAutoOffsetReset)
    properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, String.valueOf(consumer.getEnableAutoCommit))
    properties
  }

}
