package com.winfred.core.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;


/**
 * @author kevin
 */
public class MockUtils {

  public static Long getLong(long min, long max) {
    return RandomUtils.nextLong(min, max);
  }

  public static String getSku() {
    return DigestUtils.md5Hex(String.valueOf(getLong(0, 30000000)));
  }
}
