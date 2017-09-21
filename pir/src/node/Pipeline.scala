package pir.node

import pir._
import pirc._

class Pipeline(name:Option[String])(implicit design:PIR) extends InnerController(name) { self =>
  override val typeStr = "PipeCU"

  override lazy val ctrlBox:InnerCtrlBox = InnerCtrlBox()
}
object Pipeline {
  def apply[P](name: Option[String], parent:P)(block: Pipeline => Any)(implicit design: PIR):Pipeline = {
    new Pipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: Pipeline => Any) (implicit design:PIR):Pipeline =
    apply(None, parent)(block)
  def apply[P](name:String, parent:P) (block:Pipeline => Any) (implicit design:PIR):Pipeline =
    apply(Some(name), parent)(block)
}
