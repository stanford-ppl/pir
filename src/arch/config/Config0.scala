package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.graph.enums._

// 4 cu + 2 tt

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus
object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 2
  private val numTTs = 2 

  private val numArgIns = numLanes  // need to be multiple of numLanes
  private val numArgOuts = numLanes // need to be multiple of numLanes 

  // Top level controller ~= Host
  override val top = Top(numLanes, numArgIns, numArgOuts)

  def genRCU(numLanes:Int, numSRAMs:Int, numCtrs:Int, numRegs:Int) = {
    val cu = new ComputeUnit(numLanes, numSRAMs).numRegs(numRegs).numCtrs(numCtrs).numSRAMs(numSRAMs)
      .ctrlBox(numCtrs, numCtrs)
    /* Pipeline Stages */
    cu.wastages = List.fill(3) { WAStage(numOprds=2, cu.regs, ops) } // Write/read addr calculation stages
    cu.rastages = List.fill(1) { FUStage(numOprds=2, cu.regs, ops) } // Additional read addr only calculation stages 
    cu.regstages = List.fill(1) { FUStage(numOprds=2, cu.regs, ops) } // Regular stages
    cu.rdstages = List.fill(4) { ReduceStage(numOprds=2, cu.regs, ops) } // Reduction stage 

    genConnections(cu)
    genMapping(cu)
    cu
  }
  override val rcus = List.tabulate(numRCUs) { i =>
    genRCU(numLanes, numSRAMs = 2, numCtrs = 8, numRegs = 20)
  } 

  def genTT(numLanes:Int, numSRAMs:Int, numCtrs:Int, numRegs:Int) = {
    val cu = new TileTransfer(numLanes, 1).numRegs(numRegs).numCtrs(numCtrs).numSRAMs(numSRAMs)
      .ctrlBox(numCtrs, numCtrs)
    /* Pipeline Stages */
    cu.wastages = List.fill(3) { WAStage(numOprds=2, cu.regs, ops) } // Write/read addr calculation stages
    cu.rastages = List.fill(1) { FUStage(numOprds=2, cu.regs, ops) } // Additional read addr only calculation stages 

    genConnections(cu)
    genMapping(cu)
    cu
  }

  override val ttcus = List.tabulate(numTTs) { i =>
    genTT(numLanes, numSRAMs = 0, numCtrs = 4, numRegs = 20)
  }

  override val sbs = Nil

  /* Network Constrain */ 
  rcus(0).vins(0) <== ttcus(0).vout 
  rcus(0).vins(1) <== ttcus(1).vout
  rcus(1).vins(0) <== rcus(0).vout 
  rcus(1).vins(1) <== rcus(0).vout

  /* Connnect all ArgIns to scalarIns of all CUs and all ArgOuts to souts of all CUs*/
  cus.foreach { cu =>
    top.vouts.foreach { aib =>
      cu.vins.foreach { vin =>
        vin <== aib
      }
    }
    top.vins.foreach { aob => 
      aob <== cu.vout
    }
  }

  /* Generate connections relates to register mapping of a cu */
  def genMapping(cu:ComputeUnit) = {
    /* Register Constrain */
    var ptr = 0
    // All Pipeline Registers (PipeReg) connect to its previous stage ('stage.prs(reg)':Pipeline
    // Register 'reg' at stage 'stage')
    for (i <- 1 until cu.stages.size) {
      cu.regs.foreach { reg => cu.stages(i).prs(reg).in <== cu.stages(i-1).prs(reg).out }
    }
    // Bus input is forwarded to 1 register in empty stage
    cu.vins.zipWithIndex.foreach { case (vin, is) => 
      val reg = cu.etstage.prs(cu.regs(ptr + is))
      reg.in <== (vin.viport)
      // Remote write. vecIn and sram 1 to 1 mapping. Doesn't have to be the case 
      cu.srams.zipWithIndex.foreach { case (s,is) => s.writePort <== reg.out } 
    }
    // Bus output is connected to 1 register in last stage
    cu.vout.voport <== cu.stages.last.prs(cu.regs(ptr))
    cu.sins.zipWithIndex.foreach { case (si, is) => 
      val sireg = cu.etstage.prs(cu.regs(ptr + is)) 
      sireg <== si.out // ScalarIn is connected to 1 register in empty stage
      cu.ctrs.foreach { c => c.min <== sireg; c.max <== sireg ; c.step <== sireg } // Counter min, max, step can from scalarIn
    }
    // Scalar outputs is connected to 1 register in last stage
    cu.souts.zipWithIndex.foreach { case (so, is) => so.in <== cu.stages.last.prs(cu.regs(ptr + is)) }
    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    cu.ctrs.zipWithIndex.foreach { case (c, ic) => 
      (cu.etstage :: cu.wastages ++ cu.rastages).foreach(_.prs(cu.regs(ptr + ic)) <== c.out) 
    }
    ptr += cu.ctrs.size 
    // Sram read addr and write addr (probably don't need 1 reg per sram for write addr. Usually
    // only write to 1 sram)
    cu.srams.zipWithIndex.foreach { case (s, is) => 
      (cu.wastages ++ cu.rastages).foreach(s.readAddr <== _.fu.out)
      s.writeAddr <== cu.stages.last.prs(cu.regs(ptr + is))
    }
    ptr += cu.srams.size 
    cu.srams.zipWithIndex.foreach { case (s, is) => 
      s.writePort <== cu.stages.last.prs(cu.regs(ptr + is)) // Sram write port is connected to 1 register of last stage
      (cu.wastages ++ cu.rastages ++ cu.regstages.headOption).foreach(_.prs(cu.regs(ptr + is)) <== s.readPort) // Sram read port forwarding 
    }
    ptr += cu.srams.size 
    cu.rdstages.foreach( _.prs(cu.regs(ptr)) <== cu.reduce)
  }

  /* Generate primitive connections within a CU */ 
  def genConnections(cu:ComputeUnit) = {

    cu.ctrs.foreach { c => 
      c.min <== const.out // Counter max, min, step can be constant or scalarIn(specified later)
      c.max <== const.out
      c.step <== const.out
    }
    /* Chain counters together */
    for (i <- 1 until cu.ctrs.size) { cu.ctrs(i).en <== cu.ctrs(i-1).done } 
    for (i <- 0 until cu.ctrs.size by 2) { cu.ctrs(i).en <== top.clk } // Allow group counter in chain in multiple of 2

    cu.srams.foreach { s =>
      s.readAddr <== cu.ctrs.map(_.out) // sram read/write addr can be from all counters
      s.writeAddr <== cu.ctrs.map(_.out)
    } 

    /* FU Constrain  */
    cu.fustages.zipWithIndex.foreach { case (stage, i) =>
      // All stage can read from any regs of its own stage, previous stage, and Const
      val preStage = cu.stages(i) // == fustages(i-1)
      stage.fu.operands.foreach{ oprd =>
        oprd <== const.out // operand is constant
        cu.regs.foreach{ reg =>
          oprd <== stage.prs(reg) // operand is from current register block
          oprd <== preStage.prs(reg) // operand is forwarded from previous register block
        }
      }
      // All stage can write to all regs of its stage
      cu.regs.foreach{ reg => stage.prs(reg) <== stage.fu.out }
    }

    cu.wastages.foreach { stage =>
      stage.fu.operands.foreach { oprd => 
        // Creating forwarding path from counter outputs to all operands of the FUs in write 
        // addr stages
        cu.ctrs.foreach{ oprd <== _.out } 
        // Creating forwarding path from cu.srams loads to all operands of the FUs
        cu.srams.foreach{ oprd <== _.readPort }
      }
      // Connect all cu.srams's write addr to writeAddr stages
      cu.srams.foreach { _.writeAddr <== stage.fu.out }
    }
    
  }


}
