package io.github.ggutim.parser;

import io.github.ggutim.normalizer.pipeline.DefaultNormalizationPipeline;
import io.github.ggutim.parser.pipeline.ParsePipeline;
import io.github.ggutim.parser.pipeline.rule.RuleBasedParsePipeline;
import io.github.ggutim.tokenizer.Tokenizer;
import io.github.ggutim.tokenizer.word.WordTokenizer;

/**
 * Fluent builder for {@link NaturalDateParser} instances.
 * <p>
 * Provides sensible defaults for the tokenizer and parse pipeline, while still
 * giving callers a single entry point to customize those components in the future.
 */
public class NaturalDateParserBuilder {

  private final NaturalDateParser parser;
  private static final Tokenizer defaultTokenizer =
      new Tokenizer(new DefaultNormalizationPipeline(), new WordTokenizer());
  private static final ParsePipeline defaultParsePipeline = new RuleBasedParsePipeline();

  NaturalDateParserBuilder() {
    this.parser = new NaturalDateParser(defaultTokenizer, defaultParsePipeline);
  }

  /**
   * Returns a parser configured with defaults from the builder.
   *
   * @return ready-to-use parser
   */
  public NaturalDateParser build() {
    return parser;
  }
}
