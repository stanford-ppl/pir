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
            swapOutput(o1.as, o2.as)
            o2
          } else o1
        }.getOrElse(o)
      }.getOrElse(o)
    }
  }
  case class TransferRule[A:ClassTag](lambda:A => Option[PIRNode]) {
    transferRules += this
    def apply(x:PIRNode):Option[Any] = {
      x.to[A].flatMap { x => lambda(x) }
    }
  }

  RewriteRule[MemRead](s"WrittenByConstData") { reader =>
    val ConstData = MatchRule[MemWrite, Const] { write =>
      write.data.T match {
        case cn@Const(c) if !write.en.isConnected && write.waitFors.isEmpty && cn.getVec == reader.getVec => 
          val const = c match {
            case c:List[_] => assert(c.size == reader.getVec, s"$c.vec=${c.size} $reader.vec=${reader.getVec}"); cn
            case c if reader.getVec > 1 => 
              within(cn.getCtrl, cn.parent.get) { allocConst(List.fill(reader.getVec) { c }).tp(cn.inferTp.get) }
            case c => cn
          }
          Some(const)
        case _ => None
      }
    }
    reader.mem.T.inAccesses match {
      case List(writer@ConstData(c)) if writer.order.get < reader.order.get && matchInput(writer.en, reader.en) => 
        Some((reader.out, c.out))
      case _ => None
    }
  }

  RewriteRule[OpDef](s"ConstProp") { case n@OpDef(op) =>
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
            val const = within(n.parent.get, n.ctrl.get) { allocConst(c, tp=Some(n.getTp)) }
            (n.out, const.out)
          case List(out:Output[_]) =>
            (n.out, out)
        }
      }
    }
  }

  RewriteRule[RegAccumOp](s"RegAccumFMA") { 
    case n@RegAccumOp(List(OpDef(FixAdd | FltAdd)), identity) =>
      n.in.T match {
        case mul@OpDef(FixMul | FltMul) =>
          val accum = within(n.parent.get, n.ctrl.get) {
            stage(RegAccumFMA(identity)
              .in1(mul.inputs(0).connected)
              .in2(mul.inputs(1).connected)
              .en(n.en.connected)
              .first(n.first.connected)
              .init(n.init.connected)
            )
          }
          Some((n.out, accum.out))
        case _ => None
      }
    case _ => None
  }

  private def andMask(c:Int) = {
    val posMask = c.log2 - 1 
    posMask + Int.MinValue
  }
  RewriteRule[OpDef](s"StrengthReduction") { 
    case n@OpDef(FixDiv) => // Div pow of 2 to shift
      n.inputs(1) match {
        case SC(OutputField(cn@Const(c:List[_]), _)) if c.as[List[Int]].forall { c => c.isPowOf2 & (c > 0) } =>
          val samt = within(n.parent.get, n.getCtrl) { allocConst(c.as[List[Int]].map { _.log2 }, Some(cn.getTp)) }
          val shift = stage(OpDef(FixSRA).addInput(n.inputs(0).singleConnected.get, samt))
          Some(n.out, shift.out)
        case SC(OutputField(cn@Const(c:Int), _)) if c.isPowOf2 & (c > 0) => 
          val samt = within(n.parent.get, n.getCtrl) { allocConst(c.log2, Some(cn.getTp)) }
          val shift = stage(OpDef(FixSRA).addInput(n.inputs(0).singleConnected.get, samt))
          Some(n.out, shift.out)
        case _ => None
      }
    //case n@OpDef(FixMod) => // mod pow of 2 to and //TODO: fix this
      //n.inputs(1) match {
        //case SC(OutputField(cn@Const(c:List[_]), _)) if c.as[List[Int]].forall { c => c.isPowOf2 & (c > 0) } =>
          //val mask = within(n.parent.get, n.getCtrl) { allocConst(c.as[List[Int]].map { andMask }, Some(cn.getTp)) }
          //val and = stage(OpDef(FixAnd).addInput(n.inputs(0).singleConnected.get, mask))
          //Some(n.out, and.out)
        //case SC(OutputField(cn@Const(c:Int), _)) if c.isPowOf2 & (c > 0) => 
          //val mask = within(n.parent.get, n.getCtrl) { allocConst(andMask(c), Some(cn.getTp)) }
          //val and = stage(OpDef(FixAnd).addInput(n.inputs(0).singleConnected.get, mask))
          //Some(n.out, and.out)
        //case _ => None
      //}
    case n@OpDef(FixAdd) => // ShiftAdd to FMA
      val ConstShift = MatchRule[OpDef, (Output[_],Any)] { case n@OpDef(FixSLA) =>
        val (const, nonConst) = n.inputs.map { _.singleConnected.get }.partition {
          case OutputField(c:Const, _) => true
          case _ => false
        }
        const.headOption.flatMap { out =>
          val value = out.src.as[Const].value
          val mulIn = nonConst.head
          value match {
            case v:List[_] => Some((mulIn, v.map { v => (0 until v.as[Int]).map { _ => 2}.product }))
            case v:Int => Some(mulIn,((0 until v).map { _ => 2}.product))
            case _ => None
          }
        }
      }
      def FMA(mulIn:Output[_], c:Any, addIn:Output[_]) = {
        val fma = within(n.parent.get, n.getCtrl) {
          val tp = addIn.src.as[PIRNode].getTp
          val const = allocConst(c,tp=Some(tp)).tp(tp)
          stage(OpDef(FixFMA).addInput(mulIn, const.out, addIn).out)
        }
        Some(n.out, fma)
      }
      (n.inputs(0), n.inputs(1)) match {
        case (SC(OutputField(ConstShift(mulIn, value), _)), SC(addIn)) => FMA(mulIn,value,addIn.as[Output[_]])
        case (SC(addIn), SC(OutputField(ConstShift(mulIn, value), _))) => FMA(mulIn,value,addIn.as[Output[_]])
        case _ => None
      }
    case n => None
  }

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
            case from => bug(s"from=$from")
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
        case out@OutputField(ctrler:LoopController, "laneValid") if ctrler.constLaneValids.get.forall { _.nonEmpty } =>
          val const = within(ctrler.parent.get, ctrler.ctrl.get) { 
            val laneValids = ctrler.constLaneValids.get.map { _.get }
            if (laneValids.size == 1)
              allocConst(laneValids.head)
            else
              allocConst(laneValids)
          }
          swapConnection(en, out, const.out)
        case _ => 
      }
    }
    None // Non exclusive rule
  }

  // Counter valids with par < maxValid should be always true
  TransferRule[Counter] { counter =>
    if (counter.valids.exists { _.out.isConnected }) {
      dbgblk(s"CounterConstValid($counter)") {
        val ctrler = counter.parent.get
        var const:Const = null
        val constValids = counter.constValids.v
        counter.valids.filter { _.out.isConnected }.foreach { case valid@CounterValid(is) =>
          if (is.forall { i => constValids.get(i).nonEmpty }) {
            val consts = is.map { i => counter.constValids.get(i).get }
            dbg(s"Set $valid with is=$is to $consts")
            const = within(ctrler.parent.get, counter.ctrl.get) { 
              if (is.size == 1) allocConst(consts.head) else allocConst(consts)
            }
            swapOutput(valid.out, const.out)
          }
        }
        if (const != null)
          addAndVisitNode(const, ())
      }
      Some(counter)
    } else None
  }

  TransferRule[Access] { access =>
    val invalidAccess = access.en.connected.exists { 
      case OutputField(Const(false), _) => true
      case OutputField(Const(vs:List[_]), _) if vs.forall { _ == false } => true
      case _ => false
    }
    if (invalidAccess) {
      access match {
        case access:InAccess => access.mem.disconnect
        case access:OutAccess => access.out.disconnect
      }
      free(access)
      Some(access)
    } else None
  }

  TransferRule[BufferRead] { n =>
    if (n.ctx.get.streaming.get) None else {
      val writer = n.inAccess.as[BufferWrite]
      writer.data match {
        case SC(OutputField(c:Const, "out")) if !writer.en.isConnected && c.getVec == n.getVec =>
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
          Some(read)
        }
      }
    } else {
      testOne(mem.inAccesses).flatMap{ write =>
        if (write.getCtrl == read.getCtrl) {
          // Use pipeline register to carry intermediate result if read and write are on the same
          // controller
          if (write.order.get < read.order.get) {
            dbgblk(s"Remove $write -> $mem -> $read") {
              swapOutput(read.out, write.as[MemWrite].data.singleConnected.get)
            }
            Some(write)
          } else {
            //TODO: Pipe reg accum
            None
          }
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
            if (matchInput(w1.en, w2.en) && matchInput(w1.en, r1.en)) {
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
      case r1:BufferRead if r1.isFIFO == r2.isFIFO && matchRate(w2, r1) & matchInput(r1.en, w2.en) & !r2.nonBlocking =>
        val w1 = r1.inAccess.as[BufferWrite]
        dbgblk(s"Route through (3) $w1 -> $r1 -> $w2 -> $r2 detected => ") {
          dbg(s" => $w1 -> $r2")
          r1.presetVec.v.foreach { v => r2.presetVec(v) } // Most before swapConncetion
          mirrorSyncMeta(w2, w1)
          zipReduce(r1.name.v, r2.name.v) { _ + "/" + _ }.foreach { name =>
            r2.name.reset
            r2.name.update(name)
          }
          val go1 = w1.gout
          val gi2 = r2.gin
          (go1, gi2) match {
            case (Some(go1), Some(gi2)) => //TODO: shouldn't needed any more
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
          Some(r2)
        }
      case _ => None
    }
  }

  TransferRule[BankedRead] { read =>
    val mem = read.mem.T
    testOne(mem.inAccesses).flatMap { w =>
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
        Some(read)
      } else None
    }
  }

  override def visitNode(n:N):T = /*dbgblk(s"visitNode:${n}") */{
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

  override def finPass = {
    super.finPass
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

  override def runPass = {
    withGC(true) {
      super.runPass
    }
  }

  override def removeNodes[N<:Node[N]](nodes:Iterable[Node[N]]):Unit = {
    super.removeNodes(nodes)
    nodes.foreach { markVisited }
  }

}
object SC {
  def unapply(x:Edge[_,_,_]):Option[Edge[_,_,_]] = x.singleConnected.as
}

