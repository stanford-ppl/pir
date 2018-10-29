package pir
package codegen

import pir.node._
import prism.codegen._

class PIRIRPrinter(val fileName:String)(implicit compiler:PIR) extends PIRTraversal with IRPrinter {
  override def qdef(n:N) = s"${quote(n)}(${n.to[PIRNode with Product].productIterator.map(quote).mkString(",")})"
}
class PIRHtmlIRPrinter(fileName:String)(implicit compiler:PIR) extends PIRIRPrinter(fileName) with HtmlIRPrinter {
  override def quote(n:Any) = n match {
    case n:ControlTree =>
      elem("a", s"$n", "href"->s"ctrl.html#$n")
    case n => super.quote(n)
  }
}
class ControlTreeHtmlIRPrinter(val fileName:String)(implicit compiler:PIR) extends ControlTreeTraversal with HtmlIRPrinter {
  override def qdef(n:N) = s"${quote(n)}(${n.to[ControlTree].productIterator.map(quote).mkString(",")})"
}
