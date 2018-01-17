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
  def qtype(n:N) = n.name.map { name => s"${n.className}($name)" }.getOrElse(s"$n")
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

  def emitSubGraph(n:N):Unit = {
    emitSubGraph(n, qtype(n)) {
      traverseChildren(n, ())
    }
  }

  def emitSingleNode(n:N) = {
    emitNode(n,DotAttr().shape(box).label(qtype(n)))
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

  override def emitNode(n:N) = {
    n match {
      case n:Atom[_,_] => emitSingleNode(n)
      case n:Controller if n.level==InnerControl => emitSingleNode(n)
      case n => emitSubGraph(n)
    }
  }
}
