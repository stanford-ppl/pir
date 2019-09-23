package spade
package codegen

import prism.graph._
import prism.codegen._

import param._

class ParamIRPrinter(val fileName:String, topFunc: => Parameter)(implicit compiler:Compiler) extends IRPrinter with ParamTraversal {
  def top:N = topFunc
  override def visitFunc(n:N):Stream[N] = visitGlobalIn(n)
}
class ParamHtmlIRPrinter(val fileName:String, topFunc: => Parameter)(implicit compiler:Compiler) extends HtmlIRPrinter with ParamTraversal {
  def top:N = topFunc
  override def visitFunc(n:N):Stream[N] = visitGlobalIn(n)
}
