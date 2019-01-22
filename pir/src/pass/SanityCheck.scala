package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class SanityCheck(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal {
  import compiler.env._

  override def visitNode(n:N) = {
    n match {
      case n:Top => 
        n.children.foreach { 
          case c:GlobalContainer =>
          case c => err(s"child of $n is $c")
        }
      case n:GlobalContainer =>
        n.children.foreach { 
          case c:Memory =>
          case c:Context =>
          case c:GlobalIO =>
          case c => err(s"child of $n is $c")
        }
        n.depsFrom.foreach { case (depOut, depsFrom) =>
          depOut.src match {
            case d:GlobalOutput =>
            case d => err(s"$n input from ${d}.$depOut")
          }
          depsFrom.foreach { in =>
            in.src match {
              case f:GlobalInput =>
              case f => err(s"$n input at ${f}.$in")
            }
          }
        }
      case n:Context =>
        n.depsFrom.foreach { case (depOut, depsFrom) =>
          depOut.src match {
            case d:LocalInAccess =>
            case d:GlobalInput =>
            case d:Memory =>
            case d => err(s"$n input from ${d}.$depOut")
          }
          depsFrom.foreach { in =>
            in.src match {
              case f:LocalOutAccess =>
              case f:OutAccess =>
              case f => err(s"$n input at ${f}.$in")
            }
          }
        }
        n.depedsTo.foreach { case (out, depedsIn) =>
          out.src match {
            case d:LocalInAccess =>
            case d:InAccess =>
            case d => err(s"$n output at ${d}.$out")
          }
          depedsIn.foreach { in =>
            in.src match {
              case f:LocalOutAccess =>
              case f:GlobalOutput =>
              case f:Memory =>
              case f => err(s"$n input to ${f}.$in")
            }
          }
        }
      case n:GlobalIO => 
        if (n.neighbors.isEmpty) err(s"$n is not connected")
      case n =>
    }
    super.visitNode(n)
  }

}
