package pir.pass

import pir.node._

class IRCheck(implicit compiler:PIR) extends PIRPass {
  import pirmeta._

  type N = PIRNode with Product
  def shouldRun = true

  override def runPass(runner:RunPass[_]) =  {
    import runner._
    val prevRuns = runner.prevRuns
    prevRuns.foreach(_.pass.check(runner))
    val prePasses = prevRuns.map { _.pass }
    val cus = compiler.top.collectDown[GlobalContainer]()
    val accessLowerHasRun = session.hasRun[AccessLowering]
    val ctrlAllocHasRun = session.hasRun[ControlAllocation] 
    val deadCodeHasAllRun = session.hasRunAll[DeadCodeElimination] 
    cus.foreach { cu => 
      if (accessLowerHasRun) {
        if (!ctrlAllocHasRun) {
          checkCUIO[Input, LocalStore](cu)
        } else {
          checkCUIO[Input, GlobalInput](cu)
          checkCUIO[Output, GlobalOutput](cu)
        }
      }
      if (deadCodeHasAllRun) {
        checkLowering[ContextEnableOut](cu)
        checkLowering[ReadMem](cu)
        checkLowering[WriteMem](cu)
        checkLowering[LoadMem](cu)
        checkLowering[StoreMem](cu)
      }
    }
  }

  // All cu's inputs and outputs should go through a memory
  def checkCUIO[IOType<:IO:ClassTag, SrcType:ClassTag](cu:GlobalContainer) = dbgblk(s"checkCUIO($cu)") {
    cu.ios.foreach { 
      case io:IOType =>
        io.src match {
          case node:SrcType =>
          case node =>
            dbg(s"io=${qtype(io.src)}.${io}")
            io.connected.foreach { connected => 
              dbg(s"connected=${connected.src}.$connected")
            }
            err(s"$cu's global input ${io.src}.$io")
        }
      case io =>
    }
  }

  def checkLowering[T<:PIRNode:ClassTag:TypeTag](cu:GlobalContainer) = dbgblk(s"checkLowering($cu)") {
    val unlowered = cu.collectDown[T]()
    assert(unlowered.isEmpty, s"$cu still contains unlowered nodes: $unlowered")
  }

}

