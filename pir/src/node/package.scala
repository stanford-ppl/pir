package pir

import spade.node._

package object node extends pir.util.SpadeAlias with spade.util.PrismAlias {
  type PIR = pir.PIR
  type PIRPass = pir.pass.PIRPass
  type PIRMetadata = pir.util.PIRMetadata
  type PIRMap = pir.mapper.PIRMap
  type PIRApp = pir.PIRApp
  val PIRMap = pir.mapper.PIRMap

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
      case n:ComputeContext => n.collectDown[Def]().map { d => parOf(d) }.max
      case n:GlobalContainer => n.collectDown[Def]().map { d => parOf(d) }.max
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
      case n:LocalStore => n.collect[Memory](visitFunc=n.visitGlobalOut, depth=2)
      case n:LocalLoad => n.collect[Memory](visitFunc=n.visitGlobalIn, depth=2)
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
    mem.collect[LocalStore](visitFunc=mem.visitGlobalIn)
  }

  def readersOf(mem:Memory)(implicit pass:PIRPass):List[LocalLoad] = {
    import pass._
    def visitFunc(n:PIRNode):List[PIRNode] = n match {
      case n:NotEmpty => Nil
      case n:NotFull => Nil
      case n => n.visitGlobalOut(n)
    }
    mem.collect[LocalLoad](visitFunc={ 
      case n:NotEmpty => Nil
      case n:NotFull => Nil
      case n => mem.visitGlobalOut(n) 
    })
  }

  def accessesOf(mem:Memory)(implicit pass:PIRPass):List[LocalAccess] = writersOf(mem) ++ readersOf(mem)

  def globalOf(n:PIRNode) = {
    n.collectUp[GlobalContainer]().headOption
  }

  def contextOf(n:PIRNode) = {
    n.collectUp[ComputeContext]().headOption
  }

  def ctrlsOf(container:Container)(implicit pass:PIRPass) = {
    import pass.pirmeta._
    container.collectDown[ComputeNode]().flatMap { comp => ctrlOf.get(comp) }.toSet[Controller]
  }

  def innerCtrlOf(container:Container)(implicit pass:PIRPass) = {
    import pass.pirmeta._
    ctrlsOf(container).maxBy { _.ancestors.size }
  }

  def ctxEnOf(n:Container):Option[ContextEnable] = {
    n.collectDown[ContextEnable]().headOption
  }

  def goutOf(gin:GlobalInput) = {
    gin.collect[GlobalOutput](visitFunc=gin.visitGlobalIn).headOption
  }

  def ginsOf(gout:GlobalOutput) = {
    gout.collect[GlobalInput](visitFunc=gout.visitGlobalOut).toList
  }

  def connectedOf(gio:GlobalIO) = gio match {
    case gio:GlobalInput => goutOf(gio).toList
    case gio:GlobalOutput => ginsOf(gio)
  }

  def isGlobalInput(gin:GlobalIO) = gin match {
    case gin:GlobalInput => true
    case gout:GlobalOutput => false
  }

  def isGlobalOutput(gin:GlobalIO) = gin match {
    case gin:GlobalInput => false
    case gout:GlobalOutput => true
  }

  def pinTypeOf(n:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):ClassTag[_<:PinType] = dbgblk(logger, s"pinTypeOf($n)") {
    implicit val design = pass.design
    n match {
      case n:ControlNode => classTag[Bit]
      case n:Memory if isControlMem(n) => classTag[Bit]
      case n:StreamIn if parOf(n).get == 1 => classTag[Word]
      case n:StreamIn if parOf(n).get > 1 => classTag[Vector]
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

  implicit def pnodeToBct(x:PIRNode)(implicit pass:PIRPass):ClassTag[_<:PinType] = pinTypeOf(x, None)

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
    n match {
      case n:ArgFringe => Some("afg")
      case n:FringeContainer => Some("dfg")
      case n:GlobalContainer if n.collectDown[Memory]().filter(isRemoteMem).nonEmpty => Some("pmu")
      case n:GlobalContainer if n.collect[StreamOut](visitFunc=n.visitGlobalOut, depth=5).filter { stream => parOf(stream) == Some(1) }.nonEmpty => Some("dag")
      case n:GlobalContainer if n.collectDown[StageDef]().size==0 => Some("ocu")
      case n:GlobalContainer if parOf(n) == Some(1) => Some("scu")
      case n:GlobalContainer => Some("pcu")
      case n => None
    }
  }

  def isLoadFringe(n:GlobalContainer)(implicit pass:PIRPass) = n.collectDown[StreamIn]().filter{ _.field == "data" }.nonEmpty
  def isStoreFringe(n:GlobalContainer)(implicit pass:PIRPass) = n.collectDown[StreamOut]().filter{ _.field == "data" }.nonEmpty
}
