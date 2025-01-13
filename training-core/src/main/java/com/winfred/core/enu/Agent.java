package com.winfred.core.enu;

public enum Agent {
  /**
   *
   */
  CHROME("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36"),
  /**
   *
   */

  ;

  private String value;

  public String getValue() {
    return value;
  }

  Agent(String value) {
    this.value = value;
  }
}
