package pir.plasticine.util.typealias

import pir.plasticine.graph._

trait SpadeAlias {
  // Spade Nodes
  type PNode     = Node
  type PModule   = Module
  type PCL       = Controller
  type PCU       = ComputeUnit
  type PMCU      = MemoryComputeUnit
  type PMU       = MemoryUnit
  type PSCU      = ScalarComputeUnit
  type POCU       = OuterComputeUnit
  type PMC       = MemoryController
  type PTop      = Top
  type PRT       = Routable 
  type PReg      = pir.plasticine.graph.ArchReg
  type PPR       = PipeReg
  type PCtr      = Counter
  type PSRAM     = SRAM
  type POCM      = OnChipMem
  type PFU       = FuncUnit
  type PST       = Stage
  type PEST      = EmptyStage
  type PFUST     = FUStage
  type PWAST     = WAStage
  type PRDST     = ReduceStage
  type PSMem     = ScalarMem
  type PVMem     = VectorMem
  type PBuf      = LocalBuffer
  type PPRIM     = Primitive
  type PI[S<:PModule]       = Input[_<:PortType,S]
  type PO[S<:PModule]       = Output[_<:PortType,S]
  type PGI[S<:PModule] = GlobalInput[_<:PortType, S]
  type PGO[S<:PModule] = GlobalOutput[_<:PortType, S]
  type PIO[S<:PModule] = IO[_<:PortType,S]
  type PGIO[S<:PModule] = GlobalIO[_<:PortType,S]
  type PGrid[S<:Routable] = GridIO[_<:PortType,S]
  type PBS       = Bus
  type PIB      = Input[PBS,Routable]
  type POB      = Output[PBS,Routable]
  type PCB = CtrlBox
  type PMCB = MemoryCtrlBox
  type PICB = InnerCtrlBox
  type POCB = OuterCtrlBox
  type PTCB = TopCtrlBox
  type PMCCB = MCCtrlBox
  type PPDU = PredicateUnit
  type PLUT      = LUT
  type PEnLUT    = EnLUT
  type PTDLUT     = TokenDownLUT
  type PTOLUT    = TokenOutLUT
  type PAT       = AndTree
  type PUC       = UDCounter
  type PSB       = SwitchBox
  type PConst    = Const[_<:PortType]
  type PUDSM = UpDownSM
}
