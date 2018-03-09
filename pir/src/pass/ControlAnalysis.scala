package pir.pass

import pir._
import pir.node._

import prism._
import scala.collection.mutable

abstract class ControlAnalysis(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  def allocateCounterDone(counter:Primitive) = {
    val context = contextOf(counter).get
    allocate[CounterDone](context, _.counter == counter){
      val done = CounterDone(counter)
      dbg(s"counter=$counter, counter.depeds=${counter.depeds}, done.deps=${done.deps}")
      ctrlOf(done) = ctrlOf(counter)
      done
    }
  }

  def allocate[T<:PIRNode:ClassTag](
    container:Container, 
    filter:T => Boolean = (n:T) => true
  )(newNode: => T):T = dbgblk(s"allocate(container=$container, T=${implicitly[ClassTag[T]]})"){
    val nodes = collectDown[T](container).filter(filter)
    assert(nodes.size <= 1, s"more than 1 node in container: $nodes")
    nodes.headOption.getOrElse { 
      val node = newNode 
      (node, container) match {
        case (node:Primitive, context:ComputeContext) if !ctrlOf.contains(node) =>
          ctrlOf(node) = innerCtrlOf(context)
        case node => 
      }
      node.setParent(container)
    }
  }

  def insertGlobalIO(from:Def, toCtx:ComputeContext)(validFunc: => ControlNode):Def = {
    val fromCtx = contextOf(from).get
    val fromCU = globalOf(fromCtx).get
    val toCU = globalOf(toCtx).get
    if (fromCU == toCU) return from 
    val valid = validFunc
    val gout = allocate(fromCtx, (n:GlobalOutput) => n.data==from && n.valid==valid)(GlobalOutput(from, valid))
    allocate[GlobalInput](toCtx, _.globalOutput==gout)(GlobalInput(gout))
  }

  def duplicateCounterChain(context:ComputeContext, ctrl:LoopController) = dbgblk(s"duplicateCounterChain($context, $ctrl)"){
    val cchain = ctrl.cchain
    allocate[CounterChain](context, (cc:CounterChain) => ctrlOf(cc) == ctrl) {
      val cu = globalOf(context).get
      val cc = mirror(cchain, Some(context))
      ctrlOf(cc) = ctrl // cchain might be removed
      cc.counters.foreach { ctr => ctrlOf(ctr) = ctrl }
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

  // Return the control chain of context up until max controller. exclusively
  // If max is none, return all ancesstors
  def ctrlChainOf(context:ComputeContext, max:Option[Controller]) = {
    val inner = innerCtrlOf(context)
    val ctrlChain = inner :: inner.ancestors
    val idx = max.map { max => ctrlChain.indexOf(max) }.getOrElse(ctrlChain.size)
    assert(idx != -1, s"$max is not ancestor of the inner most control $inner in $context")
    ctrlChain.slice(0, idx)
  }

  def prevCtrl(context:ComputeContext, ctrl:Controller) = {
    val chain = ctrlChainOf(context, max=Some(ctrl))
    if (chain.isEmpty) None else Some(chain.last)
  }

  def allocateContextEnableOut(context:ComputeContext):ContextEnableOut = dbgblk(s"allocateContextEnableOut($context)") {
    allocate[ContextEnableOut](context) { ContextEnableOut() }
  }

  def allocateControllerDone(context:ComputeContext, ctrl:Controller):ControlNode = dbgblk(s"allocateControllerDone(ctx=$context, ctrl=$ctrl)") {
    val prevDone = prevCtrl(context, ctrl).map { prevCtrl =>
      allocateControllerDone(context, prevCtrl)
    }
    ctrl match {
      case ctrl:ArgInController => allocate[ArgInValid](context)(ArgInValid())
      case ctrl:LoopController =>
        val cchain = duplicateCounterChain(context, ctrl) 
        enableOf(cchain) = prevDone match {
          case Some(done) => done
          case None => allocateContextEnableOut(context)
        }
        allocateCounterDone(cchain.counters.last)
      case ctrl:UnitController =>
        // If UnitControl is the inner most control, the enable is the done, otherwise it's previous
        // control's done
        prevDone.fold[ControlNode] { allocateContextEnableOut(context) } { prevDone => prevDone }
      case top:TopController => prevDone.get
    }
  }


}
