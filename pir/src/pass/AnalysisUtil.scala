package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import scala.collection.mutable

trait AnalysisUtil { self:PIRPass =>

  def noPlaceAndRoute = spadeParam.isAsic || spadeParam.isP2P || spadeParam.isInf

  implicit class CtxUtil(ctx:Context) {
    def reads:Seq[LocalOutAccess] = ctx.collectDown[LocalOutAccess]().filterNot { _.isLocal }
    def writes:Seq[LocalInAccess] = ctx.collectDown[LocalInAccess]().filterNot { _.isLocal }
    def ctrs:Seq[Counter] = ctx.collectDown[Counter]()
    def ctrlers = ctx.collectDown[Controller]()
    def ctrler(ctrl:ControlTree) = {
      assertOneOrLess(
        ctx.ctrlers.filter { _.ctrl.get == ctrl }, 
        s"$ctx.ctrler with ($ctrl)"
      )
    }
    def getCtrler(ctrl:ControlTree) = {
      assertOne(
        ctx.ctrlers.filter { _.ctrl.get == ctrl }, 
        s"$ctx.ctrler with ($ctrl)"
      )
    }
    def dramCommands:Option[DRAMCommand] = assertOneOrLess(ctx.children.collect{ case fringe:DRAMCommand => fringe }, s"fringe in $ctx")
    def activeRate(n:Float) = ctx.getMeta[Float]("activeRate").update(n)
    def activeRate = ctx.getMeta[Float]("activeRate").v
    def stallRate(n:Float) = ctx.getMeta[Float]("stallRate").update(n)
    def stallRate = ctx.getMeta[Float]("stallRate").v
    def starveRate(n:Float) = ctx.getMeta[Float]("starveRate").update(n)
    def starveRate = ctx.getMeta[Float]("starveRate").v
  }

  implicit class IRRuntimeOp(n:PIRNode) {
    def getCtrl:ControlTree = n.ctrl.get
    def getBound:Option[Int] = n.getMeta[Option[Int]]("bound").getOrElseUpdate(boundProp(n).as[Option[Int]])

    def psimState(s:String) = n.getMeta[Float]("psimState").update(s)
    def psimState = n.getMeta[String]("psimState").v
  }

  implicit class NodeRuntimeOp[N<:IR](n:N) {
    def vecMeta:MetadataLike[Int] = n.getMeta[Int]("vec")
    def inferVec:Option[Int] = n.getMeta[Int]("vec").orElseUpdate { compVec(n) }
    def getVec:Int = n.inferVec.getOrElse(throw PIRException(s"Don't know how to infer vec of ${dquote(n)}"))
    def setVec(v:Int) = n.getMeta[Int]("vec").apply(v)
    def inferTp:Option[BitType] = n.getMeta[BitType]("tp").orElseUpdate { compType(n) }
    def getTp:BitType = n.inferTp.getOrElse(throw PIRException(s"Don't know how to infer type of ${dquote(n)}"))
    def setTp(v:BitType) = n.getMeta[BitType]("tp").apply(v)
    def setTp(v:Option[BitType]) = n.getMeta[BitType]("tp").update(v)
  }

  val StreamWriteContext = MatchRule[Context, FringeStreamWrite] { n =>
    n.collectFirstChild[FringeStreamWrite]
  }

  val StreamReadContext = MatchRule[Context, FringeStreamRead] { n =>
    n.collectFirstChild[FringeStreamRead]
  }

  val DRAMContext = MatchRule[Context, DRAMCommand] { n =>
    n.collectFirstChild[DRAMCommand]
  }

  val UnderControlBlock = MatchRule[PIRNode, ControlBlock] { n =>
    n.ancestors.collectFirst { case n:ControlBlock => n }
  }

