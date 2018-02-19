package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import pirc._
import pirc.util._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable


class ControllerDotCodegen(val fileName:String)(implicit design:PIR) extends PIRPass with ChildFirstTraversal with IRDotCodegen {

  import pirmeta._

  type N = Controller

  val dirName = design.outDir

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
    case n:SRAM => attr.fillcolor(orange).style(filled)
    case n:RetimingFIFO => attr.fillcolor(gold).style(filled)
    case n:FIFO => attr.fillcolor(gold).style(filled)
    case n:StreamIn => attr.fillcolor(gold).style(filled)
    case n:StreamOut => attr.fillcolor(gold).style(filled)
    case n:Reg => attr.fillcolor(limegreen).style(filled)
    case n:ArgIn => attr.fillcolor(limegreen).style(filled)
    case n:ArgOut => attr.fillcolor(limegreen).style(filled)
    case n:Controller if n.children.nonEmpty => attr.style(dashed)
    case n => super.color(attr, n)
  }

  override def emitSubGraph(n:N)(block: => Unit):Unit = {
    var attr = DotAttr()
    attr = label(attr, n)
    emitSubGraph(n, attr) { 
      emitSingleNode(n)
      ctrlOf.bmap(n).foreach {
        case mem:Memory if !isInnerAccum(mem) => emitSingleNode(mem)
        case _ =>
      }
      block
    }
  }

  val collector = new GraphCollector {
    type N = PIRNode
  }
  
  override def runPass = {
    traverseNode(design.top.topController)
  }

  override def emitEdges = {
    val mems = collectDown[Memory](design.top)
    mems.foreach { 
      case mem:ArgIn =>
        mem.readers.foreach { reader => emitEdge(mem, ctrlOf(reader)) }
      case mem:ArgOut =>
        mem.writers.foreach { writer => emitEdge(ctrlOf(writer), mem) }
      case mem if !isInnerAccum(mem) =>
        mem.readers.foreach { reader => emitEdge(mem, ctrlOf(reader)) }
        mem.writers.foreach { writer => emitEdge(ctrlOf(writer), mem) }
      case mem =>
    }
  }

  def quote(n:Any):String = qtype(n)

}
