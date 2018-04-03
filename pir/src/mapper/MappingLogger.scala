package pir.mapper

import pir._

trait MappingLogger {

  def logging[T](x:T)(implicit pass:PIRPass):T = {
    x match {
      case x:PIRMap => logging(x)
      case x:CUMap => logging(x)
      case x:FIMap => logging(x)
      case x:ConfigMap => logging(x)
      case x:Either[_,_] => x.map(x => logging(x))
      case x => pass.dbg(s"$x")
    }
    x
  }

  def logging(x:PIRMap)(implicit pass:PIRPass):PIRMap = {
    pass.dbgblk(s"pmap") {
      x.productIterator.foreach { field => logging(field) }
    }
    x
  }

  def logging(x:CUMap)(implicit pass:PIRPass):CUMap = {
    import pass._
    dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, vs) =>
        dbg(s"${quote(k)} -> ${vs.map(quote)}")
      }
    }
    x
  }

  def logging(x:FIMap)(implicit pass:PIRPass):FIMap = {
    import pass._
    dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        dbg(s"${quote(k)} -> ${quote(v)}")
      }
    }
    x
  }

  def logging(x:ConfigMap)(implicit pass:PIRPass):ConfigMap = {
    import pass._
    dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        dbg(s"${quote(k)} -> ${quote(v)}")
      }
    }
    x
  }

}
