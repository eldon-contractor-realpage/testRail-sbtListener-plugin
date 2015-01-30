name := "sbt-com.testRailsbtListener.testRail-test-reporting-plugin"

organization := "com.testRailsbtListener"

sbtPlugin := true

releaseSettings

publishTo := {
  val isSnapshot = version.value.contains("-SNAPSHOT")
  val scalasbt = "http://scalasbt.artifactoryonline.com/scalasbt/"
  val (name, url) = if (isSnapshot)
    ("typesafe-sbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
  else
    ("typesafe-sbt-plugin-releases", scalasbt+"sbt-plugin-releases")
  Some(Resolver.url(name, new URL(url))(Resolver.ivyStylePatterns))
}

//libraryDependencies += "com.googlecode.json-simple" % "json-simple" % "1.1.1"

publishMavenStyle := false


