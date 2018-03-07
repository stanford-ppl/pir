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

  def allocateContextEnable(context:ComputeContext):ContextEnableOut = dbgblk(s"allocateContextEnable($context)") {
    allocate[ContextEnableOut](context) { ContextEnableOut() }
  }

  // Return the control chain of context up until max controller.
  def ctrlChain(context:ComputeContext, max:Controller) = {
    val inner = innerCtrlOf(context)
    val ctrlChain = inner :: inner.ancestors
    val idx = ctrlChain.indexOf(max)
    assert(idx != -1, s"$max is not ancestor of the inner most control $inner in $context")
    ctrlChain.slice(0, idx+1)
  }

  def prevCtrl(context:ComputeContext, ctrl:Controller) = {
    val chain = ctrlChain(context, ctrl).dropRight(1) // The last one is ctrl
    if (chain.isEmpty) None else Some(chain.last)
  }

  val doneMap = mutable.Map[ComputeContext, mutable.Map[Controller, ControlNode]]()

  def duplicateCounterChain(context:ComputeContext, cchain:CounterChain, enable:Def) = dbgblk(s"duplicateCounterChain($context, $cchain)"){
    val ctrl = ctrlOf(cchain)
    allocate[CounterChain](context, (cc:CounterChain) => ctrlOf(cc) == ctrl) {
      val cu = globalOf(context).get
      val cc = mirror(cchain, Some(context), mirrorRule=PresetRule(cchain, enableOf, enable) )
      val readers = collectIn[LocalLoad](cc)
      val mems = collectIn[Memory](cc)
      mems.foreach { mem =>
        swapParent(mem, cu) // Move mem out of context
      }
      val writers = mems.flatMap { _.writers }
      writers.foreach { writer =>
        swapParent(writer, ComputeContext().setParent(cu))
      }
      cc
    }
  }


  def allocateControllerDone(context:ComputeContext, ctrl:Controller):ControlNode = dbgblk(s"allocateControllerDone(ctx=$context, ctrl=$ctrl)") {
    val prevDone = prevCtrl(context, ctrl).map { prevCtrl =>
      allocateControllerDone(context, prevCtrl)
    }
    ctrl match {
      case ctrl:ArgInController => allocate[ArgInValid](context)(ArgInValid())
      case ctrl:LoopController =>
        val enable = prevDone match {
          case Some(done) => done
          case None => allocateContextEnable(context)
        }
        val cchain = duplicateCounterChain(context, ctrl.cchain, enable) 
        allocateCounterDone(cchain.counters.last)
      case ctrl:UnitController =>
        // If UnitControl is the inner most control, the enable is the done, otherwise it's previous
        // control's done
        prevDone.fold[ControlNode] { allocateContextEnable(context) } { prevDone => prevDone }
    }
  }

  override def visitNode(n:N):Unit = dbgblk(s"visitNode ${qdef(n)}") {
    n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
        val context = contextOf(n).get
        accessDoneOf(n) = mem match {
          case mem if isFIFO(mem) => allocateContextEnable(context)
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
            case mem if isFIFO(mem) => allocateContextEnable(dataCtx)
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


