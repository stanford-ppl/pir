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
  var counters:List[Counter] = Nil 
  /* Pointers */
  var dep:Option[CounterChain] = None
  var copy:Option[CounterChain] = None
  toUpdate = true

  def apply(i: Int)(implicit ctrler:Controller, design: Design):Counter = {
    if (counters.size == 0) {
      // Speculatively create counters base on need and check index out of bound during update
      this.counters = (0 to i).map { j => Counter() }.toList
    }
    counters(i)
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
    this.copy = Some(cp)
    toUpdate = false
  }
  def update(bds: Seq[(Port, Port, Port)]):Unit = {
    counters = bds.zipWithIndex.map {case ((mi, ma, s),i) => Counter(mi, ma, s)}.toList
    this.copy = None 
    toUpdate = false
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
  toUpdate = true

  def update(mi:Port, ma:Port, s:Port):Unit = {
    min = mi
    max  = ma
    step = s
    toUpdate = false
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
        case s@ScalarIn(n, scalar) => s.writer match {
          case Right(w) => new ScalarIn(n, scalar, w).out 
          case Left(w) => new ScalarIn(n, scalar, w).out
        }
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
case class SRAM(name: Option[String], size: Int, writer:Controller)(implicit ctrler:Controller, design: Design) 
  extends Primitive {
  override val typeStr = "SRAM"

  var readAddr: Port = _
  var writeAddr: Port = _
  val readPort: Port = Port(this, s"${this}.rp") 

  toUpdate = true
  def update (ra:Port, wa:Port) = {
    this.readAddr = ra
    this.writeAddr = wa
    toUpdate = false
  }
  def load = readPort
}
object SRAM {
  def apply(size:Int, write:Controller)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(None, size, write)
  def apply(name:String, size:Int, write:Controller)(implicit ctrler:Controller, design: Design): SRAM
    = SRAM(Some(name), size, write)
  def apply(size:Int, write:Controller, readAddr:Port, writeAddr:Port)(implicit ctrler:Controller, design: Design): SRAM
    = { val s = SRAM(None, size, write); s.update(readAddr, writeAddr); s } 
  def apply(name:String, size:Int, write:Controller, readAddr:Port, writeAddr:Port)(implicit ctrler:Controller, design: Design): SRAM
    = { val s = SRAM(Some(name), size, write); s.update(readAddr, writeAddr); s } 
}

case class ScalarIn(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) 
  extends Primitive  {
  override val typeStr = "ScalarIn"
  var writer:Either[String,Controller] = _
  toUpdate = true
  val out:Port = Port(this, {s"${this}.out"}) 
  override def equals(that: Any) = that match {
    case n: ScalarIn => n.scalar==scalar && n.writer == writer && n.ctrler == ctrler 
    case _ => super.equals(that)
  }

  def this(n: Option[String], scalar:Scalar, w:Controller)(implicit ctrler:Controller, design: Design) = {
    this(n, scalar)
    update(w)
  }
  def this(n: Option[String], scalar:Scalar, w:String)(implicit ctrler:Controller, design: Design) = {
    this(n, scalar)
    writer = Left(w)
    design.updateLater(s"${w}", update _)
  }

  def update(n:Node) = {writer = Right(n.asInstanceOf[Controller]); toUpdate = false}
}

object ScalarIn {
  def apply(scalar:Scalar, writer:String)(implicit ctrler:Controller, design: Design):ScalarIn = { 
    new ScalarIn(None, scalar, writer)
  }
  def apply(scalar:ArgIn)(implicit ctrler:Controller, design: Design):ScalarIn = { 
    ScalarIn(scalar, "Top")
  }
  def apply(scalar:Scalar, writer:Controller)(implicit ctrler:Controller, design: Design):ScalarIn = 
    new ScalarIn(None, scalar, writer)
  def apply(name:String, scalar:Scalar, writer:Controller)(implicit ctrler:Controller, design: Design):ScalarIn = 
    new ScalarIn(Some(name), scalar, writer)
}

case class ScalarOut(name: Option[String], scalar:Scalar)(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "ScalarOut"
}
object ScalarOut {
  //TODO check argout
  def apply(scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(None, scalar)
  def apply(name:String, scalar:Scalar)(implicit ctrler:Controller, design: Design):ScalarOut = 
    ScalarOut(Some(name), scalar)
}

case class Stage(name:Option[String])(implicit ctrler:Controller, design: Design) extends Primitive {
  override val typeStr = "Stage"
  var operands:List[Port] = _
  var op:Op = _
  var result:Port = _
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
  def reduce(stage:Stage, op:Op)(implicit ctrler:ComputeUnit):Unit = {
    Stage(stage, List(ctrler.reduce(stage).read), op, ctrler.reduce(stage).read)
  }

}
object Stages {
  def apply(n:Int) (implicit ctrler:ComputeUnit, design: Design):List[Stage] = {
    List.tabulate(n) {i => 
      val s = Stage(None)
      ctrler.stageUses += (s -> Set[PipeReg]())
      ctrler.stageDefs += (s -> Set[PipeReg]())
      ctrler.stagePRs += (s -> HashMap[Int, PipeReg]())
      s
    }
  }
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

