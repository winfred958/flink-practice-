package com.winfred.core.entity;

import com.google.gson.Gson;

/**
 * @author kevin
 */
public class LogEntity {

  private String uuid;
  private String channel;
  private String event;
  private LogProperties properties;
  private Long server_time;
  private String user_agent;
  private String ip;
  private String language;


  public Long getServer_time() {
    return server_time;
  }

  public void setServer_time(Long server_time) {
    this.server_time = server_time;
  }


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getUser_agent() {
    return user_agent;
  }

  public void setUser_agent(String user_agent) {
    this.user_agent = user_agent;
  }

  public LogProperties getProperties() {
    if (null == this.properties) {
      return new LogProperties();
    }
    return properties;
  }

  public void setProperties(LogProperties properties) {
    this.properties = properties;
  }

  public LogEntity jsonParseToObject(Gson gson, String jsonStr) {
    try {
      return gson.fromJson(jsonStr, LogEntity.class);
    } catch (Exception e) {
      return new LogEntity();
    }
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }
}
