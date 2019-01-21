val paradise_version  = "2.1.0"
val scala_version = "2.12.7"

lazy val Slow = config("slow").extend(Test)

val bldSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "edu.stanford.ppl",
  version := "0.1",
  publishArtifact in (Compile, packageDoc) := false,
  scalaVersion := scala_version,
  scalaSource in Compile := baseDirectory(_ / "src").value,
  scalaSource in Test := baseDirectory(_ / "test").value,
  resourceDirectory in Compile := baseDirectory(_ / "resources").value,
  logBuffered in Test := false,
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scala_version,
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
    "-doc-title", name.value
  ),
  isSnapshot := true,
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

lazy val spade = Project("spade", file("spade/"))
.dependsOn(prism % "compile->compile;test->test")
.settings(bldSettings)
.settings(
  scalaSource in Compile := baseDirectory(_ / "src/core").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/param2").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/dot/NetworkDotGen.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/ParamIRPrinter.scala").value,
  unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/node").value
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

lazy val pir = Project("pir", file("pir/"))
  .dependsOn(prism % "compile->compile;test->test")
  .dependsOn(spade % "compile->compile")
  .settings(bldSettings).settings(
    scalaSource in Compile := baseDirectory(_ / "src/core").value,
    unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/util/").value,
    unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/pass/").value,
    unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/codegen/").value,
    unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/mapper/").value,
    unmanagedSourceDirectories in Compile += baseDirectory(_ / "src/node").value
  )

// sbt command alias
addCommandAlias("make", "compile")
addCommandAlias("pir", "; project prism; test; project apps; run-main")

addCommandAlias("spade", "; project spade; runMain spade.Plasticine --out=out/plasticine")

addCommandAlias("publishAll", "; project prism; publishLocal; project spade; publishLocal; project pir; publishLocal")
addCommandAlias("psh", "; project pir; runMain pir.PIRShell")
