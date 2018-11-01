package prism
package codegen

import scala.collection.mutable

trait CppCodegen extends Codegen {

  implicit class UtilOp(x:Any) {
    def cast(tp:String) = s"($tp) $x"
    def & = s"&${x}"
  }

}

