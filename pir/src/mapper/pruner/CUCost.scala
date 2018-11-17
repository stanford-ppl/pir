package pir

package mapper

import pir.node._
import pir.pass._
import spade.param._
import prism.collection.immutable._
import prism.util._
import prism.mapper._

//case class CUCost(
  //isAFG:AFGCost=AFGCost(),
  //isMC:MCCost=MCCost(),
  //isDAG:DAGCost=DAGCost(),
  //sram:SRAMCost=SRAMCost(),
  //sramBank:SRAMBankCost=SRAMBankCost(),
  //sramSize:SRAMSizeCost=SRAMSizeCost(),
  //sfifo:ScalarFIFOCost=ScalarFIFOCost(),
  //vfifo:VectorFIFOCost=VectorFIFOCost(),
  //sin:ScalarInputCost=ScalarInputCost(),
  //sout:ScalarOutputCost=ScalarOutputCost(),
  //vin:VectorInputCost=VectorInputCost(),
  //vout:VectorOutputCost=VectorOutputCost(),
  //stage:StageCost=StageCost(),
  //lane:LaneCost=LaneCost(),
  //op:OpCost=OpCost()
//) extends Cost[CUCost] with CaseTuple[CUCost] {
  //type K = CUMap.K
  //override def toString = s"CUCost(${overcosts.mkString(",")})"
  //def costs = productIterator.toSeq.as[Seq[Cost[_]]]
  ////def overcosts = costs.filter { _.nonEmpty }
  //def - (x:CUCost):CUCost = {
    //val diffs = costs.zip(x.costs).map { case (c, tc) => c diff tc }
    //this.newInstance[CUCost](diffs)
  //}
  //def + (x:CUCost):CUCost = {
    //val sum = costs.zip(x.costs).map { case (c, tc) => (c add tc) }
    //this.newInstance[CUCost](sum)
  //}
  //def accum[F<:Cost[F]:ClassTag](x:F) = map[F] { _ + x }
  ////def nonEmpty:Boolean = costs.nonEmpty
//}

case class AFGCost(prefix:Boolean=false) extends PrefixCost[AFGCost]
case class DAGCost(prefix:Boolean=false) extends PrefixCost[DAGCost]
case class MCCost(prefix:Boolean=false) extends PrefixCost[MCCost]
case class SRAMCost(count:Int=0, bank:Int=0, size:Int=0) extends QuantityCost[SRAMCost]
case class FIFOCost(sfifo:Int=0, vfifo:Int=0) extends QuantityCost[FIFOCost]
case class InputCost(sin:Int=0, vin:Int=0) extends QuantityCost[InputCost]
case class OutputCost(sout:Int=0, vout:Int=0) extends QuantityCost[OutputCost]
case class StageCost(quantity:Int=0) extends QuantityCost[StageCost]
case class LaneCost(quantity:Int=1) extends MaxCost[LaneCost]
case class OpCost(set:Set[Opcode]=Set.empty) extends SetCost[Opcode,OpCost]

trait CostUtil extends RuntimeAnalyzer with Memorization { self:PIRPass =>
  implicit class CostOp(x:Any) {
    def getCost[C<:Cost[C]:ClassTag]:C = x match {
      case x:CUMap.V => self.getCost(x.params.get, classTag[C]).as[C]
      case x => self.getCost(x, classTag[C]).as[C]
    }
  }

