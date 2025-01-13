package com.winfred.training.fs;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.parquet.hadoop.ParquetOutputFormat;
import org.apache.parquet.hadoop.codec.SnappyCodec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BatchBucketWriteV2 {

  public static void main(String[] args) {
    ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();

    DataSet<Tuple2<String, String>> dataSet = getDataSource(executionEnvironment);

    try {
      dataSet.print();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  private static DataSet<Tuple2<String, String>> getDataSource(ExecutionEnvironment executionEnvironment) {
    List<Tuple2<String, String>> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        list.add(new Tuple2<>("" + i, i + "-" + j));
      }
    }
    return executionEnvironment.fromCollection(list);
  }

  private static void sinkToHdfs(DataSet<Tuple2<String, String>> dataSet, Path path) throws IOException {

    ParquetOutputFormat<Tuple2<String, String>> parquetOutputFormat = new ParquetOutputFormat<>();
    JobConf jobConf = new JobConf();
    jobConf.setCompressMapOutput(true);
    jobConf.setMapOutputCompressorClass(SnappyCodec.class);

    Job job = Job.getInstance(jobConf);
  }
}
