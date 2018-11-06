package prism
package codegen

import scala.collection.mutable

trait CppCodegen extends Codegen {

  implicit class UtilOp(x:Any) {
    def cast(tp:String) = s"($tp) $x"
    def & = s"&${x}"
    def * = s"*${x}"
  }

  def emitIfElse(cond:Any)(trueCase: => Unit)(falseCase: => Unit) = {
    emitBlock(s"if ($cond)") {
      trueCase
    }
    emitBlock(s"else") {
      falseCase
    }
  }

  def emitIf(cond:Any)(trueCase: => Unit) = {
    emitBlock(s"if ($cond)") {
      trueCase
    }
  }

}

