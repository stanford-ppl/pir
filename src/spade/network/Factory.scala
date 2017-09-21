package spade.network
                          
import spade._
import spade.node._
import spade.util._
import spade.arch._

import pirc._
import pirc.util._
import pirc.enums._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pureconfig._

// Common configuration generator 
object ConfigFactory extends Logger {

  case class PlasticineConf(
    sinUcu: Int,
    stagesUcu: Int,
    sinPcu: Int,
    soutPcu:Int,
    vinPcu: Int,
    voutPcu: Int,
    regsPcu: Int,
    comp: Int,
    sinPmu: Int,
    soutPmu:Int,
    vinPmu: Int,
    voutPmu: Int,
    regsPmu: Int,
    rw: Int,
    lanes: Int
  )

    val defaultPlasticine =  com.typesafe.config.ConfigFactory.parseString("""
plasticine {
  sin-ucu = 10
  stages-ucu = 10
  sin-pcu = 10
  sout-pcu = 10
  vin-pcu = 4
  vout-pcu = 1
  regs-pcu = 16
  comp = 10
  sin-pmu = 10
  sout-pmu = 10
  vin-pmu = 4
  vout-pmu = 1
  regs-pmu = 16
  rw = 10
  lanes = 16
}
  """)

  lazy val mergedPlasticineConf = com.typesafe.config.ConfigFactory.load().withFallback(defaultPlasticine).resolve()

  lazy val plasticineConf = loadConfig[PlasticineConf](mergedPlasticineConf, "plasticine") match {
    case Right(config) => 
      config
    case Left(failures) =>
      err(s"Unable to load plasticine config!")
  }

  def getArch(name:String) = {
    name match {
      case "SN16x13_LD" => SN16x13_LD
      case "SN16x8_LD" => SN16x8_LD
      case "SN8x8_LD" => SN8x8_LD
      case "SN4x4" => SN4x4
    }
  }

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  def forwardStages(cu:ComputeUnit) = cu match {
    //case cu:MemoryComputeUnit => cu.wastages ++ cu.rastages.headOption.map{ h => List(h) }.getOrElse(Nil)
    case cu:MemoryComputeUnit => cu.stages
    case cu:OuterComputeUnit => Nil
    case cu:ComputeUnit => List(cu.fustages.head)
  }

  /* Generate connections relates to register mapping of a cu */
  def genMapping(cu:ComputeUnit) = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    /* Register Constrain */
    // All Pipeline Registers (PipeReg) connect to its previous stage ('stage.get(reg)':Pipeline
    // Register 'reg' at stage 'stage')
    for (i <- 1 until cu.stages.size) {
      cu.regs.foreach { reg => cu.stages(i).get(reg).in <== cu.stages(i-1).get(reg).out }
    }

    // Bus input is forwarded to 1 register in empty stage
    val viRegs = cu.regs.filter(_.is(VecInReg))
    assert(cu.vins.size == cu.vbufs.size, s"cu:${cu} vins:${cu.vins.size} vbufs:${cu.vbufs.size}")
    if (cu.stages.nonEmpty) assert(cu.vbufs.size == viRegs.size)
    (cu.vbufs, viRegs).zipped.foreach { case (vbuf, reg) =>
      forwardStages(cu).foreach { s => s.get(reg).in <== vbuf.readPort }
    }

