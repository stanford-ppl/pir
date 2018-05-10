package pir

import spade.node._

package object node extends PIRNodeUtil {
  //type PIR = pir.PIR
  //type PIRPass = pir.pass.PIRPass
  //type PIRMetadata = pir.util.PIRMetadata
  //type PIRApp = pir.PIRApp

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

  def innerCtrlOf(container:Container) = {
    implicit val design = container.design.asInstanceOf[PIRDesign]
    import design.pirmeta._
    ctrlsOf(container).maxBy { _.ancestors.size }
  }

}
