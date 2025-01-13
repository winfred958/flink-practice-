package com.winfred.core.entity.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author winfred
 */
public class EventBody {

  @Getter
  @Setter
  private String event_name;

  @Getter
  @Setter
  private String event_type;
  /**
   * 业务相关参数
   */

  @Getter
  @Setter
  private List<Parameter> parameters;

  @Setter
  private Utm utm;

  @Data
  @AllArgsConstructor
  public static class Parameter {
    private String name;
    private String value;
  }


  public Utm getUtm() {
    if (this.utm == null) {
      this.utm = new Utm();
    }
    return utm;
  }
}
