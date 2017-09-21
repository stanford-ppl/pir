
val bldSettings = Defaults.defaultSettings ++ Seq (
	organization := "stanford-ppl",
	publishArtifact in (Compile, packageDoc) := false,

	scalaVersion := "2.11.5",
	scalaSource in Compile <<= baseDirectory(_ / "src"),
	scalaSource in Test <<= baseDirectory(_ / "test"),
  resourceDirectory in Compile <<= baseDirectory(_ / "resources"),
  logBuffered in Test := false,

  libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.5", 
  libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.5",
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2",
  libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.7.0",

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

  parallelExecution in Test := false,
  cancelable in Global := true,
  concurrentRestrictions in Global := (Tags.limitAll(1) :: Nil)
)

lazy val pirc = Project("pirc", 
  file("./pirc"), 
  settings = bldSettings
)

lazy val spade = Project("spade", 
  file("./spade"), 
  settings = bldSettings,
  dependencies = Seq(pirc % "compile->compile")
)

lazy val pir = Project("pir", 
  file("./pir"), 
  settings = bldSettings,
  dependencies = Seq(pirc % "compile->compile", spade % "compile->compile")
)

lazy val apps = Project("apps", 
  file("apps"), 
  settings = bldSettings, 
  dependencies = Seq(pir % "compile->compile;test->test") // Allow ScalaTest of apps accesss ScalaTest of pir
)

// sbt command alias
addCommandAlias("make", ";project pir; compile")
addCommandAlias("makeapps", ";project apps; compile")
addCommandAlias("apps", ";project apps; test")
addCommandAlias("pir", "; project apps; run-main")
addCommandAlias("d", "; project apps; run-main DotProductPar1")
addCommandAlias("wip", s"""; project pir; test-only -- -n "WIP"""")
addCommandAlias("arch", s"""; project pir; test-only -- -n "ARCH"""")
