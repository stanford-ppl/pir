package pirc.codegen

import pirc._

import java.io.{File, FileInputStream, FileOutputStream}

trait Codegen extends Pass with Printer {

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
    val srcDirName = srcDir.split("/").last
    val dstDirFile = new File(s"$dstDir/$srcDirName")
    dstDirFile.mkdirs()

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

  override def finPass = {
    super.finPass
    close
  }
}
