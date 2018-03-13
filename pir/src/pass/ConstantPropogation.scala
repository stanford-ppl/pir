package pir.pass

import pir._
import pir.node._

import prism._
import prism.enums._
import prism.util._
import scala.collection.mutable

trait ConstantPropogator {
  def constOf(n:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):Option[Any] = dbgblk(logger, s"constOf($n)") {
    import pass.pirmeta._
    implicit val design = pass.design
    boundOf.get(n).orElse {
      val bound = n match {
        case Def(n, Const(value)) => Some(value)
        case Def(n, High()) => Some(true)
        case Def(n, Low()) => Some(false)
        case Def(n, LocalLoad(mem::Nil, None)) => constOf(mem, logger)
        case Def(n, LocalStore(mems, None, data)) => constOf(data, logger)
        case WithWriter(writer) => constOf(writer, logger)
        case Def(n, OpDef(op, inputs)) => eval(op, inputs.map { in => constOf(in, logger) }:_*).asInstanceOf[Option[Any]]
        case Def(n, GlobalInput(gout)) => constOf(gout, logger)
        case Def(n, GlobalOutput(data, valid)) => constOf(data, logger)
        case n => None
      }
      bound.foreach { boundOf(n) = _ }
      bound
    }
  }

  def getConstOf[T:ClassTag](n:PIRNode, logger:Option[Logging]=None)(implicit pass:PIRPass):T = {
    constOf(n).getOrElse{ throw PIRException(s"Could not constant propogate $n") } match {
      case c:T => c
      case c => throw PIRException(s"constOf($n) = $c but expect type ${implicitly[ClassTag[T]]}")
    }
  }
}
