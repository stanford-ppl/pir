package pir

package mapper

import pir.node._
import pir.pass._
import spade.param._
import prism.collection.immutable._
import prism.util._
import prism.mapper._
import prism.graph._

case class AFGCost(prefix:Boolean=false) extends PrefixCost[AFGCost]
case class MCCost(prefix:Boolean=false) extends PrefixCost[MCCost]
case class MergeBufferCost(quantity:Int=0, ways:Int=0) extends QuantityCost[MergeBufferCost]
case class SplitterCost(quantity:Int=0) extends QuantityCost[SplitterCost]
case class LockCost(quantity:Int=0) extends QuantityCost[LockCost]
case class SRAMCost(bank:Int=0, size:Int=0) extends QuantityCost[SRAMCost]
case class FIFOCost(sfifo:Int=0, vfifo:Int=0) extends QuantityCost[FIFOCost]
case class InputCost(sin:Int=0, vin:Int=0) extends QuantityCost[InputCost]
case class OutputCost(sout:Int=0, vout:Int=0) extends QuantityCost[OutputCost]
case class StageCost(quantity:Int=0) extends QuantityCost[StageCost]
case class LaneCost(quantity:Int=1) extends MaxCost[LaneCost]
case class OpCost(set:Set[Opcode]=Set.empty) extends SetCost[Opcode,OpCost]
case class ActorCost(quantity:Int=0) extends QuantityCost[ActorCost]
case class PRCost(quantity:Int=0) extends MaxCost[PRCost]
case class PCUCost(quantity:Int=0) extends MaxCost[PCUCost]

trait CUCostUtil extends PIRPass with CostUtil with Memorization { self =>
  implicit class AnyCostOp(x:Any) {
    def getCost[C<:Cost[_]:ClassTag]:C = x match {
      case x:Parameter => self.getCost(x, classTag[C]).as[C]
      case x:CUMap.V => self.getCost(x.param, classTag[C]).as[C]
      case x => self.getCost(x, classTag[C]).as[C]
    }
  }

  def scheduleBy(s:Int, v:Int, numOp: => Int):(Int,Int) = /*dbgblk(s"scheduleBy($s, $v, $numOp)") */{
    if (spadeParam.scheduled) {
      val factor = spadeParam.vecNetParam.fold { 1 } { vecNet =>
        if (vecNet.numVC > 0) { // dynamic network
          Math.max(numOp, 1)
        } else 1
      }
      (s /! factor, v /! factor)
    } else {
      (s,v)
    }
  }

  implicit class InputCostOp(x:InputCost) {
    def scheduledBy(numOp: => Int):InputCost = {
      val (sin, vin) = scheduleBy(x.sin, x.vin, numOp)
      InputCost(sin, vin)
    }
  }

  implicit class OutputCostOp(x:OutputCost) {
    def scheduledBy(numOp: => Int):OutputCost = {
      val (sout, vout) = scheduleBy(x.sout, x.vout, numOp)
      OutputCost(sout, vout)
    }
  }

  private def getCost(x:Any, ct:ClassTag[_]) = {
    if (x.isInstanceOf[OpNode] && x.asInstanceOf[OpNode].noCost.get) {
      StageCost(0)
    //} else if (x.isInstanceOf[OpNode] && x.as[OpNode].op match FixToFix) {
      //StageCost(0)
    } else {
      memorize("getCost", (x, ct)) { case (x, ct) => compCost(x, ct) }
    }
  }

  protected def switch[C<:Cost[_]:ClassTag](x:Any, ct:ClassTag[_])(cfunc:PartialFunction[Any, C]) = if (ct == classTag[C] && cfunc.isDefinedAt(x)) Some(cfunc(x)) else None

  def isVec(n:prism.graph.IR) = {
    if (n.getTp == Bool) n.getVec > spadeParam.wordWidth
    else if (n.isInstanceOf[BufferRead] && n.as[BufferRead].deepScalar.get)
      true
    else n.getVec > 1
  }

