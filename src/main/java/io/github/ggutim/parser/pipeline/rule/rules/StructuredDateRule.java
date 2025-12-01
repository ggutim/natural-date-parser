package io.github.ggutim.parser.pipeline.rule.rules;

import io.github.ggutim.parser.pipeline.rule.Rule;
import io.github.ggutim.parser.pipeline.rule.pattern.Pattern;
import io.github.ggutim.tokenizer.word.StructuredDateWord;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static io.github.ggutim.tokenizer.TokenType.STRUCTURED_DATE;

public class StructuredDateRule extends Rule {

  private final List<Pattern> patterns = List.of(getStructuredDatePattern());

  @Override
  public List<Pattern> getPatterns() {
    return patterns;
  }

  private Pattern getStructuredDatePattern() {
    return Pattern.of(
        "STRUCTURED_DATE",
        (tokens, ctx) -> {
          StructuredDateWord.DateParts parts =
              (StructuredDateWord.DateParts) tokens.get(0).value();

          Month month = Month.of(parts.month());
          MonthDay monthDay = MonthDay.of(month, parts.day());
          Year year = parts.hasYear() ? Year.of(parts.year()) : null;

          ctx.setAbsoluteDate(monthDay, month, year);
          return true;
        },
        List.of(STRUCTURED_DATE));
  }
}
