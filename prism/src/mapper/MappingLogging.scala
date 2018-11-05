package prism
package mapper

trait MappingLogging extends Logging {
  def dbgblk[T](s:String, buffer:Boolean=false, flush:Boolean=false)(block: => T):T = super.dbgblk(s) {
    if (buffer) {
      logger.openBuffer(None)
    }
    val res = block
    if (buffer) {
      res match {
        case Left(failure) => logger.closeBufferAndWrite
        case _ => logger.closeBuffer
      }
    }
    if (flush) logger.closeAllBuffersAndWrite
    res
  }

}

