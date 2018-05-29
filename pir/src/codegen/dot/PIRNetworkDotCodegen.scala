package pir
package codegen

import pir.mapper._
import spade.node._
import prism.collection.immutable._

class PIRNetworkDotCodegen[B<:PinType:ClassTag](fileName:String, mapping: => Any)(implicit compiler:PIR) 
  extends spade.codegen.NetworkDotCodegen[B](fileName)(implicitly[ClassTag[B]], compiler.arch) with MappingUtil with pir.pass.TypeUtil {

  val pirmeta = compiler.pirmeta

  def this(fileName:String)(implicit compiler:PIR) = {
    this(fileName, compiler.pirmeta.pirMap)
  }

  override val dirName = compiler.outDir

  override def color(attr:DotAttr, n:Any) = n match {
    case n:Routable if !isMapped(n, mapping) => attr
    case n:Routable => super.color(attr, n)
    case (n:ArgFringe, "top") if !isMapped(n, mapping) => attr
    case (n:ArgFringe, "bottom") if !isMapped(n, mapping) => attr
    case (from:Edge, to:Edge) if mappedTo[Edge](to, mapping) == Some(from) => 
      attr.color("indianred1").style(bold)
    case n => attr
  }

  override def label(attr:DotAttr, n:Any) = n match {
    case n:Routable => 
      mappedTo[pir.node.GlobalContainer](n).fold {
        super.label(attr, n)
      } { cuP =>
        var label = s"${quote(n)}"
        label += s"\n(${quote(cuP)}[${cuType(cuP).get}])"
        attr.label(label)
      }
    case n => super.label(attr,n)
  }

  override def emitEdge(from:prism.node.Output[N], to:prism.node.Input[N], attr:DotAttr):Unit = {
    super.emitEdge(from, to, color(attr, (from, to)))
  }

}
