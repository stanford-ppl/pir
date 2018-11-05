package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

trait RuntimeAnalyzer { self:PIRPass =>

  implicit class PIRNodeRuntimeOp(n:PIRNode) {
    def getVec:Int = n.vec.getOrElseUpdate(compVec(n))
    def getCtrl:ControlTree = n.ctrl.get
  }
  def compVec(n:PIRNode):Int = dbgblk(s"compVec($n)"){
    n match {
      case n:CounterIter if n.i.nonEmpty => 1
      case n:CounterValid if n.i.nonEmpty => 1
      case n:OpDef if n.op=="RegAccumFMA" => 1
      case n:LoopController => n.cchain.T.last.getVec
      case n:Controller => 1
      case n:Counter => n.par
      case n:BufferRead => assertOne(n.banks.get, s"$n.banks")
      case n:BufferWrite => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:PIRNode => 
        val ctrler = assertOne(
          n.ctx.get.collectDown[Controller]().filter { _.ctrl.get == n.ctrl.get }, 
          s"$n.ctrler"
        )
        ctrler.getVec
    }
  }
}

