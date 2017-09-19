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

class MemoryController(name: Option[String], val mctpe:MCType, val offchip:OffChip)(implicit design: PIR) extends InnerController(name) { 
  override val typeStr = "MemoryController"
  import pirmeta._
  
  override lazy val ctrlBox:MCCtrlBox = MCCtrlBox()

  def getFifo(name:String) = fifos.filter { _.name == Some(name) }.head

  override def updateBlock(block: this.type => Any)(implicit design: PIR):this.type = {
    super.updateBlock(block)
    sfifos.foreach { sfifo =>
      InPort(this, s"$this.${sfifo.name.get}").connect(sfifo.readPort)
    }
    this
  }

  def len = fifos.filter { _.name==Some("size") }.head
}
object MemoryController {
  def apply[P](name:String, parent:P, mctpe:MCType, offchip:OffChip)(block: MemoryController => Any)
    (implicit design: PIR): MemoryController 
    = new MemoryController(Some(name), mctpe, offchip).parent(parent).updateBlock(block)
}
