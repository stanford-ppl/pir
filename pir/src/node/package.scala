package pir

import spade.SpadeEnums 

import prism._
import prism.util._

package object node extends SpadeEnums {
  private[node] type Design = PIRDesign

  def isFIFO(n:PIRNode) = n match {
    case n:FIFO => true
    case n:RetimingFIFO => true
    case n:StreamIn => true
    case n:StreamOut => true
    case _ => false
  }

  def isReg(n:PIRNode) = n match {
    case n:Reg => true
    case n:ArgIn => true
    case n:ArgOut => true
    case n:TokenOut => true
    case n => false
  }

  def isRemoteMem(n:PIRNode) = n match {
    case (_:SRAM | _:StreamIn | _:StreamOut)  => true
    case n:FIFO if n.writers.size > 1 => true
    case n:RegFile => true
    case _ => false
  }

  def isCounter(n:PIRNode) = n match {
    case n:Counter => true
    case n:EnabledCounter => true
    case _ => false
  }

  def isReduceOp(n:PIRNode) = n match {
    case n:ReduceAccumOp => true
    case n:ReduceOp => true
    case n => false
  }

  def withinGlobal(n:PIRNode) = within[GlobalContainer](n)

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

  def parOf(x:Any, logger:Option[Logging]=None)(implicit design:Design):Option[Int] = dbgblk(logger, s"parOf($x)") {
    import design.pirmeta._
    x match {
      case x:ArgIn => Some(1)
      case x:TokenOut => Some(1)
      case x:UnitController => Some(1)
      case x:TopController => Some(1)
      case x:ArgInController => Some(1)
      case x:Counter => Some(x.par)
      case x:CounterChain => parOf(x.counters.last)
      case x:LoopController => parOf(x.cchain)
      case x:ComputeNode => parOf(ctrlOf(x))
      case x:StreamIn => parOf(ctrlOf(x))
      case x:StreamOut => parOf(ctrlOf(x))
      case x => None
    }
  }

  def isControlMem(n:Memory) = n match {
    case n:TokenOut => true
    case StreamIn("ack") => true
    case _ => false
  }

  def bundleTypeOf(n:PIRNode, logger:Option[Logging]=None)(implicit design:Design):BundleType = dbgblk(logger, s"bundleTypeOf($n)") {
    n match {
      case n:ControlNode => Bit
      case n:Memory if isControlMem(n) => Bit
      case n:StreamIn if parOf(n).get == 1 => Word
      case n:StreamIn if parOf(n).get > 1 => Vector
      case n:Memory => 
        val tps = n.writers.map(writer => bundleTypeOf(writer, logger))
        assert(tps.size==1, s"$n.writers=${n.writers} have different BundleType=$tps")
        tps.head
      case Def(n,LocalLoad(mems,_)) if isControlMem(mems.head) => Bit
      case Def(n,LocalStore(_,_,data)) => bundleTypeOf(data, logger)
      case Def(n,GlobalInput(gout)) => bundleTypeOf(gout, logger)
      case Def(n,GlobalOutput(data, valid)) => bundleTypeOf(data, logger)
      case n if parOf(n, logger).get == 1 => Word
      case n if parOf(n, logger).get > 1 => Vector
    }
  }

  def isPMU(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("pmu")
  }

  def isSCU(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("scu")
  }

  def isOCU(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("ocu")
  }

  def isPCU(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("pcu")
  }

  def isDAG(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("dag")
  }

  def isFringe(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("afg") || cuType(n) == Some("dfg")
  }

  def cuType(n:PIRNode)(implicit pass:PIRPass):Option[String] = {
    import pass._
    n match {
      case n:ArgFringe => Some("afg")
      case n:FringeContainer => Some("dfg")
      case n:GlobalContainer if collectDown[Memory](n).filter(isRemoteMem).nonEmpty => Some("pmu")
      case n:GlobalContainer if collectOut[StreamOut](n, visitFunc=visitGlobalOut, depth=5).filter { stream => parOf(stream) == Some(1) }.nonEmpty => Some("dag")
      case n:GlobalContainer if collectDown[StageDef](n).size==0 => Some("ocu")
      case n:GlobalContainer if collectDown[Def](n).forall { s => parOf(s)==Some(1) } => Some("scu")
      case n:GlobalContainer => Some("pcu")
      case n => None
    }
  }
}
