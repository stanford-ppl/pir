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

}

class MemoryLowering(implicit compiler:PIR) 
  extends GenericMemoryLowering
  with GlobalMemoryLowering
  with LocalMemoryLowering
  with LockMemoryLowering
