package pir
package codegen

import pir.mapper._
import spade.node._
import prism.collection.immutable._

class PIRNetworkDotCodegen[B<:PinType:ClassTag](fileName:String, mapping: => Any, override val openDot:Boolean)(implicit compiler:PIR) 
  extends spade.codegen.NetworkDotCodegen[B](fileName)(implicitly[ClassTag[B]], compiler.arch) with SpadeNodeUtil with pir.node.PIRNodeUtil with MappingUtil with pir.pass.TypeUtil {

  def this(fileName:String, mapping: => Any)(implicit compiler:PIR) = this(fileName, mapping, SpadeConfig.openDot)

  def this(fileName:String)(implicit compiler:PIR) = {
    this(fileName, compiler.pirmeta.pirMap)
  }

  val pirmeta = compiler.pirmeta
  import pirmeta._

  def qdef(n:Any) = n match {
    case n:PNode => n.qdef
    case n => s"$n"
  }

  override val dirName = compiler.outDir

  override def color(attr:DotAttr, n:Any) = n match {
    case n:Routable if isMapped(n, mapping) => super.color(attr, n)
    case (from:Edge, to:Edge) if isMapped((from, to), mapping) => attr.color("indianred1").style(bold)
    case n => attr
  }

  override def label(attr:DotAttr, n:Any) = n match {
    case n:Routable => 
      mappedTo[pir.node.GlobalContainer](n, mapping).fold {
        super.label(attr, n)
      } { cuP =>
        var label = super.label(attr,n).label.get
        label += s"\n(${quote(cuP)}[${cuType(cuP).get}])"
        attr.label(label)
      }
    case (from:Edge, to:Edge) => 
      mappedTo[Set[MKMap.V]]((from, to), mapping).fold {
        attr
      }{ mks =>
        attr.label(s"[${mks.map{_.id}.mkString(",")}]")
      }
    case n => super.label(attr,n)
  }

  override def emitEdge(from:prism.node.Output[N], to:prism.node.Input[N], attr:DotAttr):Unit = {
    val edge = (from, to)
    var at = attr
    at = color(at, edge)
    at = label(at, edge)
    super.emitEdge(from, to, at)
  }

}
