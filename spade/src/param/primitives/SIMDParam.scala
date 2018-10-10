package spade
package param

import node._

case class DefaultSIMDParam (
  numStages:Int,
  vectorized:Boolean,
  numRegs:Int,
  numScalarOuts:Int,
  numVectorOuts:Int,
  ops:List[Op]
) extends SIMDParam {
  lazy val numReductionStages = (Math.log(numLanes) / Math.log(2)).toInt
  lazy val numNonReductionStages = numStages - numReductionStages
  lazy val reductionIndices = List.tabulate(numStages){ i =>
    if (i >= numNonReductionStages) Some(i - numNonReductionStages) else None
  }

  lazy val cuParam = collectOut[CUParam]().head

  override lazy val pipeRegParams = {
    import cuParam._
    val prs = List.tabulate(numRegs) { ir => this.addField(PipeRegParam()) }
    prs.slice(0, numCtrs).map(_.color(CounterReg))
    if (numReductionStages > 0) {
      prs(0).color(ReduceReg) 
      prs(1).color(AccumReg) 
    }
    prs.takeRight(numScalarFifos).map(_.color(ScalarInReg))
    prs.takeRight(numScalarOuts).map(_.color(ScalarOutReg))
    prs.takeRight(numVectorFifos).map(_.color(VecInReg))
    prs.takeRight(numVectorOuts).map(_.color(VecOutReg))
    prs
  }
}

trait SIMDParam extends Parameter {
  val numRegs:Int
  val vectorized:Boolean
  lazy val designParam = collectOut[DesignParam]().head
  lazy val vecWidth = designParam.vecWidth
  lazy val numLanes:Int = if (vectorized) vecWidth else 1
  val ops:List[Op]
  val reductionIndices:List[Option[Int]]
  val numScalarOuts:Int
  val numVectorOuts:Int
  lazy val pipeRegParams:List[PipeRegParam] = List.tabulate(numRegs) { ir => addField(PipeRegParam()) }
  lazy val stageParams = reductionIndices.map { reductionIdx =>
    addField(StageParam(reductionIdx=reductionIdx))
  }
}

trait RegColor extends prism.enums.Enum
case object VecInReg extends RegColor
case object VecOutReg extends RegColor
case object ScalarInReg extends RegColor
case object ScalarOutReg extends RegColor
case object ReadAddrReg extends RegColor
case object WriteAddrReg extends RegColor
case object CounterReg extends RegColor
case object ReduceReg extends RegColor
case object AccumReg extends RegColor
