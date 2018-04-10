package pir.mapper

import prism.mapper._
import prism.collection.immutable._

trait MappingLogger {

  def log[T](pred:Boolean, x:T)(implicit pass:PIRPass):T = {
    if (pred) logging(x) else x
    x
  }

  def log[T](x:T)(implicit pass:PIRPass):T = log(true, x)

  def logging(x:Any)(implicit pass:PIRPass):Unit = {
    x match {
      case x:PIRMap => logging(x)
      case x:FactorGraphLike[_,_,_] => logging(x)
      case x:FIMap => logging(x)
      case x:ConfigMap => logging(x)
      case Right(x) => logging(x)
      case Left(x) => logging(x)
      case x:InvalidFactorGraph[_,_] => logging(x)
      case x:BindingTrace[_] => logging(x)
      case x => pass.dbg(s"$x")
    }
  }

  def logging(x:InvalidFactorGraph[_,_])(implicit pass:PIRPass):Unit = {
    pass.dbg(s"$x")
    logging(x.fg)
  }

  def logging(x:BindingTrace[_])(implicit pass:PIRPass):Unit = {
    pass.dbgblk(s"$x") {
      x.traces.foreach { trace =>
        logging(trace)
      }
    }
  }

  def logging(x:PIRMap)(implicit pass:PIRPass):Unit = {
    pass.dbgblk(s"pmap") {
      x.productIterator.foreach { field => logging(field) }
    }
  }

  def logging(x:FactorGraphLike[_,_,_])(implicit pass:PIRPass):Unit = {
    pass.dbgblk(x.getClass.getSimpleName) {
      pass.dbgblk(s"freeMap") {
        x.freeMap.foreach { case (k, vs) =>
          pass.dbg(s"${pass.quote(k)} -> ${pass.quote(vs)}")
        }
      }
      pass.dbgblk(s"usedMap") {
        x.usedMap.foreach { case (k, vv) =>
          pass.dbg(s"${pass.quote(k)} -> ${pass.quote(vv)}")
        }
      }
    }
  }

  def logging(x:MapLike[_,_,_])(implicit pass:PIRPass):Unit = {
    pass.dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        pass.dbg(s"${pass.quote(k)} -> ${pass.quote(v)}")
      }
    }
  }

}
