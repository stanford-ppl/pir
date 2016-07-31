package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._


abstract class Primitive(implicit val ctrler:Controller, design:Design) extends Node 
/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
case class CounterChain(name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "CC"
  /* Fields */
  var counters:List[Counter] = _ 
  /* Pointers */
  var dep:Option[CounterChain] = None
  var copy:Option[CounterChain] = None

  override def toUpdate = super.toUpdate || counters==null || dep==null || copy==null 

  def apply(num: Int)(implicit ctrler:Controller, design: Design):Counter = {
    if (counters == null) {
      // Speculatively create counters base on need and check index out of bound during update
      this.counters = (0 to num).map { j => Counter() }.toList
    }
    counters(num)
  }
  def copy(cp:CounterChain):Unit = {
    // Check whether speculative wire allocation was correct
    assert(counters.size <= cp.counters.size, 
      s"Accessed counter ${counters.size-1} of ${this} is out of bound")
    val addiCtrs = (counters.size until cp.counters.size).map {i => Counter()}
    counters = counters ++ addiCtrs
    counters.zipWithIndex.foreach { case(c,i) => 
      c.copy(cp.counters(i))
    }
    updateDep
    this.copy = Some(cp)
  }
  def update(bds: Seq[(Port, Port, Port)]):Unit = {
    counters = bds.zipWithIndex.map {case ((mi, ma, s),i) => Counter(mi, ma, s)}.toList
    updateDep
    this.copy = None 
  }

  def updateDep = {
    for (i <- 0 until counters.size - 1) {
      counters(i).setDep(counters(i+1))  
    }
    counters.last.setDep(None)
  }
}
object CounterChain {
  def apply(bds: (Port, Port, Port)*)(implicit ctrler:Controller, design: Design):CounterChain =
    {val c = CounterChain(None); c.update(bds); c}
  def apply(name:String, bds: (Port, Port, Port)*)(implicit ctrler:Controller, design: Design):CounterChain =
    {val c = CounterChain(Some(name)); c.update(bds); c}
  def copy(from:String, name:String) (implicit ctrler:Controller, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_${name}_copy"))
    def updateFunc(cp:Node) = cc.copy(cp.asInstanceOf[CounterChain])
    design.updateLater(s"${from}_${name}", updateFunc)
    cc
  }
  def copy(from:Controller, name:String) (implicit ctrler:Controller, design: Design):CounterChain = {
    copy(from.toString, name)
  }
}

case class Counter(val name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "Ctr"
  /* Fields */
  var min:Port = _
  var max:Port = _
  var step:Port = _
  val out:Port = Port(this, {s"${this}.out"}) 
  var dep:Option[Counter] = _
  var deped:Option[Counter] = None
  override def toUpdate = super.toUpdate || min==null || max==null || step==null || dep==null

  def update(mi:Port, ma:Port, s:Port):Unit = {
    min = mi
    max  = ma
    step = s
  }
  def setDep(c:Counter) = { dep = Some(c); c.deped = Some(this) }
  def setDep(c:Option[Counter]) = { 
    if (c.isDefined) { c.get.deped = Some(this) }
    dep = c
  }
  def copy(c:Counter) = {
    assert(min==null, 
      s"Overriding existing counter ${this} with min ${min}")
    assert(max==null, 
      s"Overriding existing counter ${this} with min ${max}")
    assert(step==null, 
      s"Overriding existing counter ${this} with min ${step}")
    def copyPort(p:Port):Port = {
      if (p.isConst) {
        p
      } else p.src match {
        case s@ScalarIn(n, scalar) => ScalarIn(n, scalar).out
        case _ => throw new Exception(s"Don't know how to copy port")
      }
    }
    update(copyPort(c.min), copyPort(c.max), copyPort(c.step))
  } 
}
object Counter{
  def apply(min:Port, max:Port, step:Port)(implicit ctrler:Controller, design: Design):Counter =
    { val c = Counter(None); c.update(min, max, step); c }
  def apply(name:String, min:Port, max:Port, step:Port)(implicit ctrler:Controller, design: Design):Counter =
    { val c = Counter(Some(name)); c.update(min, max, step); c }
  def apply()(implicit ctrler:Controller, design: Design):Counter = Counter(None)
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
case class SRAM(name: Option[String], size: Int)(implicit ctrler:Controller, design: Design) 
  extends Primitive {
  override val typeStr = "SRAM"

  var readAddr: Port = _
  var writeAddr: Port = _
  val readPort: Port = Port(this, s"${this}.rp") 
  var writePort: Port = _
  override def toUpdate = super.toUpdate || readAddr==null || writeAddr==null || writePort==null

  def updateRA(ra:Port):SRAM = { readAddr = ra; this } 
  def updateWA(wa:Port):SRAM = { writeAddr = wa; this } 
  def updateWP(wp:Port):SRAM = { writePort = wp; this } 
  def updateWP(vec:Vector):SRAM = updateWP(VecIn(vec).out)

  def load = readPort
}
object SRAM {
  /* Remote Write */
  def apply(size:Int, vec:Vector)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateWP(vec)
  def apply(name:String, size:Int, vec:Vector)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateWP(vec)
  def apply(size:Int, vec:Vector, readAddr:Port, writeAddr:Port)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateRA(readAddr).updateWA(writeAddr).updateWP(vec)
  def apply(name:String, size:Int, vec:Vector, readAddr:Port, writeAddr:Port)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateRA(readAddr).updateWA(writeAddr).updateWP(vec)

  /* Local Write */
  def apply(size:Int)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size)
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size)
  def apply(size:Int, readAddr:Port, writeAddr:Port)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size).updateRA(readAddr).updateWA(writeAddr)
  def apply(name:String, size:Int, readAddr:Port, writeAddr:Port)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size).updateRA(readAddr).updateWA(writeAddr)
}

