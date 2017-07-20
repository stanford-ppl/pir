package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.util.misc._
import pir.exceptions._
import pir.codegen.Logger

import scala.collection.mutable._

class MemoryAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = true 
  import pirmeta._

  override lazy val stream = newStream(s"MemoryAnalyzer.log")

  def analyzeStageOperands(cu:MemoryPipeline) = {
    (cu.wtAddrStages ++ cu.rdAddrStages).foreach { st =>
      st match {
        case st:WAStage => forWrite(st) = true
        case st:RAStage => forRead(st) = true
      }
      st.fu.foreach { fu =>
        fu.operands.foreach { oprd =>
          (oprd.from.src, st) match {
            case (p:Counter, st:WAStage) => forWrite(p) = true
            case (p:Counter, st:RAStage) => forRead(p) = true
            case (p:ScalarMem, st:WAStage) => forWrite(p) = true
            case (p:ScalarMem, st:RAStage) => forRead(p) = true
            case (p, st) =>
          }
        }
      }
    }
    if (cu.sram.writePort.isConnected) {
      cu.sram.writePort.from.src match {
        case fifo:FIFO => forWrite(fifo) = true
      }
    } else {
      warn(s"${cu.sram} in $cu's writePort is not connected!")
    }
  }

  def analyzeCounters(cu:MemoryPipeline) = {
    cu.rdAddrStages.foreach { stage =>
      stage.fu.foreach { fu =>
        fu.operands.foreach { oprd => 
          oprd.from.src match {
            case ctr:Counter => forRead(ctr) = true
            case PipeReg(_, CtrPR(ctr)) => forRead(ctr) = true
            case _ =>
          }
        }
      }
    }
    if (cu.sram.readAddr.isConnected) {
      cu.sram.readAddr.from.src match {
        case ctr:Counter => forRead(ctr) = true
        case _ =>
      }
    } else {
      warn(s"${cu.sram} in $cu's readAddr is not connected!")
    }
    cu.wtAddrStages.foreach { stage =>
      stage.fu.foreach { fu =>
        fu.operands.foreach { oprd => 
          oprd.from.src match {
            case ctr:Counter => forWrite(ctr) = true
            case PipeReg(_, CtrPR(ctr)) => forWrite(ctr) = true
            case _ =>
          }
        }
      }
    }
    if (cu.sram.writeAddr.isConnected) {
      cu.sram.writeAddr.from.src match {
        case ctr:Counter => forWrite(ctr) = true
        case _ =>
      }
    } else {
      warn(s"${cu.sram} in $cu's writeAddr is not connected!")
    }
  }

  def analyzeCChain(cu:MemoryPipeline) = {
    cu.cchains.foreach { cc =>
      if (cc.counters.exists(cc => forWrite(cc))) {
        forWrite(cc) = true
        cc.counters.foreach(ctr => forWrite(ctr) = true)
      }
      if (cc.counters.exists(cc => forRead(cc))) {
        forRead(cc) = true
        cc.counters.foreach(ctr => forRead(ctr) = true)
      }
    }
  }

  def analyzeScalarBufs(cu:MemoryPipeline) = {
    cu.cchains.foreach { cc =>
      cc.counters.foreach { ctr =>
        List(ctr.min, ctr.max, ctr.step).map(_.from.src).foreach {
          case mem:ScalarMem => 
            if (forRead(ctr)) {
              forRead(mem) = true
            }
            if (forWrite(ctr)) {
              forWrite(mem) = true
            }
          case _ =>
        }
      }
    }
    if (cu.sram.writeAddr.isConnected) {
      cu.sram.writeAddr.from.src match {
        case fifo:ScalarFIFO => forWrite(fifo) = true
        case _ =>
      }
    } else {
      warn(s"${cu.sram} in $cu's writeAddr is not connected!")
    }
    if (cu.sram.readAddr.isConnected) {
      cu.sram.readAddr.from.src match {
        case fifo:ScalarFIFO => forRead(fifo) = true
        case _ =>
      }
    } else {
      warn(s"${cu.sram} in $cu's readAddr is not connected!")
    }
  }

  def setSwapCC(cu:ComputeUnit) = {
    cu.mbuffers.foreach { 
      case mem =>
        mem.consumer match {
          case cu:MemoryPipeline => swapReadCChainOf(mem) = readCChainsOf(cu).last
          case cu:ComputeUnit => swapReadCChainOf(mem) = localCChainOf(cu)
          case _ =>
        }
        mem.producer match {
          case cu:MemoryPipeline => swapWriteCChainOf(mem) = writeCChainsOf(cu).last
          case cu:ComputeUnit => swapWriteCChainOf(mem) = localCChainOf(cu)
          case _ =>
        }
    }
  }

  def copySwapCC(cu:ComputeUnit) = {
    cu match {
      case cu:MemoryPipeline if cu.sram.buffering > 0 =>
        val swapRead = cu.getCopy(swapReadCChainOf(cu.sram))
        forRead(swapRead) = true
        swapRead.counters.foreach(ctr => forRead(ctr) = true)
        val swapWrite = cu.getCopy(swapWriteCChainOf(cu.sram))
        forWrite(swapWrite) = true
        swapWrite.counters.foreach(ctr => forWrite(ctr) = true)
        analyzeScalarBufs(cu)
      case cu =>
    }
  }

  def copyAccumCC(cu:ComputeUnit) = {
    cu match {
      case cu:InnerController =>
        cu.accumRegs.foreach { acc =>
          val accumCC = localCChainOf(acc.accumParent.right.get)
          val cc = cu.getCopy(accumCC)
          accumCounterOf(acc) = cc.inner
        }
      case cu =>
    }
  }

  def analyzeAddrCalc(cu:ComputeUnit) = {
    val readCCs = cu.cchains.filter { cc => forRead(cc) }
    readCChainsOf(cu) = fillChain(cu, sortCChains(readCCs))
    val writeCCs = cu.cchains.filter { cc => forWrite(cc) }
    writeCChainsOf(cu) = fillChain(cu, sortCChains(writeCCs))
    val compCCs = cu.cchains.filter { cc => !forRead(cc) && !forWrite(cc) }
    compCChainsOf(cu) = fillChain(cu, sortCChains(compCCs))
  }

  def setPar(cu:ComputeUnit) = {
    cu.cchains.foreach { cc =>
      cc.counters.foreach { ctr => parOf(ctr) = ctr.par }
    }
    cu match {
      case cu:MemoryPipeline => 
        rparOf(cu) = cu.sram.readPort.to.head.src match {
          case o:ScalarOut => 1
          case o:VecOut => readCChainsOf(cu).head.inner.par
        }
        wparOf(cu) = cu.sram.writePort.from.src match {
          case i:ScalarFIFO => 1
          case i:VectorFIFO => writeCChainsOf(cu).head.inner.par
        }
        cu.mems.foreach { 
          case mem if forRead(mem) => 
            parOf(mem) = rparOf(cu)
          case mem if forWrite(mem) =>
            parOf(mem) = rparOf(cu)
          case mem:SRAM =>
            rparOf(mem) = rparOf(cu)
            wparOf(mem) = wparOf(cu)
        }
        cu.rdAddrStages.foreach { st => parOf(st) = 1 }
        cu.wtAddrStages.foreach { st => parOf(st) = 1 }
      case cu if isStreaming(cu) =>
        parOf(cu) = localCChainOf(cu.parent).inner.par //TODO: fix for nested streaming controller
        cu.mems.foreach { mem => parOf(mem) = parOf(cu) }
        cu.stages.foreach { st => parOf(st) = parOf(cu) }
      case cu =>
        val cc = compCChainsOf(cu).head
        if (!cu.ancestors.contains(cc.original.ctrler)) { // Addresss calculation
          parOf(cu) = 1
        } else {
          parOf(cu) = cc.inner.par
        }
        cu.mems.foreach { mem => parOf(mem) = parOf(cu) }
        cu.stages.foreach { st => parOf(st) = parOf(cu) }
    }
  }

  def swapCounter(ip:InPort, ccFrom:CounterChain, ccTo:CounterChain) = {
    ip.from.src match {
      case ctr:Counter if ccFrom.counters.contains(ctr) =>
        val idx = ccFrom.counters.indexOf(ctr)
        val nctr = ccTo.counters(idx)
        ip.disconnect
        ip.connect(nctr.out)
      case PipeReg(s, CtrPR(ctr)) if ccFrom.counters.contains(ctr) =>
        val idx = ccFrom.counters.indexOf(ctr)
        val nctr = ccTo.counters(idx)
        ip.disconnect
        ip.connect(s.ctrler.asICL.ctr(s, nctr))
      case _ =>
    }
  }

  def duplicateCChain(cu:ComputeUnit) = {
    cu.cchains.foreach { cc =>
      if (forRead(cc) && forWrite(cc)) {
        val clone = CounterChain.clone(cc)(cu, design)
        clone.setCopy(cc.original)
        forRead(cc) = false
        forWrite(clone) = false
        forRead(clone) = true
        readCChainsOf(cu) = readCChainsOf(cu).map { case `cc` => clone; case cc => cc }
        cu.srams.foreach { sram => swapCounter(sram.readAddr, cc, clone) }
        cu.stages.foreach { 
          case st if forRead(st) =>
            st.fu.foreach { fu =>
              fu.operands.foreach { oprd => swapCounter(oprd, cc, clone) }
            }
          case _ =>
        }
      }
    }
  }

  addPass(canRun=design.controlAnalyzer.hasRun(1)) {
    design.top.memCUs.foreach { cu =>
      analyzeStageOperands(cu)
      analyzeCounters(cu)
      analyzeCChain(cu)
      analyzeScalarBufs(cu)

      emitBlock(s"$cu") {
        cu.stages.foreach { st =>
          dprintln(s"$st forRead=${forRead(st)} forWrite=${forWrite(st)}")
        }
        cu.mems.foreach { mem => 
          dprintln(s"$mem forRead=${forRead(mem)} forWrite=${forWrite(mem)}")
        }
        cu.cchains.foreach { cchain =>
          cchain.counters.foreach { ctr =>
            dprintln(s"$ctr forRead=${forRead(ctr)} forWrite=${forWrite(ctr)}")
          }
          dprintln(s"$cchain forRead=${forRead(cchain)} forWrite=${forWrite(cchain)}")
        }
      }
    }

    design.top.innerCUs.foreach { cu =>
      copyAccumCC(cu) // use localCChainOf

      emitBlock(s"$cu") {
        cu.accumRegs.foreach { acc =>
          dprintln(s"accumCounterOf($acc)=${accumCounterOf(acc)}")
        }
      }
    }
  }

  addPass(canRun=design.multiBufferAnalyzer.hasRun(0), 1) {
    design.top.compUnits.foreach { cu =>
      analyzeAddrCalc(cu) // use forRead, forWrite, set readCChainsOf, writeCChainsOf, compCChainsOf
      setSwapCC(cu) // use readCChainOf, writeCChainOf, localCChainOf, set swapReadCChainOf, swapWriteCChainOf
      copySwapCC(cu) // use swapReadCChainOf, swapWriteCChainOf, set forRead, forWrite
      analyzeAddrCalc(cu) // use forRead, forWrite, set readCChainsOf, writeCChainsOf, compCChainsOf
      duplicateCChain(cu) // use forRead, forWrite, readCChainOf, set forRead, forWrite
      setPar(cu) // use forRead, forWrite, set parOf, rparOf, wparOf
      emitBlock(s"$cu") {
        cu.cchains.foreach { cchain =>
          cchain.counters.foreach { ctr =>
            dprintln(s"$ctr forRead=${forRead(ctr)} forWrite=${forWrite(ctr)}")
          }
          dprintln(s"$cchain forRead=${forRead(cchain)} forWrite=${forWrite(cchain)}")
        }
        dprintln(s"readCChains:[${readCChainsOf(cu).mkString(",")}]")
        dprintln(s"writeCChains:[${writeCChainsOf(cu).mkString(",")}]")
        dprintln(s"compCChainsOf:[${compCChainsOf(cu).mkString(",")}]")
        dprintln(s"parOf($cu)=${parOf.get(cu)}")
        dprintln(s"rparOf($cu)=${rparOf.get(cu)}")
        dprintln(s"wparOf($cu)=${wparOf.get(cu)}")
        cu.mbuffers.foreach { mem =>
          dprintln(s"swapReadCChainOf($mem) = ${swapReadCChainOf.get(mem)} buffering=${mem.buffering}")
          dprintln(s"swapWriteCChainOf($mem) = ${swapWriteCChainOf.get(mem)} buffering=${mem.buffering}")
          dprintln(s"parOf($mem)=${parOf.get(mem)}")
          dprintln(s"rparOf($mem)=${rparOf.get(mem)}")
          dprintln(s"wparOf($mem)=${wparOf.get(mem)}")
        }
      }
    }
  }

}
