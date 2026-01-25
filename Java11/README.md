# Java 11 New Features

The most important new features with Java 11 are:
- Using `(var p) -> lambda` to declare the type of `p`
  - **NOTE**: this is not even necessary because one is allowed to omit the type since Java 8+
- New String methods
  - isBlank() - captures *whitespace characters*
  - lines() - returns a stream of lines
  - stripIndent() - strips whitespace from beginnings of lines in a multi-line string
- _HttpClient_ (replaces legacy _HttpUrlConnection_)
  - ```
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("https://postman-echo.com/get"))
        .headers("key1", "value1", "key2", "value2")
        .POST(HttpRequest.BodyPublishers.ofString("Sample request body"))
        .build();
    HttpResponse<String> response = HttpClient.newHttpClient()
        .send(request, HttpResponse.BodyHandlers.ofString());
    HttpHeaders responseHeaders = response.headers();
    ``` 
- Nest-based access control
  - Nested classes can access each other's private members
- EpsilonGC
  - New garbage collector
- Launch single-file source code programs without compiling
  - `java HelloWorld.java`