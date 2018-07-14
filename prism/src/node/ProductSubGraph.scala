package prism
package node

trait ProductSubGraph[N<:Node[N]] extends ProductNode[N] with SubGraph[N] { self:N with ProductSubGraph[N] =>
  override def connectFields(x:Any, i:Int):Any = {
    implicit val ev = nct
    x match {
      case x:N => 
        x.unsetParent
        this.addChild(x); x
      case x => super.connectFields(x, i)
    }
  }
}

