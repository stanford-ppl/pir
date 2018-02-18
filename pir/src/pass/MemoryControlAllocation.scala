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
      val readMems = collectIn[Memory](context)
      var enables = readMems.map { mem => NotEmpty(mem).setParent(context) }.toList
      ContextEnable(enables).setParent(context)
    }
  }

  override def visitNode(n:N, prev:T):T = {
    val node = n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) if isFIFO(mem) =>
        val context = contextOf(n).get
        val readNext = allocateContextEnable(context)
        swapNode(n)(EnabledLoadMem(mem, addr, readNext).setParent(n.parent.get))
      case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) if isFIFO(mem) =>
        val writeNext = DataValid().setParent(n.parent.get)
        swapNode(n)(EnabledStoreMem(mem, addr, data, writeNext).setParent(n.parent.get))
      case n => n
    }
    super.visitNode(node, prev)
  }

  override def check = {
  }

}


