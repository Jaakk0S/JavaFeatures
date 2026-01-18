# Java 9 Modularity

Java 9 introduced the modularization of the JRE, and the capability to declare Java code as modules. These modules can be controlled with visibility rules.

The usability of this, in building new applications, is limited, because applications shouldn't be designed as monoliths, and modularity can be achieved using a build/repository system such as Maven/Nexus.
One of the few uses of these modules would be in *monolith refactoring*, taking a codebase and dividing it into modules that are then further processed into separate projects. 

## Stripping down the JRE

Java 9 modules provide one feature that is very usable in a microservice world. This is the ability to create stripped JREs by including only the Java modules that your application depends on.

I have broken the following into steps.

### Step 1: Creating the Fat JAR

Let's create a stripped JRE for [IceCreamParlor-MenuService](https://github.com/Jaakk0S/IceCreamParlor-MenuService).

* Clone the repository: `git clone git@github.com:Jaakk0S/IceCreamParlor-MenuService.git`
* `cd IceCreamParlor-MenuService`
* Compile with Maven: `./mvnw clean package`

Now there's a "fat jar" in `target/`, filled with all Spring Boot dependencies.

### Step 2: Finding Transitive Dependencies

Our JRE should contain all the dependencies needed by your application code and all its dependencies. The tricky part now is to compile module dependency information from all 
the transitive dependencies.

We can get a classpath of the included JARs with `./mvnw dependency:build-classpath|grep -A1 "Dependencies classpath"|tail -n 1|sed -e 's/;/\n/g'`

This extracts the project full classpath from Maven output and replaces semicolons with newlines to produce a jar-per-line list.

Let's feed those lines to `jdeps` and print a module dependency summary, prune out "Error" and "not found" lines, then output this to `deps.list`:

```
for jar in `./mvnw dependency:build-classpath|grep -A1 "Dependencies classpath"|tail -n 1|sed -e 's/;/\n/g'`; do
    jdeps -R --ignore-missing-deps --multi-release 9 -s $jar|grep -v Error|grep -v "not found" >>fulldeps.list;
done
```

There's a list of dependencies now in the file `fulldeps.list`, that looks like this:

```
...
spring-boot-3.5.7.jar -> java.net.http
spring-boot-3.5.7.jar -> java.sql
spring-boot-3.5.7.jar -> java.xml
spring-boot-autoconfigure-3.5.7.jar -> java.base
spring-boot-autoconfigure-3.5.7.jar -> java.compiler
spring-boot-autoconfigure-3.5.7.jar -> java.management
...
```

### Step 3: Filtering

Let's take our list, weed out everything except the module names and remove duplicates, and parse this into a comma-separated list:
```cat fulldeps.list|cut -d ' ' -f 3|sort|uniq -d|tr '\n' , >deps.list```:

In `deps.list` we have:

```
java.base,java.compiler,java.desktop,java.instrument,java.logging,java.management,java.naming,java.net.http,java.scripting,java.sql,java.transaction.xa,java.xml,jdk.unsupported,
```

### Step 4: Customizing

Now, the method presented here is not exact art, and not a reliable solution to fully parse all dependencies. First of all, `jdeps` finds modules from reading the static dependencies, and
any dynamically added classes (such as using reflection) would not be found. Secondly, we are not traversing
down the full transitive dependency chain but just `jdeps`ing all classes in the project classpath,
not their dependencies. We might be missing some classes, and we need to add their modules.

The idea applied here is that we get the majority of classes, and then if we get runtime errors
for missing classes, we add those by hand.

I found through testing the JRE that in the case of the Spring Boot app we are working with here,
there is one library we need to add by hand: `java.security.jgss`.

So, at this point, edit `deps.list` and add this library in the end of the list.

### Step 5: Creating a new JRE

Let's use the JLink command to generate a JRE that contains just the libraries we need.

**NOTE:** JLink will use its parent JDK as a source. In this example, I am using JDK 25.

```
jlink --add-modules `cat deps.list` --output jdk-25-stripped --strip-debug --no-man-pages --no-header-files --compress=zip-1
```

### Step 6: Comparing the Size

Let's check the size of this JRE:

```
$ du -m -s jdk-25-stripped/
51      jdk-25-stripped/
```

Now, for comparison, I pulled a popular JRE Docker image: `eclipse-temurin:25-jre-alpine-3.23`, `bash`ed into it, and
checked the size of the JRE:

```
root@c86738448979:/# du -s -m /opt/java/openjdk
251     /opt/java/openjdk
```

So, in this scenario we are getting a custom JRE size from 251 MB to 51 MB.

For reference, with the JDK I am using, creating a JRE with just `java.base` will be around 30 MB.

### Step 7: Test Running

Test run the JRE by running the app with it:

```
 jdk-25-stripped/bin/java.exe -jar target/IceCreamParlor-MenuService-1.0.0-SNAPSHOT.jar
```