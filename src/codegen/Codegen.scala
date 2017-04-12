package pir.codegen

import pir.graph._
import pir.pass.Traversal
import pir.Design
import pir.pass.Pass
import pir.plasticine.main._
import pir.util.typealias._

import scala.collection.mutable.Set
import java.io.PrintWriter
import java.io.{File, FileInputStream, FileOutputStream}

abstract class Codegen(implicit design:Design) extends Pass with Printer {
  implicit def spade:Spade = design.arch

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

  def quote(n:Any):String = n match {
    case n:Node => pir.util.quote(n) 
    case n:PNode => pir.plasticine.util.quote(n)
  }

  override def finPass = {
    super.finPass
    close
  }
}
