import sbt._
import Keys._
import FileFilter._
import java.net.URL

object GeoIPBuild extends Build {
  lazy val geoip = Project(id = "geoip", base = file(".")).settings(

    name := "geoip",
    organization := "com.maxmind",
    version :=  "1.2.9",
    crossPaths := false,

    sourceGenerators in Compile <+= (version, sourceManaged in Compile) map { (version, out) =>
      val zip = new URL("http://www.maxmind.com/download/geoip/api/java/GeoIPJava-%s.zip" format (version))
      IO.unzipURL(zip, out)
      (out / "GeoIPJava-%s".format(version) / "source" ** ("*.java")).get
    },

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
}
