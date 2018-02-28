package prism.node

import prism._
import prism.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait ProductAtom[N<:Node[N]] extends ProductNode[N] with Atom[N] { self:N with ProductAtom[N] =>

  def out:Output[N]
  //Make sure lazy val is evaluated so in swapOutput the IO patterns are the same
  //Has to be lazy to avoid null pointer exception during construction in subclasses
  //
  def newIn:Input[N]

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
