package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import scala.collection.mutable

class PlastisimAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal {
  import compiler.env._
  val forward = true

  override def accountForCycle = compiler.hasRun[PlastisimAnalyzer]

  override def runPass = {
    if (accountForCycle) {
      val resets = pirTop.descendents.flatMap { d =>
        val n = d.as[PIRNode]
        val count = n.count
        if (count.v.nonEmpty && count.get == None) {
          dbg(s"Reset Count for $n...")
          count.reset
          Some(n)
        } else None
      }
      resets.foreach { _.getCount }
    } else super.runPass
  }

  override def visitNode(n:N) = {
    n match {
      case n:Context => 
        val count = n.getCount
        count.foreach { count =>
          if (n.collectDown[HostOutController]().nonEmpty) {
            count == 1
          }
        }
        n.collectDown[LocalAccess]().foreach(_.getScale)
      case n =>
    }
    super.visitNode(n)
  }

}
