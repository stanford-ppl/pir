package pir
package pass

import pir.node._
import prism.graph._
import prism.util._
import scala.collection.mutable

class RuntimeAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal {
  val forward = true

  var passTwo = false

  override def visitNode(n:N) = {
    n.to[Context].foreach { n =>
      n.getCount
    }
  }

  override def finPass = {
    passTwo = true
    val ctxs = pirTop.collectDown[Context]()
    ctxs.foreach { _.count.reset }
    // Two passes to handle cycle in data flow graph
    ctxs.foreach { n =>
      val count = n.getCount.get
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
    super.finPass
    passTwo = false
  }

  implicit class RuntimeOp[N<:IR](n:N) extends NodeRuntimeOp[N](n) {
    def getIter:Value[Long] = n.getMeta[Value[Long]]("iter").getOrElseUpdate(compIter(n.as[PIRNode]))
    def getScale:Value[Long] = n.getMeta[Value[Long]]("scale").getOrElseUpdate(compScale(n))
    def getCount:Option[Value[Long]] = n.getMeta[Value[Long]]("count").orElseUpdate(compCount(n.as[PIRNode]))
    def getScheduleFactor = n.getMeta[Int]("scheduleFactor").getOrElseUpdate(compScheduleFactor(n.as[Context]))
  }

  /*
   * Compute count of the context using reads. Return None if reads is empty
   * and ctrlers nonEmpty
   * */
  def countByReads(n:Context):Option[Value[Long]] = dbgblk(s"countByReads($n)") {
    val counts = n.reads.flatMap { read => 
      if (!passTwo && read.initToken.get) None 
      else if (read.nonBlocking) { read.getCount; None }
      else read.getCount.map { _ * read.getScale }
    }
    val (unknown, known) = counts.partition { _.unknown }
    val (finite, infinite) = known.partition { _.isFinite }
    val c = if (unknown.nonEmpty) Some(Unknown)
    else if (finite.nonEmpty) assertIdentical(finite, 
    s"$n.reads.count reads=${n.reads.map { r => (r, r.getCount) }} countByController=${countByController(n)}")
    else if (infinite.nonEmpty) Some(Infinite)
    else if (n.collectFirstChild[FringeStreamWrite].nonEmpty) None
    else { // reads is empty
      val ctrlers = n.ctrlers
      val forevers = ctrlers.filter { _.isForever }
      val (infiniteForever, stopForever) = forevers.partition { _.as[LoopController].stopWhen.T.isEmpty }
      if (ctrlers.isEmpty) None
      else if (infiniteForever.nonEmpty) Some(Infinite)
      else if (stopForever.nonEmpty) Some(Unknown)
      else None
    }
    c
  }

  def countByController(n:Context) = dbgblk(s"countByContrller($n)"){
    n.ctrlers.map { _.getIter }.reduceOption { _ * _ }
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
          if (inferByInput) countByReads(n)
          else countByController(n)
        case n:BufferRead if n.nonBlocking =>
          n.in.T.getCount
          Some(Infinite)
        case n:BufferRegRead =>
          n.writeDone.T.foreach { _.getCount }
          n.writeEn.T.foreach { _.getCount }
          n.in.T.getCount.map { _ /! n.writeDone.collectFirst[BufferWrite]().data.singleConnected.get.getScale }
        case n:LocalOutAccess =>
          n.in.T.getCount
        case n:LocalInAccess =>
          n.ctx.get.getCount.map { _ /! n.getScale }
        case n:GlobalInput =>
          n.in.T.getCount
        case n:GlobalOutput =>
          n.in.T.getCount
        case n => throw PIRException(s"Don't know how to compute count of $n")
      }
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
          case (n:BufferWrite, _) if n.en.isConnected => Unknown // Branch dependent
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

    
}
