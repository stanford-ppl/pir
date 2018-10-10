package spade
package param

import scala.collection.mutable

case class PipeRegParam(
  colors:mutable.Set[RegColor]=mutable.Set.empty
) extends Parameter {
  def color(c:RegColor) = ()//colors += c
  def is(c:RegColor) = colors.contains(c)
}
case class FuncUnitParam(
  numOperands:Int = 3
) extends Parameter

case class StageParam(
  funcUnitParam:FuncUnitParam=FuncUnitParam(),
  reductionIdx:Option[Int] // If the stage can perform reduction, which stage of the reduction can it perform. 
) extends Parameter {
  lazy val simdParam = collectOut[SIMDParam]().head
  lazy val pipeRegParams = simdParam.pipeRegParams
}
