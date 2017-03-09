//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.0.0")
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

resolvers ++= Seq(
    "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
    )

addSbtPlugin("net.ceedubs" %% "sbt-ctags" % "0.2.0")
