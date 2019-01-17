package pir
package pass

import pir.node._
import prism.graph._
import prism.graph.implicits._

import scala.collection.mutable

// BFS is slightly faster than DFS
class ConstantPropogation(implicit compiler:PIR) extends PIRTraversal with Transformer with BFSBottomUpTopologicalTraversal with UnitTraversal with BufferAnalyzer {

  val forward = true

  var memLowerHasRun = false
  var globalInsertHasRun = false

  override def initPass = {
    super.initPass
    memLowerHasRun = compiler.hasRun[MemoryLowering]
    globalInsertHasRun = compiler.hasRun[GlobalInsertion]
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

  val CounterRange = MatchRule[Counter, (Counter, Range)] { counter =>
    val min = counter.min.T
    val step = counter.step.T
    val max = counter.max.T
    val par = counter.par
    dbg(s"isInner=${counter.isInner}")
    val range = (min, step, max) match {
      case (Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) if config.forceAlign =>
        val bound = if (counter.isInner) 1 else par
        (0 until bound)
      case (Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) =>
        var bound = ((max - min) /! step) % par
        if (bound == 0) {
          if (counter.isInner) bound = 1 else bound = par
        }
        if (bound > counter.valids.size) {
          bound = 0 // vectorized valid. Cannot eliminate
        }
        dbg(s"Constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until $bound)")
        (0 until bound)
      case _ =>
        dbg(s"None constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until 1)")
        (0 until 1)
    }
    if (range.nonEmpty) Some((counter, range)) else None
  }

  val RouteThrough1 = MatchRule[MemWrite, (MemWrite, MemRead, MemWrite)] { n =>
    if (!memLowerHasRun && !globalInsertHasRun && n.en.isConnected) {
      n.data.T match {
        case r1:MemRead if !r1.en.isConnected =>
          val w1s = r1.mem.T.inAccesses
          if (w1s.size == 1) {
            val w1 = w1s.head.as[MemWrite]
            Some((w1, r1, n))
          } else None
        case _ => None
      }
    } else None
  }

  val RouteThrough2 = MatchRule[BufferWrite, (BufferWrite, BufferRead, BufferWrite)] { n =>
      n.data.T match {
        case r1:BufferRead /*if r1.done.evalTo(n.done.T) */=> Some((r1.inAccess.as[BufferWrite], r1, n))
        case _ =>  None
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
      case n:Shuffle if n.from.T == n.to.T =>
        dbgblk(s"Shuffle($n)") {
          val base = assertOne(n.base.connected, s"$n.base.connected").as[Output]
          swapOutput(n.out, base)
        }
      case WrittenByConstData(read:MemRead, c:Const) =>
        dbgblk(s"WrittenByConstData($read, $c)") {
          swapOutput(read.out, c.out)
        }
      case EvalOp(n, c) =>
        dbgblk(s"EvalOp($n, $c)") {
          val const = within(n.parent.get.as[PIRNode], n.ctrl.get) { allocConst(c) }
          swapOutput(n.out, const.out)
          addAndVisitNode(const, ())
        }
      case CounterRange(counter, range) => 
        dbgblk(s"CounterRange($counter)") {
          val ctrler = counter.parent.get.as[Controller]
          val const = within(ctrler.parent.get.as[PIRNode], counter.ctrl.get) { allocConst(true) }
          range.foreach { i =>
            val out = counter.valids(i).out
            swapOutput(out, const.out)
          }
          addAndVisitNode(const, ())
        }
      case RouteThrough1(w1, r1, w2) =>
        val mem = w2.mem.T
        disconnect(w2.mem, mem)
        val mw1 = within(w1.parent.get.as[PIRNode]) {
          mirrorAll(List(w1))(w1).as[MemWrite]
        }
        mw1.mem(mem)
        dbg(s"Route through $w1 -> $r1 -> $w2 -> $mem detected")
        dbg(s" => $mw1 -> $mem")
      case RouteThrough2(w1, r1, w2) =>
        val outs = w2.outAccesses
        disconnect(w1.out, r1)
        outs.foreach { out => disconnect(w2.out, out) }
        w1.out(outs.map { _.in })
        dbg(s"Route through $w1 -> $r1 -> $n -> $outs detected")
        dbg(s" => $w1 -> $outs")
      case n => 
    }

  }

  // Breaking loop in traversal
  override def visitIn(n:N):List[N] = n match {
    case n:LocalOutAccess => n.in.neighbors
    case n => super.visitIn(n)
  }

  override def visitOut(n:N):List[N] = super.visitOut(n).filter {
    case x:LocalOutAccess => x.in.isConnectedTo(n)
    case x => true
  }

}
