package pir
package pass

import pir.node._

import scala.collection.mutable

class AccessAnalyzer(implicit compiler:PIR) extends SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def visitNode(n:N) = { 
    n match {
      case n:Memory => analyze(n)
      case n => super.visitNode(n)
    }
  }

  def analyze(mem:Memory) = dbgblk(s"analyze($mem)") {
    val accesses = accessesOf(mem)
  }

}

