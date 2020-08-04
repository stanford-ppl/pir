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
          n.getCtrl.srcCtx.get.headOption.foreach { ctx =>
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
    dbg(s"maxCount=$maxCount")
    info(f"maxCount=${maxCount}%.2e $maxCountCtx")
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
  private val input = raw"in.*".r
  def countByReads(n:Context):Option[Value[Long]] = dbgblk(s"countByReads($n)") {
    var reads = n.reads.filterNot { read =>
      if (read.nonBlocking) { read.getCount; true } else false
    }
    if (!passTwo) reads = reads.filterNot { read => 
      val connectToUnlock = read.out.connected.exists { case InputField(lock:Lock, "unlock") => true; case _ => false }
      read.initToken.get>0 || connectToUnlock 
    }
    reads.foreach { _.getCount }
    dbg(s"reads=$reads passTwo=$passTwo")
    val bbs = n.collectDown[BlackBox]()
    val counts = bbs.headOption match {
      case Some(bb:MergeBuffer) => List(Unknown)
      case Some(bb:LockRMWBlock) => 
        (bb.lockAddrs, 
          bb.unlockReadAddr(bb.accums.head), 
          bb.unlockWriteAddr(bb.accums.head)
        ).zipped.map { case (la, ura, uwa) =>
          import Value._
          val lcount = la.T.getCount
          val urcount = ura.T.map{ _.getCount }.getOrElse(Some(Finite(0l)))
          val uwcount = uwa.T.map{ _.getCount }.getOrElse(Some(Finite(0l)))
          zipMap(lcount,urcount,uwcount) { case (la,ura,uwa) => la + ura + uwa }.getOrElse(Unknown)
        }
      case Some(_) => List(Unknown)
      case None => reads.flatMap { read => read.getCount.map { _ * read.getScale } } 
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
    if (ctrlers.exists { case top:TopController => true; case _ => false}) {
      dbg(s"$n.ctrlers=$ctrlers")
      ctrlers.map { _.getIter }.reduceOption { _ * _ }
    } else Some(Unknown)
  }

  val StreamWriteContext = MatchRule[Context, FringeStreamWrite] { case n if n.streaming.get =>
    n.collectDown[FringeStreamWrite]().headOption
  }

  private def quote(n:PIRNode) = n match {
    case n:Context => s"${n} (${n.parent.get})"
    case n => n.toString
  }

  val countStack = scala.collection.mutable.Stack[PIRNode]()
  def compCount(n:PIRNode):Option[Value[Long]] = {
    val cnt:Option[Value[Long]] = dbgblk(s"compCount(${quote(n)})"){
      if (countStack.contains(n))
        err(s"Cycle in the program graph! ${countStack.collect { case ctx:Context => quote(ctx) + s" ${quoteSrcCtx(ctx)}" }.mkString("\n")}")
      countStack.push(n)
      n match {
        case StreamWriteContext(sw) => sw.count.v match {
          case Some(v) => Some(v)
          case None => Some(Unknown)
        }
        case n:Context =>
          val ctrlers = n.ctrlers
          var inferByInput = false
          inferByInput ||= n.streaming.get
          inferByInput ||= ctrlers.exists { ctrler => ctrler.isForever || ctrler.hasBranch }
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
        case WrittenBy(o@OutputField(l:LockRMWBlock, "unlockReadData")) => 
          l.unlockReadAddr(l.accumMap(o))(l.laneMap(o)).T.flatMap { _.getCount }
        case WrittenBy(o@OutputField(l:LockRMWBlock, "unlockWriteAck")) => 
          l.unlockWriteAddr(l.accumMap(o))(l.laneMap(o)).T.flatMap { _.getCount }
        case WrittenBy(o@OutputField(l:LockRMWBlock, "lockDataOut")) => 
          l.lockAddrs(l.laneMap(o)).T.getCount
        case WrittenBy(o@OutputField(l:LockRMWBlock, "lockInputOut")) => 
          l.lockAddrs(l.laneMap(o)).T.getCount
        case WrittenBy(o@OutputField(l:LockRMWBlock, "lockAck")) => 
          l.lockAddrs(l.laneMap(o)).T.getCount
        case WrittenBy(o@OutputField(l:SparseDRAMBlock, "rmwDataOut")) => 
          val (aid, lane) = l.portMap(o.as)
          l.rmwPorts(aid)(lane)._2.singleConnected.get.src.getCount
        case WrittenBy(o@OutputField(l:SparseDRAMBlock, "readData")) => 
          val (aid, lane) = l.portMap(o.as)
          l.readPorts(aid)(lane)._1.singleConnected.get.src.getCount
        case WrittenBy(o@OutputField(l:SparseDRAMBlock, "writeAck")) => 
          val (aid, lane) = l.portMap(o.as)
          l.writePorts(aid)(lane)._1.singleConnected.get.src.getCount
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
    countStack.pop
    cnt
  }


}

trait RuntimeUtil extends TypeUtil { self:PIRPass =>

  implicit class RuntimeOp1[N<:IR](n:N) extends NodeRuntimeOp[N](n) {
    def getScheduleFactor = compScheduleFactor(n.as[Context])
    def getIter:Value[Long] = compIter(n.as[PIRNode])
  }

  def compIter(n:PIRNode):Value[Long] = dbgblk(s"compIter($n)"){
    n match {
      case n:ForeverCounter => Infinite
      case n:StridedCounter => 
        val min = n.min.T.getBound.toValue
        val max = n.max.T.getBound.toValue
        val step = n.step.T.getBound.toValue
        val par = n.par
        (max - min) /! (step * par)
      case n:Controller =>
        n.getCtrl.iter.getOrElseUpdate {
          n match {
            case n:LoopController if n.stopWhen.T.nonEmpty => Unknown
            case n:LoopController => n.cchain.T.map { _.getIter }.reduce { _ * _ }
            case n:SplitController => Unknown
            case n:Controller if n.getCtrl.schedule != Fork => Finite(1l)
            case n => Unknown
          }
        }
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
      case n:FringeCoalStore => Finite(1l)
      case n:FringeDynStore => Finite(1l)
      case n:BVBuildNoTree => Finite(1l)
      case n:MergeBuffer => 
        import Value._
        n.bounds.map { _.T.getBound.toValue }.reduce { _ + _ }
      case n => Unknown
    }
  }

  private val bound = raw"bound.*".r
  private val input = raw"in.*".r
  def compScale(n:Any):Value[Long] = dbgblk(s"compScale($n)"){
    n match {
      case OutputField(ctrler:Controller, "done") => ctrler.getIter *  compScale(ctrler.childDone)
      case OutputField(ctrler:Controller, "tileDone") => Unknown
      case OutputField(ctrler:Controller, "subTileDone") => Unknown
      case OutputField(ctrler:Controller, "childDone" | "stepped") => 
        val children = ctrler.childDone.connected.filter { _.asInstanceOf[Field[_]].name == "parentEn" }.map { _.src.as[Controller] }
        assertUnify(children, s"$ctrler.childDone.scale") { child => compScale(child.done) }.getOrElse(Finite(ctrler.ctx.get.getScheduleFactor))
      case OutputField(n:Const, _) => Finite(n.ctx.get.getScheduleFactor)
      case OutputField(n:ScratchpadDelay, _) => compScale(n.in.collectFirst[BufferWrite]().data.singleConnected.get)
      case OutputField(n:Delay, _) => compScale(n.in.collectFirst[BufferWrite]().data.singleConnected.get)
      case n:LocalAccess => 
        (n, n.done.singleConnected.get) match {
          case (n, done) if n.en.isConnected => Unknown
          case (n:BufferWrite, _) if n.en.isConnected => Unknown // Branch dependent
          case (n:TokenAccess, OutputField(r:BufferRead, _)) => compScale(r.inAccess.as[BufferWrite].data.singleConnected.get)
          case (n:BufferWrite, done) if n.ctx.get.streaming.get =>
            n.data.singleConnected.get match {
              case OutputField(n:FringeDenseStore, "ack") => n.getIter * n.ctx.get.getScheduleFactor
              case OutputField(n:FringeStreamRead, "lastBit") => Unknown
              case OutputField(n:MergeBuffer, "outBound") => n.getIter * n.ctx.get.getScheduleFactor
              case OutputField(n:LockRMWBlock, output) => Unknown
              case out => Finite(n.ctx.get.getScheduleFactor)
            }
          case (n:BufferRead, done) if n.ctx.get.streaming.get =>
            n.out.connected.head match {
              case InputField(n:DRAMDenseCommand, "size" | "offset") => n.getIter * n.ctx.get.getScheduleFactor
              case InputField(n:MergeBuffer, "init") => n.getIter * n.ctx.get.getScheduleFactor
              case InputField(n:MergeBuffer, bound) => n.getIter * n.ctx.get.getScheduleFactor
              case InputField(n:MergeBuffer, input) => Unknown
              case InputField(n:LockRMWBlock, input) => Unknown
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

  def matchOutput(out1:Output[PIRNode], out2:Output[PIRNode]) = (out1, out2) match {
    case (out1, out2) if out1 == out2 => true
    case (OutputField(Const(out1), "out"), OutputField(Const(out2), "out")) if out1 == out2 => true
    case (OutputField(Const(List(out1)), "out"), OutputField(Const(out2), "out")) if out1 == out2 => true
    case (OutputField(Const(out1), "out"), OutputField(Const(List(out2)), "out")) if out1 == out2 => true
    case (OutputField(c1:UnitController, "stepped" | "childDone" | "done"), OutputField(c2:UnitController, "stepped" | "childDone" | "done")) => c1 == c2
    case (out1, out2) => false
  }

  def matchInput(in1:Input[PIRNode], in2:Input[PIRNode]) = (in1, in2) match {
    case (in1, in2) if in1.connected.size != in2.connected.size => false
    case (InputField(_,"en" | "done"), InputField(_,"en" | "done")) => //TODO: order doesn't matter
      (in1.connected, in2.connected).zipped.forall { matchOutput _ }
    case (in1, in2) => (in1.connected, in2.connected).zipped.forall { matchOutput _ }
  }

  def matchInput(in1:Input[PIRNode], connected:List[Output[PIRNode]]) = {
    (in1.connected, connected).zipped.forall { case (o1, o2) =>
      (in1.connected, connected).zipped.forall { matchOutput _ }
    }
  }

  def matchInput(in1:Input[PIRNode], out:Output[PIRNode]) = {
    in1.connected.forall { matchOutput(_,out) }
  }

  def matchRate(a1:LocalAccess, a2:LocalAccess):Boolean = {
    if (a1.isFIFO != a2.isFIFO) return false
    val lca = leastCommonAncesstor(a1.getCtrl, a2.getCtrl).get
    val branch1 = a1.getCtrl.ancestorSlice(lca).dropRight(1)
    val branch2 = a2.getCtrl.ancestorSlice(lca).dropRight(1)
    dbg(s"branch1=$branch1")
    dbg(s"branch2=$branch2")
    val rate1 = branch1.map { _.iter.v.getOrElse(Unknown) }.reduceOption { _ * _ }.getOrElse(Finite(1l))
    val rate2 = branch2.map { _.iter.v.getOrElse(Unknown) }.reduceOption { _ * _ }.getOrElse(Finite(1l))
    if (rate1 == Unknown) return false
    if (rate2 == Unknown) return false
    return rate1 == rate2
  }

}
