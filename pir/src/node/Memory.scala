package pir
package node

import prism.graph._

abstract class Memory(implicit env:Env) extends PIRNode {

  /*  ------- Fields -------- */
  val in = new InputField[List[Access]]
  val out = new OutputField[List[Access]]

  /*  ------- Metadata -------- */
  val inits = Metadata[List[Any]]("inits")

  val dims = Metadata[List[Int]]("dims", default=List(1))

  val banks = Metadata[List[Int]]("banks")

  val depth = Metadata[Int]("depth", default=1)

  override def asInput = Some(in)
  override def asOutput = Some(out)

}

case class Reg()(implicit env:Env) extends Memory
case class FIFO()(implicit env:Env) extends Memory
case class SRAM()(implicit env:Env) extends Memory
case class RegFile()(implicit env:Env) extends Memory
case class LUT()(implicit env:Env) extends Memory

case class InputBuffer(isFIFO:Boolean=false)(implicit env:Env) extends Memory

case class Top()(implicit env:Env) extends PIRNode {
  val topCtrl = ControlTree("sequenced")
  val argFringe = ArgFringe().setParent(this)
  val hostInCtrl = ControlTree("sequenced").setParent(topCtrl)
  val hostOutCtrl = ControlTree("sequenced").setParent(topCtrl)
  val hostRead = HostRead().setParent(argFringe).ctrl(hostOutCtrl)
}

case class ArgFringe()(implicit env:Env) extends PIRNode
case class MemoryContext()(implicit env:Env) extends PIRNode
case class Context()(implicit env:Env) extends PIRNode

trait Def extends PIRNode {
  val out = new Output
  override def asOutput:Option[Output] = Some(out)
}

case class Const(value:Any)(implicit env:Env) extends Def
case class OpDef(op:String)(implicit env:Env) extends Def {
  val input = new InputField[List[PIRNode]]
}
case class HostRead()(implicit env:Env) extends Def {
  val input = new InputField[List[PIRNode]]
}
case class Counter(par:Int)(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val min = new InputField[PIRNode]
  val step = new InputField[PIRNode]
  val max = new InputField[PIRNode]

  val iters = List.fill(par) { new Output }
  val valids = List.fill(par) { new Output }
}

case class Controller()(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val cchain = new ChildField[Counter, List[Counter]]
  val en = new InputField[Option[PIRNode]]
  val done = new OutputField[Option[PIRNode]]

  val validEn = new InputField[Set[PIRNode]]
  val validDone = new OutputField[Option[PIRNode]]
}

//case class FIFO()(implicit env:BuildEnvironment) extends Memory

//case class Reg()(implicit env:BuildEnvironment) extends Memory
//case class ArgIn()(implicit env:BuildEnvironment) extends Memory
//case class ArgOut()(implicit env:BuildEnvironment) extends Memory
//case class DramAddress(dram:DRAM)(implicit env:BuildEnvironment) extends Memory

//trait Stream extends Memory {
  //val field:String
//}
//case class StreamIn(field:String)(implicit env:BuildEnvironment) extends Stream
//case class StreamOut(field:String)(implicit env:BuildEnvironment) extends Stream

//case class TokenIn()(implicit env:BuildEnvironment) extends Memory
//case class TokenOut()(implicit env:BuildEnvironment) extends Memory

//case class RetimingFIFO()(implicit env:BuildEnvironment) extends Memory

trait MemoryUtil {
  def isBuffer(n:N) = n match {
    case n:InputBuffer => true
    case _ => false
  }
  //def isFIFO(n:PIRNode) = n match {
    //case n:FIFO => true
    //case n:RetimingFIFO => true
    //case n:StreamIn => true
    //case n:StreamOut => true
    //case _ => false
  //}

  //def isReg(n:PIRNode) = n match {
    //case n:Reg => true
    //case n:ArgIn => true
    //case n:DramAddress => true
    //case n:ArgOut => true
    //case n:TokenIn => true
    //case n:TokenOut => true
    //case n => false
  //}

  //def isRemoteMem(n:PIRNode) = n match {
    //case (_:SRAM)  => true
    //case n:FIFO if writersOf(n).size > 1 => true
    //case n:RegFile => true
    //case n:LUT => true
    //case _ => false
  //}

  //def isLocalMem(n:PIRNode) = !isRemoteMem(n)

  //def isControlMem(n:Memory) = n match {
    //case n:TokenIn => true
    //case n:TokenOut => true
    //case StreamIn("ack") => true
    //case _ => false
  //}

  //def isBackPressure(n:Primitive) = n match {
    //case n:NotFull => true
    //case n:DataReady => true
    //case _ => false
  //}

}
