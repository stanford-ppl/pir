package pir.util.typealias

import pir.graph._

trait PIRAlias {
  // PIR Nodes 
  type Node  = pir.graph.Node
  type CL    = Controller
  type ICL   = InnerController
  type OCL   = OuterController
  type CU    = ComputeUnit
  type SC    = StreamController
  type SP    = StreamPipeline
  type MP    = MemoryPipeline
  type MC    = MemoryController
  type PRIM  = Primitive
  type Reg   = pir.graph.Reg
  type PR    = PipeReg
  type OCM   = OnChipMem
  type SRAM  = pir.graph.SRAM
  type SOR   = SRAMOnRead
  type SOW   = SRAMOnWrite
  type FOR   = FIFOOnRead
  type FOW   = FIFOOnWrite
  type SMem  = ScalarMem
  type SFIFO = ScalarFIFO
  type VFIFO = VectorFIFO
  type CC    = CounterChain
  type Ctr   = Counter
  type FU    = FuncUnit
  type ST    = Stage
  type EST   = EmptyStage
  type WAST  = WAStage
  type RDST  = ReduceStage
  type ACST  = AccumStage
  type SI    = ScalarIn
  type SO    = ScalarOut
  type VI    = VecIn
  type DVI   = DummyVecIn
  type VO    = VecOut
  type DVO   = DummyVecOut
  type I     = Input
  type PT    = Port
  type IP    = InPort
  type CIP   = CtrlInPort
  type OP    = OutPort
  type COP   = CtrlOutPort
  type CB    = CtrlBox
  type SCB   = StageCtrlBox
  type ICB   = InnerCtrlBox
  type OCB   = OuterCtrlBox
  type TCB   = TopCtrlBox
  type LUT   = pir.graph.LUT
  type TOLUT = TokenOutLUT
  type TDLUT = TokenDownLUT
  type EnLUT = pir.graph.EnLUT
  type AT    = AndTree
  type SBAT  = SiblingAndTree
  type UC    = UDCounter
  type Const = pir.graph.Const[_<:AnyVal]
  type Top   = pir.graph.Top
}
