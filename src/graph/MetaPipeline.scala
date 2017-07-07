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

class MetaPipeline(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "MetaPipeCU"
}
object MetaPipeline {
  def apply[P](name: Option[String], parent:P) (block: MetaPipeline => Any)
                (implicit design: Design):MetaPipeline = {
    new MetaPipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: MetaPipeline => Any)
                 (implicit design:Design):MetaPipeline =
    MetaPipeline(None, parent)(block)
  def apply[P](name:String, parent:P) (block:MetaPipeline => Any)
                (implicit design:Design):MetaPipeline =
    MetaPipeline(Some(name), parent)(block)
}