trait Output extends Primitive
trait Input extends Primitive
case class ScalarIn(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) 
  extends Input{
  scalar.addReader(this)
  override val typeStr = "ScalIn"
  override def toString = s"${super.toString}($scalar)"
  val out:Port = Port(this, {s"${this}.out"}) 
  override def equals(that: Any) = that match {
    case n: ScalarIn => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
}

object ScalarIn {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn = 
    ScalarIn(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarIn =
    ScalarIn(Some(name), scalar)
}

case class ScalarOut(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) extends Output{
  scalar.setWriter(this)
  override val typeStr = "ScalOut"
  override def toString = s"${super.toString}($scalar)"
  override def equals(that: Any) = that match {
    case n: ScalarOut => n.scalar==scalar && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
}
object ScalarOut {
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(Some(name), scalar)
}

case class VecIn(name: Option[String], vector:Vector)(implicit ctrler:Controller, design: Design) 
  extends Input{
  vector.addReader(this)
  override val typeStr = "VecIn"
  val out:Port = Port(this, {s"${this}.out"}) 
  override def equals(that: Any) = that match {
    case n: VecIn => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
}

object VecIn {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecIn = 
    VecIn(Some(name), vector)
}

case class VecOut(name: Option[String], vector:Vector)(implicit ctrler:Controller, design: Design) extends Output{
  vector.setWriter(this)
  override val typeStr = "VecOut"
  override def equals(that: Any) = that match {
    case n: VecOut => n.vector==vector && n.ctrler == ctrler 
    case _ => super.equals(that)
  }
}
object VecOut {
  def apply(vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    VecOut(None, vector)
  def apply(name:String, vector:Vector)(implicit ctrler:Controller, design: Design):VecOut = 
    VecOut(Some(name), vector)
}

case class Stage(name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "Stage"
  var operands:List[Port] = _
  var op:Op = _
  var result:Port = _
  override def toUpdate = super.toUpdate || operands==null || op==null || result==null
} 
object Stage {
  /* No Sugar API */
  def apply(stage:Stage, opds:List[Port], o:Op, r:Port)(implicit ctrler:ComputeUnit):Unit= {
    stage.operands = opds
    stage.op = o
    stage.result = r 
    ctrler.addStage(stage)
  }
  /* Sugar API */
  def apply(stage:Stage, op1:Port, op:Op, result:Port)(implicit ctrler:ComputeUnit):Unit =
    Stage(stage, List(op1), op, result)
  def apply(stage:Stage, op1:Port, op2:Port, op:Op, result:Port)(implicit ctrler:ComputeUnit):Unit = 
    Stage(stage, List(op1, op2), op, result)
  def apply(stage:Stage, op1:Port, op2:Port, op3:Port, op:Op, result:Port)(implicit ctrler:ComputeUnit):Unit =
    Stage(stage, List(op1, op2, op3), op, result)
  //TODO
  def reduce(op:Op)(implicit ctrler:ComputeUnit, design:Design):Stage = {
    val numStages = (Math.ceil(Math.log(design.arch.numLanes))/Math.log(2)).toInt 
    val rdstages = Stages.reduce(numStages) 
    rdstages.foreach {s =>
      Stage(s, List(ctrler.reduce(s).read), op, ctrler.reduce(s).read)
    }
    rdstages.last
  }
}
object Stages {
  def addMaps(s:Stage, ctrler:ComputeUnit) = {
    ctrler.stageUses += (s -> Set[PipeReg]())
    ctrler.stageDefs += (s -> Set[PipeReg]())
    ctrler.stagePRs += (s -> HashMap[Int, PipeReg]())
  }
  def apply(n:Int) (implicit ctrler:ComputeUnit, design: Design):List[Stage] = {
    List.tabulate(n) {i => 
      val s = Stage(None)
      addMaps(s, ctrler)
      s
    }
  }
  def reduce(n:Int) (implicit ctrler:ComputeUnit, design: Design):List[ReduceStage] = {
    List.tabulate(n) {i => 
      val s = new {override val idx = i} with Stage(None) with ReduceStage
      addMaps(s, ctrler)
      s
    }
  }
}
trait ReduceStage extends Stage {
  val idx:Int
  override val typeStr = s"RedStage"
}

trait Reg extends Primitive {
  var in:Option[Port] = None
  val out:Port = Port(this, {s"${this}"}) 
  def read:Port = out
}

/*
 * A Pipeline Register keeping track of which stage (column) and which logical register (row)
 * the PR belongs to
 * @param n Optional user defined name
 * @param regId Register ID the PipeReg mapped to
 **/
case class PipeReg(name:Option[String], stage:Stage, regId:Int)(implicit ctrler:Controller, design: Design) extends Reg{
  override val typeStr = "PR"
  override def toString = s"${super.toString}_${stage.name.getOrElse("")}${regId}"
  def this (stage:Stage, regId:Int)(implicit ctrler:Controller, design: Design) = this(None, stage, regId)
}
trait LoadPR      extends PipeReg {override val typeStr = "PRld"}
trait StorePR     extends PipeReg {override val typeStr = "PRst"}
trait CtrPR       extends PipeReg {override val typeStr = "PRct"}
trait ReducePR    extends PipeReg {override val typeStr = "PRrd"}
trait VecInPR     extends PipeReg {override val typeStr = "PRvi"}
trait VecOutPR    extends PipeReg {override val typeStr = "PRvo"}
trait ScalarInPR  extends PipeReg {override val typeStr = "PRsi"; val scalarIn:ScalarIn }
trait ScalarOutPR extends PipeReg {override val typeStr = "PRso"; val scalarOut:ScalarOut }
trait TempPR      extends PipeReg {override val typeStr = "PRtp"}

