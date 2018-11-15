package spade
package codegen

import prism.graph._
import prism.codegen._

import param._

class ParamIRPrinter(val fileName:String, topFunc: => N)(implicit compiler:Compiler) extends IRPrinter {
  def top:N = topFunc
  override def visitFunc(n:N):List[N] = visitGlobalIn(n)
}
class ParamHtmlIRPrinter(val fileName:String, topFunc: => N)(implicit compiler:Compiler) extends HtmlIRPrinter {
  def top:N = topFunc
  override def visitFunc(n:N):List[N] = visitGlobalIn(n)
}
