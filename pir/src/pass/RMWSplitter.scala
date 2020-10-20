package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._
import scala.collection.mutable

class RMWSplitter(implicit compiler:PIR) extends PIRTraversal with BFSBottomUpTopologicalTraversal with UnitTraversal with PIRTransformer {
  // Start by visiting every RMW access
  // Stage a new output context
  // Trace the outputs, and send them to the new context

  //def getDeps(n:N):List[N] = {
    // dbg(s"depeds = ${n.depeds.map { deped => (deped, isLive(deped)) }}")
    // depFunc(n)
  //}
  //
  //override def visitOut(n:N):List[N] = n match {
  //}
  // def traverseNodes(ns: => List[pir.node.PIRNode],zero: Unit): Unit = {
  // }
  
  val forward:Boolean = true
  val ctxMap = mutable.Map[ControlTree, Context]() 

  def getDeps(n:N):List[N] = {
    val d = super.depedFunc(n).filter { _ match {
        case _:Context => false
        case _:Memory => false
        case _ => true
      }
    }
    d ++ d.flatMap(getDeps)
  }
  
  override def visitNode(n:N) = {
    n match {
      case n@SparseRMW(_,_,_,k) =>
        if (k < 0) {
          ctxMap.clear
          dbg(s"SparseRMW: $n")
          dbg(s"output: ${n.dataOut}")
          dbg(s"output con: ${n.dataOut.T}")
          dbg(s"visitOut: ${super.depedFunc(n)}")
          dbg(s"deps: ${getDeps(n)}")
          getDeps(n).map { n =>
            val ctrl = n.ctrl.get
            val parent = n.global.getOrElse { pirTop }
            val ctx = within(parent, ctrl) { 
              ctxMap.getOrElseUpdate(ctrl, stage(Context().srcCtx.update(ctrl.srcCtx.v)))
            }
            // val belowParent = n.ancestorSlice(parent).dropRight(1).last
            // dbg(s"$n parent=$parent, ctx=$ctx belowParent=$belowParent")
            dbg(s"$n parent=$parent, ctx=$ctx")
            swapParent(n, ctx)
          }
        }
      case _ =>
        dbg(s"other: $n")
        super.visitNode(n)
    }
  }
}
