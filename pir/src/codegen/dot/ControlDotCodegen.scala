package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import pirc._
import pirc.util._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable


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
