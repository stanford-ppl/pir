package pir
package pass

import pir.node._
import prism.graph._

class BufferLowering(implicit compiler:PIR) extends PIRPass {

  override def runPass = {
    pirTop.collectDown[Memory]()
      .toIterator
      .filterNot(_.isBuffer)
      .filterNot { mem =>
        val inAccess = mem.inAccess
        val outAccess = mem.outAccess
        inAccess.size != 1 && outAccess.size != 1 && (inAccess ++ outAccess).exists { _.isInstanceOf[BanckedAccess] }
      }
      .foreach { mem =>
        dbg(s"Lower $mem to InputBuffer")
      }
  }

}
