val scala3Version = "3.3.1"
val AkkaVersion = "2.9.0"

lazy val root = project
    .in(file("."))
    .settings(
      name := "ScalaMusic",
      version := "0.1.0-SNAPSHOT",
      scalaVersion := scala3Version,
      resolvers += "Akka library repository".at("https://repo.akka.io/maven"),
      libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.17",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % "test",
      libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
      libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
    )
