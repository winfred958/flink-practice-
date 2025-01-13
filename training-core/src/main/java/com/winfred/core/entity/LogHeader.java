package com.winfred.core.entity;

/**
 * @author kevin
 */
public class LogHeader {
  private String platform;

  private String $browser_version;  //浏览器版本
  private String browser_version;  //浏览器版本

  private String $referring_domain; //链接域名
  private String referring_domain; //链接域名

  private String $os;               //操作系统 ios android
  private String os;               //操作系统 ios android

  private String $os_version;
  private String os_version;

  private String $screen_width;     //浏览器宽度
  private String screen_width;     //浏览器宽度

  private String $initial_referring_domain;
  private String initial_referring_domain;

  private String $screen_height;
  private String screen_height;

  private String $browser;
  private String browser;

  private String user_agent;
  private Long action_time;

  private String $referrer;   //父页面的链接
  private String referrer;   //父页面的链接

  private String token;       //用户token
  private String $device;  //设备类型
  private String device;  //设备类型
  private String session_id;
  private String visitor_id;

  private String $current_url;
  private String current_url;

  private String distinct_id;    //token

  private String $lib_version;
  private String lib_version;

  private String $initial_referrer;
  private String initial_referrer;

  private String mp_lib;
  private String mp_page;
  private String mp_referrer;
  private String mp_browser;       //浏览器名称
  private String mp_platform;
  private String ip;

  private String user_name;
  private String channel;

  private String app_version;
  private String device_type;
  private String device_id;

  private String page_name;

  private String lat;
  private String lon;

  private String $model;

  private String $app_version;
  private String $radio;
  private String $manufacturer;
  private String $carrier;
  private String $wifi;
  private String $app_build_number;

  private String model;
  private String radio;
  private String manufacturer;
  private String carrier;
  private String wifi;
  private String app_build_number;


