package pir
package pass

import pir.node._
import prism.graph._

class BufferLowering(implicit compiler:PIR) extends PIRPass with Transformer {

  override def runPass = {
    pirTop.collectDown[Memory]().foreach(lowerMem)
  }

  def lowerMem(mem:Memory):Unit = {
    val accesses = mem.accesses
    dbg(s"accesses=$accesses")
    val cannotToBuffer = accesses.exists {
      case mem:BanckedAccess => true
      case mem:MemRead => mem.en.T.nonEmpty
      case _ => false
    }
    if (cannotToBuffer) return
    dbg(s"Lower $mem to InputBuffer")
    mem.outAccess.foreach { access =>
      within(access.parent.get.to[PIRNode]) {
        val buffer = InputBuffer(mem.isFIFO)
        buffer.mirrorMetas(mem)
        swapConnection(access, mem.out, buffer.out)
        mem.inAccess.foreach { inAccess =>
          inAccess.mem(buffer)
        }
      }
    }
  }

}
