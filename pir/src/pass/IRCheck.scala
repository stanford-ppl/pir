package pir
package pass

import pir.node._

class IRCheck(implicit compiler:PIR) extends PIRPass {
  import pirmeta._

  type N = PIRNode with Product

  override def runPass =  {
    val cus = compiler.top.collectDown[GlobalContainer]()
    checkCUIO(cus)
    checkLowering(cus)
    checkContext(cus)
    checkCounter(cus)
  }

  def checkCounter(cus:List[GlobalContainer]):Unit = {
    cus.foreach { cu =>
      cu.collectDown[Counter]().foreach { ctr =>
        assert(within[CounterChain](ctr), s"$ctr in $cu is not within CounterChain")
      }
    }
  }

  def checkContext(cus:List[GlobalContainer]):Unit = {
    if (compiler.session.hasRun[ContextInsertion]) {
      cus.foreach { cu => 
        cu.descendents.foreach {
          case mem:Memory => assert(!within[ComputeContext](mem), s"$mem in $cu is within ComputeContext")
          case n:ComputeContext =>
          case n => assert(within[ComputeContext](n), s"$n in $cu is not within ComputeContext")
        }
      }
    }
  }

  def checkCUIO(cus:List[GlobalContainer]):Unit = {
    if (compiler.session.hasRun[ControlAllocation]) {
      cus.foreach { cu => 
        checkCUIO[Input, GlobalInput](cu)
        checkCUIO[Output, GlobalOutput](cu)
      }
    }
  }

  // All cu's inputs and outputs should go through a memory
  def checkCUIO[IOType<:IO:ClassTag, SrcType:ClassTag](cu:GlobalContainer):Unit = dbgblk(s"checkCUIO($cu)") {
    cu.ios.foreach { 
      case io:IOType =>
        io.src match {
          case node:SrcType =>
          case node =>
            dbg(s"io=${qtype(io.src)}.${io}")
            io.connected.foreach { connected => 
              dbg(s"connected=${connected.src}.$connected")
            }
            err(s"$cu's global input ${io.src}.$io from ${io.connected.map(_.src)}")
        }
      case io =>
    }
  }

  def checkLowering(cus:List[GlobalContainer]):Unit = {
    if (compiler.session.hasRunAll[DeadCodeElimination]) {
      cus.foreach { cu => 
        checkLowering[ContextEnableOut](cu)
        checkLowering[ReadMem](cu)
        checkLowering[WriteMem](cu)
        checkLowering[LoadMem](cu)
        checkLowering[StoreMem](cu)
        checkLowering[DummyOp](cu)
      }
    }
  }

  def checkLowering[T<:PIRNode:ClassTag:TypeTag](cu:GlobalContainer):Unit = dbgblk(s"checkLowering($cu)") {
    val unlowered = cu.collectDown[T]()
    assert(unlowered.isEmpty, s"$cu still contains unlowered nodes: $unlowered")
  }

}

