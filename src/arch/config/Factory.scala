package pir.plasticine.config
                          
import pir._
import pir.plasticine.graph._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.codegen.Logger

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.util.enums._

// Common configuration generator 
object ConfigFactory extends Logger {
    override lazy val stream = newStream("factory.log")

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  def forwardStages(cu:ComputeUnit) = cu match {
    case cu:MemoryComputeUnit => cu.wastages :+ cu.rastages.head
    case cu:OuterComputeUnit => Nil
    case cu:ComputeUnit => cu.fustages.head :: Nil 
  }

  /* Generate connections relates to register mapping of a cu */
  def genMapping(cu:ComputeUnit)(implicit spade:Spade) = {
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
    assert(cu.vbufs.size == viRegs.size)
    (cu.vins, cu.vbufs).zipped.foreach { case (vi, vbuf) => vbuf.writePort <== vi.ic }
    (cu.vbufs, viRegs).zipped.foreach { case (vbuf, reg) =>
      forwardStages(cu).foreach { s => s.get(reg).in <== vbuf.readPort }
    }

    if (!cu.isMCU) {
      val voRegs = cu.regs.filter(_.is(VecOutReg))
      assert(cu.vouts.size == voRegs.size, s"cu:${cu} vouts:${cu.vouts.size} voRegs:${voRegs.size}")
      (cu.vouts, voRegs).zipped.foreach { case (vo, reg) => vo.ic <== cu.stages.last.get(reg).out }
    }

    val siRegs = cu.regs.filter(_.is(ScalarInReg))
    val siPerSreg = Math.ceil( cu.sins.size * 1.0 / siRegs.size ).toInt
    val gsis:List[List[GlobalInput[Word, _]]] = cu.sins.grouped(siPerSreg).toList
    (gsis, cu.sbufs).zipped.foreach { case (sis, sbuf) => sbuf.writePort <== sis.map(_.ic) }
    (cu.sbufs, siRegs).zipped.foreach { case (sbuf, reg) =>
      forwardStages(cu).foreach { s => s.get(reg).in <-- sbuf.readPort } // broadcast
    }

    val soRegs = cu.regs.filter(_.is(ScalarOutReg))
    dprintln(s"$cu souts:${cu.souts.size} soRregs:${soRegs.size}")
    val soPerSreg = Math.ceil( cu.souts.size * 1.0 / soRegs.size ).toInt
    val gsos:List[List[GlobalOutput[Word, _]]] = cu.souts.grouped(soPerSreg).toList
    (gsos, soRegs).zipped.foreach { case (sos, reg) => 
      sos.foreach { _.ic <== (cu.stages.last.get(reg).out, 0) }
    }
    cu.sbufs.foreach { sbuf =>
      // Counter min, max, step can from scalarIn
      cu.ctrs.foreach { c => c.min <== sbuf.readPort; c.max <== sbuf.readPort ; c.step <== sbuf.readPort }
    }

    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    (cu.ctrs, cu.regs.filter(_.is(CounterReg))).zipped.foreach { case (c, reg) => 
      forwardStages(cu).foreach { s => s.get(reg).in <== c.out }
    }
    cu match {
      case cu:MemoryComputeUnit =>
        cu.srams.foreach { case sram => 
          (cu.wastages ++ cu.rastages).foreach { s =>
            sram.readAddr <== (s.fu.out, 0)
            sram.writeAddr <== (s.fu.out, 0)
          }
        }
      case _ =>
    }
  }

  /* Generate primitive connections within a CU */ 
  def genConnections(cu:ComputeUnit)(implicit spade:Spade) = {
    val spademeta: SpadeMetadata = spade
    import spademeta._
    val top = spade.top

    cu.ctrs.foreach { c => 
      c.min <== Const().out // Counter max, min, step can be constant or scalarIn(specified later)
      c.max <== Const().out
      c.step <== Const().out
    }
    /* Chain counters together */
    for (i <- 1 until cu.ctrs.size) { cu.ctrs(i).en <== cu.ctrs(i-1).done } 
    for (i <- 0 until cu.ctrs.size by 1) { isInnerCounter(cu.ctrs(i)) = true  } // Allow group counter in chain in multiple of 2

    cu.srams.foreach { s =>
      s.readAddr <== (cu.ctrs.map(_.out), 0) // sram read/write addr can be from all counters
      s.writeAddr <== (cu.ctrs.map(_.out), 0)
    } 

    /* FU Constrain  */
    cu.fustages.zipWithIndex.foreach { case (stage, i) =>
      // All stage can read from any regs of its own stage, previous stage, and Const
      val preStage = cu.stages(i) // == fustages(i-1)
      stage.fu.operands.foreach{ oprd =>
        oprd <-- Const().out // operand is constant
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
        cu.srams.foreach { sram =>
          cu.wastages.foreach { stage => sram.writeAddr <== (stage.fu.out, 0) }
          cu.rastages.foreach { stage => sram.readAddr <== (stage.fu.out, 0) }
        }
      case _ =>
    }
    forwardStages(cu).foreach { stage =>
      stage.fu.operands.foreach { oprd => 
        cu.ctrs.foreach{ oprd <== _.out }
        cu.srams.foreach { oprd <== _.readPort }
        cu.vbufs.foreach { oprd <== _.readPort }
        cu.sbufs.foreach { oprd <-- _.readPort }
      }
    }
    
  }

}
