package pir.pass

import pir._
import pir.node._
import pir.util._

import pirc._
import pirc.util._

import scala.collection.mutable._

class MemoryAnalyzer(implicit design: PIR) extends Pass with Logger {
  def shouldRun = true 
  import pirmeta._

  override lazy val stream = newStream(s"MemoryAnalyzer.log")

  def markStageOperands(cu:ComputeUnit):Unit = {
    cu match {
      case cu:MemoryPipeline => markStageOperands(cu)
      case cu =>
    }
  }

  def markStageOperands(cu:MemoryPipeline):Unit = {
    (cu.wtAddrStages ++ cu.rdAddrStages).foreach { st =>
      st match {
        case st:WAStage => forWrite(st) = true
        case st:RAStage => forRead(st) = true
      }
      st.fu.foreach { fu =>
        st match {
          case st:WAStage => 
            collectIn[Counter](fu.operands).foreach { p => forWrite(p) = true }
            collectIn[ScalarMem](fu.operands).foreach { p => forWrite(p) = true }
          case st:RAStage =>
            collectIn[Counter](fu.operands).foreach { p => forRead(p) = true }
            collectIn[ScalarMem](fu.operands).foreach { p => forRead(p) = true }
        }
      }
    }
    if (!cu.sram.writePort.isConnected) {
      warn(s"${cu.sram} in $cu's writePort is not connected!")
    }

    val forWrites = Set[Node]()
    val forReads = Set[Node]()
    forWrites ++= collectIn[FIFO](cu.sram.writePort)
    forWrites ++= collectIn[Counter](cu.sram.writeAddrMux)
    forWrites ++= collectIn[FIFO](cu.sram.writeAddrMux)
    forReads  ++= collectIn[Counter](cu.sram.readAddrMux)
    forReads  ++= collectIn[FIFO](cu.sram.readAddrMux)

    forWrites.foreach { fifo => forWrite(fifo) = true }
    forReads.foreach { p => forRead(p) = true }
  }

