package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._
import scala.collection.mutable

class ScanLongOpSplitter(implicit compiler:PIR) extends PIRTraversal with BFSBottomUpTopologicalTraversal with UnitTraversal with PIRTransformer {
  
  val forward:Boolean = true
  val ctxMap = mutable.Map[ControlTree, Context]() 

  def getDeps(n:N):List[N] = {
    val d = super.depFunc(n).filter { _ match {
        //case _:Context => false
        //case _:Memory => false
        case _ => true
      }
    }
    d ++ d.flatMap(getDeps)
  }

  def getDepeds(n:N):List[N] = {
    val d = super.depedFunc(n).filter { _ match {
        //case _:Context => false
        //case _:Memory => false
        case _ => true
      }
    }
    d ++ d.flatMap(getDepeds)
  }
  
  // Split on DRAM RMW, DRAM SpRead, DRAM SpWrite
  // Also split on DRAM Load, DRAM Store
  //
  // Look at downstream nodes and build a list of scanners. Take the
  // scanners, duplicate them, and build a mapping table. Then,
  // replace all connections to the old scanners with new ones.
  override def visitNode(n:N) = {
    n match {
      case SparseRead() | SparseWrite() | SparseRMW(_,_,_,_) =>
        dbg(s"Sparse op: $n")
        dbg(s"deps: ${getDeps(n)}")
        dbg(s"depeds: ${getDepeds(n)}")
      case _ =>
        dbg(s"other: $n")
        super.visitNode(n)
    }
  }
}
