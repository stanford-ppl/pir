package pir.pass

import pir.node._
import scala.collection.mutable

class ControlAllocation(implicit compiler:PIR) extends ControlAnalysis with BFSTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = true

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N):Unit = dbgblk(s"visitNode ${qdef(n)}") {
    n match {
      case n:EnabledLoadMem => 
      case n:EnabledStoreMem => 
      case n:LocalAccess => transformAccess(n)
      case n =>
    }
    super.visitNode(n)
  }

}
