package pir.pass
import pir.graph._
import pir.Design
import pir.Config
import pir.exceptions._

import scala.collection.mutable.Set

trait Traversal extends Pass {
  implicit def design: Design

  val visited = Set[Node]()
  override def reset {
    super.reset
    visited.clear()
  }
  
  def traverse = visitNode(design.top)

  addPass { traverse }

  def visitNode(node: Node) : Unit = {
    assert(!visited.contains(node), s"Revisiting visited node ${node}! visitedNodes:${visited}")
    visitNodeNoCheck(node)
  }
  /* Depth first search traversal on node and their fields */
  def visitNodeNoCheck(node: Node) : Unit = {
    if (visited.contains(node)) return
    visited += node
    node match {
      case n:Controller => 
        n.sins.foreach { si => visitNode(si) }
        n.vins.foreach { vi => visitNode(vi) }
        n.souts.foreach { so => visitNode(so) }
        n.vouts.foreach { vo => visitNode(vo) }
        n match {
        case c:Top => 
          c.compUnits.foreach(n => visitNode(n))
        case c:ComputeUnit => {
          c.cchains.foreach { cc => visitNode(cc) }
          c.mems.foreach { s => visitNode(s) }
          c.stages.foreach { s => visitNode(s) }
          visitNode(c.ctrlBox)
        }
      } 
      case n:Primitive => n match {
        case p:CounterChain => p.counters.foreach(c => visitNode(c))
        case p:SRAM => 
          visitNode(p.readPort)
          visitNode(p.writePort)
          visitNode(p.readAddr)
          visitNode(p.writeAddr)
          visitNode(p.swapRead)
          visitNode(p.swapWrite)
        case p:ScalarBuffer =>
          visitNode(p.readPort)
          visitNode(p.writePort)
          visitNode(p.swapRead)
          visitNode(p.swapWrite)
        case p:FIFO => 
          visitNode(p.readPort)
          visitNode(p.writePort)
          visitNode(p.enqueueEnable)
          visitNode(p.dequeueEnable)
          visitNode(p.notEmpty)
          visitNode(p.notFull)
        case p:ScalarIn =>
        case p:ScalarOut =>
        case p:VecIn =>
        case p:VecOut =>
        case p:Stage =>
        case p:UDCounter =>
        case r:PipeReg =>
        case r:ArgIn =>
        case r:ArgOut =>
        case p:Reg => 
        case p:CtrlBox =>
          p.tokenBuffers.foreach { case (dep, t) => visitNode(t) }
          p.creditBuffers.foreach { case (deped, c) => visitNode(c) }
          p.delays.foreach(visitNode)
        case p:Counter =>
        case p:Delay =>
          visitNode(p.in)
          visitNode(p.out)
      }
      case r:Const[_] =>
      case p:InPort =>
      case p:OutPort =>
      case _ => throw new Exception(s"Don't know how to visit $node")
    }
  }
}
