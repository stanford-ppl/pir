package pir.plasticine.config
                          
import pir._
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.graph.enums._

// Common configuration generator 
object ConfigFactory extends ImplicitConversion {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  def genRCU(numLanes:Int, numSRAMs:Int, numCtrs:Int, numRegs:Int)(implicit spade:Spade) = {
    val cu = new ComputeUnit(numLanes, numSRAMs).numRegs(numRegs).numCtrs(numCtrs).numSRAMs(numSRAMs)
      .ctrlBox(8, 4, 4)
    /* Pipeline Stages */
    cu.wastages = List.fill(3) { WAStage(numOprds=2, cu.regs, ops) } // Write/read addr calculation stages
    cu.rastages = List.fill(1) { FUStage(numOprds=2, cu.regs, ops) } // Additional read addr only calculation stages 
    cu.regstages = List.fill(1) { FUStage(numOprds=2, cu.regs, ops) } // Regular stages
    cu.rdstages = List.fill(4) { ReduceStage(numOprds=2, cu.regs, ops) } // Reduction stage 

    genConnections(cu)
    genMapping(cu)
    cu
  }

  def genTT(numLanes:Int, numSRAMs:Int, numCtrs:Int, numRegs:Int)(implicit spade:Spade) = {
    val cu = new TileTransfer(numLanes, 1).numRegs(numRegs).numCtrs(numCtrs).numSRAMs(numSRAMs)
      .ctrlBox(8, 4, 4)
    /* Pipeline Stages */
    cu.wastages = List.fill(3) { WAStage(numOprds=2, cu.regs, ops) } // Write/read addr calculation stages
    cu.rastages = List.fill(1) { FUStage(numOprds=2, cu.regs, ops) } // Additional read addr only calculation stages 

    genConnections(cu)
    genMapping(cu)
    cu
  }

  /* Connnect all ArgIns to scalarIns of all CUs and all ArgOuts to souts of all CUs*/
  def genArgIOConnection(implicit spade:Spade) = {
    val top = spade.top
    spade match {
      case s:PointToPointNetwork =>
        val cus = spade.cus
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
      case s:SwitchNetwork =>
        for (i <- 0 until s.sbs.size) {
          for (j <- 0 until s.sbs.head.size) {
            if (j==s.sbs.head.size-1) {
              top.vins.foreach { aob =>
                aob <== s.sbs(i)(j).vouts(2)
              }
              top.vouts.foreach { aib =>
                s.sbs(i)(j).vins(4) <== aib
              }
            }
            if (j==0) {
              top.vins.foreach { aob =>
                aob <== s.sbs(i)(j).vouts(0)
              }
              top.vouts.foreach { aib =>
                s.sbs(i)(j).vins(0) <== aib 
              }
            }
          }
        }
    }
  }

