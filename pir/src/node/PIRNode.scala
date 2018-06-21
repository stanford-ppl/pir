package pir
package node

abstract class PIRNode(implicit override val design:PIRDesign) extends prism.node.ProductNode[PIRNode] with IR with PIRCollector { self =>

  lazy val pirmeta = design.pirmeta

  type N = PIRNode
  type P = Container
  type A = Primitive

  override def ins:List[Input]
  override def outs:List[Output]
  override def ios:List[IO] = ins ++ outs

  this match {
    case self:Top =>
    case self if !design.staging =>
    case self => setParent(design.top)
  }

  override def setParent(p:P):this.type = {
    this.parent match {
      case Some(top:Top) if p != top => unsetParent
      case p =>
    }
    super.setParent(p)
  }

  def qdef = s"${name.getOrElse(toString)} = ${productName}"

}
