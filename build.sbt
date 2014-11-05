organization := "specs2-demos"

name := "default"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.0"

resolvers += "specs2-resolver-0" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.4.2" % "test",
  "org.jsoup" % "jsoup" % "1.7.3"
)


