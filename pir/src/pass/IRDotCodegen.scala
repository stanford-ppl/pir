package pir.pass
import pir._
import pir.newnode._
import pir.util._

import pirc._
import pirc.util._
import pirc.node._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

abstract class CodegenWrapper(implicit design:PIR) extends pir.newnode.Traversal with ChildFirstTraversal with pir.codegen.Codegen {

  type T = Unit

  override def reset = {
    super[Codegen].reset
    super[ChildFirstTraversal].reset
  }

  def traverseNode(n:N):Unit = traverseNode(n, ())
  override def visitNode(n:N, prev:T):T = emitNode(n)

  def emitNode(n:N):Unit = {
    emitln(s"${qdef(n)} // TODO: unmatched node")
    super.visitNode(n, ())
  }

}

class IRPrinter(implicit design:PIR) extends CodegenWrapper with pir.codegen.DotCodegen {

  override lazy val stream = newStream(s"IRPrinter.log")

  def horizontal:Boolean = false
  def shouldRun = true

  def depFunc(n:N):List[N] = n.localDeps.toList

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) {
      emitln(s"parent=${n.parent}")
      emitln(s"children=${n.children}")
      emitln(s"localDeps=${n.localDeps}")
      emitln(s"localDepeds=${n.localDepeds}")
      traverseChildren(n, ())
      n match {
        case n:Module =>
          n.ios.foreach { io =>
            emitln(s"$io.connected=[${io.connected.mkString(",")}]")
          }
        case _ =>
      }
    }
  }

  addPass {
    traverseNode(design.newTop)
  }
  
}

abstract class IRDotCodegen(fn:String)(implicit design:PIR) extends CodegenWrapper with pir.codegen.DotCodegen {

  override lazy val stream = newStream(fn)

  def horizontal:Boolean = false
  def shouldRun = true

  val nodes = mutable.ListBuffer[N]()

  addPass {
    emitBlock("digraph G") {
      if (horizontal) emitln(s"rankdir=LR")
      emitNode(design.newTop)
      nodes.foreach(emitEdge)
    }
  }
  
  
  def open = {
    s"out/bin/run ${getPath} &".replace(".dot", "") !
  }

  def shape(attr:DotAttr, n:N) = attr.shape(box)

  def color(attr:DotAttr, n:N) = attr.fillcolor(white).style(filled)

  def label(attr:DotAttr, n:N) = attr.label(qtype(n))

  def emitSubGraph(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitSubGraph(n, attr) {
      traverseChildren(n, ())
    }
  }

  def emitSingleNode(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
    nodes += n
  }

  override def emitNode(n:N) = {
    n match {
      case n:Atom[_] => emitSingleNode(n)
      case n => emitSubGraph(n)
    }
  }

  def matchLevel(n:N) = {
    ((n::n.ancestors) intersect nodes).sortBy { case n => n.ancestors.size }(Ordering[Int].reverse).headOption
  }

  def emitEdge(n:N):Unit = {
    n.ins.foreach { 
      case in if in.isConnected =>
        in.connected.foreach { out => emitEdge(out.src.asInstanceOf[N], n) }
      case in =>
    }
  }

  def emitEdge(from:N, to:N) = {
    matchLevel(from).foreach { from =>
      super.emitEdge(from, to)
    }
  }

}

abstract class GlobalIRDotCodegen(fn:String)(implicit design:PIR) extends IRDotCodegen(fn) with pirc.node.GraphCollector {

  override def label(attr:DotAttr, n:N) = n match {
    case n:Counter =>
      val fields = n.fields.zip(n.productIterator.toList).flatMap { 
        case (field, Const(v)) => Some(s"$field=$v")
        case _ => None
      }
      attr.label(s"${qtype(n)}(${fields.mkString(",")})")
    case n => super.label(attr, n)
  }

  //def shape(attr:DotAttr, n:N) = attr.shape(box)

  override def color(attr:DotAttr, n:N) = n match {
    case n:SRAM => attr.fillcolor(orange).style(filled)
    case n:StreamIn => attr.fillcolor(gold).style(filled)
    case n:StreamOut => attr.fillcolor(gold).style(filled)
    case n:Reg => attr.fillcolor(limegreen).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n => super.color(attr, n)
  }

  override def emitNode(n:N) = {
    n match {
      case n:Const[_] if collectOut[Counter](n).isEmpty =>
      //case n:Module if n.globalDeps.nonEmpty | n.globalDepeds.nonEmpty | n.isChildOf(design.newTop) => emitSingleNode(n)
      //case n:Module =>  
      case n:Module => emitSingleNode(n)
      case n => emitSubGraph(n)
    }
  }

}
