package pir.node

import pir._

import pirc._

/* Memory Pipeline */
class MemoryPipeline(implicit design: PIR) extends InnerController {
  import pirmeta._

  override val typeStr = "MemPipe"
  override lazy val ctrlBox:MemCtrlBox = MemCtrlBox()

  override def stages:List[Stage] = wtAddrStages ++ rdAddrStages

  lazy val sram:SRAM = {
    //val rms = mems.collect{ case m:SemiFIFO => m; case m:SRAM => m}
    val rms = mems.collect{ case m:SRAM => m}
    assert(rms.size==1)
    rms.head
  }
  lazy val dataOut = {
    val dout = sram.readPort.to.map{_.src}.collect{ case vo:VecOut => vo}.head
    dout.in.connect(sram.load)
    dout
  }
  def data = dataOut.vector
}
object MemoryPipeline {
  /* Sugar API */
  def apply[P](parent:P) (block: MemoryPipeline => Any) (implicit design:PIR):MemoryPipeline =
    new MemoryPipeline().parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:MemoryPipeline => Any) (implicit design:PIR):MemoryPipeline =
    new MemoryPipeline().name(name).parent(parent).updateBlock(block)
  def apply[P](name:String) (block:MemoryPipeline => Any) (implicit design:PIR):MemoryPipeline =
    new MemoryPipeline().name(name).updateBlock(block)
}



