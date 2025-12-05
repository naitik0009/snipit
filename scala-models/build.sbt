name := "scala-utils"
organization := "genSeven"
version := "1.0.0"
scalaVersion := "2.13.12"

val pekkoHttpVersion = "1.0.0"

libraryDependencies ++= Seq(
    "org.apache.pekko" %% "pekko-http-spray-json" % pekkoHttpVersion,
    "genSeven" %% "scala-utils" % "1.0.0" //depends on project A
)

