package spade
package node
import param._

abstract class CU(
  val param:CUParam, 
  val bundles:List[Bundle[_<:PinType]]
)(implicit design:SpadeDesign) extends Terminal {
  import param._

  /* ------- SUBMODULES --------------*/

  //val counters = Modules("ctr", numCtrs, Counter())
  //val srams = Modules("sram", numSrams, SRAM(sramParam))
  //val controlFifos = Modules("cfifo", numControlFifos, FIFO[Bit](controlFifoParam))
  //val scalarFifos  = Modules("sfifo", numScalarFifos, FIFO[Word](scalarFifoParam))
  //val vectorFifos  = Modules("vfifo", numVectorFifos, FIFO[Vector](vectorFifoParam))

  //val simd = simdParam.map { param => Module(SIMDUnit(param)) }

  //// SIMD input connection
  //simd.foreach { simd =>
    //simd.stages.headOption.foreach { head =>
      //head.funcUnit.operands.foreach { operand =>
        //operand <== srams.map(_.readPort)
        //operand <== vectorFifos.map(_.readPort)
        //operand <== scalarFifos.map(_.readPort.broadCast[Vector])
      //}
      //head.pipeRegs.foreach { pipeReg =>
        //pipeReg.param.colors.foreach {
          //case VecInReg => pipeReg.in <== vectorFifos.map(_.readPort)
          //case ScalarInReg => pipeReg.in <== scalarFifos.map(_.readPort.broadCast[Vector])
          //case CounterReg => pipeReg.in <== counters.map(_.out)
          //case _ =>
        //}
      //}
    //}
    ////SIMD output connection
    //simd.stages.lastOption.foreach { last =>
      //last.pipeRegs.foreach { pipeReg =>
        //pipeReg.param.colors.foreach {
          //case VecOutReg =>
            //bundle[Vector].foreach { _.outputs.foreach { _.ic <== pipeReg.out } }
          //case ScalarOutReg =>
            //bundle[Word].foreach { _.outputs.foreach { _.ic <== pipeReg.out.slice[Word](0) } }
          //case _ =>
        //}
      //}
    //}
  //}
  ////// Memory Connection
  //bundle[Vector].foreach { vio =>
    //(vio.inputs.zip(vectorFifos)).foreach { case (input, fifo) => fifo.writePort <== input.ic }
  //}
  //bundle[Word].foreach { sio =>
    //scalarFifos.foreach { fifo => fifo.writePort <== sio.inputs.map(_.ic) }
  //}
  //bundle[Bit].foreach { cio =>
    //controlFifos.foreach { fifo => fifo.writePort <== cio.inputs.map(_.ic) }
  //}
  ////TODO SRAM. multiple writer

}

case class PCU(override val param:PCUParam, override val bundles:List[Bundle[_<:PinType]])(implicit design:SpadeDesign) extends CU(param, bundles) 
case class PMU(override val param:PMUParam, override val bundles:List[Bundle[_<:PinType]])(implicit design:SpadeDesign) extends CU(param, bundles) 
case class SCU(override val param:SCUParam, override val bundles:List[Bundle[_<:PinType]])(implicit design:SpadeDesign) extends CU(param, bundles) 
case class SramAG(override val param:SramAGParam, override val bundles:List[Bundle[_<:PinType]])(implicit design:SpadeDesign) extends CU(param, bundles) 
case class DramAG(override val param:DramAGParam, override val bundles:List[Bundle[_<:PinType]])(implicit design:SpadeDesign) extends CU(param, bundles) 
