package prism.codegen

import prism._

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
      else writeln(s",")
    }
  }
  def emitMap(value: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    def block = { value(new CollectionStatus()); writeln("") }
    emitElem(block)(ms)
  }
  def emitMap(key:String)(value: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    def block = { value(new CollectionStatus()); writeln("") }
    emitPair(key)(block)(ms)
  }
  def emitList(key:String)(value: CollectionStatus=>Unit)(implicit ms:CollectionStatus):Unit = { 
    emitComma
    def block = { value(new CollectionStatus()); writeln("") }
    emitBSln(s""""$key": """, Brackets)
    block
    emitBE(Brackets)
  }
  def emitList(key:String, value: List[String])(implicit ms:CollectionStatus):Unit =
    { emitComma; emit(s""""$key" : [${value.mkString(",")}]""") }
  def emitLineMap(key:String, value:List[(String, String)])(implicit ms:CollectionStatus):Unit =
  { emitComma; emit(s""""$key" : {${value.map{ case (k,v) => s""""$k" : "$v""""}.mkString(",")}}""") }
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
    { emitComma; emit(s""""$value"""") }

  def emitComment(label:String, str:String)(implicit ms:CollectionStatus):Unit = {
    if (Config.debugCodegen) { emitPair(s"comment-$label", str) }
  }
  def emitComment(label:String, str:Int)(implicit ms:CollectionStatus):Unit = {
    emitComment(label, s"${str}")
  }
  def emitComment(str:String)(implicit ms:CollectionStatus):Unit = {
    if (Config.debugCodegen) { emitPair(s"comment", str) }
  }
}

