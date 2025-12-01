package io.github.ggutim.normalizer;

public final class LowerCaseNormalizer implements Normalizer {

  @Override
  public String normalize(String input) {
    return input.toLowerCase();
  }
}
