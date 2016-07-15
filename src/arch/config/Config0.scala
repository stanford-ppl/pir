package dhdl.plasticine.config

import dhdl.plasticine.graph._

object Config0 extends Spade {

  // Assume all CUs are homogenous for now
  
  // Intra CU specs
  val numCU = 2
  val numMemCtrls = 2

  // Inner CU Specs
  val numLane = 4
  val numReg = 10
  val numStage = 5
  val numCtr = 6
  val numSRAM = 2
  val numInPort = numLane * 2
  val numOutPort = numLane
  
  val cus = List.tabulate(numCU) { i =>
    val regs = List.tabulate(numReg) { ir =>
      PReg()
    }
    val ctrs = List.tabulate(numCtr) { ic =>
      val c = PCounter()
      c.mapTo(regs(ic))
      c
    }
    val srams = List.tabulate(numSRAM) {is =>
      val s = PSRAM(numLane)
      s.mapTo(regs(is + numCtr))
      s
    } 
    val inports = List.tabulate(numInPort) {ip =>
      InPort()
    }
    val outports = List.tabulate(numOutPort) {ip =>
      InPort()
    }
    PComputeUnit(regs, srams, ctrs, inports, outports)
  } 

  val memCtrls = List.tabulate(numMemCtrls) { i =>
      PReg()
    }
    val ctrs = List.tabulate(numCtr) { ic =>
      val c = PCounter()
      c.mapTo(regs(ic))
      c
    }
    val srams = List.tabulate(numSRAM) {is =>
      val s = PSRAM(numLane)
      s.mapTo(regs(is + numCtr))
      s
    } 
    val inports = List.tabulate(numInPort) {ip =>
      InPort()
    }
    val outports = List.tabulate(numOutPort) {ip =>
      InPort()
    }
    PMemoryController(regs, srams, ctrs, inports, outports)
  }
}
