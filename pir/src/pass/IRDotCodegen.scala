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

abstract class CodegenWrapper(implicit design:PIR) extends pir.codegen.Codegen with ChildFirstTraversal with pir.newnode.Traversal {

  type T = Unit

  override def reset = {
    super[Codegen].reset
    super[ChildFirstTraversal].reset
  }

  override def visitNode(n:N, prev:T):T = {
    emitNode(n)
  }

  def emitNode(n:N):Unit = {
    emitln(s"${qdef(n)} // TODO: unmatched node")
    traverse(n, ())
  }

  def qdef(n:N) = s"$n = ${n.productName}"
  def qtype(n:N) = n.name.map { name => s"${n.className}[$name]" }.getOrElse(s"$n")
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
    emitNode(design.newTop)
    TraversalTest.testGraph
    TraversalTest.testBFS
    TraversalTest.testDFS
    TraversalTest.testTopo
    TraversalTest.testHierTopoDFS
    TraversalTest.testHierTopoBFS
  }
  
}

abstract class IRDotCodegen(implicit design:PIR) extends CodegenWrapper with pir.codegen.DotCodegen {

  override lazy val stream = newStream(s"IRDotCodegen.dot")

  def horizontal:Boolean = false
  def shouldRun = true

  def depFunc(n:N):List[N] = n.localDeps.toList

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

  def fillcolor(attr:DotAttr, n:N) = attr

  def label(attr:DotAttr, n:N) = attr.label(qtype(n))

  def emitSubGraph(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = fillcolor(attr, n)
    attr = label(attr, n)
    emitSubGraph(n, attr) {
      traverseChildren(n, ())
    }
  }

  def emitSingleNode(n:N) = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = fillcolor(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
    nodes += n
  }

  override def emitNode(n:N) = {
    n match {
      case n:Atom[_,_] => emitSingleNode(n)
      case n => emitSubGraph(n)
    }
  }

  def matchLevel(n:N) = {
    ((n::n.ancestors) intersect nodes).sortBy { case n => n.ancestors.size }(Ordering[Int].reverse).headOption.getOrElse(n)
  }

  def emitEdge(n:N):Unit = {
    n.ins.foreach { 
      case in if in.isConnected =>
        in.connected.foreach { out =>
          val from = matchLevel(out.src.asInstanceOf[N])
          emitEdge(from, n)
        }
      case in =>
    }
  }

}

abstract class GlobalIRDotCodegen(implicit design:PIR) extends IRDotCodegen with pir.newnode.Traversal {

  override lazy val stream = newStream(s"GlobalIRDotCodegen.dot")

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

  override def fillcolor(attr:DotAttr, n:N) = n match {
    case n:SRAM => attr.fillcolor(cyan).style(filled)
    case n:StreamIn => attr.fillcolor(gold).style(filled)
    case n:StreamOut => attr.fillcolor(gold).style(filled)
    case n:Reg => attr.fillcolor(gold).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    case n => super.fillcolor(attr, n)
  }

  override def emitNode(n:N) = {
    n match {
      case n:Atom[_,_] => emitSingleNode(n)
      case n:Controller if n.level==InnerControl => emitSingleNode(n)
      case n => emitSubGraph(n)
    }
  }

}
