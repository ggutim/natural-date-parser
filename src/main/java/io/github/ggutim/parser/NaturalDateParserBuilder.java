package io.github.ggutim.parser;

import io.github.ggutim.normalizer.pipeline.DefaultNormalizationPipeline;
import io.github.ggutim.parser.pipeline.ParsePipeline;
import io.github.ggutim.parser.pipeline.rule.RuleBasedParsePipeline;
import io.github.ggutim.tokenizer.Tokenizer;
import io.github.ggutim.tokenizer.word.WordTokenizer;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Builder for {@link NaturalDateParser} instances.
 *
 * <p>Provides defaults for the tokenizer and parse pipeline, while still giving callers a single
 * entry point to customize those components in the future.
 */
public class NaturalDateParserBuilder {

  private static final Tokenizer DEFAULT_TOKENIZER =
      new Tokenizer(new DefaultNormalizationPipeline(), new WordTokenizer());
  private static final ParsePipeline DEFAULT_PARSE_PIPELINE = new RuleBasedParsePipeline();
  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  private Tokenizer tokenizer = DEFAULT_TOKENIZER;
  private ParsePipeline parsePipeline = DEFAULT_PARSE_PIPELINE;
  private DateTimeFormatter formatter = DEFAULT_FORMATTER;

  NaturalDateParserBuilder() {}

  /**
   * Overrides the default formatter used by the {@link NaturalDateParser#format(String)} helpers.
   *
   * @param formatter formatter to use for string parsing/formatting helpers
   * @return this builder for chaining
   */
  public NaturalDateParserBuilder formatter(DateTimeFormatter formatter) {
    this.formatter = Objects.requireNonNull(formatter, "formatter must not be null");
    return this;
  }

  /**
   * Returns a parser configured with defaults from the builder.
   *
   * @return a {@link NaturalDateParser} instance
   */
  public NaturalDateParser build() {
    return new NaturalDateParser(tokenizer, parsePipeline, formatter);
  }
}
