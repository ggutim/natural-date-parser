package io.github.ggutim.parser.pipeline.rule;

import io.github.ggutim.parser.pipeline.ParseContext;
import io.github.ggutim.parser.pipeline.ParsePipeline;
import io.github.ggutim.parser.pipeline.rule.rules.*;
import io.github.ggutim.tokenizer.Token;

import java.time.LocalDateTime;
import java.util.List;

public class RuleBasedParsePipeline implements ParsePipeline {

  private final List<Rule> rules =
      List.of(
          new StructuredDateRule(),
          new AbsoluteDateRule(),
          new RelativeDateKeywordRule(),
          new RelativeQuantityRule(),
          new WeekdayRule(),
          new TimeRule());

  @Override
  public LocalDateTime parse(List<Token> tokens, LocalDateTime reference) {
    ParseContext ctx = new ParseContext(reference);

    int pos = 0;
    while (pos < tokens.size()) {
      boolean matched = false;

      for (Rule rule : rules) {
        if (rule.matches(tokens, pos)) {
          if (rule.apply(ctx, tokens, pos)) {
            pos += rule.length();
            matched = true;
            break;
          }
        }
      }

      if (!matched) pos++;
    }

    return ctx.resolve();
  }
}
