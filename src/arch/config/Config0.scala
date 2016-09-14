package pir.plasticine.config
                          
import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import pir.graph.Ops

// 4 cu + 2 tt

// Assume no scalarIn and scalarOut buffer are before and after pipeline stages.
// Still have scalarIn and scalarOut as node but make sure # scalarIn and # scalarOut always equal
// to outports and inports of inbus and outbus
object Config0 extends Spade {
  override def toString = "Plasticine_Config0"

  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  // Inner CU Specs
  override val wordWidth = 32
  override val numLanes = 4
  
  private val numRCUs = 2
  private val numTTs = 2 

  private val numArgInBuses = 1 
  private val numArgOutBuses = 1 

  // ArgIns and ArgOuts are ScalarOuts and ScalarIns of the top level module.
  // argInBuses and argOutBuses bundles scalar value into buses since only bus routing including
  // ArgIn to a CU is allowed
  val argInBuses = List.tabulate(numArgInBuses) { i => OutBus(i, numLanes) }
  val argOutBuses = List.tabulate(numArgOutBuses) { i => InBus(i, numLanes) }
  val argIns = List.tabulate(numArgInBuses, argInBuses.head.inports.size) { case (ib, ia) =>
    ScalarOut(argInBuses(ib).inports(ia))
  }.flatten
  val argOuts = List.tabulate(numArgOutBuses, argOutBuses.head.outports.size) { case (ib, ia) =>
    ScalarIn(argOutBuses(ib).outports(ia))
  }.flatten
  // Top level controller ~= Host
  override val top = Top(argIns, argOuts, argInBuses, argOutBuses)

