package pir.plasticine.config
                          
import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._

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

  def genFields[T](numPRs:Int, numCtrs:Int, numSRAMs:Int, numScalarOuts:Int)(implicit cltp:TypeTag[T]) = {
    val numBusIns = if (numSRAMs==0) 1 else numSRAMs
    val numScalarIns = numLanes // Number of scalarIn per InBus 

    // Create Pipeline Regs (entire row of physicall register for all stages)
    // No overlapping between mappings
    val regs = List.tabulate(numPRs) { ir => PipeReg() }
    var ptr = 0

    val vecIns = List.tabulate(numBusIns) { is =>
      val ib = InBus(numLanes)
      regs(ptr + is) <== ib // Map logical register to InBus
      ib
    }
    val vecOut = {
      val ob = OutBus(numLanes)
      ob <== regs(ptr)
      ob
    } 
    val scalarIns = List.tabulate(numBusIns, numScalarIns) { case (ib, is) => 
      val si = ScalarIn(vecIns(ib).outports(is))
      regs(ptr + is) <== si
      si
    }.flatten
    val scalarOuts = List.tabulate(numScalarOuts) { is =>
      val so = typeOf[T] match {
        case t if t =:= typeOf[ComputeUnit] => ScalarOut(vecOut.inports(is))
        case t if t =:= typeOf[TileTransfer] => AddrOut()
      }
      so <== regs(ptr + is)
      so
    }
    val ctrs = List.tabulate(numCtrs) { ic => 
      val c = Counter() 
      val sis = scalarIns.map(_.out)
      c.min <== sis :+ Const
      c.max <== sis :+ Const
      c.step <== sis :+ Const
      regs(ptr + ic) <== c.out
      c
    }
    /* Chain counters together */
    for (i <- 1 until numCtrs) {
      ctrs(i).en <== ctrs(i-1).sat
    } 
    for (i <- 0 until numCtrs by 2) {
      ctrs(i).en <== top.clk
    }
    ptr += numCtrs

    val srams = List.tabulate(numSRAMs) { is => 
      val s = SRAM()
      s.readAddr <== ctrs.map(_.out) :+ regs(ptr + is).out 
      s.writeAddr <== ctrs.map(_.out) :+ regs(ptr + is).out
      s
    } 
    ptr += numSRAMs
    srams.zipWithIndex.foreach { case (s,is) =>
      s.writePort <== List[OutPort](vecIns(is).outports(0),regs(ptr + is))
      regs(ptr + is) <== s.readPort
    }
    ptr += numSRAMs

    /* Pipeline Stages ( Describing configurable connections of operands and result of the FU in
     * that stage ) */
    val wastages = List.fill(3) { WAStage(numOprds=2) } // Write addr calculation stages
    val regstages = List.fill(3) { Stage(numOprds=3) } // Regular stages
    val redstages = List.fill(4) { RedStage(numOprds=2) } // Reduction stage 

    // <== : readAcess, ==> : writeAcess
    val stages = wastages ++ regstages ++ redstages 
    // All stage can read from any regs of its own stage
    stages.foreach{ stage => regs.foreach { reg => stage.fu.oprds.foreach { _ <== (reg, stage) }}}
    // All Stages have forwarding path from its previous stage on all regs to all operands
    for (i <- 1 until stages.size) {
      regs.foreach { reg => stages(i).fu.oprds.foreach( _ <== (reg, stages(i-1)) )
      }
    }
    // All stage can write to all regs of its stage
    stages.foreach { stage => regs.foreach { reg => stage.fu ==> (reg, stage) } }

    // Stages where srams, ctrs, vins, vouts can be forwarded to
    val fwstages = wastages :+ regstages.head
    fwstages.foreach { stage =>
      // Set a forwarding path from inputs to first stage pipeline registers to this stage
      stage.setPRForward
      // Creating forwarding path to all operands of the FUs in these stages  
      stage.fu.oprds.foreach { oprd =>
        vecIns.foreach{ oprd <== _ }
        scalarIns.foreach{ oprd <== _ }
        ctrs.foreach{ oprd <== _.out }
        srams.foreach{ oprd <== _.readPort }
      }
    }
    // Connections in last stage
    stages.last.fu ==> vecOut
    scalarOuts.foreach { stages.last.fu ==> _ }
    srams.foreach { stages.last.fu ==> _.writePort }

    (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ptr)
  }

  override val rcus = List.tabulate(numRCUs) { i =>
    val numPRs = 20
    val numCtrs = 10
    val numSRAMs = 2
    val numScalarOuts = numLanes

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ptr) =
      genFields[ComputeUnit](numPRs, numCtrs, numSRAMs, numScalarOuts)
    val c = ComputeUnit(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages)
    regs(ptr) <== c.reduce
    c
  } 

  override val ttcus = List.tabulate(numTTs) { i =>
    val numPRs = 15
    val numCtrs = 10
    val numSRAMs = 0
    val numScalarOuts = 1

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ptr) =
      genFields[TileTransfer](numPRs, numCtrs, numSRAMs, numScalarOuts)
    val c = TileTransfer(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages)
    regs(ptr) <== c.reduce
    c
  }
  
  /* Network Constrain */ 
  rcus(0).vins(0) <== ttcus(0).vout 
  rcus(0).vins(1) <== ttcus(1).vout
  rcus(1).vins(0) <== ttcus(0).vout 
  rcus(1).vins(1) <== ttcus(1).vout
  rcus(2).vins(0) <== rcus(0).vout 
  rcus(2).vins(1) <== rcus(1).vout
  rcus(3).vins(0) <== rcus(0).vout 
  rcus(3).vins(1) <== rcus(1).vout

  /* Connnect all ArgIns to scalarIns of all CUs and all ArgOuts to scalarOuts of all CUs*/
  (rcus ++ ttcus).foreach { cu =>
    top.vouts.foreach { aib =>
      cu.vins.foreach { vin =>
        vin <== aib
      }
    }
    top.vins.foreach { aob => 
      aob <== cu.vout
    }
  }

}
