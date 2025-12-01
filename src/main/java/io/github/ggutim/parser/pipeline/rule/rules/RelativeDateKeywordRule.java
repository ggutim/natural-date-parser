package io.github.ggutim.parser.pipeline.rule.rules;

import io.github.ggutim.parser.pipeline.rule.Rule;
import io.github.ggutim.parser.pipeline.rule.pattern.Pattern;
import io.github.ggutim.tokenizer.word.DateKeywordWord.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.ggutim.tokenizer.TokenType.*;

public class RelativeDateKeywordRule extends Rule {

  private final List<Pattern> patterns = List.of(getDateKeywordPattern());

  @Override
  public List<Pattern> getPatterns() {
    return patterns;
  }

  private Pattern getDateKeywordPattern() {
    return Pattern.of(
        "DATEKEYWORD",
        (tokens, ctx) -> {
          DateKeyword value = (DateKeyword) tokens.get(0).value();
          switch (value) {
            case YESTERDAY -> ctx.setRelative(-1, ChronoUnit.DAYS);
            case TOMORROW -> ctx.setRelative(1, ChronoUnit.DAYS);
            case DAY_BEFORE_YESTERDAY -> ctx.setRelative(-2, ChronoUnit.DAYS);
            case DAY_AFTER_TOMORROW -> ctx.setRelative(2, ChronoUnit.DAYS);
            default -> ctx.setRelative(0, ChronoUnit.DAYS);
          }

          return true;
        },
        List.of(DATE_KEYWORD));
  }
}