  public String getPlatform() {
    if (this.platform == null) {
      this.platform = this.channel;
    }
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String get$browser_version() {
    return $browser_version;
  }

  public String getBrowser_version() {
    return $browser_version;
  }

  public void set$browser_version(String $browser_version) {
    this.$browser_version = $browser_version;
  }

  public String get$referring_domain() {
    return $referring_domain;
  }

  public String getReferring_domain() {
    return $referring_domain;
  }

  public void set$referring_domain(String $referring_domain) {
    this.$referring_domain = $referring_domain;
  }

  public String get$os() {
    return $os;
  }

  public String getOs() {
    return $os;
  }

  public void set$os(String $os) {
    this.$os = $os;
  }

  public String getMp_browser() {
    return mp_browser;
  }

  public void setMp_browser(String mp_browser) {
    this.mp_browser = mp_browser;
  }

  public String get$screen_width() {
    return $screen_width;
  }

  public String getScreen_width() {
    return $screen_width;
  }

  public void set$screen_width(String $screen_width) {
    this.$screen_width = $screen_width;
  }

  public String get$initial_referring_domain() {
    return $initial_referring_domain;
  }

  public String getInitial_referring_domain() {
    return $initial_referring_domain;
  }

  public void set$initial_referring_domain(String $initial_referring_domain) {
    this.$initial_referring_domain = $initial_referring_domain;
  }

  public String getMp_platform() {
    return mp_platform;
  }

  public void setMp_platform(String mp_platform) {
    this.mp_platform = mp_platform;
  }

  public String get$screen_height() {
    return $screen_height;
  }

  public String getScreen_height() {
    return $screen_height;
  }

  public void set$screen_height(String $screen_height) {
    this.$screen_height = $screen_height;
  }

  public String get$browser() {
    return $browser;
  }

  public String getBrowser() {
    return $browser;
  }

  public void set$browser(String $browser) {
    this.$browser = $browser;
  }

  public String get$referrer() {
    return $referrer;
  }

  public String getReferrer() {
    return $referrer;
  }

  public void set$referrer(String $referrer) {
    this.$referrer = $referrer;
  }

  public String getToken() {
    return this.distinct_id;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String get$device() {
    return $device;
  }

  public String getDevice() {
    return $device;
  }

  public void set$device(String $device) {
    this.$device = $device;
  }

  public String get$current_url() {
    return $current_url;
  }

  public String getCurrent_url() {
    return $current_url;
  }

  public void set$current_url(String $current_url) {
    this.$current_url = $current_url;
  }

  public String getDistinct_id() {
    return distinct_id;
  }

  public void setDistinct_id(String distinct_id) {
    this.distinct_id = distinct_id;
  }

  public String get$lib_version() {
    return $lib_version;
  }

  public String getLib_version() {
    return $lib_version;
  }

  public void set$lib_version(String $lib_version) {
    this.$lib_version = $lib_version;
  }

  public String get$initial_referrer() {
    return $initial_referrer;
  }

  public String getInitial_referrer() {
    return $initial_referrer;
  }

  public void set$initial_referrer(String $initial_referrer) {
    this.$initial_referrer = $initial_referrer;
  }

  public String getMp_lib() {
    return mp_lib;
  }

  public void setMp_lib(String mp_lib) {
    this.mp_lib = mp_lib;
  }

  public String getMp_page() {
    return mp_page;
  }

  public void setMp_page(String mp_page) {
    this.mp_page = mp_page;
  }

  public String getUser_name() {
    return this.token;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getSession_id() {
    return session_id;
  }

  public void setSession_id(String session_id) {
    this.session_id = session_id;
  }

  public String getVisitor_id() {
    return visitor_id;
  }

  public void setVisitor_id(String visitor_id) {
    this.visitor_id = visitor_id;
  }

  public String getMp_referrer() {
    return mp_referrer;
  }

  public void setMp_referrer(String mp_referrer) {
    this.mp_referrer = mp_referrer;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getApp_version() {
    if (null == app_version) {
      return $app_version;
    }
    return app_version;
  }

  public void setApp_version(String app_version) {
    this.app_version = app_version;
  }

  public String getDevice_type() {
    return device_type;
  }

  public void setDevice_type(String device_type) {
    this.device_type = device_type;
  }

  public String getDevice_id() {
    return device_id;
  }

  public void setDevice_id(String device_id) {
    this.device_id = device_id;
  }

  public String getUser_agent() {
    return user_agent;
  }

  public void setUser_agent(String user_agent) {
    this.user_agent = user_agent;
  }

  public String get$os_version() {
    return $os_version;
  }

  public void set$os_version(String $os_version) {
    this.$os_version = $os_version;
  }

  public String getOs_version() {
    return $os_version;
  }

  public Long getAction_time() {
    return action_time;
  }

  public void setAction_time(Long action_time) {
    this.action_time = action_time;
  }

  public String get$model() {
    return $model;
  }

  public void set$model(String $model) {
    this.$model = $model;
  }

  public String get$app_version() {
    return $app_version;
  }

  public void set$app_version(String $app_version) {
    this.$app_version = $app_version;
  }

  public String get$radio() {
    return $radio;
  }

  public void set$radio(String $radio) {
    this.$radio = $radio;
  }

  public String get$manufacturer() {
    return $manufacturer;
  }

  public void set$manufacturer(String $manufacturer) {
    this.$manufacturer = $manufacturer;
  }

  public String get$carrier() {
    return $carrier;
  }

  public void set$carrier(String $carrier) {
    this.$carrier = $carrier;
  }

  public String get$wifi() {
    return $wifi;
  }

  public void set$wifi(String $wifi) {
    this.$wifi = $wifi;
  }

  public String get$app_build_number() {
    return $app_build_number;
  }

  public void set$app_build_number(String $app_build_number) {
    this.$app_build_number = $app_build_number;
  }

  public String getPage_name() {
    return page_name;
  }

  public void setPage_name(String page_name) {
    this.page_name = page_name;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getLat() {
    return lat;
  }

  public String getLon() {
    return lon;
  }

  public String getModel() {
    return $model;
  }

  public String getRadio() {
    return $radio;
  }

  public String getManufacturer() {
    return $manufacturer;
  }

  public String getCarrier() {
    return $carrier;
  }

  public String getWifi() {
    return $wifi;
  }

  public String getApp_build_number() {
    return $app_build_number;
  }
}
