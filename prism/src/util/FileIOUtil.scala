package prism
package util

import java.io.{Console => _, _}
import java.nio.file.{Files, Paths}
import scala.io._

trait FileIOUtil { 

  val separator = File.separator

  def logExtensions = List(".log", ".dot", ".svg", ".txt", ".csv", ".pdf")

  def isLog(file:File):Boolean = isLog(file.getName())
  def isLog(fileName:String):Boolean = {
    fileName match {
      case fileName if fileName == "compiler.log" => false
      case fileName => logExtensions.exists { ext => fileName.endsWith(ext) }
    }
  }

  def clearLogs(outDir:String) = {
    val logs = getListOfFiles(outDir).filter(isLog)
    logs.foreach(deleteFile)
  }

  def clearDir(outDir:String, filter:String => Boolean = { _ => true}) = {
    Option(new File(outDir).listFiles).map(_.toList).getOrElse(Nil)
      .foreach{ file => if (filter(file.getName)) deleteFile(file) }
  }
  
  def getListOfFiles(dir: String):List[File] = {
    getListOfFiles(new File(dir))
  }

  def getListOfFiles(dir: File):List[File] = {
    if (dir.exists && dir.isDirectory) {
        dir.listFiles.filter(_.isFile).toList
    } else {
        List[File]()
    }
  }

  def deleteFile(file: File): Unit = {
    if (file.isDirectory) {
      Option(file.listFiles).map(_.toList).getOrElse(Nil).foreach(deleteFile(_))
      file.delete
    } else file.delete
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

  def copyFiles(srcDir: String, dstDir:String): Unit = {
    copyFiles(new File(srcDir), new File(dstDir))
  }

  def copyFiles(srcDirFile: File, dstDirFile:File): Unit = {
    for (f <- srcDirFile.listFiles) {
      if (f.isDirectory) {
        val dstDir = new File(s"${dstDirFile.getCanonicalPath()}${separator}${f.getName}")
        dstDir.mkdirs()
        copyFiles(f, dstDir)
      } else {
        val dst = s"${dstDirFile.getCanonicalPath()}${separator}${f.getName}"
        val src = f.getCanonicalPath()
        copyFile(src, dst)
      }
    }
  }

  def mkdir(dirName:String):Unit = {
    val dir = new File(dirName)
    if (!dir.exists()) {
      Files.createDirectories(dir.toPath())
      info(cstr(Console.YELLOW, s"created output directory: ${cstr(Console.CYAN,dirName)}"))
    }
  }

  def buildPath(dirs:String*) = dirs.mkString(separator)

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

  def getRelativePath(path:String, relativeTo:String) = {
    val p1 = Paths.get(path)
    val p2 = Paths.get(relativeTo)
    p2.relativize(p1).toString
  }

}
