package pir
package pass

import pir.node._
import prism.graph._

class BufferLowering(implicit compiler:PIR) extends PIRPass {

  override def runPass = {
    pirTop.collectDown[Memory]().foreach(lowerMem)
  }

  def lowerMem(mem:Memory) = {
    val accesses = mem.accesses
    val cannotToBuffer = accesses.exists {
      case mem:BanckedAccess => true
      case mem:MemRead => mem.en.T.nonEmpty
      case _ => false
    }
    if (!cannotToBuffer) {
      dbg(s"Lower $mem to InputBuffer")
    }
  }

}
