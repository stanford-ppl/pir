package pirc.util

import pirc._

import scala.collection.mutable.ListBuffer
import java.io.File

trait FileManager { 

  val logExtensions = List(".log", ".dot", ".svg", ".vcd", ".txt", ".csv", ".pdf")

  def isLog(file:File) = logExtensions.exists{ ext => file.getName().endsWith(ext) }

  def clearLogs(outDir:String) = {
    val logs = getListOfFiles(outDir).filter(isLog)
    logs.foreach { _.delete() }
  }
  
  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
    } else {
        List[File]()
    }
  }

}
