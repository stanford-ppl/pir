package pir.plasticine.traversal
import pir.plasticine.main._
import pir.plasticine.graph._
import scala.collection.mutable.Set

trait Traversal {

  val visited = Set[Node]()
  def reset {
    visited.clear
  }

  def traverse(implicit spade: Spade):Unit = {
    visitNode(spade.top)
    spade.pcus.foreach(visitNode)
    spade.mcus.foreach(visitNode)
    spade.scus.foreach(visitNode)
    spade.ocus.foreach(visitNode)
    spade.mcs.foreach(visitNode)
  } 

  def visitNode(node: Node) : Unit = {
    assert(!visited.contains(node), s"Revisiting visited node ${node}! visitedNodes:${visited}")
    node match {
      case n:Controller => 
        visitNode(n.ctrlBox)
        n match {
          case n:ComputeUnit =>
            n.srams.foreach(visitNode)
            n.ctrs.foreach(visitNode)
            n.scalarIns.foreach(visitNode)
            n.scalarOuts.foreach(visitNode)
            n.vectorIns.foreach(visitNode)
            n.vectorOuts.foreach(visitNode)
            n.stages.foreach(visitNode)
          case n:MemoryController =>
          case n:Top =>
        }
      case n:SwitchBox =>
      case n:Stage =>
        n.funcUnit.foreach(visitNode)
        n.prs.foreach(visitNode)
      case n:CtrlBox =>
        n.udcs.foreach(visitNode)
      case n =>
    }
    node match {
      case n:Module => n.ins.map(_.src).collect{case c:Const => c}.foreach(visitNode)
      case _ =>
    }
    visited += node
  }

}
