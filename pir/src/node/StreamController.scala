package pir.node

import pir._
import pirc._

class StreamController(implicit design:PIR) extends OuterController {
  override val typeStr = "StreamCtrler"
}
object StreamController {
  def apply [P](parent:P) (block: StreamController => Any)
                 (implicit design:PIR):StreamController =
    new StreamController().parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:StreamController => Any)
                 (implicit design:PIR):StreamController =
    new StreamController().name(name).parent(parent).updateBlock(block)
}


