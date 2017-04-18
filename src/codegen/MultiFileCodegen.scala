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

trait MultiFileCodegen extends Printer {
  var lineNumber:Int = 0
  var fileNumber:Int = 0
  var printer:Printer = this

  def traitName:String 
  def dir:String

  def isSplitting = printer != this

  override def pprintln(s:String):Unit = pprint(s"$s\n")
  override def pprintln:Unit = pprintln("") 
  override def pprint(s:String):Unit = {
    if (lineNumber > 100) {
      closeFile
      openFile
    }
    if (isSplitting) {
      printer.pprint(s); lineNumber += 1
    } else {
      super.pprint(s)
    }
  }

  def splitPreHeader:Unit = {
  }

  def splitPostHeader:Unit = {
  }

  def splitPreFooter:Unit = {
  }

  def splitPostFooter:Unit = {
  }

  def openFile = {
    fileNumber += 1
    printer = new Printer {
      override lazy val stream:OutputStream = newStream(dir, s"$traitName$fileNumber.scala") 
    }
    lineNumber = 0
    splitPreHeader
    val prevFile = if (fileNumber==1) " " else s" extends ${traitName}${fileNumber-1}"
    emitBSln(s"trait $traitName$fileNumber$prevFile")
    splitPostHeader
  }

  def closeFile = {
    lineNumber = 0
    splitPreFooter
    emitBEln
    printer.close
  }

  def emitSplit(block: =>Unit):Unit = {
    openFile
    block
    closeFile
    printer = this
  }

  def emitMixed(block: => Unit):Unit = {
    emitBlock(s"trait $traitName extends ${(0 until fileNumber).map(i => s"${traitName}${i+1}").mkString(" with ")}") {
//    emitBlock(s"trait $traitName extends ${traitName}${fileNumber}") {
      block
    }
  }

}
