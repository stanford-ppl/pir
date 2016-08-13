package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.PIRMisc._
import pir.plasticine.graph._
import pir.graph._
import pir.graph.mapper._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, Node, Primitive, Top, 
MemoryController => MC, InPort => IP, OutPort => OP, Const, FuncUnit => FU, Counter => CT, 
PipeReg => PR, VecIn, SRAM => SM, Stage => ST, ReduceStage => RDST, AccumPR, ScalarIn => SI}
import pir.plasticine.graph.{Node => PNode, Controller => PCL, ComputeUnit => PCU, 
TileTransfer => PTT, EmptyStage => PES, Stage => PST, FUStage => PFUST, Top => PTop, FuncUnit => PFU,
Counter => PCT, InBus => PIB, PipeReg => PPR, InPort => PIP, OutPort => POP, SRAM => PSM, 
Const => PConst, ScalarIn => PSI}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class PisaCodegen(pirMapping:PIRMapping)(implicit design: Design) extends Traversal with JsonCodegen {

  lazy val mapping:PIRMap = pirMapping.mapping
  lazy val opmap:OPMap = mapping.opmap
  lazy val ipmap:IPMap = mapping.ipmap
  lazy val fpmap:FPMap = mapping.fpmap
  lazy val stmap:STMap = mapping.stmap
  lazy val ctmap:CTMap = mapping.ctmap
  lazy val smmap:SMMap = mapping.smmap
  lazy val clmap:CLMap = mapping.clmap
  lazy val vimap:VIMap = mapping.vimap

  override val stream = newStream(Config.pisaFile) 
  
  def lookUp(op:Op):String = {
    op match {
      case o:FltOp => throw TODOException(s"Op ${op} is not supported at the moment")
      case o:FixOp => o match {
        case FixAdd => s"+"
        case FixSub => s"-"
        case FixMul => s"*"
        case FixDiv => s"%"
        case _ => throw TODOException(s"Op ${op} is not supported at the moment")
      }
      case Bypass => "passA" 
      case _ => throw TODOException(s"Op ${op} is not supported at the moment")
    }
  }

  def lookUp(ip:IP):String = lookUp(ip.from.src)

  def lookUp(node:Node):String = {
    node match {
      case Const(_, c) => s"c${c}"
      case fu:FU => 
        val stage = fu.stage
        val pstage = stmap(stage)
        lookUp(pstage)
      case ctr:CT => 
        val pctr = ctmap(ctr)
        lookUp(pctr)
      case sm:SM =>
        val psram = smmap(sm)
        lookUp(psram)
      case _ => throw new TODOException(s"Don't know how to lookUp ${node}"); "?"
    }
  }

  def lookUp(pnode:PNode):String = {
    pnode match {
      case PConst(c) => s"c${c}"
      case PConst => throw PIRException(s"don't know how to lookUp PConst")
      case pst:PST => s"s${pst.idx}"
      case pfu:PFU => lookUp(pfu.stage) 
      case pctr:PCT => s"i${pctr.idx}"
      case pib:PIB => s"bus${pib.idx}"
      case psm:PSM => s"m${psm.idx}"
      case _ => throw new TODOException(s"Don't know how to lookUp ${pnode}"); "?"
    }
  }

  def lookUp(pstage:PST, pn:PNode):String = {
    pn match {
      case ppr:PPR =>
        if (ppr.stage.idx==pstage.idx) {
          s"l${ppr.reg.idx}"
        } else if (ppr.stage.idx==pstage.idx-1) {
          s"r${ppr.reg.idx}"
        } else {
          throw PIRException(s"Reading from not accessable stage curr:${pstage}, ${ppr.stage}")
        }
      case _ => 
        lookUp(pn)
    }
  }

  override def traverse:Unit = {
    if (pirMapping.failed) return
    implicit val ms = new CollectionStatus(false)
    emitBlock {
      emitMap("PISA") { implicit ms =>
        emitPair("version", 0.1)
        emitMap("topconfig"){ implicit ms =>
          emitPair("type", "plasticine")
          emitMap("config") { implicit ms =>
            emitList("cu") { implicit ms =>
              emitMain
            }
          }
        }
      }
      pprintln
    }
  }

  def emitMain(implicit ms:CollectionStatus) = {
    design.arch.ctrlers.foreach { ctrler =>
      ctrler match {
        case top:PTop =>
        case pcu:PCU => pcu match {
          case tt:PTT =>
          case _ =>
            emitMap { implicit ms =>
              if (clmap.pmap.contains(pcu)) {
                val cu = clmap.pmap(pcu)
                //emitComment(s"${cu}")
              }
              emitList(s"scratchpads") { implicit ms =>
                pcu.srams.foreach{ psram => 
                  emitMap{ implicit ms =>
                    if (smmap.pmap.contains(psram)) {
                      val sram = smmap.pmap(psram)
                      emitPair("ra", lookUp(sram.readAddr))
                      sram.writeAddr.from.src match {
                        case pr:PR => 
                          emitPair("wa", "local")
                        case _ => 
                          emitPair("wa", lookUp(sram.writeAddr))
                      }
                      val wd = sram.writePort.from.src match {
                        case v:VecIn => lookUp(vimap(v))
                        case s:PR => "local"
                      }
                      emitPair("wd", wd)
                      emitPair("wen",lookUp(sram.writeCtr))
                    } else {
                      emitPair("ra", "x")
                      emitPair("wa", "x")
                      emitPair("wd", "x")
                      emitPair("wen", "x")
                    }
                  }
                }
              }
              emitList(s"counterChain") { implicit ms =>
                val pctrs = pcu.ctrs
                val chain = List.tabulate(pctrs.size-1) { i =>
                  if (ctmap.pmap.contains(pctrs(i)) && ctmap.pmap.contains(pctrs(i+1))) 1
                  else 0
                }
                emitPair("chain", s"[${chain.mkString(",")}]")
                emitMap("counters") { implicit ms =>
                  pcu.ctrs.foreach { pctr =>
                    emitMap { implicit ms =>
                    if (ctmap.pmap.contains(pctr)) {
                      val ctr = ctmap.pmap(pctr)
                      emitPair("max", lookUp(ctr.max))
                      emitPair("min", lookUp(ctr.min))
                      emitPair("stride", lookUp(ctr.step))
                    } else {
                      emitPair("max", "x")
                      emitPair("min", "x")
                      emitPair("stride", "x")
                    }
                    }
                  }
                }
              }
              emitList(s"pipeStage") { implicit ms =>
                pcu.stages.foreach { pstage =>
                  emitMap { implicit ms =>
                    pstage match {
                      case s:PFUST =>
                        val pfu = s.fu
                        //emitComment(s"${pstage}")
                        if (stmap.pmap.contains(pstage)) { //Physical stage have corresponding pir stage
                          val fu = stmap.pmap(pstage).fu.get
                          if (fu.operands.size>2)
                            throw PIRException(s"Dont' support any operation with more than 2 operands at the moment ${fu.operands}")
                          // Operand
                          val popA = fpmap(pfu.operands.head)
                          emitPair("opA", lookUp(pstage, popA.src.get))
                          if (fu.operands.size==1)
                            emitPair("opB", "x")
                          else {
                            val popB = fpmap(pfu.operands(1))
                            emitPair("opB", lookUp(pstage, popB.src.get))
                          }
                          var op = lookUp(fu.op)
                          val stage = stmap.pmap(pstage)
                          if (stage.isInstanceOf[RDST]) {
                            op = "r" + op
                          }
                          emitPair("opcode", s"${op}")
                          val firstResult = fu.out.to.head
                          val pip = ipmap(firstResult) // TODO: only gen 1 result at the time
                          emitPair("result", s"${lookUp(pstage, pip.src.get)}")
                          firstResult.src match {
                            case PR(s, r) => r match {
                              case AccumPR(_, Const(_, c)) => 
                                emitPair("accumInit", s"c${c}")
                              case _ =>
                            }
                            case _ =>
                          }
                        } else {
                          emitPair("opA", "x")
                          emitPair("opB", "x")
                          emitPair("opcode", "x")
                          emitPair("result", "x")
                        }
                      case _ =>
                    }
                    val rstrs = pstage.prs.flatMap { case (preg, ppr) =>
                      assert(pstage==ppr.stage)
                      if (fpmap.contains(ppr.in)) {
                        fpmap(ppr.in).src.get match {
                          case p:PFU => Some(s""""r${preg.idx}" : "alu"""")
                          case p:PSI => None
                          case p => Some(s""""r${preg.idx}" : "${lookUp(pstage, p)}"""")
                        }
                      } else None
                    }
                    emitList("fwd", rstrs.toList)
                  }
                }
              }
              emitMap(s"control") { implicit ms =>
              }
            }
        }
      }
    }
  }

  override def finPass = {
    close
    info(s"Finishing PisaCodegen in ${getPath}")
  }

}
