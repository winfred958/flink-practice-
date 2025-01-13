package com.winfred.core.entity;

import com.winfred.core.annotation.MysqlID;
import com.winfred.core.annotation.MysqlResult;
import com.winfred.core.annotation.MysqlTableName;

/**
 * @author kevin
 */

@MysqlTableName(name = "database_name.table_name")
public class MysqlObjectEntity {

  @MysqlID
  private Long id;

  @MysqlResult(column = "timestamp", unique = true)
  private Long timestamp;

  @MysqlResult(column = "tag", unique = true)
  private String tag;

  @MysqlResult(column = "value")
  private Long value;

  @MysqlResult(column = "update_timestamp")
  private Long updateTimestamp;

  @MysqlResult(column = "update_time")
  private String updateTime;

  public MysqlObjectEntity(Long id, Long timestamp, String tag, Long value, Long updateTimestamp, String updateTime) {
    this.id = id;
    this.timestamp = timestamp;
    this.tag = tag;
    this.value = value;
    this.updateTimestamp = updateTimestamp;
    this.updateTime = updateTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  public Long getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(Long updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }


}
