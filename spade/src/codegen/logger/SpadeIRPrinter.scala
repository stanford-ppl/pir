package spade
package codegen

class SpadeIRPrinter(val fileName:String)(implicit compiler:Spade) extends SpadeCodegen with prism.codegen.IRPrinter {

  lazy val metadata = Some(spademeta)

  def qdef(n:Any) = quote(n)
}
