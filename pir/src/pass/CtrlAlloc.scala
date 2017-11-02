package pir.pass

import pir._
import pir.node._
import pir.util._

import pirc._
import pirc.util._
import pirc.enums._

import scala.collection.mutable._

class CtrlAlloc(implicit design: PIR) extends Pass with Logger {
  import pirmeta._

  def shouldRun = PIRConfig.ctrl

  override lazy val stream = newStream(s"CtrlAlloc.log")

  addPass(canRun=design.memoryAnalyzer.hasRun, runCount=1) {
    design.top.ctrlers.foreach { ctrler =>
      connectDone(ctrler)
      setPredicate(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      connectEnable(ctrler)
      connectMemoryControl(ctrler)
    }
    topoSort(design.top).reverse.foreach { ctrler =>
      connectSibling(ctrler)
      connectHeads(ctrler)
      connectLasts(ctrler)
    }
    //connectLastsRec(design.top)
  }

  def OCU_MAX_CIN = {
    val ocu = arch.ocus.head
    ocu.cins.size
  }

  def setPredicate(ctrler:Controller) = {
    setAccumPredicate(ctrler)
    setFifoPredicate(ctrler)
  }

  def setFifoPredicate(ctrler:Controller) = {
    ctrler match {
      case cu:Pipeline =>
        val lookUp = Map[Reg, FIFO]()
        def addPredicate(mem:FIFO, sel:Input) = {
          sel.from.src match {
            case PipeReg(stage, reg) => lookUp += reg -> mem
            case x => throw PIRException(s"Not supported format for FIFO Predicate in ${ctrler} sel=$x")
          }
        }
        cu.stages.reverseIterator.foreach { stage =>
          stage.fu.foreach { fu =>
            fu.op match {
              case MuxOp =>
                val sel::data = fu.operands
                data.foreach { 
                  _.from.src match {
                    case PipeReg(stage, LoadPR(mem:FIFO)) => addPredicate(mem, sel)
                    case mem:FIFO => addPredicate(mem, sel)
                    case _ =>
                  }
                }
              case _ =>
            }
            fu.out.to.foreach {
              _.src match {
                case PipeReg(stage, reg) if lookUp.contains(reg) =>
                  var const:Option[Int] = None
                  var counter:Option[Counter] = None
                  fu.operands.foreach {
                    _.from.src match {
                      case Const(c:Int) => const = Some(c)
                      case PipeReg(stage, CtrPR(ctr)) => counter = Some(ctr)
                      case ctr:Counter => counter = Some(ctr)
                      case x => throw PIRException(s"Not supported operand $x for FIFO predication in ${ctrler}")
                    }
                  }
                  if (const.isEmpty || counter.isEmpty) {
                    throw PIRException(s"Not supported operand for FIFO predication in ${ctrler} const=$const counter=$counter")
                  } else {
                    fu.op match {
                      case op:FixOp if fixCompOps.contains(op) => 
                        val predUnit = cu.ctrlBox.setFifoPredicate(counter.get, op, const.get)
                        val fifo = lookUp(reg)
                        fifo.predicate.connect(predUnit.out)
                      case op => throw PIRException(s"Unsupported op for FIFO predication $op")
                    }
                  }
                case _ =>
              }
            }
          }
        }
      case _ =>
    }
  }

  def setAccumPredicate(cu:Controller) = {
    (cu, cu.ctrlBox) match {
      case (cu:InnerController, cb:InnerCtrlBox) =>
        assert(cu.accumRegs.size<=1, s"Assume $cu can only have a single accumulator at the moment")
        cu.accumRegs.headOption match {
          case None => //out.v := false
          case Some(acc) => 
            val ctr = accumCounterOf(acc)
            cb.setAccumPredicate(ctr, FixEql, 0)
        }
      case (cu, cb) =>
    }
  }

  /*
   * Use local copy if existed. Otherwise route the done
   * */
  //def getDone(current:ComputeUnit, cchain:CounterChain) = {
    //if (current.containsCopy(cchain)) {
      //val lcchain = current.getCC(cchain)
      //current.ctrlBox match {
        //case cb:StageCtrlBox => current.getCC(lcchain).outer.done
        //case cb:MemCtrlBox if (readCChainsOf(cb.ctrler).last == lcchain) => cb.readDone.out
        //case cb:MemCtrlBox if (writeCChainsOf(cb.ctrler).last == lcchain) => cb.writeDone.out
      //}
    //} else {
      //cchain.ctrler.ctrlBox match {
        //case cb:StageCtrlBox => cb.doneOut
        //case cb:MemCtrlBox if (readCChainsOf(cb.ctrler).last == cchain) => cb.readDone.out
        //case cb:MemCtrlBox if (writeCChainsOf(cb.ctrler).last == cchain) => cb.writeDone.out
      //}
    //}
  //}

  // EnqueueEnable signal in waddrser
  def getEnqueueEnable(mem:OnChipMem, waddrser:Controller) = {
    mem match {
      case mem:MultiBuffer =>
        producerOf.get((mem, waddrser)).fold{
          waddrser match {
            case top:Top => top.ctrlBox.command
            case cu:ComputeUnit => localCChainOf(waddrser).outer.done
          }
        } { producer =>
          waddrser.asCU.getCC(localCChainOf(producer)).outer.done
        }
      case mem:FIFO =>
        waddrser.ctrlBox match {
          case cb:MCCtrlBox => cb.running
          case cb:MemCtrlBox => cb.readEn.out
          case cb:StageCtrlBox => cb.en.out
        }
    }
  }

  // DequeueEnable signal in raddrser
  def getDequeueEnable(mem:OnChipMem, raddrser:Controller) = {
    mem match {
      case mem:MultiBuffer =>
        consumerOf.get((mem, raddrser)).fold {
          localCChainOf(raddrser).outer.done
        } { consumer =>
          raddrser.asCU.getCC(localCChainOf(consumer)).outer.done
        }
      case mem:FIFO =>
        raddrser.ctrlBox match {
          case cb:MemCtrlBox if forRead(mem) => cb.readEn.out
          case cb:MemCtrlBox if forWrite(mem) => cb.writeEn.out
          case cb:StageCtrlBox => cb.en.out
        }
    }
  }

  // - write to FIFO, enqueuEnable is from valid along data
  // - write to Local MultiBuffer, enqueueEnable is valid along data, valid is from producer's done
  //   copy in writer 
  // - write to remote MultiBuffer, enqueueEnable is sent through explicit control FIFO, set to be
  //   producer's done copy in writer
  // - read from FIFO, dequeueEnable is from local controller enable
  // - read from Local MultiBuffer, dequeuenable is from local copy of consumer's done
  // - read from Remote Multibuffer, dequeueable is from controlFIFO sent from reader, set to be
  //   copy of consumer's done in reader controller.
 
  def connectGlobal(out:Output, in:Input, retime:Boolean=false):Option[FIFO] = emitBlock(s"connectGlobal($out, $in, $retime)"){
    val writer = out.ctrler
    val reader = in.ctrler
    val gout = writer.newOut[Control](out)
    val bus = gout.variable
    val gin = reader.newIn(bus)
    gout.in.connect(out)
    if (retime) {
      val fifo = reader.getRetimingFIFO(gin)
      readersOf(fifo) = List(reader)
      writersOf(fifo) = List(writer)
      in.connect(fifo.readPort)
      Some(fifo)
    } else {
      in.connect(gin.out)
      None
    }
  }

  def connectWrite(mem:OnChipMem, waddrser:Controller):Unit = emitBlock(s"connectWrite($mem, $waddrser)"){
    val enqueueEnable = getEnqueueEnable(mem, waddrser)
    mem match {
      case mem:SRAM => // Route through control network
        val fifo = connectGlobal(enqueueEnable, mem.enqueueEnable, retime=true).get
        forWrite(fifo) = true
        connectWrite(fifo, waddrser)
        connectRead(fifo, mem.ctrler)
      case mem => // FIFO, Scalar/Control MultiBuffer: Route alone data path
        collectIn[GlobalInput](mem.writePortMux).map{ in =>
          val out = in.from
          out.valid.connect(enqueueEnable)
        }
        mem.enqueueEnable.connect(mem.writePortMux.valid)
    }
  }

  def connectRead(mem:OnChipMem, raddrser:Controller):Unit = emitBlock(s"connectRead($mem, $raddrser)"){
    val dequeueEnable = getDequeueEnable(mem, raddrser)
    mem match {
      case mem:RemoteMem => // Route through control network
        val fifo = connectGlobal(dequeueEnable, mem.dequeueEnable, retime=true).get
        forRead(fifo) = true
        connectWrite(fifo, raddrser)
        connectRead(fifo, mem.ctrler)
      case mem:LocalMem => // Locally connected
        mem.dequeueEnable.connect(dequeueEnable)
    }
  }

  def connectMemoryControl(ctrler:Controller) = emitBlock(s"connectMemoryControl($ctrler)"){
    ctrler.mems.foreach { mem =>
      waddrserOf(mem).foreach { waddrser => connectWrite(mem, waddrser) }
      raddrserOf(mem).foreach { raddrser => connectRead(mem, raddrser) }
    }
  }

  def connectHeads(ctrler:Controller) = emitBlock(s"connectHeads($ctrler)") {
    //val heads = ctrler.children.filter{_.isHead}
    val heads = ctrler.children.filter { case c:OuterController => true; case c:InnerController => c.isHead }
    dprintln(s"$ctrler heads:[${heads.mkString(",")}]")
    heads.foreach { head =>
      val enOut = ctrler.ctrlBox match {
        case pcb:OuterCtrlBox => pcb.enOut
        case pcb:TopCtrlBox => pcb.command
      }
      val hcb = head.ctrlBox.asInstanceOf[StageCtrlBox]
      val tk = hcb.tokenBuffer(ctrler)
      connectGlobal(enOut, tk.inc)
      tk.dec.connect(hcb.done.out)
      hcb.siblingAndTree.addInput(tk.out)
    }
  }

  def connectLast(parent:Controller, last:Controller) = {
    (parent, last, parent.ctrlBox, last.ctrlBox) match {
      case (parent:Top, last:ComputeUnit, pcb:TopCtrlBox, ccb:StageCtrlBox) =>
        connectGlobal(ccb.doneOut, pcb.status)
      case (parent:OuterController, last:ComputeUnit, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        connectGlobal(ccb.doneOut, tk.inc)
        tk.dec.connect(pcb.childrenAndTree.out)
        pcb.childrenAndTree.addInput(tk.out)
    }
  }

  def connectLasts(parent:Controller, lasts:List[Controller]):Unit = emitBlock(s"connectLasts(parent=$parent)") {
    dprintln(s"lasts=$lasts")
    if (!lasts.isEmpty) {
      dprintln(s"OCU_MAX_CIN=$OCU_MAX_CIN parent.cins=${parent.cins.size}")
      parent.cins.foreach { cin =>
        dprintln(s"parent.cin=${cin.from}")
      }
      val lastGroups = lasts.grouped(OCU_MAX_CIN - parent.cins.size).toList
      val midParents = lastGroups.map { lasts =>
        val midParent = if (lastGroups.size==1) parent else {
          dprintln(s"parent=$parent lastGroups:$lastGroups")
          val clone = parent.cloneType("collector")
          localCChainOf(clone) = CounterChain.dummy(clone.asCU, design)
          isHead(clone) = false 
          isLast(clone) = true
          isStreaming(clone) = isStreaming(lasts.head)
          isPipelining(clone) = isPipelining(lasts.head)
          isTailCollector(clone) = true
          parOf(clone) = parOf(parent)
          ancestorsOf(clone) = clone :: ancestorsOf(parent)
          descendentsOf(clone) = List(clone)
          clone.asCU.parent(parent)
          connectDone(clone)
          connectEnable(clone)
          clone
        }
        dprintln(s"$parent midParent:$midParent lasts:[${lasts.mkString(",")}]")
        lasts.foreach { last =>
          connectLast(midParent, last)
        }
        midParent
      }
      if (midParents.size>1) connectLasts(parent, midParents)
    }
  }

  def connectLasts(ctrler:Controller):Unit = {
    val lasts = ctrler.children.filter{_.isLast}
    connectLasts(ctrler, lasts)
  }

  def connectLastsRec(ctrler:Controller):Unit = {
    val lasts = ctrler.children.filter{_.isLast}
    connectLasts(ctrler, lasts)

    val seqs = ctrler.children.filter{ child => child.isSeq && !lasts.contains(child) }
    seqs.foreach(connectLasts)
    lasts.foreach(connectLastsRec)
  }

  def connectToken(consumer:Controller, tk:(Any, Output)):Unit = {
    val cb = consumer.ctrlBox.asInstanceOf[StageCtrlBox]
    val (dep, token) = tk
    val tb = cb.tokenBuffer(dep)
    tb.inc.connect(token)
    tb.dec.connect(cb.done.out)
    cb.siblingAndTree.addInput(tb.out)
  }

  def connectTokens(consumer:Controller, tokens:List[(Any, Output)]):Unit = {
    val tokenGroups = if (tokens.size==0) Nil else tokens.grouped(OCU_MAX_CIN - consumer.cins.size).toList
    val midConsumers = tokenGroups.map { tokens =>
      val midConsumer = if (tokenGroups.size==1) consumer else {
        val clone = consumer.cloneType("splitter")
        localCChainOf(clone) = CounterChain.dummy(clone.asCU, design)
        isHead(clone) = false
        isLast(clone) = false
        isStreaming(clone) = isStreaming(consumer)
        isPipelining(clone) = isPipelining(consumer)
        isHeadSplitter(clone) = true
        parOf(clone) = parOf(consumer)
        val _ :: ancestors = ancestorsOf(consumer)
        ancestorsOf(clone) = clone :: ancestors
        descendentsOf(clone) = List(clone)
        clone.asCU.parent(consumer.asCU.parent)
        connectDone(clone)
        connectEnable(clone)
        clone
      }
      tokens.foreach { token =>
        connectToken(midConsumer, token)
      }
      midConsumer
    }
    if (midConsumers.size>1) 
      connectTokens(consumer, midConsumers.map(cm => (cm, cm.ctrlBox.asInstanceOf[StageCtrlBox].doneOut)))
  }

  def connectSibling(ctrler:Controller) = emitBlock(s"connectSibling($ctrler)"){
    // Forward dependency
    // - FIFO.notEmpty + Mbuffer.valid
    (ctrler, ctrler.ctrlBox) match {
      case (cu:MemoryPipeline, cb:MemCtrlBox) =>
        cu.lmems.foreach {
          case mem if (forRead(mem)) => cb.readFifoAndTree.addInput(mem.notEmpty)
          case mem if (forWrite(mem)) => cb.writeFifoAndTree.addInput(mem.notEmpty)
        }
        cb.readFifoAndTree.addInput(cu.sram.notEmpty)
        cb.writeFifoAndTree.addInput(cu.sram.notFull)
      case (cu:ComputeUnit, cb:InnerCtrlBox) =>
        cu.lmems.foreach { mem => cb.fifoAndTree.addInput(mem.notEmpty) }
      case (cu:MemoryController, cb:MCCtrlBox) =>
        cu.lmems.foreach { mem => cb.fifoAndTree.addInput(mem.notEmpty) }
      case _ =>
    }
    // - Token 
    //(ctrler, ctrler.ctrlBox) match {
      //case (cu:ComputeUnit, cb:OuterCtrlBox) if isStreaming(cu) =>
      //case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) =>
        //// Token
        //val tokens = cu.trueConsumed.flatMap { mem =>
          //(mem, mem.producer) match {
            //case (mem:SRAM, producer:ComputeUnit) => None
              //// SRAM no need for token because handled by FIFONotEmpty
            //case (mem, producer:ComputeUnit) =>
              //Some(mem, getDone(cu, swapWriteCChainOf(mem))) // should always from remote
            //case (mem, producer:Top) => None // No synchronization needed
          //}
        //}
        //connectTokens(cu, tokens)
      //case _ =>
    //}
    // Backward pressure
    // - FIFO.notFull
    //ctrler.mems.foreach { 
      //case mem if backPressureOf(mem) =>
        //mem.writers.foreach { writer =>
          //val andTree = writer.ctrlBox match {
            //case cb:InnerCtrlBox => Some(cb.tokenInAndTree)
            //case cb:MemCtrlBox => Some(cb.tokenInAndTree)
            //case _ => None
          //}
          //andTree.foreach { at => connectGlobal(mem.notFull, at.addInput) }
        //}
      //case mem =>
    //}
    
    // Optimization: only take one notFull from fifos from the same reader
    dprintln(s"$ctrler.writtenMems=${ctrler.writtenMems}")
    ctrler.writtenMems.groupBy(_.ctrler).foreach { case (memCtrler, mems) =>
      val andTree = ctrler.ctrlBox.ctrlBox match {
        case cb:InnerCtrlBox => Some(cb.tokenInAndTree)
        case cb:MemCtrlBox => Some(cb.tokenInAndTree)
        case _ => None
      }
      andTree.foreach { at => 
        val (fifos, mbuffers) =  mems.partition { case mem:FIFO => true; case _ => false}
        (mbuffers ++ fifos.headOption).foreach { mem =>
          connectGlobal(mem.notFull, at.addInput)
        }
      }
    }
    // - Credit
    //(ctrler, ctrler.ctrlBox) match {
      //case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) => 
        //cu.trueProduced.foreach { mem =>
          //mem.consumer match {
            //case consumer:ComputeUnit if mem.buffering != 1 & mem.buffering < cu.parent.length =>
              //val inc = consumer.ctrlBox match {
                //case cmcb:MemCtrlBox if forRead(mem) => cmcb.readDone.out
                //case cmcb:MemCtrlBox if forWrite(mem) => cmcb.readDone.out
                //case cmcb:StageCtrlBox => cmcb.doneOut
              //}
              //val cd = cb.creditBuffer(mem, mem.buffering)
              //cd.inc.connect(inc)
              //cd.dec.connect(cb.done.out)
              //cb.siblingAndTree.addInput(cd.out)
            //case consumer =>
          //}
        //}
      //case (cu, cb) =>
    //}
  }

  def chainCChain(cchains:List[CounterChain]) = {
    val chained = ListBuffer[CounterChain]()
    cchains.zipWithIndex.foreach { case (cc, i) =>
      if (i!=0) {
        cc.inner.en.connect(cchains(i-1).outer.done)
      }
    }
  }

  def connectEnable(ctrler:Controller) = emitBlock(s"connectEnable($ctrler)"){
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        //readCChainsOf(ctrler).headOption.foreach { _.inner.en.connect(cb.readEn.out) }
        //writeCChainsOf(ctrler).headOption.foreach { _.inner.en.connect(cb.writeEn.out) }
        //chainCChain(readCChainsOf(ctrler))
        //chainCChain(writeCChainsOf(ctrler))
      case (ctlrer:MemoryController, cb) =>
      case (ctrler:ComputeUnit, cb:StageCtrlBox) if isHeadSplitter(ctrler) | isTailCollector(ctrler) =>
        localCChainOf(ctrler).inner.en.connect(cb.en.out)
        chainCChain(sortCChains(ctrler.cchains))
      case (ctrler:InnerController, cb:InnerCtrlBox) =>
        localCChainOf(ctrler).inner.en.connect(cb.en.out)
        chainCChain(sortCChains(ctrler.cchains))
      case (ctrler:OuterController, cb:OuterCtrlBox) =>
        localCChainOf(ctrler).inner.en.connect(cb.en.out)
        chainCChain(sortCChains(ctrler.cchains))
      case (ctrler, cb) =>
    }
  }

  def connectDone(ctrler:Controller) = emitBlock(s"connectDone($ctrler)"){
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        //TODO HACK for case when readCC and writeCC are the same set of counter chain. There will be two copy of the
        //swapReadCChain in the same controller. the duplicate's original is also swapReadCChain.
        //However CU.getCC will only return the original copy 
        //val readCC = ctrler.cchains.filter { cc => cc.original == swapReadCChainOf(ctrler.sram) && forRead(cc) }.head
        //val readDone = readCC.outer.done
        //cb.readDone.in.connect(readDone)
        //val writeDone = ctrler.getCC(swapWriteCChainOf(ctrler.sram)).outer.done
        //cb.writeDone.in.connect(writeDone)
      case (ctlrer:MemoryController, cb) =>
      case (ctrler:ComputeUnit, cb:StageCtrlBox) =>
        cb.done.in.connect(localCChainOf(ctrler).outer.done)
      case (ctrler, cb) =>
    }
  }

  override def finPass = {
    design.top.compUnits.foreach {
      case cu:MemoryController =>
      case cu:MemoryPipeline =>
      case cu => assert(cu.cchains.nonEmpty, s"$cu's cchain is empty")
    }
    //design.top.compUnits.foreach { cu =>
      //cu match {
        //case cu:OuterController =>
          //cu.cchains.foreach{ cc =>
            //if (cc.inner.en.isConnected || cc.outer.done.isConnected)
              //throw PIRException(s"Outer controller shouldn't connect inner most counter enable or outer most counter done signals ${cu}")
          //}
        //case cu:InnerController =>
          //cu.cchains.flatMap{ _.counters }.foreach { ctr =>
            //if (!ctr.en.isConnected) 
              //throw PIRException(s"${ctr}'s en in ${ctr.cchain} in ${ctr.ctrler} is not connected")
          //}
      //}
    //}
    super.finPass
    close
  }
}
