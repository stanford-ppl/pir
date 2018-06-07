package pir
package mapper

import pir.node._
import pir.pass._
import spade.node._

trait CUConstrain extends Constrain with PIRNodeUtil with SpadeNodeUtil with TypeUtil {
  type K = CUMap.K
  type V = CUMap.V
  type FG = CUMap
  val fgct:ClassTag[FG] = classTag[FG]

  val pirmeta = pass.pirmeta
  val spademeta = pass.spademeta

  override def quote(n:Any) = n match {
    case n:GlobalContainer => s"$n[${cuType(n).get}]"
    case n:Iterable[_] => n.map(quote).toString
    case n => super.quote(n)
  }

  def inputsP(cuP:K) = {
    cuP.ins.groupBy { in =>
      in.from.src
    }.map { case (src, ins) => ins.head.src }
  }
  def outputsP(cuP:K) = {
    cuP.outs.map { out => out.src }
  }
  lazy val isPartitioner = pass.isInstanceOf[GlobalPartioner]

  def fifosP(cuP:K) = {
    val fifos = cuP.collectDown[pir.node.FIFO]()
    val rfifos = if (isPartitioner) {
      cuP.collectDown[pir.node.RetimingFIFO]() ++ 
      inputsP(cuP).filterNot { _.isInstanceOf[LocalStore] }
    } else {
      cuP.collectDown[pir.node.RetimingFIFO]()
    }
    fifos ++ rfifos
  }
}

case class CUCost(costs:Cost[_]*) extends Cost[CUCost]{
  override def toString = s"CUCost(${costs.mkString(",")})"
  val isSplittable = true
  def overCosts(that:CUCost) = {
    costs.zip(that.costs).filter { case (cost, tcost) =>
      cost.compareAsC(tcost) > 0
    }
  }
  def fit(that:Any) = {
    val fits = costs.zip(that.asInstanceOf[CUCost].costs).map { case (cost, tcost) =>
      cost.fit(tcost)
    }
    (fits.forall(_._1), fits.filter(!_._1).forall(_._2))
  }
  def compare(that:CUCost) = {
    val comps = costs.zip(that.costs).map { case (cost, tcost) =>
      cost.compareAsC(tcost)
    }
    if (comps.exists { _ > 0 }) 1 
    else if (comps.forall { _ == 0 }) 0
    else -1
  }
}
trait PrefixCost[C<:PrefixCost[C]] extends Cost[C] {
  val isSplittable = false 
  val prefix:Boolean
  def fit(that:Any) = (this <= that.asInstanceOf[C], isSplittable)
  def compare(that:C) = if (prefix == that.prefix) 0 else 1
}
trait QuantityCost[C<:QuantityCost[C]] extends Cost[C] {
  val quantity:Int
  def fit(that:Any) = (this <= that.asInstanceOf[C], isSplittable)
  def compare(that:C) = quantity.compare(that.quantity)
}
case class AFGCost(prefix:Boolean) extends PrefixCost[AFGCost]
case class MCCost(prefix:Boolean) extends PrefixCost[MCCost]
case class SramCost(quantity:Int) extends QuantityCost[SramCost] { val isSplittable = false }
case class ControlFifoCost(quantity:Int) extends QuantityCost[ControlFifoCost] { val isSplittable = true }
case class ScalarFifoCost(quantity:Int) extends QuantityCost[ScalarFifoCost] { val isSplittable = true }
case class VectorFifoCost(quantity:Int) extends QuantityCost[VectorFifoCost] { val isSplittable = true }
case class ControlInputCost(quantity:Int) extends QuantityCost[ControlInputCost] { val isSplittable = true }
case class ScalarInputCost(quantity:Int) extends QuantityCost[ScalarInputCost] { val isSplittable = true }
case class VectorInputCost(quantity:Int) extends QuantityCost[VectorInputCost] { val isSplittable = true }
case class ControlOutputCost(quantity:Int) extends QuantityCost[ControlOutputCost] { val isSplittable = true }
case class ScalarOutputCost(quantity:Int) extends QuantityCost[ScalarOutputCost] { val isSplittable = true }
case class VectorOutputCost(quantity:Int) extends QuantityCost[VectorOutputCost] { val isSplittable = true }
case class StageCost(quantity:Int) extends QuantityCost[StageCost] { val isSplittable = true }
case class LaneCost(quantity:Int) extends QuantityCost[LaneCost] { val isSplittable = false }

