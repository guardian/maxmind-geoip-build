import sbt._
import Keys._

object GeoIPBuild extends Build {

	override lazy val settings = super.settings ++ Seq(
		name := "geoip",

		organization := "com.maxmind",

		version :=  "1.28",

		javaSource in Compile <<= baseDirectory(_ / "source"),

		publishTo in ThisBuild <<= (version) { version: String =>
				val publishType = if (version.endsWith("SNAPSHOT")) "snapshots" else "releases"
				Some(
						Resolver.file(
								"guardian github " + publishType,
								file(System.getProperty("user.home") + "/guardian.github.com/maven/repo-" + publishType)
						)
				)
		}
	)

	lazy val geoip = Project(id = "geoip", base = file("."), settings = Project.defaultSettings ++ settings)
}
