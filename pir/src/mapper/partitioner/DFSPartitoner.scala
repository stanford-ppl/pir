package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait DFSPartitioner extends Partitioner with BufferAnalyzer {

  def getCosts(k:Any, x:Any) = {
    k match {
      case k:ArgFringe => Nil
      case k:DRAMFringe => Nil
      case k:MemoryContainer =>
        //x.getCost[InputCost] ::
        //x.getCost[OutputCost] ::
        Nil
      case k =>
        x.getCost[StageCost] ::
        x.getCost[InputCost] ::
        x.getCost[OutputCost] ::
        //x.getCost[FIFOCost] ::
        Nil
    }
  }

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val vs = fg.freeValuesOf(k)
        val vcost = vs.map { v => getCosts(k,v) }.maxBy { 
          case List(StageCost(sc), InputCost(sin, vin), OutputCost(sout,vout)) => 
            (sc, vin, vout, sin, sout)
        }
        dbg(s"Recover $k")
        dbg(s"value cost=$vcost")
        val ks = split(k, vcost).toSet
        Right(fg.mapFreeMap { _ - k ++ (ks, vs) })
      case x => super.recover(x)
    }
  }

  lazy val schedular = new DFSTopologicalTraversal with Schedular {
    val forward = false
  }
  def split(k:Any, vcost:Any):List[Any] = dbgblk(s"split($k)") {
    val kcost = getCosts(k, k)
    dbg(s"kcost=$kcost")
    k match {
      case k:ComputeContainer =>
        val ctxs = k.collectDown[Context]().flatMap { ctx => split(ctx, vcost).asInstanceOf[List[Context]] }
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
        (k::k.descendents).foreach { removeNode }
        globals
      case k:Context =>
        val scope = k.children.filter { include }
        val sctx = SplitContext(scope.asInstanceOf[List[PIRNode]])
        val res = split(sctx, vcost).asInstanceOf[List[SplitContext]]
        dbg(s"splits=${res.size}")
        val ctxs = within(k.global.get) {
          res.map { case sctx@SplitContext(scope) =>
            val ctx = Context()
            dbg(s"Create $ctx for $sctx")
            scope.foreach { n => swapParent(n, ctx) }
            removeCache(sctx)
            ctx
          }
        }
        ctxs.foreach { ctx =>
          dupDeps(k, ctx)
          bufferInput(ctx)
        }
        (k::k.descendents).foreach { removeNode }
        ctxs
      case k:SplitContext =>
        if (!notFit(kcost, vcost)) List(k)
        else {
          val nodes = schedular.scheduleScope(k.scope).asInstanceOf[List[PIRNode]]
          val (head, tail) = nodes.splitAt(nodes.size/2)
          split(SplitContext(head), vcost) ++ split(SplitContext(tail),vcost)
        }
    }
  }

  def include(n:N) = n match {
    case n:OpNode => true
    case n:LocalInAccess => true
    //case n:LocalOutAccess => true
    case n => false
  }

  override def removeNode(n:N) = {
    super.removeNode(n)
    removeCache(n)
  }

  def removeCache(n:Any) = {
    resetCacheOn {
      case `n` => true
      case (`n`, _) => true
      case _ => false
    }
  }

  def visitIn(scope:Context)(n:N):List[N] = (visitGlobalIn(n).flatMap {
    case x if !x.isDescendentOf(scope) => None
    case x => 
      val underScope = (x::x.ancestors).filter { _.parent.fold(false) { _ == scope } }.head
      underScope :: underScope.descendents
  }).distinct

  def dupDeps(from:Context, to:Context) = dbgblk(s"dupDeps($from, $to)") {
    var deps = to.accum(visitFunc=visitIn(from) _)
    deps = deps.filterNot { _ == to }
    deps ++= from.collectDown[TokenRead]()
    dbg(s"deps=$deps")
    val mapping = within(to) { mirrorAll(deps) }
    to.descendents.foreach { x =>
      x.localDeps.foreach { dep =>
        mapping.get(dep).foreach { toDep =>
          swapDep(x, dep, toDep)
        }
      }
    }
  }

  override protected def compCost(x:Any, ct:ClassTag[_]) = {
    switch[InputCost](x,ct) { 
      case x: Context => 
        val ins = x.collectDown[LocalOutAccess]()
        val (vins, sins) = ins.partition { _.getVec > 1 }
        InputCost(sins.size, vins.size)
      case x: SplitContext => 
        val deps = x.deps.filter { d =>
          include(d) || d.isInstanceOf[LocalOutAccess]
        }.distinct.toList
        dbg(s"deps=$deps")
        val ins = deps
        val (vins, sins) = ins.partition { _.getVec > 1 }
        InputCost(sins.size, vins.size)
    } orElse switch[OutputCost](x,ct) { 
      case x: Context => 
        val outs = x.collectDown[LocalInAccess]()
        val (vouts, souts) = outs.partition { _.getVec > 1 }
        OutputCost(souts.size, vouts.size)
      case x: SplitContext => 
        val depedFroms = x.depedsTo.keys.toSeq
        dbg(s"depedFroms=${depedFroms.toList}")
        val outs = depedFroms
        val (vouts, souts) = outs.partition { _.getVec > 1 }
        OutputCost(souts.size, vouts.size)
    } orElse switch[StageCost](x,ct) { 
      case x: SplitContext => 
        StageCost(x.scope.collect{ case op:OpNode => op }.size)
    } getOrElse super.compCost(x, ct)
  }

}

case class SplitContext(scope:List[PIRNode]) extends {
  override def toString = s"SplitContext${hashCode}"
  def deps:Seq[Node[_]] = {
    val descendents = scope.flatMap { n => n :: n.descendents }
    val edges = descendents.toIterator.flatMap { _.localEdges }
    val ins = edges.collect { case i:Input => i }
    ins.flatMap { in =>
      in.connected.map { _.src }
      .filterNot { descendents.contains } 
    }.toSeq.distinct
  }

  def depedsTo:Map[Node[_], Seq[Node[_]]] = {
    val descendents = scope.flatMap { n => n::n.descendents }
    val edges = descendents.toIterator.flatMap { _.localEdges }
    val outs = edges.collect { case i:Output => i }
    outs.flatMap { out =>
      out.connected.map { _.src }
      .filterNot { descendents.contains } 
      .map { deped => (deped, out.src) } 
    }.toSeq.groupBy { _._2 }.mapValues { _.map { _._1 } }
  }

}

