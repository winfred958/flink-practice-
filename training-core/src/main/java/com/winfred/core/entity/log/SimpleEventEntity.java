package com.winfred.core.entity.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleEventEntity {
  private String uuid;

  private Long server_time;

  private String source;

  private String platform;

  private String visitor_id;
  private String session_id;
  private String token;

  private Long action_time;

  private String os;
  private String os_version;
  private String lib; //sdk名称
  private String lib_version;// sdk版本

  private String ip;

  private String screen_height;
  private String screen_width;

  /**
   * web
   */
  private String browser;
  private String browser_version;
  private String agent;
  private String referer;
  private String current_url;

  private String initial_referrer;
  private String initial_referring_domain;
  private String language;
  /**
   * app
   */

  private String app_version;
  private String app_build_number;

  private String lat;
  private String lon;

  private String device;
  private String device_type;
  private String manufacturer;// 制造商
  private String carrier;// 运营商
  private String model;
  private String radio;
  private String wifi;

  private String device_language;
  private String app_language;

  @Data
  @AllArgsConstructor
  public static class Parameter {
    private String name;
    private String value;
  }

  private String event_name;

  private String event_type;
  /**
   * 业务相关参数
   */

  private List<Parameter> parameters;
}
