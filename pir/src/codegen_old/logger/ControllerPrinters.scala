package pir
package codegen

import pir.pass._
import pir.node._

class ControllerPrinter(val fileName:String)(implicit design:PIR) extends PIRPass 
  with ControllerTopDownTopologicalTraversal with prism.traversal.DFSTopDownTopologicalTraversal with Codegen {

  val forward=true

  override def emitNode(n:N) = {
    emitBlock(qtype(n)) { 
      emitln(s"style=${n.style}")
      emitln(s"level=${n.level}")
      emitln(s"inMems=${inMemsOf(n)}")
      emitln(s"outMems=${outMemsOf(n)}")
      super.visitNode(n)
    }
  }

}
