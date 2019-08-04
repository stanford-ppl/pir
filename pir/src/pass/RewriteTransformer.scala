package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import prism.graph.implicits._
import spade.param._
import prism.util._

import scala.collection.mutable

trait RewriteUtil { self: PIRTransformer =>
  // run when the node is created
  val rewriteRules = mutable.ListBuffer[RewriteRule[_]]()
  // run during RewriteTransformer
  val transferRules = mutable.ListBuffer[TransferRule[_]]()

  case class RewriteRule[A:ClassTag](name:String)(lambda:A => Option[(Output[_],Output[_])]) {
    rewriteRules += this
    def apply(x:PIRNode):Option[(Output[_],Output[_])] = {
      x.to[A].flatMap { x => lambda(x) }
    }
    def apply(o:Output[_]):Output[_] = {
      o.src.to[A].map { x =>
        lambda(x).map { case (o1, o2) =>
          if (o1 == o) {
            dbg(s"$name rewrite ${dquote(o1)} to ${dquote(o2)}")
            o2
          } else o1
        }.getOrElse(o)
      }.getOrElse(o)
    }
  }
  case class TransferRule[A:ClassTag](lambda:A => Option[Any]) {
    transferRules += this
    def apply(x:PIRNode):Option[Any] = {
      x.to[A].flatMap { x => lambda(x) }
    }
  }

  RewriteRule[MemRead](s"WrittenByConstData") { reader =>
    val ConstData = MatchRule[MemWrite, Const] { write =>
      write.data.T match {
        case c@Const(_) if !write.en.isConnected && write.waitFors.isEmpty => Some(c)
        case _ => None
      }
    }
    reader.mem.T.inAccesses match {
      case List(writer@ConstData(c)) if writer.order.get < reader.order.get && matchInput(writer.en, reader.en) => 
        Some((reader.out, c.out))
      case _ => None
    }
  }

  RewriteRule[OpDef](s"EvalOp") { case n@OpDef(op) =>
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
            (n.out, const.out)
          case List(out:Output[_]) =>
            (n.out, out)
        }
      }
    }
  }

  //RewriteRule[OpDef](s"FirstIter") { n =>
    //(n.op, n.inputs.map{_.T}) match {
      ////TODO: fix this
      //case (FixEql, List(iter:CounterIter, Const(c))) if config.option[Boolean]("shuffle-hack") =>
        //val ctr = iter.counter.T
        //ctr.min.T match {
          //case Some(Const(min)) if min == c => 
            //Some((n.out, ctr.isFirstIter))
          //case _ => None
        //}
      //case (_) => None
    //}
  //}

  def memoryPrunerHashRun = compiler.hasRun[MemoryPruner]

  RewriteRule[Shuffle](s"Shuffle") { n =>
    (n.from, n.to, n.base) match {
      case (SC(OutputField(Const(from:List[_]),"out")), SC(OutputField(Const(to:List[_]),"out")), base) if from.intersect(to).isEmpty => 
        dbgblk(s"ShuffleUnmatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val c = within(n.ctx.get, n.getCtrl) {
            allocConst(List.fill(n.inferVec.get)(n.filled))
          }
          Some((n.out, c.out))
        }
      case (from, to, base) if (!memoryPrunerHashRun) => None
      case (from, to, base) if (matchInput(n.from, n.to) & n.base.T.getVec == n.from.T.getVec & n.getVec == n.to.T.getVec) => None
        dbgblk(s"ShuffleMatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val base = assertOne(n.base.connected, s"$n.base.connected")
          Some((n.out, base))
        }
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
          Some((n.out, c.out))
        }
      case (from, to, base) => None
    }
  }

}

