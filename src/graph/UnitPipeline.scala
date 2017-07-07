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

/* Inner Unit Pipe */
class UnitPipeline(override val name: Option[String])(implicit design: Design) extends Pipeline(name) { self =>
  override val typeStr = "UnitPipe"
}
object UnitPipeline {
  def apply[P](name: Option[String], parent:P)(implicit design: Design):UnitPipeline =
    new UnitPipeline(name).parent(parent)
  /* Sugar API */
  def apply[P](parent:P) (block: UnitPipeline => Any) (implicit design:Design):UnitPipeline =
    UnitPipeline(None, parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:UnitPipeline => Any) (implicit design:Design):UnitPipeline =
    UnitPipeline(Some(name), parent).updateBlock(block)
}

