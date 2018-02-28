package pir.codegen
import pir._

import prism._
import prism.codegen._

class PIRPrinter(val fileName:String)(implicit compiler:PIR) extends PIRCodegen with IRPrinter {

  lazy val metadata = pirmeta
  override def quote(n:Any) = qtype(n) 

}

