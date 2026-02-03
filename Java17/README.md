# Java 17 New Features

The most important new features with Java 17 are:
- Enhanced random number generators:
    - ```
        RandomGeneratorFactory.of(String algorithm)
            .create()
            .ints(streamSize, 0, 100);
        ```
- `-illegal-access` discontinued (allowed access to Java internal APIs)
