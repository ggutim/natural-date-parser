package io.github.ggutim.normalizer.pipeline;

import io.github.ggutim.normalizer.*;

import java.util.List;

public class DefaultNormalizationPipeline extends NormalizationPipeline {

  @Override
  public List<Normalizer> getNormalizers() {
    return List.of(
        new LowerCaseNormalizer(),
        new CharacterNormalizer(),
        new WhitespaceNormalizer(),
        new NumberWordNormalizer(),
        new MultiWordNormalizer());
  }
}
