package prism
package util

trait ArgLoader extends FileManager {
  val configPath =".prismrc"
  def loadArgs(inputArgs:Array[String]) = {
    if (exists(configPath)) {
      var lines = getLines(configPath).map(_.trim)
      lines = lines.filterNot { line => line.startsWith("#") || line.isEmpty }
      lines.map(line => s"--" + line) ++ inputArgs
    } else inputArgs
  }
}
