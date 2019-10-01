package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import scala.collection.mutable

class RuntimeAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal with RuntimeUtil {
  val forward = true

  var passTwo = false
  var maxCount:Long = 0
  var maxCountCtx:String = ""
  val ctxs = mutable.ListBuffer[Context]()

  override def initPass = {
    super.initPass
    maxCount = 0
  }

  override def visitNode(n:N) = {
    n.to[Context].foreach { n =>
      ctxs += n
      val count = n.getCount
      count.foreach {
        case Finite(count) =>
          maxCount = math.max(maxCount, count)
          n.getCtrl.srcCtx.v.foreach { ctx =>
            maxCountCtx = ctx
          }
        case _ =>
      }
    }
  }

  override def finPass = {
    passTwo = true
    dbg(s"passTwo ----------------")
    // Two passes to handle cycle in data flow graph
    ctxs.foreach { n =>
      val count = compCount(n).get
      if (n.collectDown[HostOutController]().nonEmpty & count.isKnown) {
        assert(count == Finite(1), s"Host out count != 1: $count")
      }
      n.collectDown[FringeStreamRead]().headOption.foreach { streamRead =>
        streamRead.count.v.foreach { v =>
          assert(count == v, s"StreamOut count $count != annotated count $v")
        }
      }
      countByReads(n).foreach { c =>
        c.zip(count).foreach { case (c, count) =>
          assert(c == count, s"${n.reads}.count($c) * scale != $n.count($count)")
        }
      }
    }
    ctxs.clear
    super.finPass
    passTwo = false
    if (maxCount > 1000000) {
      dbg(s"maxCount=$maxCount")
      warn(f"maxCount=${maxCount}%.2e $maxCountCtx")
    }
  }

  implicit class RuntimeOp2[N<:IR](n:N) extends RuntimeOp1[N](n) {
    override def getScheduleFactor = n.getMeta[Int]("scheduleFactor").getOrElseUpdate(compScheduleFactor(n.as[Context]))
    override def getIter:Value[Long] = n.getMeta[Value[Long]]("iter").getOrElseUpdate(compIter(n.as[PIRNode]))
    def getScale:Value[Long] = n.getMeta[Value[Long]]("scale").getOrElseUpdate(compScale(n))
    def getCount:Option[Value[Long]] = n.getMeta[Value[Long]]("count").orElseUpdate(compCount(n.as[PIRNode]))
  }

  /*
   * Compute count of the context using reads. Return None if reads is empty
   * and ctrlers nonEmpty
   * */
  def countByReads(n:Context):Option[Value[Long]] = dbgblk(s"countByReads($n)") {
    var reads = n.reads.filterNot { read =>
      if (read.nonBlocking) { read.getCount; true } else false
    }
    if (!passTwo) reads = reads.filterNot { read => 
      val connectToUnlock = read.out.connected.exists { case InputField(lock:Lock, "unlock") => true; case _ => false }
      read.initToken.get || connectToUnlock 
    }
    dbg(s"reads=$reads passTwo=$passTwo")
    val counts = reads.flatMap { read => 
      read.getCount.map { _ * read.getScale }
    }
    val (unknown, known) = counts.partition { _.unknown }
    val (finite, infinite) = known.partition { _.isFinite }
    val c = if (unknown.nonEmpty) Some(Unknown)
    else if (finite.nonEmpty) assertIdentical(finite, 
    s"$n.reads.count reads=${reads.map { r => (r, r.getCount) }} countByController=${countByController(n)} \n${quoteSrcCtx(n)}")
    else if (infinite.nonEmpty) Some(Infinite)
    else if (n.collectFirstChild[FringeStreamWrite].nonEmpty) None
    else { // reads is empty
      val ctrlers = n.ctrlers
      val forevers = ctrlers.filter { _.isForever }
      val (infiniteForever, stopForever) = forevers.partition { _.as[LoopController].stopWhen.T.isEmpty }
      if (ctrlers.isEmpty) if (passTwo) Some(Unknown) else None
      else if (infiniteForever.nonEmpty) Some(Infinite)
      else if (stopForever.nonEmpty) Some(Unknown)
      else if (passTwo) Some(Unknown) else None
    }
    c
  }

  def countByController(n:Context) = dbgblk(s"countByContrller($n)"){
    val ctrlers = n.ctrlers
    dbg(s"$n.ctrlers=$ctrlers")
    ctrlers.map { _.getIter }.reduceOption { _ * _ }
  }

  val StreamWriteContext = MatchRule[Context, FringeStreamWrite] { case n if n.streaming.get =>
    n.collectDown[FringeStreamWrite]().headOption
  }

  def compCount(n:PIRNode):Option[Value[Long]] = {
    dbgblk(s"compCount($n)"){
      n match {
        case StreamWriteContext(sw) => sw.count.v match {
          case Some(v) => Some(v)
          case None => Some(Unknown)
        }
        case n:Context =>
          val ctrlers = n.ctrlers
          var inferByInput = false
          inferByInput ||= n.streaming.get
          inferByInput ||= ctrlers.exists { ctrler => ctrler.isForever && !ctrler.hasBranch }
          inferByInput ||= ctrlers.isEmpty
          if (inferByInput) countByReads(n)
          else countByController(n)
        case n:BufferRead if n.nonBlocking =>
          n.in.T.getCount
          Some(Infinite)
        case n:BufferRegRead =>
          n.writeDone.T.foreach { _.getCount }
          n.writeEn.T.foreach { _.getCount }
          n.in.T.getCount.map { _ /! n.writeDone.collectFirst[BufferWrite]().data.singleConnected.get.getScale }
        case WrittenBy(OutputField(_:FringeStreamRead, "lastBit")) => 
          Some(Finite(1))
        case n:LocalOutAccess =>
          n.in.T.getCount.map { _ * n.in.getVec /! n.out.getVec }
        case n:LocalInAccess =>
          n.ctx.get.getCount.map { _ /! n.getScale }
        case n:GlobalInput =>
          n.in.T.getCount
        case n:GlobalOutput =>
          n.in.T.getCount
        case n => bug(s"Don't know how to compute count of $n")
      }
    }
  }


}

