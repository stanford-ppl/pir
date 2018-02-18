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

  def isReg(n:Memory) = n match {
    case n:Reg => true
    case n:ArgIn => true
    case n:ArgOut => true
    case n => false
  }

  def addNode[T<:Primitive](node:T, context:ComputeContext):T = {
    node.setParent(context)
    val ctrl = collectDown[ComputeNode](context).flatMap { comp => ctrlOf.get(comp) }.toSet[Controller].maxBy { _.ancestors.size }
    ctrlOf(node) = ctrl
    node
  }

  def allocateContextEnable(context:ComputeContext) = dbgblk(s"allocateContextEnable($context)") {
    enableOf(context).getOrElse {
      val readMems = collectIn[Memory](context)
      var enables = readMems.map { mem => 
        addNode(NotEmpty(mem),context)
      }.toList
      addNode(ContextEnable(enables),context)
    }
  }

  def allocateDelayedContextEnable(context:ComputeContext) = dbgblk(s"allocateDelayedContextEnable($context)"){
    addNode(DelayedContextEnable(allocateContextEnable(context)), context)
  }

  def innerCtrl(context:ComputeContext) = dbgblk(s"innerCtrl($context)"){
    collectDown[ComputeNode](context).map { comp => ctrlOf(comp) }.toSet[Controller].maxBy { _.ancestors.size }
  }

  def prevCtrl(ctrlChain:List[Controller], ctrl:Controller) = {
    val idx = ctrlChain.indexOf(ctrl)
    assert(idx != -1, s"$ctrlChain doesn't contains $ctrl")
    if (idx==0) None else Some(ctrlChain(idx-1))
  }

  def allocateContextDone(context:ComputeContext, ctrl:Controller):ControlNode = dbgblk(s"allocateContextDone(ctx=$context, ctrl=$ctrl)") {
    ctrl match {
      case ctrl:ArgInController => addNode(ArgInValid(),context)
      case ctrl:LoopController =>
        val counter = ctrl.cchain.counters.last
        val cchains = collectDown[CounterChain](context).filter { cchain =>
          ctrlOf(cchain) == ctrl
        }
        assert(cchains.size <= 1, s"more than 1 CounterChain for $ctrl in $context! cchains=$cchains")
        val cchain = cchains.headOption.getOrElse {
          mirror(ctrl.cchain, Some(context))
        }
        addNode(CounterDone(cchain.counters.last), context)
      case ctrl:UnitController =>
        val inner = innerCtrl(context)
        val ctrlChain = inner :: inner.ancestors
        prevCtrl(ctrlChain, ctrl).fold[ControlNode] {
          // Inner most control is UnitControl
          allocateDelayedContextEnable(context)
        } { prevCtrl => allocateContextDone(context, ctrl) }
    }
  }

  def insertGlobalIO(data:Def, access:LocalStore) = {
    val dataCtx = contextOf(data).get
    val accessCtx = contextOf(access).get
    val dataCU = globalOf(dataCtx).get
    val accessCU = globalOf(accessCtx).get
    if (dataCU == accessCU) data
    else {
      val Def(_:LocalStore, LocalStore(mem::Nil, _, _)) = access
      val valid = mem match {
        case mem if isFIFO(mem) => allocateDelayedContextEnable(dataCtx)
        case mem if isReg(mem) => allocateContextDone(dataCtx, ctrlOf(data))
      }
      val gout = addNode(GlobalOutput(data, valid), dataCtx)
      val gin = addNode(GlobalInput(gout), accessCtx)
      gin
    }
  }

  override def visitNode(n:N, prev:T):T = {
    val node = dbgblk(s"visitNode($n)") {
      n match {
        case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
          val context = contextOf(n).get
          val readNext = mem match {
            case mem if isFIFO(mem) => allocateContextEnable(context)
            case mem => allocateContextDone(context, ctrlOf(n))
          }
          swapNode(n,EnabledLoadMem(mem, addr, readNext).setParent(n.parent.get))
        case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
          val gdata = insertGlobalIO(data, n)
          val writeNext = gdata match {
            case gdata:GlobalInput => 
              assert(isReg(mem) || isFIFO(mem), s"${qdef(n)}'s data is Global")
              addNode(DataValid(gdata),contextOf(n).get)
            case gdata => allocateContextDone(contextOf(n).get, ctrlOf(n))
          }
          dbg(s"writeNext=$writeNext")
          swapNode(n,EnabledStoreMem(mem, addr, gdata, writeNext).setParent(n.parent.get))
        case n => n
      }
    }
    visited += node
    super.visitNode(node, prev)
  }

  override def check = {
  }

}


