An implementation of sbt's test interface <http://github.com/harrah/test-interface> for JUnit 4.

Unlike Scala testing frameworks like ScalaTest (which can also run JUnit test cases), both JUnit
and this adapter are pure Java, so you can run JUnit tests with any Scala version supported by
sbt without having to build a binary-compatible test framework first.

See LICENSE.txt for licensing conditions (BSD-style).

To use with xsbt 0.7.x, add the following dependency to your project:

    val bryanjswift = "Bryan J Swift Repository" at "http://repos.bryanjswift.com/maven2/"
    val junitInterface = "com.novocode" % "junit-interface" % "0.4.0" % test

Or you can get it directly from Github:

    val junitInterface = "com.novocode" %% "junit-interface" % "0.4.0" from "http://github.com/downloads/bryanjswift/junit-interface/junit-interface-0.4.0.jar"

== Publishing ==

In order to publish the jar somewhere you can either update the project file to override the
publishTo property or add a resolver file (.resolver by default, configurable with the property
'resolver.path'). The resolver file is of the form:

    resolver.name=Example Resolver
    resolver.host=example.com
    resolver.port=22
    resolver.path=/path/to/publish/into/
    resolver.type=sftp|ssh
