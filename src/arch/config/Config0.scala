package pir.plasticine.config

import pir.plasticine.graph._

object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // Assume all CUs are homogenous for now

   // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val cus = List.tabulate(5) { i =>
    val numPRs = 40
    val numCtrs = 10
    val numSRAMs = 2
    val numScalarIn = numLanes * numSRAMs
    val numScalarOut = numLanes 
    // Create Pipeline Regs (entire row of physicall register for all stages)
    // No overlapping between mappings
    val regs = List.tabulate(numPRs) { ir => Reg() }
    var ptr = 0
    val ctrs = List.tabulate(numCtrs) { ic => Counter(regs(ic)) }
    ptr += numCtrs
    val srams = List.tabulate(numSRAMs) { is => SRAM(numLanes, regs(ptr + is), regs(ptr + is)) } 
    ptr += numSRAMs
    val sinRegs = List.tabulate(numScalarIn) { ip => regs(ptr + ip) }
    val soutRegs = List.tabulate(numScalarOut) { ip => regs(ptr + ip) }
    ptr += Math.max(numScalarIn, numScalarOut)
    val vinReg = regs(ptr)
    val voutReg = regs(ptr)
    ptr += 1
    val reduceReg = regs(ptr)
    ComputeUnit(regs, srams, ctrs, sinRegs, soutRegs, vinReg, voutReg, reduceReg)
  } 

  private val memCtrls = List.tabulate(4) { i =>
    val numPRs = 40
    val numCtrs = 10
    val numSRAMs = 2
    val numScalarIn = numLanes * numSRAMs
    val numScalarOut = numLanes 
    // Create Pipeline Regs (entire row of physicall register for all stages)
    // No overlapping between mappings
    val regs = List.tabulate(numPRs) { ir => Reg() }
    var ptr = 0
    val ctrs = List.tabulate(numCtrs) { ic => Counter(regs(ic)) }
    ptr += numCtrs
    val srams = List.tabulate(numSRAMs) { is => SRAM(numLanes, regs(ptr + is), regs(ptr + is)) } 
    ptr += numSRAMs
    val sinRegs = List.tabulate(numScalarIn) { ip => regs(ptr + ip) }
    val soutRegs = List.tabulate(numScalarOut) { ip => regs(ptr + ip) }
    ptr += Math.max(numScalarIn, numScalarOut)
    val vinReg = regs(ptr)
    val voutReg = regs(ptr)
    ptr += 1
    val reduceReg = regs(ptr)
    MemoryController(regs, srams, ctrs, sinRegs, soutRegs, vinReg, voutReg, reduceReg)
  }

  override val computeUnits = cus ++ memCtrls 

}
