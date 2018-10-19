package prism
package codegen

import prism.graph._

trait PythonCodegen extends Codegen {
  def emitComment(s:Any) = {
    emitln(s"# $s")
  }
}

