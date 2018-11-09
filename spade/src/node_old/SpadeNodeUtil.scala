package spade
package node

import param._
trait SpadeNodeUtil extends Logging {

  def indexing[T<:SpadeNode](ns:List[T])(implicit design:SpadeDesign):List[T] = {
    import design.spademeta._
    ns.zipWithIndex.foreach { case (n, i) =>
      indexOf(n) = i
    }
    ns
  }

  def naming[T<:SpadeNode](n:T, s:String)(implicit design:SpadeDesign) = {
    import design.spademeta._
    nameOf(n) = s
    n
  }

  def bctOf(x:Any):ClassTag[_] = x match {
    case x:DirectedEdge[_,_] => x.bct
    case x:Pin[_] => x.bct
    case x:Bundle[_] => x.bct
    case x:StaticGridNetwork[_] => x.bct
    case x:StaticGridNetworkParam[_] => x.bct
    case x:DynamicGridNetwork[_] => x.bct
    case x:DynamicGridNetworkParam[_] => x.bct
    case x:FIFO[_] => x.bct
    case x => throw PIRException(s"don't have ClassTag[_<:PinType] for $x")
  }

  def isInput(port:Pin[_]) = port match {
    case port:Input[_] => true
    case port:Output[_] => false
  }

  def isOutput(port:Port[_]) = port match {
    case port:Input[_] => false
    case port:Output[_] => true
  }

  def is[B<:PinType:ClassTag](x:ClassTag[_<:PinType]) = implicitly[ClassTag[B]] == x
  def isBit(x:ClassTag[_<:PinType]) = is[Bit](x)
  def isWord(x:ClassTag[_<:PinType]) = is[Word](x)
  def isVector(x:ClassTag[_<:PinType]) = is[Vector](x)
  def as[A[_<:PinType], B<:PinType:ClassTag](x:A[_])(implicit f:A[_] => ClassTag[_<:PinType]) = {
    if (is[B](f(x))) Some(x.asInstanceOf[A[B]]) else None
  }
  implicit def DirectedEdgeToBct(x:DirectedEdge[_,_]):ClassTag[_<:PinType] = x.bct.asInstanceOf[ClassTag[_<:PinType]]
  implicit def PinToBct(x:Pin[_]):ClassTag[_<:PinType] = x.bct.asInstanceOf[ClassTag[_<:PinType]]
  implicit def BundleToBct(x:Bundle[_]):ClassTag[_<:PinType] = x.bct.asInstanceOf[ClassTag[_<:PinType]]
  implicit def FIFOToBct(x:FIFO[_]):ClassTag[_<:PinType] = x.bct.asInstanceOf[ClassTag[_<:PinType]]
  implicit def DynamicGridNetworkToBct(x:DynamicGridNetwork[_]):ClassTag[_<:PinType] = x.bct.asInstanceOf[ClassTag[_<:PinType]]
  implicit def StaticGridNetworkToBct(x:StaticGridNetwork[_]):ClassTag[_<:PinType] = x.bct.asInstanceOf[ClassTag[_<:PinType]]

  def isMesh(n:Any):Boolean = n match {
    case n:SpadeDesign => isMesh(n.top)
    case n:GridTop => n.param.networkParams.forall(isMesh)
    case n:StaticGridNetwork[_] => isMesh(n.param)
    case n:DynamicGridNetwork[_] => isMesh(n.param)
    case n:DynamicGridNetworkParam[_] => n.isMesh
    case n:StaticGridNetworkParam[_] => n.isMesh
    case _ => false
  }

  def isTorus(n:Any):Boolean = n match {
    case n:SpadeDesign => isTorus(n.top)
    case n:GridTop => n.param.networkParams.forall(isTorus)
    case n:StaticGridNetwork[_] => isTorus(n.param)
    case n:DynamicGridNetwork[_] => isTorus(n.param)
    case n:DynamicGridNetworkParam[_] => n.isTorus
    case n:StaticGridNetworkParam[_] => n.isTorus
    case _ => false
  }

  def isCMesh(n:Any):Boolean = n match {
    case n:SpadeDesign => isCMesh(n.top)
    case n:StaticCMeshTop => n.param.networkParams.forall(isCMesh)
    case n:StaticCMeshNetwork[_] => true
    case n:StaticCMeshNetworkParam[_] => true
    case n:DynamicCMeshTop => true
    case n:DynamicCMeshTopParam => true
    case n:DynamicCMeshNetwork[_] => true
    case n:DynamicCMeshNetworkParam[_] => true
    case _ => false
  }

  def isDynamic(n:Any):Boolean = n match {
    case n:SpadeDesign => isDynamic(n.top)
    case n:DynamicGridTop => true
    case n:DynamicGridTopParam => true
    case n:DynamicCMeshTop => true
    case n:DynamicCMeshTopParam => true
    case n:DynamicGridNetwork[_] => true
    case n:DynamicGridNetworkParam[_] => true
    case n:DynamicCMeshNetwork[_] => true
    case n:DynamicCMeshNetworkParam[_] => true
    case n => false
  }

  def isStatic(n:Any):Boolean = n match {
    case n:SpadeDesign => isStatic(n.top)
    case n:StaticGridTop => true
    case n:StaticGridTopParam => true
    case n:StaticCMeshTop => true
    case n:StaticCMeshTopParam => true
    case n:StaticGridNetwork[_] => true
    case n:StaticGridNetworkParam[_] => true
    case n:StaticCMeshNetwork[_] => true
    case n:StaticCMeshNetworkParam[_] => true
    case n => false
  }

  def isAsic(n:Any):Boolean = n match {
    case n:SpadeDesign => isAsic(n.top)
    case n:AsicTop => true
    case n:AsicTopParam => true
    case n => false
  }

  def isPointToPoint(n:Any):Boolean = n match {
    case n:SpadeDesign => isPointToPoint(n.top)
    case n:PointToPointTop => true
    case n:PointToPointTopParam => true
    case n => false
  }

  def cuOf(n:SpadeNode) = n.collectUp[CU]().headOption

  def routableOf(n:SpadeNode) = n.collectUp[Routable]().headOption

  override def quote(n:Any) = n match {
    case n:SpadeNode => n.qindex
    case n => super.quote(n)
  }
}
