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
        case cu:ScalarComputeUnit => assert(cu.srams.size==0) // TileTransfer has no sram
        case cu:ComputeUnit => cu.srams(is).writePort <== reg.out
      }
    }
    // Bus output is connected to 1 register in last stage
    // TODO
    //cu.vout.voport <== cu.stages.last.prs(cu.regs(voutPtr))
    (0 until cu.numSinReg).foreach { is =>
      val sireg = cu.etstage.prs(cu.regs(sinsPtr + is)) 
      cu.ctrs.foreach { c => c.min <== sireg; c.max <== sireg ; c.step <== sireg } // Counter min, max, step can from scalarIn
      // ScalarInXbar
      //cu.sins.foreach { sin => sireg <== sin.out}
    }
    // Scalar outputs is connected to 1 register in last stage
    //cu.souts.groupBy(_.inport.map{_.src}).map { case (outBus, souts) =>
      //souts.zipWithIndex.foreach { case (so, is) =>
        //so.in <== cu.stages.last.prs(cu.regs(soutsPtr + is))
      //}
    //}
    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    cu.ctrs.zipWithIndex.foreach { case (c, ic) => 
      cu.etstage.prs(cu.regs(ctrsPtr + ic)) <== c.out
    }
    cu match {
      case cu:MemoryComputeUnit =>
        cu.ctrs.zipWithIndex.foreach { case (c, ic) => 
          (cu.wastages ++ cu.rastages).foreach(_.prs(cu.regs(ctrsPtr + ic)) <== c.out) 
        }
        cu.srams.zipWithIndex.foreach { case (s, is) => 
          (cu.wastages ++ cu.rastages).foreach(s.readAddr <== _.fu.out)
          (cu.wastages ++ cu.rastages).foreach(_.prs(cu.regs(loadsPtr + is)) <== s.readPort) // Sram read port forwarding 
        }
      case _ =>
    }
    // Sram read addr and write addr (probably don't need 1 reg per sram for write addr. Usually
    // only write to 1 sram)
    cu.srams.zipWithIndex.foreach { case (s, is) => 
      s.writeAddr <== cu.stages.last.prs(cu.regs(waPtr))
      s.writePort <== cu.stages.last.prs(cu.regs(wpPtr)) // Sram write port is connected to 1 register of last stage
      cu.regstages.headOption.foreach(_.prs(cu.regs(loadsPtr + is)) <== s.readPort) // Sram read port forwarding 
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

    cu match {
      case cu:MemoryComputeUnit =>
        cu.wastages.foreach { stage =>
          stage.fu.operands.foreach { oprd => 
            // Creating forwarding path from counter outputs to all operands of the FUs in write 
            // addr stages
            cu.ctrs.foreach{ oprd <== _.out } 
          }
          // Connect all cu.srams's write addr to writeAddr stages
          cu.srams.foreach { _.writeAddr <== stage.fu.out }
        }
        (cu.wastages ++ cu.rastages).foreach { stage =>
          stage.fu.operands.foreach { oprd =>
            // Creating forwarding path from cu.srams loads to all operands of the FUs
            cu.srams.foreach{ oprd <== _.readPort }
          }
        }
      case _ =>
    }
    cu.regstages.headOption.foreach { stage =>
      stage.fu.operands.foreach { oprd =>
        // Creating forwarding path from cu.srams loads to all operands of the FUs
        cu.srams.foreach{ oprd <== _.readPort }
      }
    }
    
  }

}
