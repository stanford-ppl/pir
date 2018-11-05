package prism
package codegen

import java.nio.file._
import java.io._
import scala.collection.mutable.Stack

trait FormatPrinter {
  def sw:StreamWriter
  val tab = "  "
  def incLevel:Unit = { sw.level += 1 }
  def decLevel:Unit = { sw.level -= 1; assert(sw.level >= 0) }
  def incLevel(i:Int):Unit = (0 until i).foreach(_ => incLevel)
  def decLevel(i:Int):Unit = (0 until i).foreach( _ => decLevel)
  def blist = { sw.listing = true; incLevel }
  def elist = { sw.listing = false; decLevel }

  trait Braces { def s:String; def e:String }
  case object Brackets extends Braces { def s = "["; def e = "]" }
  case object CurlyBraces extends Braces { def s = "{"; def e = "}" }
  case object Parentheses extends Braces { def s = "("; def e = ")" }
  case object NoneBraces extends Braces { def s = ""; def e = "" }

  def indent(s:String) = if (s=="") "" else s"${tab*sw.level}$s"
  def listFormat(s:String) = if (sw.listing) s"- $s" else s

  def write(s:String):Unit = sw.print(s)
  def writeln(s:String):Unit = sw.println(s)

  def emit:Unit = write("") 
  def emit(s:String):Unit = write(indent(listFormat(s)))
  def emitln(s:String):Unit = writeln(indent(listFormat(s)))
  def emitln:Unit = writeln("")

  def emit(s:Any):Unit = emit(s.toString) 

  def emitBSln:Unit = emitBSln(None, None, None)
  def emitBSln(b:Braces):Unit = emitBSln(None, Some(b), None)
  def emitBSln(bs:String):Unit = emitBSln(Some(bs),None, None)
  def emitBSln(bs:String, b:Braces):Unit = emitBSln(Some(bs), Some(b), None)
  def emitBSln(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = { 
    emitln(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).s}${es.fold(""){ es => s" $es"}}"); incLevel
  }

  def emitBS:Unit = emitBS(None, None, None)
  def emitBS(b:Braces):Unit = emitBS(None, Some(b), None)
  def emitBS(bs:String):Unit = emitBS(Some(bs),None, None)
  def emitBS(bs:String, b:Braces):Unit = emitBS(Some(bs), Some(b), None)
  def emitBS(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = { 
    emit(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).s}${es.fold(""){ es => s" $es"}}"); incLevel
  }

  def emitBEln(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = {
    decLevel; emitln(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).e}${es.fold(""){ es => s" $es"}}")
  }
  def emitBEln(bs:String, b:Braces):Unit = emitBEln(Some(bs), Some(b), None) 
  def emitBEln(es:String):Unit = emitBEln(None, None, Some(es))
  def emitBEln(b:Braces):Unit = emitBEln(None, Some(b), None)
  def emitBEln:Unit = emitBEln(None, None, None)

  def emitBE(bs:Option[String], b:Option[Braces], es:Option[String]):Unit = {
    decLevel; emit(s"${bs.fold(""){ bs => s"$bs "}}${b.getOrElse(CurlyBraces).e}${es.fold(""){ es => s" $es"}}")
  }
  def emitBE(bs:String, b:Braces):Unit = emitBE(Some(bs), Some(b), None) 
  def emitBE(es:String):Unit = emitBE(None, None, Some(es))
  def emitBE(b:Braces):Unit = emitBE(None, Some(b), None)
  def emitBE:Unit = emitBE(None, None, None)

  def emitBlock[T](block: =>T):T = emitBlock(None, None, None)(block)
  def emitBlock[T](b:Braces)(block: =>T):T = emitBlock(None, Some(b), None)(block)
  def emitBlock[T](bs:String)(block: =>T):T = emitBlock(Some(bs), None, None)(block)
  def emitBlock[T](bs:String, b:Braces)(block: =>T):T = emitBlock(Some(bs), Some(b), None)(block)
  def emitBlock[T](bs:String, block: =>T, es: String):T = emitBlock(Some(bs), None, Some(es))(block)
  def emitBlock[T](bs:Option[String], b:Option[Braces], es:Option[String])(block: =>T):T = 
    { emitBSln(bs, b, None); val res = block; emitBEln(None, b, es); res }

  def emitList[T](s:String)(block: => T) = { emitln(s); blist; val res = block; elist; res}

  def emitTitleComment(title:String) = 
    emitln(s"/*****************************${title}****************************/")
  def flush = sw.flush
  def close = sw.close
}
