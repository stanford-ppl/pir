package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class GlobalInsertion(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with BufferAnalyzer {
  import compiler.env._

  val ctxMap = mutable.Map[ControlTree, Context]() 

  override def visitNode(n:N) = n match {
    case n:Context if n.collectDown[DRAMCommand]().nonEmpty =>
      val global = within(pirTop) { DRAMFringe() }
      swapParent(n,global)
    case n:Context if !n.isUnder[GlobalContainer] =>
      val global = within(pirTop) { ComputeContainer() }
      swapParent(n,global)
    case _ => super.visitNode(n)
  }

  override def finPass = {
    val globals = pirTop.collectDown[GlobalContainer]()
    globals.foreach(insertGlobalIO)
    super.finPass
  }

}
