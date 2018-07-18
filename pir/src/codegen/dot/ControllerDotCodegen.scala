package pir
package codegen

import pir.pass._
import pir.node._
import prism.traversal._

class ControllerDotCodegen(val fileName:String)(implicit compiler:PIR) extends PIRPass with ControllerChildFirstTraversal with IRDotCodegen {

  import pirmeta._

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
    case n:Memory if isRemoteMem(n) => attr.fillcolor(orange).style(filled)
    case n:Memory if isFIFO(n) => attr.fillcolor(gold).style(filled)
    case n:Memory if isReg(n) => attr.fillcolor(limegreen).style(filled)
    case n:DramController => attr.fillcolor("lightseagreen").style(filled)
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
      case n:StreamIn => label += s"\n(${n.field})"
      case n:StreamOut => label +=s"\n(${n.field})"
      case n =>
    }
    val metas = List(parOf, itersOf, countsOf, boundOf, ctrlOf, srcCtxOf)
    metas.foreach { meta =>
      meta.asK(n).flatMap { k => meta.get(k) }.foreach { v =>
        label += s"\n(${meta.name}=$v)"
      }
    }
    n match {
      case n:Controller => 
        getParOf(n)
        label += s"\nlevel=${n.level}"
        label += s"\nstyle=${n.style}"
      case n:Memory =>
        (accessesOf(n)).foreach { access =>
          label += s"\n$access"
          itersOf.get(access).foreach { m => label += s"\n(iters=$m)" }
        }
    }
    attr.label(label)
  }

  override def emitSingleNode(n:N):Unit = {
    ctrlOf.foreach { 
      case (mem:RetimingFIFO, `n`) if memWithSameWriteAndReadCtrl(mem) =>
      case (mem:Memory, `n`) => emitSingleNode(mem)
      case _ => 
    }
    super.emitSingleNode(n)
  }

  def memWithSameWriteAndReadCtrl(mem:Memory) = {
    val readCtrl = outAccessesOf(mem).map { read => ctrlOf(read) }.toSet
    val writeCtrl = inAccessesOf(mem).map { write => ctrlOf(write) }.toSet
    readCtrl.size==1 && readCtrl == writeCtrl
  }

  override def emitEdges = {
    val mems = compiler.top.collectDown[Memory]()
    mems.foreach { 
      case mem:RetimingFIFO if memWithSameWriteAndReadCtrl(mem) =>
      case mem =>
        outAccessesOf(mem).foreach { access => emitEdge(mem, ctrlOf(access)) }
        inAccessesOf(mem).foreach { access => emitEdge(ctrlOf(access), mem) }
    }
  }

  override def quote(n:Any):String = qtype(n)

}
