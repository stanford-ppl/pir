package pir
package node

abstract class PIRNode(implicit override val design:PIRDesign) 
  extends prism.node.ProductNode[PIRNode] with IR with PIRCollector with pass.BuildEnvironment { self =>

  def designP = design

  lazy val pirmeta = design.pirmeta

  type N = PIRNode
  type P = Container
  type A = Primitive

  override def ins:List[Input]
  override def outs:List[Output]
  override def ios:List[IO] = ins ++ outs

  if (design.staging) {
    setCurrentParent(this)
    setCurrentCtrl(this)
  }

  def qdef = s"${name.getOrElse(toString)} = ${productName}"

}
