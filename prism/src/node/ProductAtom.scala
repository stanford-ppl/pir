package prism
package node

trait ProductAtom[N<:Node[N]] extends ProductNode[N] with Atom[N] { self:N with ProductAtom[N] =>

  def newIn:Input[N]
  def newOut:Output[N]
  def out = outs.headOption.getOrElse(newOut)

  def connect(io:Edge[N]):Edge[N] = {
    io match {
      case io:Input[N] => out.connect(io)
      case io:Output[N] => newIn.connect(io)
    }
  }

  def isInputField(field:Any, fieldIdx:Int) = true 
  final def isOutputField(field:Any, fieldIdx:Int) = !isInputField(field, fieldIdx)

  override def connectFields(x:Any, i:Int):Any = {
    x match {
      case x:ProductAtom[N] if isInputField(x, i) => this.connect(x.out)
      case x:ProductAtom[N] if isOutputField(x, i) => this.out.connect(x.newIn)
      case x:Iterable[_] if isOutputField(x,i) => 
        super.connectFields(x,i).asInstanceOf[Iterable[Output[N]]].toSet.head
      case x => super.connectFields(x, i)
    }
  }

  override def evaluateFields(f:Any, x:Any):Any = (f, x) match {
    case (f:Iterable[_], x:Output[N]) => x.connected.map{_.src}
    case (f, x:Edge[N]) => x.singleConnected.map{_.src}.getOrElse(null)
    case (f,x) => super.evaluateFields(f,x)
  }
}

object Def {
  def unapply[T:ClassTag](x:T):Option[(T, T)] = {
    x match {
      case x:T => 
        x match {
          case n:ProductAtom[_] => Some((x, n.newInstance(n.values, staging=false)))
          case _ => None
        }
      case _ => None
    }
  }
}
