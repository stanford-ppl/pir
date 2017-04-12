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

  def emitLambda(s:String, ins:Any*)(block: =>Any) = { 
    emitBS(s"$s ")
    val input = if (ins.size==1) { s"${ins.head}" } else { s"case (${ins.mkString(",")})" }
    emitln(s"${input} =>")
    block
    emitBEln
  }

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

  def emitInst(s:String)(block: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    emitComma;
    emitBlock(s, Parentheses) {
      block(new CollectionStatus())
      pprintln
    }
  }
}


