package pir.pass

import pir._
import pir.node._
import pir.util._

import pirc._
import pirc.util._

import scala.collection.mutable

class LiveAnalyzer(implicit design: PIR) extends Pass with Logger {
  def shouldRun = true 
  import pirmeta._

  override lazy val stream = newStream(s"LiveAnalyzer.log")

  /*
   * useOf(s):     stage s uses s.prev's register as operand. If stage s uses it's own pipeline register
   *               it's not a use by this definition
   * defOf(s):     stage s writes to stage s
   * liveOutOf(s): pipeline register of stage s is used by later stage either by FunctionUnit or
   *               propogated to next pipeline register
   * liveInOf(s):  pipeline register of stage s's input is connected to either previous stage's
   *               pipeline register or current stage's function unit
   * */
  addPass {
    design.top.innerCUs.foreach { implicit cu =>
      // Uses in sram and counter
      updatesPrim(cu)
      // Write Addr Stages
      stageAnalysis(cu.wtAddrStages)(cu)
      stageAnalysis(cu.rdAddrStages)(cu)
      //val locals = empty::cu.localStages.toList
      stageAnalysis(cu.localStages)(cu)
      // Interference Graph
      infAnalysis(cu)
    }
  } 

  private def stageAnalysis(stages:List[Stage])(implicit cu:ComputeUnit) = emitBlock(s"stageAnalysis($cu)"){
    if (stages.nonEmpty) {
      updateStages(stages)
      liveness(stages) 
      updateInit(stages) // Add def to first use of regs with init value that are live in in first stage 
      liveness(stages) // Second round of liveness analysis

      connect(stages)
      checkConnection(stages)
    }
  }

  private def updatesPrim(implicit cu:ComputeUnit) = {
    collectIn[PipeReg](cu.mems ++ cu.cchains).foreach { case PipeReg(stage, reg) => stage.addLiveOut(reg) }
  }
  
  private def updateStages(stages:List[Stage])(implicit cu:ComputeUnit) = emitBlock(s"updateStages") {
    stages.zipWithIndex.foreach { case (s, i) =>
      s.operands = s.operands.map ( opd => addOperand(opd, s, stages) )
      s.results = s.results.map(res => addRes(res, s, stages) )
      s.fu.get.op = s.op
      dprintln(s"stage = $s[${s.index.get}]")
      dprintln(s"- uses = ${useOf(s)}")
      dprintln(s"- defs = ${defOf(s)}")
    }
  }

  private def addOperand(oprd:Any, stage:Stage, stages:List[Stage]) (implicit cu:ComputeUnit):Any = {
    oprd match {
      case oprd:Counter if stage!= stages.head => addOperand(cu.ctr(oprd), stage, stages)
      case oprd:OnChipMem if stage != stages.head => addOperand(cu.load(oprd), stage, stages)
      case oprd:Reg => useOf(stage) = oprd; oprd
      case oprd => oprd
    }
  }


  private def addRes(result:Any, stage:Stage, stages:List[Stage])(implicit cu:ComputeUnit) = {
    result match {
      case res@(_:WtAddrPR | _:StorePR | _:VecOutPR | _:ScalarOutPR) => 
        defOf(stage) = res.asInstanceOf[Reg]
        liveOutOf(stages.last) = res.asInstanceOf[Reg]
        res
      case res:Reg => defOf(stage) = res; res
      case res => throw PIRException(s"Unknown type of result = ${res} for $stage in $cu")
    }
  }

  private def liveness(stages:List[Stage]) = emitBlock(s"liveness") {
    stages.reverseIterator.foreach { stage =>
      if (stage!= stages.last) { liveOutOf(stage).clear }
      val uses = useOf(stage).filterNot { case AccumPR(init) => true ; case _ => false }
      liveOutOf(stage) = liveOutOf(stage) ++ stage.next.map { n => liveInOf(n) }.getOrElse(Nil) ++ stage.next.map { n => useOf(n) }.getOrElse(Nil)
      liveInOf(stage) = (liveOutOf(stage) -- defOf(stage))
      dprintln(s"stage:$stage[${stage.index.get}]")
      dprintln(s"- uses = ${useOf(stage)}")
      dprintln(s"- defs = ${defOf(stage)}")
      dprintln(s"- liveOut = ${liveOutOf(stage)}") 
      dprintln(s"- liveIn = ${liveInOf(stage)}") 
    }
  }

