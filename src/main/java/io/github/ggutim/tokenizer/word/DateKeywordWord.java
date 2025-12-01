package io.github.ggutim.tokenizer.word;

import io.github.ggutim.tokenizer.Token;
import io.github.ggutim.tokenizer.TokenType;
import java.util.Map;

public class DateKeywordWord implements Word {

  public static final Map<String, DateKeyword> DATE_KEYWORDS =
      Map.ofEntries(
          Map.entry("today", DateKeyword.TODAY),
          Map.entry("tdy", DateKeyword.TODAY),
          Map.entry("tday", DateKeyword.TODAY),
          Map.entry("2day", DateKeyword.TODAY),
          Map.entry("tomorrow", DateKeyword.TOMORROW),
          Map.entry("tmrw", DateKeyword.TOMORROW),
          Map.entry("tmr", DateKeyword.TOMORROW),
          Map.entry("tomorow", DateKeyword.TOMORROW),
          Map.entry("tomoro", DateKeyword.TOMORROW),
          Map.entry("tommorow", DateKeyword.TOMORROW),
          Map.entry("tommorrow", DateKeyword.TOMORROW),
          Map.entry("2moro", DateKeyword.TOMORROW),
          Map.entry("2morrow", DateKeyword.TOMORROW),
          Map.entry("yesterday", DateKeyword.YESTERDAY),
          Map.entry("yday", DateKeyword.YESTERDAY),
          Map.entry("ystrday", DateKeyword.YESTERDAY),
          Map.entry("day_before_yesterday", DateKeyword.DAY_BEFORE_YESTERDAY),
          Map.entry("day_after_tomorrow", DateKeyword.DAY_AFTER_TOMORROW));

  @Override
  public boolean match(String word) {
    return DATE_KEYWORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (match(word)) {
      return new Token(TokenType.DATE_KEYWORD, word, DATE_KEYWORDS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }

  public enum DateKeyword {
    TODAY,
    TOMORROW,
    YESTERDAY,
    DAY_AFTER_TOMORROW,
    DAY_BEFORE_YESTERDAY,
  }
}
