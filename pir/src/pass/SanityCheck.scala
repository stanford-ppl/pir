package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

class SanityCheck(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal {

  var ctrlBlockInsertHashRun = false 
  override def initPass = {
    ctrlBlockInsertHashRun = compiler.hasRun[ControlBlockInsertion]
    super.initPass
  }

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
        if (ctrlBlockInsertHashRun) {
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
              case d:RMWAccess =>
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
        val ctrlers = n.ctrlers
        val ctrl = n.getCtrl
        val ctrls = ctrl.ancestorTree
        val externCtrlers = ctrlers.filter { ctrler => !ctrls.contains(ctrler.getCtrl) }
        if (externCtrlers.nonEmpty)
          bug(s"Unexpected ctrler $externCtrlers in ctx=${dquote(n)}")
        if (!ctrlBlockInsertHashRun) {
          val bbs = n.collectDown[BlackBox]() ++ n.collectDown[Access]()
          if (bbs.nonEmpty && ctrlers.size > 1)
            bug(s"More than one ctrler in streaming context $n $ctrlers $bbs")
        }
      case n:GlobalIO => 
        if (n.neighbors.isEmpty) err(s"$n is not connected")
      case n =>
    }
    super.visitNode(n)
  }

}
