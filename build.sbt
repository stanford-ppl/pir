  // scalaVersion := "2.10.2"
  scalaVersion := "2.11.5"
  // resolvers ++= Seq(
  //  "scct-github-repository" at "http://mtkopone.github.com/scct/maven-repo"
  // )

  libraryDependencies += "org.encog" % "encog-core" % "3.3.0"
  //http://search.maven.org/remotecontent?filepath=org/encog/encog-core/3.3.0/encog-core-3.3.0.jar

  //  "edu.berkeley.cs" %% "chisel" % "latest.release"
  //
  //addCommandAlias("make", ";project pir;compile")
  //
  addCommandAlias("make", ";project pir; compile")
  addCommandAlias("makeapps", ";project apps; compile")
  addCommandAlias("pir", "; project apps; run-main")
  addCommandAlias("d", "; project apps; run-main DotProductPar1")
  addCommandAlias("wip", s"""test-only -- -n "WIP"""")
  addCommandAlias("arch", s"""test-only -- -n "ARCH"""")
  cancelable in Global := true
