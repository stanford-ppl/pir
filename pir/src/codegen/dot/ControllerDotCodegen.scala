package pir.codegen

import pir.node._
import prism.traversal._

class ControllerDotCodegen(val fileName:String)(implicit compiler:PIR) extends PIRPass with ChildFirstTraversal with IRDotCodegen {

  import pirmeta._

  type N = Controller

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
      block
    }
  }

  override def label(attr:DotAttr, n:Any) = {
    var label = quote(n)
    n match {
      case n:Controller => getParOf(n)
      case _ =>
    }
    val metas = List(parOf, itersOf, countsOf)
    metas.foreach { meta =>
      meta.asK(n).flatMap { k => meta.get(k) }.foreach { v =>
        label += s"\n(${meta.name}=$v)"
      }
    }
    attr.label(label)
  }

  override def emitSingleNode(n:N):Unit = {
    ctrlOf.foreach { 
      case (mem:RetimingFIFO, `n`) =>
      case (mem:Memory, `n`) => emitSingleNode(mem)
      case _ => 
    }
    super.emitSingleNode(n)
  }

  override def runPass = {
    traverseNode(compiler.top.topController)
  }

  override def emitEdges = {
    val mems = compiler.top.collectDown[Memory]()
    mems.foreach { 
      case mem:RetimingFIFO =>
      case mem =>
        readersOf(mem).foreach { reader => emitEdge(mem, ctrlOf(reader)) }
        writersOf(mem).foreach { writer => emitEdge(ctrlOf(writer), mem) }
    }
  }

  override def quote(n:Any):String = qtype(n)

}
