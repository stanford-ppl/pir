package pir
package mapper

import pir.node._
import pir.pass._
import spade.param._

trait CUConstrain extends Constrain with PIRNodeUtil with spade.node.SpadeNodeUtil with TypeUtil {
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

  def qdef(n:Any) = n match {
    case n:PNode => n.qdef
    case n => s"$n"
  }

}

class CUCostConstrain(implicit pass:CUPruner) extends CUConstrain with CostConstrain[CUCost] {

  def getKeyCost(cuP:K):CUCost = keyCost(cuP)
  def getValueCost(cuS:V):CUCost = valueCost(cuS.param)
  override def prune(fg:FG):EOption[FG] = pass.withMemory { super.prune(fg) }
  def fit(key:K, value:V):(Boolean, Boolean) =  {
    pass.memorize("fit", (key,value.param)) { case (key, value) =>
    val kc = keyCost(key)
    val vc = valueCost(value)
    dbgblk(s"fits(${quote(key)}, ${quote(value)})") {
      val fits = kc.fit(key, vc)
      (key, value) match {
        case (key, value:DramAGParam) if isDAG(key) & !fits._1 => 
          warn(s"${quote(key)}(dag) not fit ${quote(value)} overCosts=${kc.overCosts(vc).mkString(",")}")
        case _ =>
      }
      fits
    }
  }
  }

  def inputsP(cuP:K) = cuP.ins.groupBy { _.from.src }.map { case (src, ins) => ins.head.src }
  def outputsP(cuP:K) = cuP.outs.map { _.src }
  def keyCost(cuP:K) = pass.memorize("keyCost", cuP) { cuP => 
    dbgblk(s"keyCost(${quote(cuP)})") {
      val ins = inputsP(cuP)
      val outs = outputsP(cuP)
      val stages = cuP.collectDown[StageDef]()
      val ops = stages.flatMap { _.productIterator.toList.collect { case op:Op => op } }.toSet
      val numLanes:Int = stages.map(s => pass.getParOf(s)).reduceOption{ _ max _}.getOrElse(1)
      CUCost(
        AFGCost(isAFG(cuP)),
        MCCost(isDFG(cuP) || isSFG(cuP)),
        SramSizeCost(maxOption(cuP.collectDown[pir.node.SRAM]().map { _.size}).getOrElse(0)),
        SramCost(cuP.collectDown[pir.node.Memory]().filter(isRemoteMem).size),
        ControlInputCost(ins.filter(n => isBit(n)).size),
        ScalarInputCost(ins.filter(n => isWord(n)).size),
        VectorInputCost(ins.filter(n => isVector(n)).size),
        ControlOutputCost(outs.filter(n => isBit(n)).size),
        ScalarOutputCost(outs.filter(n => isWord(n)).size),
        VectorOutputCost(outs.filter(n => isVector(n)).size),
        StageCost(stages.size),
        LaneCost(numLanes),
        OpCost(ops)
      )
    }
  }

  val topS = pass.designS.top
  def controlInput(param:Parameter) = param match {
    case param:CUParam => Math.min(topS.minInputs[Bit](param), param.numControlFifos)
    case param => topS.minInputs[Bit](param)
  }
  // HACK: leave some buffer on scalar input to allow copying of counter chain
  def scalarInput(param:Parameter) = param match {
    case param:CUParam if pass.isInstanceOf[GlobalPartioner] => Math.min(topS.minInputs[Word](param), param.numScalarFifos) - 1
    case param:CUParam => Math.min(topS.minInputs[Word](param), param.numScalarFifos)
    case param => topS.minInputs[Word](param)
  }
  def vectorInput(param:Parameter) = param match {
    case param:CUParam => Math.min(topS.minInputs[Vector](param), param.numVectorFifos)
    case param => topS.minInputs[Vector](param)
  }
  def controlOutput(param:Parameter) = topS.minOutputs[Bit](param)
  def scalarOutput(param:Parameter) = param match {
    case param:CUParam if param.simdParam.nonEmpty => Math.min(topS.minOutputs[Word](param), param.simdParam.get.numScalarOuts)
    case param => topS.minOutputs[Word](param)
  }
  def vectorOutput(param:Parameter) = param match {
    case param:CUParam if param.simdParam.nonEmpty => Math.min(topS.minOutputs[Vector](param), param.simdParam.get.numVectorOuts)
    case param => topS.minOutputs[Vector](param)
  }
  def valueCost(param:Parameter):CUCost = pass.memorize("valueCost", param) { param =>
    dbgblk(s"valueCost(${quote(param)})"){
      CUCost(
        AFGCost(param.isInstanceOf[ArgFringeParam]),
        MCCost(param.isInstanceOf[MCParam]),
        SramSizeCost(param match { case param:CUParam => param.sramParam.size; case _ => 0 }),
        SramCost(param match { case param:CUParam => param.numSrams; case _ => 0 }),
        ControlInputCost(controlInput(param)),
        ScalarInputCost(scalarInput(param)),
        VectorInputCost(vectorInput(param)),
        ControlOutputCost(controlOutput(param)),
        ScalarOutputCost(scalarOutput(param)),
        VectorOutputCost(vectorOutput(param)),
        StageCost(param match { case param:CUParam => param.simdParam.fold(0) { _.stageParams.size }; case _ => 0 }),
        LaneCost(param match { case param:CUParam => param.simdParam.fold(1) { _.numLanes }; case _ => 1 }),
        OpCost(param match { case param:CUParam => param.simdParam.map { _.ops.toSet }.getOrElse(Set.empty); case _ => Set.empty })
      )
    }
  }
}

class CUArcConsistencyConstrain(implicit pass:CUPruner) extends CUConstrain with ArcConsistencyConstrain
class CUMatchingConstrain(implicit pass:CUPruner) extends CUConstrain with MatchingConstrain
