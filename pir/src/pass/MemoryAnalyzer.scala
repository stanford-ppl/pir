package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import scala.collection.mutable

trait MemoryAnalyzer extends PIRPass { self:PIRTransformer =>

  def insertToken(
    fctx:Context, 
    tctx:Context, 
    dep:Option[Output[PIRNode]]=None,
    isMemReduce:Boolean = false
  )(implicit file:sourcecode.File, line: sourcecode.Line):TokenRead = {
    val isFIFO = false
    val fctrl = fctx.ctrl.get
    val tctrl = tctx.ctrl.get
    dbgblk(s"insertToken(fctx=$fctx($fctrl), tctx=$tctx($tctrl))") {
      val (enq, deq) = compEnqDeq(isFIFO=isFIFO, fctx, tctx, None, Nil, isMemReduce=isMemReduce)
      val write = within(fctx, fctrl) {
        allocate[TokenWrite](_.done.isConnectedTo(enq)) {
          stage(TokenWrite().done(enq).dummy(dep))
        }
      }
      within(tctx, tctrl) {
        allocate[TokenRead](read => read.in.isConnectedTo(write.out) && read.done.isConnectedTo(deq)) {
          stage(TokenRead().in(write).done(deq))
        }
      }
    }
  }

  def compEnqDeq(
    isFIFO:Boolean, 
    octx:Context, 
    ictx:Context, 
    out:Option[Output[PIRNode]], 
    ins:Seq[Input[PIRNode]], 
    isMemReduce:Boolean=false
  ):(Output[PIRNode], Output[PIRNode]) = {
    val from = out.map { _.src }
    val o = octx.ctrl.get
    val i = ictx.ctrl.get
    dbgblk(s"compEnqDeq(isFIFO=$isFIFO, o=${dquote(o)}, i=${dquote(i)})") {
      dbg(s"out=$from.$out")
      dbg(s"ins=${ins.map { in => s"${in.src}.$in"}.mkString(",")}")
      (out, ins) match {
        case (out,InputField(n:ScanCounter, "ctrlWord")::_) if isFIFO => (tileDone(o, octx), tileDone(i, ictx))
        case (out,InputField(n:ScanCounter, "packCntIdx")::_) if isFIFO => (subTileDone(o, octx), subTileDone(i, ictx))
        // case (out,InputField(n:DataScanCounter, "ctrlWord")::_) if isFIFO => (tileDone(o, octx), tileDone(i, ictx))
        case (out,InputField(n:ScanCounterDataFollower, "vecTotalSet")::_) if isFIFO => (tileDone(o, octx), tileDone(i, ictx))
        // case (out,InputField(n:ScanCounter, "tileCount")::_) if isFIFO => (childDone(o, octx), childDone(i, ictx))
        // case (out,InputField(n:DataScanCounter, "tileCount")::_) if isFIFO => (childDone(o, octx), childDone(i, ictx))
        case (out,InputField(n:DataScanCounter, "cnt")::_) if isFIFO => (tileDone(o, octx), tileDone(i, ictx))
        case (out,InputField(n:DataScanCounter, "indOrData")::_) if isFIFO => (subTileDone(o, octx), subTileDone(i, ictx))
        case (out,ins) if isFIFO => (childDone(o, octx), childDone(i, ictx))
        case (out,Seq(InputField(n:LoopController, "stopWhen"))) if o == i => (childDone(o, octx), childDone(i, ictx))
        case (out,ins) if o == i => 
          if (isMemReduce) (done(o, octx), done(i, ictx)) // hack for mem reduce to improve performance
          else (childDone(o, octx), childDone(i, ictx))
        case (out,ins) =>
          val lca = leastCommonAncesstor(o,i).get
          val oAncesstors = o.ancestorTree
          val iAncesstors = i.ancestorTree
          val oidx = oAncesstors.indexOf(lca)
          val iidx = iAncesstors.indexOf(lca)
          // Use def to prevent evaluation outside if statement to prevent idx out of bound
          // in case of one ctrl is ancesstor of another
          def octrl = oAncesstors(oidx-1)
          def ictrl = iAncesstors(iidx-1)
          val enq = if (lca == o) childDone(o, octx) else done(octrl, octx)
          val deq = if (lca == i) childDone(i, ictx) else done(ictrl, ictx)
          dbg(s"enqCtrl=${enq.src.getCtrl} deqCtrl=${deq.src.getCtrl}")
          (enq,deq)
      }
    }
  }

