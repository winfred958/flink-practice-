package com.winfred.core.sink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkBase;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch.util.IgnoringFailureHandler;
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.List;

/**
 * @author winfred958
 */
public class EsSink<T> {

  /**
   * @param httpHosts
   * @param userName
   * @param password
   * @return
   */
  public ElasticsearchSink<T> getIndexSink(final List<HttpHost> httpHosts, final String userName, final String password) {
    ElasticsearchSink.Builder<T> sinkBuilder = new ElasticsearchSink.Builder<>(httpHosts, new ElasticsearchSinkFunction<T>() {
      @Override
      public void process(T element, RuntimeContext ctx, RequestIndexer indexer) {
        IndexRequest indexRequest = Requests.indexRequest();
        // FIXME: 反射获取实体类自定义注解信息 (index, _id)
        indexRequest.index();
        indexRequest.id();
        indexRequest.source(JSON.toJSONString(element), XContentType.JSON);
        indexer.add(indexRequest);
      }
    });

    sinkBuilder.setBulkFlushMaxActions(5000);
    sinkBuilder.setBulkFlushMaxSizeMb(10);

    sinkBuilder.setBulkFlushBackoff(true);
    sinkBuilder.setBulkFlushBackoffRetries(3);
    sinkBuilder.setBulkFlushBackoffType(ElasticsearchSinkBase.FlushBackoffType.EXPONENTIAL);

    sinkBuilder.setFailureHandler(new IgnoringFailureHandler());
    sinkBuilder
        .setRestClientFactory(restClientBuilder -> {

          // http config
          restClientBuilder
              .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                  CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                  credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
                  return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
              });

          // RequestConfig 设置
          restClientBuilder
              .setRequestConfigCallback(requestConfigBuilder -> {
                return requestConfigBuilder.setSocketTimeout(6000);
              });

        });
    return sinkBuilder.build();
  }
}
