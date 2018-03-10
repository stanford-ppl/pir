package pir.pass

import pir._
import pir.node._

import prism._
import prism.enums._
import scala.collection.mutable

class ConstantPropogation(implicit compiler:PIR) extends BFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = true

  override def shouldRun = true

  override def visitNode(n:N):T = {
    n match {
      case Def(n, _) if boundOf.isDefinedAt(n) =>
      case Def(n, LocalLoad(mem::Nil, None)) if boundOf.isDefinedAt(mem) =>
        boundOf.mirror(n, mem)
      case Def(n, LocalStore(mems, None, data)) if boundOf.isDefinedAt(data) =>
        mems.foreach { mem => boundOf.mirror(data, mem) }
      case Def(n, Const(value)) =>
        boundOf(n) = value
      case Def(n, High()) =>
        boundOf(n) = true
      case Def(n, Low()) =>
        boundOf(n) = false
      case Def(n, OpDef(op, inputs)) =>
        eval(op, inputs.map { in => boundOf.get(in) }:_*).asInstanceOf[Option[Any]].foreach { value =>
          boundOf(n) = value
        }
      case _ =>
    }
    dbg(s"visitNode ${qdef(n)} bound=${boundOf.get(n)}")
    super.visitNode(n)
  }

  override def runPass =  {
    traverseNode(compiler.top)
  }

}
