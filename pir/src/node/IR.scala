package pir
package node

trait IR extends prism.node.IR { 
  def name(n:String)(implicit design:PIRDesign):this.type = {
    import design.pirmeta._
    nameOf(this) = n
    this
  } 
  def name(n:Option[String])(implicit design:PIRDesign):this.type = { 
    n.foreach{n => this.name(n)}; 
    this 
  }
  def name(implicit design:PIRDesign) = {
    import design.pirmeta._
    nameOf.get(this)
  }

  def srcCtx(n:String)(implicit design:PIRDesign):this.type = {
    import design.pirmeta._
    srcCtxOf(this) = n
    this
  } 

  def ctrl(ctrler:Any)(implicit design:PIRDesign):this.type = {
    import design.pirmeta._
    (this, ctrler) match {
      case (self:Controller, ctrler:Controller) => self.setParent(ctrler)
      case (self:Memory, _) =>
      case (self:PIRNode, ctrler:Controller) => ctrlOf(self) = ctrler 
      case _ =>
    }
    this
  }

  def qtype(implicit design:PIRDesign) = name.map { name => s"${className}${id}[$name]" }.getOrElse(this.toString)
}

