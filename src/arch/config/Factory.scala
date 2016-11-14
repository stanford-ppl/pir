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
  
  def genRCU(numSRAMs:Int, numCtrs:Int, numRegs:Int)(implicit spade:Spade) = {
    val cu = new ComputeUnit().numRegs(numRegs).numCtrs(numCtrs).numSRAMs(numSRAMs)
    /* Pipeline Stages */
    cu.addWAstages(List.fill(4) { WAStage(numOprds=3, cu.regs, ops) }) // Write/read addr calculation stages
    cu.addRAstages(List.fill(0) { FUStage(numOprds=3, cu.regs, ops) }) // Additional read addr only calculation stages 
    cu.addRegstages(List.fill(0) { FUStage(numOprds=3, cu.regs, ops) }) // Regular stages
    cu.addRdstages(List.fill(4) { ReduceStage(numOprds=3, cu.regs, ops)}) // Reduction stage 
    cu.addRegstages(List.fill(2) { FUStage(numOprds=3, cu.regs, ops) }) // Regular stages
    genConnections(cu)
    cu
  }

  def genTT(numSRAMs:Int, numCtrs:Int, numRegs:Int)(implicit spade:Spade) = {
    val cu = new TileTransfer().numRegs(numRegs).numCtrs(numCtrs).numSRAMs(numSRAMs)
      //.ctrlBox(numTokenOutLUTs=8, numTokenDownLUTs=8, inBandwidth=1, outBandwidth=1)
    /* Pipeline Stages */
    cu.addWAstages(List.fill(3) { WAStage(numOprds=3, cu.regs, ops) }) // Write/read addr calculation stages
    cu.addRAstages(List.fill(1) { FUStage(numOprds=3, cu.regs, ops) }) // Additional read addr only calculation stages 
    genConnections(cu)
    cu
  }

  def genMC(numCtrs:Int, numRegs:Int)(implicit spade:Spade) = {
    val cu = new MemoryController().numRegs(numRegs).numCtrs(numCtrs).numSRAMs(2)
    genConnections(cu)
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
              top.vins.foreach { _ <== s.sbs(i)(j).voutAt("N") }
              top.vouts.foreach { _ ==> s.sbs(i)(j).vinAt("N") }
            }
            if (j==0) {
              top.vins.foreach { _ <== s.sbs(i)(j).voutAt("S") }
              top.vouts.foreach { _ ==> s.sbs(i)(j).vinAt("S") }
            }
          }
        }
    }
  }

  /* Generate connections relates to register mapping of a cu */
  def genMapping(cu:ComputeUnit, vinsPtr:Int, voutPtr:Int, sinsPtr:Int, soutsPtr:Int, ctrsPtr:Int, waPtr:Int, wpPtr:Int, loadsPtr:Int, rdPtr:Int)(implicit spade:Spade) = {
    /* Register Constrain */
    // All Pipeline Registers (PipeReg) connect to its previous stage ('stage.prs(reg)':Pipeline
    // Register 'reg' at stage 'stage')
    for (i <- 1 until cu.stages.size) {
      cu.regs.foreach { reg => cu.stages(i).prs(reg).in <== cu.stages(i-1).prs(reg).out }
    }
    // Bus input is forwarded to 1 register in empty stage
    cu.vins.zipWithIndex.foreach { case (vin, is) => 
      val reg = cu.etstage.prs(cu.regs(vinsPtr + is))
      reg.in <== (vin.viport)
      // Remote write. vecIn and sram 1 to 1 mapping. Doesn't have to be the case 
      cu match {
        case cu:TileTransfer => assert(cu.srams.size==0) // TileTransfer has no sram
        case cu:MemoryController => //TODO
        case cu:ComputeUnit => cu.srams(is).writePort <== reg.out
      }
    }
    // Bus output is connected to 1 register in last stage
    cu.vout.voport <== cu.stages.last.prs(cu.regs(voutPtr))
    (0 until spade.numScalarInReg).foreach { is =>
      val sireg = cu.etstage.prs(cu.regs(sinsPtr + is)) 
      cu.ctrs.foreach { c => c.min <== sireg; c.max <== sireg ; c.step <== sireg } // Counter min, max, step can from scalarIn
      // ScalarInXbar
      cu.sins.foreach { sin => sireg <== sin.out}
    }
    // Scalar outputs is connected to 1 register in last stage
    cu.souts.groupBy(_.inport.map{_.src}).map { case (outBus, souts) =>
      souts.zipWithIndex.foreach { case (so, is) =>
        so.in <== cu.stages.last.prs(cu.regs(soutsPtr + is))
      }
    }
    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    cu.ctrs.zipWithIndex.foreach { case (c, ic) => 
      (cu.etstage :: cu.wastages ++ cu.rastages).foreach(_.prs(cu.regs(ctrsPtr + ic)) <== c.out) 
    }
    // Sram read addr and write addr (probably don't need 1 reg per sram for write addr. Usually
    // only write to 1 sram)
    cu.srams.zipWithIndex.foreach { case (s, is) => 
      (cu.wastages ++ cu.rastages).foreach(s.readAddr <== _.fu.out)
      s.writeAddr <== cu.stages.last.prs(cu.regs(waPtr))
      s.writePort <== cu.stages.last.prs(cu.regs(wpPtr)) // Sram write port is connected to 1 register of last stage
      (cu.wastages ++ cu.rastages ++ cu.regstages.headOption).foreach(_.prs(cu.regs(loadsPtr + is)) <== s.readPort) // Sram read port forwarding 
    }
    cu.rdstages.foreach( _.prs(cu.regs(rdPtr)) <== cu.reduce)
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
      }
      // Connect all cu.srams's write addr to writeAddr stages
      cu.srams.foreach { _.writeAddr <== stage.fu.out }
    }

    (cu.wastages ++ cu.rastages ++ cu.regstages.headOption).foreach { stage =>
      stage.fu.operands.foreach { oprd =>
        // Creating forwarding path from cu.srams loads to all operands of the FUs
        cu.srams.foreach{ oprd <== _.readPort }
      }
    }
    
  }

  // Generate interconnection network with switch boxes
  def genSwitchNetwork(cus:List[List[ComputeUnit]], mcs:List[MemoryController], sbs:List[List[SwitchBox]]) = {
    val numRowCUs = cus.size
    val numColCUs = cus.head.size
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU to CU (Horizontal W -> E)
        if (i!=numRowCUs-1)
          cus(i)(j).vout ==> cus(i+1)(j).vinAt("W")
        // CU to CU (Vertical S -> N)
        if (j!=numColCUs-1) {
          cus(i)(j).vout ==> cus(i)(j+1).vinAt("S")
        }
      }
    }
    for (i <- 0 until numRowCUs+1) {
      for (j <- 0 until numColCUs+1) {
        // SB to SB (Horizontal)
        if (i!=numRowCUs) {
          sbs(i)(j).voutAt("E").zip(sbs(i+1)(j).vinAt("W")).foreach{ case (o,i) => o ==> i } // W -> E
          sbs(i)(j).vinAt("E").zip(sbs(i+1)(j).voutAt("W")).foreach{ case (i,o) => i <== o } // E -> W
        }
        // SB to SB (Vertical)
        if (j!=numColCUs) {
          sbs(i)(j).voutAt("N").zip(sbs(i)(j+1).vinAt("S")).foreach{ case (o,i) => o ==> i } // S -> N
          sbs(i)(j).vinAt("N").zip(sbs(i)(j+1).voutAt("S")).foreach{ case (i,o) => i <== o } // N -> S
        }
      }
    }
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU and SB (NW <-> SE) (top left)
        cus(i)(j).vinAt("NW").zip(sbs(i)(j+1).voutAt("SE")).foreach { case (i, o) => o ==> i }
        // CU and SB (NE <-> SW) (top right)
        cus(i)(j).vout ==> sbs(i+1)(j+1).vinAt("SW")
        // CU and SB (SW <-> NE) (bottom left)
        cus(i)(j).vinAt("SW").zip(sbs(i)(j).voutAt("NE")).foreach { case (i, o) => o ==> i }
        // CU and SB (SE <-> NW) (bottom right)
        cus(i)(j).vout ==> sbs(i+1)(j).vinAt("NW")
      }
    }
    for (j <- 0 until sbs.head.size) {
      if (j<numRowCUs) {
        sbs(0)(j).voutAt("W").zip(mcs(j).vinAt("E")).foreach { case(o, i) => o ==> i}
        mcs(j).vout ==> sbs(0)(j).vinAt("W")
      }
    }
  }

  def genCtrlSwitchNetwork(cus:List[List[ComputeUnit]], mcs:List[MemoryController], csbs:List[List[SwitchBox]])(implicit spade:Spade) = {
    val top = spade.top
    val numRowCUs = cus.size
    val numColCUs = cus.head.size
    val bandWidth = 2
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU to CU (Horizontal)
        if (i!=numRowCUs-1) {
          cus(i)(j).coutAt("E").zip(cus(i+1)(j).cinAt("W")).foreach { case (o, i) => o ==> i } // W -> E 
          cus(i)(j).cinAt("E").zip(cus(i+1)(j).coutAt("W")).foreach { case (i, o) => o ==> i } // E -> W
        }
        // CU to CU (Vertical)
        if (j!=numColCUs-1) {
          cus(i)(j).coutAt("N").zip(cus(i)(j+1).cinAt("S")).foreach { case (o, i) => o ==> i } // S -> N
          cus(i)(j).cinAt("N").zip(cus(i)(j+1).coutAt("S")).foreach { case (i, o) => o ==> i } // N -> S 
        }
      }
    }
    for (i <- 0 until numRowCUs+1) {
      for (j <- 0 until numColCUs+1) {
        // SB to SB (Horizontal)
        if (i!=numRowCUs) {
          csbs(i)(j).voutAt("E").zip(csbs(i+1)(j).vinAt("W")).foreach { case (o, i) => o ==> i } // W -> E 
          csbs(i)(j).vinAt("E").zip(csbs(i+1)(j).voutAt("W")).foreach { case (i, o) => o ==> i } // E -> W
        }
        // SB to SB (Vertical)
        if (j!=numColCUs) {
          csbs(i)(j).voutAt("N").zip(csbs(i)(j+1).vinAt("S")).foreach { case (o, i) => o ==> i } // S -> N
          csbs(i)(j).vinAt("N").zip(csbs(i)(j+1).voutAt("S")).foreach { case (i, o) => o ==> i } // N -> S 
        }
        // Top to SB
        if (j==numColCUs) {
          top.cin <== csbs(i)(j).voutAt("N") // bottom up 
          top.cout ==> csbs(i)(j).vinAt("N") // top down
        }
        if (j==0) {
          top.cin <== csbs(i)(j).voutAt("S") // top down
          top.cout ==> csbs(i)(j).vinAt("S") // bottom up 
        }
      }
    }
    for (i <- 0 until numRowCUs) {
      for (j <- 0 until numColCUs) {
        // CU and SB (NW <-> SE) (top left)
        cus(i)(j).coutAt("NW").zip(csbs(i)(j+1).vinAt("SE")).foreach { case (o, i) => o ==> i }
        cus(i)(j).cinAt("NW").zip(csbs(i)(j+1).voutAt("SE")).foreach { case (i, o) => o ==> i }
        // CU and SB (NE <-> SW) (top right)
        cus(i)(j).coutAt("NE").zip(csbs(i+1)(j+1).vinAt("SW")).foreach { case (o, i) => o ==> i }
        cus(i)(j).cinAt("NE").zip(csbs(i+1)(j+1).voutAt("SW")).foreach { case (i, o) => o ==> i }
        // CU and SB (SW <-> NE) (bottom left)
        cus(i)(j).coutAt("SW").zip(csbs(i)(j).vinAt("NE")).foreach { case (o, i) => o ==> i }
        cus(i)(j).cinAt("SW").zip(csbs(i)(j).voutAt("NE")).foreach { case (i, o) => o ==> i }
        // CU and SB (SE <-> NW) (bottom right)
        cus(i)(j).coutAt("SE").zip(csbs(i+1)(j).vinAt("NW")).foreach { case (o, i) => o ==> i }
        cus(i)(j).cinAt("SE").zip(csbs(i+1)(j).voutAt("NW")).foreach { case (i, o) => o ==> i }
      }
    }
    for (j <- 0 until mcs.size) {
      // MC and SB (SE <-> NW) (bottom right)
      //mcs(j).coutAt("SE").zip(csbs(0)(j).vinAt("NW")).foreach { case (o, i) => o ==> i }
      //mcs(j).cinAt("SE").zip(csbs(0)(j).voutAt("NW")).foreach { case (i, o) => o ==> i }
      // MC and SB (E <-> W) (right)
      mcs(j).coutAt("E").zip(csbs(0)(j).vinAt("W")).foreach { case (o, i) => o ==> i } // W -> E 
      mcs(j).cinAt("E").zip(csbs(0)(j).voutAt("W")).foreach { case (i, o) => o ==> i } // E -> W
      // MC and SB (NE <-> SW) (top right)
      //mcs(j).coutAt("NE").zip(csbs(0)(j).vinAt("SW")).foreach { case (o, i) => o ==> i }
      //mcs(j).cinAt("NE").zip(csbs(0)(j).voutAt("SW")).foreach { case (i, o) => o ==> i }
    }

  }

}
