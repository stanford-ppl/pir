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

class MemoryController(name: Option[String], val mctpe:MCType, val offchip:OffChip)(implicit design: Design) extends StreamPipeline(name) { self =>
  override val typeStr = "MemoryController"
  import pirmeta._
  
  override lazy val ctrlBox:MCCtrlBox = MCCtrlBox()

  def getFifo(name:String) = fifos.filter { _.name == Some(name) }.head

  override def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    super.updateBlock(block)
    sfifos.foreach { sfifo =>
      CtrlInPort(this, s"$this.${sfifo.name.get}").connect(sfifo.readPort)
    }
    this
  }

  def len = fifos.filter { _.name==Some("size") }.head
}
object MemoryController {
  def apply[P](name:String, parent:P, mctpe:MCType, offchip:OffChip)(block: MemoryController => Any)
    (implicit design: Design): MemoryController 
    = new MemoryController(Some(name), mctpe, offchip).parent(parent).updateBlock(block)
}