  // Generate fields for ComputeUnits and TileTransfer CU
  def genFields[T](numPRs:Int, numCtrs:Int, numSRAMs:Int, numScalarOuts:Int)(implicit cltp:TypeTag[T]) = {
    val numBusIns = if (numSRAMs==0) 1 else numSRAMs // If there's no sram (TileTransfer CU), there's at least 1 bus input

    // Create Logical Registers (entire row of physical register for all stages)
    val regs = List.tabulate(numPRs) { ir => Reg(ir) }

    val vecIns = List.tabulate(numBusIns) { is => InBus(is, numLanes) } // Bus Input with numLanes words
    val vecOut =  OutBus(0, numLanes) // Bus Output with numLanes words. Assume only single bus output per CU for now
    val scalarIns = List.tabulate(numBusIns, vecIns.head.outports.size) { case (ib, is) => // Scalar inputs. 1 per word in bus input 
      ScalarIn(vecIns(ib).outports(is))
    }.flatten
    val scalarOuts = List.tabulate(vecOut.inports.size) { is =>
      typeOf[T] match {
        case t if t =:= typeOf[ComputeUnit] => ScalarOut(vecOut.inports(is))
        // Spetial scalar out for TileTransfer. The only scalar routing case. TileTransfer has 1 bus and output (generated internally) and 1 scalar output
        case t if t =:= typeOf[TileTransfer] => AddrOut() 
      }
    }
    val ctrs = List.tabulate(numCtrs) { ic => 
      val c = Counter(ic) 
      c.min <== const.out // Counter max, min, step can be constant or scalarIn(specified later)
      c.max <== const.out
      c.step <== const.out
      c
    }
    /* Chain counters together */
    for (i <- 1 until numCtrs) { ctrs(i).en <== ctrs(i-1).done } 
    for (i <- 0 until numCtrs by 2) { ctrs(i).en <== top.clk } // Allow group counter in chain in multiple of 2

    val srams = List.tabulate(numSRAMs) { is => 
      val s = SRAM(is)
      s.readAddr <== ctrs.map(_.out) // sram read/write addr can be from all counters
      s.writeAddr <== ctrs.map(_.out)
      s
    } 
    // Remote write. vecIn and sram 1 to 1 mapping. Doesn't have to be the case 
    srams.zipWithIndex.foreach { case (s,is) => s.writePort <== vecIns(is).outports(0) } 

    /* Pipeline Stages */
    val ops = Ops.allOps // All fu can perform all operations
    val etstage = EmptyStage(regs)
    val wastages:List[WAStage] = List.fill(3) { WAStage(numOprds=2, regs, ops) } // Write/read addr calculation stages
    val rastages:List[FUStage] = List.fill(1) { FUStage(numOprds=2, regs, ops) } // Additional read addr only calculation stages 
    val regstages:List[FUStage] = List.fill(1) { FUStage(numOprds=1, regs, ops) } // Regular stages
    val rdstages:List[ReduceStage] = List.fill(4) { ReduceStage(numOprds=2, regs, ops) } // Reduction stage 
    val fustages:List[FUStage] = wastages ++ rastages ++ regstages ++ rdstages // Stages with fu 
    val stages:List[Stage] = etstage :: fustages // All stages 
    val fwstages:List[FUStage] = wastages ++ rastages :+ regstages.head // Stages where srams, ctrs, scalarIn, vecIn can be forwarded to

    /* Register Constrain */
    var ptr = 0
    // All Pipeline Registers (PipeReg) connect to its previous stage ('stage.prs(reg)':Pipeline
    // Register 'reg' at stage 'stage')
    for (i <- 1 until stages.size) {
      regs.foreach { reg => stages(i).prs(reg).in <== stages(i-1).prs(reg).out }
    }
    // Bus input is forwarded to 1 register in empty stage
    vecIns.zipWithIndex.foreach { case (vin, is) => etstage.prs(regs(ptr + is)) <== (vin.viport) }
    // Bus output is connected to 1 register in last stage
    vecOut.voport <== stages.last.prs(regs(ptr))
    scalarIns.zipWithIndex.foreach { case (si, is) => 
      val sireg = etstage.prs(regs(ptr + is)) 
      sireg <== si.out // ScalarIn is connected to 1 register in empty stage
      ctrs.foreach { c => c.min <== sireg; c.max <== sireg ; c.step <== sireg } // Counter min, max, step can from scalarIn
    }
    // Scalar outputs is connected to 1 register in last stage
    scalarOuts.zipWithIndex.foreach { case (so, is) => so.in <== stages.last.prs(regs(ptr + is)) }
    // Counters can be forwarde to empty stage, writeAddr and readAddr stages 
    ctrs.zipWithIndex.foreach { case (c, ic) => 
      (etstage :: wastages ++ rastages).foreach(_.prs(regs(ptr + ic)) <== c.out) 
    }
    ptr += numCtrs
    // Sram read addr and write addr (probably don't need 1 reg per sram for write addr. Usually
    // only write to 1 sram)
    srams.zipWithIndex.foreach { case (s, is) => 
      (wastages ++ rastages).foreach(s.readAddr <== _.fu.out)
      s.writeAddr <== stages.last.prs(regs(ptr + is))
    }
    ptr += numSRAMs
    srams.zipWithIndex.foreach { case (s, is) => 
      s.writePort <== stages.last.prs(regs(ptr + is)) // Sram write port is connected to 1 register of last stage
      (wastages ++ rastages :+ regstages.head).foreach(_.prs(regs(ptr + is)) <== s.readPort) // Sram read port forwarding 
    }
    ptr += numSRAMs

    /* FU Constrain  */
    fustages.zipWithIndex.foreach { case (stage, i) =>
      // All stage can read from any regs of its own stage, previous stage, and Const
      val preStage = stages(i) // == fustages(i-1)
      stage.fu.operands.foreach{ oprd =>
        oprd <== const.out // operand is constant
        regs.foreach{ reg =>
          oprd <== stage.prs(reg) // operand is from current register block
          oprd <== preStage.prs(reg) // operand is forwarded from previous register block
        }
      }
      // All stage can write to all regs of its stage
      regs.foreach{ reg => stage.prs(reg) <== stage.fu.out }
    }

    wastages.foreach { stage =>
      stage.fu.operands.foreach { oprd => 
        // Creating forwarding path from counter outputs to all operands of the FUs in write 
        // addr stages
        ctrs.foreach{ oprd <== _.out } 
        // Creating forwarding path from srams loads to all operands of the FUs
        srams.foreach{ oprd <== _.readPort }
      }
      // Connect all srams's write addr to writeAddr stages
      srams.foreach { _.writeAddr <== stage.fu.out }
    }
    
    val ctrlBox = CtrlBox(numCtrs = numCtrs, numTokenOuts =numCtrs, numTokenIns=numCtrs)
    (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox, ptr)
  }

  override val rcus = List.tabulate(numRCUs) { i =>
    val numPRs = 20
    val numCtrs = 8
    val numSRAMs = 2
    val numScalarOuts = numLanes

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox, ptr) =
      genFields[ComputeUnit](numPRs, numCtrs, numSRAMs, numScalarOuts)
    val c = ComputeUnit(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox)
    c.rdstages.foreach( _.prs(regs(ptr)) <== c.reduce)
    c
  } 

  override val ttcus = List.tabulate(numTTs) { i =>
    val numPRs = 15
    val numCtrs = 8
    val numSRAMs = 0
    val numScalarOuts = 1

    val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox, ptr) =
      genFields[TileTransfer](numPRs, numCtrs, numSRAMs, numScalarOuts)
    val c = TileTransfer(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox)
    c.rdstages.foreach( _.prs(regs(ptr)) <== c.reduce)
    c
  }
  
  /* Network Constrain */ 
  rcus(0).vins(0) <== ttcus(0).vout 
  rcus(0).vins(1) <== ttcus(1).vout
  rcus(1).vins(0) <== rcus(0).vout 
  rcus(1).vins(1) <== rcus(0).vout

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
