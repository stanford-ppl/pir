package pir.codegen

import pir.Design
import pir.plasticine.main._
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

trait ScalaCodegen extends Printer {
  def emitComment(s:Any) = {
    emitln(s"// $s")
  }
  def quote(n:Iterable[_]):String = s"List(${n.mkString(",")})"

  def emitComma(implicit ms:CollectionStatus):Unit = { 
    if (ms.inScope) { 
      if (ms.firstPair) ms.firstPair = false 
      else pprintln(s",")
    }
  }

  def emitComma(s:String)(implicit ms:CollectionStatus):Unit = { 
    emitComma
    emit(s)
  }

  def emitInst(s:String)(block: CollectionStatus=>Unit)(e:String)(implicit ms:CollectionStatus):Unit = { 
    emitComma;
    emitBlock(s, None, Some(e), Parentheses) {
      block(new CollectionStatus())
      pprintln
    }
  }

  def emitLambda[T](s:String)(block: =>T):T = { 
    emitBlock(s=s, ss=None, es=None)(block)
  }

  def emitLambda[T](s:String, ss:String)(block: =>T):T = { 
    emitBlock(s=s, ss=Some(ss), es=None)(block)
  }

  def emitBlock[T](s:String, ss:Option[String]=None, es:Option[String]=None, b:Braces=CurlyBraces)(block: =>T):T = { 
    emitBS(s"$s ", b)
    ss.foreach { ss => emit(s"$ss =>") }
    emitln
    val res = block
    emitBE(b)
    es.foreach { es => emit(es) }
    emitln
    res
  }
}
