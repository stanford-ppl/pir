package pir
package pass

import pir.node._
import scala.collection.mutable

abstract class ControlAnalysis(implicit compiler:PIR) extends PIRTransformer { self:PIRTraversal =>
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

  def allocate[T<:PIRNode:ClassTag:TypeTag](
    container:Container, 
    filter:T => Boolean = (n:T) => true
  )(newNode: => T):T = dbgblk(s"allocate(container=$container, T=${implicitly[ClassTag[T]]})"){
    val nodes = container.collectDown[T]().filter(filter)
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

  def allocateWithFields[T<:PIRNode:ClassTag:TypeTag](fields:Any*)(container:Container):T = 
    dbgblk(s"allocate(container=$container, T=${implicitly[ClassTag[T]]})"){
    val args = fields :+ design
    def newNode = {
      val constructor = implicitly[ClassTag[T]].runtimeClass.getConstructors()(0)
      constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    }
    allocate(container, (n:T) => n.values == fields)(newNode)
  }

  def insertGlobalIO(
    from:Def, 
    toCtx:ComputeContext
  )(validFunc: => ControlNode)(readyFunc: => ControlNode):Def = dbgblk(s"insertGlobalIO($from, $toCtx)") {
    val fromCtx = contextOf(from).get
    val fromCU = globalOf(fromCtx).get
    val toCU = globalOf(toCtx).get
    if (fromCU == toCU) from else {
      val gout = allocateWithFields[GlobalOutput](from,validFunc)(fromCtx)
      if (compiler.arch.designParam.topParam.busWithReady) {
        allocateWithFields[ReadyValidGlobalInput](gout, readyFunc)(toCtx)
      } else {
        allocateWithFields[ValidGlobalInput](gout)(toCtx)
      }
    }
  }

  def prevCtrl(context:ComputeContext, ctrl:Controller) = {
    val inner = innerCtrlOf(context)
    val chain = inner.ancestorSlice(ctrl).dropRight(1)
    if (chain.isEmpty) None else Some(chain.last)
  }

  def prevControllerDone(context:ComputeContext, ctrl:Controller):Option[ControlNode] = {
    prevCtrl(context, ctrl).map { prevCtrl =>
      allocateControllerDone(context, prevCtrl)
    }
  }

  def allocateControllerDone(context:ComputeContext, ctrl:Controller):ControlNode = dbgblk(s"allocateControllerDone(ctx=$context, ctrl=$ctrl)") {
    ctrl match {
      case ctrl:ForeverController => 
        allocate[ForeverControllerDone](context, x => ctrlOf(x) == ctrl){
          val done = ForeverControllerDone()
          ctrlOf(done) = ctrl
          done
        }
      case ctrl:LoopController =>
        val cchain = duplicateCounterChain(context, ctrl) 
        cchain.inner.getEnable.getOrElse {
          val en = allocateControllerEnable(context, ctrl)
          cchain.inner.setEnable(en)
        }
        allocateCounterDone(cchain.outer)
      case ctrl:ArgInController => allocateControllerEnable(context, ctrl)
      case ctrl:ArgOutController => allocateControllerEnable(context, ctrl)
      case ctrl:UnitController => allocateControllerEnable(context, ctrl)
      case top:TopController => allocateControllerEnable(context, ctrl)
      case top:DramController => 
        val en = allocateControllerEnable(context, ctrl)
        allocateWithFields[DramControllerDone](en)(context)
    }
  }

  def allocateControllerEnable(context:ComputeContext, ctrl:Controller):ControlNode = dbgblk(s"allocateControllerEnable(ctx=$context, ctrl=$ctrl)") {
    val prevDone = prevControllerDone(context, ctrl)
    prevDone.getOrElse { allocateWithFields[ContextEnableOut]()(context) }
  }

  def duplicateCounterChain(context:ComputeContext, ctrl:LoopController) = dbgblk(s"duplicateCounterChain($context, $ctrl)"){
    allocate[CounterChain](context, (cc:CounterChain) => ctrlOf(cc) == ctrl) {
      val cu = globalOf(context).get
      val cc = ctrl.cchain
      val mcc = mirror(cc, Some(context))
      ctrlOf(mcc) = ctrl 
      val mmems = mcc.collectIn[Memory]()
      val mreaders = mmems.flatMap { readersOf }
      val mwriters = mmems.flatMap { writersOf }
      dbg(s"mcc=${mcc}")
      dbg(s"mreaders=${mreaders}")
      dbg(s"mwriters=${mwriters}")
      mmems.foreach { mem =>
        swapParent(mem, cu) // Move mem out of context
      }
      mwriters.foreach { mwriter =>
        swapParent(mwriter, ComputeContext().setParent(cu))
      }
      (mwriters ++ mreaders).foreach { access =>
        visited += access
        transformAccess(access)
      }
      mcc
    }
  }

  override def mirrorX[T](x:T, mapping:mutable.Map[Any,Any]=mutable.Map.empty)(implicit design:Design):T = {
    implicit val pirdata = design.asInstanceOf[PIRDesign]
    (getOrElseUpdate(mapping, x) {
      x match {
        case x:PIRNode =>
          x match {
            case Def(n:LocalLoad, LocalLoad(mem::Nil, None)) =>
              dbgblk(s"mirrorX(${quote(x)})") { 
                val mmem = super.mirrorX(mem, mapping)
                ReadMem(mmem)
              }
            case Def(n:LocalStore, LocalStore(mem::Nil, None, Def(gin, GlobalInput(Def(gout, GlobalOutput(data, valid)))))) =>
              dbgblk(s"mirrorX(${quote(x)})") { 
                val mmem = super.mirrorX(mem, mapping)
                WriteMem(mmem, data)
              }
            case Def(n:LocalStore, LocalStore(mem::Nil, None, data)) =>
              dbgblk(s"mirrorX(${quote(x)})") { 
                val mmem = super.mirrorX(mem, mapping)
                val mdata = super.mirrorX(data, mapping)
                WriteMem(mmem, mdata)
              }
            case n => super.mirrorX(x, mapping)
          }
        case n => super.mirrorX(x, mapping)
      }
    }).asInstanceOf[T]
  }

  def getAccessNext(n:LocalAccess, mem:Memory, ctx:ComputeContext) = {
    mem match {
      case mem if isFIFO(mem) => allocateControllerEnable(ctx, ctrlOf(n))
      case mem => allocateControllerDone(ctx, topCtrlOf(n))
    }
  }

  def transformAccess(n:LocalAccess) = dbgblk(s"transformAccess ${qdef(n)}") {
    val newAccess = n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
        EnabledLoadMem(mem, addr, getAccessNext(n, mem, contextOf(n).get))
      case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
        val context = contextOf(n).get
        val gdata = insertGlobalIO(data, context) {
          // TODO: optimization. if data is constant, just duplicate it 
          val dataCtx = contextOf(data).get
          dbg(s"dataCtx=$dataCtx")
          getAccessNext(n, mem, dataCtx)
        }{
          allocateWithFields[NotFull](mem)(context)
        }
        val writeNext = gdata match {
          case gdata:GlobalInput => 
            assert(isReg(mem) || isFIFO(mem), s"${qdef(n)}'s data is Global")
            allocateWithFields[DataValid](gdata)(context)
          case gdata => 
            // writeNext could be compute locally, from data producer, or from addresser. For now always
            // compute locally
            getAccessNext(n, mem, context)
        }
        EnabledStoreMem(mem, addr, gdata, writeNext)
      case Def(n:LocalReset, LocalReset(mem::Nil, reset)) =>
        val context = contextOf(n).get
        val greset = insertGlobalIO(reset, context) {
          val resetCtx = contextOf(reset).get
          dbg(s"resetCtx=$resetCtx")
          getAccessNext(n, mem, resetCtx)
        }{
          allocateWithFields[Low]()(context)
        }
        val writeNext = greset match {
          case greset:GlobalInput => allocateWithFields[DataValid](greset)(context)
        }
        EnabledResetMem(mem, greset, writeNext)
    }
    newAccess.setParent(contextOf(n).get)
    swapNode(n, newAccess)
  }

}
