package pir.graph

import pir.Design
import pir.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.codegen.Logger

abstract class OuterController(name:Option[String])(implicit design:Design) extends ComputeUnit(name) {

  override def toUpdate = super.toUpdate

  override def getCopy(cchain:CounterChain)(implicit logger:Logger):CounterChain = {
    if (cchain.ctrler!=ctrler)
      throw PIRException(s"OuterController cannot make copy of other CounterChain")
    else cchain
  }

  lazy val ctrlBox:OuterCtrlBox = OuterCtrlBox()

}
