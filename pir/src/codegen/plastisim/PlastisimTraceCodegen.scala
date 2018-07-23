package pir
package codegen

import pir.node._
import pir.pass._
import prism.util._
import sys.process._

import scala.collection.mutable

class PlastisimTraceCodegen(implicit compiler:PIR) extends PlastisimCodegen with ScalaCodegen with Memorization {
  import pirmeta._
  import spademeta._

  val fileName = s"gen_trace.scala"

  override def initPass = {
    resetAllCaches
    super.initPass
    offsetMap = scala.collection.immutable.ListMap[DRAM, Int]()
  }

  lazy val bytePerWord = topParam.wordWidth / 8

  // mutable.ListMap doesn't preserve the order even scaladoc says so
  var offsetMap = scala.collection.immutable.ListMap[DRAM, Int]()

  lazy val ctrlbmap = ctrlOf.rmap

  val tracked = mutable.ListBuffer[PNode]()
  val trackedCtrls = mutable.ListBuffer[Controller]()
  val traced = mutable.ListBuffer[PNode]()
  val emitted = mutable.ListBuffer[PNode]()

  def setDramOffset = dbgblk(s"setDramOffset"){
    val drams = top.collectDown[DramFringe]().flatMap { _.dram }.toSet
    var offset = 0
    drams.foreach { dram =>
      val dims = staticDimsOf.get(dram).getOrElse {
        throw PIRException(s"static dimention of $dram unknown!")
      }
      emitComment(s"$dram -> $offset dims=$dims size=${dims.product}")
      offsetMap += dram -> offset
      val size = dims.product
      offset += size
    }
  }

