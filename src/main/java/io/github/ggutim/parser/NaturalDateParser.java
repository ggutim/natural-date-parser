package io.github.ggutim.parser;

import io.github.ggutim.parser.pipeline.ParsePipeline;
import io.github.ggutim.tokenizer.Token;
import io.github.ggutim.tokenizer.Tokenizer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Entry point for parsing human-readable date/time expressions.
 *
 * <p>A {@code NaturalDateParser} produces a {@link LocalDateTime}. Instances are created through
 * {@link NaturalDateParserBuilder}.
 */
public class NaturalDateParser {

  private final Tokenizer tokenizer;
  private final ParsePipeline pipeline;
  private final DateTimeFormatter formatter;

  NaturalDateParser(Tokenizer tokenizer, ParsePipeline pipeline, DateTimeFormatter formatter) {
    this.tokenizer = Objects.requireNonNull(tokenizer, "tokenizer must not be null");
    this.pipeline = Objects.requireNonNull(pipeline, "parse pipeline must not be null");
    this.formatter = Objects.requireNonNull(formatter, "formatter must not be null");
  }

  /**
   * Parses the supplied text using {@link LocalDateTime#now()} as a reference point.
   *
   * @param input natural language date/time expression
   * @return resolved {@link LocalDateTime}
   */
  public LocalDateTime parse(String input) {
    return parse(input, LocalDateTime.now());
  }

  /**
   * Parses the supplied text relative to the provided reference date/time.
   *
   * @param input natural language date/time expression
   * @param reference reference point used to resolve relative expressions
   * @return resolved {@link LocalDateTime}
   */
  public LocalDateTime parse(String input, LocalDateTime reference) {
    List<Token> tokens = tokenizer.tokenize(input);
    return pipeline.parse(tokens, reference);
  }

  /**
   * Formats the resolved date/time using the builder-provided formatter.
   *
   * @param input natural language date/time expression
   * @return formatted representation of the parsed date/time
   */
  public String format(String input) {
    return format(input, LocalDateTime.now());
  }

  /**
   * Formats the resolved date/time relative to a custom reference using the configured formatter.
   *
   * @param input natural language date/time expression
   * @param reference reference point used to resolve relative expressions
   * @return formatted representation of the parsed date/time
   */
  public String format(String input, LocalDateTime reference) {
    return format(input, reference, formatter);
  }

  /**
   * Resolves {@code input} relative to {@code reference} and formats it with the provided
   * formatter.
   *
   * @param input natural language date/time expression
   * @param reference reference point used to resolve relative expressions
   * @param formatter formatter used to render the resulting {@link LocalDateTime}
   * @return formatted representation of the parsed date/time
   */
  public String format(String input, LocalDateTime reference, DateTimeFormatter formatter) {
    LocalDateTime ldt = parse(input, reference);
    return ldt.format(formatter);
  }

  /**
   * Creates a builder that can be customized before producing a parser instance.
   *
   * @return builder configured with sensible defaults
   */
  public static NaturalDateParserBuilder builder() {
    return new NaturalDateParserBuilder();
  }
}
