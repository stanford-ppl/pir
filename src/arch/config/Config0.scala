package pir.plasticine.config
                          
import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus
object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // Assume all CUs are homogenous for now

   // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 4
  private val numTTs = 2 

  private val numArgInBuses = 1 
  private val numArgOutBuses = 1 

  val argInBuses = List.fill(numArgInBuses) { OutBus(numLanes) }
  val argOutBuses = List.fill (numArgOutBuses) { InBus(numLanes) }
  val argIns = List.tabulate(numArgInBuses, argInBuses.head.inports.size) { case (ib, ia) =>
    ScalarOut(argInBuses(ib).inports(ia))
  }.flatten
  val argOuts = List.tabulate(numArgOutBuses, argOutBuses.head.outports.size) { case (ib, ia) =>
    ScalarIn(argOutBuses(ib).outports(ia))
  }.flatten
  override val top = Top(argIns, argOuts, argInBuses, argOutBuses)

  def genFields[T](numPRs:Int, numCtrs:Int, numSRAMs:Int, numScalarOuts:Int)(implicit cltp:TypeTag[T]) = {
    val numBusIns = if (numSRAMs==0) 1 else numSRAMs

    // Create Logical Registers (entire row of physical register for all stages)
    val regs = List.tabulate(numPRs) { ir => Reg() }

    // Pipeline Stages
    val etstage = EmptyStage(regs)
    val wastages:List[WAStage] = List.fill(3) { WAStage(numOprds=2, regs) } // Write addr calculation stages
    val rastages:List[FUStage] = List.fill(3) { FUStage(numOprds=2, regs) } // Read addr calculation stages 
    val regstages:List[FUStage] = List.fill(3) { FUStage(numOprds=1, regs) } // Regular stages
    val redstages:List[ReduceStage] = List.fill(4) { ReduceStage(numOprds=2, regs) } // Reduction stage 
    val fustages:List[FUStage] = wastages ++ rastages ++ regstages ++ redstages 
    val stages:List[Stage] = etstage :: fustages 
    // Stages where srams, ctrs, scalarIn, vecIn can be forwarded to
    val fwstages:List[FUStage] = wastages ++ rastages :+ regstages.head

    val vecIns = List.tabulate(numBusIns) { is => InBus(numLanes) }
    val vecOut =  OutBus(numLanes)
    val scalarIns = List.tabulate(numBusIns, vecIns.head.outports.size) { case (ib, is) => 
      ScalarIn(vecIns(ib).outports(is))
    }.flatten
    val scalarOuts = List.tabulate(vecOut.inports.size) { is =>
      typeOf[T] match {
        case t if t =:= typeOf[ComputeUnit] => ScalarOut(vecOut.inports(is))
        case t if t =:= typeOf[TileTransfer] => AddrOut()
      }
    }
    val ctrs = List.tabulate(numCtrs) { ic => 
      val c = Counter() 
      val sis = scalarIns.map(_.out)
      c.min <== sis :+ Const // TODO
      c.max <== sis :+ Const
      c.step <== sis :+ Const
      c
    }
    /* Chain counters together */
    for (i <- 1 until numCtrs) { ctrs(i).en <== ctrs(i-1).sat } 
    for (i <- 0 until numCtrs by 2) { ctrs(i).en <== top.clk }

    val srams = List.tabulate(numSRAMs) { is => 
      val s = SRAM()
      s.readAddr <== ctrs.map(_.out)
      s.writeAddr <== ctrs.map(_.out)
      s
    } 
    srams.zipWithIndex.foreach { case (s,is) => s.writePort <== vecIns(is).outports(0) }

    /* Register Mapping */
    var ptr = 0
    vecIns.zipWithIndex.foreach { case (ib, is) => regs(ptr + is) <-- (ib.viport, etstage) }
    regs(ptr) <-- (vecOut.voport, stages.last)
    scalarIns.zipWithIndex.foreach { case (si, is) => regs(ptr + is) <-- (si.out, etstage) }
    scalarOuts.zipWithIndex.foreach { case (so, is) => regs(ptr + is) <-- (so.in, stages.last) }
    ctrs.zipWithIndex.foreach { case (c, ic) => regs(ptr + ic) <-- (c.out, fwstages) }
    ptr += numCtrs
    srams.zipWithIndex.foreach { case (s, is) => 
      regs(ptr + is) <-- (s.readAddr, fwstages) 
      regs(ptr + is) <-- (s.writeAddr, fwstages) 
    }
    ptr += numSRAMs
    srams.zipWithIndex.foreach { case (s, is) => 
      regs(ptr + is) <-- (s.writePort, fwstages)
      regs(ptr + is) <-- (s.readPort, fwstages)
    }
    ptr += numSRAMs

    /* FU Constrain  */
    fustages.zipWithIndex.foreach { case (stage, i) =>
      // All stage can read from any regs of its own stage, previous stage, and Const
      val preStage = stages(i-1)
      stage.fu.operands.foreach{ oprd =>
        oprd <== Const
        regs.foreach{ reg =>
          oprd <== stage.prs(reg)
          oprd <== preStage.prs(reg)
        }
      }
      // All stage can write to all regs of its stage
      regs.foreach{ reg => stage.prs(reg) <== stage.fu.out }
    }

    fwstages.foreach { stage =>
      // Creating forwarding path to all operands of the FUs in these stages  
      stage.fu.operands.foreach { oprd =>
        ctrs.foreach{ oprd <== _.out }
        srams.foreach{ oprd <== _.readPort }
      }
    }
    
    /* PipeReg Constrain */
    vecOut <== fustages.last.fu.out
    scalarOuts.foreach { _.in <== fustages.last.fu.out }
    srams.foreach { _.writePort <== fustages.last.fu.out }

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
    regs(ptr) <-- (c.reduce, stages.filter(s =>s.isInstanceOf[ReduceStage] ))
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
    regs(ptr) <-- (c.reduce, stages.filter(s =>s.isInstanceOf[ReduceStage] ))
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
