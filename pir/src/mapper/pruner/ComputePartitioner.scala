package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait ComputePartitioner extends CUPruner {

  lazy val schedular = new PIRTraversal with DFSTopologicalTraversal with Schedular {
    val forward = false
  }

  def split[T](k:T, vcost:List[Cost[_]]):List[T] = dbgblk(s"split($k)") {
    val kcost = getCosts(k)
    dbg(s"kcost=$kcost")
    (k match {
      case k:ComputeContainer =>
        val ctxs:List[Context] = k.collectDown[Context]().flatMap { ctx => split(ctx, vcost) }
        val globals = ctxs.map { ctx =>
          within(pirTop) {
            val global = ComputeContainer()
            val gouts = ctx.collectOut[GlobalOutput]()
            swapParent(ctx, global)
            gouts.foreach { gout => swapParent(gout, global) }
            global
          }
        }
        globals.foreach { insertGlobalIO }
        removeNodes(k::k.descendents)
        globals
      case k:Context =>
        val scope = k.children.filter { include }
        val part = Partition(scope)
        val parts = split(part, vcost)
        dbg(s"partitions=${parts.size}")
        val ctxs = within(k.global.get, k.ctrl.get) {
          parts.map { case part@Partition(scope) =>
            val ctx = Context()
            dbg(s"Create $ctx for $part")
            scope.foreach { n => swapParent(n, ctx) }
            ctx
          }
        }
         // need to run in two pass to avoid duplicated allocation
        (ctxs,parts).zipped.foreach { case (ctx,part) => dupDeps(k, ctx, part.getCost[InputCost]) }
        ctxs.foreach { ctx => bufferInput(ctx) }
        (part::parts).foreach { removeCache }
        removeNodes(k::k.descendents)
        ctxs
      case k:Partition =>
        if (fit(kcost, vcost)) List(k)
        else {
          val nodes = schedular.scheduleScope(k.scope)
          //dbg(s"schedule:")
          //nodes.foreach { n => dbg(s"$n") }
          val (head, tail) = nodes.splitAt(nodes.size/2)
          split(Partition(head), vcost) ++ split(Partition(tail),vcost)
        }
    }).as[List[T]]
  }

  def include(n:N) = n match {
    case n:OpNode => true
    case n:LocalInAccess => true
    //case n:LocalOutAccess => true // Not include read so they can be duplicated at each partition
    case n => false
  }

  def visitIn(scope:Context)(n:N):List[N] = (visitGlobalIn(n).flatMap {
    case x if !x.isDescendentOf(scope) => None
    case x => 
      val underScope = (x::x.ancestors).filter { _.parent.fold(false) { _ == scope } }.head
      underScope :: underScope.descendents
  }).distinct

  def dupDeps(from:Context, to:Context, incost:InputCost) = dbgblk(s"dupDeps($from, $to)") {
    var deps = getDeps(to, visitIn(from))
    val noInput = (incost.sin + incost.vin) == 0
    if (noInput && deps.filter { _.isInstanceOf[Controller] }.isEmpty ) {
      val ctrlerDeps = getCtrlerDeps(from, visitIn(from))
      assert(ctrlerDeps.nonEmpty, s"$from has no ctrlers and no inputs")
      deps ++= ctrlerDeps
    }
    deps = deps.distinct
    val mapping = within(to) { mirrorAll(deps) }
    to.descendents.foreach { x =>
      x.localDeps.foreach { dep =>
        mapping.get(dep).foreach { toDep =>
          swapDep(x, dep, toDep.as[PIRNode])
        }
      }
    }
  }

  def scheduleBy(s:Int, v:Int, numOp: => Int) = dbgblk(s"scheduleBy($s, $v, $numOp)") {
    if (spadeParam.scheduled) {
      val factor = spadeParam.vecNetParam.fold { 1 } { vecNet =>
        if (vecNet.numVC > 0) { // dynamic network
          Math.max(numOp, 1)
        } else 1
      }
      (s /! factor, v /! factor)
    } else {
      (s,v)
    }
  }

  override protected def compCost(x:Any, ct:ClassTag[_]) = {
    switch[InputCost](x,ct) { 
      case x: GlobalContainer =>
        val ins = x.collectDown[GlobalInput]()
        val (vins, sins) = ins.partition { _.getVec > 1 }
        val (nsin, nvin) = scheduleBy(sins.size, vins.size, x.collectDown[Context]().map { _.collectDown[OpNode]().size }.min)
        InputCost(nsin, nvin)
      case x: Context => 
        val ins = x.collectDown[LocalOutAccess]()
        val (vins, sins) = ins.partition { _.getVec > 1 }
        val (nsin, nvin) = scheduleBy(sins.size, vins.size, x.collectDown[OpNode]().size)
        InputCost(nsin, nvin)
      case x: Partition => 
        val deps = x.deps.filter { d =>
          include(d) || d.isInstanceOf[LocalOutAccess]
        }.distinct.toList
        dbg(s"deps=$deps")
        val ins = deps
        val (vins, sins) = ins.partition { _.getVec > 1 }
        val (nsin, nvin) = scheduleBy(sins.size, vins.size, x.scope.size)
        InputCost(nsin, nvin)
    } orElse switch[OutputCost](x,ct) { 
      case x: GlobalContainer => 
        val outs = x.collectDown[LocalInAccess]()
        val (vouts, souts) = outs.partition { _.getVec > 1 }
        val (nsout, nvout) = scheduleBy(souts.size, vouts.size, x.collectDown[Context]().map { _.collectDown[OpNode]().size }.min)
        OutputCost(nsout, nvout)
      case x: Context => 
        val outs = x.collectDown[LocalInAccess]()
        val (vouts, souts) = outs.partition { _.getVec > 1 }
        val (nsout, nvout) = scheduleBy(souts.size, vouts.size, x.collectDown[OpNode]().size)
        OutputCost(nsout, nvout)
      case x: Partition => 
        val depedFroms = x.depedsTo.keys.toSeq
        dbg(s"depedFroms=${depedFroms.toList}")
        val outs = depedFroms
        val (vouts, souts) = outs.partition { _.getVec > 1 }
        val (nsout, nvout) = scheduleBy(souts.size, vouts.size, x.scope.size)
        OutputCost(nsout, nvout)
    } orElse switch[StageCost](x,ct) { 
      case x: Partition => 
        StageCost(x.scope.collect{ case op:OpNode => op }.size)
    } getOrElse super.compCost(x, ct)
  }

}

case class Partition(scope:List[PIRNode]) extends {
  override def toString = super.toString
  def deps:Seq[PIRNode] = {
    val descendents = scope.flatMap { n => n :: n.descendents }
    val edges = descendents.toIterator.flatMap { _.localEdges }
    val ins = edges.collect { case i:Input[PIRNode] => i }
    ins.flatMap { in =>
      in.connected.map { _.src }
      .filterNot { descendents.contains } 
    }.toSeq.distinct
  }

  def depedsTo:Map[PIRNode, Seq[PIRNode]] = {
    val descendents = scope.flatMap { n => n::n.descendents }
    val edges = descendents.toIterator.flatMap { _.localEdges }
    val outs = edges.collect { case i:Output[PIRNode] => i }
    outs.flatMap { out =>
      out.connected.map { _.src }
      .filterNot { descendents.contains } 
      .map { deped => (deped, out.src) } 
    }.toSeq.groupBy { _._2 }.mapValues { _.map { _._1 } }
  }

}

