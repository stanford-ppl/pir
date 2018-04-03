package prism.util

import prism._

import java.io._

trait FileManager { 

  type File = java.io.File
  val separator = File.separator

  val logExtensions = List(".log", ".dot", ".svg", ".vcd", ".txt", ".csv", ".pdf")

  def isLog(file:File) = logExtensions.exists{ ext => file.getName().endsWith(ext) }

  def clearLogs(outDir:String) = {
    val logs = getListOfFiles(outDir).filter(isLog)
    logs.foreach(deleteFiles)
  }
  
  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
    } else {
        List[File]()
    }
  }

  def deleteFiles(file: File): Unit = {
    if (file.isDirectory) {
      Option(file.listFiles).map(_.toList).getOrElse(Nil).foreach(deleteFiles(_))
    }
    file.delete
  }

  def copyFile(src: String, dst: String) = {
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
      println(s"[pir] creating output directory: $dirName");
      dir.mkdirs();
    }
    dirName
  }

  def buildPath(dirName:String, fileName:String) = s"${dirName}${separator}${fileName}"

  def dirName(fullPath:String) = fullPath.split(separator).dropRight(1).mkString(separator)

  def baseName(fullPath:String) = fullPath.split(separator).last

}
