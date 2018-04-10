package pir.mapper

import prism.mapper._
import prism.collection.immutable._

trait MappingLogger {

  def log[T](pred:Boolean, x:T)(implicit logger:Logging):T = {
    if (pred) logging(x) else x
    x
  }

  def log[T](x:T)(implicit logger:Logging):T = log(true, x)

  def logging(x:Any)(implicit logger:Logging):Unit = {
    x match {
      case x:PIRMap => logging(x)
      case x:FactorGraphLike[_,_,_] => logging(x)
      case x:FIMap => logging(x)
      case x:ConfigMap => logging(x)
      case Right(x) => logging(x)
      case Left(x) => logging(x)
      case x:InvalidFactorGraph[_,_] => logging(x)
      case x:BindingTrace[_] => logging(x)
      case x => logger.dbg(s"$x")
    }
  }

  def logging(x:InvalidFactorGraph[_,_])(implicit logger:Logging):Unit = {
    logger.dbg(s"$x")
    logging(x.fg)
  }

  def logging(x:BindingTrace[_])(implicit logger:Logging):Unit = {
    logger.dbgblk(s"$x") {
      x.traces.foreach { trace =>
        logging(trace)
      }
    }
    logger.dbg(s"$x.last=${x.last}")
  }

  def logging(x:PIRMap)(implicit logger:Logging):Unit = {
    logger.dbgblk(s"pmap") {
      x.productIterator.foreach { field => logging(field) }
    }
  }

  def logging(x:FactorGraphLike[_,_,_])(implicit logger:Logging):Unit = {
    logger.dbgblk(x.getClass.getSimpleName) {
      logger.dbgblk(s"freeMap") {
        x.freeMap.foreach { case (k, vs) =>
          logger.dbg(s"${logger.quote(k)} -> ${logger.quote(vs)}")
        }
      }
      logger.dbgblk(s"usedMap") {
        x.usedMap.foreach { case (k, vv) =>
          logger.dbg(s"${logger.quote(k)} -> ${logger.quote(vv)}")
        }
      }
    }
  }

  def logging(x:MapLike[_,_,_])(implicit logger:Logging):Unit = {
    logger.dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        logger.dbg(s"${logger.quote(k)} -> ${logger.quote(v)}")
      }
    }
  }

}
