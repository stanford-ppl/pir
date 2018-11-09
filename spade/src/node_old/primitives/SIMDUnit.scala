package spade
package node
import param._

case class SIMDUnit(param:SIMDParam)(implicit design:SpadeDesign) extends Module {
  import param._
  val stages = Modules("stage", stageParams.map { param => Stage(param) })

  stages.zipWithIndex.foreach { case (stage, i) =>
    // Stage Operands 
    stage.funcUnit.operands.foreach { operand =>
      operand <== stage.pipeRegs.map(_.out)
      if (i != 0) operand <== stages(i-1).pipeRegs.map(_.out)
    }
    // Stage Output 
    stage.pipeRegs.foreach { _.in <== stage.funcUnit.out }
  }

}
