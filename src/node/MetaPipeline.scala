package pir.node

import pir._

import pirc._

class MetaPipeline(name:Option[String])(implicit design:PIR) extends OuterController(name) {
  override val typeStr = "MetaPipeCU"
}
object MetaPipeline {
  def apply[P](name: Option[String], parent:P) (block: MetaPipeline => Any)
                (implicit design: PIR):MetaPipeline = {
    new MetaPipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: MetaPipeline => Any)
                 (implicit design:PIR):MetaPipeline =
    MetaPipeline(None, parent)(block)
  def apply[P](name:String, parent:P) (block:MetaPipeline => Any)
                (implicit design:PIR):MetaPipeline =
    MetaPipeline(Some(name), parent)(block)
}
