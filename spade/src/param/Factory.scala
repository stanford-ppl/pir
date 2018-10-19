package spade
package param

import node._
import prism.enums._
import scala.collection.mutable._
import pureconfig._

object Factory extends Logging {
  def create[T](param:Parameter)(implicit design:SpadeDesign):T = (param match {
    case param:StaticGridTopParam => StaticGridTop(param)
    case param:DynamicGridTopParam => DynamicGridTop(param)
    case param:StaticCMeshTopParam => StaticCMeshTop(param)
    case param:DynamicCMeshTopParam => DynamicCMeshTop(param)
    case param:AsicTopParam => AsicTop(param)
    case param:PointToPointTopParam => PointToPointTop(param)
    case param:PCUParam => PCU(param, nios)
    case param:PMUParam => PMU(param, nios)
    case param:SCUParam => SCU(param, nios)
    case param:SramAGParam => SramAG(param, nios)
    case param:DramAGParam => DramAG(param, nios)
    case param:ArgFringeParam => ArgFringe(param, nios)
    case param:MCParam => MC(param, nios)
    case param:SwitchParam => SwitchBox(param, nios)
    case param:RouterParam => Router(param, nios)
    case param:StaticGridNetworkParam[_] => StaticGridNetwork[B](param)
    case param:DynamicGridNetworkParam[_] => DynamicGridNetwork[B](param)
    case param:StaticCMeshNetworkParam[_] => StaticCMeshNetwork[B](param)
    case param:DynamicCMeshNetworkParam[_] => DynamicCMeshNetwork[B](param)
    case param => throw PIRException(s"Don't know how to create $param")
  }).asInstanceOf[T]
}
