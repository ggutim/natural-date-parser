package io.github.ggutim.tokenizer.word;

import io.github.ggutim.tokenizer.Token;

public interface Word {

  boolean match(String word);

  Token tokenize(String word);
}
