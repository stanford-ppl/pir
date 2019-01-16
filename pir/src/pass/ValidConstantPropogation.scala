package pir
package pass

import pir.node._
import prism.graph._

class ValidConstantPropogation(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with Transformer with MemoryAnalyzer {

  override def visitNode(n:N) = {
    n match {
      case n:Counter => propogate(n)
      case n => 
        val ens = n.localIns.filter { in => 
          in.as[Field[_]].name == "en"
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
    dbg(s"isInner=${counter.isInner}")
    val range = (min, step, max) match {
      case (Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) if config.forceAlign =>
        val bound = if (counter.isInner) 1 else par
        (0 until bound)
      case (Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) =>
        var bound = ((max - min) /! step) % par
        if (bound == 0) {
          if (counter.isInner) bound = 1 else bound = par
        }
        if (bound > counter.valids.size) {
          bound = 0 // vectorized valid. Cannot eliminate
        }
        dbg(s"Constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until $bound)")
        (0 until bound)
      case _ =>
        dbg(s"None constant loop bounds min=$min, step=$step, max=$max, par=$par (0 until 1)")
        (0 until 1)
    }
    if (range.nonEmpty) {
      val const = within(pirTop, counter.ctrl.get) { allocConst(true) }
      range.foreach { i =>
        val out = counter.valids(i).out
        swapOutput(out, const.out)
      }
    }
  }

  def propogateEn(en:Input) = {
    val constEns = en.neighbors.filter { case Const(true) => true; case _ => false }
    constEns.foreach { constEn => disconnect(en, constEn) }
  }

}
