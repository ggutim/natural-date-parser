package io.github.ggutim.tokenizer;

import io.github.ggutim.normalizer.pipeline.DefaultNormalizationPipeline;
import io.github.ggutim.normalizer.pipeline.NormalizationPipeline;
import io.github.ggutim.tokenizer.word.WordTokenizer;

import java.util.*;

public final class Tokenizer {

  private final NormalizationPipeline normalizer;
  private final WordTokenizer wordTokenizer;

  public Tokenizer(DefaultNormalizationPipeline normalizer, WordTokenizer wordTokenizer) {
    this.normalizer = normalizer;
    this.wordTokenizer = wordTokenizer;
  }

  public List<Token> tokenize(String input) {
    String cleaned = normalizer.normalize(input);
    String[] parts = cleaned.split("\\s+");

    List<Token> tokens = new ArrayList<>();

    for (String part : parts) {
      tokens.add(wordTokenizer.tokenize(part));
    }

    return tokens;
  }
}
