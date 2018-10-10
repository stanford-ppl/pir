package spade
package codegen

import spade.param._
import prism.collection.mutable._

class ParamScalaCodegen(val fileName:String)(implicit compiler:Spade) extends ParamCodegen with ScalaCodegen {

  val forward = true

  override def quote(n:Any):String = n match {
    case n:Parameter => s"x${n.id}"
    case n:Table[_,_,_] => s"table${n.hashCode}"
    case n:String => s""""$n""""
    case n => super.quote(n)
  }

  override def emitNode(n:N) = {
    n.values.foreach{ case n:Table[_,_,_] => emitTable(n); case _ => }
    emitCaseClassInst(n)
  }

  def emitTable(n:Table[_,_,_]) = {
    emitInst(s"val ${quote(n)}_map = Map") { ms =>
      n.map.foreach { case (k, v) =>
        emitln(s"${quote(k)} -> ${quote(v)}")
      }
    }("")
    emitln(s"val ${quote(n)} = new Table(${quote(n)}_map) with ChannelWidth")
  }

  override def initPass = {
    super.initPass
    emitln(s"package spade.params")
    emitln
  }

  override def runPass = {
    emitBlock(s"trait GeneratedParam") {
      emitln
      emitln
      super.runPass
    }
  }
}
