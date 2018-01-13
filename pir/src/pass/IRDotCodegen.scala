package pir.pass

import pir._
import pir.newnode._
import pir.util._

import pirc._
import pirc.util._
import pirc.node._

import sys.process._
import scala.language.postfixOps

abstract class CodegenWrapper(implicit design:PIR) extends pir.codegen.Codegen with DFSTopologicalTraversal with pir.newnode.Traversal {

  type T = Unit

  override def reset = {
    super[Codegen].reset
    super[DFSTopologicalTraversal].reset
  }

  def visitNode(n:N, prev:T):T = emitNode(n)

  def emitNode(n:N):Unit = traverse(n, ())

  def qdef(n:N) = s"$n = ${n.className}"
}

abstract class IRDotCodegen(implicit design:PIR) extends CodegenWrapper with pir.codegen.DotCodegen with Logger {

  override lazy val stream = newStream(s"IRDotCodegen.log")

  def horizontal:Boolean = false
  def shouldRun = true

  def depFunc(n:N):List[N] = n.localDeps

  //addPass {
    //emitBlock("digraph G") {
      //if (horizontal) emitln(s"rankdir=LR")
      //emitNode(design.newTop)
    //}
  //}
  
  addPass {
    TraversalTest.testBFS
    TraversalTest.testDFS
    TraversalTest.testTopoBFS
    TraversalTest.testTopoDFS
  }
  
  def open = {
    s"out/bin/run ${getPath} &".replace(".dot", "") !
  }

  override def emitNode(n:N):Unit = {
    n match {
      case n:Module =>
        emitList(qdef(n)) {
          dprintln(s"parent=${n.parent}")
        }
      case n:Container =>
        emitBlock(qdef(n)) {
          dprintln(s"parent=${n.parent}")
          dprintln(s"children=${n.children}")
          dprintln(s"")
          super.emitNode(n)
        }
    }
  }

}

