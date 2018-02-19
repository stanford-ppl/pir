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
    traverseNode(design.top)
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

  def allocateContextEnable(context:ComputeContext) = dbgblk(s"allocateContextEnable($context)") {
    allocate[ContextEnable](context) {
      val readMems = collectIn[Memory](context)
      var enables = readMems.map { mem => 
        allocate[NotEmpty](context, _.mem == mem)(NotEmpty(mem))
      }.toList
      ContextEnable(enables)
    }
  }

  def allocateDelayedContextEnable(context:ComputeContext) = {
    allocate[DelayedContextEnable](context)(DelayedContextEnable(allocateContextEnable(context)))
  }

  def allocate[T<:PIRNode:ClassTag](context:ComputeContext, filter:T => Boolean = (n:T) => true)(createNode: => T):T = dbgblk(s"allocate(ctx=$context)"){
    val nodes = collectDown[T](context).filter(filter)
    assert(nodes.size <= 1, s"more than 1 node in context: $nodes")
    nodes.headOption.getOrElse { 
      val node = createNode 
      node match {
        case node:Primitive if !ctrlOf.contains(node) =>
          ctrlOf(node) = innerCtrlOf(context)
        case node => 
      }
      node.setParent(context)
    }
  }

  def prevCtrl(ctrlChain:List[Controller], ctrl:Controller) = {
    val idx = ctrlChain.indexOf(ctrl)
    assert(idx != -1, s"$ctrlChain doesn't contains $ctrl")
    if (idx==0) None else Some(ctrlChain(idx-1))
  }

  def allocateContextDone(context:ComputeContext, ctrl:Controller):ControlNode = dbgblk(s"allocateContextDone(ctx=$context, ctrl=$ctrl)") {
    ctrl match {
      case ctrl:ArgInController => allocate[ArgInValid](context)(ArgInValid())
      case ctrl:LoopController =>
        val cchain = allocate[CounterChain](context, (cc:CounterChain) => ctrlOf(cc) == ctrl) {
          mirror(ctrl.cchain, Some(context))
        }
        val counter = cchain.counters.last
        allocate[CounterDone](context, _.counter == counter){
          val done = CounterDone(counter)
          ctrlOf(done) = ctrlOf(counter)
          done
        }
      case ctrl:UnitController =>
        val inner = innerCtrlOf(context)
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
      val gout = allocate(dataCtx, (n:GlobalOutput) => n.data==data && n.valid==valid)(GlobalOutput(data, valid))
      val gin = allocate[GlobalInput](accessCtx, _.globalOutput==gout)(GlobalInput(gout))
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
          val context = contextOf(n).get
          val gdata = insertGlobalIO(data, n)
          val writeNext = gdata match {
            case gdata:GlobalInput => 
              assert(isReg(mem) || isFIFO(mem), s"${qdef(n)}'s data is Global")
              allocate[DataValid](context, _.globalInput==gdata)(DataValid(gdata))
            case gdata => allocateContextDone(context, ctrlOf(n))
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


