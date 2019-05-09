package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import scala.collection.mutable

trait RuntimeAnalyzer extends Logging { self:PIRPass =>

  def noPlaceAndRoute = spadeParam.isAsic || spadeParam.isP2P || spadeParam.isInf

  implicit class CtxUtil(ctx:Context) {
    def reads:Seq[LocalOutAccess] = ctx.collectChildren[LocalOutAccess].filterNot { _.isLocal }
    def writes:Seq[LocalInAccess] = ctx.collectChildren[LocalInAccess].filterNot { _.isLocal }
    def ctrs:Seq[Counter] = ctx.collectDown[Counter]()
    def cb = ctx.collectFirstChild[ControlBlock]
    def ctrlers = ctx.cb.map { _.collectChildren[Controller] }.getOrElse(Nil)
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
    def scheduleFactor = ctx.getMeta[Int]("scheduleFactor").v
    def getScheduleFactor = ctx.getMeta[Int]("scheduleFactor").getOrElseUpdate(compScheduleFactor(ctx))
  }

  implicit class PIRNodeRuntimeOp(n:PIRNode) {
    def getCtrl:ControlTree = n.ctrl.get
    def getBound:Option[Int] = n.getMeta[Option[Int]]("bound").getOrElseUpdate(boundProp(n).as[Option[Int]])
    def getScale:Value[Long] = n.scale.getOrElseUpdate(compScale(n))
    def getIter:Value[Long] = n.iter.getOrElseUpdate(compIter(n))
    def getCount:Value[Long] = n.count.getOrElseUpdate(compCount(n))

    def psimState(s:String) = n.getMeta[Float]("psimState").update(s)
    def psimState = n.getMeta[String]("psimState").v
  }
  implicit class NodeRuntimeOp[N<:IR](n:N) {
    def vecMeta:MetadataLike[Int] = n.getMeta[Int]("vec")
    def inferVec:Option[Int] = n.getMeta[Int]("vec").orElseUpdate { compVec(n) }
    def getVec:Int = n.inferVec.getOrElse(throw PIRException(s"Don't know how to infer vec of $n"))
    def setVec(v:Int) = n.getMeta[Int]("vec").apply(v)
    def inferTp:Option[BitType] = n.getMeta[BitType]("tp").orElseUpdate { compType(n) }
    def getTp:BitType = n.inferTp.getOrElse(throw PIRException(s"Don't know how to infer type of $n"))
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

  def compIter(n:PIRNode):Value[Long] = dbgblk(s"compIter($n)"){
    n match {
      case n:Counter if n.isForever => Infinite
      case n:Counter if !n.isForever => 
        val min = n.min.T.get.getBound.toValue
        val max = n.max.T.get.getBound.toValue
        val step = n.step.T.get.getBound.toValue
        val par = n.par
        (max - min) /! (step * par)
      case n:LoopController =>
        n.cchain.T.map { _.getIter }.reduce { _ * _ }
      case n:Controller => Finite(1l)
      case n:FringeDenseLoad =>
        val size = n.size.T.getBound.toValue
        val dataPar = n.data.T.getVec
        size /! (n.data.getTp.bytePerWord.get * dataPar)
      case n:FringeDenseStore =>
        val size = n.size.T.getBound.toValue
        val dataPar = n.data.T.getVec
        size /! (n.data.getTp.bytePerWord.get * dataPar)
      case n:FringeSparseLoad => Finite(1l)
      case n:FringeSparseStore => Finite(1l)
      case n => Unknown
    }
  }

  def compScale(n:Any):Value[Long] = dbgblk(s"compScale($n)"){
    n match {
      case OutputField(ctrler:Controller, "done") => ctrler.getIter *  compScale(ctrler.valid)
      case OutputField(ctrler:Controller, "valid" | "childDone") => 
        val children = ctrler.valid.connected.filter { _.asInstanceOf[Field[_]].name == "parentEn" }.map { _.src.as[Controller] }
        assertUnify(children, s"$ctrler.valid.scale") { child => compScale(child.done) }.getOrElse(Finite(ctrler.ctx.get.getScheduleFactor))
      case OutputField(n:Const, _) => Finite(n.ctx.get.getScheduleFactor)
      case n:LocalAccess => 
        (n, n.done.singleConnected.get) match {
          case (n:TokenAccess, OutputField(r:BufferRead, _)) => compScale(r.inAccess.as[BufferWrite].data.singleConnected.get)
          case (n:BufferWrite, done) if n.ctx.get.streaming.get =>
            n.data.singleConnected.get match {
              case OutputField(n:FringeDenseStore, "ack") => n.getIter * n.ctx.get.getScheduleFactor
              case out => Finite(n.ctx.get.getScheduleFactor)
            }
          case (n:BufferRead, done) if n.ctx.get.streaming.get =>
            n.out.connected.head match {
              case InputField(n:DRAMDenseCommand, "size" | "offset") => n.getIter * n.ctx.get.getScheduleFactor
              case in => Finite(n.ctx.get.getScheduleFactor)
            }
          case (n, done) => compScale(done) 
        }
      case n => throw PIRException(s"Don't know how to compute scale of $n")
    }
  }

  def compScheduleFactor(n:Context):Int = dbgblk(s"compScheduleFactor($n)"){
    if (spadeParam.scheduled) {
      Math.max(n.collectDown[OpNode]().size,1)
    } else {
      1
    }
  }

  // To handle cycle in computing count
  val countStack = mutable.HashSet[Context]()

  /*
   * Compute count of the context using reads. Return None if reads is empty
   * and ctrlers nonEmpty
   * */
  def countByReads(n:Context):Option[Value[Long]] = dbgblk(s"countByReads($n)") {
    countStack += n
    var reads = n.reads
    reads = reads.filterNot { read => countStack.contains(read.inAccess.ctx.get) }
    val counts = reads.map { read => read.getCount * read.getScale }
    val (unknown, known) = counts.partition { _.unknown }
    val (finite, infinite) = known.partition { _.isFinite }
    val c = if (unknown.nonEmpty) Some(Unknown)
    else if (finite.nonEmpty) assertIdentical(finite, s"$n.reads.count reads=$reads")
    else if (infinite.nonEmpty) Some(Infinite)
    else if (n.collectFirstChild[FringeStreamWrite].nonEmpty) None
    else { // reads is empty
      val ctrlers = n.ctrlers
      if (ctrlers.isEmpty) throw PIRException(s"$n's ctrlers and reads are empty")
      else if (ctrlers.exists { _.isForever }) Some(Infinite)
      else None
    }
    countStack -= n
    c
  }

  def compCount(n:PIRNode):Value[Long] = dbgblk(s"compCount($n)"){
    n match {
      case StreamWriteContext(sw) => sw.count.v.getOrElse(throw PIRException(s"${sw.name.v.getOrElse(sw.sname.get)} is not annotated with count"))
      case n:Context =>
        val ctrlers = n.ctrlers
        if (n.streaming.get || ctrlers.exists { _.isForever }) countByReads(n).get
        else ctrlers.map { _.getIter }.reduce { _ * _ }
      case n:LocalOutAccess =>
        n.in.T.getCount
      case n:LocalInAccess =>
        n.ctx.get.getCount /! n.getScale
      case n:GlobalInput =>
        n.in.T.getCount
      case n:GlobalOutput =>
        n.in.T.getCount
      case n => throw PIRException(s"Don't know how to compute count of $n")
    }
  }

  import spade.param._
  def compVec(n:IR):Option[Int] = dbgblk(s"compVec(${dquote(n)})") {
    n match {
      case OutputField(n:Controller, "done") => Some(1)
      case OutputField(n:Controller, "childDone") => Some(1)
      case OutputField(n:Controller, "valid") => Some(1)
      case OutputField(n:FringeDenseStore, "ack") => Some(1)
      case OutputField(n:PIRNode, _) if n.localOuts.size==1 => n.inferVec
      case n:Controller => None
      case n:Memory => None
      case n:CounterIter => Some(n.is.size)
      case n:CounterValid => Some(n.is.size)
      case n:DRAMAddr => Some(1)
      case n:HostWrite => Some(1)
      case n:HostRead => Some(1)
      case Const(v:List[_]) => Some(v.size)
      case Const(v) => Some(1)
      case n:TokenWrite => Some(1)
      case n:TokenRead => Some(1)
      case n:CountAck => Some(1)
      case n:MemWrite => n.data.inferVec
      //TODO: this info should be from spatial. vec of streamOut should be bank of stream
      case n:MemRead if n.getCtrl.schedule == "Streaming" => Some(n.mem.banks.get.head) 
      case n:MemRead => n.getCtrl.inferVec
      case n:BankedWrite => zipMap(n.data.inferVec, n.offset.inferVec) { case (a,b) => Math.max(a,b) }
      case n:BankedRead => n.offset.inferVec // Before lowering
      case n:BufferWrite => n.data.inferVec
      case n:BufferRead => n.in.inferVec //TODO: consider FIFO with unequal reader and writer parallelization factor
      case n:RegAccumOp => Some(1)
      case n:PrintIf => n.msg.inferVec
      case n:AssertIf => n.msg.inferVec
      case n:ExitIf => n.msg.inferVec
      case n@OpDef(Mux) => zipMap(n.inputs(1).inferVec, n.inputs(2).inferVec) { case (a,b) => Math.max(a,b) }
      case n@OpDef(_:FixOp | _:FltOp | _:BitOp | _:TextOp | BitsAsData) => flatReduce(n.inputs.map{ _.inferVec}) { case (a,b) => Math.max(a,b) }
      case n:Shuffle => n.to.T.inferVec
      case n:GlobalOutput => n.in.T.inferVec
      // During staging time GlobalInput might temporarily not connect to GlobalOutput
      case n:GlobalInput => n.in.inferVec
      case InputField(n:Shuffle, "from" | "base") => zipMap(n.base.singleConnected.get.inferVec, n.from.singleConnected.get.inferVec) { case (a,b) => Math.max(a,b) }
      case InputField(n:BufferWrite, "en") => Some(1)
      case InputField(n:BankedAccess, "en") if n.mem.bankids.nonEmpty => Some(n.mem.nBanks)
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
      case n@OpDef(_:BitOp) => Some(Bool)
      case n@OpDef(_:TextOp) => Some(Text)
      case n@OpDef(_:FixOp | _:FltOp) => assertUnify(n.inputs, s"$n.tp") { _.inferTp }.get
      case Const(_:Boolean) => Some(Bool)
      case Const(_:Int) => Some(Fix(true, 32, 0))
      case Const(_:Float) => Some(Flt(23, 8))
      case Const((i:Int) :: _) => Some(Fix(true, 32, 0))
      case Const(_:String) => Some(Text)
      case InputField(n, "en" | "parentEn") => Some(Bool)
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


}

