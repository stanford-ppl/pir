package pir.codegen

import pir._
import pir.misc._
import pir.plasticine.graph._
import pir.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class CollectionStatus {
  var firstPair = true 
  var inScope = true 
  def this(im:Boolean) = {
    this()
    inScope = im
  }
}
trait JsonCodegen extends Printer {
  def emitComma(implicit ms:CollectionStatus) = { 
    if (ms.inScope) { 
      if (ms.firstPair) ms.firstPair = false 
      else pprintln(s",")
    }
  }
  def emitMap(value: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    def block = { value(new CollectionStatus()); pprintln }
    emitElem(block)(ms)
  }
  def emitMap(key:String)(value: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    def block = { value(new CollectionStatus()); pprintln }
    emitPair(key)(block)(ms)
  }
  def emitList(key:String)(value: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    emitComma
    def block = { value(new CollectionStatus()); pprintln }
    emitCSln(s""""$key": """)
    block
    emitCE
  }
  def emitList(key:String, value: List[String])(implicit ms:CollectionStatus):Unit = { 
    { emitComma; emit(s""""$key" : [${value.mkString(",")}]""") }
  }
  def emitPair(key:String, value: Int)(implicit ms:CollectionStatus):Unit = 
    { emitComma; emit(s""""$key" : $value""") }
  def emitPair(key:String, value: Double)(implicit ms:CollectionStatus):Unit = 
    { emitComma; emit(s""""$key" : $value""") }
  def emitPair(key:String)(value: =>Unit)(implicit ms:CollectionStatus):Unit = 
    { emitComma; emit(s""""$key" :"""); emitBSln; value; emitBE }
  def emitPair(key:String, value: String)(implicit ms:CollectionStatus):Unit = 
    { emitComma; emit(s""""$key" : "$value"""") }
  def emitElem(value: =>Unit)(implicit ms:CollectionStatus):Unit = 
    { emitComma; emit; emitBSln; value; emitBE }
  def emitElem(value: String)(implicit ms:CollectionStatus):Unit = 
    { emitComma; emit(s"""$value""") }

  def emitComment(label:String, str:String)(implicit ms:CollectionStatus) = {
    if (Config.debugCodegen) { emitPair(s"comment-$label", str) }
  }
  def emitComment(str:String)(implicit ms:CollectionStatus) = {
    if (Config.debugCodegen) { emitPair(s"comment", str) }
  }
}

