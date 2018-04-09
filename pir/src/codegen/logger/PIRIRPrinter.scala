package pir.codegen

class PIRPrinter(val fileName:String)(implicit compiler:PIR) extends PIRCodegen with IRPrinter {

  lazy val metadata = Some(pirmeta)
  override def quote(n:Any) = qtype(n) 

}

