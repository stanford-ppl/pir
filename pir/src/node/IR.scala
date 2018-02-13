package pir.node

import pir._

import pirc._
import pirc.util._

trait IR extends prism.node.IR { 
  def name(n:String)(implicit design:PIR):this.type = {
    design.newTop.metadata.nameOf(this) = n
    this
  } 
  def name(n:Option[String])(implicit design:PIR):this.type = { 
    n.foreach{n => this.name(n)}; 
    this 
  }
  def name(implicit design:PIR) = design.newTop.metadata.nameOf.get(this)

  def ctrl(ctrler:Any)(implicit design:PIR):this.type = {
    (this, ctrler) match {
      case (self:Controller, ctrler:Controller) => self.setParent(ctrler)
      case (self:Controller, top:Top) => self.setParent(top.topController)
      case (_, top:Top) =>
      case (self:Memory, _) =>
      case (self:PIRNode, ctrler:Controller) => design.newTop.metadata.ctrlOf(self) = ctrler 
    }
    this
  }

}

