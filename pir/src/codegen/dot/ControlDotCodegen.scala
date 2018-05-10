package pir
package codegen

import pir.node._

class ControlDotCodegen(fileName:String)(implicit design:PIR) extends PIRIRDotCodegen(fileName) {

  import pirmeta._

  override def emitNode(n:N) = {
    n match {
      case n:ControlNode => super.emitNode(n) 
      case n:GlobalInput => super.emitNode(n)
      case n:GlobalOutput => super.emitNode(n)
      case n:Memory => super.emitNode(n)
      case n:LocalAccess => super.emitNode(n)
      case n:Primitive => super.visitNode(n)
      case n => super.emitNode(n) 
    }
  }

}
