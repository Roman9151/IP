ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"
val circeVersion = "0.14.3"
val http4sVersion = "0.23.18"
val http4sBlaze = "0.23.13"
libraryDependencies += "com.comcast" %% "ip4s-core" % "3.2.0"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-ember-client" % http4sVersion
)
libraryDependencies += "dev.zio" %% "zio" % "2.0.15"
libraryDependencies += "dev.zio" %% "zio-http" % "0.0.5"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test"
lazy val root = (project in file("."))
  .settings(
    name := "task"
  )
