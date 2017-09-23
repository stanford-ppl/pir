package pir.node

import pir._
import pirc._

abstract class OuterController(implicit design:PIR) extends ComputeUnit {

  override def toUpdate = super.toUpdate

  override def getCopy(cchain:CounterChain)(implicit logger:Logger):CounterChain = {
    if (cchain.ctrler!=ctrler)
      throw PIRException(s"OuterController cannot make copy of other CounterChain")
    else cchain
  }

  lazy val ctrlBox:OuterCtrlBox = OuterCtrlBox()

}
