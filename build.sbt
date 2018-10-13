val paradise_version  = "2.1.0"

val bldSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "stanford-ppl",
  publishArtifact in (Compile, packageDoc) := false,
  scalaVersion := "2.12.5",
  scalaSource in Compile := baseDirectory(_ / "src").value,
  scalaSource in Test := baseDirectory(_ / "test").value,
  resourceDirectory in Compile := baseDirectory(_ / "resources").value,
  logBuffered in Test := false,
  libraryDependencies += "org.scala-lang" % "scala-library" % "2.12.5", 
  libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.12.5",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.5",
  libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test",
  libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.7.0",
  //libraryDependencies += "io.suzaku" %% "boopickle" % "1.3.0",
  retrieveManaged := true,
  //javaOptions += "-Xmx1G", // java heap size ignored unless fork in run := true
  //javaOptions += "-Xms1G", // java stack size
  scalacOptions += "-feature",
  scalacOptions += "-unchecked",
  scalacOptions += "-deprecation",
  scalacOptions += "-language:experimental.macros", // Globally enable macros
  scalacOptions += "-language:implicitConversions", // Globally enable implicit conversions
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
  concurrentRestrictions in Global := (Tags.limitAll(1) :: Nil),
  addCompilerPlugin("org.scalamacros" % "paradise" % paradise_version cross CrossVersion.full)
)

lazy val prism = Project("prism", 
  file("prism/"), 
  settings = bldSettings,
  dependencies = Seq()
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
addCommandAlias("make", "compile")
addCommandAlias("makepir", ";project pir; compile")

addCommandAlias("makeapps", ";project apps; compile")

addCommandAlias("apps", ";project apps; test")

addCommandAlias("pir", "; project prism; test; project apps; run-main")
//addCommandAlias("pir", "; project apps; run-main")

addCommandAlias("spade", "; project arch; run-main")

addCommandAlias("wip", s"""; project pir; test-only -- -n "WIP"""")

addCommandAlias("arch", s"""; project arch; test-only -- -n "ARCH"""")

addCommandAlias("prism-test", "; project prism; test")
addCommandAlias("spade-test", "; project spade; test; project arch; test")
