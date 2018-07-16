package pir
package pass

import pir.node._
import scala.collection.mutable

class ControlAllocation(implicit compiler:PIR) extends ControlTransformer with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  val forward = true

  override def visitNode(n:N):Unit = {
    n match {
      case n:LocalAccess => super.visitNode(transformAccess(n))
      case n => super.visitNode(n)
    }
  }

  def prevCtrl(ctrl:Controller) = {
    val context = currentParent.get
    val inner = innerCtrlOf(context)
    val chain = inner.ancestorSlice(ctrl).dropRight(1)
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

  override def mirrorX(n:N, mapping:mutable.Map[N,N])(implicit design:Design):N = {
    n match {
      case n:Memory =>
        val cu = globalOf(currentParent.get).get
        withParent(cu) { super.mirrorX(n, mapping) }
      case Def(n:LocalLoad, LocalLoad(mem::Nil, None)) =>
        val mmem = mirror(mem, mapping)
        mapping.getOrElseUpdate(n, {
          readersOf(mmem).filter { _.isDescendentOf(currentParent.get) }.headOption.getOrElse {
            val m = ReadMem(mmem)
            pirmeta.mirror(n,m)
            originOf(m) = n
            queue += m // TODO:HACK top down traversal doen't automatically traverse the newly created nodes yet
            m
          }
        })
      case Def(n:LocalStore, LocalStore(mem::Nil, None, data)) =>
        val origData = data match {
          case Def(gin, GlobalInput(Def(gout, GlobalOutput(data, valid)))) => data
          case data => data // mirroring not lowered node
        }
        val mmem = mirror(mem, mapping)
        val cu = globalOf(currentParent.get).get
        mapping.getOrElseUpdate(n, {
          val mwriter = writersOf(mmem).headOption
          dbg(s"mwriter=$mwriter")
          mwriter.getOrElse {
            withParent(cu) {
              withParent(ComputeContext()) {
                val m = WriteMem(mmem, origData)
                pirmeta.mirror(n,m)
                originOf(m) = n
                queue += m
                m
              }
            }
          }
        })
      case n => super.mirrorX(n, mapping)
    }
  }

  def getAccessNext(n:LocalAccess, mem:Memory) = {
    mem match {
      case mem if isFIFO(mem) => allocateControllerEnable(ctrlOf(n))
      case mem => allocateControllerDone(topCtrlOf(n))
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
            case gdata:GlobalInput => 
              assert(isReg(mem) || isFIFO(mem), s"${qdef(n)}'s data is Global")
              allocateWithFields[DataValid](gdata) 
            case gdata => 
              // writeNext could be compute locally, from data producer, or from addresser. For now always
              // compute locally
              getAccessNext(n, mem)
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
          }
          EnabledResetMem(mem, greset, writeNext)
        }
    }
  } }

}
