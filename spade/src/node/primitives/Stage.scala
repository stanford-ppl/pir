package spade
package node
import param._

case class PipeReg(param:PipeRegParam)(implicit design:SpadeDesign) extends Module {
  val in = Input[Vector](s"in")
  val out = Output[Vector](s"out")
}
case class FuncUnit(param:FuncUnitParam)(implicit design:SpadeDesign) extends Module {
  val operands = Inputs[Vector](s"operand", param.numOperands)
  val out = Output[Vector]("out")
}

case class Stage(param:StageParam)(implicit design:SpadeDesign) extends Module {
  import param._
  val funcUnit = Module(FuncUnit(param.funcUnitParam), "funcUnit")
  lazy val pipeRegs = Modules("pipeReg",param.pipeRegParams.map { param => PipeReg(param) })
}
