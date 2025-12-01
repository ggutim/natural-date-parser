package io.github.ggutim.parser.pipeline;

import io.github.ggutim.tokenizer.Token;

import java.time.LocalDateTime;
import java.util.List;

public interface ParsePipeline {

  LocalDateTime parse(List<Token> tokens, LocalDateTime reference);
}
