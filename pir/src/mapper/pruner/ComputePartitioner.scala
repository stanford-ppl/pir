package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait ComputePartitioner extends CUPruner with ExternComputePartitioner with LocalRetimer with GlobalRetimer { self =>

  var splitAlgo:String = "bfs"
  private def scheduler = splitAlgo match {
    case "bfs" => new PIRTraversal with BFSTopologicalTraversal with Scheduler { val forward = config.splitForward }
    case "dfs" => new PIRTraversal with DFSTopologicalTraversal with Scheduler { val forward = config.splitForward }
    case _ => new PIRTraversal with DFSTopologicalTraversal with Scheduler { val forward = config.splitForward }
  }

  def split[T](k:T, vcost:List[Cost[_]]):List[T] = dbgblk(s"split($k)") {
    val kcost = getCosts(k)
    dbg(s"kcost=$kcost")
    (k match {
      case k:ComputeContainer =>
        val ctxs:List[Context] = k.collectDown[Context]().flatMap { ctx => split(ctx, vcost) }
        val globals = ctxs.map { ctx =>
          within(pirTop) {
            val global = stage(ComputeContainer().delay.update(ctx.delay.v))
            ctx.delay.reset
            swapParent(ctx, global)
            global
          }
        }
        val retimes = retimeGlobal(globals, vcost.collectFirst{ case cost:StageCost => cost.quantity }.get)
        removeNodes(k.descendentTree)
        globals ++ retimes
      case k:Context if fit(kcost, vcost) => List(k)
      case k:Context =>
        //breakPoint(s"$k")
        val scope = k.children.filter { include }
        val delays = retimeLocal(k,scope)
        val part = new Partition(scope ++ delays)
        val parts = split(part, vcost)
        dbg(s"partitions=${parts.size}")
        val ctxs = within(k.global.get, k.ctrl.get) {
          parts.map { case part@Partition(scope) =>
            val ctx = stage(Context().delay.update(part.delay))
            dbg(s"Create $ctx for $part")
            scope.foreach { n => swapParent(n, ctx) }
            ctx
          }
        }
         // need to run in two pass to avoid duplicated allocation
        alias ++= ctxs.map { ctx => (ctx, k) }
        ctxs.foreach { ctx => bufferInput(ctx) }
        alias.clear
        dupDeps(ctxs, from=Some(k))
        (part::parts).foreach { removeCache }
        removeNodes(k.descendentTree)
        ctxs.foreach { ctx => if (ctx.ctrlers.isEmpty) ctx.streaming := true }
        //breakPoint(s"$k ${ctxs}")
        ctxs
      case k:Partition if fit(kcost, vcost) => List(k)
      case k:Partition =>
        val nodes = scheduler.scheduleScope(k.scope)
        splitAlgo match {
          case "solver" => externSplit(nodes, vcost)
          case _ => heuristicSplit(nodes, vcost)
        }
    }).as[List[T]]
  }

  def withAlgo[T](algo:String)(block: => T) = {
    val saved = splitAlgo
    splitAlgo = algo
    val res = block
    splitAlgo = saved
    res
  }

  def heuristicSplit(nodes:List[PIRNode],vcost:List[Cost[_]]):List[Partition] = {
    if (nodes.size==0) return Nil
    var canFit = true
    var (inpart, rest) = nodes.splitAt(1)
    while (canFit && rest.nonEmpty) {
      inpart = inpart :+ rest.head
      rest = rest.tail
      val part = new Partition(inpart)
      val kcost = getCosts(part)
      val isolateRetime = inpart.forall { 
        case d:Delay => !inpart.contains(d.in.T) && !inpart.contains(d.out.T.head)
        case _ => true
      }
      canFit = fit(kcost, vcost) && isolateRetime
      if (!canFit) {
        rest = inpart.last :: rest
        inpart = inpart.slice(0,inpart.size-1)
      }
    }
    dbg(s"Split ${inpart.size}/${nodes.size}")
    if (splitAlgo=="dfs") {
      val restSorted = scheduler.scheduleScope(rest.reverse)
      heuristicSplit(restSorted,vcost) :+ new Partition(inpart)
    } else {
      heuristicSplit(rest,vcost) :+ new Partition(inpart)
    }
  }

  val alias = scala.collection.mutable.Map[Context,Context]()

  override def childDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    super.childDone(ctrl, alias.get(ctx).getOrElse(ctx))
  }

  def include(n:PIRNode) = n match {
    case n:LocalOutAccess => false // Not include read so they can be duplicated at each partition
    case n:Const => false
    case n:ControlBlock => false
    case UnderControlBlock(cb) => false
    //case n:LocalInAccess => true
    case n:Def => true
    //case n => true
  }

  override protected def compCost(x:Any, ct:ClassTag[_]) = {
    switch[InputCost](x,ct) { 
      case x: Partition => 
        val deps = x.deps.filter { d =>
          include(d) || d.isInstanceOf[LocalOutAccess]
        }.distinct.toList
        //dbg(s"deps=$deps")
        val ins = deps
        val (vins, sins) = ins.partition { isVec(_) }
        InputCost(sins.size, vins.size).scheduledBy(x.scope.size)
    } orElse switch[OutputCost](x,ct) { 
      case x: Partition => 
        val depedFroms = x.depedsTo.keys.toSeq
        //dbg(s"depedFroms=${depedFroms.toList}")
        val outs = depedFroms
        val (vouts, souts) = outs.partition { isVec(_) }
        OutputCost(souts.size, vouts.size).scheduledBy(x.scope.size)
    } orElse switch[StageCost](x,ct) { 
      case x: Partition => 
        StageCost(x.scope.collect{ case op:OpNode => op }.size)
    } getOrElse super.compCost(x, ct)
  }

}

class Partition(val scope:List[PIRNode]) {
  var delay:Option[Int] = None
  override def toString = s"${super.toString}(${scope.size},delay=$delay)"
  def deps:Seq[PIRNode] = {
    val descendents = scope.flatMap { n => n.descendentTree }
    val edges = descendents.toIterator.flatMap { _.localEdges }
    val ins = edges.collect { case i:Input[PIRNode] => i }
    ins.flatMap { in =>
      in.connected.map { _.src }
      .filterNot { descendents.contains } 
    }.toSeq.distinct
  }

  def depedsTo:Map[PIRNode, Seq[PIRNode]] = {
    val descendents = scope.flatMap { n => n.descendentTree }
    val edges = descendents.toIterator.flatMap { _.localEdges }
    val outs = edges.collect { case i:Output[PIRNode] => i }
    outs.flatMap { out =>
      out.connected.map { _.src }
      .filterNot { descendents.contains } 
      .map { deped => (deped, out.src) } 
    }.toSeq.groupBy { _._2 }.mapValues { _.map { _._1 } }
  }

}
object Partition {
  def unapply(x:Partition):Option[List[PIRNode]] = Some(x.scope)
}