  val backwardSchedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = PlastisimTraceCodegen.this.logger
    val forward = false
    override def visitFunc(n:N):List[N] = super.visitFunc(n).filter { 
      case n:ControlNode => false
      case n => true
    }
    override def visitIn(n:N):List[N] = n match {
      case n:Memory if boundOf.contains(n) => Nil
      case Def(n, DramAddress(dram)) if offsetMap.contains(dram) => Nil
      case n => visitGlobalIn(n)
    }
    override def visitOut(n:N):List[N] = visitGlobalOut(n)
    override def isDepFree(n:N) = true
  }

  val forwardSchedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = PlastisimTraceCodegen.this.logger
    val forward = true
    override def visitIn(n:N):List[N] = visitGlobalIn(n)
    override def visitOut(n:N):List[N] = visitGlobalOut(n)
  }

  def gatherTracedNodes = {
    val fringes = compiler.top.collectDown[DramFringe]()
    fringes.foreach { fringe =>
      fringe.collectDown[StreamOut]().filter { so => 
          so.field == "offset" || so.field == "size" || so.field == "addr"
      }.foreach { so =>
        val read = readersOf(so).head
        traced += read
        dbgblk(s"trace nodes for $so at $read") {
          val nodes = backwardSchedular.scheduleNode(read)
          tracked ++= nodes
          nodes.reverseIterator.foreach { n => dbg(qdef(n)) }
        }
      }
    }

    val ctrls = tracked.flatMap { n => ctrlOf.get(n) }.toSet.asInstanceOf[Set[Controller]].flatMap { ctrl =>
      ctrl :: ctrl.ancestors
    }
    trackedCtrls ++= ctrls
    dbg(s"tracking ctrls=$ctrls")
    ctrls.foreach { 
      case c:LoopController => 
        cchainOf(c).counters.foreach { ctr =>
          dbgblk(s"track nodes for $ctr") {
            val nodes = backwardSchedular.scheduleNode(ctr)
            tracked ++= nodes
            nodes.reverseIterator.foreach { n => dbg(qdef(n)) }
          }
        }
      case c =>
    }
  }

  def nodeOf[T<:PIRNode:ClassTag](n:Controller):T = memorize(s"nodeOf", (n, implicitly[ClassTag[T]])) { case (n, ct) =>
    val nodes = ctrlbmap(n).collect { case ct(cc) => originOf(cc) }.toSet.toList
    val grouped = groupByForkJoin(nodes).map(_.head)
    dbg(s"grouped=${grouped.map { node => (node, ctrlOf(node))}}")
    assertOne(grouped, s"$n.$ct")
  }
  def cchainOf(n:LoopController):CounterChain = nodeOf[CounterChain](n)
  def countersOf(n:LoopController) = cchainOf(n).counters
  def iterOf(n:Counter):Option[CounterIter] = assertOneOrLess(n.collectOut[CounterIter](depth=2), s"counter Iter")

  override def runPass = {
    setDramOffset
    gatherTracedNodes
    emitMain
  }

  override def finPass = {
    super.finPass
    psimOut.foreach { psimOut =>
      shell(s"mkdir -p $psimOut/traces")
      shell(s"mkdir -p $psimOut/trace_classes")
      val log = s"$dirName/trace.log"
      //shell(s"trace", s"scalac ${dirName}/gen_trace.scala -d $psimOut/trace_classes", log)
      //shell(s"trace", s"scala -classpath $psimOut/trace_classes tracer $psimOut/traces", log)
      shell(s"trace", s"""${sys.env.get("PIR_HOME").get}/bin/run_trace $dirName $psimOut""", log)
    }
  }

  val controllerTraversal = new ControllerTopDownTopologicalTraversal with prism.traversal.DFSTopDownTopologicalTraversal with UnitTraversal {
    val traceGen = PlastisimTraceCodegen.this
    implicit val designP = traceGen.designP
    lazy val pirmeta = designP.pirmeta
    override lazy val logger = traceGen.logger
    val forward = true

    override def visitNode(n:N, prev:T):T = {
      if (!trackedCtrls.contains(n)) return
      n match {
        case n:LoopController => 
          emitln(s"// loop $n")
          val counters = traceGen.cchainOf(n).counters
          def emitLoop(idx:Int):Unit = {
            if (idx >= counters.size) {
              genNodes(n)
              super.visitNode(n, prev) 
            } else {
              val counter = counters(idx)
              // Exps to compute counter bounds
              val exps = backwardSchedular.scheduleNode(counter).reverse
              genNodes(exps)
              emitLambda(s"$counter.foreach", s"${n}_iter$idx") {
                traceGen.cchainOf(n).counters.flatMap(iterOf).foreach(genNode)
                emitLoop(idx+1)
              }
            }
          }
          emitLoop(0)
        case n:DramController =>
          nodeOf[ProcessDramCommand](n) match {
            case Def(_, ProcessDramDenseLoad(dram, offset, size)) =>
              emitLambda(s"(0 until $size / $bytePerWord / ${n.par}).foreach", s"i") {
                genNodes(n)
                super.visitNode(n, prev)
              }
            case Def(_, ProcessDramDenseStore(dram, offset, size, data)) =>
              emitLambda(s"(0 until $size / $bytePerWord / ${n.par}).foreach", s"i") {
                genNodes(n)
                super.visitNode(n, prev)
              }
          }
        case n:ForeverController if (foreverInMems(n).nonEmpty)=>
          emitComment(s"$n")
          emitBlock(s"while (${foreverInMems(n)}.exists{_.nonEmpty})") {
            genNodes(n)
            super.visitNode(n, prev)
          }
        case n =>
          emitComment(s"$n")
          emitBlock(s"ctrl") {
            genNodes(n)
            super.visitNode(n, prev)
          }
      }
    }
  }

  def reschedule(exps:List[N]) = {
    val (mems, others) = exps.partition { case mem:Memory => true; case _ => false }
    mems ++ others
  }

  def genNodes(n:Controller):Unit = {
    ctrlbmap.get(n).foreach { nodes => genNodes(nodes.toList) }
  }

  def genNodes(ns:List[N]):Unit = {
    val nodes = ns.filter { n => !emitted.contains(n) && tracked.contains(n) }
    val scheduled = reschedule(forwardSchedular.scheduleScope(nodes))
    scheduled.foreach(genNode)
  }

  def genDRAM = {
    emitln(s"val dram = scala.collection.mutable.ArrayBuffer[Any]()")
    offsetMap.foreach { 
      case (dram, offset) if fileNameOf.contains(dram)=>
        val fn = fileNameOf(dram)
        emitln(s"// $dram")
        staticDimsOf(dram) match {
          case List(dim1) =>
            emitLambda(s"""dram ++= scala.io.Source.fromFile("$fn").getLines.toArray.map""", "line") {
              emitln(s"""parse(line)""")
            }
          case List(dim1, dim2) =>
            emitLambda(s"""dram ++= scala.io.Source.fromFile("$fn").getLines.toArray.map""", "line", ".flatten.asInstanceOf[Array[Any]]") {
              emitln(s"""line.split(",").map { e => parse(e) }""")
            }
        }
      case (dram, offset) =>
        emitln(s"dram ++= Array.ofDim[Any](${staticDimsOf(dram).product}) // $dram")
    }
  }

  def genNode(n:N) = {
    emitted += n
    n match {
      case Def(n, Counter(min, max, step, par)) =>
        emitln(s"val $n = Range(${min}.asInstanceOf[Int], ${max}.asInstanceOf[Int], ${step}.asInstanceOf[Int] * ${par})")
      case Def(n,GlobalInput(gout:GlobalOutput)) =>
        emitln(s"val $n = ${gout}")
      case Def(n, GlobalOutput(data, valid)) =>
        emitln(s"val $n = ${data}")
      case DramAddress(dram) if offsetMap.contains(dram) => 
        emitln(s"val $n = Some(${offsetMap(dram)} * $bytePerWord)") 
      case n if isReg(n) & boundOf.contains(n) => 
        emitln(s"val $n = Some(${boundOf(n)})")
      case n if isFIFO(n) & boundOf.contains(n) => 
        emitln(s"val $n = mutable.Queue(${boundOf(n)})")
      case n if isReg(n) =>
        emitln(s"var $n:Option[Any] = None")
      case n if isFIFO(n) =>
        emitln(s"val $n = mutable.Queue[Any]()")
      case SRAM(size, banking) => 
        val dims = staticDimsOf(n)
        emitln(s"val $n = Array.ofDim[Any](${dims.product}) // dims=$dims")
      case Def(n, LocalStore(mems, None, data)) =>
        mems.foreach { 
          case mem if isReg(mem) =>
            emitComment(qdef(n))
            emitln(s"$mem = Some(${data})")
          case mem if isFIFO(mem) =>
            emitln(s"$mem.enqueue(${data})")
          case mem =>
            emitln(s"//TODO ${qdef(n)}")
        }
      case n:LocalLoad if memsOf(n).size==1 && isReg(memsOf(n).head) =>
        val mem = memsOf(n).head
        emitln(s"val $n = ${mem}.get")
      case n:LocalLoad if memsOf(n).size==1 && isFIFO(memsOf(n).head) =>
        val mem = memsOf(n).head
        emitln(s"val $n = ${mem}.dequeue")
      case Def(n, EnabledLoadMem(mem, Some(faddr), readNext)) =>
        emitln(s"val $n = fvecLoad($mem, $faddr)")
      case Def(n, EnabledStoreMem(mem, Some(faddr), data, writeNext)) =>
        emitln(s"fvecStore($mem, $faddr, $data)")
      case Const(c) =>
        emitln(s"val $n = $c")
      case Def(n, OpDef(op, inputs)) =>
        emitln(s"val $n = eval($op, ${inputs})")
      case Def(n, CounterIter(counter, offset)) =>
        val cchain = cchainOf(counter)
        val idx = cchain.counters.indexOf(counter)
        val ctr = cchainOf(ctrlOf(cchain).asInstanceOf[LoopController]).counters(idx)
        offset match {
          case Some(offset) =>
            emitln(s"val $n = ${ctrlOf(n)}_iter${idx} + $offset * ${ctr.step}")
          case None =>
            val par = getParOf(counter)
            emitln(s"val $n = List.tabulate(${par}) { p => ${ctrlOf(n)}_iter${idx} + p * ${ctr.step} }")
        }
      case Def(n,ProcessDramDenseLoad(dram, offset, size)) =>
        val par = getParOf(n)
        emitLambda(s"val $n = List.tabulate($par)", s"j") {
          emitln(s"val byteOffset = $offset.asInstanceOf[Int] / $bytePerWord")
          emitln(s"val addr = byteOffset + i * $par + j")
          emitln(s"dram(addr)")
        }
      case n:Container =>
      case n =>
        emitln(s"// TODO: unmatched node ${qdef(n)}")
    }
    if (traced.contains(n)) emitln(s"""trace(args(0) + "/$n.trace", $n)""")
  }

  def emitMain = {
    emitln(s"import scala.collection.mutable ")
    emitln(s"import java.io._ ")
    emitBlock(s"object tracer") {
      emitln(s"val pws = mutable.Map[String, PrintWriter]()")
      emitBlock(s"def main(args:Array[String]) = ") {
        genDRAM
        controllerTraversal.traverseTop
        emitln(s"pws.foreach(_._2.close)")
      }
      emitBlock(s"def trace(fileName:String, node:Any) = node match") {
        emitln(s"""case node:List[_] => getWriter(fileName).write(node.mkString(" ") + "\\n")""")
        emitln(s"""case node => getWriter(fileName).write(node.toString + "\\n")""")
      }
      emitBlock(s"def getWriter(fileName:String) = ") {
        emitln(s"pws.getOrElseUpdate(fileName, new PrintWriter(new File(fileName)))")
      }
      emitBlock(s"def ctrl(block : => Unit)") {
        emitln(s"block")
      }

      emitln(s"""

  import scala.util.{Try, Success, Failure}
  def parse(x:String):Any = {
    Try(x.toInt).recoverWith{ case _ => Try(x.toLong) }.recoverWith { case _ => Try(x.toFloat) }.recoverWith { case _ => Try(x.toDouble) }.get
  }

  def fvecLoad(mem:Array[Any], addr:Any):Any = addr match {
    case addrs:List[_] => addrs.map { a => mem(a.asInstanceOf[Int]) }
    case addr:Int => mem(addr)
  }

  def fvecStore(mem:Array[Any], addr:Any, data:Any) = (addr, data) match {
    case (addr:List[_],data:List[_]) => addr.zip(data).foreach { case (a, d) => mem(a.asInstanceOf[Int]) = d }
    case (addr:Int, data) => mem(addr) = data
    case (addr, data) => throw new Exception("Unknown "+ addr + " " + data + " type for fvecStore")
  }

  def vecLoad(mem:Array[_], addr:List[_]):Any =  {
    val idx::rest = addr
    if (rest.isEmpty) {
      idx match {
        case idx:List[_] => idx.map { vi => mem(vi.asInstanceOf[Int]) }
        case idx => mem(idx.asInstanceOf[Int])
      }
    } else {
      idx match {
        case idx:List[_] => idx.map { vi => vecLoad(mem(vi.asInstanceOf[Int]).asInstanceOf[Array[_]], rest) }
        case idx => vecLoad(mem(idx.asInstanceOf[Int]).asInstanceOf[Array[_]], rest)
      }
    }
  }

  def vecStore(mem:Array[_], addr:List[_], data:Any):Unit =  {
    val idx::rest = addr
    if (rest.isEmpty) {
      (idx, data) match {
        case (idx:List[_], data:List[_]) => 
          idx.zip(data).foreach { case (i, d) => 
            mem.asInstanceOf[Array[Any]](i.asInstanceOf[Int]) = d
          }
        case (idx,data) => 
          mem.asInstanceOf[Array[Any]](idx.asInstanceOf[Int]) = data
      }
    } else {
      val range = (idx, data) match {
        case (idx:List[_], data:List[_]) => idx.zip(data)
        case (idx,data) => List((idx, data))
      }
      range.foreach { case (i,d) => 
        vecStore(mem(i.asInstanceOf[Int]).asInstanceOf[Array[_]], rest, d)
      }
    }
  }
      """)

      emitOps
    }
  }

  def emitOps = {
    emitln(s"""
trait Enum
sealed trait Op extends Enum

sealed trait Op1 extends Op
sealed trait Op2 extends Op
sealed trait Op3 extends Op
sealed trait Op4 extends Op

sealed trait CompOp extends Op

sealed trait FixOp extends Op
case object FixAdd extends FixOp             with Op2
case object FixSub extends FixOp             with Op2
case object FixMul extends FixOp             with Op2
case object FixDiv extends FixOp             with Op2
case object FixMin extends FixOp             with Op2
case object FixMax extends FixOp             with Op2
case object FixLt  extends FixOp with CompOp with Op2
case object FixLeq extends FixOp with CompOp with Op2
case object FixGt  extends FixOp with CompOp with Op2
case object FixGeq extends FixOp with CompOp with Op2
case object FixEql extends FixOp with CompOp with Op2
case object FixNeq extends FixOp with CompOp with Op2
case object FixMod extends FixOp             with Op2
case object FixSra extends FixOp             with Op2
case object FixSla extends FixOp             with Op2
case object FixUsla extends FixOp            with Op2
case object FixNeg extends FixOp             with Op1
case object FixRandom extends FixOp          with Op1
case object FixUnif extends FixOp            with Op1
case object FixAbs extends FixOp             with Op1

sealed trait FltOp extends Op
case object FltAdd extends FltOp             with Op2
case object FltSub extends FltOp             with Op2
case object FltMul extends FltOp             with Op2
case object FltDiv extends FltOp             with Op2
case object FltMin extends FltOp             with Op2
case object FltMax extends FltOp             with Op2
case object FltLt  extends FltOp with CompOp with Op2
case object FltLeq extends FltOp with CompOp with Op2
case object FltGt  extends FltOp with CompOp with Op2
case object FltGeq extends FltOp with CompOp with Op2
case object FltEql extends FltOp with CompOp with Op2
case object FltNeq extends FltOp with CompOp with Op2
case object FltExp extends FltOp             with Op1
case object FltAbs extends FltOp             with Op1
case object FltLog extends FltOp             with Op1
case object FltSqr extends FltOp             with Op1
case object FltNeg extends FltOp             with Op1

sealed trait BitOp  extends Op
case object BitAnd  extends BitOp with Op2
case object BitOr   extends BitOp with Op2
case object BitNot  extends BitOp with Op1
case object BitXnor extends BitOp with Op2
case object BitXor  extends BitOp with Op2

case object MuxOp   extends Op with Op3
case object Bypass extends Op with Op1

val fixOps = List(FixAdd, FixSub, FixMul, FixDiv, FixMin, FixMax, FixLt, FixLeq, FixGt, FixGeq, FixEql, FixNeq, FixMod, FixSra, FixSla, FixUsla, FixNeg, FixRandom, FixUnif)
val fltOps = List(FltAdd, FltSub, FltMul, FltDiv, FltMin, FltMax, FltLt, FltGt, FltGeq, FltEql, FltNeq, FltExp, FltAbs, FltLog, FltSqr, FltNeg)
val bitOps = List(BitAnd, BitOr, BitNot, BitXnor, BitXor)
val otherOps = List(MuxOp, Bypass)

def ops = (fixOps ++ fltOps ++ bitOps ++ otherOps).toList
def compOps = ops.collect {case op:CompOp => op}
def fixCompOps = fixOps.collect {case op:CompOp => op}
def fltCompOps = fltOps.collect {case op:CompOp => op}

object ToInt {
  def unapply(x:Any):Option[Int] = x match {
    case x:Int => Some(x)
    case x:Float => Some(java.lang.Float.floatToIntBits(x))
    case _ => None
  }
}
object ToFloat {
  def unapply(x:Any):Option[Float] = x match {
    case x:Float => Some(x)
    case x:Int => Some(x.toFloat)
    case _ => None
  }
}
object ToBool {
  def unapply(x:Any):Option[Boolean] = x match {
    case x:Boolean => Some(x)
    case x:Int => Some(x > 0)
    case _ => None
  }
}

def eval(op:Op, ins:List[Any]):Any = {
  (op, ins) match {
    case (FixAdd   , ToInt(a)::ToInt(b)::_)     => (a + b)
    case (FixSub   , ToInt(a)::ToInt(b)::_)     => (a - b)
    case (FixMul   , ToInt(a)::ToInt(b)::_)     => (a * b)
    case (FixDiv   , ToInt(a)::ToInt(b)::_)     => (a / b)
    case (FixMin   , ToInt(a)::ToInt(b)::_)     => (Math.min(a, b))
    case (FixMax   , ToInt(a)::ToInt(b)::_)     => (Math.max(a, b))
    case (FixLt    , ToInt(a)::ToInt(b)::_)     => (a < b)
    case (FixLeq   , ToInt(a)::ToInt(b)::_)     => (a <= b)
    case (FixGt    , ToInt(a)::ToInt(b)::_)     => (a > b)
    case (FixGeq   , ToInt(a)::ToInt(b)::_)     => (a >= b)
    case (FixEql   , ToInt(a)::ToInt(b)::_)     => (a == b)
    case (FixNeq   , ToInt(a)::ToInt(b)::_)     => (a != b)
    case (FixMod   , ToInt(a)::ToInt(b)::_)     => (a % b)
    case (FixSra   , ToInt(a)::ToInt(b)::_)     => (a >> b)
    case (FixSla   , ToInt(a)::ToInt(b)::_)     => (a << b)
    case (FixUsla  , ToInt(a)::ToInt(b)::_)     => (a << b)
    case (FixNeg   , ToInt(a)::_)               => (-a)
    case (FixRandom, ToInt(a)::_)               => (0) //TODO
    case (FixUnif  , ToInt(a)::_)               => (0) //TODO

    case (FltAdd   , ToFloat(a)::ToFloat(b)::_) => (a + b)
    case (FltSub   , ToFloat(a)::ToFloat(b)::_) => (a - b)
    case (FltMul   , ToFloat(a)::ToFloat(b)::_) => (a * b)
    case (FltDiv   , ToFloat(a)::ToFloat(b)::_) => (a / b)
    case (FltMin   , ToFloat(a)::ToFloat(b)::_) => (Math.min(a , b))
    case (FltMax   , ToFloat(a)::ToFloat(b)::_) => (Math.max(a , b))
    case (FltLt    , ToFloat(a)::ToFloat(b)::_) => (a < b)
    case (FltLeq   , ToFloat(a)::ToFloat(b)::_) => (a <= b)
    case (FltGt    , ToFloat(a)::ToFloat(b)::_) => (a > b)
    case (FltGeq   , ToFloat(a)::ToFloat(b)::_) => (a >= b)
    case (FltEql   , ToFloat(a)::ToFloat(b)::_) => (a == b)
    case (FltNeq   , ToFloat(a)::ToFloat(b)::_) => (a != b)
    case (FltExp   , ToFloat(a)::ToFloat(b)::_) => (Math.exp(a))
    case (FltAbs   , ToFloat(a)::ToFloat(b)::_) => (Math.abs(a))
    case (FltLog   , ToFloat(a)::ToFloat(b)::_) => (Math.log(a))
    case (FltSqr   , ToFloat(a)::ToFloat(b)::_) => (Math.sqrt(a))
    case (FltNeg   , ToFloat(a)::ToFloat(b)::_) => (-a)

    case (BitAnd   , ToBool(a)::ToBool(b)::_)   => (a && b)
    case (BitOr    , ToBool(a)::ToBool(b)::_)   => (a || b)
    case (BitNot   , ToBool(a)::ToBool(b)::_)   => (!a)
    case (BitXnor  , ToBool(a)::ToBool(b)::_)   => (a == b)
    case (BitXor   , ToBool(a)::ToBool(b)::_)   => (a != b)

    case (Bypass   , a::_)                      => a
    case (MuxOp    , ToBool(true)::a::b::_)     => a
    case (MuxOp    , ToBool(false)::a::b::_)    => b
    case (op, (a:List[_])::(b:List[_])::(c:List[_])::rest) => (a,b,c).zipped.map { case (a,b,c) => eval(op, a::b::c::rest) }
    case (op, (a:List[_])::(b:List[_])::rest) => (a,b).zipped.map { case (a,b) => eval(op, a::b::rest) }
    case (op, a::(b:List[_])::(c:List[_])::rest) => (b,c).zipped.map { case (b,c) => eval(op, a::b::c::rest) }
    case (op, (a:List[_])::b::(c:List[_])::rest) => (a,c).zipped.map { case (a,c) => eval(op, a::b::c::rest) }
    case (op, (a:List[_])::rest) => a.map { a => eval(op, a::rest) }
    case (op, (a::(b:List[_])::rest)) => b.map { b => eval(op, a::b::rest) }

    case (op, ins) => throw new Exception("Don't know how to evaluate " + op + " ins=" + ins)
  }
}
""")

  }


}
