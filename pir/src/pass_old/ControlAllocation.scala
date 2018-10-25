package pir
package pass

import pir.node._
import scala.collection.mutable

class ControlAllocation(implicit compiler:PIR) extends ControlTransformer with ContextLinearizer with AccessControlUtil {
  import pirmeta._

  val forward = true

  val memories = mutable.Queue[Memory]()
  val processed = mutable.ListBuffer[Memory]()

  override def runPass = {
    memories ++= designP.top.collectDown[Memory]()
    while (memories.nonEmpty) {
      analyze(memories.dequeue)
    }
  }

  def analyze(mem:Memory) = if (!processed.contains(mem)) {
    processed += mem
    dbgblk(s"ctrlAlloc($mem)") {
      accessesOf(mem).foreach(transformAccess)
      memories ++= linearizeAccessContext(mem)
      ()
    }
  }

  def prevCtrl(ctrl:Controller) = {
    val context = currentParent.get
    val inner = innerCtrlOf(context)
    val chain = leastCommonAncesstor(inner, ctrl).get match {
      case lca if lca == ctrl =>
        inner.ancestorSlice(ctrl).dropRight(1)
      case lca if lca.style == ForkJoin => 
        val ctrlslice = ctrl.ancestorSlice(lca)
        val innerslice = inner.ancestorSlice(lca)
        dbg(s"ctrlslice = $ctrlslice")
        dbg(s"innerslice = $innerslice")
        innerslice.dropRight(ctrlslice.size)
      case lca => throw PIRException(s"$ctrl is not ancestors of $inner")
    }
    if (chain.isEmpty) None else Some(chain.last)
  }

  def prevControllerDone(ctrl:Controller):Option[ControlNode] = {
    prevCtrl(ctrl).map { prevCtrl =>
      withCtrl(prevCtrl) { allocateControllerDone(prevCtrl) }
    }
  }

  def allocateControllerDone(ctrl:Controller):ControlNode = dbgblk(s"allocateControllerDone(ctrl=$ctrl)") {
    ctrl match {
      case ctrl:ForeverController => 
        allocate[ForeverControllerDone](x => ctrlOf(x) == ctrl){
          ForeverControllerDone()
        }
      case ctrl:LoopController =>
        val cchain = duplicateCounterChain(ctrl) 
        cchain.inner.getEnable.getOrElse {
          val en = allocateControllerEnable(ctrl)
          cchain.inner.setEnable(en)
        }
        allocateWithFields[CounterDone](cchain.outer)
      case ctrl:ArgInController => allocateControllerEnable(ctrl)
      case ctrl:ArgOutController => allocateControllerEnable(ctrl)
      case ctrl:UnitController => allocateControllerEnable(ctrl)
      case top:TopController => allocateControllerEnable(ctrl)
      case top:DramController => 
        val en = allocateControllerEnable(ctrl)
        allocateWithFields[DramControllerDone](en)
    }
  }

  def allocateControllerEnable(ctrl:Controller):ControlNode = dbgblk(s"allocateControllerEnable(ctrl=$ctrl)") {
    val prevDone = prevControllerDone(ctrl)
    prevDone.getOrElse { allocateWithFields[ContextEnableOut]() }
  }

  def duplicateCounterChain(ctrl:LoopController) = dbgblk(s"duplicateCounterChain($ctrl)"){
    allocate[CounterChain]((cc:CounterChain) => ctrlOf(cc) == ctrl) {
      val context = currentParent.get
      val cu = globalOf(context).get
      val cc = ctrl.cchain
      mirror(cc)
    }
  }

  def origDataOf(n:LocalStore):Def = dataOf(n) match {
    case Def(gin, GlobalInput(Def(gout, GlobalOutput(data, valid)))) => data
    case data => data
  }
  private def origDataOf(n:LocalLoad):List[Def] = {
    val mem::Nil = memsOf(n)
    writersOf(mem).map { writer =>
      origDataOf(writer)
    }
  }

  override def mirrorX(n:N, mapping:mutable.Map[N,N])(implicit design:Design):N = {
    n match {
      case Def(n:LocalLoad, LocalLoad(mem::Nil, None)) =>
        val cu = globalOf(currentParent.get).get
        val writer::Nil = writersOf(mem)
        val data::Nil = origDataOf(n)
        dbg(s"data=${qdef(data)}")
        val readCtx = currentParent.get
        readCtx.collectDown[LocalLoad]().filter { load =>
          origDataOf(load).contains(data)
        }.headOption.getOrElse {
          val mmem = withParentCtrl(cu, ctrlOf(mem)) { 
            mirrorX(mem, mem.values).asInstanceOf[Memory]
          }
          val writeCtx = data match {
            case data:GlobalInput => 
              cu.collectDown[GlobalInput]().filter { gin => 
                goutOf(gin).get == goutOf(data).get 
              }.headOption.fold {
                withParent(cu) { ComputeContext() }
              } { gin => contextOf(gin).get }
            case data => withParent(cu) { ComputeContext() } // Haven't lowered
          }
          withParentCtrl(writeCtx, ctrlOf(writer)) {
            WriteMem(mmem, data)
          }
          val m = withParentCtrl(readCtx, ctrlOf(n)) { ReadMem(mmem) }
          memories += mmem
          pirmeta.mirror(n,m)
          originOf(m) = n
          m
        }
      case n:Memory => throw PIRException(s"Shouldn't mirror memory this way")
      case n:LocalStore => throw PIRException(s"Shouldn't mirror store this way")
      case n => super.mirrorX(n, mapping)
    }
  }

  def getAccessNext(n:LocalAccess, mem:Memory) = {
    mem match {
      case mem if isFIFO(mem) => allocateControllerEnable(ctrlOf(n))
      case mem => allocateControllerDone(getTopCtrl(n))
    }
  }

  def transformAccess(n:LocalAccess) = lowerNode(n) { dbgblk(s"lower Node ${qdef(n)}") {
    n match {
      case n:EnabledAccess => n
      case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
        withParentCtrl(contextOf(n).get, ctrlOf(n)) {
          EnabledLoadMem(mem, addr.map(assertOne(_,s"$n.addr")), getAccessNext(n, mem))
        }
      case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
        val context = contextOf(n).get
        withParentCtrl(context, ctrlOf(n)) {
          val gdata = insertGlobalIO(data, context) {
            // TODO: optimization. if data is constant, just duplicate it 
            getAccessNext(n, mem) // valid
          }{
            allocateWithFields[NotFull](mem) // ready
          }
          val writeNext = gdata match {
            case gdata:GlobalInput => allocateWithFields[DataValid](gdata) 
            case gdata => getAccessNext(n, mem)
          }
          EnabledStoreMem(mem, addr.map(assertOne(_,s"$n.addr")), gdata, writeNext)
        }
      case Def(n:LocalReset, LocalReset(mem::Nil, reset)) =>
        val context = contextOf(n).get
        withParentCtrl(context, ctrlOf(n)) {
          val greset = insertGlobalIO(reset, context) {
            getAccessNext(n, mem) // valid
          }{
            allocateWithFields[Low]() //ready
          }
          val writeNext = greset match {
            case greset:GlobalInput => allocateWithFields[DataValid](greset)
            case reset => getAccessNext(n, mem)
          }
          EnabledResetMem(mem, greset, writeNext)
        }
    }
  } }

}
