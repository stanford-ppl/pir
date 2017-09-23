package pir.node

import pir._

import pirc._

class Sequential(implicit design:PIR) extends OuterController {
  override val typeStr = "SeqCU"
}
object Sequential {
  /* Sugar API */
  def apply[P](parent:P) (block: Sequential => Any)
                 (implicit design:PIR):Sequential =
    new Sequential().parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:Sequential => Any)
                 (implicit design:PIR):Sequential =
    new Sequential().name(name).parent(parent).updateBlock(block)
}

