package dhdl.plasticine.config

import dhdl.plasticine.graph._

trait Spade {
  val numCU:Int

  val numLane:Int
  val numReg:Int
  val numStage:Int

  val cus:List[PComputeUnit]

  val memCtrls:List[PMemoryController]
}
