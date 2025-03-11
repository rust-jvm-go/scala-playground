ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.4"

lazy val root = (project in file("."))
  .settings(
    name := "scala-playground",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.7"
  )
