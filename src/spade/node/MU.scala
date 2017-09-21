package spade.node

import spade._
import spade.util._

import pirc.exceptions._
import pirc.enums._
import pirc.util._

//case class MemoryUnitParam(
  //numCtrs:Int = 4,
  //sbufSize:Int = 16,
  //vbufSize:Int = 16
//) extends ComputeUnitParam() {
  //val numSRAMs = 1
  //val sramSize = 128
  //val numUDCs = 0
  //val numRegs = 0
  //override val numLanes = 1

  ///* Parameters */
  //def config(cu:MemoryUnit)(implicit spade:Spade) = {
    //cu.numScalarBufs(3)
    //cu.numVecBufs(cu.vins.size)
    //cu.genConnections
  //}
//}

//class MemoryUnit(override val param:MemoryUnitParam=MemoryUnitParam())(implicit spade:Spade) 
  //extends ComputeUnit(param) {
  //override val typeStr = "mu"
  //import spademeta._
  //import param._

  //override lazy val ctrlBox:MUCtrlBox = new MUCtrlBox()

  //def sram = srams.head
  //override def config = param.config(this)
//}

