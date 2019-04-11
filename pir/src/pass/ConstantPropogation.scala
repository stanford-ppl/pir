package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import prism.graph.implicits._
import spade.param._

import scala.collection.mutable

// BFS is slightly faster than DFS
class ConstantPropogation(implicit compiler:PIR) extends PIRTraversal with Transformer with BFSBottomUpTopologicalTraversal with UnitTraversal with BufferAnalyzer {

  val forward = true

  var memLowerHasRun = false
  var memPrunerHasRun = false
  var globalInsertHasRun = false
  var initializerHasRun = false
  var memoryPrunerHashRun = false

  override def initPass = {
    super.initPass
    memLowerHasRun = compiler.hasRun[MemoryLowering]
    globalInsertHasRun = compiler.hasRun[GlobalInsertion]
    initializerHasRun = compiler.hasRun[TargetInitializer]
    memoryPrunerHashRun = compiler.hasRun[MemoryPruner]
  }

  val WrittenByConstData = MatchRule[MemRead, (MemRead, Const)] { read =>
    val ConstData = MatchRule[MemWrite, Const] { write =>
      write.data.T match {
        case c@Const(_) => Some(c)
        case _ => None
      }
    }
    read.mem.T.inAccesses match {
      case List(ConstData(c)) => Some((read, c))
      case _ => None
    }
  }

  val EvalOp = MatchRule[OpDef, (OpDef, Any)] { case n@OpDef(op) =>
    val ins = n.input.T.map { 
      case Const(c) => Some(c)
      case out => None
    }
    op.eval(ins).map { c => 
      dbg(s"Const prop $n($op).ins(${ins}) to $c")
      (n, c)
    }
  }

  // Counter valids with par < maxValid should be always true
  val CounterConstValid = MatchRule[Counter, (Counter, Int)] { counter =>
    val min = counter.min.T
    val step = counter.step.T
    val max = counter.max.T
    val par = counter.par
    val maxValid = (min, step, max) match {
      case (Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) =>
        var bound = ((max - min) /! step) % par
        if (bound == 0) {
          bound = par
        }
        dbg(s"Constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until $bound)")
        bound
      case _ =>
        dbg(s"None constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until 1)")
        1
    }
    Some((counter, maxValid))
  }

  val RouteThrough1 = MatchRule[MemWrite, (MemWrite, MemRead, MemWrite)] { n =>
    if (!initializerHasRun && !memLowerHasRun && !globalInsertHasRun && n.en.isConnected) {
      n.data.T match {
        case r1:MemRead if !r1.en.isConnected =>
          val w1s = r1.mem.T.inAccesses
          if (w1s.size == 1 && r1.mem.T.isFIFO == n.mem.T.isFIFO) {
            val w1 = w1s.head.as[MemWrite]
            Some((w1, r1, n))
          } else None
        case _ => None
      }
    } else None
  }

  // (W) => r1 (R) => n (W)
  val RouteThrough2 = MatchRule[BufferWrite, (BufferWrite, BufferRead, BufferWrite)] { n =>
    n.data.T match {
      case r1:BufferRead if !initializerHasRun => Some((r1.inAccess.as[BufferWrite], r1, n))
      case _ =>  None
    }
  }

  val FirstIter = MatchRule[OpDef, (Counter, OpDef)] { n =>
    (n.op, n.input.T) match {
      case (FixEql, List(iter:CounterIter, Const(c))) =>
        val ctr = iter.counter.T
        ctr.min.T match {
          case Some(Const(min)) if min == c => Some(ctr, n)
          case _ => None
        }
      case (_) => None
    }
  }

  val ShuffleMatch = MatchRule[Shuffle, Shuffle] { n =>
    (n.from.T, n.to.T) match {
      case (from, to) if !memoryPrunerHashRun => None
      case (from, to) if from == to => Some(n)
      case (Const(from), Const(to)) if from == to => Some(n)
      case (Const(List(from:Int)), Const(to:Int)) if from == to => Some(n)
      case (Const(from:Int), Const(List(to))) if from == to => Some(n)
      case (from, to) => None
    }
  }

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${quote(n)}") */{
    super.visitNode(n)
    n.to[PIRNode].foreach { n =>
      n.localIns.filter { _.as[Field[_]].name == "en" }.foreach { en =>
        en.connected.distinct.foreach {
          case out@OutputField(Const(true), "out") => 
            en.disconnectFrom(out)
            dbg(s"${en.src}.${en} disconnect ${out.src}.${out}")
          case _ => 
        }
      }
    }
    n match {
      case ShuffleMatch(n) =>
        dbgblk(s"ShuffleMatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val base = assertOne(n.base.connected, s"$n.base.connected")
          swapOutput(n.out, base)
        }
      case WrittenByConstData(read:MemRead, c:Const) =>
        dbgblk(s"WrittenByConstData($read, $c)") {
          swapOutput(read.out, c.out)
        }
      case FirstIter(ctr, n) if config.option[Boolean]("shuffle-hack") => // TODO: fix this
        dbgblk(s"FirstIter($ctr)") {
          swapOutput(n.out, ctr.isFirstIter)
        }
      case EvalOp(n, c) =>
        dbgblk(s"EvalOp($n, $c)") {
          val const = within(n.parent.get, n.ctrl.get) { allocConst(c) }
          swapOutput(n.out, const.out)
          addAndVisitNode(const, ())
        }
      case CounterConstValid(counter, maxValid) => 
        dbgblk(s"CounterConstValid($counter, $maxValid)") {
          val ctrler = counter.parent.get
          val const = within(ctrler.parent.get, counter.ctrl.get) { allocConst(true) }
          counter.valids.foreach { case valid@CounterValid(is) =>
            if (is.forall(_ < maxValid)) {
              dbg(s"Set $valid with is=$is to true")
              swapOutput(valid.out, const.out)
            }
          }
          addAndVisitNode(const, ())
        }
      case RouteThrough1(w1, r1, w2) =>
        val mem = w2.mem.T
        dbg(s"Route through $w1 -> $r1 -> $w2 -> $mem detected")
        disconnect(w2.mem, mem)
        val mw1 = within(w1.parent.get) {
          mirrorAll(List(w1))(w1).as[MemWrite]
        }
        dbg(s" => $mw1 -> $mem")
        mw1.mem(mem)
      case RouteThrough2(w1, r1, w2) =>
        val outs = w2.outAccesses
        dbg(s"Route through $w1 -> $r1 -> $w2 -> $outs detected")
        dbg(s" => $w1 -> $outs")
        outs.foreach { out => disconnect(w2.out, out) }
        w1.out(outs.map { _.in })
      case n => 
    }

  }

  // Breaking loop in traversal
  override def visitIn(n:N):List[N] = n match {
    case n:LocalOutAccess => n.in.neighbors.toList
    case n => super.visitIn(n)
  }

  override def visitOut(n:N):List[N] = super.visitOut(n).filter {
    case x:LocalOutAccess => x.in.isConnectedTo(n)
    case x => true
  }

}
