package pir.pass

import pir._
import pir.node._

import pirc._

import scala.collection.mutable
import scala.reflect._
import pirc.util._

class ControlAllocation(implicit design:PIR) extends PIRTransformer with BFSTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = true

  override def runPass =  {
    traverseNode(design.top)
  }

  override def visitOut(n:N):List[N] = {
    n match {
      case n:Memory => Nil
      case n => super.visitOut(n)
    }
  }

  def allocateContextEnable(context:ComputeContext) = dbgblk(s"allocateContextEnable($context)") {
    allocate[ContextEnable](context) {
      val readMems = collectIn[Memory](context) // All read memories should be local to the context in the same GlobalContainer
      val writtenRemoteMems = collectOut[LocalStore](context, visitFunc=visitOut _).map {
        case Def(n, LocalStore(mem::Nil, _, _)) => (n, mem)
      }
      val writtenLocalMems = collectDown[LocalStore](context).map {
        case Def(n, LocalStore(mem::Nil, _, _)) => (n, mem)
      }
      dbg(s"readMems:${readMems.map(qtype)}")
      dbg(s"writtenRemoteMems:${writtenRemoteMems.map{ case (w, m) => (qtype(w), qtype(m)) }}")
      dbg(s"writtenLocalMems:${writtenLocalMems.map{ case (w, m) => (qtype(w), qtype(m)) }}")
      val notEmpties = readMems.map { mem => 
        allocate[NotEmpty](context, _.mem == mem)(NotEmpty(mem))
      }.toList
      val notFulls = (writtenRemoteMems ++ writtenLocalMems).flatMap { 
        case (writer, mem:ArgOut) => None
        case (writer, mem) =>
          val writerCtx = contextOf(writer).get
          val notFull = allocate[NotFull](writerCtx, _.mem == mem)(NotFull(mem))
          Some(insertGlobalIO(notFull, context)(allocate[High](writerCtx)(High())))
      }
      ContextEnable(notEmpties ++ notFulls)
    }
  }

  def allocateDelayedContextEnable(context:ComputeContext) = {
    allocate[DelayedContextEnable](context)(DelayedContextEnable(allocateContextEnable(context)))
  }

  def allocate[T<:PIRNode:ClassTag](
    context:ComputeContext, 
    filter:T => Boolean = (n:T) => true
  )(createNode: => T):T = dbgblk(s"allocate(ctx=$context, T=${implicitly[ClassTag[T]]})"){
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
          //TODO this might mirror lowered access nodes
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
        } { prevCtrl => allocateContextDone(context, prevCtrl) }
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

  override def visitNode(n:N, prev:T):T = {
    val node = dbgblk(s"visitNode($n)") {
      n match {
        case Def(n:LocalLoad, LocalLoad(mem::Nil, addr)) =>
          val context = contextOf(n).get
          val readNext = mem match {
            case mem if isFIFO(mem) => allocateContextEnable(context)
            case mem => allocateContextDone(context, topCtrlOf(n))
          }
          swapNode(n,EnabledLoadMem(mem, addr, readNext).setParent(n.parent.get))
        case Def(n:LocalStore, LocalStore(mem::Nil, addr, data)) =>
          val context = contextOf(n).get
          val gdata = insertGlobalIO(data, context) {
            val dataCtx = contextOf(data).get
            mem match {
              case mem if isFIFO(mem) => allocateDelayedContextEnable(dataCtx)
              case mem if isReg(mem) => allocateContextDone(dataCtx, ctrlOf(data))
            }
          }
          val writeNext = gdata match {
            case gdata:GlobalInput => 
              assert(isReg(mem) || isFIFO(mem), s"${qdef(n)}'s data is Global")
              allocate[DataValid](context, _.globalInput==gdata)(DataValid(gdata))
            case gdata => allocateContextDone(context, topCtrlOf(n))
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


