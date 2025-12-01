package io.github.ggutim.parser;

import io.github.ggutim.parser.pipeline.ParsePipeline;
import io.github.ggutim.tokenizer.Token;
import io.github.ggutim.tokenizer.Tokenizer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entry point for parsing human-readable date/time expressions.
 * <p>
 * A {@code NaturalDateParser} produces a {@link LocalDateTime}.
 * Instances are created through {@link NaturalDateParserBuilder}.
 */
public class NaturalDateParser {

  private final Tokenizer tokenizer;
  private final ParsePipeline pipeline;

  NaturalDateParser(Tokenizer tokenizer, ParsePipeline pipeline) {
    this.tokenizer = tokenizer;
    this.pipeline = pipeline;
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
   * @param input     natural language date/time expression
   * @param reference reference point used to resolve relative expressions
   * @return resolved {@link LocalDateTime}
   */
  public LocalDateTime parse(String input, LocalDateTime reference) {
    List<Token> tokens = tokenizer.tokenize(input);
    return pipeline.parse(tokens, reference);
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
