package pir.node

import pir._

import pirc._
import pirc.util._

abstract class Primitive(implicit design: PIR) extends PIRNode with prism.node.Atom[PIRNode] { self =>
  override def ins:List[Input] = super.ins.asInstanceOf[List[Input]]
  override def outs:List[Output] = super.outs.asInstanceOf[List[Output]]

  lazy val out = new Output
  out //Make sure lazy val is evaluated so in swapOutput the IO patterns are the same

  def connect(io:IO)(implicit design:PIR):IO = {
    io match {
      case io:Input => out.connect(io)
      case io:Output => new Input()(this, design).connect(io)
    }
  }
  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Primitive => this.connect(x.out) // LocalStore override this function. it connects to Memory.in
      case x => super.connectFields(x, i)
    }
  }

  override def evaluateFields(f:Any, x:Any):Any = (f, x) match {
    case (f, x:IO) => x.singleConnected.map{_.src}.getOrElse(null)
    case (f,x) => super.evaluateFields(f,x)
  }
}