  private def getCost(x:Any, ct:ClassTag[_]) = memorize("getCost", (x, ct)) { case (x, ct) =>
    def switch[C<:Cost[C]:ClassTag](cfunc:Any => C) = if (ct == classTag[C]) Some(cfunc(x)) else None
    switch[AFGCost] {
      case n:GlobalContainer => AFGCost(n.isInstanceOf[ArgFringe])
      case n:Parameter => AFGCost(n.isInstanceOf[ArgFringeParam])
    } orElse switch[DAGCost] {
      case n:GlobalContainer => DAGCost(n.siblingDepeds.exists{_.isInstanceOf[DRAMFringe]})
      case n:Parameter => 
        val isDAG:Boolean = n.to[DramAGParam].map { _ => true }.getOrElse {
          val pattern = n.collectOut[Pattern]().head
          val hasDAG = pattern.cuParams.exists { _.isInstanceOf[DramAGParam] }
          n.to[CUParam].fold (false) { _ => !hasDAG }
        }
        DAGCost(isDAG)
    } orElse switch[MCCost] {
      case n:GlobalContainer => MCCost(n.isInstanceOf[DRAMFringe])
      case n:Parameter => MCCost(n.isInstanceOf[MCParam])
    } orElse switch[SRAMCost] {
      case n:GlobalContainer =>
        val srams = n.collectDown[SRAM]()
        val sramSize = srams.map { sram => sram.depth.get * sram.size }.maxOption.getOrElse(0)
        val nBanks = srams.map { _.nBanks }.maxOption.getOrElse(0)
        SRAMCost(srams.size, nBanks, sramSize)
      case n:CUParam => SRAMCost(n.sramParam.count, n.sramParam.bank, n.sramParam.sizeInWord)
      case n => SRAMCost(0,0,0)
    } orElse switch[FIFOCost] {
      case n:GlobalContainer => 
        val fifos = n.collectDown[FIFO]()
        val (vfifos, sfifos) = fifos.partition { _.getVec > 1 }
        val ctxs = n.collectDown[Context]()
        val fcost = FIFOCost(sfifos.size, vfifos.size)
        ctxs.map { _.getCost[FIFOCost] }.fold(fcost) { _ + _ }
      case n:Context =>
        val fifos = n.collectDown[BufferRead]()
        val (vfifos, sfifos) = fifos.partition { _.getVec > 1 }
        FIFOCost(sfifos.size, vfifos.size)
      case n:CUParam =>
        FIFOCost(n.fifoParamOf("word").fold(0){_.count}, n.fifoParamOf("vec").fold(0){_.count})
      case n:Parameter => FIFOCost(0,0)
    } orElse switch[InputCost] {
      case n:GlobalContainer => 
        val gins = n.collectDown[GlobalInput]()
        val (vins, sins) = gins.partition { _.getVec > 1 }
        InputCost(sins.size, vins.size)
      case n:CUParam => InputCost(n.numSin, n.numVin)
      case n:Parameter => InputCost(100,100)
    } orElse switch[OutputCost] {
      case n:GlobalContainer => 
        val outs = n.collectDown[GlobalOutput]()
        val (vouts, souts) = outs.partition { _.getVec > 1 }
        OutputCost(souts.size, vouts.size)
      case n:CUParam => OutputCost(n.numSout, n.numVout)
      case n:Parameter => OutputCost(100,100)
    } orElse switch[StageCost] {
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[StageCost] }.reduce { _ + _ }
      case n:Context =>
        val ops = n.collectDown[OpDef]()
        StageCost(ops.size)
      case n:CUParam => 
        StageCost(n.numStage)
      case n:Parameter => StageCost(0)
    } orElse switch[LaneCost] {
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[LaneCost] }.reduce { _ + _ }
      case n:Context =>
        val inner = n.collectDown[Controller]().minOptionBy { _.ctrl.get }
        LaneCost(inner.map { _.getVec }.getOrElse(1))
      case n:CUParam => LaneCost(n.numLane)
      case n:Parameter => LaneCost(1)
    } orElse switch[OpCost] {
      case n:GlobalContainer => 
        val ctxs = n.collectDown[Context]()
        ctxs.map { _.getCost[OpCost] }.reduce { _ + _ }
      case n:Context =>
        val ops = n.collectDown[OpDef]()
        OpCost(ops.map { _.op }.toSet)
      case n:CUParam => OpCost(n.ops)
      case n:Parameter => OpCost(Set.empty)
    } getOrElse {
      throw PIRException(s"Don't know how to compute $ct of $x")
    }
  }

  //class CUCostConstrain extends CostConstrain[CUCost] {
    //def getCost(x:Any) = x.getCost.as[CUCost]
    //override def prune[T](field:T):EOption[T] = {
      //field match {
        //case field:CUMap => prune(field).as[EOption[T]]
        //case field => Right(field)
      //}
    //}
  //}
}
