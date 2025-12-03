package io.github.ggutim.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NaturalDateParserBuilderTest {

  private static final LocalDateTime REFERENCE = LocalDateTime.of(2026, 6, 15, 12, 0);

  @Test
  void shouldBuildParserWithDefaults() {
    NaturalDateParser parser = NaturalDateParser.builder().build();

    LocalDateTime parsed = parser.parse("Tomorrow", REFERENCE);

    assertEquals(LocalDateTime.of(2026, 6, 16, 12, 0), parsed);
  }

  @Test
  void shouldFormatUsingDefaultFormatter() {
    NaturalDateParser parser = NaturalDateParser.builder().build();

    String formatted = parser.format("Tomorrow", REFERENCE);

    assertEquals("2026-06-16T12:00:00", formatted);
  }

  @Test
  void shouldUseBuilderConfiguredFormatter() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    NaturalDateParser parser = NaturalDateParser.builder().formatter(formatter).build();

    String formatted = parser.format("Tomorrow at 11 a.m.", REFERENCE);

    assertEquals("16/06/2026 11:00", formatted);
  }
}
