package prism
package codegen

import scala.collection.mutable

trait CppCodegen extends Codegen {

  def isPointer(x:Any) = false

  implicit class UtilOp(x:Any) {
    def cast(tp:String) = s"($tp) $x"
    def & = if (!isPointer(x)) s"&${x}" else s"$x"
    def * = if (isPointer(x)) s"*${x}" else s"$x"
    def field(f:String) = {
      if (isPointer(x)) s"$x->$f" else s"$x.$f"
    }
  }

  def emitIfElse(cond:Any)(trueCase: => Unit)(falseCase: => Unit) = {
    if (cond.toString == "true") trueCase
    else if (cond.toString == "false") falseCase
    else {
      emitBlock(s"if ($cond)") {
        trueCase
      }
      emitBlock(s"else") {
        falseCase
      }
    }
  }

  def emitIf(cond:Any)(trueCase: => Unit) = {
    if (cond.toString == "true") trueCase 
    else emitBlock(s"if ($cond)") {
      trueCase
    }
  }

  def declareClass(name:String)(block: => Unit) = {
    emitBlock(bs=Some(s"class $name"), b=None, es=Some(";")) {
      block
    }
  }

}

