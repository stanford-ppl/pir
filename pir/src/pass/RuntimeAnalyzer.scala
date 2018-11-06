package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

trait RuntimeAnalyzer { self:PIRPass =>

  implicit class PIRNodeRuntimeOp(n:PIRNode) {
    def getVec:Int = n.getMeta[Int]("vec").getOrElseUpdate(compVec(n))
    def getCtrl:ControlTree = n.ctrl.get
  }
  def compVec(n:PIRNode):Int = dbgblk(s"compVec($n)"){
    n match {
      case n:Const => 1
      case n:CounterIter if n.i.nonEmpty => 1
      case n:CounterValid if n.i.nonEmpty => 1
      case n:RegAccumOp => 1
      case n:LoopController if n.ctrl.get.children.isEmpty => n.cchain.T.last.getVec
      case n:Controller => 1
      case n:ControllerValid => 1
      case n:ControllerDone => 1
      case n:Counter => n.par
      case n:BufferRead => assertOne(n.banks.get, s"$n.banks")
      case n:BufferWrite => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:BanckedAccess =>
        val bank = n.bank.T
        val offset = n.offset.T
        (bank :+ offset).map { _.getVec }.max
      case n:Access =>
        val mem = n.mem.T
        assertOne(mem.banks.get, s"$n's mem $mem bank dimension")
      case n:PIRNode => 
        val ctrler = assertOne(
          n.ctx.get.collectDown[Controller]().filter { _.ctrl.get == n.ctrl.get }, 
          s"$n.ctrler"
        )
        ctrler.getVec
    }
  }
}

