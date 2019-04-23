package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import spade.param.{FixOp, FltOp, BitOp}
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
    def inferVec:Option[Int] = n.getMeta[Int]("vec").orElseUpdate { compVec(n) }
    def getVec:Int = n.inferVec.getOrElse(throw PIRException(s"Don't know how to infer vec of $n"))
    def setVec(v:Int) = n.getMeta[Int]("vec").apply(v)
    def inferTp:Option[BitType] = n.getMeta[BitType]("tp").orElseUpdate { compType(n) }
    def getTp:BitType = n.inferTp.getOrElse(throw PIRException(s"Don't know how to infer type of $n"))
    def setTp(v:BitType) = n.getMeta[BitType]("tp").apply(v)
    def setTp(v:Option[BitType]) = n.getMeta[BitType]("tp").apply(v)
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
        size /! (spadeParam.bytePerWord * dataPar)
      case n:FringeDenseStore =>
        val size = n.size.T.getBound.toValue
        val dataPar = n.data.T.getVec
        size /! (spadeParam.bytePerWord * dataPar)
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

  def compVec(n:IR):Option[Int] = dbgblk(s"compVec(${dquote(n)})") {
    n match {
      case Const(v:List[_]) => Some(v.size)
      case Const(v) => Some(1)
      case n:TokenWrite => Some(1)
      case n:TokenRead => Some(1)
      case WithMem(access, mem:Reg) => Some(1)
      case WithMem(access, mem:FIFO) if access.getCtrl.schedule=="Streaming" => Some(mem.banks.get.head)
      case n:BufferWrite => n.data.inferVec
      case n:RegAccumOp => Some(1)
      case n@OpDef(_:FixOp | _:FltOp | _:BitOp) => flatReduce(n.input.connected.map{ out => out.inferVec}) { case (a,b) => Math.max(a,b) }
      case n:Shuffle => n.to.T.inferVec
      case n:GlobalOutput => n.in.T.inferVec
      // During staging time GlobalInput might temporarily not connect to GlobalOutput
      case n:GlobalInput => n.in.inferVec
      case InputField(n:OpDef, "input") => None
      case InputField(n:Shuffle, "from" | "base") => zipMap(n.base.singleConnected.get.inferVec, n.from.singleConnected.get.inferVec) { case (a,b) => Math.max(a,b) }
      case InputField(n:BufferWrite, "en") => Some(1)
      case InputField(n:BankedAccess, "en") if n.mem.bankids.nonEmpty => Some(n.mem.nBanks)
      case InputField(n:Controller, "en" | "parentEn") => Some(1)
      case n:Input[_] if n.isConnected => n.singleConnected.get.inferVec
      case OutputField(n:Controller, "done") => Some(1)
      case OutputField(n:Controller, "childDone") => Some(1)
      case OutputField(n:Controller, "valid") => Some(1)
      case OutputField(n:BankedRead, "out") if n.mem.bankids.nonEmpty => Some(n.mem.nBanks)
      case n:Output[_] => n.src.as[PIRNode].inferVec
      case n:ControlTree => if (n.children.isEmpty) Some(n.par.get) else Some(1)
      case n => None
    }
  }

  def compType(n:IR):Option[BitType] = dbgblk(s"compType(${dquote(n)})") {
    n match {
      case n:Shuffle => n.base.inferTp
      case n:TokenRead => Some(Bool)
      case n:TokenWrite => Some(Bool)
      case n:BufferWrite => n.data.inferTp
      case n:BufferRead => n.in.inferTp
      case n:GlobalInput => n.in.inferTp
      case n:GlobalOutput => n.in.inferTp
      case n@OpDef(_:BitOp) => Some(Bool)
      case n@OpDef(_:FixOp | _:FltOp) => assertUnify(n.input.T, s"$n.tp") { _.inferTp }.get
      case Const(_:Boolean) => Some(Bool)
      case Const(_:Int) => Some(Fix(true, 32, 0))
      case Const(_:Float) => Some(Flt(23, 8))
      case Const((i:Int) :: _) => Some(Fix(true, 32, 0))
      case Const(_:String) => Some(Text)
      case InputField(n:OpDef, "input") => None
      case InputField(n, "en" | "parentEn") => Some(Bool)
      case n:Input[_] if n.isConnected => n.singleConnected.get.inferTp
      case OutputField(n:Controller, "valid") => Some(Bool)
      case OutputField(n:Controller, "done") => Some(Bool)
      case OutputField(n:Controller, "childDone") => Some(Bool)
      case n:Output[_] => n.src.as[PIRNode].inferTp
      case n:Any => None
    }
  }

  def stage[T<:PIRNode](n:T):T = {
    val tp = n.inferTp
    n.localIns.foreach { in => 
      in.inferVec
      in.inferTp
    }
    val vec = n.inferVec
    val fields = n.fedges.map { e => s".${e.name}(${e.connected.map { dquote }})" }.mkString
    dbg(s"Create $n")
    dbgn(n)
    n
  }

}

