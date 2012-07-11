Maxmind GeoIP client build
==========================

Maxmind(http://www.maxmind.com) provide an LGPL Java client(http://www.maxmind.com/app/java) for their GeoIP data, but as far as I'm aware, there's no Maven or Ivy artifact available.  This just provides an SBT script to generate one.  To publish a version, edit the `project/Build.scala` to reflect the desired version, then run:

`sbt clean publish`
