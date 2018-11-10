package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class PlastisimAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal {
  import compiler.env._
  val forward = true

  override def visitNode(n:N) = {
    n match {
      case n:Context => 
        val count = n.getCount
        count.foreach { count =>
          if (n.collectDown[HostOutController]().nonEmpty) {
            count == 1
          }
        }
      case n =>
    }
    super.visitNode(n)
  }

}
