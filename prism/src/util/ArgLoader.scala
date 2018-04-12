package prism.util

trait ArgLoader extends FileManager {
  val configPath =".prismrc"
  def loadArgs(inputArgs:Array[String]) = {
    if (exists(configPath)) {
      inputArgs ++ getLines(configPath).map(line => s"--" + line.trim)
    } else inputArgs
  }
}
