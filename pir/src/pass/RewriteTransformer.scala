package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import prism.graph.implicits._
import spade.param._
import prism.util._

import scala.collection.mutable

trait RewriteUtil extends PIRPass { self: PIRTransformer =>
  //TODO: make this a map of classTag => rule to speedup
  //
  // run when the node is created
  val allRewriteRules = mutable.ListBuffer[RewriteRule[_]]()
  // run during RewriteTransformer
  val allTransferRules = mutable.ListBuffer[TransferRule[_]]()

  var rewriteRules:Iterable[RewriteRule[_]] = Nil
  var transferRules:Iterable[TransferRule[_]] = Nil

  override def initPass = {
    rewriteRules = allRewriteRules.filter { _.enable() }
    transferRules = allTransferRules.filter { _.enable() }
    super.initPass
  }

  class RewriteRule[A:ClassTag](val name:String, val enable: () => Boolean, lambda:A => Option[(Output[_],Output[_])]) {
    allRewriteRules += this
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
  object RewriteRule {
    def apply[A:ClassTag](name:String, enable: => Boolean=true)(lambda:A => Option[(Output[_],Output[_])]) =
      new RewriteRule(name, () => enable,lambda)
  }
  class TransferRule[A:ClassTag](val enable: () => Boolean, lambda:A => Option[PIRNode]) {
    allTransferRules += this
    def apply(x:PIRNode):Option[Any] = {
      x.to[A].flatMap { x => lambda(x) }
    }
  }
  object TransferRule {
    def apply[A:ClassTag](enable: => Boolean=true)(lambda:A => Option[PIRNode]) =
      new TransferRule(() => enable,lambda)
  }

