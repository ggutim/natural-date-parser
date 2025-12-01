# Natural Date Parser for Java ☕🗣📅

## Description

**Natural Date Parser** allows to parse date and times from natural language to `java.time.LocalDateTime` objects. The engine is written completely in Java, uses normalization, tokenization and pattern matching to parse (completely _AI-less_).

## Capabilities

- Basic relative dates (e.g. _today_, _tomorrow_)
- Relative offsets with units (e.g. _in 30 minutes_, _in 2 days_, _3 weeks ago_)
- Weekday expressions (e.g. _next monday_, _last friday_)
- Absolute date + time (e.g. _17 april_, _21st of march_, _august 3rd at 10:00_)
- Other date + time expressions (e.g. _saturday at 5pm_, _tomorrow at noon_)
- Fuzzy (e.g. _jan_ for January, _thu_ for Thursday)

## Future implementations

- Absolute dates using numbers only (e.g. _10/02/2025_, _2025-02-10 at 7pm_)
- More complex relatives (e.g. _a week from friday_, _the day after next monday_)
- More about local date times and multi-language support

## Installation

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

## Usage

```java
// Create the parser
NaturalDateParser parser = NaturalDateParser.builder().build();
// This will use LocalDateTime.now() as the reference date
LocalDateTime date = parser.parse("Tomorrow at 5pm");
// This will use a different reference date. Result will be relative to that one
LocalDateTime reference = LocalDateTime.of();
LocalDateTime date = parser.parse("Tomorrow at 5pm", reference);
```
