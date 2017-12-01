package pir.node

import pir._
import pir.util._
import pir.pass.ForwardRef

import pirc._
import pirc.enums._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

abstract class ComputeUnit(implicit design: PIR) extends Controller with RegisterBlock with PipelineBlock with CounterBlock{
  override val typeStr = "CU"
  import pirmeta._

  override def reset = {
    super[RegisterBlock].reset
    super[PipelineBlock].reset
    super[CounterBlock].reset
  }

}

