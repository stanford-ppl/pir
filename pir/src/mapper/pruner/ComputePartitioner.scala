package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait ComputePartitioning extends CUPruner with PartitionCost {
  var splitAlgo:String = "bfs"
  def withAlgo[T](algo:String)(block: => T) = {
    val saved = splitAlgo
    splitAlgo = algo
    val res = block
    splitAlgo = saved
    res
  }

  protected def scheduler = splitAlgo match {
    case "bfs" => new PIRTraversal with BFSTopologicalTraversal with Scheduler { val forward = config.splitForward }
    case "dfs" => new PIRTraversal with DFSTopologicalTraversal with Scheduler { val forward = config.splitForward }
    case _ => new PIRTraversal with DFSTopologicalTraversal with Scheduler { val forward = config.splitForward }
  }

  def partition(nodes:List[PIRNode], vcost:List[Cost[_]]):List[Partition] = 
    err(s"Unsupported split-algo=${splitAlgo}")
}

trait TraversalPartitioner extends ComputePartitioning {

  override def partition(nodes:List[PIRNode],vcost:List[Cost[_]]):List[Partition] = 
    if (splitAlgo=="dfs" || splitAlgo == "bfs") {
    if (nodes.size==0) return Nil
    var canFit = true
    var (inpart, rest) = nodes.splitAt(0)
    var prevPart:Partition = null
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
      } else {
        prevPart = part
      }
    }
    dbg(s"Split ${inpart.size}/${nodes.size} $prevPart")
    if (splitAlgo=="dfs") {
      val restSorted = scheduler.scheduleScope(rest.reverse)
      partition(restSorted,vcost) :+ prevPart
    } else {
      partition(rest,vcost) :+ prevPart
    }
  } else super.partition(nodes, vcost)

}

trait ComputePartitioner extends ComputePartitioning
  with TraversalPartitioner with CVXPyComputePartitioner with GurobiComputePartitioner
  with LocalRetimer with GlobalRetimer { self =>

  def split[T](k:T, vcost:List[Cost[_]]):List[T] = dbgblk(s"split($k)") {
    val kcost = getCosts(k)
    dbg(s"kcost=$kcost")
    (k match {
      case k:ComputeContainer =>
        val ctxs:List[Context] = k.collectDown[Context]().flatMap { ctx => split(ctx, vcost) }
        val globals = ctxs.map { ctx =>
          within(pirTop, k.srcCtx.get) {
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
        val ctxs = within(k.global.get, k.ctrl.get, k.srcCtx.get) {
          parts.map { case part@Partition(scope) =>
            val ctx = stage(Context().delay.update(part.delay).name.mirror(k.name))
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
        withAlgo(config.splitAlgo) {
          partition(nodes, vcost)
        }
    }).as[List[T]]
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

  override def isPartitionDep(out:Output[PIRNode]) = {
    val dep = out.src
    include(dep) || dep.isInstanceOf[LocalOutAccess]
  }

}

