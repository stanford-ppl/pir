package pir

import java.io.PrintWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

trait Printer {
  var fileName:String = "System.out"
  val stream:OutputStream = System.out

  def getPath = {
    if (stream==System.out) "console"
    else s"${Config.outDir}${File.separator}${fileName}"
  }

  lazy val pw = new PrintWriter(stream)
  val tab = "  "
  var level = 0

  def emit(s:String) = pw.print(s"${tab*level}${s}")
  def emitln(s:String) = pw.println(s"${tab*level}${s}")
  def emitBS(s:String):Unit = { emit(s); emitBS }
  def emitBS:Unit = { pw.println(s"{"); level += 1 }
  def emitBE = { level -= 1; emitln(s"}") }
  def emitBlock(s:String)(block: =>Any) = { emitBS(s); block; emitBE }
  def flush = pw.flush()
  def close = {
    pw.flush()
    if (stream != System.out)
      pw.close()
  }

  def newStream(fname:String) = { 
    fileName = fname
    val dir = new File(Config.outDir);
    if (!dir.exists()) {
      println(s"creating output directory: ${System.getProperty("user.dir")}${File.separator}${Config.outDir}");
      dir.mkdir();
    }
    new FileOutputStream(new File(s"${getPath}"))
  }
}
