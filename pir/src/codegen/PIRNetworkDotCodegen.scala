package pir.codegen

class PIRNetworkDotCodegen[B<:PinType:ClassTag](fileName:String)(implicit compiler:PIR) 
extends spade.codegen.NetworkDotCodegen[B](fileName)(implicitly[ClassTag[B]], compiler.arch) {
  override val dirName = compiler.outDir
}
