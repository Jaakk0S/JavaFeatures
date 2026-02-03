# Java 22 New Features

The most important new features with Java 22 are:
- Unnamed variables (_)
  - Use `_` to replace unreadable, bad variable names that are not meant to be passed
- Implicitly declared classes and instance main methods
  - Simply writing `void main() {}` in an empty file will translate to a Java standard template
  - What is compiled is an unnamed class in an unnamed package in an unnamed module
- Foreign Function and Memory API
  - It's possible to call native functions or access memory outside the JVM
  - See [ForeignFunctions.java](ForeignFunctions.java)
- Multi-file source programs
  - Running a Java file directly (without compiling first with `javac`) had a restriction that it couldn't reference other source files
  - Now it can reference other Java source files
