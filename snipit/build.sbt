// --- 1. VERSIONS (MUST BE AT THE TOP) ---
val caskVersion = "0.9.1"
val circeVersion = "0.14.6"
val slickVersion = "3.4.1"

// --- 2. SETTINGS ---
val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.13.12",
  libraryDependencies += "genSeven" %% "scala-models" % "1.0.0"
)

// --- 3. MODULES ---
lazy val root = (project in file("."))
  .settings(name := "snipit-root")
  .aggregate(core, backend)

lazy val core = (project in file("core"))
  .settings(commonSettings, name := "snipit-core")

lazy val backend = (project in file("backend"))
  .dependsOn(core)
  .settings(
    commonSettings,
    name := "snipit-backend",
    libraryDependencies ++= Seq(
      // Cask
      "com.lihaoyi" %% "cask" % caskVersion,

      // Circe
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,

      // DB
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
      "org.postgresql" % "postgresql" % "42.5.4",

      // Utils
      "org.mindrot" % "jbcrypt" % "0.4",
      "ch.qos.logback" % "logback-classic" % "1.2.11"
    )
  )