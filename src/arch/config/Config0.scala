package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.ListBuffer

object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // Assume all CUs are homogenous for now

   // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 4
  private val numTTs = 2 
  private val numArgIns = numLanes // per bus
  private val numArgOuts = numLanes // per bus 

  private val numArgInBuses = 1 
  private val numArgOutBuses = 1 

  val argInBuses = List.fill(numArgInBuses) { OutBus(numLanes) }
  val argOutBuses = List.fill (numArgOutBuses) { InBus(numLanes) }
  val argIns = List.tabulate(numArgInBuses, numArgIns) { case (ib, ia) =>
    ScalarOut(argInBuses(ib).inports(ia))
  }.flatten
  val argOuts = List.tabulate(numArgOutBuses, numArgOuts) { case (ib, ia) =>
    ScalarIn(argOutBuses(ib).outports(ia))
  }.flatten
  override val top = Top(argIns, argOuts, argInBuses, argOutBuses)

  def genFields(numPRs:Int, numCtrs:Int, numSRAMs:Int) = {
    val numBusIns = if (numSRAMs==0) 1 else numSRAMs
    val numScalarIns = numLanes // Number of scalarIn per InBus 
    val numScalarOuts = numLanes 
    // Create Pipeline Regs (entire row of physicall register for all stages)
    // No overlapping between mappings
    val regs = List.tabulate(numPRs) { ir => PipeReg() }
    var ptr = 0

    val vecIns = List.tabulate(numBusIns) { is =>
      val ib = InBus(numLanes)
      regs(ptr + is) <= ib
      ib
    }
    val vecOut = {
      val ob = OutBus(numLanes)
      ob <= regs(ptr)
      ob
    } 
    val scalarIns = List.tabulate(numBusIns, numScalarIns) { case (ib, is) => 
        val si = ScalarIn(vecIns(ib).outports(is))
        regs(ptr + is) <= si
        si
    }.flatten
    val scalarOuts = List.tabulate(numScalarOuts) { is =>
      val so = ScalarOut(vecOut.inports(is))
      so <= regs(ptr + is)
      so
    }

    val ctrs = List.tabulate(numCtrs) { ic => 
      val c = Counter() 
      val sis = scalarIns.map(_.out)
      c.min <= sis :+ Const
      c.max <= sis :+ Const
      c.step <= sis :+ Const
      regs(ptr + ic) <= c.out
      c
    }
    for (i <- 1 until numCtrs) { // Chain counters together
      ctrs(i).en <= ctrs(i-1).sat
    } 
    for (i <- 0 until numCtrs by 2) {
      ctrs(i).en <= top.clk
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

    (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, ptr)
  }

  override val rcus = List.tabulate(numRCUs) { i =>
    val numPRs = 20
    val numCtrs = 10
    val numSRAMs = 2

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, ptr) = genFields(numPRs, numCtrs, numSRAMs)
    val c = ComputeUnit(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut)
    regs(ptr) <= c.reduce
    c
  } 

  override val ttcus = List.tabulate(numTTs) { i =>
    val numPRs = 15
    val numCtrs = 10
    val numSRAMs = 0

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, ptr) = genFields(numPRs, numCtrs, numSRAMs)
    val c = TileTransfer(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut)
    regs(ptr) <= c.reduce
    c
  }
  
  /* Network Constrain */ 
  rcus(0).vins(0) <= ttcus(0).vout 
  rcus(0).vins(1) <= ttcus(1).vout
  rcus(1).vins(0) <= ttcus(0).vout 
  rcus(1).vins(1) <= ttcus(1).vout
  rcus(2).vins(0) <= rcus(0).vout 
  rcus(2).vins(1) <= rcus(1).vout
  rcus(3).vins(0) <= rcus(0).vout 
  rcus(3).vins(1) <= rcus(1).vout

  /* Connnect all ArgIns to scalarIns of all CUs and all ArgOuts to scalarOuts of all CUs*/
  (rcus ++ ttcus).foreach { cu =>
    top.vouts.foreach { aib =>
      cu.vins.foreach { vin =>
        vin <= aib
      }
    }
    top.vins.foreach { aob => 
      aob <= cu.vout
    }
  }

}
