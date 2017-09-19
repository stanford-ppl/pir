package pir.node

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.PIR
import pir.node._
import pirc.enums._
import pirc.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef
import pir.util._

class Sequential(name:Option[String])(implicit design:PIR) extends OuterController(name) {
  override val typeStr = "SeqCU"
}
object Sequential {
  def apply[P](name: Option[String], parent:P) (block: Sequential => Any)
                (implicit design: PIR):Sequential = {
    new Sequential(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply[P](parent:P) (block: Sequential => Any)
                 (implicit design:PIR):Sequential =
    Sequential(None, parent)(block)
  def apply[P](name:String, parent:P) (block:Sequential => Any)
                 (implicit design:PIR):Sequential =
    Sequential(Some(name), parent)(block)
}

