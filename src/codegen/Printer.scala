package pir.codegen

import pir._
import pir.exceptions._
import pir.plasticine.main.Spade
import pir.pass.Pass

import java.io.PrintWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.ByteArrayOutputStream
import scala.collection.mutable.Stack

trait Printer {
  var fileName:String = "System.out"
  var dirPath:String = Config.outDir 
  lazy val stream:OutputStream = System.out
  def stdOut = fileName=="System.out" 

  def getPath = {
    if (stdOut) "console"
    else s"${dirPath}${File.separator}${fileName}"
  }

  lazy val writer = new PrintWriter(stream)
  //lazy val pw = new PrintWriter(stream)
  def pw:PrintWriter = { bufferWriters.headOption.getOrElse(writer) }
  val tab = "  "
  var level = 0
  var listing = false
  def incLevel = level += 1
  def decLevel = { level -= 1; assert(level >= 0) }
  def blist = { listing = true; incLevel }
  def elist = { listing = false; decLevel }

  def pprint(s:String):Unit = { pw.print(s); if (stdOut) flush }
  def pprintln(s:String):Unit = { pw.println(s); if (stdOut) flush }
  def pprintln:Unit = { pw.println; if (stdOut) flush }

  trait Braces { def s:String; def e:String }
  case object Brackets extends Braces { def s = "["; def e = "]" }
  case object CurlyBraces extends Braces { def s = "{"; def e = "}" }
  case object Parentheses extends Braces { def s = "("; def e = ")" }

  def emit(s:String):Unit = pprint(s"${tab*level}${if (listing) "- " else ""}${s}")
  def emit(s:Any):Unit = emit(s.toString) 
  def emit:Unit = emit("") 
  def emitln(s:String):Unit = pprintln(s"${tab*level}${if (listing) "- " else ""}${s}")
  def emitln:Unit = pprintln
  def emitln(i:Int):Unit = (0 until i).foreach { i => pprintln }
  def emitBSln(s:String, b:Braces):Unit = { emit(s); emitBSln(b) }
  def emitBSln(s:String):Unit = { emit(s); emitBSln }
  def emitBSln(b:Braces):Unit = { pprintln(b.s); incLevel }
  def emitBSln:Unit = { pprintln(CurlyBraces.s); incLevel }
  def emitBS(s:String, b:Braces):Unit = { emit(s); emitBS(b) }
  def emitBS(s:String):Unit = { emit(s); emitBS }
  def emitBS(b:Braces):Unit = { pprint(b.s); incLevel }
  def emitBS:Unit = { pprint(CurlyBraces.s); incLevel }
  def emitBEln(s:String, b:Braces):Unit = { emitBE(b); pprintln(s) }
  def emitBEln(s:String):Unit = { emitBE; pprintln(s) }
  def emitBEln(b:Braces):Unit = { emitBE(b); pprintln }
  def emitBEln:Unit = { emitBE; pprintln }
  def emitBE(b:Braces):Unit = { decLevel; emit(b.e) }
  def emitBE:Unit = { decLevel; emit(CurlyBraces.e) }
  def emitBE(s:String):Unit = { decLevel; emit(s"$s ${CurlyBraces.e}") }
  def emitBlock[T](block: =>T):T = { emitBSln; val res = block; emitBEln; res }
  def emitBlock[T](b:Braces)(block: =>T):T = { emitBSln(b); val res = block; emitBEln(b); res }
  def emitBlock[T](s:String)(block: =>T):T = { emitBSln(s"$s "); val res = block; emitBEln; res }
  def emitBlock[T](s:String, b:Braces)(block: =>T):T = { emitBSln(s"$s ", b); val res = block; emitBEln(b); res }
  def emitList[T](s:String)(block: => T) = { emitln(s); blist; val res = block; elist; res}

  def emitTitleComment(title:String) = 
    emitln(s"/*****************************${title}****************************/")
  def flush = pw.flush()
  def close = {
    pw.flush()
    if (stream != System.out)
      pw.close()
  }

  def newStream(dp:String, fname:String):FileOutputStream = { 
    fileName = fname
    dirPath = dp
    val dir = new File(dirPath)
    if (!dir.exists()) {
      println(s"[pir] creating output directory: ${System.getProperty("user.dir")}${File.separator}${Config.outDir}");
      dir.mkdir();
    }
    new FileOutputStream(new File(s"${getPath}"))
  }
  def newStream(fname:String)(implicit design:Design):FileOutputStream = { newStream(s"${Config.outDir}/$design", fname) }
  def newStream(fname:String, spade:Spade):FileOutputStream = { newStream(s"${Config.outDir}/$spade", fname) }

  /* A temporary stream to write all data */
  val buffers = Stack[ByteArrayOutputStream]()
  val bufferWriters = Stack[PrintWriter]()
  def openBuffer = {
    pw.flush
    buffers.push(new ByteArrayOutputStream())
    bufferWriters.push(new PrintWriter(buffers.top))
  }
  /*
   * Close the temporary stream and write all content in the temp stream to actual file
   * */
  def closeAndWriteBuffer = {
    val bufStream = buffers.pop
    val nextStream = buffers.headOption.getOrElse(stream)
    nextStream.write(bufStream.toByteArray())
    nextStream.flush
    buffers.push(bufStream)
    closeBuffer
  }

  /* Close the temporary stream */
  def closeBuffer = {
    val pw = bufferWriters.pop
    pw.flush()
    pw.close()
    buffers.pop
  }
}
