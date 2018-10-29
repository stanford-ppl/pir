package pir
package codegen

import pir.node._
import prism.codegen._

class PIRIRPrinter(val fileName:String)(implicit compiler:PIR) extends PIRTraversal with IRPrinter {
}
class PIRHtmlIRPrinter(val fileName:String)(implicit compiler:PIR) extends PIRTraversal with HtmlIRPrinter {
  override def quote(n:Any) = n match {
    case n:ControlTree =>
      elem("a", s"$n", "href"->s"ctrl.html#$n")
    case n => super.quote(n)
  }
}
class ControlTreeHtmlIRPrinter(val fileName:String)(implicit compiler:PIR) extends ControlTreeTraversal with HtmlIRPrinter {
}
