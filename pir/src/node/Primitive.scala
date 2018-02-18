package pir.node

import pir._

import pirc._
import pirc.util._

abstract class Primitive(implicit design: PIR) extends PIRNode with prism.node.Atom[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  lazy val out = new Output
  out
  //Make sure lazy val is evaluated so in swapOutput the IO patterns are the same
  //Has to be lazy to avoid null pointer exception during construction in subclasses

  def isInputField(field:Any, fieldIdx:Int) = true 
  final def isOutputField(field:Any, fieldIdx:Int) = !isInputField(field, fieldIdx)

  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => out.connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }

  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Primitive if isInputField(x, i) => this.connect(x.out)
      case x:Memory if isOutputField(x, i) => this.out.connect(x.newIn)
      case x:Iterable[_] if isOutputField(x,i) =>
        val outs = super.connectFields(x,i).asInstanceOf[Iterable[Output]].toSet.head
      case x => super.connectFields(x, i)
    }
  }

  override def evaluateFields(f:Any, x:Any):Any = (f, x) match {
    case (f:Iterable[_], x:Output) => x.connected.map{_.src}
    case (f, x:IO) => x.singleConnected.map{_.src}.getOrElse(null)
    case (f,x) => super.evaluateFields(f,x)
  }
}

