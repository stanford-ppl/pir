package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map

class LiveAnalysis(implicit val design: Design) extends Traversal{

  //override def reset = nameMap.clear()
  override def traverse = {
    design.top.compUnits.foreach { implicit cu =>
      // Uses in sram and counter
      updatesPrim(cu)
      // Write Addr Stages
      cu.wtAddrStages.foreach { was => updateStages(was); liveness(was); connectPRs(was) }
      // Local Stages
      val empty = if (cu.wtAddrStages.size > 0) cu.wtAddrStages.last.last else cu.emptyStage 
      val locals = empty::cu.localStages.toList
      updateStages(locals)
      liveness(locals) 
      connectPRs(locals)
    }
  } 

  def updatesPrim(implicit cu:ComputeUnit) = {
    cu.srams.foreach { sram =>
      addLiveOut(sram.readAddr)
      addLiveOut(sram.writeAddr)
      if (sram.writePort.isConnected)
        addLiveOut(sram.writePort)
    }
    cu.cchains.foreach { cc => 
      cc.counters.foreach { ctr =>
        addLiveOut(ctr.min)
        addLiveOut(ctr.max)
        addLiveOut(ctr.step)
      }
    }
  }
  
  def updateStages(stages:List[Stage])(implicit cu:ComputeUnit) = {
    stages.zipWithIndex.foreach { case (s, i) =>
      if (s.fu.isDefined) {
        s.operands.foreach { opd => addOpd(opd, s, stages) }
        s.results.foreach { res => addRes(res, i, stages) }
      }
    }
  }

  private def addLiveOut(port:InPort) (implicit cu:ComputeUnit):Unit = {
    port.from.src match {
      case p@PipeReg(stage, reg) => stage.addLiveOut(reg)
      case _ => 
    }
  }

  private def addOpd(port:InPort, stage:Stage, stages:List[Stage]) (implicit cu:ComputeUnit) = {
    port.from.src match {
      case pr@PipeReg(s, reg) => 
        if (stage == s) s.addUse(reg) // If is current stage, is a usage
        else s.addLiveOut(reg) // Forwarding path, a branch out
      case pm => {
        if (stage.isInstanceOf[LocalStage] && stage==stages.head && !pm.isInstanceOf[SRAM]) {
          throw PIRException(s"Local stage ${stage} of ${stage.ctrler} excepts the first stage cannot directly reference ${pm} as operand")
        }
        pm match {
          case n:VecIn => stage.addDef(cu.vecInPR(n))
          case n:ScalarIn => stage.addDef(cu.scalarInPR(n))
          case n:SRAM => stage.addDef(cu.loadPR(n)) 
          case n:Counter => stage.addDef(cu.ctrPR(n))
          case _ =>
        }
      }
    } 
  }


  private def addRes(res:OutPort, i:Int, stages:List[Stage])(implicit cu:ComputeUnit) = {
    val stage = stages(i)
    res.to match {
      case p:PRInPort =>
        val PipeReg(s, reg) = p.src
        s.addDef(reg)
        reg match {
          case (_:StorePR | _:VecOutPR | _:ScalarOutPR) => stages.last.addLiveOut(reg)
          case _ => 
        }
      case p:RdAddrInPort =>
        val sram = p.src
        if (stage!=stages.last) // Loaded value are forwarded one stage after readAddr calc
          stages(i+1).addDef(cu.loadPR(sram))
      case _ =>
    }
  }

  def compLiveIn(liveOuts:ISet[Reg], defs:ISet[Reg], uses:ISet[Reg]):ISet[Reg] = 
    (liveOuts -- defs ++ uses)
  // Definition of use here is different from traditional CPU use
  // sn: reg(sn,c) <- reg(sn-1, a) + reg(sn-1, b)
  // reg(sn-1, a) and reg(sn-1, b) are considered as use of sn-1 rather than sn 
  def liveness(stages:List[Stage]) = {
    for (i <- stages.size-1 to 0 by -1){
      val s = stages(i)
      s.liveOuts = if (s==stages.last) s.liveOuts else s.liveOuts ++ stages(i+1).liveIns
      s.liveIns = compLiveIn(s.liveOuts, s.defs.toSet, s.uses.toSet)
      s.operands.foreach { o =>
        o.from.src match {
          case PipeReg(stage,reg) => if (reg.isInstanceOf[AccumPR]) s.liveIns = s.liveIns - reg
          case _ =>
        }
      }
      if (s == stages.head) { // Empty stage. Add forwarding path to liveIn variables 
        s.liveIns.foreach{ r => 
          r match {
            case (_:CtrPR | _:VecInPR | _:ScalarInPR ) => s.addDef(r)
            case _ => throw PIRException(s"Register ${r} has no definition!")
          }
        }
        s.liveIns = compLiveIn(s.liveOuts, s.defs.toSet, s.uses.toSet)
      } else if (i==1) { // s == stages.second. First stage after "empty stage" 
        s.liveIns.foreach{ r => 
          // If there's no def on loaded value, check if sram's readAddr is directly connected to 
          // the counter. If it is, forward loaded value to the first local stage
          if (r.isInstanceOf[LoadPR]) { 
            val sram = r.asInstanceOf[LoadPR].rdPort.src
            if (sram.readAddr.from.isInstanceOf[CtrOutPort]) s.addDef(r)
            else throw PIRException(s"${sram} has no readAddr defined!")
          }
        }
        s.liveIns = compLiveIn(s.liveOuts, s.defs.toSet, s.uses.toSet)
      }
    }
  }

  // If reg:
  // if liveOut, register is enabled
  // if liveIn, register is passed through from previous reg
  // if defs but not defined by ALU, forwarding value to pipereg
  // assert liveOut but not liveIn and no def
  def connectPRs(stages:List[Stage])(implicit cu:ComputeUnit) = {
    for (i <- 0 until stages.size) {
      val stage = stages(i)
      stage.liveOuts.foreach { reg =>
        val pr = cu.pipeReg(stage, reg)
        if (!pr.in.isConnected) {
          if (stage.defs.contains(reg)) {
            if (!stage.fu.isDefined || !stage.fu.get.defines(reg)) {
              reg match {
                case CtrPR(_, ctr) => pr.in.connect(ctr.out) 
                case LoadPR(_, rdPort) => pr.in.connect(rdPort) 
                case VecInPR(_, vecIn) => 
                  if (stage!=stages.head) pr.in.connect(cu.pipeReg(stages.head, reg))
                case ScalarInPR(_, scalarIn) =>
                  if (stage!=stages.head) pr.in.connect(cu.pipeReg(stages.head, reg))
                case _ => throw PIRException(s"Cannot forward reg type: ${reg}")
              }
            }
          } else if (stage.liveIns.contains(reg)) {
            val pre = stages(i-1)
            val prePr = cu.pipeReg(pre, reg)
            pr.in.connect(prePr)
          } else {
            throw PIRException(s"what's going on")
          } 
        }
        if (stage==stages.last) {
          if (!pr.out.isConnected) {
            reg match {
              case StorePR(_, wtPort) => wtPort.connect(pr.out)
              case VecOutPR(_) =>
              case ScalarOutPR(_, _) =>
              case _ => throw PIRException(s"Unknown live out variable ${reg} in last stage ${stage}!")
            }
          }
        }
      }
    }
  }

  override def finPass = {
    info("Finishing Liveness Analysis")
  }

}
