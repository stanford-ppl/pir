import sbt._
import Keys._
import java.io.File
import CommandExample._

object PIRBuild extends Build {
	if (System.getProperty("showSuppressedErrors") == null) System.setProperty("showSuppressedErrors", "false")

}