  def boundProp(n:PIRNode):Option[Any] = dbgblk(s"boundProp($n)"){
    n match {
      case Const(v) => Some(v)
      case n:BufferRead => n.in.T.getBound
      case n:BufferWrite => n.data.T.getBound
      case n:GlobalInput => n.in.T.getBound
      case n:GlobalOutput => n.in.T.getBound
      case n => None
    }
  }

  import spade.param._
  def compVec(n:IR):Option[Int] = dbgblk(s"compVec(${dquote(n)})") {
    n match {
      case OutputField(n:Controller, "done") => Some(1)
      case OutputField(n:Controller, "childDone") => Some(1)
      case OutputField(n:Controller, "valid") => Some(1)
      case OutputField(n:LoopController, "firstIter") => Some(1)
      case OutputField(n:FringeDenseStore, "ack") => Some(1)
      case OutputField(n:FringeStreamRead, "done") => Some(1)
      case OutputField(n:PIRNode, _) if n.localOuts.size==1 => n.inferVec
      case n:Controller => None
      case n:Memory => None
      case n:PIRNode if n.presetVec.nonEmpty => n.presetVec.v
      case n:CounterIter => Some(n.is.size)
      case n:CounterValid => Some(n.is.size)
      case n:DRAMAddr => Some(1)
      case n:HostWrite => Some(1)
      case n:HostRead => Some(1)
      case Const(v:List[_]) => Some(v.size)
      case Const(v) => Some(1)
      case n:TokenWrite => Some(1)
      case n:TokenRead => Some(1)
      case n:MemWrite => n.data.inferVec
      case WithMem(n:MemRead, mem:Reg) => 
        val b = n.mem.banks.get.head
        assert(b == 1, s"Register have more than 1 bank $n $b")
        Some(1)
      //case WithMem(n:MemRead, mem:FIFO) => Some(n.getCtrl.par.get) // doesn't work for stream in
      //out under stream controller
      case WithMem(n:MemRead, mem:FIFO) => n.broadcast.v.map { _.size }.orElse(Some(n.mem.banks.get.head))
      case n:BankedWrite => zipMap(n.data.inferVec, n.offset.inferVec) { case (a,b) => Math.max(a,b) }
      case n:BankedRead => n.offset.inferVec // Before lowering
      case n:FlatBankedAccess => Some(n.mem.nBanks)
      case n:BufferWrite => n.data.inferVec
      case n:BufferRead => n.in.inferVec
      case n:RegAccumOp => Some(1)
      case n:PrintIf => n.msg.inferVec
      case n:AssertIf => n.msg.inferVec
      case n:ExitIf => n.msg.inferVec
      case n@OpDef(_:FixOp | _:FltOp | _:BitOp | _:TextOp | Mux | BitsAsData) => flatReduce(n.inputs.map{ _.inferVec}) { case (a,b) => Math.max(a,b) }
      case n:Shuffle => n.to.T.inferVec
      case n:GlobalOutput => n.in.T.inferVec
      // During staging time GlobalInput might temporarily not connect to GlobalOutput
      case n:GlobalInput => n.in.inferVec
      case InputField(n:Shuffle, "from" | "base") => zipMap(n.base.singleConnected.get.inferVec, n.from.singleConnected.get.inferVec) { case (a,b) => Math.max(a,b) }
      case n@InputField(_:LocalInAccess, "en" | "done") => n.as[Input[PIRNode]].connected.map { o => o.inferVec }.maxOption.getOrElse(Some(1))
      case InputField(n:FlatBankedAccess, "done") => Some(1)
      case InputField(n:FlatBankedAccess, field) => Some(n.mem.T.nBanks)
      case InputField(n:Controller, "en" | "parentEn") => Some(1)
      case n:Input[_] if n.isConnected && n.connected.size==1 => n.singleConnected.get.inferVec
      case n:ControlTree => if (n.children.isEmpty) Some(n.par.get) else Some(1)
      case n => None
    }
  }

