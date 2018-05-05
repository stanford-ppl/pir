package pir.pass

import pir.node._
import prism.enums._

trait ConstantPropogator {
  def getBoundOf(n:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):Option[Any] = dbgblk(logger, s"getBoundOf($n)") {
    import pass.pirmeta._
    implicit val design = pass.design
    boundOf.get(n).orElse {
      val bound = n match {
        case Def(n, Const(value)) => Some(value)
        case Def(n, High()) => Some(true)
        case Def(n, Low()) => Some(false)
        case Def(n, LocalLoad(mem::Nil, None)) => getBoundOf(mem, logger)
        case Def(n, LocalStore(mems, None, data)) => getBoundOf(data, logger)
        case WithWriter(writer) => getBoundOf(writer, logger)
        case Def(n, OpDef(op, inputs)) => eval(op, inputs.map { in => getBoundOf(in, logger) }).asInstanceOf[Option[Any]]
        case Def(n, GlobalInput(gout)) => getBoundOf(gout, logger)
        case Def(n, GlobalOutput(data, valid)) => getBoundOf(data, logger)
        case n => None
      }
      bound.foreach { boundOf(n) = _ }
      bound
    }
  }

  def getBoundAs[T:ClassTag](n:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):Option[T] = {
    getBoundOf(n, logger).map {
      case c:T => c
      case c => throw PIRException(s"getBoundOf($n) = $c but expect type ${implicitly[ClassTag[T]]}")
    }
  }

}
