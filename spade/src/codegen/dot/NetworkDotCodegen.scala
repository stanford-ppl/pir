package spade
package codegen

import spade.node._ 
import spade.param._ 

class NetworkDotCodegen[B<:PinType:ClassTag](val fileName:String)(implicit compiler:Spade) extends SpadeCodegen with IRDotCodegen {

  import spademeta._

  def openDot = SpadeConfig.openDot

  override def finPass = {
    super.finPass
    if (openDot) open
  }

  override def label(attr:DotAttr, n:Any) = {
    var label = quote(n)
    compiler.top.param match {
      case param:DynamicGridTopParam =>
        n match {
          case n:Routable =>
            val List(x,y) = indexOf(n)
            val maxDim = math.max(param.numTotalRows, param.numTotalCols)
            val idx = (param.numTotalRows-1-y) * maxDim + x
            label += s"\n($idx)"
          case n =>
        }
      case param =>
    }
    attr.label(label)
  }

  val scale = 5

  def pos(attr:DotAttr, n:Any) = {
    (n, compiler.designParam.topParam) match {
      case (n:SpadeNode, param:DynamicGridTopParam) =>
        indexOf.get(n).foreach { case List(x,y) =>
          n match {
            case n:Router => attr.pos(((x-0.5)*scale, (y-0.5)*scale))
            case n:DramAG => attr.pos((x*scale, (y+0.15)*scale))
            case n:MC => attr.pos((x*scale, (y-0.15)*scale))
            case n:ArgFringe => attr.pos((x*scale, (y+0.5)*scale))
            case n => attr.pos((x*scale, y*scale))
          }
        }
      case (n:SpadeNode, param) =>
        indexOf.get(n).foreach { case List(x,y) => attr.pos((x*scale, y*scale)) }
      case _ =>
    }
    attr
  }

  override def color(attr:DotAttr, n:Any) = n match {
    case n:PCU => attr.fillcolor("dodgerblue").style(filled)
    case n:PMU => attr.fillcolor("lightseagreen").style(filled)
    case n:SCU => attr.fillcolor("palevioletred1").style(filled)
    case n:DramAG => attr.fillcolor("palevioletred1").style(filled)
    case n:MC => attr.fillcolor("forestgreen").style(filled)
    case n:SwitchBox => attr.fillcolor("indianred1").style(filled)
    case n:Router => attr.fillcolor("indianred1").style(filled)
    case (n:ArgFringe, "top") => attr.fillcolor("orange").style(filled)
    case (n:ArgFringe, "bottom") => attr.fillcolor("orange").style(filled)
    case n:ArgFringe => attr.fillcolor("orange").style(filled)
    //case n:OuterComputeUnit => Color("orange")
    case n => super.color(attr, n)
  }

  override def setAttrs(n:Any):DotAttr = {
    var attr = super.setAttrs(n)
    attr = pos(attr, n)
    attr
  }
  
  override def emitNode(n:N) = {
    n match {
      case n:Top => super.visitNode(n)
      case n:Routable => emitSingleNode(n)
    }
  }

  override def emitEdge(from:prism.node.Output[N], to:prism.node.Input[N], attr:DotAttr):Unit = {
    (from, to) match {
      case (from:DirectedEdge[_,_], to:DirectedEdge[_,_]) if is[B](from) & is[B](to) => 
        emitEdgeMatched(from.src.asInstanceOf[N], to.src, attr) 
      case _ => 
    }
  }

  override def open = {
    shell(s"bin/dot -c ${outputPath} &".replace(".dot", ""))
  }

  override def quote(n:Any):String = n match {
    case n:SpadeNode => n.qindex
    case _ => n.toString
  }

}
