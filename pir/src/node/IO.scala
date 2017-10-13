package pir.node

import pir._

import pirc._

import scala.collection.mutable.ListBuffer
import scala.math.max

class Range (s:Output, e:Output) {
  val start:Output = s
  val end:Output = e
  def by(step:Output) = (start, end, step)
}

abstract class IO(implicit val src:Module, design:PIR) extends Node {
  src.addIO(this)
  def isConnected:Boolean
  def isOutput = this.isInstanceOf[Output]
  def isInput = this.isInstanceOf[Input]
  def asOutput = this.asInstanceOf[Output]
  def asInput = this.asInstanceOf[Input]

  def ctrler:Controller = src match {
    case p:Primitive => p.ctrler
    case ctrler:Controller => ctrler
  }

  def isGlobal:Boolean
}
class Input(implicit src:Module, design:PIR) extends IO {
  override val typeStr = "Input"
  var _from:Output = _
  def from:Output = _from
  def isConnected = from!=null
  def connect(o:Output):Unit = { 
    if (isConnected) {
      assert(from == o, s"${this}(id=$id) is already connected to ${from} but trying to reconnect to $o")
    } else {
      _from = o; 
      o.connect(this)
      this match {
        case in:GlobalInput =>
        case _ if isGlobal =>
          throw PIRException(s"Only GlobalIO can go across CU boundary! in=${this.ctrler}.$this out=${o.ctrler}.$o")
        case _ =>
      }
    }
  }
  def isConnectedTo(o:Output) = { from == o }
  def disconnect:Unit = if (isConnected) { from.disconnect(this); _from = null }

  def isGlobal:Boolean = { isConnected && !from.src.isConst && from.ctrler != ctrler }
}
object Input {
  def apply[S<:Module](s:S)(implicit design:PIR):Input = new Input()(s, design)
  def apply[S<:Module](s:S, toStr: => String)(implicit design:PIR):Input = {
    new Input()(s, design) {override def toString = toStr}
  }
}
class Output(implicit src:Module, design:PIR) extends IO {
  val _to:ListBuffer[Input] = new ListBuffer[Input]()
  def to:List[Input] = _to.toList
  def isConnected = to.size!=0
  def isConnectedTo(i:Input) = { to.contains(i) }
  def connect(i:Input):Unit = {
    if (!_to.contains(i)) {
      _to += i;
      i.connect(this)
    }
  } 
  def disconnect:Unit = { to.foreach { _.disconnect }; assert(to.isEmpty) }
  def disconnect(in:Input):Unit = { 
    if (isConnectedTo(in)) {
      in.disconnect
      _to -= in
    }
  } 
  override val typeStr = "Output"
  def width(implicit design:PIR) = design.arch.wordWidth
  def by(step:Output)(implicit design:PIR) = (Const(0).out, this, step)
  def until(max:Output) = new Range(this, max)

  def isGlobal:Boolean = { isConnected && to.exists{ ip => !ip.src.isConst && ip.ctrler != ctrler } }
}
object Output {
  def apply(s:Module)(implicit design:PIR):Output = new Output()(s, design)
  def apply(s:Module, toStr: => String)(implicit design:PIR):Output = {
    new Output()(s, design) { override def toString = toStr }
  }
  def apply(s:Module, t:Input, toStr: => String)(implicit design:PIR):Output = {
    new Output()(s, design) { override def toString = toStr; t.connect(this)}
  }
}

trait GlobalIO extends IO with Primitive {
  val variable:Variable
  override def equals(that: Any) = that match {
    case io:GlobalIO => io.variable==variable && io.ctrler == ctrler && this.isInput == io.isInput
    case _ => super.equals(that)
  }

  override def asInput = this.asInstanceOf[GlobalInput]
  override def asOutput = this.asInstanceOf[GlobalOutput]
  def isControl = variable.isInstanceOf[Control]
  def isScalar = variable.isInstanceOf[Scalar]
  def isVector = variable.isInstanceOf[Vector]
}

case class GlobalInput(variable:Variable)(implicit ctrler:Controller, design:PIR) extends Input with GlobalIO {
  override val typeStr = variable match {
    case _:Control => s"ControlInput"
    case _:Scalar => s"ScalarInput"
    case _:Vector => s"VectorInput"
  }
  variable.addReader(this)
  //def writer:Output[Variable] = variable.writer
  val out = Output(this, s"${this}.out")
  val valid = Output(this, s"${this}.valid")
  override def from:GlobalOutput = super.from.asInstanceOf[GlobalOutput]
}

case class GlobalOutput(variable:Variable)(implicit ctrler:Controller, design:PIR) extends Output with GlobalIO {
  override val typeStr = variable match {
    case _:Control => s"ControlOutput"
    case _:Scalar => s"ScalarOutput"
    case _:Vector => s"VectorOutput"
  }
  variable.setWriter(this)
  //def readers:List[Input[Variable]]
  val in = Input(this, s"${this}.in")
  val valid = Input(this, s"${this}.valid")
  override def to:List[GlobalInput] = super.to.map{_.asInstanceOf[GlobalInput]}
}


