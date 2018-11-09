package pir
package pass

import pir.node._
import prism.graph._

class ValidConstantPropogation(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with Transformer {

  override def visitNode(n:N) = {
    n match {
      case n:Counter => propogate(n)
      case n => 
        val ens = n.localIns.filter { in => 
          in.asInstanceOf[Field[_]].name == "en"
        }
        ens.foreach { propogateEn }
    }
    super.visitNode(n)
  }

  def propogate(counter:Counter) = dbgblk(s"propogate($counter)"){
    val min = counter.min.T
    val step = counter.step.T
    val max = counter.max.T
    val par = counter.par
    val range = (min, step, max) match {
      case (Const(min:Int), Const(step:Int), Const(max:Int)) =>
        var bound = ((max - min) /! step) % par
        if (bound == 0) {
          if (counter.isInner) bound = 1 else bound = par
        }
        dbg(s"Constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until $bound)")
        (0 until bound)
      case _ =>
        dbg(s"None constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until 1)")
        (0 until 1)
    }
    range.foreach { i =>
      val edge = counter.valids(i).out
      edge.connected.foreach { c =>
        dbg(s"disconnect valid($i) from ${c.src}.$c")
        edge.disconnectFrom(c)
      }
    }
  }

  def propogateEn(en:Input) = {
    val constEns = en.neighbors.filter { case Const(true) => true; case _ => false }
    constEns.foreach { constEn => disconnect(en, constEn) }
  }

}