trait RuntimeUtil extends TypeUtil { self:PIRPass =>

  implicit class RuntimeOp1[N<:IR](n:N) extends NodeRuntimeOp[N](n) {
    def getScheduleFactor = compScheduleFactor(n.as[Context])
    def getIter:Value[Long] = compIter(n.as[PIRNode])
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
      case n:LoopController if n.stopWhen.T.nonEmpty => Unknown
      case n:LoopController =>
        n.cchain.T.map { _.getIter }.reduce { _ * _ }
      case n:Controller if n.getCtrl.schedule != Fork => Finite(1l)
      case n:FringeDenseLoad =>
        val size = n.size.T.getBound.toValue
        val dataPar = n.data.getVec
        size /! (n.data.getTp.bytePerWord.get * dataPar)
      case n:FringeDenseStore =>
        val size = n.size.T.getBound.toValue
        val dataPar = n.data.getVec
        size /! (n.data.getTp.bytePerWord.get * dataPar)
      case n:FringeSparseLoad => Finite(1l)
      case n:FringeSparseStore => Finite(1l)
      case n => Unknown
    }
  }

  def compScale(n:Any):Value[Long] = dbgblk(s"compScale($n)"){
    n match {
      case OutputField(ctrler:Controller, "done") => ctrler.getIter *  compScale(ctrler.childDone)
      case OutputField(ctrler:Controller, "childDone" | "childDone") => 
        val children = ctrler.childDone.connected.filter { _.asInstanceOf[Field[_]].name == "parentEn" }.map { _.src.as[Controller] }
        assertUnify(children, s"$ctrler.childDone.scale") { child => compScale(child.done) }.getOrElse(Finite(ctrler.ctx.get.getScheduleFactor))
      case OutputField(n:Const, _) => Finite(n.ctx.get.getScheduleFactor)
      case n:LocalAccess => 
        (n, n.done.singleConnected.get) match {
          case (n, done) if n.en.isConnected => Unknown
          case (n:BufferWrite, _) if n.en.isConnected => Unknown // Branch dependent
          case (n:TokenAccess, OutputField(r:BufferRead, _)) => compScale(r.inAccess.as[BufferWrite].data.singleConnected.get)
          case (n:BufferWrite, done) if n.ctx.get.streaming.get =>
            n.data.singleConnected.get match {
              case OutputField(n:FringeDenseStore, "ack") => n.getIter * n.ctx.get.getScheduleFactor
              case OutputField(n:FringeStreamRead, "lastBit") => Unknown
              case out => Finite(n.ctx.get.getScheduleFactor)
            }
          case (n:BufferRead, done) if n.ctx.get.streaming.get =>
            n.out.connected.head match {
              case InputField(n:DRAMDenseCommand, "size" | "offset") => n.getIter * n.ctx.get.getScheduleFactor
              case in => Finite(n.ctx.get.getScheduleFactor)
            }
          case (n, done) => compScale(done) 
        }
      case n => bug(s"Don't know how to compute scale of $n")
    }
  }

  def compScheduleFactor(n:Context):Int = dbgblk(s"compScheduleFactor($n)"){
    if (spadeParam.scheduled) {
      Math.max(n.collectDown[OpNode]().size,1)
    } else {
      1
    }
  }

  def outputMatch(out1:Output[PIRNode], out2:Output[PIRNode]) = (out1, out2) match {
    case (out1, out2) if out1 == out2 => true
    case (OutputField(Const(out1), "out"), OutputField(Const(out2), "out")) if out1 == out2 => true
    case (OutputField(Const(List(out1)), "out"), OutputField(Const(out2), "out")) if out1 == out2 => true
    case (OutputField(Const(out1), "out"), OutputField(Const(List(out2)), "out")) if out1 == out2 => true
    case (OutputField(c1:UnitController, "childDone" | "done"), OutputField(c2:UnitController, "childDone" | "done")) => c1 == c2
    case (out1, out2) => false
  }

  def matchInput(in1:Input[PIRNode], in2:Input[PIRNode]) = (in1, in2) match {
    case (in1, in2) if in1.connected.size != in2.connected.size => false
    case (InputField(_,"en" | "done"), InputField(_,"en" | "done")) => //TODO: order doesn't matter
      (in1.connected, in2.connected).zipped.forall { outputMatch _ }
    case (in1, in2) => (in1.connected, in2.connected).zipped.forall { outputMatch _ }
  }

  def matchInput(in1:Input[PIRNode], connected:List[Output[PIRNode]]) = {
    (in1.connected, connected).zipped.forall { case (o1, o2) =>
      (in1.connected, connected).zipped.forall { outputMatch _ }
    }
  }

  def matchInput(in1:Input[PIRNode], out:Output[PIRNode]) = {
    in1.connected.forall { outputMatch(_,out) }
  }

  def matchRate(a1:LocalAccess, a2:LocalAccess) = {
    (compScale(a1), compScale(a2)) match {
      case (Unknown, _) => false
      case (_, Unknown) => false
      case (s1, s2) => s1 == s2
    }
  }


}