// BFS is slightly faster than DFS
class RewriteTransformer(implicit compiler:PIR) extends PIRTraversal with PIRTransformer 
  with BFSBottomUpTopologicalTraversal with UnitTraversal 
  with BufferAnalyzer with RewriteUtil {

  val forward = true

  var memLowerHasRun = false
  var memPrunerHasRun = false
  var globalInsertHasRun = false
  var initializerHasRun = false
  var _memoryPrunerHashRun = false
  override def memoryPrunerHashRun = _memoryPrunerHashRun

  override def initPass = {
    super.initPass
    memLowerHasRun = compiler.hasRun[MemoryLowering]
    globalInsertHasRun = compiler.hasRun[GlobalInsertion]
    initializerHasRun = compiler.hasRun[TargetInitializer]
    _memoryPrunerHashRun = compiler.hasRun[MemoryPruner]
  }

  TransferRule[PIRNode] { n =>
    n.localIns.filter { _.as[Field[_]].name == "en" }.foreach { en =>
      en.connected.distinct.foreach {
        case out@OutputField(Const(true), "out") => 
          en.disconnectFrom(out)
          dbg(s"${dquote(en)} disconnect ${dquote(out)}")
        case out@OutputField(Const(vs:List[_]), "out") if vs.forall { _ == true } => 
          en.disconnectFrom(out)
          dbg(s"${dquote(en)} disconnect ${dquote(out)}")
        case _ => 
      }
    }
    None // Non exclusive rule
  }

  // Counter valids with par < maxValid should be always true
  TransferRule[Counter] { counter =>
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

  TransferRule[BufferRead] { n =>
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

  TransferRule[MemRead] { read =>
    val mem = read.mem.T
    if (mem.inAccesses.isEmpty) {
      mem.inits.v.fold { 
        err(s"Reading undefined memory ${quoteSrcCtx(mem)}")
      }{ inits =>
        dbgblk(s"Embed Reg initial value($mem)"){
          val const = within(read.parent.get, read.getCtrl) {
            stage(Const(inits))
          }
          swapOutput(read.out, const.out)
        }
      }
      Some(read)
    } else {
      testOne(mem.inAccesses).map{ write =>
        if (write.getCtrl == read.getCtrl) {
          if (write.order.get < read.order.get) {
            dbgblk(s"Remove $write -> $mem -> $read") {
              swapOutput(read.out, write.as[MemWrite].data.singleConnected.get)
            }
          } else {
            //TODO: Pipe reg accum
          }
          Some(write)
        } else None
      }
    }
  }

  /*
   * w1 -> m1 -> r1 -> w2 -> m2
   * mw1 -> m2
   * */
  TransferRule[MemWrite] { w2 =>
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

  override def compScheduleFactor(n:Context):Int = 1 
  // HACK: For now, don't care whether it's scheduled or not to check
  // rate matching
  
  TransferRule[BufferRead] { r2 =>
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

  TransferRule[BankedRead] { read =>
    val mem = read.mem.T
    testOne(mem.inAccesses).map { w =>
      val write = w.as[BankedWrite]
      if (write.order.get < read.order.get) {
        val matchBank = matchInput(read.bank, write.bank)
        val matchOfst = matchInput(read.offset, write.offset)
        val matchEn = matchInput(read.en, write.en)
        dbg(s"matchBank=$matchBank")
        dbg(s"matchOfst=$matchOfst")
        dbg(s"matchEn=$matchEn")
        if (matchBank && matchOfst && matchEn) dbgblk(s"RouteThroughMemory") {
          val fifoWrite = within(write.getCtrl, write.parent.get) {
            stage(MemWrite().data(write.data.singleConnected.get))
          }
          val fifo = within(mem.parent.get) {
            val fifo = Reg().addAccess(fifoWrite)
            fifo.mirrorMetas(mem)
            stage(fifo)
          }
          val fifoRead = within(read.getCtrl, read.parent.get) {
            stage(MemRead().setMem(fifo))
          }
          dbg(s"$write -> $mem -> $read => $fifoWrite -> $fifo -> $fifoRead")
          swapOutput(read.out, fifoRead.out)
        }
      }
    }
  }

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${quote(n)}") */{
    super.visitNode(n)
    val res = rewriteRules.foldLeft[Option[_]](None) {
      case (None, rule) => rule(n).map { case (o1, o2) =>
          dbgblk(s"${rule.name}") {
            swapOutput(o1.as, o2.as)
          }
          Some(n)
        }.getOrElse(None)
      case (Some(n), rule) => Some(n)
    }
    transferRules.foldLeft[Option[_]](res) { 
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

