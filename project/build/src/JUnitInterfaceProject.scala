import sbt._
import java.io.File
import bjs.project.ResolverPlugin

class JUnitInterfaceProject(info: ProjectInfo) extends DefaultProject(info)
{
  val junit = "junit" % "junit" % "4.8.1"
  val testInterface = "org.scala-tools.testing" % "test-interface" % "0.5"
  override def javaCompileOptions = JavaCompileOption("-target") :: JavaCompileOption("1.5") :: Nil

  /*********** Publishing ***********/
  override def deliverScalaDependencies = Nil
  override def disableCrossPaths = true
  override def managedStyle = ManagedStyle.Maven



  /*********** Extra meta-data for the POM ***********/
  override def pomExtra =
      (<name>JUnitInterface</name>
      <url>http://github.com/szeiger/junit-interface/</url>
      <inceptionYear>2009</inceptionYear>
      <description>An implementation of sbt's test interface for JUnit 4</description>
      <licenses>
        <license>
          <name>Two-clause BSD-style license</name>
          <url>http://github.com/szeiger/junit-interface/blob/master/LICENSE.txt</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <developers>
        <developer>
          <id>szeiger</id>
          <name>Stefan Zeiger</name>
          <timezone>+1</timezone>
          <email>szeiger [at] novocode.com</email>
        </developer>
        <developer>
          <id>bryanjswift</id>
          <name>Bryan J Swift</name>
          <timezone>-5</timezone>
          <email>bryan [at] bryanjswift.com</email>
        </developer>
      </developers>
      <scm>
        <url>http://github.com/szeiger/junit-interface/</url>
      </scm>)
}
