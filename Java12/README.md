# Java 12 New Features

The most important new features with Java 12 are:
- Shenandoah GC
- String class new methods
  - `.indent()` - Indents each line in a multi-line String
  - `.transform(function)` - Uses a supplied Operator function to transform the string
- `File::mismatch` method
  - Compares two files for the first position of their mismatched content
- Teeing collector
  - Duplicates the stream to two separate collectors. Their output is then passed to a merge function.
  - See [TeeingCollector.java](TeeingCollector.java)
- Compact number formatting
  - Allows formatting of numbers into strings based on _locale_ and in either long or short form
  - For example: 2592 in "US" and SHORT format with 2 digits:
    - _"2.59K"_
  - For example: 2592 in "US" and LONG format with 2 digits:
    - _"2.59 thousand"_
