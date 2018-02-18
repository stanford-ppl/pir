package pir.pass

import pir._
import pir.node._

import pirc._

import scala.collection.mutable
import scala.reflect._
import pirc.util._

class MemoryControlAllocation(implicit design:PIR) extends PIRTransformer with BFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = true

  override def runPass =  {
    traverseNode(design.newTop)
  }

  def isFIFO(n:Memory) = n match {
    case n:FIFO => true
    case n:RetimingFIFO => true
    case n:StreamIn => true
    case n:StreamOut => true
    case _ => false
  }

  def allocateContextEnable(context:ComputeContext) = {
    enableOf(context).getOrElse {

    }
  }

  //override def visitNode(n:N, prev:T):T = {
    //val node = n match {
      //case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) if isFIFO(mem) =>
        //val context = contextOf(n).get
        //EnabledLoadMem(List(mem), addr, readNext).setParent(n.parent.get)
      //case Def(n:LocalStore, LocalStore(mems, addr, data)) => n
      //case n => n
    //}
    //super.visitNode(node, prev)
  //}

  override def check = {
  }

}