  RewriteRule[MemRead](s"WrittenByConstData") { reader =>
    val ConstData = MatchRule[MemWrite, Const] { case write =>
      val readVec = reader.out.getVec
      write.data.T match {
        case cn@Const(c) if !write.en.isConnected && write.waitFors.isEmpty && cn.out.getVec == readVec => 
          val const = c match {
            case c:List[_] => assert(c.size == readVec, s"$c.vec=${c.size} $reader.vec=${readVec}"); cn
            case c if readVec > 1 => 
              within(cn.getCtrl, cn.parent.get) { allocConst(List.fill(readVec) { c }).tp(cn.inferTp.get) }
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

  RewriteRule[OpDef](s"ConstProp",config.enableConstProp) { case n@OpDef(op) =>
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

  RewriteRule[OpDef](s"PipeAccum", config.enablePipeAccum) { 
    case n@OpDef(Mux) =>
      val accumWrites = n.out.neighbors.collect { case write:MemWrite if write.mem.T.isAccum => write }
      testOne(accumWrites).flatMap { accumWrite =>
        testOne(accumWrite.mem.T.outAccesses).flatMap { accumRead =>
          val accumOps = accumRead.out.accum(
            prefix = { case `n` => true; case _ => false },
            depth = 5
          )
          if (accumOps.contains(n)) {
            val redOps = accumOps.filterNot { _ == n }
            within(n.parent.get, n.getCtrl) {
              val first::init::_ = n.inputs.map { _.connected }
              val ins = redOps.head.localIns.flatMap { _.connected }.filterNot { _.src == accumRead }
              val in = assertOne(ins, s"accum $n.in")
              val acc = stage(RegAccumOp(redOps, accumWrite.mem.T.inits.get)
                .first(first).in(in).init(init).en(accumWrite.en.connected))
              accumWrite.data.disconnect
              dbgn(acc)
              Some((n.out, acc.out))
            }
          } else None
        }
      }
    case _ => None
  }

  private def andMask(c:Int) = {
    val posMask = c.log2 - 1 
    posMask + Int.MinValue
  }
  RewriteRule[OpDef](s"StrengthReduction",config.enableStrengthReduce) { 
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
        val ins = n.inputs.map { _.singleConnected.get }
        val (const, nonConst) = ins.partition {
          case OutputField(c:Const, _) => true
          case _ => false
        }
        const.headOption.flatMap { out =>
          val value = out.src.as[Const].value
          val mulIn = ins.filterNot { _ == const }.head // Possible both are consts if consprop is disabled
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
          val const = allocConst(c,tp=Some(tp))
          stage(OpDef(FixFMA).addInput(mulIn, const.out, addIn).out)
        }
        Some(n.out, fma)
      }
      dbg(s"ConstShift $n")
      (n.inputs(0), n.inputs(1)) match {
        case (SC(OutputField(ConstShift(mulIn, value), _)), SC(addIn)) => FMA(mulIn,value,addIn.as[Output[_]])
        case (SC(addIn), SC(OutputField(ConstShift(mulIn, value), _))) => FMA(mulIn,value,addIn.as[Output[_]])
        case _ => None
      }
    case n => None
  }

  RewriteRule[Shuffle](s"Shuffle",config.enableConstProp && compiler.hasRun[MemoryPruner]) { n =>
    (n.from, n.to, n.base) match {
      case (SC(OutputField(Const(from:List[_]),"out")), SC(OutputField(Const(to:List[_]),"out")), base) if from.intersect(to).isEmpty => 
        dbgblk(s"ShuffleUnmatch($n, from=${dquote(n.from.T)}, to=${dquote(n.to.T)})") {
          val c = within(n.ctx.get, n.getCtrl) {
            allocConst(List.fill(n.getVec)(n.filled))
          }
          Some((n.out, c.out))
        }
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

  TransferRule[PIRNode]() { n =>
    n.localIns.filter { _.as[Field[_]].name == "en" }.foreach { en =>
      en.connected.distinct.foreach {
        case out@OutputField(Const(true), "out") => 
          en.disconnectFrom(out)
          dbg(s"${dquote(en)} disconnect ${dquote(out)}")
        case out@OutputField(Const(vs:List[_]), "out") if vs.forall { _ == true } => 
          en.disconnectFrom(out)
          dbg(s"${dquote(en)} disconnect ${dquote(out)}")
        case out@OutputField(ctrler:LoopController, "laneValid") if config.enableRangeAnalysis && ctrler.constLaneValids.get.forall { _.nonEmpty } =>
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
  TransferRule[Counter](config.enableRangeAnalysis) { counter =>
    if (counter.valids.exists { _.out.isConnected }) {
      dbgblk(s"CounterConstValidIter($counter)") {
        val ctrler = counter.parent.get
        var vconst:Const = null
        var iconst:Const = null
        val constValids = counter.constValids.get
        val constIters = counter.constIters.get
        counter.valids.filter { _.out.isConnected }.foreach { case valid@CounterValid(is) =>
          if (is.forall { i => constValids(i).nonEmpty }) {
            val consts = is.map { i => constValids(i).get }
            dbg(s"Set $valid with is=$is to $consts")
            vconst = within(ctrler.parent.get, counter.ctrl.get) { 
              if (is.size == 1) allocConst(consts.head) else allocConst(consts)
            }
            swapOutput(valid.out, vconst.out)
          }
        }
        counter.iters.filter { _.out.isConnected }.foreach { case iter@CounterIter(is) =>
          if (is.forall { i => constIters(i).nonEmpty }) {
            val consts = is.map { i => constIters(i).get }
            dbg(s"Set $iter with is=$is to $consts")
            iconst = within(ctrler.parent.get, counter.ctrl.get) { 
              if (is.size == 1) allocConst(consts.head) else allocConst(consts)
            }
            swapOutput(iter.out, iconst.out)
          }
        }
        if (iconst != null)
          addAndVisitNode(iconst, ())
        if (vconst != null)
          addAndVisitNode(vconst, ())
      }
      Some(counter)
    } else None
  }

  TransferRule[Access]() { access =>
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

  TransferRule[BufferRead]() { n =>
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

  TransferRule[MemRead]() { read =>
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
  TransferRule[MemWrite](
    config.enableRouteElim && 
    !compiler.hasRun[TargetInitializer] && 
    !compiler.hasRun[MemoryLowering] && 
    !compiler.hasRun[GlobalInsertion]
  ) { w2 =>
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
              mirrorMetas(mw1,w1)
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
  }

  override def compScheduleFactor(n:Context):Int = 1 
  // HACK: For now, don't care whether it's scheduled or not to check
  // rate matching
  
  TransferRule[BufferRead](config.enableRouteElim) { r2 =>
    val w2 = r2.inAccess.as[BufferWrite]
    //w2.data.T match {
      //case r1:BufferRead =>
        //val w1 = r1.inAccess.as[BufferWrite]
        //dbg(s"Testing Route through (2) $w1 -> $r1 -> $w2 -> $r2")
        //dbg(s"isFIFO = ${r1.isFIFO == r2.isFIFO}")
        //dbg(s"matchRate = ${matchRate(r1, w2)}")
        //dbg(s"matchEn = ${matchInput(r1.en, w2.en)}")
        //dbg(s"sameVec = ${r1.out.getVec == r2.out.getVec}")
      //case _ =>
    //}
    w2.data.T match {
      case r1:BufferRead if r1.isFIFO == r2.isFIFO && 
                            matchRate(r1, w2) & 
                            matchInput(r1.en, w2.en) & 
                            !r2.nonBlocking &&
                            r1.out.getVec == r2.out.getVec =>
        val w1 = r1.inAccess.as[BufferWrite]
        dbgblk(s"Route through (2) $w1 -> $r1 -> $w2 -> $r2 detected => ") {
          dbg(s" => $w1 -> $r2")
          if (r2.in.getVec != r1.in.getVec) {
            r2.in.vecMeta.reset
            r2.in.setVec(r1.in.getVec)
          }
          transferLocalAccess(r1,r2)
          //TDOO: fix this
          //val ens = r1.en.connected.filter { out =>
            //val ctrl = out.src.getCtrl
            //ctrl == r2.getCtrl || ctrl.isDescendentOf(r2.getCtrl)
          //}
          //r2.en(ens)
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

  type ConstBankAddr = List[(Int,Int)]
  val constCastAddr = mutable.HashMap[(Memory, Seq[Int]), Option[ConstBankAddr]]()
  val constCastRead = mutable.HashMap[(Memory, Seq[Int]), Output[PIRNode]]()

  import spade.param._
  private def constFlatAddr(input:Input[PIRNode], dims:List[Int]):Option[Any] = {
    val ins = input.connected.map { addr =>
      val const = addr.src.to[Const]
      if (const.isEmpty) return None
      const.get.value
    }
    Some(flattenND[Any](ins, dims)(castNum))
  }

  /*
   * Returns an option of constant flatten address and flatten bank
   * */
  private def toConstAddr(access:BankedAccess, mem:Memory):Option[ConstBankAddr] = {
    def analyzeAddr:Option[ConstBankAddr] = {
      if (access.en.isConnected) return None
      val dims = mem match {
        case mem:SRAM => mem.banks.get
        case mem:LUT => mem.dims.get
        case mem:RegFile => mem.dims.get
      }
      val bank = constFlatAddr(access.bank, dims).getOrElse(return None)
      val offset = constFlatAddr(access.offset, dims).getOrElse(return None)
      Some(((bank,offset) match {
        case tup@(bank:List[_],offset:List[_]) => bank.zip(offset)
        case (bank:List[_],offset) => bank.map { bank => (bank, offset) }
        case (bank,offset:List[_]) => offset.map { offset => (bank, offset) }
        case tup@(bank,offset) => List((bank,offset))
      }).as[List[(Int,Int)]])
    }
    // For read access, check if the access group is already analyzed
    access match {
      case access:BankedRead =>
        val group = if (config.enableBroadcastRead) access.castgroup.v else None
        group.foreach { grp =>
          constCastAddr.get((mem, grp)).foreach { res =>
            return res
          }
        }
        val res = analyzeAddr
        group.foreach { grp =>
          constCastAddr += ((mem, grp) -> res)
        }
        res
      case access:BankedWrite => analyzeAddr
    }
  }

  def constAddrRouteThrough(read:BankedRead):Option[BankedRead] = {
    val mem = read.mem.T
    val raddrs = toConstAddr(read, mem).getOrElse(return None)
    if (mem.inAccesses.exists { _.order.get > read.order.get }) return None
    // A pair of bank ID and bank offset
    val writtenAddr = scala.collection.mutable.Set[(Int,Int)]()
    dbg (s"$read raddrs=$raddrs")
    // For each constant writer, get the shuffle transformation for this reader
    val constWrites = mem.inAccesses.flatMap { write =>
      val waddrs = toConstAddr(write.as[BankedWrite], mem).getOrElse(return None)
      val to = raddrs.map { raddr =>
        val idx = waddrs.indexOf(raddr)
        if (idx > 0) {
          // If a single address is written multiple times, then stop
          if (writtenAddr.contains(raddr)) return None
          writtenAddr += raddr
        }
        idx
      }
      if (to.exists { _ != -1 }) {
        dbg(s"write=$write waddr=${waddrs} to=${to}")
        Some((write.as[BankedWrite], to))
      } else {
        None
      }
    }
    dbgblk(s"constAddrRouteThrough($read)") {
      val group = if (config.enableBroadcastRead) read.castgroup.v else None
      val readCtrl = read.getCtrl
      val out = group.flatMap { grp =>
        constCastRead.get((mem, grp))
      } getOrElse {
        val shuffles = within(read.parent.get, readCtrl) {
          constWrites.map { case (write, to) =>
            val data = write.data.singleConnected.get
            val reg = within(pirTop) {
              stage(Reg().tp(write.getTp).banks(List(write.getVec)))
            }
            within(write.parent.get, write.getCtrl) {
              stage(MemWrite().setMem(reg).data(data))
            }
            val read = stage(MemRead().setMem(reg).presetVec(data.getVec))
            stage(Shuffle(0,write.id).from(allocConst((0 until data.getVec).toList)).to(allocConst(to)).base(read.out))
          }
        }
        within(read.parent.get, readCtrl) {
          reduceTree[Output[PIRNode]](shuffles.map { _.out }) { case (s1,s2) =>
            stage(OpDef(FixOr).addInput(s1,s2)).out
          }.get
        }
      }
      val readBy = read.out.connected
      val outsrc = out.src
      if (outsrc.getCtrl == readCtrl) {
        swapOutput(read.out, out)
      } else {
        val fifo = within(pirTop) {
          stage(FIFO().tp(read.getTp).banks(List(read.getVec)))
        }
        within(outsrc.parent.get, outsrc.getCtrl) {
          stage(MemWrite()).setMem(fifo).data(out)
        }
        val fifoRead = within(read.parent.get, readCtrl) {
          stage(MemRead()).setMem(fifo)
        }
        readBy.foreach { in =>
          swapConnection(in, read.out, fifoRead.out)
        }
      }
      Some(read)
    }
  }

  def matchAddrRouteThrough(read:BankedRead):Option[BankedRead] = {
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

  TransferRule[BankedRead](config.enableRouteElim && !compiler.hasRun[MemoryLowering]) { read =>
    constAddrRouteThrough(read) orElse
    matchAddrRouteThrough(read)
  }

  TransferRule[Shuffle](compiler.hasRun[MemoryPruner]) { n =>
    free(n.offset)
    None
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

  override def initPass = {
    constCastAddr.clear
    super.initPass
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

