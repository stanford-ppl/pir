package prism
package codegen

import prism.node._

trait PythonCodegen extends Codegen {
  def emitComment(s:Any) = {
    emitln(s"# $s")
  }
}

