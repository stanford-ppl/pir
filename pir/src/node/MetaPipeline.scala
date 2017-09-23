package pir.node

import pir._

import pirc._

class MetaPipeline()(implicit design:PIR) extends OuterController() {
  override val typeStr = "MetaPipeCU"
}
object MetaPipeline {
  /* Sugar API */
  def apply [P](parent:P) (block: MetaPipeline => Any)
                 (implicit design:PIR):MetaPipeline =
    new MetaPipeline().parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:MetaPipeline => Any)
                (implicit design:PIR):MetaPipeline =
    new MetaPipeline().name(name).parent(parent).updateBlock(block)
}
