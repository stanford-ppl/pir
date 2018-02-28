package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import prism._
import prism.util._

import scala.collection.mutable

class LocalIRDotCodegen(filename:String)(implicit compiler:PIR) extends PIRIRDotCodegen(filename) {
  override def emitNode(n:N) = {
    n match {
      case n:CUContainer if List("x5074", "x4725_d0_b0", "x5055").contains(n.name.get) => emitSubGraph(n)(traverse(n))
      case n:CUContainer => traverse(n)
      case n => super.emitNode(n)
    }
  }
}

