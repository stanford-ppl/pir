package pir
package mapper

import prism.mapper._
import prism.collection.immutable._

import spade.node._

trait MappingLogger extends Logging {

  def log[T](pred:Boolean, x:T):T = {
    if (pred) logging(x) else x
    x
  }

  def log[T](x:T):T = log(true, x)

  def logging(x:Any):Unit = {
    x match {
      case x:TopMap => logging(x)
      case x:FactorGraphLike[_,_,_] => logging(x)
      case x:prism.collection.MapLike[_,_] => logging(x)
      case Right(x) => logging(x)
      case Left(x) => logging(x)
      //case x:InvalidFactorGraph[_,_] => logging(x)
      case x:BindingTrace[_,_] => logging(x)
      case x => dbg(s"$x")
    }
  }

  def logging(x:BindingTrace[_,_]):Unit = {
    dbgblk(s"$x") {
      x.traces.foreach { trace =>
        logging(trace)
      }
      logging(x.mapping)
    }
  }

  def logging(x:TopMap):Unit = {
    dbgblk(s"tmap") {
      x.productIterator.foreach { field => logging(field) }
    }
  }

  def logging(x:FactorGraphLike[_,_,_]):Unit = {
    dbgblk(x.getClass.getSimpleName) {
      dbgblk(s"freeMap") {
        x.freeMap.foreach { case (k, vs) =>
          dbg(s"${dquote(k)} -> ${dquote(vs)}")
        }
      }
      dbgblk(s"usedMap") {
        x.usedMap.foreach { case (k, vv) =>
          dbg(s"${dquote(k)} -> ${dquote(vv)}")
        }
      }
    }
  }

  def logging(x:prism.collection.MapLike[_,_]):Unit = {
    dbgblk(x.getClass.getSimpleName) {
      x.foreach { case (k, v) =>
        dbg(s"${dquote(k)} -> ${dquote(v)}")
      }
    }
  }

  override def dquote(x:Any) = x match {
    case x:Set[_] if x.nonEmpty =>
      if (x.head.isInstanceOf[Routable]) {
        val params = x.flatMap { _.asInstanceOf[Routable].params }
        s"[${params.mkString(",")}] (${x.size})"
      } else super.dquote(x)
    case x:Routable =>
      s"$x(${x.params.get})"
    case _ => super.dquote(x)
  }

}
