package pir.graph.traversal
import pir.graph._
import pir.Design
import pir.Config

import scala.collection.mutable.Set

abstract class DFSTraversal(implicit val design: Design) extends Traversal{
  def traverse(node: Node) = {
    visitNode(node)
  }
  
  override def traverse = traverse(design.top)

  /* Depth first search traversal on node and their fields */
  def visitNode(node: Node) : Unit = {
    assert(!visited.contains(node), s"Revisiting visited node ${node}! visitedNodes:${visited}")
    node match {
      case n:Controller => 
        n.sins.foreach { si => visitNode(si) }
        n.souts.foreach { so => visitNode(so) }
        n.vins.foreach { vi => visitNode(vi) }
        n.vouts.foreach { vo => visitNode(vo) }
        n match {
        case c:Top => 
          c.memCtrls.foreach(n => visitNode(n))
          c.compUnits.foreach(n => visitNode(n))
        case c:ComputeUnit => {
          c.cchains.foreach { cc => visitNode(cc) }
          c.srams.foreach { s => visitNode(s) }
          c.stages.foreach {s => visitNode(s) }
          c match {
            case cu:TileTransfer =>
            case _ =>
          }
        }
        case c:MemoryController =>
      } 
      case n:Primitive => n match {
        case p:CounterChain => p.counters.foreach(c => visitNode(c))
        case p:SRAM => 
        case p:ScalarIn =>
        case p:ScalarOut =>
        case p:VecIn =>
        case p:VecOut =>
        case p:Stage =>
          p.operands.foreach(op => visitNode(op.src))
          visitNode(p.result.src)
        case p:Reg => p match {
          case r:PipeReg =>
          case r:Const =>
          case r:ArgIn =>
          case r:ArgOut =>
        }
      }
      case _ =>
        throw new Exception(s"Don't know how to visit $node")
    }
    visited += node
  }
}
