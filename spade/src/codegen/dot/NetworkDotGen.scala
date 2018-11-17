package spade
package codegen

import spade.node._ 
import spade.param._ 
import prism.codegen._

class SpadeNetworkDotGen(val fileName:String)(implicit compiler:Spade) extends NetworkDotGen {
  lazy val top = compiler.spadeTop
}

trait NetworkDotGen extends IRDotCodegen {

  override def label(attr:DotAttr, n:N) = {
    var label = quote(n)
    attr.label(label)
  }

  override def color(attr:DotAttr, n:N) = n match {
    case Param(_:PCUParam) => attr.fillcolor("dodgerblue").style(filled)
    case Param(_:PMUParam) => attr.fillcolor("lightseagreen").style(filled)
    case Param(_:DramAGParam) => attr.fillcolor("palevioletred1").style(filled)
    case Param(_:MCParam) => attr.fillcolor("forestgreen").style(filled)
    case _:Connector => attr.fillcolor("indianred1").style(filled)
    case Param(_:ArgFringeParam) => attr.fillcolor("orange").style(filled)
    case n => super.color(attr, n)
  }
  
  override def emitNode(n:N) = {
    n match {
      case n:Top => super.visitNode(n)
      case n:Routable => emitSingleNode(n)
    }
  }

}
