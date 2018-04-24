package pir

import spade.node._

package object node extends pir.util.SpadeAlias with spade.util.PrismAlias with PIRNodeUtil {
  type PIR = pir.PIR
  type PIRPass = pir.pass.PIRPass
  type PIRMetadata = pir.util.PIRMetadata
  type PIRMap = pir.mapper.PIRMap
  type PIRApp = pir.PIRApp
  val PIRMap = pir.mapper.PIRMap

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

  def parOf(x:Controller, logger:Option[Logging]):Option[Int] = dbgblk(logger, s"parOf($x)") {
    x match {
      case x:UnitController => Some(1)
      case x:TopController => Some(1)
      case x:LoopController => parOf(x.cchain)
      case x:ArgInController => Some(1)
      case DramController(par) => Some(par)
    }
  }

  def parOf(x:PIRNode, logger:Option[Logging]=None):Option[Int] = dbgblk(logger, s"parOf($x)") {
    implicit val design = x.design.asInstanceOf[PIRDesign]
    import design.pirmeta._
    x match {
      case x:Counter => Some(x.par)
      case x:CounterChain => parOf(x.counters.last)
      case Def(n, ReduceOp(op, input)) => parOf(input, logger).map { _ / 2 }
      case Def(n, AccumOp(op, input)) => parOf(input, logger)
      case x:ComputeNode => parOf(ctrlOf(x), logger)
      case n:ComputeContext => n.collectDown[Def]().map { d => parOf(d) }.max
      case n:GlobalContainer => n.collectDown[Def]().map { d => parOf(d) }.max
      case x:Memory => throw PIRException(s"memory $x is not defined on parOf")
      case x => None
    }
  }

  def innerCtrlOf(container:Container) = {
    implicit val design = container.design.asInstanceOf[PIRDesign]
    import design.pirmeta._
    ctrlsOf(container).maxBy { _.ancestors.size }
  }

  def pinTypeOf(n:PIRNode, logger:Option[Logging]=None):ClassTag[_<:PinType] = dbgblk(logger, s"pinTypeOf($n)") {
    implicit val design = n.design.asInstanceOf[PIRDesign]
    n match {
      case n:ControlNode => classTag[Bit]
      case n:Memory if isControlMem(n) => classTag[Bit]
      case n:Memory => 
        val tps = writersOf(n).map(writer => pinTypeOf(writer, logger))
        assert(tps.size==1, s"$n.writers=${writersOf(n)} have different PinType=$tps")
        tps.head
      case Def(n,LocalLoad(mems,_)) if isControlMem(mems.head) => classTag[Bit]
      case Def(n,LocalStore(_,_,data)) => pinTypeOf(data, logger)
      case Def(n,ValidGlobalInput(gout)) => pinTypeOf(gout, logger)
      case Def(n,ReadyValidGlobalInput(gout, ready)) => pinTypeOf(gout, logger)
      case Def(n,GlobalOutput(data,valid)) => pinTypeOf(data, logger)
      case n if parOf(n, logger).get == 1 => classTag[Word]
      case n if parOf(n, logger).get > 1 => classTag[Vector]
      case n => throw PIRException(s"Don't know pinTypeOf($n)")
    }
  }

  implicit def pnodeToBct(x:PIRNode):ClassTag[_<:PinType] = pinTypeOf(x, None)

  def isPMU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("pmu")
  }

  def isSCU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("scu")
  }

  def isOCU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("ocu")
  }

  def isPCU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("pcu")
  }

  def isDAG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("dag")
  }

  def isAFG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("afg")
  }

  def isDFG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("dfg")
  }

  def isFringe(n:GlobalContainer):Boolean = {
    isAFG(n) || isDAG(n)
  }

  def cuType(n:PIRNode):Option[String] = {
    n match {
      case n:ArgFringe => Some("afg")
      case n:DramFringe => Some("dfg")
      case n:GlobalContainer if n.collectDown[Memory]().filter(isRemoteMem).nonEmpty => Some("pmu")
      case n:GlobalContainer if n.visitLocalOut[PIRNode](n).forall(_.isInstanceOf[DramFringe]) => Some("dag")
      case n:GlobalContainer if n.collectDown[StageDef]().size==0 => Some("ocu")
      case n:GlobalContainer if parOf(n) == Some(1) => Some("scu")
      case n:GlobalContainer => Some("pcu")
      case n => None
    }
  }

}
