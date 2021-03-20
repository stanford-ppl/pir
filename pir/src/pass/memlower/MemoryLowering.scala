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

  val invalidAddress = -1

  // Combine enable signal if more than one exists. Change offset tp invalidAddress if not enabled.
  def flattenEnable(access:Access) = dbgblk(s"flattenEnable($access)"){
    val parent = access.parent.get
    within(parent, parent.getCtrl) {
      val ens = access.en.connected
      var en:Option[Output[PIRNode]] = None
      if (ens.size > 1) {
        var red:List[Output[PIRNode]] = ens.toList
        while (red.size > 1) {
          red = red.sliding(2,2).map{ 
            case List(en1, en2) => stage(OpDef(And).addInput(en1,en2).noCost(true)).out
            case List(en1) => en1
          }.toList
        }
        dbg(s"Combine enable signals $ens => $en")
        en = Some(red.head)
      } else {
        en = ens.headOption
      }
      val addr = access match {
        case access:LockAccess => access.addr
        case access:BankedAccess => access.offset
        case access:SparseAccess => access.addr
      }
      en.foreach { en =>
        val newAddr = stage(OpDef(Mux).noCost(true).addInput(en, addr.singleConnected.get, allocConst(invalidAddress).out).out)
        addr.disconnect
        addr(newAddr)
      }
      access.en.disconnect
    }
  }

}

class MemoryLowering(implicit compiler:PIR) 
  extends GenericMemoryLowering
  with GlobalMemoryLowering
  with LocalMemoryLowering
  with LockMemoryLowering
  with SparseSRAMLowering
//  with SparseParSRAMLowering
  with SparseDRAMLowering
