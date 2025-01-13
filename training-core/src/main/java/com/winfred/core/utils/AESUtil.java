package com.winfred.core.utils;


import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author kevin
 */
public class AESUtil {
  private static final String KEY_ALGORITHM = "AES";
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法

  private static final String default_passwd = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

  private static Logger logger = LoggerFactory.getLogger(AESUtil.class);

  /**
   * AES 加密操作
   *
   * @param content  待加密内容
   * @param password 加密密码
   * @return 返回Base64转码后的加密数据
   */
  public static String encrypt(String content, String password) {
    if (null == content) {
      return content;
    }
    content = content.trim();
    try {
      /**
       * 创建密码器
       */
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      byte[] byteContent = content.getBytes("utf-8");
      /**
       * 初始化为加密模式的密码器
       */
      cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
      /**
       *  加密
       */
      byte[] result = cipher.doFinal(byteContent);
      /**
       *  通过Base64转码返回
       */
      return Base64.encodeBase64String(result).replaceAll("\\s", "");
    } catch (Exception ex) {
//            logger.error("AES encrypt error", ex);
    }

    return null;
  }

  /**
   * AES 解密操作
   *
   * @param content
   * @param password
   * @return
   */
  public static String decrypt(String content, String password) {
    try {
      // 实例化
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      // 使用密钥初始化，设置为解密模式
      cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
      // 执行操作
      byte[] result = cipher.doFinal(Base64.decodeBase64(content));
      return new String(result, "utf-8");
    } catch (Exception ex) {
//            logger.error("AES decrypt error", ex);
    }
    return null;
  }

  /**
   * 生成加密秘钥
   *
   * @return
   */
  private static SecretKeySpec getSecretKey(final String password) {
    // 返回生成指定算法密钥生成器的 KeyGenerator 对象
    KeyGenerator kg = null;

    try {
      kg = KeyGenerator.getInstance(KEY_ALGORITHM);
      // 防止linux 生成随机key
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(password.getBytes());
      // AES 要求密钥长度为 128
      kg.init(128, secureRandom);
      // 生成一个密钥
      SecretKey secretKey = kg.generateKey();
      // 转换为AES专用密钥
      return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    } catch (NoSuchAlgorithmException ex) {
//            logger.error("NoSuchAlgorithmException", ex);
    }
    return null;
  }

  public static void main(String[] args) {
    String s = "你好呀, 123.456";

    System.out.println("原始数据: " + s);

    String s1 = AESUtil.encrypt(s, "1234");
    System.out.println("加密数据: " + s1);

    System.out.println("解密数据: " + AESUtil.decrypt(s1, "1234"));

    System.out.println("解密数据: " + AESUtil.decrypt("b7xeubPo9lcJ4rz7+iW0hA==", "ZHvTxIxketar28U46lvcUI7oS6g="));
  }
}
