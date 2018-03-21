
val bldSettings = Defaults.defaultSettings ++ Seq(
	organization := "stanford-ppl",
	publishArtifact in (Compile, packageDoc) := false,
	scalaVersion := "2.11.5",
	scalaSource in Compile <<= baseDirectory(_ / "src"),
	scalaSource in Test <<= baseDirectory(_ / "test"),
  resourceDirectory in Compile <<= baseDirectory(_ / "resources"),
  logBuffered in Test := false,
  libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.5", 
  libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.5",
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2" % "test",
  libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.7.0",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.7",
  retrieveManaged := true,
  javaOptions in (Test) += "-Xdebug",
  javaOptions in (Test) += "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005",
  javaOptions += "-Xmx3G",
  //scalacOptions += "-Yno-generic-signatures",
  scalacOptions += "-feature",
  scalacOptions += "-unchecked",
  scalacOptions += "-deprecation",
  autoAPIMappings := true,
  scalacOptions in (Compile, doc) ++= Seq(
    "-doc-root-content", 
    baseDirectory.value+"/root-doc.txt",
    "-diagrams",
    "-diagrams-debug",
    //"-diagrams-dot-timeout", "20", "-diagrams-debug",
    "-doc-title", name.value
  ),
  parallelExecution in Test := true,
  cancelable in Global := true,
  concurrentRestrictions in Global := (Tags.limitAll(1) :: Nil)
)

lazy val macros = Project("macros", 
  file("macros/"), 
  settings = bldSettings
)

lazy val prism = Project("prism", 
  file("prism/"), 
  settings = bldSettings,
  dependencies = Seq(macros % "compile->compile;test->test")
)

lazy val spade = Project("spade", 
  file("spade/"), 
  settings = bldSettings,
  dependencies = Seq(prism % "compile->compile;test->test")
)

lazy val arch:Project = Project("arch", 
  file("spade/arch"), 
  settings = bldSettings,
  dependencies = Seq(spade % "compile->compile;test->test") // Allow ScalaTest of apps accesss ScalaTest of pir
)

lazy val pir = Project("pir", 
  file("pir/"), 
  settings = bldSettings,
  dependencies = Seq(prism % "compile->compile;test->test", spade % "compile->compile", arch % "compile->compile")
)

lazy val apps = Project("apps", 
  file("pir/apps"), 
  settings = bldSettings, 
 // Allow ScalaTest of apps accesss ScalaTest of pir
  dependencies = Seq(pir % "compile->compile;test->test", arch % "compile->compile", prism % "test->test")
)

// sbt command alias
addCommandAlias("make", ";project pir; compile")

addCommandAlias("makeapps", ";project apps; compile")

addCommandAlias("apps", ";project apps; test")

addCommandAlias("pir", "; project prism; test; project apps; run-main")
//addCommandAlias("pir", "; project apps; run-main")

addCommandAlias("spade", "; project arch; run-main")

addCommandAlias("wip", s"""; project pir; test-only -- -n "WIP"""")

addCommandAlias("arch", s"""; project arch; test-only -- -n "ARCH"""")

addCommandAlias("prism-test", "; project prism; test")
addCommandAlias("macros-test", "; project macros; test")
addCommandAlias("spade-test", "; project spade; test; project arch; test")
