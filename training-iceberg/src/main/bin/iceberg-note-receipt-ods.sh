#!/bin/bash

FLINK_HOME=/data/kai/flink
HOME_PATH=$(
  cd "$(dirname "$0")"
  cd ..
  pwd
)

source ~/.bash_profile

CMD=$(
  cat <<EOF
${FLINK_HOME}/bin/flink run \
  --class com.winfred.iceberg.stream.NoteReceiptStreamOdsTable \
  --yarnship ${HOME_PATH}/lib/iceberg-flink-runtime-1.14-0.13.1.jar \
  --classpath file://${HOME_PATH}/lib/iceberg-flink-runtime-1.14-0.13.1.jar \
  --jobmanager yarn-cluster \
  --yarnslots 1 \
  --yarnjobManagerMemory 1024 \
  --yarntaskManagerMemory 2048 \
  --parallelism 1 \
  --detached \
  --yarnname NoteReceiptStreamOdsTable \
  --yarnqueue default \
  ${HOME_PATH}/lib/training-iceberg.jar \
    --checkpoint-dir hdfs://spacex-hadoop-qa/flink/checkpoint \
    --warehouse-path hdfs://spacex-hadoop-qa/iceberg/warehouse \
    --topic-names note_receipt_test \
    --table-name channel_note_receipt

EOF
)

echo -e "CMD:\n${CMD}\n"

${CMD}
