package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.PIRMisc._
import pir.plasticine.graph._

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
trait JsonCodegen extends Traversal with Printer {
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
}

class PisaCodegen(implicit design: Design) extends Traversal with JsonCodegen {

  override val stream = newStream(Config.pisaFile) 
  
  override def initPass = {
  }

  def opMap(op:Op):String = {
    op match {
      case o:FltOp => throw TODOException(s"Op ${op} is not supported at the moment")
      case o:FixOp => match {
        case o:FixAdd => s"+"
        case o:FixSub => s"-"
        case o:FixMul => s"*"
        case o:FixDiv => s"%"
      }
    }
  }

  override def traverse = {
    implicit val ms = new CollectionStatus(false)
    emitBlock {
      emitMap("PISA") { implicit ms =>
        emitPair("version", 0.1)
        emitMap("topconfig"){ implicit ms =>
          emitPair("type", "plasticine")
          emitMap("config") { implicit ms =>
            emitList("cu") { implicit ms =>
              emitMain
            }
          }
        }
      }
      pprintln
    }
  }

  def emitMain(implicit ms:CollectionStatus) = {
    design.arch.ctrlers.foreach { ctrler =>
      ctrler match {
        case top:Top =>
        case cu:ComputeUnit => cu match {
          case tt:TileTransfer =>
          case _ =>
            emitMap { implicit ms =>
              emitList(s"scratchpads") { implicit ms =>
                cu.srams.foreach{ s => 
                  emitMap{ implicit ms =>
                    emitPair("ra", "x")
                    emitPair("wa", "x")
                    emitPair("wd", "x")
                    emitPair("wen", "x")
                  }
                }
              }
              emitList(s"counterChain") { implicit ms =>
                emitPair("chain", "[0]") // ??
                emitMap("counters") { implicit ms =>
                  emitMap { implicit ms =>
                    emitPair("max", 64)
                    emitPair("min", 64)
                    emitPair("stride", 64)
                  }
                }
              }
              emitList(s"pipeStage") { implicit ms =>
                cu.stages.foreach { stage =>
                  emitMap { implicit ms =>
                    emitPair("opA", "m1")
                    emitPair("opB", "m0")
                    emitPair("opcode", "m0")
                    emitPair("result", "m0")
                    emitPair("fwd", "m0")
                  }
                }
              }
              emitMap(s"control") { implicit ms =>
              }
            }
            //emitBlock(s"ctrs") {
            //  cu.ctrs.foreach{ c =>
            //    emitBlock(s"${c}") {
            //    }
            //  }
            //}
            //emitln(s"reduce: ${cu.reduce.mt}")
            //emitBlock("stages") {
            //  cu.stages.foreach { s =>
            //  }
            //}
        }
      }
    }
  }

  override def finPass = {
    close
    info(s"Finishing PisaCodegen in ${getPath}")
  }

}
