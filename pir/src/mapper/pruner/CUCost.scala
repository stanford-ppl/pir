package pir

package mapper

import pir.node._
import pir.pass._
import spade.param._
import prism.collection.immutable._
import prism.util._
import prism.mapper._

case class CUCost(
  isAFG:AFGCost=AFGCost(),
  isMC:MCCost=MCCost(),
  isDAG:DAGCost=DAGCost(),
  sram:SRAMCost=SRAMCost(),
  sramBank:SRAMBankCost=SRAMBankCost(),
  sramSize:SRAMSizeCost=SRAMSizeCost(),
  sfifo:ScalarFIFOCost=ScalarFIFOCost(),
  vfifo:VectorFIFOCost=VectorFIFOCost(),
  sin:ScalarInputCost=ScalarInputCost(),
  sout:ScalarOutputCost=ScalarOutputCost(),
  vin:VectorInputCost=VectorInputCost(),
  vout:VectorOutputCost=VectorOutputCost(),
  stage:StageCost=StageCost(),
  lane:LaneCost=LaneCost(),
  op:OpCost=OpCost()
) extends Cost[CUCost] with CaseTuple[CUCost] {
  type K = CUMap.K
  override def toString = s"CUCost(${overcosts.mkString(",")})"
  def costs = productIterator.toSeq.as[Seq[Cost[_]]]
  def overcosts = costs.filter { _.nonEmpty }
  def - (x:CUCost):CUCost = {
    val diffs = costs.zip(x.costs).map { case (c, tc) => c diff tc }
    this.newInstance[CUCost](diffs)
  }
  def + (x:CUCost):CUCost = {
    val sum = costs.zip(x.costs).map { case (c, tc) => (c add tc) }
    this.newInstance[CUCost](sum)
  }
  def accum[F<:Cost[F]:ClassTag](x:F) = map[F] { _ + x }
  def nonEmpty:Boolean = costs.nonEmpty
}

case class AFGCost(prefix:Boolean=false) extends PrefixCost[AFGCost]
case class DAGCost(prefix:Boolean=false) extends PrefixCost[DAGCost]
case class MCCost(prefix:Boolean=false) extends PrefixCost[MCCost]
case class SRAMCost(quantity:Int=0) extends QuantityCost[SRAMCost]
case class SRAMBankCost(quantity:Int=0) extends QuantityCost[SRAMBankCost]
case class SRAMSizeCost(quantity:Int=0) extends QuantityCost[SRAMSizeCost]
//case class ControlInputCost(quantity:Int) extends QuantityCost[ControlInputCost]
case class ScalarFIFOCost(quantity:Int=0) extends QuantityCost[ScalarFIFOCost]
case class VectorFIFOCost(quantity:Int=0) extends QuantityCost[VectorFIFOCost]
case class ScalarInputCost(quantity:Int=0) extends QuantityCost[ScalarInputCost]
case class VectorInputCost(quantity:Int=0) extends QuantityCost[VectorInputCost]
//case class ControlOutputCost(quantity:Int) extends QuantityCost[ControlOutputCost]
case class ScalarOutputCost(quantity:Int=0) extends QuantityCost[ScalarOutputCost]
case class VectorOutputCost(quantity:Int=0) extends QuantityCost[VectorOutputCost]
case class StageCost(quantity:Int=0) extends QuantityCost[StageCost]
case class LaneCost(quantity:Int=1) extends MaxCost[LaneCost]
case class OpCost(set:Set[Opcode]=Set.empty) extends SetCost[Opcode,OpCost]

trait CostUtil extends RuntimeAnalyzer with Memorization { self:PIRPass =>
  def getCost(x:Any):Cost[_] = memorize("getCost", x) { x => 
    dbgblk(s"getCost($x)"){
      x match {
        case x:GlobalContainer => 
          val ctxs = x.collectDown[Context]()
          val srams = x.collectDown[SRAM]()
          val sramSize = srams.map { sram => sram.depth.get * sram.size }.maxOption
          val nBanks = srams.map { _.nBanks }.maxOption
          val fifos = x.collectDown[FIFO]()
          val (vfifos, sfifos) = fifos.partition { _.getVec > 1 }
          val gins = x.collectDown[GlobalInput]()
          val gouts = x.collectDown[GlobalOutput]()
          val (vins, sins) = gins.partition { _.getVec > 1 }
          val (vouts, souts) = gouts.partition { _.getVec > 1 }
          ctxs.map { _.getCost.as[CUCost] }.fold(CUCost()) { _ + _ }
          .foldAt(x.to[ArgFringe]) { case (cost, afg) => cost accum AFGCost(true) }
          .foldAt(x.to[DRAMFringe]) { case (cost, afg) => cost accum MCCost(true) }
          .accum(DAGCost(x.siblingDepeds.exists{_.isInstanceOf[DRAMFringe]}))
          .accum(SRAMCost(srams.size))
          .foldAt(sramSize) { case (cost, size) => cost accum SRAMSizeCost(size) }
          .foldAt(nBanks) { case (cost, nb) => cost accum SRAMBankCost(nb) }
          .accum(VectorFIFOCost(vfifos.size))
          .accum(ScalarFIFOCost(sfifos.size))
          .accum(VectorInputCost(vins.size))
          .accum(VectorOutputCost(vouts.size))
          .accum(ScalarInputCost(sins.size))
          .accum(ScalarOutputCost(souts.size))
        case x:Context =>
          val fifos = x.collectDown[BufferRead]()
          val (vfifos, sfifos) = fifos.partition { _.getVec > 1 }
          val ops = x.collectDown[OpDef]()
          val inner = x.collectDown[Controller]().minOptionBy { _.ctrl.get }
          CUCost(
            vfifo=VectorFIFOCost(vfifos.size),
            sfifo=ScalarFIFOCost(sfifos.size),
            stage=StageCost(ops.size),
            op=OpCost(ops.map { _.op }.toSet),
          )
          .foldAt(inner) { case (cost, inner) => cost accum LaneCost(inner.getVec) }
        case x:CUParam =>
          CUCost(
            sram=SRAMCost(x.sramParam.count),
            sramSize=SRAMSizeCost(x.sramParam.sizeInWord),
            sramBank=SRAMBankCost(x.sramParam.bank),
            vin=VectorInputCost(x.numVin),
            vout=VectorOutputCost(x.numVout),
            sin=ScalarInputCost(x.numSin),
            sout=ScalarOutputCost(x.numSout),
            lane=LaneCost(x.numLane),
            stage=StageCost(x.numStage),
          )
      }
    }
  }

  implicit class CostOp(x:Any) {
    def getCost:Cost[_] = self.getCost(x)
  }

  class CUCostConstrain extends CostConstrain[CUCost] {
    def getCost(x:Any) = x match {
      case x:CUMap.K => getCost(x)
      case x:CUMap.V => getCost(x.params.get)
      case x => throw PIRException(s"Don't know how to compute cost of $x")
    }
    override def prune(field:Any):EOption[Any] = {
      field match {
        case field:CUMap => prune(field)
        case field => Right(field)
      }
    }
  }
}
