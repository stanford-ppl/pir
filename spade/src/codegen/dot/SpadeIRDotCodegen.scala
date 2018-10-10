package spade
package codegen

import spade.node._ 

class SpadeIRDotCodegen[M<:SpadeNode:ClassTag:TypeTag](val fileName:String)(implicit compiler:Spade) extends SpadeCodegen with IRDotCodegen {

  import spademeta._

  def getLabel(n:Any) = quote(n)

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  //override def color(attr:DotAttr, n:Any) = n match {
    //case n => super.color(attr, n)
  //}

  override def emitNode(n:N) = {
    n match {
      case n:OnChipMem => emitSingleNode(n)
      case n:Counter => emitSingleNode(n)
      case n:FuncUnit => emitSingleNode(n)
      case n:PipeReg => emitSingleNode(n)
      case n:Pin[_] => emitSingleNode(n)
      case n:BroadCast[_,_] => emitSingleNode(n)
      case n => super.emitNode(n) 
    }
  }

}
