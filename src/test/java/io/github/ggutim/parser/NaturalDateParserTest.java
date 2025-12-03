package io.github.ggutim.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NaturalDateParserTest {

  private static final LocalDateTime REFERENCE = LocalDateTime.of(2026, 6, 15, 12, 0);

  private final NaturalDateParser parser = NaturalDateParser.builder().build();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldParseDateCorrectly")
  void shouldParseDateCorrectly(String input, LocalDateTime REFERENCE, LocalDateTime expected) {
    LocalDateTime parsed = parser.parse(input, REFERENCE);
    assertEquals(expected, parsed);
  }

  @Test
  void shouldFormatUsingDefaultFormatterWithReference() {
    LocalDateTime REFERENCE = LocalDateTime.of(2026, 6, 15, 12, 0);

    String formatted = parser.format("Tomorrow", REFERENCE);

    assertEquals("2026-06-16T12:00:00", formatted);
  }

  @Test
  void shouldFormatUsingCustomFormatter() {
    LocalDateTime REFERENCE = LocalDateTime.of(2026, 6, 15, 12, 0);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    String formatted = parser.format("Tomorrow at 11 a.m.", REFERENCE, formatter);

    assertEquals("16/06/2026 11:00", formatted);
  }

  private static Stream<Arguments> provideInputsForShouldParseDateCorrectly() {
    return Stream.of(
        Arguments.of("Today", REFERENCE, LocalDateTime.of(2026, 6, 15, 12, 0)),
        Arguments.of("Tomorrow", REFERENCE, LocalDateTime.of(2026, 6, 16, 12, 0)),
        Arguments.of("Yesterday", REFERENCE, LocalDateTime.of(2026, 6, 14, 12, 0)),
        Arguments.of("Day after tomorrow", REFERENCE, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("Day before yesterday", REFERENCE, LocalDateTime.of(2026, 6, 13, 12, 0)),
        Arguments.of("Tomorrow at 11 a.m.", REFERENCE, LocalDateTime.of(2026, 6, 16, 11, 0)),
        Arguments.of("Today at 1 pm", REFERENCE, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Today at 1 a.m.", REFERENCE, LocalDateTime.of(2026, 6, 15, 1, 0)),
        Arguments.of("Today at 13:45", REFERENCE, LocalDateTime.of(2026, 6, 15, 13, 45)),
        Arguments.of(
            "Day after tomorrow at 06:30", REFERENCE, LocalDateTime.of(2026, 6, 17, 6, 30)),
        Arguments.of("In 45 minutes", REFERENCE, LocalDateTime.of(2026, 6, 15, 12, 45)),
        Arguments.of("In 2 hours", REFERENCE, LocalDateTime.of(2026, 6, 15, 14, 0)),
        Arguments.of("at noon in 2 hours", REFERENCE, LocalDateTime.of(2026, 6, 15, 14, 0)),
        Arguments.of("In 2 days", REFERENCE, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("In 3 weeks", REFERENCE, LocalDateTime.of(2026, 7, 6, 12, 0)),
        Arguments.of("In 2 months", REFERENCE, LocalDateTime.of(2026, 8, 15, 12, 0)),
        Arguments.of("In 2 years", REFERENCE, LocalDateTime.of(2028, 6, 15, 12, 0)),
        Arguments.of("2 weeks ago", REFERENCE, LocalDateTime.of(2026, 6, 1, 12, 0)),
        Arguments.of("3 days ago at 9pm", REFERENCE, LocalDateTime.of(2026, 6, 12, 21, 0)),
        Arguments.of("March 5 2027 at 6 pm", REFERENCE, LocalDateTime.of(2027, 3, 5, 18, 0)),
        Arguments.of("5 March 2027", REFERENCE, LocalDateTime.of(2027, 3, 5, 12, 0)),
        Arguments.of("March 5", REFERENCE, LocalDateTime.of(2026, 3, 5, 12, 0)),
        Arguments.of("5 of March", REFERENCE, LocalDateTime.of(2026, 3, 5, 12, 0)),
        Arguments.of("June 1st at 17:00", REFERENCE, LocalDateTime.of(2026, 6, 1, 17, 0)),
        Arguments.of("1st of June at midnight", REFERENCE, LocalDateTime.of(2026, 6, 1, 0, 0)),
        Arguments.of("Sunday, March 3rd 2024", REFERENCE, LocalDateTime.of(2024, 3, 3, 12, 0)),
        Arguments.of("next Sunday at noon", REFERENCE, LocalDateTime.of(2026, 6, 21, 12, 0)),
        Arguments.of("last Friday at midnight", REFERENCE, LocalDateTime.of(2026, 6, 12, 0, 0)),
        Arguments.of("this Wednesday at 7am", REFERENCE, LocalDateTime.of(2026, 6, 17, 7, 0)),
        Arguments.of("Monday at 18:30", REFERENCE, LocalDateTime.of(2026, 6, 15, 18, 30)),
        Arguments.of("in 1 week next monday at 9", REFERENCE, LocalDateTime.of(2026, 6, 29, 9, 0)),
        Arguments.of("in 2 days at midnight", REFERENCE, LocalDateTime.of(2026, 6, 17, 0, 0)),
        Arguments.of("12:30", REFERENCE, LocalDateTime.of(2026, 6, 15, 12, 30)),
        Arguments.of("19:30", REFERENCE, LocalDateTime.of(2026, 6, 15, 19, 30)),
        Arguments.of("17 June 2027", REFERENCE, LocalDateTime.of(2027, 6, 17, 12, 0)),
        Arguments.of("June 17", REFERENCE, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("June 17th at noon", REFERENCE, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("3:30, March 12th, 2023", REFERENCE, LocalDateTime.of(2023, 3, 12, 3, 30)),
        Arguments.of(
            "3 thirty AM on Sunday, March 12th, 2023",
            REFERENCE,
            LocalDateTime.of(2023, 3, 12, 3, 30)),
        Arguments.of("tmrw at 5", REFERENCE, LocalDateTime.of(2026, 6, 16, 5, 0)),
        Arguments.of("tommorow at 17:30", REFERENCE, LocalDateTime.of(2026, 6, 16, 17, 30)),
        Arguments.of("tdy", REFERENCE, LocalDateTime.of(2026, 6, 15, 12, 0)),
        Arguments.of("yday at noon", REFERENCE, LocalDateTime.of(2026, 6, 14, 12, 0)),
        Arguments.of("29-12-2024", REFERENCE, LocalDateTime.of(2024, 12, 29, 12, 0)),
        Arguments.of("29/12", REFERENCE, LocalDateTime.of(2026, 12, 29, 12, 0)),
        Arguments.of("11/12/24", REFERENCE, LocalDateTime.of(2024, 12, 11, 12, 0)),
        Arguments.of("12/31/24", REFERENCE, LocalDateTime.of(2024, 12, 31, 12, 0)),
        Arguments.of("2024-12-29 at 19:30", REFERENCE, LocalDateTime.of(2024, 12, 29, 19, 30)),
        Arguments.of("29/12 at midnight", REFERENCE, LocalDateTime.of(2026, 12, 29, 0, 0)),
        Arguments.of("31.01.2025 at 08:15", REFERENCE, LocalDateTime.of(2025, 1, 31, 8, 15)),
        Arguments.of("04/05/06 at 09:00", REFERENCE, LocalDateTime.of(2006, 5, 4, 9, 0)),
        Arguments.of("03-07 at 18:00", REFERENCE, LocalDateTime.of(2026, 7, 3, 18, 0)),
        Arguments.of("2024-12-29 at 7 pm", REFERENCE, LocalDateTime.of(2024, 12, 29, 19, 0)));
  }
}