  def stageCost(op:OpNode) = {
    if (op.noCost.get) {
      dbg(s"Node: $op free!")
      0 
    //} else if (op.isInstanceOf[Shuffle]) {
      //dbg(s"Node: $op free! (SHUFFLE)")
      //0 
    } else if (op.isInstanceOf[OpDef]) {
      op.as[OpDef].op match { 
        case FixToFix => 0
        case _ => 1
      }
    } else {
      1
    }
  }

  protected def compCost(x:Any, ct:ClassTag[_]):Cost[_] = dbgblk(s"getCost($x,${ct.toString.split("\\.").last})") {
    switch[AFGCost](x,ct) {
      case n:GlobalContainer => AFGCost(n.isInstanceOf[ArgFringe])
      case n:Parameter => AFGCost(n.isInstanceOf[ArgFringeParam])

    } orElse switch[MCCost](x,ct) {
      case n:GlobalContainer => MCCost(n.isInstanceOf[DRAMFringe])
      case n:Parameter => MCCost(n.isInstanceOf[MCParam])

    } orElse switch[MergeBufferCost](x,ct) {
      case n:GlobalContainer => 
        val mbs = n.collectDown[MergeBuffer]()
        MergeBufferCost(mbs.size, mbs.map { _.inputs.size }.sum)
      case n:Parameter => 
        val cuParam= n.to[CUParam]
        MergeBufferCost(cuParam.fold(0){_.numMergeBuffer}, cuParam.fold(0) {_.mergeBufferWays})

    } orElse switch[ActorCost](x,ct) {
      case n:MemoryContainer => ActorCost(n.collectChildren[Context].filterNot { ctx => ctx.streaming.get || ctx.follow.get}.size)
      case n:GlobalContainer => ActorCost(n.collectChildren[Context].size)
      case n:CUParam => ActorCost(n.numCtx)
      case n:ArgFringeParam => ActorCost(64)
      case n:Parameter => ActorCost(1)

    //} orElse switch[SplitterCost](x,ct) {
      //case n:GlobalContainer => 
        //val numSplitter = n.children.view.map { child =>
          //child.children.count {
            //case x:Splitter => true 
            //case x:SplitLeader => true 
            //case x:Scanner => true 
            //case x => false
          //}
        //}.reduceOption { _ + _ }.getOrElse(0)
        //SplitterCost(numSplitter)
      //case n:Parameter => SplitterCost(n.to[CUParam].fold(0){_.numSplitter})

    } orElse switch[LockCost](x,ct) {
      case n:GlobalContainer => LockCost(n.collectDown[Lock]().size)
      case n:Parameter => LockCost(n.to[CUParam].fold(0){_.numLock})

    } orElse switch[SRAMCost](x,ct) {
      case n:GlobalContainer =>
        n.descendents.collect {
          case mem:SparseMem => mem.getCost[SRAMCost]
          case mem:LockMem => mem.getCost[SRAMCost]
          case mem:SRAM => mem.getCost[SRAMCost]
          case mem:LUT => mem.getCost[SRAMCost]
          case mem:RegFile => mem.getCost[SRAMCost]
          case mem:Lock => mem.getCost[SRAMCost]
          case mem:ScratchpadDelay => mem.getCost[SRAMCost]
        }.reduceOption { _ + _ }.getOrElse(SRAMCost(0,0))
      case n:Context => 
        n.descendents.collect {
          case mem:Lock => mem.getCost[SRAMCost]
          case mem:ScratchpadDelay => mem.getCost[SRAMCost]
        }.reduceOption { _ + _ }.getOrElse(SRAMCost(0,0))
      case n:SRAM => SRAMCost(n.nBanks, n.capacity)
      case n:SparseMem => SRAMCost(n.nBanks, n.capacity)
      case n:LockMem => SRAMCost(n.nBanks, n.capacity)
      case n:LUT => SRAMCost(n.nBanks, n.capacity)
      case n:RegFile => SRAMCost(n.nBanks, n.capacity)
      case n:Lock => SRAMCost(spadeParam.vecWidth, 100)
      case n:ScratchpadDelay => SRAMCost(n.in.getVec, n.cycle * n.in.getVec)
      case n:CUParam => n.sramParam.getCost[SRAMCost]
      case n:SRAMParam => SRAMCost(n.bank, n.sizeInWord)
      case n => SRAMCost(0,0)

    } orElse switch[FIFOCost](x,ct) {
      case n:GlobalContainer => 
        val fifos = n.collectDown[FIFO]()
        val (vfifos, sfifos) = fifos.partition { isVec(_) }
        val ctxs = n.collectDown[Context]()
        val fcost = FIFOCost(sfifos.size, vfifos.size)
        ctxs.map { _.getCost[FIFOCost] }.fold(fcost) { _ + _ }
      case n:Context =>
        val fifos = n.collectDown[BufferRead]()
        val (vfifos, sfifos) = fifos.partition { isVec(_) }
        FIFOCost(sfifos.size, vfifos.size)
      case n:CUParam =>
        FIFOCost(n.fifoParamOf("word").fold(0){_.count}, n.fifoParamOf("vec").fold(0){_.count})
      case n:Parameter => FIFOCost(0,0)

    } orElse switch[InputCost](x,ct) {
      case x: GlobalContainer =>
        val ins = x.depsFrom.keys
        val (vins, sins) = ins.partition { isVec(_) }
        InputCost(sins.size, vins.size)
          .scheduledBy(x.collectDown[Context]().map { _.collectDown[OpNode]().size }.min)
      case x: Context => 
        val ins = x.collectDown[LocalOutAccess]().filter { _.in.neighbors.exists { _.parent != Some(x) } }
        val (vins, sins) = ins.partition { in => isVec(in) }
        InputCost(sins.size, vins.size)
          .scheduledBy(x.collectDown[OpNode]().size)
      case n:CUParam => InputCost(n.numSin, n.numVin)
      case n:Parameter => InputCost(100,100)

    } orElse switch[OutputCost](x,ct) {
      case x: GlobalContainer => 
        val outs = x.depedsTo.keys
        val (vouts, souts) = outs.partition { isVec(_) }
        OutputCost(souts.size, vouts.size)
          .scheduledBy(x.collectDown[Context]().map { _.collectDown[OpNode]().size }.min)
      case x: Context => 
        val outs = x.collectDown[LocalInAccess]().filter { _.out.neighbors.exists { _.parent != Some(x) } }
        val (vouts, souts) = outs.partition { out => isVec(out) }
        OutputCost(souts.size, vouts.size)
          .scheduledBy(x.collectDown[OpNode]().size)
      case n:CUParam => OutputCost(n.numSout, n.numVout)
      case n:Parameter => OutputCost(100,100)

    } orElse switch[PCUCost](x,ct) {
      case n:Context => 
        val cost = n.descendents.map {
          case x:Splitter => 6 // TEST
          case x:SplitLeader => 6 
          case x:Scanner => 6 
          case x:DataScanner => 6 
          case x:BVBuildNoTree => 6 
          case x:BVBuildTree => 6 
          case x:BVBuildTreeLen => 6 
          case _ => 0
        }.reduceOption { _ + _ }.getOrElse(0)
        PCUCost(cost)
      case n:CUParam => PCUCost(n.numPCU)
      case n:Parameter => PCUCost(0)
      case _ => PCUCost(0)
    } orElse switch[StageCost](x,ct) {
      case n:OpNode => StageCost(stageCost(n))
      case n:Context => 
        val cost = n.descendents.map {
          case n:OpNode => stageCost(n)
          case x:Splitter => 6 // TODO: set to pcu stage depth
          case x:SplitLeader => 6 // TODO: set to pcu stage depth
          case x:Scanner => 6 // TODO: set to pcu stage depth
          case x:DataScanner => 6 // TODO: set to pcu stage depth
          case x:BVBuildNoTree => 6 // TODO: set to pcu stage depth
          case x:BVBuildTree => 6 // TODO: set to pcu stage depth
          case _ => 0
        }.reduceOption { _ + _ }.getOrElse(0)
        StageCost(cost)
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[StageCost] }.reduceOption { _ + _ }.getOrElse(StageCost())
      case n:PIRNode => StageCost(0)
      case n:CUParam => 
        StageCost(n.numStage)
      case n:Parameter => StageCost(0)

    } orElse switch[LaneCost](x,ct) {
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[LaneCost] }.reduceOption { _ + _ }.getOrElse(LaneCost())
      case n:Context =>
        val lane = assertUnify(n.collectDown[Controller]().filter { _.getCtrl.isLeaf }.distinct, s"$n.lane") {
          _.getCtrl.getVec
        }.getOrElse(1)
        LaneCost(lane)
      case n:CUParam => LaneCost(n.numLane)
      case n:Parameter => LaneCost(1)

    } orElse switch[OpCost](x,ct) {
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[OpCost] }.reduceOption { _ + _ }.getOrElse(OpCost())
      case n:Context =>
        val ops = n.collectDown[OpDef]()
        OpCost(ops.map { _.op }.toSet)
      case n:CUParam => OpCost(n.ops)
      case n:Parameter => OpCost(Set.empty)

    } orElse switch[PRCost](x,ct) {
      case n:ArgFringe => PRCost(0)
      case n:DRAMFringe => PRCost(0)
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[PRCost] }.reduceOption { _ + _ }.getOrElse(PRCost())
      case n:Context =>
        val ops = n.children.filter {
          case _:OpNode => true
          case _ => false
        }
        getPRCost(n,ops)
      case n:CUParam => PRCost(n.numReg)
      case n:Parameter => PRCost(0)

    } getOrElse {
      bug(s"Don't know how to compute $ct of $x")
    }
  }

  def getPRCost(n:Any, ops:List[PIRNode]) = {
    dfsScheduler.resetTraversal
    val depFree = dfsScheduler.scheduleDepFree(ops)
    val liveOuts = depFree.flatMap { _.outs.filter { _.isConnected } }.toSet
    val (dfsReg, _) = dfsScheduler.traverseScope(ops, (liveOuts.size, liveOuts))
    val numRegs = dfsReg
    PRCost(numRegs)
  }

  private trait PRTraversal extends PIRTraversal with TopologicalTraversal {
    type T = (Int, Set[Output[PIRNode]])
    val forward = false

    def zero = (0, Set.empty)

    override def visitNode(n:N, prev:T) = {
      val defs = n.localOuts
      val (maxLives, lives) = prev
      val newLives = lives -- defs ++ n.localIns.flatMap { _.connected.filterNot {
        case OutputField(_:Const, _) => true
        case OutputField(_:Controller, _) => true // Control signals doesn't take pr
        case _ => false
      } }
      var numRegs = newLives.size
      //breakPoint(s"$n $numRegs ${newLives.map { dquote }}")
      n match {
        case _:RegAccumOp | _:RegAccumFMA => numRegs += 1
        case _ =>
      }
      val newMaxLives = math.max(maxLives, newLives.size)
      (newMaxLives, newLives)
    } 

    override def visitIn(n:N):List[N] = visitGlobalIn(n)
    override def visitOut(n:N):List[N] = visitGlobalOut(n)
  }

  private lazy val dfsScheduler = new PRTraversal with DFSTopologicalTraversal {}
  private lazy val bfsScheduler = new PRTraversal with BFSTopologicalTraversal {}

}
