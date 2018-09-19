package pir
package codegen

import pir.node._

class PartitalDotCodegen(fileName:String, cus:Iterable[GlobalContainer])(implicit design:PIR) extends PIRIRDotCodegen(fileName) {

  import pirmeta._

  override def emitNode(n:N) = {
    n match {
      case cu:GlobalContainer if !cus.toList.contains(cu) =>
      case n => super.emitNode(n) 
    }
  }

}
