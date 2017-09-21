package spade.node

import spade._
import spade.util._

/* Routable element at interconnection level */
trait Routable extends Module with Simulatable {
  import spademeta._
  implicit val ctrler:this.type = this 
  def scalarIO:ScalarIO[this.type]
  def vectorIO:VectorIO[this.type]
  def ctrlIO:ControlIO[this.type]
  def gridIOs = scalarIO :: vectorIO :: ctrlIO :: Nil
  def sins = scalarIO.ins // Scalar Inputs
  def souts = scalarIO.outs // Scalar Outputs
  def vins = vectorIO.ins// Input Buses/Vector inputs
  def vouts = vectorIO.outs // Output Buses/Vector outputs
  def cins = ctrlIO.ins // Control inputs
  def couts = ctrlIO.outs // Control outputs

  def isMCU:Boolean = this.isInstanceOf[MemoryComputeUnit]
  def isSCU:Boolean = this.isInstanceOf[ScalarComputeUnit]
  def isTop:Boolean = this.isInstanceOf[Top]
  def asCU:ComputeUnit = this.asInstanceOf[ComputeUnit]
  def genConnections:this.type = { ConfigFactory.genConnections(this); this } 
  def config(implicit spade:SwitchNetwork):Unit = {}
  //override def toString = s"${coordOf.get(this).fold(super.toString) { case (x,y) => s"$typeStr[$x,$y]"}}"
  def register(implicit sim:Simulator):Unit = {
    (sins ++ vins).foreach { in =>
      in.v.valid.default = false
      in.ic.asBus.v.valid.default = false
    }
    (souts ++ vouts).foreach { out =>
      out.v.valid.default = false
      out.ic.v.asBus.valid.default = false
    }
    (cins ++ couts).foreach { cio =>
      cio.v.default = false
      cio.ic.v.default = false
    }
  }
}