  /* Generate connections relates to register mapping of a cu */
  def genMapping(cu:ComputeUnit)(implicit spade:Spade) = {
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
      cu match {
        case cu:TileTransfer => assert(cu.srams.size==0) // TileTransfer has no sram
        case cu:ComputeUnit => cu.srams(is).writePort <== reg.out
      }
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
  def genConnections(cu:ComputeUnit)(implicit spade:Spade) = {
    val top = spade.top
    val const = spade.const

    cu.ctrs.foreach { c => 
      c.min <== const.out // Counter max, min, step can be constant or scalarIn(specified later)
      c.max <== const.out
      c.step <== const.out
    }
    /* Chain counters together */
    for (i <- 1 until cu.ctrs.size) { cu.ctrs(i).en <== cu.ctrs(i-1).done } 
    for (i <- 0 until cu.ctrs.size by 1) { cu.ctrs(i).en <== top.clk } // Allow group counter in chain in multiple of 2

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

  // Generate interconnection network with switch boxes
  def genSwitchNetwork(cus:List[List[ComputeUnit]], sbs:List[List[SwitchBox]]) = {
    val numRowCUs = cus.size
    val numColCUs = cus.head.size
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU to CU (Horizontal)
        if (i!=numRowCUs-1)
          cus(i+1)(j).vins(0) <== cus(i)(j).vout
        // CU to CU (Vertical)
        if (j!=numColCUs-1)
          cus(i)(j+1).vins(2) <== cus(i)(j).vout
      }
    }
    for (i <- 0 until numRowCUs+1) {
      for (j <- 0 until numColCUs+1) {
        // SB to SB (Horizontal)
        if (i!=numRowCUs) {
          sbs(i+1)(j).vins(2) <== sbs(i)(j).vouts(1) // Left to right
          sbs(i)(j).vins(5) <== sbs(i+1)(j).vouts(4)
        }
        // SB to SB (Vertical)
        if (j!=numColCUs) {
          sbs(i)(j+1).vins(0) <== sbs(i)(j).vouts(2)
          sbs(i)(j).vins(4) <== sbs(i)(j+1).vouts(0)
        }
      }
    }
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // SB to CU (NW -> SE)
        cus(i)(j).vins(1) <== sbs(i)(j).vouts(3)
        // SB to CU (SW -> NE)
        cus(i)(j).vins(3) <== sbs(i)(j+1).vouts(5)
        // CU to SB (SW -> NE)
        sbs(i+1)(j).vins(3) <== cus(i)(j).vout
        // CU to SB (NW -> SE)
        sbs(i+1)(j+1).vins(1) <== cus(i)(j).vout
      }
    }
  }

  def genCtrlSwitchNetwork(cus:List[List[ComputeUnit]], ttcus:List[TileTransfer], csbs:List[List[SwitchBox]])(implicit spade:Spade) = {
    val top = spade.top
    val numRowCUs = cus.size
    val numColCUs = cus.head.size
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU to CU (Horizontal)
        if (i!=numRowCUs-1) {
          cus(i)(j).couts(6) ==> cus(i+1)(j).cins(2) // left to right
          cus(i+1)(j).couts(2) ==> cus(i)(j).cins(6) // right to left
        }
        // CU to CU (Vertical)
        if (j!=numColCUs-1) {
          cus(i)(j).couts(0) ==> cus(i)(j+1).cins(4) // bottom up 
          cus(i)(j+1).couts(4) ==> cus(i)(j).cins(0) // top down 
        }
      }
    }
    for (i <- 0 until numRowCUs+1) {
      for (j <- 0 until numColCUs+1) {
        // SB to SB (Horizontal)
        if (i!=numRowCUs) {
          csbs(i)(j).vouts(6) ==> csbs(i+1)(j).vins(2) // Left to right 
          csbs(i+1)(j).vouts(2) ==> csbs(i)(j).vins(6) // Right to left
        }
        // SB to SB (Vertical)
        if (j!=numColCUs) {
          csbs(i)(j).vouts(0) ==> csbs(i)(j+1).vins(4) // bottom up 
          csbs(i)(j+1).vouts(4) ==> csbs(i)(j).vins(0) // top down
        }
        if (j==numColCUs) {
          // Top down
          csbs(i)(j).vouts(0) ==> top.cin // bottom up 
          top.cout ==> csbs(i)(j).vins(0) // top down
        }
        if (j==0) {
          csbs(i)(j).vouts(4) ==> top.cin // top down
          top.cout ==> csbs(i)(j).vins(4) // bottom up 
        }
      }
    }
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // SB and CU (NW <-> SE)
        csbs(i+1)(j).vouts(1) ==> cus(i)(j).cins(5)
        cus(i)(j).couts(5) ==> csbs(i+1)(j).vins(1)
        // SB and CU (SE <-> NW)
        csbs(i)(j+1).vouts(5) ==> cus(i)(j).cins(1)
        cus(i)(j).couts(1) ==> csbs(i)(j+1).vins(5)
        // SB and CU (SW <-> NE)
        csbs(i)(j).vouts(7) ==> cus(i)(j).cins(3)
        cus(i)(j).couts(3) ==> csbs(i)(j).vins(7)
        // SB and CU (NW <-> SE)
        csbs(i+1)(j+1).vouts(3) ==> cus(i)(j).cins(7)
        cus(i)(j).couts(7) ==> csbs(i+1)(j+1).vins(3)
      }
    }
    for (i <- 0 until ttcus.size) {
      ttcus(i).couts(5) ==> csbs(0)(i).vins(1)
      csbs(0)(i).vouts(1) ==> ttcus(i).cins(5)
      ttcus(i).couts(6) ==> csbs(0)(i).vins(2)
      csbs(0)(i).vouts(2) ==> ttcus(i).cins(6)
      ttcus(i).couts(7) ==> csbs(0)(i).vins(3)
      csbs(0)(i).vouts(3) ==> ttcus(i).cins(7)
    }

  }

}
