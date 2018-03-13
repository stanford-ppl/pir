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

  def isRemoteMem(n:PIRNode)(implicit pass:PIRPass) = n match {
    case (_:SRAM | _:StreamIn | _:StreamOut)  => true
    case n:FIFO if writersOf(n).size > 1 => true
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

  def parOf(x:Controller, logger:Option[Logging])(implicit pass:PIRPass):Option[Int] = dbgblk(logger, s"parOf($x)") {
    x match {
      case x:UnitController => Some(1)
      case x:TopController => Some(1)
      case x:LoopController => parOf(x.cchain)
      case x:ArgInController => Some(1)
    }
  }

  def parOf(x:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):Option[Int] = dbgblk(logger, s"parOf($x)") {
    import pass.pirmeta._
    implicit val design = pass.design
    x match {
      case x:ArgIn => Some(1)
      case x:TokenOut => Some(1)
      case x:Counter => Some(x.par)
      case x:CounterChain => parOf(x.counters.last)
      case x:StreamIn => parOf(ctrlOf(x), logger)
      case x:StreamOut => parOf(ctrlOf(x), logger)
      case Def(n, ReduceOp(op, input)) => parOf(input, logger).map { _ / 2 }
      case Def(n, AccumOp(op, input)) => parOf(input, logger)
      case x:ComputeNode => parOf(ctrlOf(x), logger)
      case x => None
    }
  }

  def isControlMem(n:Memory) = n match {
    case n:TokenOut => true
    case StreamIn("ack") => true
    case _ => false
  }

  def memsOf(n:Any)(implicit pass:PIRPass) = {
    import pass._
    n match {
      case n:LocalStore => collectOut[Memory](n, visitFunc=visitGlobalOut, depth=2)
      case n:LocalLoad => collectIn[Memory](n, visitFunc=visitGlobalIn, depth=2)
    }
  }

  def accessNextOf(n:PIRNode)(implicit pass:PIRPass) = {
    import pass._
    n match {
      case Def(n,EnabledLoadMem(mem, addrs, readNext)) => readNext
      case Def(n,EnabledStoreMem(mem, addrs, data, writeNext)) => writeNext
    }
  }

  def writersOf(mem:Memory)(implicit pass:PIRPass):List[LocalStore] = {
    import pass._
    collectIn[LocalStore](mem, visitFunc=visitGlobalIn)
  }

  def readersOf(mem:Memory)(implicit pass:PIRPass):List[LocalLoad] = {
    import pass._
    def visitFunc(n:PIRNode):List[PIRNode] = n match {
      case n:NotEmpty => Nil
      case n:NotFull => Nil
      case n => visitGlobalOut(n)
    }
    collectIn[LocalLoad](mem, visitFunc=visitGlobalOut)
  }

  def accessesOf(mem:Memory)(implicit pass:PIRPass):List[LocalAccess] = writersOf(mem) ++ readersOf(mem)

  def bundleTypeOf(n:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):BundleType = dbgblk(logger, s"bundleTypeOf($n)") {
    implicit val design = pass.design
    n match {
      case n:ControlNode => Bit
      case n:Memory if isControlMem(n) => Bit
      case n:StreamIn if parOf(n).get == 1 => Word
      case n:StreamIn if parOf(n).get > 1 => Vector
      case n:Memory => 
        val tps = writersOf(n).map(writer => bundleTypeOf(writer, logger))
        assert(tps.size==1, s"$n.writers=${writersOf(n)} have different BundleType=$tps")
        tps.head
      case Def(n,LocalLoad(mems,_)) if isControlMem(mems.head) => Bit
      case Def(n,LocalStore(_,_,data)) => bundleTypeOf(data, logger)
      case Def(n,ValidGlobalInput(gout)) => bundleTypeOf(gout, logger)
      case Def(n,ReadyValidGlobalInput(gout, ready)) => bundleTypeOf(gout, logger)
      case Def(n,GlobalOutput(data,valid)) => bundleTypeOf(data, logger)
      case n if parOf(n, logger).get == 1 => Word
      case n if parOf(n, logger).get > 1 => Vector
      case n => throw PIRException(s"Don't know bundleTypeOf($n)")
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

  def isAFG(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("afg")
  }

  def isDFG(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    cuType(n) == Some("dfg")
  }

  def isFringe(n:GlobalContainer)(implicit pass:PIRPass):Boolean = {
    isAFG(n) || isDAG(n)
  }

  def cuType(n:PIRNode)(implicit pass:PIRPass):Option[String] = {
    import pass.{pass => _, _}
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

  def isLoadFringe(n:FringeContainer)(implicit pass:PIRPass) = {
    import pass._
    collectDown[StreamOut](n).nonEmpty
  }
  def isStoreFringe(n:FringeContainer)(implicit pass:PIRPass) = !isLoadFringe(n)

}
