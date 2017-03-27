package pir.plasticine.util.typealias

import pir.plasticine.graph._

trait SpadeAlias {
  // Spade Nodes
  type PNode     = Node
  type PModule   = Module
  type PCL       = Controller
  type PCU       = ComputeUnit
  type PMCU      = MemoryComputeUnit
  type PSCU      = ScalarComputeUnit
  type POCU       = OuterComputeUnit
  type PMC       = MemoryController
  type PTop      = Top
  type PNE       = NetworkElement
  type PGIO[+NE<:PNE] = GridIO[NE]
  type PReg      = pir.plasticine.graph.ArchReg
  type PPR       = PipeReg
  type PCtr      = Counter
  type PSRAM     = pir.plasticine.graph.SRAM
  type PFU       = FuncUnit
  type PST       = Stage
  type PEST      = EmptyStage
  type PFUST     = FUStage
  type PWAST     = WAStage
  type PRDST     = ReduceStage
  type PSI       = ScalarIn
  type PSO       = ScalarOut
  type PI       = Input[_<:PortType,_<:PModule]
  type PO       = Output[_<:PortType,_<:PModule]
  type PIO[S<:PModule] = IO[_<:PortType,S]
  type PBS       = Bus
  type PIB      = Input[PBS,PNE]
  type POB      = Output[PBS,PNE]
  type PCB = CtrlBox
  type PLUT      = LUT
  type PEnLUT    = EnLUT
  type PTDLUT     = TokenDownLUT
  type PTOLUT    = TokenOutLUT
  type PUC       = UDCounter
  type PSB       = SwitchBox
  type PConst    = Const
}
