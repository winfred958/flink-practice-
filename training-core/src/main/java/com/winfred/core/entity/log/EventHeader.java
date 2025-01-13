package com.winfred.core.entity.log;

public class EventHeader {
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


  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getVisitor_id() {
    return visitor_id;
  }

  public void setVisitor_id(String visitor_id) {
    this.visitor_id = visitor_id;
  }

  public String getSession_id() {
    return session_id;
  }

  public void setSession_id(String session_id) {
    this.session_id = session_id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getAction_time() {
    return action_time;
  }

  public void setAction_time(Long action_time) {
    this.action_time = action_time;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public String getOs_version() {
    return os_version;
  }

  public void setOs_version(String os_version) {
    this.os_version = os_version;
  }

  public String getLib() {
    return lib;
  }

  public void setLib(String lib) {
    this.lib = lib;
  }

  public String getLib_version() {
    return lib_version;
  }

  public void setLib_version(String lib_version) {
    this.lib_version = lib_version;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getScreen_height() {
    return screen_height;
  }

  public void setScreen_height(String screen_height) {
    this.screen_height = screen_height;
  }

  public String getScreen_width() {
    return screen_width;
  }

  public void setScreen_width(String screen_width) {
    this.screen_width = screen_width;
  }

  public String getBrowser() {
    return browser;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }

  public String getBrowser_version() {
    return browser_version;
  }

  public void setBrowser_version(String browser_version) {
    this.browser_version = browser_version;
  }

  public String getAgent() {
    return agent;
  }

  public void setAgent(String agent) {
    this.agent = agent;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public String getCurrent_url() {
    return current_url;
  }

  public void setCurrent_url(String current_url) {
    this.current_url = current_url;
  }

  public String getInitial_referrer() {
    return initial_referrer;
  }

  public void setInitial_referrer(String initial_referrer) {
    this.initial_referrer = initial_referrer;
  }

  public String getInitial_referring_domain() {
    return initial_referring_domain;
  }

  public void setInitial_referring_domain(String initial_referring_domain) {
    this.initial_referring_domain = initial_referring_domain;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getApp_version() {
    return app_version;
  }

  public void setApp_version(String app_version) {
    this.app_version = app_version;
  }

  public String getApp_build_number() {
    return app_build_number;
  }

  public void setApp_build_number(String app_build_number) {
    this.app_build_number = app_build_number;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getDevice_type() {
    return device_type;
  }

  public void setDevice_type(String device_type) {
    this.device_type = device_type;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRadio() {
    return radio;
  }

  public void setRadio(String radio) {
    this.radio = radio;
  }

  public String getWifi() {
    return wifi;
  }

  public void setWifi(String wifi) {
    this.wifi = wifi;
  }

  public String getDevice_language() {
    return device_language;
  }

  public void setDevice_language(String device_language) {
    this.device_language = device_language;
  }

  public String getApp_language() {
    return app_language;
  }

  public void setApp_language(String app_language) {
    this.app_language = app_language;
  }
}

