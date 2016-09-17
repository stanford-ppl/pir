package pir.graph.traversal

import pir._
import pir.codegen._
import pir.misc._
import pir.typealias._
import pir.plasticine.main._
import pir.graph.enums._
import pir.graph.mapper._
import pir.graph.{EnLUT => _, _}
import pir.plasticine.graph.{ ConstVal => PConstVal, Const => PConst }

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class PisaCodegen(pirMapping:PIRMapping)(implicit design: Design) extends Traversal with JsonCodegen with Metadata {
  implicit lazy val spade:Spade = design.arch

  lazy val mapping:PIRMap = pirMapping.mapping
  lazy val opmap:OPMap = mapping.opmap
  lazy val ipmap:IPMap = mapping.ipmap
  lazy val fpmap:FPMap = mapping.fpmap
  lazy val stmap:STMap = mapping.stmap
  lazy val ctmap:CTMap = mapping.ctmap
  lazy val smmap:SMMap = mapping.smmap
  lazy val clmap:CLMap = mapping.clmap
  lazy val vimap:VIMap = mapping.vimap
  lazy val lumap:LUMap = mapping.lumap 
  lazy val ucmap:UCMap = mapping.ucmap

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
      case ctr:Ctr => 
        val pctr = ctmap(ctr)
        lookUp(pctr)
      case sm:SRAM =>
        val psram = smmap(sm)
        lookUp(psram)
      case pr@PipeReg(stage, reg) =>
        val pstage = stmap(stage)
        val pin = ipmap(pr.in)
        val ppr = pin.src.get 
        lookUp(pstage, ppr)
      case _ => throw new TODOException(s"Don't know how to lookUp ${node}"); "?"
    }
  }

  def lookUp(pnode:PNode):String = {
    pnode match {
      case PConstVal(c) => s"c${c}"
      case PConst() => throw PIRException(s"don't know how to lookUp PConst")
      case pst:PST => s"s${indexOf(pst)}"
      case pfu:PFU => lookUp(pfu.stage) 
      case pctr:PCtr => s"i${indexOf(pctr)}"
      case pib:PIB => s"bus${indexOf(pib)}"
      case psm:PSRAM => s"m${indexOf(psm)}"
      case _ => throw new TODOException(s"Don't know how to lookUp ${pnode}"); "?"
    }
  }

	def lookUp(banking: Banking):String = {
    banking match {
      case Strided(stride) => s"b${stride}"
      case Diagonal(stride1, stride2) => throw new TODOException(s"Don't support diagonal banking at the moment") 
      case Duplicated() => s"c" 
      case NoBanking() => s"no" 
    }
	}

  /*
   * @param pstage current stage
   * @param pn
   * */
  def lookUp(pstage:PST, pn:PNode):String = {
    pn match {
      case ppr:PPR =>
        if (indexOf(ppr.stage)==indexOf(pstage)) {
          s"l${indexOf(ppr.reg)}"
        } else if (indexOf(ppr.stage)==indexOf(pstage)-1) {
          s"r${indexOf(ppr.reg)}"
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

  def numWAStage(pcu:PCU):Int = {
    var lastWAStage = 0
    pcu.stages.zipWithIndex.foreach { case (pstage, i) =>
      if (stmap.pmap.contains(pstage)) {
        val stage = stmap.pmap(pstage)
        if (stage.isInstanceOf[WAST])
          lastWAStage = i
      }
    }
    lastWAStage
  }

  def localWADelay(pcu:PCU, ctr:Ctr):Int = {
    val cu = ctr.ctrler.asInstanceOf[ICL]
    val wasrams = cu.srams.filter(_.writeCtr==ctr)
    val wastages = pcu.stages.filter { pstage =>
      if (stmap.pmap.contains(pstage)) {
        val stage = stmap.pmap(pstage)
        stage match {
          case wast:WAST =>
            if (wasrams.forall(wasram => wast.srams.right.get.contains(wasram))) {
              true
            } else false
          case _ =>false 
        }
      } else false
    }
    if (wastages.size==0) 0 else indexOf(wastages.last) - indexOf(wastages.head)
  }

  //TODO
  val ctrlInterConnectDelay:Int = 1
  val dataInterConnectDelay:Int = 1
  val timeMplx = 1

  def localDoneDelay(pcu:PCU, ctr:Ctr):String = {
    val cchain = ctr.cchain
    if (cchain.isCopy) { "0" }
    else if (cchain.outer!=ctr) { "0" }
    else {
      val delay = (pcu.stages.size - numWAStage(pcu)) * timeMplx + 
      (ctrlInterConnectDelay - dataInterConnectDelay)
      s"${delay}"
    }
  }

  def writeAddrStartDelay(pcu:PCU, ctr:Ctr):String = {
    val cchain = ctr.cchain
    if (!cchain.isCopy) { "0" }
    else if (cchain.inner!=ctr) { "0" }
    else {
      var fromCU = cchain.copy.get.ctrler.asInstanceOf[CU]
      fromCU = fromCU match {
        case i:ICL => i
        case o:OCL => o.inner
      }
      val pFromCU = clmap(fromCU).asInstanceOf[PCU]
      val delay = (pFromCU.stages.size - numWAStage(pFromCU)) * timeMplx +
                  dataInterConnectDelay - localWADelay(pcu, ctr)
      s"${delay}"
    }
  }

  def emitSRAMs(pcu:PCU)(implicit ms:CollectionStatus) = {
    emitList(s"scratchpads") { implicit ms =>
      pcu.srams.foreach{ psram => 
        emitMap { implicit ms =>
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
              case v:VI => lookUp(vimap(v))
              case s:PR => "local"
            }
            emitPair("wd", wd)
            emitPair("wen",lookUp(sram.writeCtr))
            emitPair("banking", lookUp(sram.banking))
            emitPair("dblBuf", sram.doubleBuffer.toString)
            emitPair("rswap", lookUp(sram.swapRead))
            emitPair("rswap", lookUp(sram.swapWrite))
          } else {
            emitPair("ra", "x")
            emitPair("wa", "x")
            emitPair("wd", "x")
            emitPair("wen", "x")
            emitPair("banking", "x")
            emitPair("dblBuf", "false")
            emitPair("rswap", "x")
            emitPair("rswap", "x")
          }
        }
      }
    }
  }

  def emitCounterChains(pcu:PCU)(implicit ms:CollectionStatus) = {
    emitList(s"counterChain") { implicit ms =>
      val pctrs = pcu.ctrs
      val ctrs = pctrs.zipWithIndex.map{ case (pctr,i) => 
        val ctr = if (ctmap.pmap.contains(pctr)) ctmap.pmap(pctr).toString
        else s"not mapped"
        s"$i -> ${ctr}"
      }
      emitComment("ctrs", s"[${ctrs.mkString(",")}]")
      val chain = List.tabulate(pctrs.size-1) { i =>
        if (ctmap.pmap.contains(pctrs(i))&&ctmap.pmap.contains(pctrs(i+1))) {
          val ctr = ctmap.pmap(pctrs(i))
          val ctrp1 = ctmap.pmap(pctrs(i+1)) 
          if (ctrp1.en.from == ctr.done) s""""1""""
          else s""""0""""
        } else s""""0""""
      }
      emitList("chain", chain)
      emitMap("counters") { implicit ms =>
        pcu.ctrs.foreach { pctr =>
          emitMap { implicit ms =>
            if (ctmap.pmap.contains(pctr)) {
              val ctr = ctmap.pmap(pctr)
              emitPair("max", lookUp(ctr.max))
              emitPair("min", lookUp(ctr.min))
              emitPair("stride", lookUp(ctr.step))
              emitPair("startDelay", writeAddrStartDelay(pcu, ctr))
              emitPair("endDelay",  localDoneDelay(pcu, ctr))
            } else {
              emitPair("max", "x")
              emitPair("min", "x")
              emitPair("stride", "x")
              emitPair("startDelay", "x")
              emitPair("endDelay", "x")
            }
          }
        }
      }
    }
  }
  def emitStages(pcu:PCU)(implicit ms:CollectionStatus) = {
    emitList(s"pipeStage") { implicit ms =>
      pcu.stages.foreach { pstage =>
        emitMap { implicit ms =>
          pstage match {
            case s:PFUST =>
              val pfu = s.fu
              if (stmap.pmap.contains(pstage)) { //Physical stage have corresponding pir stage
                val fu = stmap.pmap(pstage).fu.get
                if (fu.operands.size>2)
                  throw PIRException(s"Don't support any operation with more than 2 operands at the moment ${fu.operands}")
                val stage = stmap.pmap(pstage)
                stage match {
                  case wast:WAST => 
                    val srams = wast.srams.right.get
                    val ctrlers = srams.map(_.ctrler).toSet
                    assert(ctrlers.size==1, s"Cannot have write addr calculation stage for srams from different ctrlers [${ctrlers.mkString(",")}]")
                    emitPair(s"en", lookUp(ctrlers.head.localCChain.inner))
                  case _ =>
                    emitPair(s"en", lookUp(stage.ctrler.localCChain.inner))
                }
                emitComment("stage", s"${pstage} <- ${stage}")
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
                if (stage.isInstanceOf[RDST]) {
                  op = "r" + op
                }
                emitPair("opcode", s"${op}")
                val results = fu.out.to
                val pips= results.map(result => ipmap(result))
                val reses = pips.map(pip => lookUp(pstage, pip.src.get)) 
                emitList("result", reses.map(r => s""""$r"""").toList)
                val inits = results.map(_.src).collect { 
                  case PipeReg(s,r) => r }.collect {
                    case AccumPR(_, Const(_, c)) => c 
                }
                if (inits.size>1)
                  throw PIRException(s"Currently assume writing to a single accum per stage ${inits}")
                else if (inits.size==1)
                  emitPair("accumInit", s"c${inits.head}")
              } else {
                emitComment("stage", s"${pstage} <- no map")
                if (clmap.pmap.contains(pcu)) {
                  val cu = clmap.pmap(pcu).asInstanceOf[CU]
                  emitPair(s"en", lookUp(cu.localCChain.inner))
                }
                emitPair("opA", "x")
                emitPair("opB", "x")
                emitPair("opcode", "x")
                emitList("result", Nil)
              }
            case _ =>
          }
          val rstrs = pstage.prs.flatMap { case (preg, ppr) =>
            assert(pstage==ppr.stage)
            if (fpmap.contains(ppr.in)) {
              fpmap(ppr.in).src.get match {
                case p:PFU => Some(s""""r${indexOf(preg)}" : "alu"""")
                case p:PSI => None
                case p => Some(s""""r${indexOf(preg)}" : "${lookUp(pstage, p)}"""")
              }
            } else None
          }
          emitList("fwd", rstrs.map(s => s"{${s}}").toList)
        }
      }
    }
  }
  def emitCtrl(pcu:PCU)(implicit ms:CollectionStatus) {
    emitMap(s"control") { implicit ms =>
      emitList("tokenDownLUT") { implicit ms =>
        emitMap { implicit ms =>
          val ptdlut = pcu.ctrlBox.tokDownLUT
          val table = if (!lumap.pmap.contains(ptdlut)) {
            CtrlCodegen.lookUpX(ptdlut.numIns)
          } else {
            val tdlut = lumap.pmap(ptdlut)
            val inits = ListBuffer[IP]()
            val tos = ListBuffer[OP]()
            val map:Map[OP, Int] = Map.empty
            tdlut.ins.foreach { in =>
              in.from.src match {
                case t:Top =>
                  inits += in
                case p:PRIM => 
                  if (p.ctrler==tdlut.ctrler.parent)
                    inits += in
                  else
                    tos += in.from
                case c =>
                  emitln(s"${c}")
              }
            }
            assert(inits.size <= 1, s"inits:${inits}")
            emitComment("IO", s"tdlut.ins:${tdlut.ins.map(_.from)} init:${inits.head} tos:${tos}")
            inits.foreach { init =>
              val pip = ipmap(init).asInstanceOf[PBIP]
              map += (init.from -> indexOf(pip))
            }
            tos.foreach { to =>
              map += (to -> indexOf(ucmap(to.src.asInstanceOf[UC])))
            }
            val tf:List[Boolean] => Boolean = tdlut.transFunc.tf(map, _)
            emitComment(s"${tdlut}", s"TransferFunction: ${tdlut.transFunc.info}, ${map}")
            CtrlCodegen.lookUp(ptdlut.numIns, tf)
          }
          emitPair("table", s"${table}")
        }
      }
      val doneXbar = ListBuffer[String]()
      emitList("tokenOutLUT") { implicit ms =>
        pcu.ctrlBox.tokOutLUTs.foreach { ptolut =>
          emitMap { implicit ms =>
            val table = if (!lumap.pmap.contains(ptolut)) {
              CtrlCodegen.lookUpX(ptolut.numIns)
            } else {
              val tolut = lumap.pmap(ptolut)
              val ctrs = tolut.ins.map(_.from.src.asInstanceOf[Ctr])
              assert(ctrs.size<=2)
              val map:Map[OP, Int] = Map.empty
              doneXbar ++= List.tabulate(2) { i => // sel for Xbar
                if (i<ctrs.size) s""""${indexOf(ctmap(ctrs(i)))}"""" 
                else s""""x""""
              }
              ctrs.zipWithIndex.foreach { case (ctr,i) =>
                map += (ctr.done -> i)
              }
              val tf:List[Boolean] => Boolean = tolut.transFunc.tf(map, _)
              emitComment(s"${tolut}", s"TransferFunction: ${tolut.transFunc.info}, ${map}")
              CtrlCodegen.lookUp(ptolut.numIns, tf)
            }
            emitPair("table", s"${table}")
          }
        }
      }
      val tom = pcu.ctrlBox.tokenOuts.map { to =>
        if (opmap.pmap.contains(to)) {
          if (opmap.pmap(to).src.isInstanceOf[EnLUT]) s""""1""""
          else s""""0""""
        } else s""""x""""
      }
      emitList("tokenOutMux", tom)
      emitMap("doneXbar") { implicit ms =>
        emitList("outSelect", doneXbar.toList)
      }
      val incs = ListBuffer[String]() 
      val decs = ListBuffer[String]() 
      val initVals = ListBuffer[String]() 
      pcu.ctrlBox.udcs.map { pudc =>
        if (ucmap.pmap.contains(pudc)) {
          // inc
          val udc = ucmap.pmap(pudc)
          val inc = if (udc.inc.isConnected) {
            val pip = ipmap(udc.inc).asInstanceOf[PBIP]
            s""""${indexOf(pip)}""""
          } else { s""""x"""" }
          incs += inc 
          // dec
          val ctr = udc.dec.from.src.asInstanceOf[Ctr]
          val pctr = ctmap(ctr)
          decs += s""""${indexOf(pctr)}""""
          initVals += s""""${udc.initVal}""""
        } else {
          incs += s""""x""""
          decs += s""""x""""
          initVals += s""""x""""
        }
      }
      emitMap("incXbar") { implicit ms =>
        emitList("outSelect", incs.toList)
      }
      emitMap("decXbar") { implicit ms =>
        emitList("outSelect", decs.toList)
      }
      emitList("udcInit", initVals.toList)
      emitList("enableLUT") { implicit ms =>
        pcu.ctrlBox.enLUTs.foreach { penlut => 
          emitMap { implicit ms =>
            val table = if (!lumap.pmap.contains(penlut)) {
              s"""["x"]"""
            } else {
              val enlut = lumap.pmap(penlut)
              val udcs = enlut.ins.map(_.from.src.asInstanceOf[UC])
              val map:Map[OP, Int] = Map.empty
              udcs.foreach { udc =>
                val pudc = ucmap(udc)
                map += (udc.out -> indexOf(pudc))
              }
              val tf:List[Boolean] => Boolean = enlut.transFunc.tf(map, _)
              emitComment(s"${enlut}", s"TransferFunction: ${enlut.transFunc.info}, ${map}")
              CtrlCodegen.lookUp(penlut.numIns, tf)
            }
            emitPair("table", s"${table}")
          }
        }
      }
      val emuxs = pcu.ctrs.zipWithIndex.map { case (pctr, i) => 
        if (ctmap.pmap.contains(pctr)) {
          val ctr = ctmap.pmap(pctr)
          ctr.en.from.src match {
            case e:EnLUT =>
              val penlut = lumap(e).asInstanceOf[PEnLUT]
              val cu = clmap.pmap(pcu)
              if (e.ctrler==cu) {
                assert(indexOf(penlut) == i)
                s""""0""""
              } else { // from token in
                //TODO: config interconnect
                s""""1""""
              }
            case c:Ctr => //Chained
              s""""x""""
          }
        } else {
          s""""x""""
        }
      }
      emitList(s"enableMux", emuxs)
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
                emitComment(s"${cu}")
              }
              emitSRAMs(pcu)
              emitCounterChains(pcu)
              emitStages(pcu)
              emitCtrl(pcu)
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
object CtrlCodegen {
  def lookUp(numBits:Int, transFunc: List[Boolean] => Boolean):String = {
    val size:Int = Math.pow(2, numBits).toInt
    val table = ListBuffer[Boolean]()
    for (i <- 0 until size) {
      var inputs = i.toBinaryString.toList.map(_ == '1') // Boolean inputs
      inputs = List.fill(numBits-inputs.size)(false) ++ inputs
      table += transFunc(inputs)
    }
    val l = table.map(b => if (b) s""""1"""" else """"0"""" ).toList.mkString(",")
    s"[${l}]"
  }
  def lookUpX(numBits:Int):String = {
    val l = List.fill(Math.pow(2, numBits).toInt)(s""""x"""").mkString(",")
    s"[$l]" 
  }
  def printTable(table:List[String]) = {
    val size = table.size
    val numBits = Math.ceil(Math.log(size)/Math.log(2)).toInt
    dprintln(s"----- Start ------")
    for (i <- 0 until size) {
      dprintln(f"${int2Bin(i, numBits+1)} ${table(i)}")
    }
    dprintln(s"----- End ------")
  }

  def int2Bin(i:Int, width:Int):String = {
    val fmt = s"%${width}s"
    String.format(fmt, Integer.toBinaryString(i)).replace(' ', '0')
  }

  def bool2Bin(i:Boolean):String = if (i) "1" else "0"
}
