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

}

class CUCostConstrain(implicit pass:CUPruner) extends CUConstrain with CostConstrain[CUCost] {
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
      VectorInputCost(cuS.bundle[Vector].fold(0) { _.inputs.size }),
      ControlOutputCost(cuS.bundle[Bit].fold(0) { _.outputs.size }),
      ScalarOutputCost(cuS.bundle[Word].fold(0) { _.outputs.size }),
      VectorOutputCost(cuS.bundle[Vector].fold(0) { _.outputs.size }),
      StageCost(cuS match { case cuS:CU => cuS.param.simdParam.fold(0) { _.stageParams.size }; case _ => 0 }),
      LaneCost(cuS match { case cuS:CU => cuS.param.simdParam.fold(1) { _.numLanes }; case _ => 1 })
    )
  }
}

class CUArcConsistencyConstrain(implicit pass:CUPruner) extends CUConstrain with ArcConsistencyConstrain
class CUMatchingConstrain(implicit pass:CUPruner) extends CUConstrain with MatchingConstrain
