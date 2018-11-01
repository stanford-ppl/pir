val paradise_version  = "2.1.0"

lazy val Slow = config("slow").extend(Test)

val bldSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "edu.stanford.ppl",
  version := "0.1",
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
  libraryDependencies += "io.spray" %%  "spray-json" % "1.3.4",
  /*libraryDependencies += "com.thoughtworks.xstream" % "xstream" % "1.4.3",*/
  //libraryDependencies += "io.suzaku" %% "boopickle" % "1.3.0",
  retrieveManaged := true,
  //javaOptions += "-Xmx1G", // java heap size ignored unless fork in run := true
  //javaOptions += "-Xms1G", // java stack size
  scalacOptions += "-feature",
  scalacOptions += "-unchecked",
  scalacOptions += "-deprecation",
  scalacOptions += "-language:experimental.macros", // Globally enable macros
  scalacOptions += "-language:implicitConversions", // Globally enable implicit conversions
  scalacOptions += "-language:reflectiveCalls",
  testOptions in Test  += Tests.Argument("-l", "Slow"),
  testOptions in Slow -= Tests.Argument("-l", "Slow"),
  testOptions in Slow += Tests.Argument("-n", "Slow"),

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

lazy val prism = Project("prism", file("prism/"))
  .configs(Slow)
  .settings(
    bldSettings, 
    inConfig(Slow)(Defaults.testTasks)
  )

lazy val spade = Project("spade", 
  file("spade/"), 
  settings = bldSettings,
  dependencies = Seq(prism % "compile->compile;test->test")
).settings(
  scalaSource in Compile := baseDirectory(_ / "src/core").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/param2").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/dot/NetworkDotCodegen.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/node/Factory.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/node/SpadeNode.scala").value
)
/*.settings(*/
  /*unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/param3").value,*/
  /*libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.8.1",*/
  /*libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",*/
  /*libraryDependencies += "com.thesamet.scalapb" %% "scalapb-json4s" % "0.7.0",*/
  /*PB.targets in Compile := Seq(*/
    /*scalapb.gen() -> (sourceManaged in Compile).value*/
  /*),*/
  /*PB.protoSources in Compile := Seq(baseDirectory(_ / "src/param3/proto/").value)*/
 /*)*/

lazy val pir = Project("pir", 
  file("pir/"), 
  settings = bldSettings,
  dependencies = Seq(prism % "compile->compile;test->test", spade % "compile->compile")
).settings(
  scalaSource in Compile := baseDirectory(_ / "src/core").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/util/PIRDebugger.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/pass/").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/logger/PIRIRPrinter.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/dot/PIRIRDotGen.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/dot/ControllerTreeDotGen.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/tungsten/").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/node").value
)

// sbt command alias
addCommandAlias("make", "compile")
addCommandAlias("makepir", ";project pir; compile")

addCommandAlias("makeapps", ";project apps; compile")

addCommandAlias("pir", "; project prism; test; project apps; run-main")
//addCommandAlias("pir", "; project apps; run-main")

addCommandAlias("spade", "; project spade; run-main")

addCommandAlias("wip", s"""; project pir; test-only -- -n "WIP"""")

addCommandAlias("publishAll", "; project prism; publishLocal; project spade; publishLocal; project pir; publishLocal")