    // One to one
    val siRegs = cu.regs.filter(_.is(ScalarInReg))
    (cu.sbufs, siRegs).zipped.foreach { case (sbuf, reg) =>
      forwardStages(cu).foreach { s => s.get(reg).in <-- sbuf.readPort } // broadcast
    }

    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    (cu.ctrs, cu.regs.filter(_.is(CounterReg))).zipped.foreach { case (c, reg) => 
      forwardStages(cu).foreach { s => s.get(reg).in <== c.out }
    }
  }

  def connectDataIO(cu:Controller):Unit = {
    // Xbar
    cu.sins.foreach { sin => cu.sbufs.foreach { sbuf => sbuf.writePortMux.inputs.foreach { _<== sin.ic } } }
    // One to one
    (cu.vins, cu.vbufs).zipped.foreach { case (vi, vbuf) => vbuf.writePortMux.inputs.foreach { _ <== vi.ic } }

    cu match {
      case cu:MemoryComputeUnit =>
        cu.vouts.foreach { _.ic <== cu.sram.readPort }
        cu.souts.foreach { _.ic <== (cu.sram.readPort,0) }
      case cu:ComputeUnit =>
        val voRegs = cu.regs.filter(_.is(VecOutReg))
        //assert(cu.vouts.size == voRegs.size, s"cu:${cu} vouts:${cu.vouts.size} voRegs:${voRegs.size}")
        //(cu.vouts, voRegs).zipped.foreach { case (vo, reg) => vo.ic <== cu.stages.last.get(reg).out }
        // Xbar
        cu.vouts.foreach { vout => voRegs.foreach { reg => vout.ic <== cu.stages.last.get(reg).out } }
        // Xbar
        val soRegs = cu.regs.filter(_.is(ScalarOutReg))
        cu.souts.foreach { sout => soRegs.foreach { reg => sout.ic <== (cu.stages.last.get(reg).out, 0) } }
      case cu:MemoryController =>
      case cu:Top =>
    }

  }

  def connectSRAM(cu:ComputeUnit):Unit = {
    cu.srams.foreach { sram =>
      sram.readAddrMux.inputs.foreach { _ <== (cu.ctrs.map(_.out), 0) }// sram read/write addr can be from all counters
      sram.readAddrMux.inputs.foreach { _ <== cu.sbufs.map(_.readPort) }
      sram.writeAddrMux.inputs.foreach { _ <== (cu.ctrs.map(_.out), 0) }
      sram.writeAddrMux.inputs.foreach { _ <== cu.sbufs.map(_.readPort) }
      cu match {
        case cu:MemoryComputeUnit =>
          //cu.rastages.foreach { stage => sram.readAddrMux.inputs.foreach { _ <== (stage.fu.out, 0) } }
          //cu.wastages.foreach { stage => sram.writeAddrMux.inputs.foreach { _ <== (stage.fu.out, 0) } }
          cu.stages.foreach { stage => 
            sram.readAddrMux.inputs.foreach { _ <== (stage.funcUnit.get.out, 0) }
            sram.writeAddrMux.inputs.foreach { _ <== (stage.funcUnit.get.out, 0) }
          }
        case _ =>
      }

      sram.writePortMux.inputs.foreach { _ <== cu.vbufs.map(_.readPort) }
      cu.sbufs.foreach { sbuf => sram.writePortMux.inputs.foreach { _.sliceHead(sbuf.readPort) } }
    } 
  }

  def connectCounters(cu:ComputeUnit):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    val top = spade.top

    cu.ctrs.foreach { c => 
      c.min <== Const().out // Counter max, min, step can be constant or scalarIn(specified later)
      c.min <== cu.sbufs.map(_.readPort)
      c.max <== Const().out
      c.max <== cu.sbufs.map(_.readPort)
      c.step <== Const().out
      c.step <== cu.sbufs.map(_.readPort)
    }
    /* Chain counters together */
    for (i <- 1 until cu.ctrs.size) { cu.ctrs(i).en <== cu.ctrs(i-1).done } 
    for (i <- 0 until cu.ctrs.size by 1) { isInnerCounter(cu.ctrs(i)) = true  } // Allow group counter in chain in multiple of 2
  }

  /* Generate primitive connections within a CU */ 
  def connectData(cu:ComputeUnit):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    val top = spade.top

    /* FU Constrain  */
    cu.fustages.foreach { stage =>
      // All stage can read from any regs of its own stage, previous stage, and Const
      stage.fu.operands.foreach{ oprd =>
        oprd <-- Const().out // operand is constant
        cu.regs.foreach{ reg =>
          oprd <== stage.get(reg) // operand is from current register block
          stage.prev.foreach { oprd <== _.get(reg) }// operand is forwarded from previous register block
        }
      }
      // All stage can write to all regs of its stage
      cu.regs.foreach{ reg => stage.get(reg) <== stage.fu.out }
    }

    forwardStages(cu).foreach { stage =>
      stage.funcUnit.get.operands.foreach { oprd => 
        cu.ctrs.foreach{ oprd <== _.out }
        cu.srams.foreach { oprd <== _.readPort }
        cu.vbufs.foreach { oprd <== _.readPort }
        cu.sbufs.foreach { oprd <-- _.readPort }
      }
    }

  }

  def connectCounters(cu:Controller):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    (cu, cu.ctrlBox) match {
      case (cu:ComputeUnit, cb:InnerCtrlBox) => 
        cu.ctrs.foreach { cb.done.in <== _.done }
        cu.ctrs.filter { ctr => isInnerCounter(ctr) }.map(_.en <== cb.en.out)
      case (cu:OuterComputeUnit, cb:OuterCtrlBox) => 
        cu.ctrs.foreach { cb.done.in <== _.done }
        cu.ctrs.filter { ctr => isInnerCounter(ctr) }.map(_.en <== cb.en.out)
      case (cu:MemoryComputeUnit, cb:MemoryCtrlBox) => 
        cu.ctrs.foreach { cb.readDone.in <== _.done }
        //cu.cins.foreach { cb.readDone.in <== _.ic }
        cu.ctrs.foreach { cb.writeDone.in <== _.done }
        //cu.cins.foreach { cb.writeDone.in <== _.ic }
        cu.ctrs.filter { ctr => isInnerCounter(ctr) }.map(_.en <== cb.readEn.out)
        cu.ctrs.filter { ctr => isInnerCounter(ctr) }.map(_.en <== cb.writeEn.out)
      case (cu:MemoryController, cb:CtrlBox) =>
      case (top:Top, cb:TopCtrlBox) =>
    }
  }

  def connectUDCs(cu:Controller):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    (cu, cu.ctrlBox) match {
      case (cu:ComputeUnit, cb:InnerCtrlBox) => 
        cb.udcs.foreach { udc =>
          udc.inc <== cu.cins.map{_.ic}
          udc.dec <== cb.done.out
          udc.dec <== cb.en.out
        }
      case (cu:OuterComputeUnit, cb:OuterCtrlBox) => 
        cb.udcs.foreach { udc =>
          udc.inc <== cu.cins.map{_.ic}
          udc.dec <== cb.childrenAndTree.out
          udc.dec <== cb.done.out
        }
      case (cu:MemoryComputeUnit, cb:MemoryCtrlBox) => 
        //cb.readUDC.inc <== cb.writeDone.out
        //cb.readUDC.dec <== cb.readDone.out
      case (mc:MemoryController, cb:CtrlBox) =>
      case (top:Top, cb:TopCtrlBox) =>
    }
  }

  def connectMemoryControl(cu:Controller):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    val low = Const(false)
    (cu, cu.ctrlBox) match {
      case (cu:MemoryComputeUnit, cb:MemoryCtrlBox) => 
        cu.sbufs.foreach { buf => 
          buf.dequeueEnable <== cb.readDone.out; 
          buf.dequeueEnable <== cb.writeDone.out
          buf.dequeueEnable <== cb.readEn.out; 
          buf.dequeueEnable <== cb.writeEn.out; 
          buf.dequeueEnable <== cb.writeEnDelay.out; 
          buf.dec <== cb.readDone.out; 
          buf.dec <== cb.writeDone.out
          buf.dec <== cb.readEn.out; 
          buf.dec <== cb.writeEn.out; 
          buf.dec <== cb.writeEnDelay.out; 
          //buf.enqueueEnable <== buf.writePort.valid 
          buf.predicate <== low.out
        }
        cu.vbufs.foreach { buf => 
          //buf.enqueueEnable <== buf.writePort.valid 
          buf.dequeueEnable <== cb.writeEnDelay.out; 
          buf.dec <== cb.writeEn.out 
          buf.predicate <== low.out
        }
        cu.srams.foreach { sram => 
          sram.inc <== cb.writeDone.out
          sram.dec <== cb.readDone.out 
          sram.dequeueEnable <== cb.readDoneDelay.out 
          sram.enqueueEnable <== cb.writeDoneDelay.out
          sram.writeEn <== cb.writeEnDelay.out
          sram.readEn <== cb.readEnDelay.out
        }
        cb.writeFifoAndTree <== cu.bufs.map(_.notEmpty) :+ cu.sram.notFull
        cb.readFifoAndTree <== (cu.bufs.map(_.notEmpty) :+ cu.sram.notEmpty)
      case (cu:ComputeUnit, cb:InnerCtrlBox) => 
        cu.bufs.foreach { buf =>
          buf.dequeueEnable <== cu.ctrs.map(_.done)
          buf.dequeueEnable <== cu.cins.map(_.ic)
          buf.dequeueEnable <== cb.en.out; 
          buf.dec <== cu.ctrs.map(_.done)
          buf.dec <== cu.cins.map(_.ic)
          buf.dec <== cb.en.out; 
          //buf.enqueueEnable <== buf.writePort.valid 
          buf.predicate <== low.out 
          buf.predicate <== cb.fifoPredUnit.out
        }
      case (cu:OuterComputeUnit, cb:OuterCtrlBox) => 
        cu.bufs.foreach { buf => 
          buf.dequeueEnable <== cb.done.out
          buf.dec <== cb.done.out
          //buf.enqueueEnable <== buf.writePort.valid 
          buf.predicate <== low.out
        }
      case (mc:MemoryController, cb:MCCtrlBox) =>
        //mc.sbufs.foreach { buf => buf.enqueueEnable <== cu.cins.map(_.ic) }
        mc.sbufs.foreach { buf => 
          buf.dequeueEnable <== cb.en.out
          buf.dec <== cb.en.out
        }
        mc.vdata.dequeueEnable <== cb.running
        mc.sdata.dequeueEnable <== cb.running
        mc.vdata.dec <== cb.running
        mc.sdata.dec <== cb.running
        mc.bufs.foreach { buf => buf.predicate <== low.out }
      case (top:Top, cb:TopCtrlBox) =>
    }
  }

  def connectStageControl(cu:Controller):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    (cu, cu.ctrlBox) match {
      case (cu:MemoryComputeUnit, cb:MemoryCtrlBox) => 
        //TODO: map enable once read and write stage can be shared
        //cu.rastages.foreach { _.prs.foreach { _.en <== cb.readEn.out } }
        //cu.wastages.foreach { _.prs.foreach { _.en <== cb.writeEn.out } }
        cu.stages.foreach { _.prs.foreach { pr =>
          pr.en <== cb.writeEn.out
          pr.en <== cb.readEn.out
        } }
      case (cu:ComputeUnit, cb:InnerCtrlBox) => 
        cu.stages.foreach { _.prs.foreach { _.en <== cb.en.out } }
      case (cu:OuterComputeUnit, cb:OuterCtrlBox) => 
      case (mc:MemoryController, cb:CtrlBox) =>
      case (top:Top, cb:TopCtrlBox) =>
    }
  }

  def connectCtrlIO(cu:Controller):Unit = {
    implicit val spade:Spade = cu.spade
    val spademeta: SpadeMetadata = spade
    import spademeta._
    (cu, cu.ctrlBox) match {
      case (cu:ComputeUnit, cb:InnerCtrlBox) => 
        cu.couts.foreach { cout => 
          cout.ic <== cu.bufs.map(_.notFull)
          cout.ic <== cb.doneDelay.out
          cout.ic <== cb.enDelay.out
        }
      case (cu:OuterComputeUnit, cb:OuterCtrlBox) => 
        cu.couts.foreach { cout => 
          cout.ic <== cb.udsm.doneOut 
          cout.ic <== cb.en.out
        }
      case (cu:MemoryComputeUnit, cb:MemoryCtrlBox) => 
        cb.tokenInXbar.in <== cu.cins.map(_.ic)
        cu.couts.foreach { cout => 
          cout.ic <== cu.bufs.map(_.notFull)
          cout.ic <== cb.writeDone.out
          cout.ic <== cb.readDone.out
        }
      case (mc:MemoryController, cb:MCCtrlBox) =>
        mc.couts.foreach { cout =>
          cout.ic <== mc.bufs.map(_.notFull)
          cout.ic <== cb.rdone
          cout.ic <== cb.wdone
        }
      case (top:Top, cb:TopCtrlBox) =>
        top.cins.foreach { _.ic ==> cb.status }
        top.couts.foreach { _.ic <== cb.command}
    }
  }

  def connectCtrl(cu:Controller):Unit = {
    connectCounters(cu)
    connectUDCs(cu)
    connectMemoryControl(cu)
    connectStageControl(cu)
    connectCtrlIO(cu)
  }

  def genConnections(prt:Routable):Unit = {
    prt match {
      case prt:Top =>
        connectCtrl(prt)
      case prt:ComputeUnit => 
        connectSRAM(prt)
        connectCounters(prt)
        connectDataIO(prt)
        connectData(prt)
        genMapping(prt)
        connectCtrl(prt)
      case prt:MemoryController =>
        connectDataIO(prt)
        connectCtrl(prt)
      case prt:SwitchBox => 
        prt.connectXbars
    }
  }

}
