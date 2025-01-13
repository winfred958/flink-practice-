package com.winfred.core.entity;

/**
 * @author kevin
 */
public class LogParameter {

  private String attribute_name;
  private String name;
  private String parameter_id;
  private String selector;
  private String track_id;
  private String type;
  private Object value;
  private Object condition;

  public String getAttribute_name() {
    return attribute_name;
  }

  public void setAttribute_name(String attribute_name) {
    this.attribute_name = attribute_name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getParameter_id() {
    return parameter_id;
  }

  public void setParameter_id(String parameter_id) {
    this.parameter_id = parameter_id;
  }

  public String getSelector() {
    return selector;
  }

  public void setSelector(String selector) {
    this.selector = selector;
  }

  public String getTrack_id() {
    return track_id;
  }

  public void setTrack_id(String track_id) {
    this.track_id = track_id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public Object getCondition() {
    return condition;
  }

  public void setCondition(Object condition) {
    this.condition = condition;
  }
}
