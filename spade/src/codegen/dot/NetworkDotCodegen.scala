package spade
package codegen

import spade.node._ 
import spade.param._ 
import prism.codegen._

class NetworkDotCodegen(val fileName:String, topFunc: => Top)(implicit compiler:Compiler) extends IRDotCodegen {

  lazy val top = topFunc

  override def label(attr:DotAttr, n:N) = {
    var label = quote(n)
    //compiler.top.param match {
      //case param:DynamicGridTopParam =>
        //n match {
          //case n:Routable =>
            //val List(x,y) = indexOf(n)
            //val maxDim = math.max(param.numTotalRows, param.numTotalCols)
            //val idx = (param.numTotalRows-1-y) * maxDim + x
            //label += s"\n($idx)"
          //case n =>
        //}
      //case param =>
    //}
    attr.label(label)
  }

  override def color(attr:DotAttr, n:N) = n match {
    case Param(_:PCUParam) => attr.fillcolor("dodgerblue").style(filled)
    case Param(_:PMUParam) => attr.fillcolor("lightseagreen").style(filled)
    //case Param(_:SCUParam) => attr.fillcolor("palevioletred1").style(filled)
    case Param(_:DramAGParam) => attr.fillcolor("palevioletred1").style(filled)
    case Param(_:MCParam) => attr.fillcolor("forestgreen").style(filled)
    case _:Connector => attr.fillcolor("indianred1").style(filled)
    case Param(_:ArgFringeParam) => attr.fillcolor("orange").style(filled)
    //case n:OuterComputeUnit => Color("orange")
    case n => super.color(attr, n)
  }
  
  override def emitNode(n:N) = {
    n match {
      case n:Top => super.visitNode(n)
      case n:Routable => emitSingleNode(n)
    }
  }

  //override def emitEdge(from:prism.node.Output[N], to:prism.node.Input[N], attr:DotAttr):Unit = {
    //(from, to) match {
      //case (from:DirectedEdge[_,_], to:DirectedEdge[_,_]) if is[B](from) & is[B](to) => 
        //emitEdgeMatched(from.src.asInstanceOf[N], to.src, attr) 
      //case _ => 
    //}
  //}

  //override def quote(n:Any):String = n match {
    //case n:SpadeNode => n.qindex
    //case _ => n.toString
  //}

}
