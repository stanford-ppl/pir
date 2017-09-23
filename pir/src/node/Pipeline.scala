package pir.node

import pir._
import pirc._

class Pipeline(implicit design:PIR) extends InnerController { self =>
  override val typeStr = "PipeCU"

  override lazy val ctrlBox:InnerCtrlBox = InnerCtrlBox()
}
object Pipeline {
  def apply [P](parent:P) (block: Pipeline => Any) (implicit design:PIR):Pipeline =
    new Pipeline().parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:Pipeline => Any) (implicit design:PIR):Pipeline =
    new Pipeline().name(name).parent(parent).updateBlock(block)
}