  def subTileDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = dbgblk(s"subTileDone($ctrl, $ctx)"){
    if (ctx.getCtrl.ancestorSlice(ctrl).exists { _.isAsync }) {
      err(s"Trying to get done of $ctrl where $ctx is under async controller ctx.ctrl=${ctx.getCtrl}")
    }
    val ctrler = if (ctx.streaming.get) {
      within(ctx, ctrl) { 
        allocate[UnitController]()(stage(UnitController().par(1)))
      }
    } else if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get
    } else {
       //Distributed controller
      ctx.getCtrler(ctrl)
    }
    ctrler.subTileDone
  }

  def tileDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = dbgblk(s"tileDone($ctrl, $ctx)"){
    if (ctx.getCtrl.ancestorSlice(ctrl).exists { _.isAsync }) {
      err(s"Trying to get done of $ctrl where $ctx is under async controller ctx.ctrl=${ctx.getCtrl}")
    }
    val ctrler = if (ctx.streaming.get) {
      within(ctx, ctrl) { 
        allocate[UnitController]()(stage(UnitController().par(1)))
      }
    } else if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get
    } else {
       //Distributed controller
      ctx.getCtrler(ctrl)
    }
    ctrler.tileDone
  }

  def childDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = dbgblk(s"childDone($ctrl, $ctx)"){
    if (ctx.getCtrl.ancestorSlice(ctrl).exists { _.isAsync }) {
      err(s"Trying to get done of $ctrl where $ctx is under async controller ctx.ctrl=${ctx.getCtrl}")
    }
    val ctrler = if (ctx.streaming.get) {
      within(ctx, ctrl) { 
        allocate[UnitController]()(stage(UnitController().par(1)))
      }
    } else if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get
    } else {
       //Distributed controller
      ctx.getCtrler(ctrl)
    }
    ctrler.childDone
  }

  def done(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (ctx.getCtrl.ancestorSlice(ctrl).exists { _.isAsync }) {
      if (ctrl == pirTop.accelTopCtrl) {
        val const = within(ctx, ctrl) {
          allocConst(false, Some(Bool))
        }
        return const.out
      } else {
        err(s"Trying to get done of $ctrl where $ctx is under async controller ctx.ctrl=${ctx.getCtrl}")
      }
    }
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.done
    } else {
      //Distributed controller
      ctx.getCtrler(ctrl).done
    }
  }

  def allocate[T<:PIRNode:ClassTag:TypeTag](
    filter:T => Boolean = (n:T) => true,
    allowDuplicates:Boolean = false
  )(newNode: => T):T = {
    val ct = implicitly[ClassTag[T]]
    val container = stackTop[PIRParent].getOrElse(bug(s"allocate[$ct] outside PIRParent env")).as[PIRNode]
    (container, classTag[T]) match {
      case (container:Top, ct) if ct == classTag[Const] => newNode // allocation is too expensive performance-wise, just get a new one
      case (container, ct) if ct == classTag[Const] => 
        container.children.find { case c:T => filter(c); case _ => false }.getOrElse { newNode }.as[T]
      case _ =>
        val nodes = container.collectDown[T]().filter(filter)
        val opt = if (allowDuplicates) nodes.headOption else assertOneOrLess(nodes, s"$ct under $container")
        opt.getOrElse {
          val node = within(container) { newNode }
          dbg(s"allocate[$ct](container=$container) = ${dquote(node)}")
          node
        }
    }
  }

  private def equalValue(a:Any, b:Any):Boolean = {
    (a,b) match {
      case (a:Iterable[_], b:Iterable[_]) => a.size == b.size && (a,b).zipped.forall { (a,b) => equalValue(a,b) }
      case (a,b) => a == b && (a.getClass == b.getClass)
    }
  }

  def allocConst(value:Any, tp:Option[BitType]=None) = allocate[Const] { c => 
    tp.fold(true) { _ == c.out.getTp } &&
    equalValue(c.value,value) &&
    stackTop[Ctrl].fold(true) { ctrl => c.getCtrl == ctrl }
  } { 
    val c = Const(value)
    tp.foreach { tp =>
      c.out.tpMeta.update(tp)
    }
    stage(c)
  }


  def insertBarrier(from:Access, to:Access, carried:Boolean,depth:Int) = {
    val isMemReduce = from.mem.T.isMemReduceAccum.get && to.mem.T.isMemReduceAccum.get
    if (isMemReduce) {
      dbg(s"isMemReduce=${isMemReduce} $from $to")
    }
    val fromCtx = from.ctx.get
    val toCtx = to.ctx.get
    fromCtx.streaming.reset
    toCtx.streaming.reset
    val token = insertToken(fromCtx, toCtx,isMemReduce=isMemReduce).depth(depth)
    fromCtx.streaming(true) // hack to get the correct childDone
    toCtx.streaming(true)
    if (carried) {
      token.initToken := 1
      token.inits := true
    }
    dbg(s"$token.depth = ${token.depth.get}")
  }

  /*
   * W => W (fake dependency) partial updates
   * W => R (true dependency)
   * R => W (fake dependency)
   * R => R (fake dependency for SRAM [time multiplex read port])/(no dependency for DRAM)
   *
   * Dependency checking: 
   *  called on all access and their visible predecessors:
   * For A1, A2
   *  if sequential:
   *    if R => R for DRAM:
   *      no synchronization
   *    else:
   *      token(1)
   *  if pipeline/multibuffer:
   *    if buffer depth known(SRAM): 
   *      token(depth)
   *    if buffer depth unknown(DRAM)
   *      token(1) for true dependency
   *      no synchronization for fake dependency
   * For A2, A1:
   *  if sequential:
   *    if R => R for DRAM:
   *      no synchronization
   *    else:
   *      token(1).init(1)
   *  if piplined/multibuffer:
   *    if buffer depth known(SRAM):
   *      if sram access latency = 1 and A2,A1 single cycle apart
   *        no synchronization
   *      else:
   *        token(depth).init(depth)
   *    if buffer depth unknown(SRAM):
   *      token(1).init(1) for true dependency
   *      no synchronization for fake dependency
   *
   *  For DRAM since we don't know number of buffer the user have, we don't synchronize on pipelined
   *  fake dependency. 
   *  It's user's responsibility to handle backpressure or fake dependency either 
   *  with explicit token or
   *  make sure there's no need of synchronization because:
   *    1. "infinite" buffers, working on non-overlapping tiles. Most commonly used
   *    2. There is true carried dependency 
   *
   * Monotonicity: if A1 => A2 and there's no entry point between A1 and A2, then no need to
   * synchronize A2 with anyone else. This assume if exists A3 such that A3 => A2, then A3 => A1.
   * This is trivially true for A2.tp == A1.tp. When they are not equal, 
   * A3   A1   A2   Monotonicity
   * W    W    R        V
   * R    W    R        V
   * R    R    W        (X for DRAM, V for SRAM)
   * W    R    W        V
   *
   * To handle this, we first find all forward and carried accesses each access can depends on, then
   * perform transitive reduction on the forward dependency DAG and backward dependency DAG, and
   * finally insert tokens.
   * 
   * */
  def consistencyBarrier[A<:PIRNode](accesses:List[A])(dependsOn:(A,A) => Option[Int])(insertBarrier:(A,A,Boolean,Int) => Unit):List[UnrolledAccess[A]] = {
    val uas = accesses.groupBy { _.progorder.get }.map { 
      case (progorder, accesses) => 
        val lanes = accesses.head match {
          case access:Access => accesses.sortBy { _.as[Access].order.get }
          case access => accesses.sortBy { _.id }
        }
        UnrolledAccess(lanes)
    }.toList
    def insertBarrierUnrolled(predua:UnrolledAccess[A], ua:UnrolledAccess[A], carried:Boolean, depth:Int) = {
      predua.lanes.zipWithIndex.foreach { case (pred, predLane) =>
        ua.lanes.zipWithIndex.foreach { case (access, accessLane) =>
          dbgblk(s"insertBarrier(${dquote(predua)}[$predLane], ${dquote(ua)}[$accessLane], carried=$carried, depth=$depth)") {
            insertBarrier(pred,access,carried,depth)
          }
        }
      }
    }
    def dependsOnUnrolled(a:UnrolledAccess[A],b:UnrolledAccess[A]) = {
      dependsOn(a.lanes.head, b.lanes.head)
    }
    unrolledConsistencyBarrier(uas)(dependsOnUnrolled)(insertBarrierUnrolled)
  }

  def unrolledConsistencyBarrier[A<:PIRNode]
    (uas:List[UnrolledAccess[A]])
    (dependsOn:(UnrolledAccess[A],UnrolledAccess[A]) => Option[Int])
    (insertBarrier:(UnrolledAccess[A],UnrolledAccess[A],Boolean,Int) => Unit):List[UnrolledAccess[A]] = {
    val sorted = uas.sortBy { _.progorder }

    val mem = uas.head.lanes.head match {
      case access:Access => access.mem.T
      case fringe:DRAMCommand => fringe.dram
    }
    dbgblk(s"sorted(${dquote(mem)})") {
      sorted.foreach { ua =>
        dbg(s"UA[${ua.progorder}] ${ua.lanes.map{dquote}.mkString(",")}")
        dbg(s"- ${ua.srcCtx}")
      }
    }

    def findPredecessors(ua:UnrolledAccess[A]):(List[(UnrolledAccess[A],Int)],List[(UnrolledAccess[A],Int)]) = {
      val (before, rest) = sorted.span { _ != ua }
      val (_,after) = rest.splitAt(1)
      dbg(s"${dquote(ua)} before: ${before.map{dquote(_)}.mkString(",")} rest: ${rest.map{dquote(_)}.mkString(",")}")

      val forward = before.flatMap { before => dependsOn(ua, before).map { depth => (before, depth) } }
      val carried = after.flatMap { after => 
        val lca = leastCommonAncesstor(ua.ctrl, after.ctrl).get
        if (lca.ancestorTree.exists { _.isLoop.get }) {
          dependsOn(ua, after).map { depth => (after, depth) }
        } else Nil
      }

      (forward, carried)
    }

    // Get an reachable sets for forward and carried dependency
    val (forward, carried) = sorted.map { ua =>
      val (forward,carried) = findPredecessors(ua)
      dbg(s"${dquote(ua)} forward: ${forward.map{dquote(_)}.mkString(",")} carried: ${carried.map{dquote(_)}.mkString(",")}")
      (ua -> forward,ua -> carried)

    }.unzip.map1 { _.toMap }.map2 { _.toMap }

    // Perform transitive reduction on the reachable set. Forward and carried separately to keep
    // them DAG.  
    
    type AdjList = Map[UnrolledAccess[A],List[(UnrolledAccess[A], Int)]]
    // Use dfs to get all reachable access and the most relaxed buffer depth (largest) on that path
    def getReachable(adjlist:AdjList, ua:UnrolledAccess[A]):List[(UnrolledAccess[A], Int)] = {
      val reached = adjlist(ua)
      reached ++ reached.flatMap { case (neighbor, depth) => 
        getReachable(adjlist,neighbor).map { case (reached, depth2) => reached -> math.max(depth, depth2) }
      }
    }
    def transitiveReduce(adjlist:AdjList,forward:Boolean):AdjList = {
      adjlist.map { case (ua, reachable) =>
        val reachedByNeighbors = reachable.flatMap { case (reached, depth) => 
          getReachable(adjlist, reached).map { case (reached, depth2) => reached -> math.max(depth, depth2) }
        }
        ua -> reachable.filterNot { case (reached, depth) => 
          // reachedByNeighbors.exists { case (nreached, ndepth) => nreached == reached && (ndepth <= depth) }
            reachedByNeighbors.exists { case (nreached, ndepth) =>
                nreached == reached && ((ndepth < depth) || (if (forward) ndepth == depth else nreached.progorder > reached.progorder))
          }
        }
      }
    }
    val reducedForward = transitiveReduce(forward, true)
    val reducedCarried = transitiveReduce(carried, false)

    sorted.foreach { ua =>
      dbg(s"${dquote(ua)} rforward:${reducedForward(ua).map{dquote(_)}.mkString(",")} rcarried: ${reducedCarried(ua).map{dquote(_)}.mkString(",")}")
    }

    sorted.foreach { ua =>
      reducedForward(ua).foreach { case (prev,depth) =>
        insertBarrier(prev, ua, false,depth)
      }
      reducedCarried(ua).foreach { case (prev,depth) =>
        insertBarrier(prev, ua, true, depth)
      }
    }
    sorted
  }

  override def dquote(n:Any) = n match {
    case (a,b) => s"(${dquote(a)},${dquote(b)})"
    case n:UnrolledAccess[_] => s"UA[${n.progorder}]"
    case _ => super.dquote(n)
  }

}

/*
 * Group of accesses belong to the same pre unrolled access, sorted by unrolling order
 * */
case class UnrolledAccess[T<:PIRNode](lanes:List[T]) {
  def progorder = lanes.head.progorder.get
  def ctrl = lanes.head.getCtrl
  def srcCtx = lanes.head.srcCtx.v.getOrElse(s"No source context")
}
