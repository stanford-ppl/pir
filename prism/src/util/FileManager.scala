package prism
package util

import java.io.{Console => _, _}
import scala.io._

trait FileManager { 

  val separator = File.separator

  def logExtensions = List(".dot", ".svg", ".txt", ".csv", ".pdf")

  def isLog(file:File):Boolean = isLog(file.getName())
  def isLog(fileName:String):Boolean = {
    fileName match {
      case fileName if fileName.endsWith(".log") & fileName.contains("-") =>
        fileName.split("-")(0).forall { _.isDigit }
      case fileName => logExtensions.exists { ext => fileName.endsWith(ext) }
    }
  }

  def clearLogs(outDir:String) = {
    val logs = getListOfFiles(outDir).filter(isLog)
    logs.foreach(deleteFile)
  }
  
  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
    } else {
        List[File]()
    }
  }

  def deleteFile(file: File): Unit = {
    if (file.isDirectory) {
      Option(file.listFiles).map(_.toList).getOrElse(Nil).foreach(deleteFile(_))
    }
    file.delete
  }

  def deleteFile(path: String): Unit = {
    deleteFile(new File(path))
  }

  def copyFile(src: String, dst: String):Unit = {
    if (src == dst) return
    val srcFile = new File(src)
    val dstFile = new File(dst)
    new FileOutputStream(dstFile)
            .getChannel()
            .transferFrom (
              new FileInputStream(srcFile).getChannel(), 0, Long.MaxValue
            )
  }

  def copyDir(srcDirFile: File, dstDirFile: File): Unit = {
    for (f <- srcDirFile.listFiles) {
      if (f.isDirectory) {
        val dstDir = new File(s"${dstDirFile.getAbsolutePath}/${f.getName}")
        dstDir.mkdirs()
        copyDir(f, dstDir)
      } else {
        val dst = s"${dstDirFile.getAbsolutePath()}/${f.getName}"
        val src = f.getAbsolutePath()
        copyFile(src, dst)
      }
    }
  }

  def copyDir(srcDir: String, dstDir: String): Unit = {
    val srcDirFile = new File(srcDir)
    val srcDirName = srcDir.split(separator).last
    val dstDirFile = new File(s"$dstDir$separator$srcDirName")
    dstDirFile.mkdirs()

    for (f <- srcDirFile.listFiles) {
      if (f.isDirectory) {
        val dstDir = new File(s"${dstDirFile.getAbsolutePath}${separator}${f.getName}")
        dstDir.mkdirs()
        copyDir(f, dstDir)
      } else {
        val dst = s"${dstDirFile.getAbsolutePath()}${separator}${f.getName}"
        val src = f.getAbsolutePath()
        copyFile(src, dst)
      }
    }
  }

  def mkdir(dirName:String):String = {
    val dir = new File(dirName)
    if (!dir.exists()) {
      info(cstr(Console.YELLOW, s"creating output directory: ${cstr(Console.CYAN,dirName)}"))
      dir.mkdirs();
    }
    dirName
  }

  def buildPath(dirName:String, fileName:String) = s"${dirName}${separator}${fileName}"

  def dirName(fullPath:String) = fullPath.split(separator).dropRight(1).mkString(separator)

  def baseName(fullPath:String) = fullPath.split(separator).last

  def exists(path:String) = {
    new File(path).exists
  }

  def getLines(path:String):Iterator[String] = Source.fromFile(path).getLines

  def getCSVRows(path:String):Iterator[Map[String,String]] = {
    val iter = getLines(path)
    val headers = iter.next.split(",").map(_.trim)
    iter.map { line =>
      val cells = line.split(",").map(_.trim)
      headers.zip(cells).toMap
    }
  }

}
