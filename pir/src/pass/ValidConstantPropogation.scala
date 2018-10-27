package pir
package pass

import pir.node._
import prism.graph._

class ValidConstantPropogation(implicit compiler:PIR) extends PIRPass {

  override def runPass = {
    val ctrs = pirTop.collectDown[Counter]()
    ctrs.foreach(propogate)
  }

  def propogate(counter:Counter) = dbgblk(s"propogate($counter)"){
    val min = counter.min.T
    val step = counter.step.T
    val max = counter.max.T
    val par = counter.par
    val range = (min, step, max) match {
      case (Const(min:Int), Const(step:Int), Const(max:Int)) =>
        dbg(s"Constant loop bounds min=$min, step=$step, max=$max, par=$par")
        val bound = ((max - min) /! step) % par
        (0 until bound)
      case _ =>
        dbg(s"None constant loop bounds min=$min, step=$step, max=$max, par=$par")
        (0 until 1)
    }
    range.foreach { i =>
      val edge = counter.valids(i)
      edge.connected.foreach { c =>
        dbg(s"disconnect valid($i) from ${c.src}.$c")
        edge.disconnectFrom(c)
      }
    }

  }

}
