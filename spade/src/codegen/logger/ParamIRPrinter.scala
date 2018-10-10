package spade
package codegen

import param._

class ParamIRPrinter(val fileName:String)(implicit compiler:Spade) extends ParamCodegen with IRPrinter {

  val forward = false
  lazy val metadata = None 

  def qdef(n:Any) = n match {
    case n:Parameter => s"${n.productName}"
    case n => s"$n"
  }

}
