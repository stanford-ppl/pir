package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class GlobalInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer {

  val ctxMap = mutable.Map[ControlTree, Context]() 

  override def visitNode(n:N) = n match {
    case n:Context if !n.isUnder[GlobalContainer] =>
      val global = within(pirTop) { 
        n match {
          case n if n.hasDescendent[DRAMCommand,StreamCommand] => DRAMFringe()
          case n if n.hasDescendent[GlobalBlackBox] => BlackBoxContainer()
          case n => stage(ComputeContainer().name.mirror(n.name))
        }
      }
      swapParent(n,global)
    case _ => super.visitNode(n)
  }

}
