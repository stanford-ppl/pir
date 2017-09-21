package pir.node

import pir._
import pirc._

class StreamController(name:Option[String])(implicit design:PIR) extends OuterController(name) {
  override val typeStr = "StreamCtrler"
}
object StreamController {
  def apply[P](name: Option[String], parent:P) (block: StreamController => Any)
                (implicit design: PIR):StreamController = {
    new StreamController(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: StreamController => Any)
                 (implicit design:PIR):StreamController =
    StreamController(None, parent)(block)
  def apply[P](name:String, parent:P) (block:StreamController => Any)
                 (implicit design:PIR):StreamController =
    StreamController(Some(name), parent)(block)
}


