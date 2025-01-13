package com.winfred.core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 */
public class LogBody {
  private String event_pagekey;
  private String event_name;
  private String event_memo;
  private String event_type;
  private List<String> event_elements;
  private String event_track_type;
  private List<LogParameter> event_parameters;
  private List<LogUtm> utm;

  public String getEvent_pagekey() {
    return event_pagekey;
  }

  public void setEvent_pagekey(String event_pagekey) {
    this.event_pagekey = event_pagekey;
  }

  public String getEvent_name() {
    return event_name;
  }

  public void setEvent_name(String event_name) {
    this.event_name = event_name;
  }

  public String getEvent_memo() {
    return event_memo;
  }

  public void setEvent_memo(String event_memo) {
    this.event_memo = event_memo;
  }

  public String getEvent_type() {
    return event_type;
  }

  public void setEvent_type(String event_type) {
    this.event_type = event_type;
  }

  public String getEvent_track_type() {
    return event_track_type;
  }

  public void setEvent_track_type(String event_track_type) {
    this.event_track_type = event_track_type;
  }

  public List<LogParameter> getEvent_parameters() {
    if (null == this.event_parameters) {
      return new ArrayList<>();
    }
    return event_parameters;
  }

  public void setEvent_parameters(List<LogParameter> event_parameters) {
    this.event_parameters = event_parameters;
  }

  public List<String> getEvent_elements() {
    if (null == this.event_elements) {
      return new ArrayList<>();
    }
    return event_elements;
  }

  public void setEvent_elements(List<String> event_elements) {
    this.event_elements = event_elements;
  }

  public List<LogUtm> getUtm() {
    if (null == this.utm) {
      this.utm = new ArrayList<>();
    }
    return utm;
  }

  public void setUtm(List<LogUtm> utm) {
    this.utm = utm;
  }
}
