package pir
package codegen

import prism.codegen._

class PIRIRPrinter(val fileName:String)(implicit compiler:PIR) extends PIRTraversal with IRPrinter {
  //override def quote(n:Any) = qtype(n) 
}

