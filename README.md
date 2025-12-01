# Natural Date Parser for Java 🗣📅☕

Parse human language like “next Friday at 8pm” into concrete `java.time.LocalDateTime` values. Everything runs locally, powered by a combination of normalization, tokenization, and rule-based pipelines (zero AI).

👉 **Docs & API reference:** https://ggutim.github.io/natural-date-parser/

---

## Highlights

- Relative keywords (_today_, _tomorrow_, _yesterday_)
- Offsets with units (_in 45 minutes_, _3 weeks ago_)
- Weekday expressions (_next Monday_, _last Friday at noon_)
- Absolute dates & times (_21st of March at 10:00_)
- Fuzzy abbreviations (_jan_, _thu_, _tmr_)
- Numeric formats (_10/02/2025_, _2025-02-10 at 19:00_)

### On the roadmap
- Richer relative grammar (_a week from Friday_, _the day after next Monday_)
- Additional locale/time-zone awareness
- Multi-language tokenizers

---

## Installation

Available from Maven Central as `io.github.ggutim:natural-date-parser`.

### Maven
```xml
<dependency>
  <groupId>io.github.ggutim</groupId>
  <artifactId>natural-date-parser</artifactId>
  <version>1.0.2</version>
</dependency>
```

### Gradle
```groovy
implementation("io.github.ggutim:natural-date-parser:1.0.2")
```

---

## Quick start

```java
import com.ggutim.parser.NaturalDateParser;

NaturalDateParser parser = NaturalDateParser.builder().build();

// Uses LocalDateTime.now()
LocalDateTime quick = parser.parse("Tomorrow at 5pm");

// Provide a deterministic reference (useful for tests)
LocalDateTime reference = LocalDateTime.of(2024, 5, 10, 12, 0);
LocalDateTime relative = parser.parse("next friday at noon", reference);
```

Need more examples? Check the [usage guide](https://ggutim.github.io/natural-date-parser/#usage).

---

## Contributing

1. Fork the repo and create a feature branch.
2. Add tests when changing parsing behavior.
3. Run `mvn test` and include screenshots/logs if you fix parser edge cases.

Open an [issue](https://github.com/ggutim/natural-date-parser/issues) if you hit tricky phrases or want to propose new rules.
