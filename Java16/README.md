# Java 16 New Features

The most important new features with Java 16 are:
- Day period support (`DateTimeFormatter.ofPattern("3 B")` -> "3 _in the afternoon_")
- `Stream.toList()`
- Records can now be class members of inner classes
- Pattern matching type checks:
    - `instanceof` can add a *binding variable*, then the variable can be immediately referenced
- Sealed classes updates:
  - Not allowed to make _anonymous_ or _local_ (defined inside a code block) classes out of sealed classes