  def compType(n:IR):Option[BitType] = dbgblk(s"compType(${dquote(n)})") {
    n match {
      case OutputField(n:Controller, "valid") => Some(Bool)
      case OutputField(n:Controller, "done") => Some(Bool)
      case OutputField(n:LoopController, "firstIter") => Some(Bool)
      case OutputField(n:Controller, "childDone") => Some(Bool)
      case OutputField(n:PIRNode, _) if n.localOuts.size==1 => n.inferTp
      case n:CounterIter => Some(Fix(true, 32, 0))
      case n:CounterValid => Some(Bool)
      case n:PrintIf => Some(Bool)
      case n:Shuffle => n.base.inferTp
      case n:TokenRead => Some(Bool)
      case n:TokenWrite => Some(Bool)
      case n:BufferWrite => n.data.inferTp
      case n:BufferRead => n.in.inferTp
      case n:GlobalInput => n.in.inferTp
      case n:GlobalOutput => n.in.inferTp
      case n:RegAccumOp => n.in.inferTp
      case n:MemRead => n.mem.inferTp
      case n:MemWrite => n.data.inferTp
      case n:Memory => assertOptionUnify(n.inAccesses, s"$n.type") { w => w.inferTp }
      case n@OpDef(_:BitOp) => Some(Bool)
      case n@OpDef(_:TextOp) => Some(Text)
      case n@OpDef(_:FixOp | _:FltOp) => assertUnify(n.inputs, s"$n.tp") { _.inferTp }.get
      case n@OpDef(Mux) => assertUnify(n.inputs.slice(1,3), s"$n.tp") { _.inferTp }.get
      case Const(_:Boolean) => Some(Bool)
      case Const(_:Int) => Some(Fix(true, 32, 0))
      case Const(_:Float) => Some(Flt(23, 8))
      case Const(_:String) => Some(Text)
      case Const((_:Boolean)::_) => Some(Bool)
      case Const((_:Int)::_) => Some(Fix(true, 32, 0))
      case Const((_:Float)::_) => Some(Flt(23, 8))
      case InputField(n, "en" | "parentEn" | "done") => Some(Bool)
      case n:Input[_] if n.isConnected && n.connected.size == 1 => n.singleConnected.get.inferTp
      case n:Any => None
    }
  }

  def stage[T<:PIRNode](n:T):T = dbgblk(s"stage($n)"){
    val tp = n.inferTp
    n.localIns.foreach { in => 
      in.inferVec
      in.inferTp
    }
    val vec = n.inferVec
    dbgn(n)
    n
  }

  def quoteSrcCtx(n:PIRNode) = {
    var msg = dquote(n)
    n.ctx.map { ctx => msg += s" ($ctx)"}
    msg += " " + n.srcCtx.v.getOrElse("No spatial source context")
    n.name.v.foreach { n => msg += s": $n" }
    msg
  }

  implicit class EdgeOp(x:IR) {
    def matchWith(y:IR):Boolean = {
      (x,y) match {
        case (x:Input[_], y:Input[_]) if x.connected.size != y.connected.size => false
        case (x:Input[_], y:Input[_]) => x.connected.zip(y.connected).forall { case (x,y) => x.matchWith(y) }
        case (x:Output[_],y:Output[_]) if x == y => true
        case (WithNode(Const(x)), WithNode(Const(y))) if x == y => true
        case (x,y) => false
      }
    }
  }

  def topName = if (config.asModule) config.moduleName.getOrElse(pirTop.name.get + "Top") else "Top"

  def isVecLink(n:GlobalOutput) = {
    if (n.getVec == 1) false
    else if (n.getTp == Bool && n.getVec < 32) false
    else true
  }

  def isCtrlLink(n:GlobalOutput) = {
    n.getVec == 1 && n.getTp == Bool
  }

  def mirrorSyncMeta(from:PIRNode, to:PIRNode) = {
    to.waitFors.mirror(from.waitFors)
    to.barrier.mirror(from.barrier)
  }

}

