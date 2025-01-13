package com.winfred.core.utils;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class IkAnalyzerUtils {

  private static final Logger log = LoggerFactory.getLogger(IkAnalyzerUtils.class);

  /**
   * 词典仅初始化一次
   */
  private static class Singleton {
    private static final IKAnalyzer analyzer = new IKAnalyzer();
    private static final IKAnalyzer analyzerSmart = new IKAnalyzer(true);
  }

  public static List<String> getTerms(String text) {
    return getTerms(text, false);
  }

  public static List<String> getTerms(String text, boolean useSmart) {
    List<String> termList = new ArrayList<>(16);
    try (
        StringReader reader = new StringReader(text);
        TokenStream tokenStream = getAnalyzer(useSmart).tokenStream("", reader);
    ) {
      tokenStream.reset();
      while (tokenStream.incrementToken()) {
        CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
        termList.add(String.valueOf(term));
      }
    } catch (IOException e) {
      log.error("", e);
    }
    return termList;
  }

  private static IKAnalyzer getAnalyzer(boolean useSmart) {
    if (useSmart) {
      return Singleton.analyzerSmart;
    } else {
      return Singleton.analyzer;
    }
  }
}
