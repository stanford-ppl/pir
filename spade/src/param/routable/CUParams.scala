package spade
package param

import spade.node._
import prism.enums._
import prism.node._

import SpadeConfig._
trait CUParam extends Parameter {
  // Memory
  val numSrams:Int
  val sramParam:SRAMParam
  val numControlFifos:Int
  val numScalarFifos:Int
  val numVectorFifos:Int
  val controlFifoParam:FIFOParam
  val scalarFifoParam:FIFOParam
  val vectorFifoParam:FIFOParam
  val numCtrs:Int
  val simdParam:Option[SIMDParam]

  // -----   Derived parameters
  var cu:CU = _
}
case class PCUParam (
  numControlFifos:Int=4,
  numScalarFifos:Int=6,
  numVectorFifos:Int=option[Int]("vfifo"),
  controlFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  scalarFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  vectorFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  numCtrs:Int=6,
  simdParam:Option[SIMDParam]=Some(DefaultSIMDParam(
    numStages=6, 
    ops=allOps, 
    vectorized=true, 
    numRegs=16, 
    numScalarOuts=4, 
    numVectorOuts=4
  ))
) extends CUParam {
  val numSrams:Int = 0
  val sramParam:SRAMParam = SRAMParam(0,0)
}
case class SCUParam (
  numControlFifos:Int=4,
  numScalarFifos:Int=6,
  numVectorFifos:Int=0,
  controlFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  scalarFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  vectorFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  numCtrs:Int=6,
  simdParam:Option[SIMDParam]=Some(DefaultSIMDParam(
    numStages=6, 
    ops=allOps, 
    vectorized=false, 
    numRegs=16, 
    numScalarOuts=4, 
    numVectorOuts=0
  ))
) extends CUParam {
  val numSrams:Int = 0
  val sramParam:SRAMParam = SRAMParam(0,0)
}
case class PMUParam (
  numControlFifos:Int=4,
  numScalarFifos:Int=6,
  numVectorFifos:Int=option[Int]("vfifo"),
  controlFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  scalarFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  vectorFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  sramParam:SRAMParam=SRAMParam(size=option("pmu-sram-size"),4), // 256 kB
  numCtrs:Int=6,
  simdParam:Option[SIMDParam]=Some(DefaultSIMDParam(
    numStages=4, 
    ops=fixOps ++ bitOps ++ otherOps, 
    vectorized=true, 
    numRegs=16, 
    numScalarOuts=4, 
    numVectorOuts=4
  ))
) extends CUParam {
  val numSrams:Int = 1 
}
case class SramAGParam (
  numControlFifos:Int=4,
  numScalarFifos:Int=6,
  numVectorFifos:Int=option[Int]("vfifo"),
  controlFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  scalarFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  vectorFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  numCtrs:Int=6,
  simdParam:Option[SIMDParam]=Some(DefaultSIMDParam(
    numStages=6, 
    ops=fixOps ++ bitOps ++ otherOps, 
    vectorized=true, 
    numRegs=16, 
    numScalarOuts=4, 
    numVectorOuts=4
  ))
) extends CUParam {
  val numSrams:Int = 0
  val sramParam:SRAMParam = SRAMParam(0,0)
}
case class DramAGParam (
  numControlFifos:Int=3,
  numScalarFifos:Int=4,
  numVectorFifos:Int=0,
  controlFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  scalarFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  vectorFifoParam:FIFOParam=FIFOParam(size=option("fifo-depth")),
  numCtrs:Int=6,
  simdParam:Option[SIMDParam]=Some(DefaultSIMDParam(
    numStages=8, 
    ops=fixOps ++ bitOps ++ otherOps, 
    vectorized=false, 
    numRegs=16, 
    numScalarOuts=3, 
    numVectorOuts=0
  ))
) extends CUParam {
  val numSrams:Int = 0
  val sramParam:SRAMParam = SRAMParam(0,0)
}
