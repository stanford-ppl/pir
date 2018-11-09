package prism
package util

trait ArgLoader extends FileManager {
  val configPath =s"${sys.env("HOME")}${separator}.pirconf"
  def loadArgs(inputArgs:Array[String]) = {
    if (exists(configPath)) {
      var lines = getLines(configPath).toArray
      lines = lines.map { line => line.split("#")(0).trim }
      lines = lines.filterNot { _.isEmpty }
      lines.map(line => s"--" + line) ++ inputArgs
    } else inputArgs
  }
}