  def markCChain(cu:MemoryPipeline):Unit = {
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

  def markLocalMem(cu:ComputeUnit):Unit = cu match {
    case cu:MemoryPipeline => markLocalMem(cu)
    case cu =>
  }

  def markLocalMem(cu:MemoryPipeline):Unit = emitBlock(s"markLocalMem($cu)") {
    cu.cchains.foreach { cc =>
      dprintln(s"cc=$cc collectIn[ScalarMem]=${collectIn[ScalarMem](cc)}")
      collectIn[ScalarMem](cc).foreach { mem =>
        if (forRead(cc)) { forRead(mem) = true }
        if (forWrite(cc)) { forWrite(mem) = true }
        dprintln(forRead.info(mem))
        dprintln(forWrite.info(mem))
      }
    }
    collectIn[FIFO](cu.sram.writePort).foreach { fifo => forWrite(fifo) = true }
    collectIn[FIFO](cu.sram.writeAddr).foreach { fifo => forWrite(fifo) = true }
    collectIn[FIFO](cu.sram.readAddr).foreach { fifo => forRead(fifo) = true }
  }

  //def setSwapCC(mem:MultiBuffer):Unit = emitBlock(s"setSwapCC($mem)") {
    //if (swapReadCChainOf.contains(mem) && swapWriteCChainOf.contains(mem)) return
    //mem.consumer match {
      //case cu:MemoryPipeline => 
        //dprintln(s"${mem.ctrler}.$mem.consumer=$cu")
        //if (mem.ctrler==cu) {
          //if (forRead(mem)) swapReadCChainOf(mem) = readCChainsOf(cu).last
          //else if (forWrite(mem)) swapReadCChainOf(mem) = writeCChainsOf(cu).last
        //} else { //Read remotely
          //val readers = mem.readPort.to.flatMap { _.src match {
            //case out:Output => out.readers.filter { _.ctrler == cu }
            //case _ => Nil
          //} }
          //dprintln(s"readers=$readers")
          //dprintln(s"collectOut[LocalMem]=${collectOut[LocalMem](readers)}")
          //val isForRead = collectOut[LocalMem](readers).exists(r => forRead(r))
          //val isForWrite = collectOut[LocalMem](readers).exists(r => forWrite(r))
          //assert(!(isForRead && isForWrite), 
            //s"${mem.ctrler}.$mem in is used both for read and for write in $cu. Don't know what is swap")
          //assert(isForRead || isForWrite, 
            //s"${mem.ctrler}.$mem is not used forRead or forWrite in $cu")
          //if (isForRead) {
            //swapReadCChainOf(mem) = readCChainsOf(cu).lastOption.getOrElse {
              //setSwapCC(cu.sram)
              //swapReadCChainOf(cu.sram)
            //}
          //} else if (isForWrite) {
            //swapReadCChainOf(mem) = writeCChainsOf(cu).lastOption.getOrElse {
              //setSwapCC(cu.sram)
              //swapWriteCChainOf(cu.sram)
            //}
          //}
        //}
      //case cu:ComputeUnit => swapReadCChainOf(mem) = localCChainOf(cu)
      //case _ =>
    //}
    //mem.producer match {
      //case cu:MemoryPipeline => swapWriteCChainOf(mem) = readCChainsOf(cu).last
      //case cu:ComputeUnit => swapWriteCChainOf(mem) = localCChainOf(cu)
      //case _ =>
    //}
    //dprintln(s"swapReadCChainOf($mem) = ${swapReadCChainOf.get(mem)} buffering=${mem.buffering}")
    //dprintln(s"swapWriteCChainOf($mem) = ${swapWriteCChainOf.get(mem)} buffering=${mem.buffering}")
  //}

  //def setSwapCC(cu:ComputeUnit):Unit = emitBlock(s"setSwapCC($cu)") {
    //cu.mbuffers.foreach { mem => setSwapCC(mem) }
  //}

  //def copySwapCC(cu:ComputeUnit) = emitBlock(s"copySwapCC($cu)") {
    //cu match {
      //case cu:MemoryPipeline =>
        //val swapRead = cu.getCopy(swapReadCChainOf(cu.sram))
        //forRead(swapRead) = true
        //swapRead.counters.foreach(ctr => forRead(ctr) = true)
        //val swapWrite = cu.getCopy(swapWriteCChainOf(cu.sram))
        //forWrite(swapWrite) = true
        //swapWrite.counters.foreach(ctr => forWrite(ctr) = true)
        //dprintln(s"swapRead=$swapRead")
        //dprintln(s"swapWrite=$swapWrite")
        //markLocalMem(cu)
      //case cu =>
    //}
  //}
  
  def analyzeNewCC(cchain:CounterChain) = {
    collectIn[OnChipMem](cchain).foreach{ mem =>
      design.accessAnalyzer.setAccess(mem)
      copySwapCC(mem)
    }
    cchain
  }

  def copySwapCC(access:ComputeUnit, topCtrler:Controller, forRead:Boolean=false, forWrite:Boolean=false):Unit = {
    access.getCopy(localCChainOf(topCtrler)).foreach { cc =>
      if (forRead) pirmeta.forRead(cc) = true
      if (forWrite) pirmeta.forWrite(cc) = true
      val (readCCs, rest) = access.cchains.partition { cc => pirmeta.forRead(cc) }
      val (writeCCs, localCCs) = rest.partition { cc => pirmeta.forWrite(cc) }
      dprintln(s"topCtrler=$topCtrler")
      dprintln(s"readCCs=${readCCs.map { cc => (cc, cc.original.ctrler) }}")
      dprintln(s"writeCCs=${writeCCs.map { cc => (cc, cc.original.ctrler)} }")
      dprintln(s"localCCs=${localCCs.map { cc => (cc, cc.original.ctrler)} }")
      var newCCs = fillChain(access, sortCChains(readCCs))._2
      newCCs ++= fillChain(access, sortCChains(writeCCs))._2
      newCCs ++= fillChain(access, sortCChains(localCCs))._2
      (cc :: newCCs).foreach(analyzeNewCC)
    }
  }

  def copySwapCC(mem:OnChipMem):Unit = {
    mem match {
      case mem:SRAM =>
        (mem.writeAddrMux.inputs).foreach { input =>
          val addrser = addrserOf(mem, input)
          mem.topCtrlMap.get(input).foreach { topCtrl =>
            copySwapCC(addrser.asCU, topCtrl, forWrite=true)
          }
        }
        (mem.readAddrMux.inputs).foreach { input =>
          val addrser = addrserOf(mem, input)
          mem.topCtrlMap.get(input).foreach { topCtrl =>
            copySwapCC(addrser.asCU, topCtrl, forRead=true)
          }
        }
      case mem:LocalMem =>
        mem.writePortMux.inputs.foreach { input =>
          val writer = collectIn[GlobalInput](input).head.from.ctrler
          mem.topCtrlMap.get(input).foreach { topCtrl =>
            copySwapCC(writer.asCU, topCtrl)
          }
        }
        mem.topCtrlMap.get(mem.readPort).foreach { topCtrl =>
          copySwapCC(mem.ctrler.asCU, topCtrl)
        }
    }
  }

  def copySwapCC(cu:ComputeUnit):Unit = emitBlock(s"copySwapCC($cu)") {
    cu.mems.foreach { mem => copySwapCC(mem) }
  }

  def copyAccumCC(cu:ComputeUnit) = emitBlock(s"copyAccumCC($cu)"){
    cu match {
      case cu:InnerController =>
        cu.accumRegs.foreach { acc =>
          val accumCC = localCChainOf(acc.accumParent.right.get)
          val cc = cu.getCopy(accumCC).map { cc => analyzeNewCC(cc) }.getOrElse(accumCC)
          accumCounterOf(acc) = cc.outer
          dprintln(s"accumCounterOf($acc)=${accumCounterOf(acc)}")
        }
      case cu =>
    }
  }

  //TODO: Fix this for multiple reader and writer
  def sortCChains(cchains:List[CounterChain]):List[CounterChain] = {
    val ancSize = cchains.map { _.original.ctrler.ancestors.size }
    cchains.sortBy { cc => cc.original.ctrler.ancestors.size }.reverse
  }

  def analyzeAddrCalc(cu:ComputeUnit) = emitBlock(s"analyzeAddrCalc($cu)"){
    readCChainsOf(cu) = {
      val readCCs = cu.cchains.filter { cc => forRead(cc) }
      val (ccs, nccs) = fillChain(cu, sortCChains(readCCs))
      nccs.foreach(analyzeNewCC)
      ccs
    }
    writeCChainsOf(cu) = {
      val writeCCs = cu.cchains.filter { cc => forWrite(cc) }
      val (ccs, nccs) = fillChain(cu, sortCChains(writeCCs))
      nccs.foreach(analyzeNewCC)
      ccs
    }
    compCChainsOf(cu) = {
      val compCCs = cu.cchains.filter { cc => !forRead(cc) && !forWrite(cc) }
      val (ccs, nccs) = fillChain(cu, sortCChains(compCCs))
      nccs.foreach(analyzeNewCC)
      ccs
    }
    dprintln(s"readCChains:[${readCChainsOf(cu).mkString(",")}]")
    dprintln(s"writeCChains:[${writeCChainsOf(cu).mkString(",")}]")
    dprintln(s"compCChains:[${compCChainsOf(cu).mkString(",")}]")
  }

  def setPar(cu:ComputeUnit) = emitBlock(s"setPar($cu)"){
    cu.cchains.foreach { cc => cc.counters.foreach { ctr => parOf(ctr) = ctr.par } }
    cu match {
      case cu:MemoryPipeline => 
        parOf(cu) = 1
        val rpars = cu.srams.flatMap { sram => 
          collectOut[GlobalOutput](sram.readPort).map { _.variable.par }
        } 
        val wpars = cu.srams.flatMap { sram => 
          collectIn[GlobalInput](sram.writePort).map { _.variable.par }
        } 
        assert(rpars.size==1, s"$cu's rpars=$rpars")
        assert(wpars.size==1, s"$cu's wpars=$wpars")
        rparOf(cu) = rpars.head 
        wparOf(cu) = wpars.head 
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
      case cu:MemoryController =>
        cu.mems.foreach { mem =>
          parOf(mem) = collectIn[GlobalInput](mem.writePort).map{_.variable.par}.head
        }
        if (cu.mctpe.isLoad) {
          parOf(cu) = cu.getBus("data").par
        } else if (cu.mctpe.isStore) {
          parOf(cu) = collectIn[GlobalInput](cu.getFifoWithName("data").head.writePort).head.variable.par
        }
      case cu =>
        val cc = localCChainOf(cu) 
        parOf(cu) = cc.inner.par
        cu.mems.foreach { mem => parOf(mem) = parOf(cu) }
        cu.stages.foreach { st => parOf(st) = parOf(cu) }
    }
    dprintln(parOf.info(cu))
    dprintln(rparOf.info(cu))
    dprintln(wparOf.info(cu))
  }

  def swapCounter(ip:Input, ccFrom:CounterChain, ccTo:CounterChain) = {
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

  def duplicateCChain(cu:ComputeUnit) = emitBlock(s"duplicateCChain($cu)") {
    cu.cchains.foreach { cc =>
      if (forRead(cc) && forWrite(cc)) {
        val clone = CounterChain.clone(cc)(cu, design)
        dprintln(s"cloning original=$cc clone=$clone")
        clone.setCopy(cc.original) //TODO: Hack
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

  addPass(canRun=design.controlAnalyzer.hasRun(0)) {
    design.top.memCUs.foreach { cu =>
      markStageOperands(cu)
      markCChain(cu)
      markLocalMem(cu)
      copySwapCC(cu) // use producerOf, consumerOf, make copy of CounterChains

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
    }

    design.top.compUnits.foreach { cu =>
      //setSwapCC(cu) // use readCChainOf, writeCChainOf, localCChainOf, set swapReadCChainOf, swapWriteCChainOf
      //copySwapCC(cu) // use swapReadCChainOf, swapWriteCChainOf, set forRead, forWrite
      analyzeAddrCalc(cu) // use forRead, forWrite, set readCChainsOf, writeCChainsOf, compCChainsOf
      markLocalMem(cu) // set forRead, forWrite
      //duplicateCChain(cu) // use forRead, forWrite, readCChainOf, set forRead, forWrite
      setPar(cu) // use forRead, forWrite, set parOf, rparOf, wparOf
      emitBlock(s"$cu") {
        cu.cchains.foreach { cchain =>
          cchain.counters.foreach { ctr =>
            dprintln(s"$ctr forRead=${forRead(ctr)} forWrite=${forWrite(ctr)}")
          }
          dprintln(s"$cchain forRead=${forRead(cchain)} forWrite=${forWrite(cchain)}")
        }
        dprintln(s"parOf($cu)=${parOf.get(cu)}")
        dprintln(s"rparOf($cu)=${rparOf.get(cu)}")
        dprintln(s"wparOf($cu)=${wparOf.get(cu)}")
        cu.mbuffers.foreach { mem =>
          dprintln(s"parOf($mem)=${parOf.get(mem)}")
          dprintln(s"rparOf($mem)=${rparOf.get(mem)}")
          dprintln(s"wparOf($mem)=${wparOf.get(mem)}")
        }
      }
    }
  }

}
