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

class StreamPipeline(name:Option[String])(implicit design:Design) extends InnerController(name) { self =>
  override val typeStr = "StreamPipe"
  import pirmeta._

  private var _parent:StreamController = _
  override def parent:StreamController = _parent
  override def parent[T](parent:T):this.type = {
    parent match {
      case p:StreamController => _parent = p; p.addChildren(this)
      case p:String => super.parent(parent)
      case p => throw PIRException(s"StreamPipeline's parent must be StreamController $this.parent=$p")
    }
    this
  }
  override def removeParent:Unit = _parent = null
}
object StreamPipeline {
  def apply[P](name: Option[String], parent:P) (block: StreamPipeline => Any)
                (implicit design: Design):StreamPipeline = {
    new StreamPipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: StreamPipeline => Any)
                 (implicit design:Design):StreamPipeline =
    StreamPipeline(None, parent)(block)
  def apply[P](name:String, parent:P) (block:StreamPipeline => Any)
                (implicit design:Design):StreamPipeline =
    StreamPipeline(Some(name), parent)(block)
}
