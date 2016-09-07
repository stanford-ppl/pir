import sbt._
import Keys._
import java.io.File
import CommandExample._

object PIRBuild extends Build {
	if (System.getProperty("showSuppressedErrors") == null) System.setProperty("showSuppressedErrors", "false")

	val bldSettings = Defaults.defaultSettings ++ Seq (
 		organization := "stanford-ppl",
		publishArtifact in (Compile, packageDoc) := false,

		scalaVersion := "2.11.5",
		scalaSource in Compile <<= baseDirectory(_ / "src"),
		scalaSource in Test <<= baseDirectory(_ / "test"),
    logBuffered in Test := false,

    libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.5", 
    libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.5",
    libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2",

    retrieveManaged := true,
    scalacOptions += "-Yno-generic-signatures",
    scalacOptions += "-feature",
    scalacOptions += "-deprecation",

    parallelExecution in Test := false,
    concurrentRestrictions in Global := (Tags.limitAll(1) :: Nil)

  )

  val cmds = Seq(hello, helloAll, failIfTrue, changeColor, printState)
	lazy val pir = Project("pir", file("."), settings = bldSettings) settings(commands ++= cmds)

	lazy val apps = Project("apps", file("apps"), settings = bldSettings) dependsOn pir settings(commands ++= cmds)

}