class CUCostConstrain(implicit pass:CUPruner) extends CUConstrain with CostConstrain[CUCost] {
  def fit(key:K, value:V):(Boolean, Boolean) = {
    val kc = keyCost(key)
    val vc = valueCost(value)
    val fits = kc.fit(vc)
    (key, value) match {
      case (key, value:DramAG) if isDAG(key) & !fits._1 => 
        warn(s"${quote(key)}(dag) not fit ${quote(value)} overCosts=${kc.overCosts(vc).mkString(",")}")
      case _ =>
    }
    fits
  }
  def getKeyCost(cuP:K):CUCost = dbgblk(s"getKeyCost(${quote(cuP)})"){
    val fifos = fifosP(cuP)
    val ins = inputsP(cuP)
    val outs = outputsP(cuP)
    val stages = cuP.collectDown[StageDef]()
    val numLanes:Int = stages.map(s => pass.getParOf(s)).reduceOption{ _ max _}.getOrElse(1)
    CUCost(
      AFGCost(isAFG(cuP)),
      MCCost(isDFG(cuP) || isSFG(cuP)),
      SramCost(cuP.collectDown[pir.node.SRAM]().size),
      ControlFifoCost(fifos.filter(n => isBit(n)).size),
      ScalarFifoCost(fifos.filter(n => isWord(n)).size),
      VectorFifoCost(fifos.filter(n => isVector(n)).size),
      ControlInputCost(ins.filter(n => isBit(n)).size),
      ScalarInputCost(ins.filter(n => isWord(n)).size),
      VectorInputCost(ins.filter(n => isVector(n)).size),
      ControlOutputCost(outs.filter(n => isBit(n)).size),
      ScalarOutputCost(outs.filter(n => isWord(n)).size),
      VectorOutputCost(outs.filter(n => isVector(n)).size),
      StageCost(stages.size),
      LaneCost(numLanes)
    )
  }
  def getValueCost(cuS:V):CUCost = dbgblk(s"getValueCost(${quote(cuS)})"){
    CUCost(
      AFGCost(cuS.isInstanceOf[spade.node.ArgFringe]),
      MCCost(cuS.isInstanceOf[MC]),
      SramCost(cuS match { case cuS:CU => cuS.param.numSrams; case _ => 0 }),
      ControlFifoCost(cuS match { case cuS:CU => cuS.param.numControlFifos; case _ => 0 }),
      ScalarFifoCost(cuS match { case cuS:CU => cuS.param.numScalarFifos; case _ => 0 }),
      VectorFifoCost(cuS match { case cuS:CU => cuS.param.numVectorFifos; case _ => 0 }),
      ControlInputCost(cuS.bundle[Bit].fold(0) { _.inputs.size }),
      ScalarInputCost(cuS.bundle[Word].fold(0) { _.inputs.size }),
      VectorInputCost(cuS.bundle[Vector].get.inputs.size),
      ControlOutputCost(cuS.bundle[Bit].get.outputs.size),
      ScalarOutputCost(cuS.bundle[Word].get.outputs.size),
      VectorOutputCost(cuS.bundle[Vector].get.outputs.size),
      StageCost(cuS match { case cuS:CU => cuS.param.simdParam.fold(0) { _.stageParams.size }; case _ => 0 }),
      LaneCost(cuS match { case cuS:CU => cuS.param.simdParam.fold(1) { _.numLanes }; case _ => 1 })
    )
  }
}

class CUArcConsistencyConstrain(implicit pass:CUPruner) extends CUConstrain with ArcConsistencyConstrain
class CUMatchingConstrain(implicit pass:CUPruner) extends CUConstrain with MatchingConstrain

