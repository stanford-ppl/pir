package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import scala.collection.mutable

trait RuntimeAnalyzer { self:PIRPass =>

  implicit class CtxUtil(ctx:Context) {
    def reads:Seq[LocalOutAccess] = ctx.collectDown[LocalOutAccess]().filterNot { _.isLocal }
    def writes:Seq[LocalInAccess] = ctx.collectDown[LocalInAccess]().filterNot { _.isLocal }
    def ctrs:Seq[Counter] = ctx.collectDown[Counter]()
    def ctrler(ctrl:ControlTree) = {
      assertOne(
        ctx.collectDown[Controller]().filter { _.ctrl.get == ctrl }, 
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
  implicit class NodeRuntimeOp(n:ND) {
    def getVec:Int = n.getMeta[Int]("vec").getOrElseUpdate(compVec(n))
  }

  val StreamWriteContext = MatchRule[Context, FringeStreamWrite] { n =>
    n.collectDown[FringeStreamWrite]().headOption
  }

  val StreamReadContext = MatchRule[Context, FringeStreamRead] { n =>
    n.collectDown[FringeStreamRead]().headOption
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
      case OutputField(n:DRAMDenseCommand, "deqCmd") => n.getIter * n.ctx.get.getScheduleFactor
      case OutputField(n:FringeSparseLoad, "deqCmd") => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:DRAMLoadCommand, "dataValid") => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:DRAMStoreCommand, "deqData") => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:DRAMDenseCommand, "ackValid") => n.getIter * n.ctx.get.getScheduleFactor
      case OutputField(n:DRAMSparseCommand, "ackValid") => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:FringeStreamWrite, "dataValid") => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:FringeStreamRead, "deqData") => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:PIRNode, _) => n.getScale 
      case n:LocalAccess => compScale(assertOne(n.done.connected, s"$n.done.connected"))
      case n:ControllerDone =>
        val ctrler = n.ctrler
        ctrler.getIter *  ctrler.valid.T.getScale
      case n:ControllerValid =>
        val ctrler = n.ctrler
        val children = ctrler.valid.T.out.connected.filter { _.asInstanceOf[Field[_]].name == "parentEn" }.map { _.src.as[Controller] }
        assertUnify(children, s"$n.valid.scale") { child =>
          child.valid.T.getScale * child.getIter
        }.getOrElse(Finite(n.ctx.get.getScheduleFactor))
      case n@Const(true) => Finite(n.ctx.get.getScheduleFactor)
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

  /*
   * Compute count of the context using reads. Return None if reads is empty
   * and ctrlers nonEmpty
   * */
  def countByReads(n:Context):Option[Value[Long]] = {
    val reads = n.reads
    val counts = reads.map { read => read.getCount * read.getScale }
    val (unknown, known) = counts.partition { _.unknown }
    val (finite, infinite) = known.partition { _.isFinite }
    if (unknown.nonEmpty) Some(Unknown)
    else if (finite.nonEmpty) assertIdentical(finite, s"$n.reads.count reads=$reads")
    else if (infinite.nonEmpty) Some(Infinite)
    else if (n.collectDown[FringeStreamWrite]().nonEmpty) None
    else { // reads is empty
      val ctrlers = n.collectDown[Controller]()
      if (ctrlers.isEmpty) throw PIRException(s"$n's ctrlers and reads are empty")
      else if (ctrlers.exists { _.isForever }) Some(Infinite)
      else None
    }
  }

  def compCount(n:PIRNode):Value[Long] = dbgblk(s"compCount($n)"){
    n match {
      case StreamWriteContext(sw) => sw.count.v.getOrElse(throw PIRException(s"${sw.name.v.getOrElse(sw.sname.get)} is not annotated with count"))
      case n:Context =>
        val ctrlers = n.collectDown[Controller]()
        val reads = n.reads
        if (ctrlers.isEmpty || ctrlers.exists { _.isForever }) countByReads(n).get
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

  def compVec(n:N):Int = dbgblk(s"compVec($n)"){
    n match {
      case n:Const => 1
      case n:CounterIter if n.i.nonEmpty => 1
      case n:CounterValid if n.i.nonEmpty => 1
      case n:RegAccumOp => 1
      case n:ControllerValid => 1
      case n:ControllerDone => 1
      case n:GlobalOutput => n.in.T.getVec
      case n:GlobalInput => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:BufferWrite if n.getCtrl.schedule=="Streaming" =>
        assertUnify(n.outAccesses, s"$n.outAccesses.bank") { _.banks.get.head }.get
      case n:MemWrite if n.getCtrl.schedule=="Streaming" =>
        n.mem.banks.get.head
      case n:BufferRead if n.getCtrl.schedule=="Streaming" =>
        n.banks.get.head
      case n:MemRead if n.getCtrl.schedule=="Streaming" =>
        n.mem.banks.get.head
      case n:PIRNode => n.getCtrl.getVec
      case n:ControlTree =>
        if (n.children.isEmpty) n.par.get else 1
    }
  }

}

