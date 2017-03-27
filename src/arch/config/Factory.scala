package pir.plasticine.config
                          
import pir._
import pir.plasticine.graph._
import pir.plasticine.main._
import pir.plasticine.util._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.util.enums._

// Common configuration generator 
object ConfigFactory {

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  def forwardStages(cu:ComputeUnit) = cu match {
    case cu:MemoryComputeUnit => cu.wastages :+ cu.rastages.head
    case cu:ComputeUnit => cu.fustages.head :: Nil 
  }

  /* Generate connections relates to register mapping of a cu */
  def genMapping(cu:ComputeUnit, sinsPtr:Int, soutsPtr:Int, ctrsPtr:Int, waPtr:Int, wpPtr:Int, loadsPtr:Int, rdPtr:Int)(implicit spade:Spade) = {
    val spademeta: SpadeMetadata = spade
    import spademeta._
    /* Register Constrain */
    // All Pipeline Registers (PipeReg) connect to its previous stage ('stage.get(reg)':Pipeline
    // Register 'reg' at stage 'stage')
    for (i <- 1 until cu.stages.size) {
      cu.regs.foreach { reg => cu.stages(i).get(reg).in <== cu.stages(i-1).get(reg).out }
    }
    // Bus input is forwarded to 1 register in empty stage
    assert(cu.vins.size == cu.vectorIns.size)
    (cu.vins, cu.vectorIns).zipped.foreach { case (vi, vin) => busesOf(vin) += vi }
    (cu.vectorIns, cu.regs.filter(_.is(VecInReg))).zipped.foreach { case (vin, reg) =>
      forwardStages(cu).foreach { s => s.get(reg).in <== vin.out }
    }
    assert(cu.vouts.size == cu.vectorOuts.size)
    (cu.vouts, cu.vectorOuts).zipped.foreach { case (vo, vout) => busesOf(vout) += vo }
    (cu.vectorOuts, cu.regs.filter(_.is(VecOutReg))).zipped.foreach { case (vout, reg) =>
      cu.stages.last.get(reg).in <== vout.out
    }
    val siPerSin = Math.ceil( cu.sins.size * 1.0 / cu.scalarIns.size ).toInt
    val gsis:List[List[Input[Bus, _]]] = cu.sins.grouped(siPerSin).toList
    (gsis, cu.scalarIns).zipped.foreach { case (sis, sin) => busesOf(sin) ++= sis }
    (cu.scalarIns, cu.regs.filter(_.is(ScalarInReg))).zipped.foreach { case (sin, reg) =>
      forwardStages(cu).foreach { s => s.get(reg).in <== sin.out }
    }
    val soPerSout = Math.ceil( cu.souts.size * 1.0 / cu.scalarOuts.size ).toInt
    val gsos:List[List[Output[Bus, _]]] = cu.souts.grouped(soPerSout).toList
    (gsos, cu.scalarOuts).zipped.foreach { case (sos, sout) => busesOf(sout) ++= sos }
    (cu.scalarOuts, cu.regs.filter(_.is(ScalarOutReg))).zipped.foreach { case (sout, reg) =>
      cu.stages.last.get(reg).in <== sout.out
    }
    cu.scalarIns.foreach { sin =>
      // Counter min, max, step can from scalarIn
      cu.ctrs.foreach { c => c.min <== sin.out; c.max <== sin.out ; c.step <== sin.out }
    }
    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    (cu.ctrs, cu.regs.filter(_.is(CounterReg))).zipped.foreach { case (c, reg) => 
      forwardStages(cu).foreach { s => s.get(reg) <== c.out }
    }
    cu match {
      case cu:MemoryComputeUnit =>
        cu.srams.foreach { case sram => 
          (cu.wastages ++ cu.rastages).foreach { s =>
            sram.readAddr <== s.fu.out
            sram.writeAddr <== s.fu.out
          }
        }
      case _ =>
    }
  }

  /* Generate primitive connections within a CU */ 
  def genConnections(cu:ComputeUnit)(implicit spade:Spade) = {
    val top = spade.top

    cu.ctrs.foreach { c => 
      c.min <== Const().out // Counter max, min, step can be constant or scalarIn(specified later)
      c.max <== Const().out
      c.step <== Const().out
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
        oprd <== Const().out // operand is constant
        cu.regs.foreach{ reg =>
          oprd <== stage.get(reg) // operand is from current register block
          oprd <== preStage.get(reg) // operand is forwarded from previous register block
        }
      }
      // All stage can write to all regs of its stage
      cu.regs.foreach{ reg => stage.get(reg) <== stage.fu.out }
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
