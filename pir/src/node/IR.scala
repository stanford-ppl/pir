package pir.node

import pir._

import prism._
import prism.util._

trait IR extends prism.node.IR { 
  def name(n:String)(implicit design:Design):this.type = {
    import design.pirmeta._
    nameOf(this) = n
    this
  } 
  def name(n:Option[String])(implicit design:Design):this.type = { 
    n.foreach{n => this.name(n)}; 
    this 
  }
  def name(implicit design:Design) = {
    import design.pirmeta._
    nameOf.get(this)
  }

  def ctrl(ctrler:Any)(implicit design:Design):this.type = {
    import design.pirmeta._
    (this, ctrler) match {
      case (self:Controller, ctrler:Controller) => self.setParent(ctrler)
      case (self:Controller, top:Top) => self.setParent(top.topController)
      case (_, top:Top) =>
      case (self:Memory, _) =>
      case (self:PIRNode, ctrler:Controller) => ctrlOf(self) = ctrler 
    }
    this
  }

  def qtype(implicit design:Design) = name.map { name => s"${className}${id}[$name]" }.getOrElse(this.toString)
}

