package pir.node

import pir.PIR
import pir.util._
import pirc._
import pirc.exceptions._
import scala.reflect.runtime.universe._

abstract class OuterController(name:Option[String])(implicit design:PIR) extends ComputeUnit(name) {

  override def toUpdate = super.toUpdate

  override def getCopy(cchain:CounterChain)(implicit logger:Logger):CounterChain = {
    if (cchain.ctrler!=ctrler)
      throw PIRException(s"OuterController cannot make copy of other CounterChain")
    else cchain
  }

  lazy val ctrlBox:OuterCtrlBox = OuterCtrlBox()

}
