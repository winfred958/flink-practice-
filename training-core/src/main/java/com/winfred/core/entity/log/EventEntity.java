package com.winfred.core.entity.log;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author winfred
 */
public class EventEntity {
  @Setter
  private String uuid;

  @Setter
  private Long server_time;

  @Getter
  @Setter
  private String source;

  @Setter
  private EventHeader header;

  @Setter
  private EventBody body;

  public String getUuid() {
    if (StringUtils.isBlank(this.uuid)) {
      this.uuid = UUID.randomUUID().toString();
    }
    return uuid;
  }

  public Long getServer_time() {
    if (this.server_time == null) {
      this.server_time = System.currentTimeMillis();
    }
    return server_time;
  }

  public EventHeader getHeader() {
    if (this.header == null) {
      this.header = new EventHeader();
    }
    return header;
  }

  public EventBody getBody() {
    if (this.body == null) {
      this.body = new EventBody();
    }
    return body;
  }
}
