package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.util.enums._
import pir.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef
import pir.util._

class Sequential(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "SeqCU"
}
object Sequential {
  def apply[P](name: Option[String], parent:P) (block: Sequential => Any)
                (implicit design: Design):Sequential = {
    new Sequential(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply[P](parent:P) (block: Sequential => Any)
                 (implicit design:Design):Sequential =
    Sequential(None, parent)(block)
  def apply[P](name:String, parent:P) (block:Sequential => Any)
                 (implicit design:Design):Sequential =
    Sequential(Some(name), parent)(block)
}

