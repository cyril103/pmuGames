ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "pmuGames",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= List("org.scalatest" %% "scalatest" % "3.2.17" % "test",
      "org.scala-js" %%% "scalajs-dom" % "2.4.0")
  )
