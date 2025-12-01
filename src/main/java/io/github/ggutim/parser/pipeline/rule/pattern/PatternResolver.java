package io.github.ggutim.parser.pipeline.rule.pattern;

import io.github.ggutim.parser.pipeline.ParseContext;
import io.github.ggutim.tokenizer.Token;

import java.util.List;

@FunctionalInterface
public interface PatternResolver {

  boolean resolve(List<Token> tokens, ParseContext ctx);
}
