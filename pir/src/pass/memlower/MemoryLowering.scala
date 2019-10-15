package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait GenericMemoryLowering extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer with DependencyAnalyzer with CUCostUtil {
  override def visitNode(n:N) = n match {
    case n:Memory => err(s"Don't know how to lower memory ${dquote(n)}")
    case _ => super.visitNode(n)
  }

  // And enable signals
  def flattenEnable(access:Access) = dbgblk(s"flattenEnable($access)"){
    val parent = access.parent.get
    within(parent, parent.getCtrl) {
      val ens = access.en.connected
      if (ens.size > 1) {
        var red:List[Output[PIRNode]] = ens.toList
        while (red.size > 1) {
          red = red.sliding(2,2).map{ 
            case List(en1, en2) => stage(OpDef(And).addInput(en1,en2)).out
            case List(en1) => en1
          }.toList
        }
        val en = red.head
        dbg(s"And enable signals $ens => $en")
        access.en.disconnect
        access.en(en)
      }
    }
  }

    // Insert token for sequencial control dependency
  def sequencedScheduleBarrierInsertion(mem:Memory):Unit = {
    if (mem.isFIFO) return
    dbgblk(s"sequencedScheduleBarrierInsertion($mem)") {
      val ctrls = mem.accesses.toStream.flatMap { a => a.getCtrl.ancestorTree }.distinct
      ctrls.foreach { ctrl =>
        if (ctrl.schedule == Sequenced) {
          val accesses = ctrl.children.flatMap { childCtrl => 
            val childAccesses = mem.accesses.filter { a => 
              a.getCtrl.isDescendentOf(childCtrl) || a.getCtrl == childCtrl
            }
            if (childAccesses.nonEmpty) Some((childCtrl, childAccesses)) else None
          }
          if (accesses.nonEmpty) {
            dbgblk(s"Insert token for sequenced schedule of $ctrl") {
              accesses.sliding(2, 1).foreach{
                case List((fromCtrl, from), (toCtrl, to)) =>
                  from.foreach { fromAccess =>
                    to.foreach { toAccess =>
                      dbg(s"Insert token between $fromAccess ($fromCtrl) and $toAccess ($toCtrl)")
                      insertToken(fromAccess.ctx.get, toAccess.ctx.get).depth(1)
                    }
                  }
                case _ =>
              }
            }
          }
        }
      }
    }
  }

  //def accessBarrierInsertion(mem:Memory):Unit = dbgblk(s"accessBarrierInsertion($mem)"){
    //val accesses = mem.accesses.sortBy { _.order.get } // Access sorted by program order
    //accesses.sliding(2,1).foreach { case (from, to) =>
      //val fromCtrl = from.getCtrl
      //val toCtrl = to.getCtrl
    //}
  //}
}

class MemoryLowering(implicit compiler:PIR) 
  extends GenericMemoryLowering
  with GlobalMemoryLowering
  with LocalMemoryLowering
  with LockMemoryLowering
  with LockMemoryBackBoxLowering
