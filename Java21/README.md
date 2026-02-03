# Java 21 New Features

The most important new features with Java 21 are:
- Record patterns
  - `instanceof` can be used to check records
- Pattern matching updated:
  - `switch` statement supports `instanceof` a.k.a pattern matching
- Scoped values:
    - Replacement of ThreadLocal:
      - Comes with automatic `remove()`
      - No need to pass ThreadLocal context in method parameters or to use global variables
  - `ScopedValue.where` returns a _Carrier_ object
  - Carrier has methods
    - `get`: returns the value
    - `where`: for binding values in a _chainable_ manner
    - `call`: calls a `ScopedValue.CallableOp` value-returning operation
    - `run`: runs a Runnable (doesn't return a value)
  - For ThreadLocal problems, c.f. https://dev.to/thellu/threadlocal-in-java-why-you-must-call-remove-in-thread-pools-avoid-oom-49l4
  - Sample code: [ScopedValues.java](ScopedValues.java)
- Sequenced collections
  - Added 3 new _interfaces_ to collection hierarchy, as parents of some of the existing classes