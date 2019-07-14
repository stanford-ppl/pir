package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import prism.graph.implicits._
import spade.param._

import scala.collection.mutable

// BFS is slightly faster than DFS
class RewriteTransformer(implicit compiler:PIR) extends PIRTraversal with PIRTransformer with BFSBottomUpTopologicalTraversal with UnitTraversal with BufferAnalyzer {

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

  def outputMatch(out1:Output[PIRNode], out2:Output[PIRNode]) = (out1, out2) match {
    case (out1, out2) if out1 == out2 => true
    case (OutputField(Const(out1), "out"), OutputField(Const(out2), "out")) if out1 == out2 => true
    case (OutputField(Const(List(out1)), "out"), OutputField(Const(out2), "out")) if out1 == out2 => true
    case (OutputField(Const(out1), "out"), OutputField(Const(List(out2)), "out")) if out1 == out2 => true
    case (OutputField(c1:UnitController, "valid" | "done"), OutputField(c2:UnitController, "valid" | "done")) => c1 == c2
    case (out1, out2) => false
  }

  def matchInput(in1:Input[PIRNode], in2:Input[PIRNode]) = (in1, in2) match {
    case (in1, in2) if in1.connected.size != in2.connected.size => false
    case (in1, in2) => (in1.connected, in2.connected).zipped.forall { outputMatch _ }
  }

  val WrittenByConstData = MatchRule[MemRead, (MemRead, Const)] { reader =>
    val ConstData = MatchRule[MemWrite, Const] { write =>
      write.data.T match {
        case c@Const(_) if !write.en.isConnected && write.waitFors.isEmpty => Some(c)
        case _ => None
      }
    }
    reader.mem.T.inAccesses match {
      case List(writer@ConstData(c)) if writer.order.get < reader.order.get && matchInput(writer.en, reader.en) => Some((reader, c))
      case _ => None
    }
  }

  val EvalOp = MatchRule[OpDef, (OpDef, Any)] { case n@OpDef(op) =>
    val ins = n.inputs.map { 
      _.connected match {
        case List(OutputField(Const(c), "out")) => Literal(c)
        case outs => outs
      }
    }
    op.eval(ins).map { exp => 
      dbg(s"Const prop $n($op).ins(${ins}) to $exp")
      (n, exp)
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

  /*
   * w1 -> m1 -> r1 -> w2 -> m2
   * mw1 -> m2
   * */
  val RouteThrough1 = MatchRule[MemWrite, (MemWrite, Memory, MemRead, MemWrite, Memory)] { w2 =>
    if (!initializerHasRun && !memLowerHasRun && !globalInsertHasRun) {
      w2.data.T match {
        case r1:MemRead if matchInput(w2.en, r1.en) =>
          val m1 = r1.mem.T
          val m2 = w2.mem.T
          val w1s = m1.inAccesses
          if (w1s.size == 1 && m1.isFIFO == m2.isFIFO && !m2.nonBlocking.get) {
            val w1 = w1s.head.as[MemWrite]
            if (matchInput(w1.en, w2.en)) Some((w1, m1, r1, w2, m2))
            else None
          } else None
        case _ => None
      }
    } else None
  }

  // w1 -> r1 -> w2 -> r2s 
  // w1 -> r2s
  val RouteThrough2 = MatchRule[BufferWrite, (BufferWrite, BufferRead, BufferWrite)] { w2 =>
    w2.data.T match {
      case r1:BufferRead if !initializerHasRun & matchInput(r1.done, w2.done) 
                          & !w2.en.isConnected & w2.outAccesses.forall { !_.nonBlocking } =>
        val w1 = r1.inAccess.as[BufferWrite]
        Some((w1, r1, w2))
      case _ =>  None
    }
  }

  val FirstIter = MatchRule[OpDef, (Counter, OpDef)] { n =>
    (n.op, n.inputs.map{_.T}) match {
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
    (n.from, n.to) match {
      case (from, to) if !memoryPrunerHashRun => None
      case (from, to) if matchInput(from, to) => Some(n)
      case (from, to) => None
    }
  }

  val ShuffleUnmatch = MatchRule[Shuffle, Shuffle] { n =>
    (n.from, n.to) match {
      case (SConnected(OutputField(Const(from:List[_]),"out")), SConnected(OutputField(Const(to:List[_]),"out"))) if from.intersect(to).isEmpty => Some(n)
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
          case out@OutputField(Const(vs:List[_]), "out") if vs.forall { _ == true } => 
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
      case ShuffleUnmatch(n) =>
        dbgblk(s"ShuffleUnmatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val c = within(n.ctx.get, n.getCtrl) {
            allocConst(List.fill(n.inferVec.get)(n.filled))
          }
          swapOutput(n.out, c.out)
        }

      case WrittenByConstData(read:MemRead, c:Const) =>
        dbgblk(s"WrittenByConstData($read, $c)") {
          swapOutput(read.out, c.out)
        }
      case FirstIter(ctr, n) if config.option[Boolean]("shuffle-hack") => // TODO: fix this
        dbgblk(s"FirstIter($ctr)") {
          swapOutput(n.out, ctr.isFirstIter)
        }
      case EvalOp(n, exp) =>
        dbgblk(s"EvalOp($n, $exp)") {
          exp match {
            case Literal(c) =>
              val const = within(n.parent.get, n.ctrl.get) { allocConst(c) }
              swapOutput(n.out, const.out)
              addAndVisitNode(const, ())
            case List(out:Output[_]) =>
              swapOutput(n.out, out.as[Output[PIRNode]])
          }
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
      case RouteThrough1(w1, m1, r1, w2, m2) =>
        dbgblk(s"Route through $w1 -> $m1 -> $r1 -> $w2 -> $m2 detected") {
          disconnect(w2.mem, m2)
          val mw1 = within(w1.parent.get) { mirrorAll(List(w1))(w1).as[MemWrite] }
          mw1.mirrorMetas(w1)
          mirrorSyncMeta(w2, mw1)
          dbg(s" => $mw1 -> $m2")
          mw1.mem(m2)
          val name = zipReduce(m1.name.v, m2.name.v) { _ + "/" + _ }
          m2.name.reset
          m2.name.update(name)
        }
      case RouteThrough2(w1, r1, w2) =>
        val r2s = w2.outAccesses
        dbgblk(s"Route through $w1 -> $r1 -> $w2 -> $r2s detected => ") {
          dbg(s" => $w1 -> $r2s")
          mirrorSyncMeta(w2, w1)
          r2s.foreach { out => 
            val prevIn = out.in.connected
            swapInput(out, w2.out, w1.out)
            if (prevIn != out.in.connected) {
              val name = zipReduce(r1.name.v, out.name.v) { _ + "/" + _ }
              out.name.reset
              out.name.update(name)
            }
          }
        }
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
