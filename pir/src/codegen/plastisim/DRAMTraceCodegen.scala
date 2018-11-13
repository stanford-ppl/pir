package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.codegen._

import scala.collection.mutable

trait ProgramOrderTraversal extends PIRTraversal with ChildFirstTraversal {
  lazy val ctrlMap = top.descendents.as[List[PIRNode]].groupBy { _.ctrl.v }

  override def visitFunc(n:N):List[N] = n match {
    case n:Top => scheduleCtrl(n.topCtrl)
    case n:Controller => scheduleCtrl(n.ctrl.get)
    case n => Nil
  }

  def scheduleCtrl(ctrl:ControlTree) = {
    var nodes:List[PIRNode] = ctrlMap.get(Some(ctrl)).getOrElse(Nil)
    nodes ++= ctrl.children.map { _.as[ControlTree].ctrler.get }
    ctrl.ctrler.v.foreach { ctrler =>
      nodes = nodes.filterNot { node => node == ctrler }
    }
    if (ctrl.parent.isEmpty) { nodes ++= ctrlMap(None) }

    val (consts, (mems, others)) = nodes.partition { 
      _.isInstanceOf[Const] 
    }.map2 {
      _.partition { _.isInstanceOf[Memory]}
    }
    val ordered = others.sortBy { _.id }
    consts ++ mems ++ ordered
  }

}

class DRAMTraceCodegen(implicit compiler:PIR) extends ProgramOrderTraversal with ScalaCodegen with PlastisimUtil { self =>

  override def dirName = tracePath
  override val fileName = traceName

  // mutable.ListMap doesn't preserve the order even scaladoc says so
  var offsetMap = scala.collection.immutable.ListMap[DRAM, Int]()
  val tracked = mutable.ListBuffer[PIRNode]()

  implicit class TraceOp(n:PIRNode) {
    def isTracked = tracked.contains(n)
  }

  override def initPass = {
    super.initPass
    offsetMap = scala.collection.immutable.ListMap[DRAM, Int]()
    tracked.clear
    pirTop.collectDown[DRAMCommand]().foreach { fringe =>
      fringe.localIns.foreach { in =>
        val field = in.as[Field[_]]
        val fname = field.name
        if (fname == "offset" || fname == "size" || fname == "addr") {
          val node = field.T.as[PIRNode]
          val nodes = node.accum({ 
            case x:CounterIter => true
            case x:CounterValid => true
            case x:GlobalContainer => true
            case x => false
          }, visitGlobalIn _)
          dbgblk(s"tracing $fringe.$in") {
            nodes.foreach { node => dbg(node) }
          }
          tracked ++= nodes.asInstanceOf[List[PIRNode]]
        }
      }
    }
  }

  override def runPass = {
    setDramOffset
    emitHeader
    super.runPass
    emitFooter
  }

  override def finPass = {
    super.finPass
    val log = s"$dirName/trace.log"
    val exitCode = shell("trace", s"bash run_trace.sh", log)
    if (exitCode != 0) {
      fail(s"fail to generate trace")
    }
  }

