package pir.node

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.PIR
import pir.node._
import pir.util.enums._
import pir.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef
import pir.util._


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


