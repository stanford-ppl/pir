package pir.codegen

import pir.mapper._
import spade.node._
import prism.collection.immutable._
import prism.mapper._

class PIRNetworkDotCodegen[B<:PinType:ClassTag](fileName:String, mapping: => Any)(implicit compiler:PIR) 
  extends spade.codegen.NetworkDotCodegen[B](fileName)(implicitly[ClassTag[B]], compiler.arch) {

  def this(fileName:String)(implicit compiler:PIR) = {
    this(fileName, compiler.pirmeta.pirMap)
  }

  override val dirName = compiler.outDir

  def isUsed(n:Any, mapping:Any):Boolean = {//dbgblk(s"isUsed(${quote(n)}, ${mapping.getClass.getSimpleName})") {
    (n, mapping) match {
      case (n, Left(mapping)) => isUsed(n, mapping) 
      case (n, Right(mapping)) => isUsed(n, mapping) 
      case (n, bt@BindingTrace(pnode, mapping)) => bt.last.fold { isUsed(n, mapping) } { failure => isUsed(n, failure) }

      case (n:Routable, mapping:PIRMap) => isUsed(n, mapping.cumap)
      case (n:Routable, InvalidFactorGraph(fg:CUMap, k)) => isUsed(n, fg)
      case (n:Routable, mapping:CUMap) => mapping.usedMap.bmap.contains(n)

      case ((from, to), mapping:PIRMap) => isUsed(n, mapping.fimap)
      case ((from, to), mapping:FIMap) => mapping.get(to.asInstanceOf[FIMap.K]) == Some(from)
      case (n, mapping) => 
        //dbg(s"don't know how to lookup isUsed(${quote(n)}, $mapping)")
        false
    }
  }

  override def color(attr:DotAttr, n:Any) = n match {
    case n:Routable if !isUsed(n, mapping) => attr
    case (n:ArgFringe, "top") if !isUsed(n, mapping) => attr
    case (n:ArgFringe, "bottom") if !isUsed(n, mapping) => attr
    case (from:Edge, to:Edge) if isUsed(n, mapping) => attr.color("indianred1").style(bold)
    case (from:Edge, to:Edge) => attr
    case n => super.color(attr, n)
  }

  override def emitEdge(from:prism.node.Edge[N], to:prism.node.Edge[N], attr:DotAttr):Unit = {
    super.emitEdge(from, to, color(attr, (from, to)))
  }

}
