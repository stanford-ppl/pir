package pir.pass

import pir._
import pir.node._

import prism._

import scala.collection.mutable
import prism.util._

class ControlAllocation(implicit compiler:PIR) extends ControlAnalysis with BFSTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = true

  override def runPass =  {
    traverseNode(compiler.top)
  }

  val doneMap = mutable.Map[ComputeContext, mutable.Map[Controller, ControlNode]]()

  override def visitNode(n:N):Unit = dbgblk(s"visitNode ${qdef(n)}") {
    n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
        val context = contextOf(n).get
        accessDoneOf(n) = mem match {
          case mem if isFIFO(mem) => allocateContextEnableOut(context)
          case mem => allocateControllerDone(context, topCtrlOf(n))
        }
        accessDoneOf.info(n).foreach(dbg)
      case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
        val context = contextOf(n).get
        val gdata = insertGlobalIO(data, context) {
          // TODO: optimization. if data is constant, just duplicate it 
          val dataCtx = contextOf(data).get
          dbg(s"dataCtx=$dataCtx")
          mem match {
            case mem if isFIFO(mem) => allocateContextEnableOut(dataCtx)
            case mem if isReg(mem) => allocateControllerDone(dataCtx, ctrlOf(data))
          }
        }
        accessDoneOf(n) = gdata match {
          case gdata:GlobalInput => 
            assert(isReg(mem) || isFIFO(mem), s"${qdef(n)}'s data is Global")
            allocate[DataValid](context, _.globalInput==gdata)(DataValid(gdata))
          case gdata => 
            // writeNext could be compute locally, from data producer, or from addresser. For now always
            // compute locally
            allocateControllerDone(context, topCtrlOf(n))
        }
        accessDoneOf.info(n).foreach(dbg)
      case n => n
    }
    super.visitNode(n)
  }

  override def check = {
  }

}


