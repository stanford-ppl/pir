package spade
package node
import param._

trait Top extends Module {
  val param:TopParam
  import param._
  import design.spademeta._

  @transient val bundleGroups = ListBuffer[BundleGroup]()

  @transient val networks:List[Network[_<:PinType]]

  def bundleGroup(param:Parameter, coord:Option[(Int,Int)]=None) = {
    val bg = BundleGroup(param,coord=coord)
    bundleGroups += bg
    bg
  }

  def createSubmodules = {
    paramMap.headOption.foreach { _._2.keys.foreach(checkParam) }
    bundleGroups.foreach { case b@BundleGroup(param, coord) => 
      val m = Module(Factory.create(param, b.bundles))
      coord.foreach { coord => indexOf(m) = coord }
    }
  }

  def checkParam(param:Parameter):Unit = param match {
    case param:CUParam if param.simdParam.nonEmpty =>
      checkParam(s"$param cin", minInputs[Bit](param), param.numControlFifos)
      checkParam(s"$param sin", minInputs[Word](param), param.numScalarFifos)
      checkParam(s"$param vin", minInputs[Vector](param), param.numVectorFifos)
      val simd = param.simdParam.get
      checkParam(s"$param sout", minOutputs[Word](param), simd.numScalarOuts)
      checkParam(s"$param vout", minOutputs[Vector](param), simd.numVectorOuts)
    case param:CUParam =>
      checkParam(s"$param cin", minInputs[Bit](param), param.numControlFifos)
      checkParam(s"$param sin", minInputs[Word](param), param.numScalarFifos)
      checkParam(s"$param vin", minInputs[Vector](param), param.numVectorFifos)
    case param =>
  }

  def checkParam(msg:String, netParam:Int, cuParam:Int):Unit = {
    if (cuParam > netParam) warn(s"$msg cuParam=$cuParam > netParam=$netParam")
  }

  private lazy val paramMap:Map[ClassTag[_], Map[Parameter, List[Bundle[_]]]] = networks.map { net => 
    val map = bundleGroups.groupBy { bg => bg.param }.map { case (param, bgs) =>
      (param, bgs.map { bg => bg.bundle(net.bct) }.toList)
    }.toMap
    (net.bct, map)
  }.toMap

  lazy val minInputSize = paramMap.mapValues { _.mapValues{ _.map{_.inputs.size}.min } }
  def minInputs[B<:PinType:ClassTag](param:Parameter) = 
    minInputSize.get(implicitly[ClassTag[B]]).map { _.getOrElse(param, 0) }.getOrElse(0)

  lazy val maxInputSize = paramMap.mapValues { _.mapValues{ _.map{_.inputs.size}.max } }
  def maxInputs[B<:PinType:ClassTag](param:Parameter) = 
    maxInputSize.get(implicitly[ClassTag[B]]).map { _.getOrElse(param, 0) }.getOrElse(0)

  lazy val minOutputSize = paramMap.mapValues { _.mapValues{ _.map{_.outputs.size}.min } }
  def minOutputs[B<:PinType:ClassTag](param:Parameter) = 
    minOutputSize.get(implicitly[ClassTag[B]]).map { _.getOrElse(param, 0) }.getOrElse(0)

  lazy val maxOutputSize = paramMap.mapValues { _.mapValues{ _.map{_.outputs.size}.max } }
  def maxOutputs[B<:PinType:ClassTag](param:Parameter) = 
    maxOutputSize.get(implicitly[ClassTag[B]]).map { _.getOrElse(param, 0) }.getOrElse(0)
}
