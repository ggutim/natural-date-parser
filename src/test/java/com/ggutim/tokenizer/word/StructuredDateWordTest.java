package com.ggutim.tokenizer.word;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StructuredDateWordTest {

  StructuredDateWord word = new StructuredDateWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("29-12-2024"),
        Arguments.of("29/12"),
        Arguments.of("11/12/24"),
        Arguments.of("12/31/24"),
        Arguments.of("2024-12-29"),
        Arguments.of("31.01.25"),
        Arguments.of("04/05/06"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("2024-13-01"),
        Arguments.of("32/01/2024"),
        Arguments.of("12/2024"),
        Arguments.of("12-31-2024-01"),
        Arguments.of("31/31/2024"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldTokenize")
  void shouldTokenize(String inputWord, Token expectedToken) {
    Token token = word.tokenize(inputWord);
    assertEquals(expectedToken.type(), token.type());
    assertEquals(expectedToken.text(), token.text());
    assertEquals(expectedToken.value(), token.value());
  }

  private static Stream<Arguments> provideInputsForShouldTokenize() {
    return Stream.of(
        Arguments.of(
            "29-12-2024",
            new Token(
                TokenType.STRUCTURED_DATE,
                "29-12-2024",
                new StructuredDateWord.DateParts(29, 12, 2024))),
        Arguments.of(
            "11/12/24",
            new Token(
                TokenType.STRUCTURED_DATE,
                "11/12/24",
                new StructuredDateWord.DateParts(11, 12, 2024))),
        Arguments.of(
            "12/31/24",
            new Token(
                TokenType.STRUCTURED_DATE,
                "12/31/24",
                new StructuredDateWord.DateParts(31, 12, 2024))),
        Arguments.of(
            "29/12",
            new Token(
                TokenType.STRUCTURED_DATE,
                "29/12",
                new StructuredDateWord.DateParts(29, 12, null))),
        Arguments.of(
            "31.01.25",
            new Token(
                TokenType.STRUCTURED_DATE,
                "31.01.25",
                new StructuredDateWord.DateParts(31, 1, 2025))),
        Arguments.of(
            "04/05/06",
            new Token(
                TokenType.STRUCTURED_DATE,
                "04/05/06",
                new StructuredDateWord.DateParts(4, 5, 2006))));
  }
}
