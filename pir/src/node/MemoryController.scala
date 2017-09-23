package pir.node


import pir._

import pirc._
import pirc.enums._

import scala.reflect.runtime.universe._

class MemoryController(val mctpe:MCType, val offchip:OffChip)(implicit design: PIR) extends InnerController() { 
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
    = new MemoryController(mctpe, offchip).name(name).parent(parent).updateBlock(block)
}
