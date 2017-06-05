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
    spade match {
      case spade:SwitchNetwork => spade.sbs.foreach(visitNode)
      case _ =>
    }
  } 

  def visitNode(node: Node) : Unit = {
    //assert(!visited.contains(node), s"Revisiting visited node ${node}! visitedNodes:${visited}")
    if (visited.contains(node)) return
    visited += node
    node match {
      case n:NetworkElement =>
        n.gridIOs.foreach{_.ios.foreach(visitNode)}
        n match {
          case n:Controller => 
            visitNode(n.ctrlBox)
            n match {
              case n:ComputeUnit =>
                n.srams.foreach(visitNode)
                n.ctrs.foreach(visitNode)
                n.sbufs.foreach(visitNode)
                n.vbufs.foreach(visitNode)
                n.stages.foreach(visitNode)
              case n:MemoryController =>
                n.sbufs.foreach(visitNode)
                n.vbufs.foreach(visitNode)
              case n:Top =>
            }
          case n:SwitchBox =>
        }
      case n:GlobalIO[_,_] => visitNode(n.ic)
      case n:Stage =>
        n.funcUnit.foreach(visitNode)
        n.prs.foreach(visitNode)
      case n:TopCtrlBox => 
      case n:CtrlBox =>
        n.udcs.foreach(visitNode)
        n.andTrees.foreach(visitNode)
        n.andGates.foreach(visitNode)
        n.delays.foreach(visitNode)
        n match {
          case n:OuterCtrlBox => 
            visitNode(n.pulserSM)
          case n =>
        }
      case n =>
    }
    node match {
      case n:Module => n.ins.map(_.src).collect{case c:Const[_] => c}.foreach(visitNode)
      case _ =>
    }
  }

}
