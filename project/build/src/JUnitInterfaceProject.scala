import sbt._
import scala.collection.Set
import scala.xml._
import java.io.{File, FileOutputStream}
import java.nio.channels.Channels
import bjs.project.ResolverPlugin

class JUnitInterfaceProject(info: ProjectInfo) extends DefaultProject(info) with ResolverPlugin
{
  val junit = "junit" % "junit" % "4.7"
  val testInterface = "org.scala-tools.testing" % "test-interface" % "0.2"
  override def javaCompileOptions = JavaCompileOption("-target") :: JavaCompileOption("1.5") :: Nil

  /*********** Publishing ***********/
  override def deliverScalaDependencies = Nil
  override def managedStyle = ManagedStyle.Maven

  /*********** Extra meta-data for the POM ***********/
  override def makePomAction = enrichPom dependsOn superMakePom
  lazy val superMakePom = super.makePomAction
  lazy val enrichPom = task {
    val in = XML.loadFile(pomPath.asFile)
    val out = <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      {in \ "modelVersion"}
      {in \ "groupId"}
      {in \ "artifactId"}
      {in \ "packaging"}
      <name>JUnitInterface</name>
      {in \ "version"}
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
      </scm>
      {in \ "dependencies"}
    </project>
    val fos = new FileOutputStream(pomPath.asFile)
    try {
      val w = Channels.newWriter(fos.getChannel(), "UTF-8")
      try {
        w.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n")
        w.write(new PrettyPrinter(java.lang.Integer.MAX_VALUE, 2).format(out, TopScope))
      } finally { w.close() }
    } finally { fos.close() }
    None
  }
}
