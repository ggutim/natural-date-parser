package io.github.ggutim.tokenizer.word;

import io.github.ggutim.tokenizer.Token;
import io.github.ggutim.tokenizer.TokenType;

import java.time.DateTimeException;
import java.time.LocalDate;
public class StructuredDateWord implements Word {

  private static final String SEPARATOR_REGEX = "[/\\-.]";
  private static final int TWO_DIGIT_YEAR_CUTOFF = 50; // 00-50 => 2000s, 51-99 => 1900s

  public record DateParts(int day, int month, Integer year) {
    public boolean hasYear() {
      return year != null;
    }
  }

  @Override
  public boolean match(String word) {
    return parse(word) != null;
  }

  @Override
  public Token tokenize(String word) {
    DateParts parts = parse(word);
    if (parts != null) {
      return new Token(TokenType.STRUCTURED_DATE, word, parts);
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }

  private DateParts parse(String word) {
    String[] pieces = word.split(SEPARATOR_REGEX);
    if (pieces.length == 3) {
      return parseTriple(pieces);
    }
    if (pieces.length == 2) {
      return parseDouble(pieces);
    }
    return null;
  }

  private DateParts parseTriple(String[] pieces) {
    Integer first = toInt(pieces[0]);
    Integer second = toInt(pieces[1]);
    Integer third = toInt(pieces[2]);
    if (first == null || second == null || third == null) return null;

    // 1) D-M-Y
    Integer year = expandYear(third);
    if (isValidDate(first, second, year)) {
      return new DateParts(first, second, year);
    }

    // 2) Y-M-D (ISO-like)
    year = expandYear(first);
    if (isValidDate(third, second, year)) {
      return new DateParts(third, second, year);
    }

    // 3) M-D-Y (US)
    year = expandYear(third);
    if (isValidDate(second, first, year)) {
      return new DateParts(second, first, year);
    }

    return null;
  }

  private DateParts parseDouble(String[] pieces) {
    Integer first = toInt(pieces[0]);
    Integer second = toInt(pieces[1]);
    if (first == null || second == null) return null;

    // Priority: D-M then M-D
    if (isValidDate(first, second, 2000)) {
      return new DateParts(first, second, null);
    }
    if (isValidDate(second, first, 2000)) {
      return new DateParts(second, first, null);
    }
    return null;
  }

  private Integer expandYear(Integer value) {
    if (value == null) return null;
    if (value >= 100) return value;
    if (value <= TWO_DIGIT_YEAR_CUTOFF) return 2000 + value;
    return 1900 + value;
  }

  private boolean isValidDate(int day, int month, Integer year) {
    int y = (year != null) ? year : 2000; // placeholder leap year for validation
    try {
      LocalDate.of(y, month, day);
      return true;
    } catch (DateTimeException e) {
      return false;
    }
  }

  private Integer toInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
