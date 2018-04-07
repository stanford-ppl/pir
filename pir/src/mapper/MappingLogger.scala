package pir.mapper

trait MappingLogger {

  def logging[T](x:T)(implicit pass:PIRPass):T = {
    x match {
      case x:PIRMap => logging(x)
      case x:CUMap => logging(x)
      case x:FIMap => logging(x)
      case x:ConfigMap => logging(x)
      case Right(x) => logging(x)
      case Left(f@InvalidFactorGraph(fg, k)) => 
        pass.dbg(s"$f")
        logging(fg)
      case Left(x) => logging(x)
      case x => pass.dbg(s"$x")
    }
    x
  }
  def logging[T](pred:Boolean, x:T)(implicit pass:PIRPass):T = {
    if (pred) logging(x) else x
  }

  def logging(x:PIRMap)(implicit pass:PIRPass):PIRMap = {
    pass.dbgblk(s"pmap") {
      x.productIterator.foreach { field => logging(field) }
    }
    x
  }

  def logging(x:CUMap)(implicit pass:PIRPass):CUMap = {
    pass.dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, vs) =>
        pass.dbg(s"${pass.quote(k)} -> ${vs.map(pass.quote)}")
      }
    }
    x
  }

  def logging(x:FIMap)(implicit pass:PIRPass):FIMap = {
    pass.dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        pass.dbg(s"${pass.quote(k)} -> ${pass.quote(v)}")
      }
    }
    x
  }

  def logging(x:ConfigMap)(implicit pass:PIRPass):ConfigMap = {
    pass.dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        pass.dbg(s"${pass.quote(k)} -> ${pass.quote(v)}")
      }
    }
    x
  }

}
