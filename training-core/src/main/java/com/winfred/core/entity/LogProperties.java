package com.winfred.core.entity;

/**
 * @author kevin
 */
public class LogProperties {

  private LogHeader header;
  private LogBody body;

  public LogHeader getHeader() {
    if (null == this.header) {
      return new LogHeader();
    }
    return header;
  }

  public void setHeader(LogHeader header) {
    this.header = header;
  }

  public LogBody getBody() {
    if (null == this.body) {
      return new LogBody();
    }
    return body;
  }

  public void setBody(LogBody body) {
    this.body = body;
  }
}
