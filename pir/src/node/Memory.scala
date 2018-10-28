package pir
package node

import prism.graph._
import prism.graph.implicits._

trait MemoryNode extends PIRNode {
  /*  ------- Metadata -------- */
  val inits = Metadata[List[Any]]("inits")
  val dims = Metadata[List[Int]]("dims", default=List(1))
  val banks = Metadata[List[Int]]("banks")
  val depth = Metadata[Int]("depth", default=1)
}

abstract class Memory(implicit env:Env) extends MemoryNode with DefNode[PIRNode] {

  /*  ------- Fields -------- */
  val in = new InputField[List[Access]]
  val out = new OutputField[List[Access]]

  override def asInput = Some(in)
}

case class Reg()(implicit env:Env) extends Memory
case class FIFO()(implicit env:Env) extends Memory
case class SRAM()(implicit env:Env) extends Memory
case class RegFile()(implicit env:Env) extends Memory
case class LUT()(implicit env:Env) extends Memory

case class Top()(implicit env:Env) extends PIRNode {
  val topCtrl = ControlTree("Pipelined")
  val argFringe = ArgFringe().setParent(this)
  val hostInCtrl = ControlTree("Sequenced").setParent(topCtrl)
  val hostOutCtrl = ControlTree("Sequenced").setParent(topCtrl)
  val hostRead = HostRead().setParent(argFringe).ctrl(hostOutCtrl)
  val hostWrite = HostWrite().setParent(argFringe).ctrl(hostInCtrl)
  val hostInDone = HostWrite().setParent(argFringe).ctrl(hostInCtrl)
  val hostOutDone = HostWrite().setParent(argFringe).ctrl(hostOutCtrl)
}

trait GlobalContainer extends PIRNode
case class ArgFringe()(implicit env:Env) extends GlobalContainer
case class MemoryContainer()(implicit env:Env) extends GlobalContainer
case class Context()(implicit env:Env) extends PIRNode

trait Def extends PIRNode with DefNode[PIRNode] {
  final val out = new OutputField[List[PIRNode]]
}

case class Const(value:Any)(implicit env:Env) extends Def
case class OpDef(op:String)(implicit env:Env) extends Def {
  val input = new InputField[List[PIRNode]]
}
case class HostRead()(implicit env:Env) extends Def {
  val input = new InputField[List[PIRNode]]
}
case class HostWrite()(implicit env:Env) extends Def
case class CountAck()(implicit env:Env) extends Def {
  val input = new InputField[List[PIRNode]]
}
case class Counter(par:Int)(implicit env:Env) extends Def {
  /*  ------- Fields -------- */
  val min = new InputField[PIRNode]
  val step = new InputField[PIRNode]
  val max = new InputField[PIRNode]
  def iters = this.collectOut[CounterIter]().sortBy { _.i }
  def valids = this.collectOut[CounterValid]().sortBy { _.i }
}

case class CounterIter(i:Int)(implicit env:Env) extends Def {
  val counter = new InputField[Counter]
}
case class CounterValid(i:Int)(implicit env:Env) extends Def {
  val counter = new InputField[Counter]
}

case class Controller()(implicit env:Env) extends PIRNode {
  /*  ------- Fields -------- */
  val cchain = new ChildField[Counter, List[Counter]]

  val en = new InputField[Option[PIRNode]]
  val parentEn = new InputField[Option[PIRNode]]

  val valid = ControllerValid().resetParent(this)
  val done = ControllerDone().resetParent(this)
}
case class ControllerDone()(implicit env:Env) extends Def
case class ControllerValid()(implicit env:Env) extends Def

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

trait MemoryUtil extends CollectorImplicit {

  implicit class MemUtil(n:Memory) {
    def inAccess = n.collect[Access](visitGlobalIn _)
    def outAccess = n.collect[Access](visitGlobalOut _)
    def accesses = inAccess ++ outAccess

    def isFIFO = n match {
      case n:FIFO => true
      case _ => false
    }
  }

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
