# Java 15 New Features

The most important new features with Java 15 are:
- Records
  - Immutable
  - public constructor with parameter for each field
  - public getters for fields
  - `equals`, `hashCode` and `toString`
  - constructors can be customized with a special syntax, and new constructors can be added
  - static variables and methods can be added
- Sealed classes
  - Motivation: limit superclass extension
  - `sealed interface` + `permits`: permit only specific classes to implement  
  - `sealed class` + `permits`: permit only specific classes to extend
  - Constraints for permitted subclasses:
    - Must belong to the same module as the sealed class
    - Must *extend* the sealed class
    - Must be either `final`, `sealed` or explicitly `non-sealed`
- Hidden classes
  - Runtime only
  - Not found in classpath or using reflection
  - Meant for framework code, *for brief use*
  - Will be garbage-collected
