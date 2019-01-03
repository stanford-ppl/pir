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
        } else if (n.count.isEmpty) {
          n match {
            case n:GlobalIO => Some(n)
            case n:LocalAccess => Some(n)
            case n => None
          }
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
            assert(count == 1, s"Host out count != 1: $count")
          }
          n.collectDown[FringeStreamRead]().headOption.foreach { streamRead =>
            streamRead.count.v.flatten.foreach { v =>
              assert(count == v, s"StreamOut count $count != annotated count $v")
            }
          }
        }
        n.collectDown[LocalAccess]().foreach(_.getScale)
      case n =>
    }
    super.visitNode(n)
  }

}
