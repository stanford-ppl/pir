package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions

object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // Assume all CUs are homogenous for now

   // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 4
  private val numTTs = 2 

  def genFields(numPRs:Int, numCtrs:Int, numSRAMs:Int) = {
    val numBusIns = if (numSRAMs==0) 1 else numSRAMs
    val numScalarIns = numLanes * numBusIns 
    val numScalarOuts = numLanes 
    // Create Pipeline Regs (entire row of physicall register for all stages)
    // No overlapping between mappings
    val regs = List.tabulate(numPRs) { ir => Reg() }
    var ptr = 0

    val scalarIns = List.tabulate(numScalarIns) { is =>
      val si = ScalarIn()
      si <= regs(ptr + is)
      si
    }
    val scalarOuts = List.tabulate(numScalarOuts) { is =>
      val si = ScalarOut()
      regs(ptr + is) <= si
      si
    }
    val vecIns = List.tabulate(numBusIns) { is =>
      val outports = List.tabulate(numLanes){ i => val op = OutPort(); scalarIns(is*numBusIns + i) <= op; op }
      val b = InBus(outports)
      regs(ptr + is) <= b 
      b
    }
    val inports = List.tabulate(numLanes){ i => val ip = InPort(); ip <= scalarOuts(i); ip }
    val vecOuts = OutBus(inports)
    vecOuts <= regs(ptr)

    val ctrs = List.tabulate(numCtrs) { ic => 
      val c = Counter() 
      c.min <= scalarIns.map(_.out) :+ Const
      c.max <= scalarIns.map(_.out) :+ Const
      c.step <= scalarIns.map(_.out) :+ Const
      regs(ptr + ic) <= c.out
      c
    }
    ptr += numCtrs

    val srams = List.tabulate(numSRAMs) { is => 
      val s = SRAM()
      s.readAddr <= ctrs.map(_.out) :+ regs(ptr + is).out 
      s.writeAddr <= ctrs.map(_.out) :+ regs(ptr + is).out
      s
    } 
    ptr += numSRAMs
    srams.zipWithIndex.foreach { case (s,is) =>
      s.writePort <= List[OutPort](vecIns(is).outports(0),regs(ptr + is))
      regs(ptr + is) <= s.readPort
    }
    ptr += numSRAMs

    (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOuts, ptr)
  }

  private val rcus = List.tabulate(numRCUs) { i =>
    val numPRs = 20
    val numCtrs = 10
    val numSRAMs = 2

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOuts, ptr) = genFields(numPRs, numCtrs, numSRAMs)
    val c = ComputeUnit(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOuts)
    regs(ptr) <= c.reduce
    c
  } 

  private val mcs = List.tabulate(numTTs) { i =>
    val numPRs = 15
    val numCtrs = 10
    val numSRAMs = 0

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOuts, ptr) = genFields(numPRs, numCtrs, numSRAMs)
    val c = TileTransfer(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOuts)
    regs(ptr) <= c.reduce
    c
  }

  /* Network Constrain */ 
  rcus(0).vins(0) <= mcs(0).vout 
  rcus(0).vins(1) <= mcs(1).vout
  rcus(1).vins(0) <= mcs(0).vout 
  rcus(1).vins(1) <= mcs(1).vout
  rcus(2).vins(0) <= rcus(0).vout 
  rcus(2).vins(1) <= rcus(1).vout
  rcus(3).vins(0) <= rcus(0).vout 
  rcus(3).vins(1) <= rcus(1).vout
  
  override val computeUnits = rcus ++ mcs 

}