  private def updateInit(stages:List[Stage]) = emitBlock(s"updateInit") {
    liveInOf(stages.head).foreach {
      case reg@TempPR(Some(init)) => 
        val firstUseStage = useOf.imap(reg).toList.sortBy(_.index.get).head
        dprintln(s"firstUseStage of $reg with init = $init in $firstUseStage, replace with const")
        useOf(firstUseStage).remove(reg)
        firstUseStage.operands = firstUseStage.operands.map {
          case `reg` => Const(init)
          case reg => reg
        } 
      case reg@AccumPR(init) => 
        useOf.imap(reg).foreach { stage =>
          if (defOf(stage).contains(reg)) { // Accum Reg shouldn't be liveIn but should be liveOut
            useOf(stage) -= reg
          }
        }
      case reg@(_:CtrPR | _:VecInPR | _:ScalarInPR | _:LoadPR) => // Okay to liveIn
      case reg => throw PIRException(s"$reg is liveIn in this first stage of $stages but doesn't have initial value")
    }
  }

  /*
   * If a stage's reg operand is not in useOf(stage), read it from current stage, otherwise from
   * previous stage
   * */
  private def connect(stages:List[Stage])(implicit cu:ComputeUnit) = emitBlock(s"connect") {
    stages.foreach { stage =>
      dprintln(s"stage:$stage[${stage.index.get}]")
      dprintln(s"operands:${stage.operands}")
      dprintln(s"uses:${useOf(stage)}")
      // Connect operands
      stage.operands.foreach {
        case oprd:Counter => stage.fu.get.addOperand(oprd.out)
        case oprd:OnChipMem => stage.fu.get.addOperand(oprd.readPort)
        case oprd:Const[_] => stage.fu.get.addOperand(oprd.out)
        case oprd:Output => stage.fu.get.addOperand(oprd) // Not using register
        case oprd:Reg if useOf(stage).contains(oprd) =>
          val pr = stage.prev.get.get(oprd)
          stage.fu.get.addOperand(pr.out)
          dprintln(s"connect stage $stage operand $oprd pr=$pr")
        case oprd:Reg => // AccumReg
          val pr = stage.get(oprd)
          stage.fu.get.addOperand(pr.out)
        case oprd => throw PIRException(s"Unknown type of oprd = $oprd for $stage in $cu")
      }
      // Connect Function Unit output
      stage.results.foreach {
        case reg:Reg if defOf(stage).contains(reg) =>
          val pr = stage.get(reg)
          stage.fu.get.out.connect(pr.in)
      }
      // Connect input of Pipeline Registers
      liveInOf(stage).foreach { reg =>
        // Connect liveIn in first stages
        if (stage == stages.head) {
          val out = reg match {
            case CtrPR(ctr) => ctr.out
            case ScalarInPR(in) => in.out
            case VecInPR(in) => in.out
            case LoadPR(mem) => mem.readPort
          }
          stage.get(reg).in.connect(out)
        } else {
          val pr = stage.get(reg)
          val prevStage = stage.prev.get
          pr.in.connect(prevStage.get(reg).out)
          dprintln(s"Connect LiveIn $reg, prevStage=$prevStage, stage=$stage")
        }
      }
      // Connect output of Pipeline Regsiters
      liveOutOf(stage).foreach { reg =>
        if (stage==stages.last) {
          val pr = stage.get(reg)
          reg match { 
            case StorePR(mem) => mem.writePort(pr.out)
            case VecOutPR(vecOut) => vecOut.in.connect(pr.out)
            case ScalarOutPR(scalarOut) => scalarOut.in.connect(pr.out)
            case reg => throw PIRException(s"Unknown live out variable ${reg} in last stage ${stage}!")
          }
        }
      }
    }
  }

  def checkConnection(stages:List[Stage])(implicit cu:ComputeUnit) = {
    stages.foreach { stage =>
      stage.fu.get.operands.foreach { oprd => assert(oprd.isConnected, s"oprd $oprd in $stage of $cu is not connected") }
      warn(!stage.fu.get.out.isConnected, s"$stage output is not connected in $cu")
      stage.prs.foreach { pr =>
        assert(pr.in.isConnected, s"$pr.in $cu is not connected")
        assert(pr.out.isConnected, s"$pr.out $cu is not connected")
      }
    }
  }

  private def infAnalysis(cu:ComputeUnit):Unit = emitBlock(s"infAnalysis($cu)") {
    val stages = cu.stages
    stages.foreach { s =>
      // Possibly define but no use
      (liveOutOf(s) ++ defOf(s)).foreach { r =>
        if (!cu.infGraph.contains(r)) cu.infGraph += (r -> mutable.Set.empty)
        // register doesn't interfere with co-def from the same source
        // e.g. FU writes to 2 registers
        val diffSrcLiveOut = liveOutOf(s).filterNot { lo => s.get(r).in.from == s.get(lo).in.from }
        cu.infGraph(r) ++= diffSrcLiveOut 
      }
    }
    cu.infGraph.foreach { case (k, v) => 
      emitln(s"${k}: [${v.mkString(s",")}]")
    }
  }

  override def finPass = {
    endInfo("Finishing Liveness Analysis")
  }

}
