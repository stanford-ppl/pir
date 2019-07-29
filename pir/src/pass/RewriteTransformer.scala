package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import prism.graph.implicits._
import spade.param._
import prism.util._

import scala.collection.mutable

// BFS is slightly faster than DFS
class RewriteTransformer(implicit compiler:PIR) extends PIRTraversal with PIRTransformer 
  with BFSBottomUpTopologicalTraversal with UnitTraversal 
  with BufferAnalyzer with RuntimeUtil {

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

  def matchRate(a1:LocalAccess, a2:LocalAccess) = {
    (compScale(a1), compScale(a2)) match {
      case (Unknown, _) => false
      case (_, Unknown) => false
      case (s1, s2) => s1 == s2
    }
  }

  override def compScheduleFactor(n:Context):Int = 1 
  // HACK: For now, don't care whether it's scheduled or not to check
  // rate matching
  
  val rules = mutable.ListBuffer[Rule[_]]()
  val xrules = mutable.ListBuffer[XRule[_]]()

  case class Rule[A:ClassTag](lambda:A => Unit) {
    rules += this
    def apply(x:Any):Unit = {
      x.to[A].foreach { x => lambda(x) }
    }
  }

  case class XRule[A:ClassTag](lambda:A => Option[Any]) {
    xrules += this
    def apply(x:Any):Option[Any] = {
      x.to[A].flatMap { x => lambda(x) }
    }
  }

  XRule[MemRead] { reader =>
    val ConstData = MatchRule[MemWrite, Const] { write =>
      write.data.T match {
        case c@Const(_) if !write.en.isConnected && write.waitFors.isEmpty => Some(c)
        case _ => None
      }
    }
    reader.mem.T.inAccesses match {
      case List(writer@ConstData(c)) if writer.order.get < reader.order.get && matchInput(writer.en, reader.en) => 
        dbgblk(s"WrittenByConstData($reader, $c)") {
          swapOutput(reader.out, c.out)
        }
        Some(reader)
      case _ => None
    }
  }

  XRule[BufferRead] { n =>
    dbg(s"$n ${n.ctx}")
    if (n.ctx.get.streaming.get) None else {
      val writer = n.inAccess.as[BufferWrite]
      writer.data match {
        case SC(OutputField(c:Const, "out")) if !writer.en.isConnected =>
          dbgblk(s"WrittenByConstData($n, $c)") {
            val mc = within(n.parent.get, n.getCtrl) { allocConst(c.value) }
            swapOutput(n.out, mc.out)
          }
          Some(n)
        case _ => None
      }
    }
  }

  XRule[OpDef] { case n@OpDef(op) =>
    val ins = n.inputs.map { 
      _.connected match {
        case List(OutputField(Const(c), "out")) => Literal(c)
        case outs => outs
      }
    }
    op.eval(ins).map { exp => 
      dbg(s"Const prop $n($op).ins(${ins}) to $exp")
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
    }
  }

  // Counter valids with par < maxValid should be always true
  XRule[Counter] { counter =>
    if (counter.valids.exists { _.out.isConnected }) {
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
      dbgblk(s"CounterConstValid($counter, $maxValid)") {
        val ctrler = counter.parent.get
        var const:Const = null
        counter.valids.foreach { case valid@CounterValid(is) =>
          if (is.forall(_ < maxValid)) {
            dbg(s"Set $valid with is=$is to true")
            if (const == null)
              const = within(ctrler.parent.get, counter.ctrl.get) { allocConst(true) }
            swapOutput(valid.out, const.out)
          }
        }
        if (const != null)
          addAndVisitNode(const, ())
      }
      Some(counter)
    } else None
  }

  /*
   * w1 -> m1 -> r1 -> w2 -> m2
   * mw1 -> m2
   * */
  XRule[MemWrite] { w2 =>
    if (!initializerHasRun && !memLowerHasRun && !globalInsertHasRun) {
      w2.data.T match {
        case r1:MemRead if matchInput(w2.en, r1.en) =>
          val m1 = r1.mem.T
          val m2 = w2.mem.T
          val w1s = m1.inAccesses
          if (w1s.size == 1 && m1.isFIFO == m2.isFIFO && !m2.nonBlocking.get) {
            val w1 = w1s.head.as[MemWrite]
            if (matchInput(w1.en, w2.en)) {
              dbgblk(s"Route through (1) $w1 -> $m1 -> $r1 -> $w2 -> $m2 detected") {
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
              Some(w2)
            }
            else None
          } else None
        case _ => None
      }
    } else None
  }

  XRule[BufferRead] { r2 =>
    val w2 = r2.inAccess.as[BufferWrite]
    w2.data.T match {
      case r1:BufferRead if matchRate(w2, r1) & !w2.en.isConnected & !r2.nonBlocking =>
        val w1 = r1.inAccess.as[BufferWrite]
        dbgblk(s"Route through (3) $w1 -> $r1 -> $w2 -> $r2 detected => ") {
          dbg(s" => $w1 -> $r2")
          val go1 = w1.gout
          val gi2 = r2.gin
          (go1, gi2) match {
            case (Some(go1), Some(gi2)) =>
              // w1 -> go1 -> gi1 -> r1 -> w2 -> go2 -> gi2 -> r2
              // w1 -> go1 -> gi2 -> r2
              val go2 = gi2.in.T
              swapConnection(gi2.in, go2.out, go1.out)
            case (Some(go1), None) =>
              // w1 -> go1 -> gi1 -> r1 -> w2 -> r2
              // w1 -> go1 -> gi1 -> r2
              val gi1 = r1.gin.get
              swapConnection(r2.in, w2.out, gi1.out)
            case (None, Some(gi2)) =>
              // w1 -> r1 -> w2 -> go2 -> gi2 -> r2
              // w1 -> go2 -> gi2 -> r2
              val go2 = gi2.in.T
              swapConnection(go2.in, w2.out, w1.out)
            case (None, None) => 
              // w1 -> r1 -> w2 -> r2
              // w1 -> r2
              swapConnection(r2.in, w2.out, w1.out)
          }
          mirrorSyncMeta(w2, w1)
          zipReduce(r1.name.v, r2.name.v) { _ + "/" + _ }.foreach { name =>
            r2.name.reset
            r2.name.update(name)
          }
          Some(r2)
        }
      case _ => None
    }
  }

  Rule[OpDef] { n =>
    (n.op, n.inputs.map{_.T}) match {
      //TODO: fix this
      case (FixEql, List(iter:CounterIter, Const(c))) if config.option[Boolean]("shuffle-hack") =>
        val ctr = iter.counter.T
        ctr.min.T match {
          case Some(Const(min)) if min == c => 
            dbgblk(s"FirstIter($ctr)") {
              swapOutput(n.out, ctr.isFirstIter)
            }
            Some(n)
          case _ => None
        }
      case (_) => None
    }
  }

  XRule[Shuffle] { n =>
    (n.from, n.to, n.base) match {
      case (SC(OutputField(Const(from:List[_]),"out")), SC(OutputField(Const(to:List[_]),"out")), base) if from.intersect(to).isEmpty => 
        dbgblk(s"ShuffleUnmatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val c = within(n.ctx.get, n.getCtrl) {
            allocConst(List.fill(n.inferVec.get)(n.filled))
          }
          swapOutput(n.out, c.out)
        }
        Some(n)
      case (from, to, base) if (!memoryPrunerHashRun) => None
      case (from, to, base) if (matchInput(n.from, n.to) & n.base.T.getVec == n.from.T.getVec & n.getVec == n.to.T.getVec) => None
        dbgblk(s"ShuffleMatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val base = assertOne(n.base.connected, s"$n.base.connected")
          swapOutput(n.out, base)
        }
        Some(n)
      case (SC(OutputField(Const(from),"out")), SC(OutputField(Const(to),"out")), SC(OutputField(Const(base),"out"))) => 
        def get(i:Int) = {
          (from, base) match {
            case (from:Int, base) => if (from == i) base else n.filled
            case (from:List[_], base) => 
              val idx = from.indexWhere { _ == i }
              if (idx == -1) n.filled else {
                base match {
                  case base:List[_] => base(idx)
                  case base => base
                }
              }
            case from => throw PIRException(s"from=$from")
          }
        }
        val value = to match {
          case to:List[_] => to.map { i => get(i.as[Int]) }
          case to:Int => get(to)
        }
        dbgblk(s"ShuffleResolve($n)") {
          dbg(s"from=$from to=$to base=$base value=$value")
          val c = within(n.ctx.get, n.getCtrl) {
            allocConst(value)
          }
          swapOutput(n.out, c.out)
        }
        Some(n)
      case (from, to, base) => None
    }
  }

  Rule[PIRNode] { n =>
    n.localIns.filter { _.as[Field[_]].name == "en" }.foreach { en =>
      en.connected.distinct.foreach {
        case out@OutputField(Const(true), "out") => 
          en.disconnectFrom(out)
          dbg(s"${en.src}.${en} disconnect ${dquote(out)}")
        case out@OutputField(Const(vs:List[_]), "out") if vs.forall { _ == true } => 
          en.disconnectFrom(out)
          dbg(s"${en.src}.${en} disconnect ${dquote(out)}")
        case _ => 
      }
    }
  }

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${quote(n)}") */{
    super.visitNode(n)
    rules.foreach { _(n) }
    xrules.foldLeft[Option[_]](None) { 
      case (None, rule) => rule(n)
      case (Some(n), rule) => Some(n)
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

  override def removeNodes[N<:Node[N]](nodes:Iterable[Node[N]]):Unit = {
    super.removeNodes(nodes)
    nodes.foreach { markVisited }
  }

}
object SC {
  def unapply(x:Edge[_,_,_]) = x.singleConnected
}

