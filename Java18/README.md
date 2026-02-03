# Java 18 New Features

The most important new features with Java 18 are:
- Make UTF-8 the default charset, regardless of the operating system, locale, and language settings
- Simple Web Server
  - `jwebserver -b 127.0.0.100 -p 4444 -d /tmp -o verbose`
  - Only supports HTTP/1.1
  - Java API:
    ```
      HttpServer server = SimpleFileServer.createFileServer(
          new InetSocketAddress(8080), Path.of("\tmp"), OutputLevel.INFO);
      server.start();    
    ```
- Internet-Address Resolution SPI:
  ```
  InetAddress[] addresses = InetAddress.getAllByName("www.happycoders.eu");
  System.out.println("addresses = " + Arrays.toString(addresses));  
  ```