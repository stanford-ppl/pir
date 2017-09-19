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
