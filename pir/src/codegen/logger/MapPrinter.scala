package pir.codegen

import pir._
import pir.util.typealias._

import spade._
import spade.util._
import spade.traversal._

import pirc._
import pirc.util._

class MapPrinter(implicit design: PIR) extends Codegen with HiearchicalTraversal {
  def shouldRun = Config.debug && PIRConfig.mapping

  implicit lazy val mp:PIRMap = design.mapping.get

  override lazy val stream = newStream("Mapping.log") 
  
  override def initPass = {
    super.initPass
  }

  override def traverseDown(n:Any):Unit = {
    def emitBlock(n:PNode)(block: => Unit) = {
      super.emitBlock(s"${quote(n)} -> ${mp.pmmap(n)}") { 
        block
        super.traverseDown(n)
      } 
    }
    n match {
      case n:PI[_] => 
        emitln(s"${quote(n)}(${mp.pmmap.get(n)}) <= ${fanInOf(n)(mp).map(quote)}")
      case n:PO[_] => 
        emitln(s"${quote(n)}(${mp.pmmap.get(n)}) => ${fanOutOf(n)(mp).map(quote)}")
      case n:PFU if mp.pmmap.contains(n) => 
        emitBlock(n) { emitln(s"$n.op=${mp.pmmap.to[FU](n).op}") }
      case n:PCU if mp.pmmap.contains(n) => 
        emitBlock(n) {
          super.emitBlock(s"colors:") {
            n.regs.foreach { preg => s"${quote(preg)} => ${mp.rcmap.get(preg)}" }
          }
        }
      case n:PNode if mp.pmmap.contains(n) => emitBlock(n) { } 
      case (_:PST | _:PCtr | _:PCL | _:PMem) => 
      case n => super.traverseDown(n)
    }
  }

  addPass(canRun=design.mapping.nonEmpty) {
    traverseDown(design.arch.top)
    //design.arch.ctrlers.foreach(emit)
    //design.arch match {
      //case sn:SwitchNetwork =>
        //sn.sbs.foreach(emit)
      //case _ =>
    //}
    emitBlock(s"mkmap") {
      mp.mkmap.map.foreach { case (k,v) =>
        emitln(s"${quote(k.src)}.$k -> $v")
      }
    }
  }

  def print(mapping:PIRMap) = {
    run
  }

  //def emitModule(m:PModule, title:String)(block: =>Any) = {
    //emitList(s"${title}") {
      //m.ins.foreach(emit)
      //m.outs.foreach(emit)
      //block
    //}
  //}

  override def finPass = {
    close
    endInfo(s"Finishing MapPrinter Printing in ${getPath}")
  }

}