  override def emitNode(n:N) = n match {
    case n:LoopController =>
      def prev():Unit = {
        visitNode(n)
      }
      val lambda = n.cchain.T.foldLeft(prev _) { case (prev, counter) =>
        { () => 
          emitLambda(s"${counter}.foreach", s"iter_$counter" ) {
            prev()
          }
        }
      }
      lambda()
    case n:Controller =>
      emitln(s"// $n {")
      incLevel
      visitNode(n)
      decLevel
      emitln(s"// } $n")
    case n:Counter => 
      emitln(s"val $n = (${n.min.T} until ${n.max.T} by (${n.step.T} * ${n.par}))")
    case n@Const(v) => 
      emitln(s"val $n = $v")
    case n:ControllerValid =>
    case n:ControllerDone =>
    case n:CounterValid =>
    case n@CounterIter(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"val $n = iter_$ctr + $i * ${ctr.step.T}")
    case n@CounterIter(None) =>
      val ctr = n.counter.T
      emitln(s"val $n = List.tabulate(${ctr.par}) { i => iter_$ctr + i * ${ctr.step.T} }")
    case n:FringeDenseLoad =>
      emitln(s"// $n")
      emitln(s"""trace("${n}_size.trace", ${n.size.T.as[MemRead].mem.T.inAccesses.head})""")
      emitln(s"""trace("${n}_offset.trace", ${n.offset.T.as[MemRead].mem.T.inAccesses.head})""")
    case n:FringeDenseStore =>
      emitln(s"// $n")
      emitln(s"""trace("${n}_size.trace", ${n.size.T.as[MemRead].mem.T.inAccesses.head})""")
      emitln(s"""trace("${n}_offset.trace", ${n.offset.T.as[MemRead].mem.T.inAccesses.head})""")
    case n:FringeSparseLoad =>
      emitln(s"// $n")
      emitln(s"""trace("${n}_addr.trace", ${n.addr.T.as[MemRead].mem.T.inAccesses.head})""")
    case n:FringeSparseStore =>
      emitln(s"// $n")
      emitln(s"""trace("${n}_addr.trace", ${n.addr.T.as[MemRead].mem.T.inAccesses.head})""")
    case n:FIFO =>
      emitln(s"val $n = mutable.Queue[Any]()")
    case n:Reg =>
      emitln(s"var $n:Any = None")
    case n:MemWrite if n.isTracked =>
      n.mem.T match {
        case mem:Reg =>
          emitln(s"$mem = ${n.data.T}")
        case mem:FIFO =>
          emitln(s"$mem.enqueue(${n.data.T})")
      }
      emitln(s"val $n = ${n.data.T}")
    case n:MemRead if n.isTracked =>
      n.mem.T match {
        case mem:Reg =>
          emitln(s"val $n = $mem")
        case mem:FIFO =>
          emitln(s"val $n = $mem.dequeue()")
      }
    case n:DRAMAddr if n.isTracked =>
      emitln(s"val $n = ${offsetMap(n.dram)} // DRAMAddr(${n.dram})")
    case n:GlobalContainer =>
    case n@OpDef(op) if n.isTracked =>
      val ins = n.input.T
      emitln(s"""val $n = eval("$op")(${ins.mkString(",")})""")
    case n => 
      emitln(s"// $n")
      visitNode(n)
  }

  def setDramOffset = dbgblk(s"setDramOffset"){
    val drams = pirTop.collectDown[DRAMCommand]().map { _.dram }.distinct
    var offset = 0
    drams.foreach { dram =>
      val dims = dram.dims.get
      emitComment(s"$dram -> $offset dims=$dims size=${dims.product}")
      offsetMap += dram -> offset
      val size = dims.product
      offset += size
    }
  }

  def emitHeader = {
    emitln(s"import scala.collection.mutable ")
    emitln(s"import java.io._ ")
    emitln(s"object tracer {")
    incLevel 
    emitln(s"val pws = mutable.Map[String, PrintWriter]()")
    emitln(s"def main(args:Array[String]) = {")
    incLevel 
  }

  def emitFooter = {
    emitln(s"pws.foreach(_._2.close)")
    decLevel 
    emitln("}")
    emitBlock(s"def trace(fileName:String, node:Any) = node match") {
      emitln(s"""case node:List[_] => getWriter(fileName).write(node.mkString(" ") + "\\n")""")
      emitln(s"""case node => getWriter(fileName).write(node.toString + "\\n")""")
    }
    emitBlock(s"def getWriter(fileName:String) = ") {
      emitln(s"pws.getOrElseUpdate(fileName, new PrintWriter(new File(fileName)))")
    }
    emitln(helperFunctions)
    decLevel 
    emitln("}")
  }

  val helperFunctions = """
  def eval(op:String)(inputs:Any*):Any = {
    val vecs = inputs.collect { case vec:List[_] => vec }
    val vecSize = assertUnify(vecs) { _.size }.getOrElse(1)
    val vins = inputs.map {
      case vec:List[_] => vec
      case e => List.fill(vecSize)(e)
    }
    val vres = List.tabulate(vecSize) { i =>
      val ins = vins.map { _(i) }
      evalOp(op)(ins)
    }
    if (vecs.size == 0) {
      vres.head
    } else {
      vres
    }
  }
  def evalOp(op:String)(inputs:Seq[Any]):Any = {
    (op, inputs) match {
      case ("FixAdd", Seq(a:Int, b:Int)) => a + b
      case ("FixAdd", Seq(a:Float, b:Float)) => a * b
      case ("FixMul", Seq(a:Int, b:Int)) => a * b
      case ("FixMul", Seq(a:Float, b:Float)) => a * b
      case ("FixFMA", Seq(a:Int, b:Int, c:Int)) => a * b + c
      case ("FixFMA", Seq(a:Float, b:Float, c:Float)) => a * b + c
      case ("FixToFix", ins) => ins(0)
      case op => throw new Exception(s"Don't know how to eval $op")
    }
  }

  def assertUnify[A,B](list:Iterable[A])(lambda:A => B):Option[B] = {
    val res = list.map(a => lambda(a))
    assert(res.toSet.size<=1, s"$list doesnt have the same evaluation = $res")
    res.headOption
  }
"""

}
