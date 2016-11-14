package pir.graph.traversal

import pir.Design
import pir.codegen._
import pir.misc._
import pir.typealias._
import pir.plasticine.main._
import pir.graph.enums._
import pir.graph.mapper._
import pir.graph.{EnLUT => _, ScalarInPR, _}
import pir.plasticine.graph.{ ConstVal => PConstVal, Const => PConst}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class PisaCodegen(pirMapping:PIRMapping)(implicit design: Design) extends Traversal with JsonCodegen with Metadata with DebugLogger {
  lazy val dir = sys.env("PLASTICINE_HOME") + "/apps"
  override val stream = newStream(dir, s"${design}.json") 
  
  implicit lazy val spade:Spade = design.arch

  // Mapping results
  lazy val mapping:PIRMap = pirMapping.mapping
  lazy val vimap:VIMap = mapping.vimap
  lazy val vomap:VOMap = mapping.vomap
  lazy val opmap:OPMap = mapping.opmap
  lazy val ipmap:IPMap = mapping.ipmap
  lazy val fpmap:FPMap = mapping.fpmap
  lazy val stmap:STMap = mapping.stmap
  lazy val ctmap:CTMap = mapping.ctmap
  lazy val smmap:SMMap = mapping.smmap
  lazy val clmap:CLMap = mapping.clmap
  lazy val fbmap:FBMap = mapping.fbmap
  lazy val lumap:LUMap = mapping.lumap 
  lazy val ucmap:UCMap = mapping.ucmap
  lazy val rtmap:RTMap = mapping.rtmap
  lazy val simap:SIMap = mapping.simap
  lazy val somap:SOMap = mapping.somap
  lazy val rcmap:RCMap = mapping.rcmap

  override def traverse:Unit = {
    if (pirMapping.fail) return
    implicit val ms = new CollectionStatus(false)
    emitBlock {
      emitMap("PISA") { implicit ms =>
        emitPair("version", 0.1)
        emitMap("topconfig"){ implicit ms =>
          emitPair("type", "plasticine")
          emitMap("config") { implicit ms =>
            emitMain
          }
        }
      }
      pprintln
    }
  }

  override def finPass = {
    close
    info(s"Finishing PisaCodegen in ${getPath}")
  }

  def emitMain(implicit ms:CollectionStatus) = {
    emitTop
    emitCUs
    emitNetwork
    emitMCs
  }

  def emitTop(implicit ms:CollectionStatus) = {
    val argOuts = ListBuffer[String]()
    design.top.sins.foreach { sin =>
      argOuts += s"${sin.scalar} -> ${indexOf(simap(sin).outport.get)}"
    }
    val argIns = ListBuffer[String]()
    design.top.souts.foreach { sout =>
      argIns += s"${sout.scalar} -> ${indexOf(somap(sout).inport.get)}"
    }
    emitComment("argIns", argIns.mkString(","))
    emitComment("argOuts", argOuts.mkString(","))
    emitMap("top") { implicit ms =>
      design.arch match {
        case sn:SwitchNetwork =>
          // Status
          val status = fbmap(vimap(design.top.status.from))
          val bottomRow = sn.csbs.map{_.head}
          val topRow = sn.csbs.map{_.last}
          val obs = bottomRow.flatMap{_.voutAt("S")} ++ topRow.flatMap{_.voutAt("N")}
          val idx = obs.indexOf(status)
          emitPair(s"done", s"$idx")
          // ArgOutBus
          val ao = vimap(design.top.status.from)
          assert(design.arch.top.vins.size==1)
          if (!design.top.vins.isEmpty) {
            assert(design.top.vins.size==1)
            val aob = fbmap(vimap(design.top.vins.head))
            val bottomRow = sn.sbs.map{_.head}
            val topRow = sn.sbs.map{_.last}
            val obs = bottomRow.flatMap{_.voutAt("S")} ++ topRow.flatMap{_.voutAt("N")}
            val idx = obs.indexOf(aob)
            emitPair(s"argOut", s"$idx")
          }
        case pn:PointToPointNetwork =>
      }
    }
  }

  def emitCUs(implicit ms:CollectionStatus) = {
    emitList("cu") { implicit ms =>
      design.arch.rcus.foreach { pcu =>
        if (clmap.pmap.contains(pcu)) {
          emitMap { implicit ms =>
            var comment = design.arch match {
              case sn:SwitchNetwork => s"$pcu[${coordOf(pcu)}]"
              case pn:PointToPointNetwork => s"$pcu[${indexOf(pcu)}] "
            } 
            if (clmap.pmap.contains(pcu)) {
              val cu = clmap.pmap(pcu)
              comment += s" <- ${cu}"
            } else {
              comment += s" <- no mapping"
            }
            emitComment("CUName", comment)
            //emitInterconnect(pcu)
            emitScalar(pcu)
            emitOnChipMems(pcu)
            emitCounterChains(pcu)
            emitStages(pcu)
            emitCtrl(pcu)
          }
        } else {
          emitElem("x")
        }
      }
    }
  }

  def emitNetwork(implicit ms:CollectionStatus) = {
    design.arch match {
      case sn:SwitchNetwork =>
        emitList("dataSwitch") { implicit ms =>
          sn.sbs.flatten.foreach { sb =>
            emitSwitch(sb)
          }
        }
        emitList("controlSwitch") { implicit ms =>
          sn.csbs.flatten.foreach { sb =>
            emitSwitch(sb)
          }
        }
      case _ =>
    }
  }

  def emitMCs(implicit ms:CollectionStatus) = {
    emitList("mu") { implicit ms:CollectionStatus =>
      design.arch.mcs.foreach { pmc =>
        if (clmap.pmap.contains(pmc)) {
          val mc = clmap.pmap(pmc).asInstanceOf[MC]
          emitMap { implicit ms:CollectionStatus =>
            val isWr = mc.mctpe match {
              case TileLoad => "0" 
              case TileStore => "1"
            }
            emitPair("isWr", s"${isWr}")
            emitPair("scatterGatter", "0")
            emitComment("CommandFIFO-enqueueEnable", s"${indexOf(vimap(mc.commandFIFO.enqueueEnable.from))}")
            mc.dataFIFO.foreach { dataFIFO =>
              emitComment("DataFIFO-enqueueEnable", s"${indexOf(vimap(dataFIFO.enqueueEnable.from))}")
              emitComment("DataFIFO-notFull", s"${indexOf(vomap(dataFIFO.notFull))}")
            }
            emitComment("CommandFIFO-notFull", s"${indexOf(vomap(mc.commandFIFO.notFull))}")
          }
          emitCounterChains(pmc)
        } else {
          emitElem("x")
        }
      }
    }
  }

  def emitScalar(pcu:PCU)(implicit ms:CollectionStatus) = {
    var siXbar = ListBuffer[String]() 
    pcu.etstage.prs.foreach { case (preg, ppr) =>
      val psins = ppr.in.fanIns.map(_.src).collect {case psi:PSI => psi}
      if (psins.size!=0) { // Is scalarIn Register
        val mpsins = psins.filter { psin =>
          simap.pmap.get(psin).fold(false) { sin => 
            val reg = sin.ctrler.asInstanceOf[CU].scalarInPR(sin)
            rcmap(reg) == preg
          }
        }
        if (mpsins.size==0) siXbar += s""""x""""
        else if(mpsins.size==1) siXbar += s""""${indexOf(mpsins.head)}""""
        else throw PIRException(s"ScalarIn Register $ppr is mapped to two scalarIns $mpsins")
      }
    }
    emitXbar("scalarInXbar", siXbar.toList)
    val cu = clmap.pmap(pcu).asInstanceOf[ComputeUnit]
    cu match {
      case cu:UnitPipeline => emitPair("scalarOutMux", "1")
      case cu => emitPair("scalarOutMux", "0")
    }
    val simux = ListBuffer[String]()
    pcu.regs.foreach { reg => 
      if (pcu.etstage.prs(reg).in.fanIns.exists(_.src.isInstanceOf[PSI])) {
        simux += "0" //TODO
      }
    }
    emitXbar("scalarInMux", simux.toList)
  }

  def emitSwitch(sb:PSB)(implicit ms:CollectionStatus) = {
    val ins = ListBuffer[String]()
    sb.vouts.foreach { pvout =>
      if (pvout.isConnected) {
        if (fpmap.contains(pvout.voport)) {
          val vin = fpmap(pvout.voport).src.asInstanceOf[PIB]
          ins += s""""${sb.io(vin)}""""
        } else {
          ins += s""""x""""
        }
      }
    }
    //emitXbar(ins.toList)
    emitMap { implicit ms =>
      emitComment(s"sb", DotCodegen.quote(sb))
      emitList("outSelect", ins.toList)
    }
  }

  def lookUp(op:Op):String = {
    op match {
      // Fix Point Operations
      case FixAdd => s"+"
      case FixSub => s"-"
      case FixMul => s"*"
      case FixDiv => s"/"
      case FixLt  => s"<"
      case FixLeq => s"<=" 
      case FixEql => s"==" 
      case FixNeq => s"!=" 
      case FixMod => s"%" 
      // Floating Point Operations
      case FltAdd => s"f+"
      case FltSub => s"f-"
      case FltMul => s"f*"
      case FltDiv => s"f/"
      case FltLt  => s"f<"
      case FltLeq => s"f<=" 
      case FltEql => s"f==" 
      case FltNeq => s"f!=" 
      // Others
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
      case sm:OnMem =>
        val psram = smmap(sm)
        lookUp(psram)
      case pr@PipeReg(stage, reg) =>
        val pstage = stmap(stage)
        val pin = ipmap(pr.in)
        val ppr = pin.src
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
   * @param pn spade node to look up
   * */
  def lookUp(pstage:PST, pn:PNode):String = {
    pn match {
      case ppr:PPR => // Look up node is a Pipeline Register
        if (indexOf(ppr.stage) == -1) { // Empty stage
          s"e${indexOf(ppr.reg)}"
        } else if (pstage.isInstanceOf[PRDST]) {
          s"t${indexOf(ppr.reg)}"
        } else if (indexOf(ppr.stage)==indexOf(pstage)) { // refering stage is current stage
          s"l${indexOf(ppr.reg)}"
        } else if (indexOf(ppr.stage)==indexOf(pstage)-1) { // refering stage is previous stage
          s"r${indexOf(ppr.reg)}"
        } else {
          throw PIRException(s"Reading from not accessable stage curr:${pstage}, ${ppr.stage}")
        }
      case _ => lookUp(pn)
    }
  }

  /*
   * Figure out the index of last mapped write addr calculation stage/number of write addr
   * calculation stage
   * @param pcu Spade Compute Unit
   * */
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
    cu match {
      case mc:MC => 0
      case icl:ICL =>
        val wasrams = icl.mems.collect{ case sm:SOW => sm}.filter(_.writeCtr==ctr)
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
  }

  //TODO
  val timeMplx = 1

  /* Calculate the number of active local stages*/
  def numLocalStages(pcu:PCU):Int = {
    pcu.stages.zipWithIndex.foreach { case (pst, idx) =>
      if (stmap.pmap.contains(pst)) {
        stmap.pmap(pst) match {
          case st:LocalStage => return pcu.stages.size-idx
          case _ =>
        }
      }
    }
    throw PIRException(s"PCU doesn't have any mapped stage?")
  }

  /* Calculate amount of delay to start the inner counter */
  def startDelay(pcu:PCU, ctr:Ctr):String = {
    val cchain = ctr.cchain
    if (!ctr.isInner) { "0" }
    else if (cchain.isLocal) {
      assert(ctr.ctrler.isInstanceOf[ICL])
      val icl = ctr.ctrler.asInstanceOf[ICL]
      val delays = ctr.ctrler.vins.map { vin =>
        vin.tokenIn.fold(0) { cin =>
          val dataInterConnectDelay = rtmap(vin)
          val ctrlInterConnectDelay = rtmap(cin.from)
          assert(dataInterConnectDelay==ctrlInterConnectDelay)
          0 //TODO: assume data delay matches control delay for all inputs for now
        }
      } 
      if (delays.size==0) "0"
      else s"${delays.max}"
    } else if (!cchain.isCopy) { "0" }
    else { // Write Address Start Delay
      if (ctr.cchain.wasrams.size==0) { "0" } else {
        assert(ctr.cchain.wasrams.size==1)
        val sram = ctr.cchain.wasrams.head
        assert(sram.isRemoteWrite)
        val vin = sram.writePort.from.src.asInstanceOf[VecIn]
        val fromCU = vin.writer.ctrler
        val pFromCU = clmap(fromCU).asInstanceOf[PCU]
        val dataInterConnectDelay = rtmap(vin)
        val ctrlInterConnectDelay = rtmap(ctr.en.from)
        //TODO: assume data delay matches control delay for all inputs for now
        val delay = numLocalStages(pFromCU) + dataInterConnectDelay - ctrlInterConnectDelay 
        s"${delay}"
      }
    }
  }

  /* amount of delay to decalre done of the counter */
  def doneDelay(pcu:PCU, ctr:Ctr):String = {
    val cchain = ctr.cchain
    if (!cchain.isLocal) { "0" }
    else if (ctr.isInner) { s"${numLocalStages(pcu)}" }
    else { "0" }
  }

  def emitInterconnect(pcu:PCU)(implicit ms:CollectionStatus) = {
    val inputs = pcu.vins.map { pvin =>
      fbmap.get(pvin).fold(s""""x"""") { pob => s""""${indexOf(pob.src)}""""}
    }
    emitList("inputs", inputs)
  }

  def emitOnChipMems(pcu:PCU)(implicit ms:CollectionStatus) = {
    emitList(s"scratchpads") { implicit ms =>
      pcu.srams.foreach{ psram => 
        if (smmap.pmap.contains(psram)) {
          emitMap { implicit ms =>
            val mem = smmap.pmap(psram)
            mem match {
              case mem:SOR =>
                emitPair("ra", lookUp(mem.readAddr))
                emitPair("deqEn", "x")
                emitPair("isReadFifo", "0")
              case mem:FOR =>
                emitPair("ra", "x")
                val enlut = mem.dequeueEnable.from.src.asInstanceOf[EnLUT]
                emitPair("deqEn", s"${indexOf(lumap(enlut))}")
                emitPair("isReadFifo", "1")
            }
            mem match {
              case mem:SOW =>
                mem.writeAddr.from.src match {
                  case pr:PR => 
                    emitPair("wa", "local")
                  case _ => 
                    emitPair("wa", lookUp(mem.writeAddr))
                }
                emitPair("wen", lookUp(mem.writeCtr))
                emitPair("start", "x")
                emitPair("end", "x")
                emitPair("enqEn", "x")
                emitPair("isWriteFifo", "0")
              case mem:FOW =>
                emitPair("wa", "x")
                emitPair("wen", "x")
                if (mem.start.isDefined)
                  emitPair("start", s"${lookUp(mem.start.get.src)}")
                else
                  emitPair("start", s"x")
                if (mem.end.isDefined)
                  emitPair("end", s"${lookUp(mem.end.get.src)}")
                else
                  emitPair("end", s"x")
                //emitPair(s"enqEn", s"${pcu.ctrlBox.io(vimap(mem.enqueueEnable.from))}")
                emitPair(s"enqEn", s"${indexOf(vimap(mem.enqueueEnable.from))}")
                emitPair("isWriteFifo", "1")
            }
            val wd = mem.writePort.from.src match {
              case v:VI => lookUp(vimap(v))
              case s:PR => "local"
            }
            emitPair("wd", wd)
            emitPair("banking", lookUp(mem.banking))
            mem.buffering match {
              case MultiBuffer(n, swapRead, swapWrite) =>
                emitPair("numBufs", n)
                emitPair("rswap", lookUp(swapRead))
                emitPair("wswap", lookUp(swapWrite))
              case DoubleBuffer(swapRead, swapWrite) =>
                emitPair("numBufs", 2)
                emitPair("rswap", lookUp(swapRead))
                emitPair("wswap", lookUp(swapWrite))
              case SingleBuffer() =>
                emitPair("numBufs", 1)
                emitPair("rswap", "x")
                emitPair("wswap", "x")
            }
          }
        } else {
          emitElem("x")
        }
      }
    }
  }

  def emitCounterChains(pcu:PCU)(implicit ms:CollectionStatus) = {
    emitMap(s"counterChain") { implicit ms =>
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
      emitList("counters") { implicit ms =>
        pcu.ctrs.foreach { pctr =>
          if (ctmap.pmap.contains(pctr)) {
            val ctr = ctmap.pmap(pctr)
            emitMap { implicit ms =>
              emitPair("max", lookUp(ctr.max))
              emitPair("min", lookUp(ctr.min))
              emitPair("stride", lookUp(ctr.step))
              emitPair("startDelay", startDelay(pcu, ctr))
              emitPair("endDelay",  doneDelay(pcu, ctr))
            }
          } else {
            emitElem("x")
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
                emitComment("stage", s"${pstage} <- ${stage}${PIRPrinter.genFields(stage)}")
                // Operand
                val popA = fpmap(pfu.operands.head)
                emitPair("opA", lookUp(pstage, popA.src))
                if (fu.operands.size < 2)
                  emitPair("opB", "x")
                else {
                  val popB = fpmap(pfu.operands(1))
                  emitPair("opB", lookUp(pstage, popB.src))
                }
                if (fu.operands.size < 3)
                  emitPair("opC", "x")
                else {
                  val popC = fpmap(pfu.operands(2))
                  emitPair("opC", lookUp(pstage, popC.src))
                }
                emitPair("opcode", s"${lookUp(fu.op)}")
                val results = fu.out.to
                val pips= results.map(result => ipmap(result))
                val reses = pips.map(pip => lookUp(pstage, pip.src)) 
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
                emitPair("opC", "x")
                emitPair("opcode", "x")
                emitList("result", Nil)
                emitPair("accumInit", s"x")
              }
            case _ =>
          }
          val rstrs = pstage.prs.flatMap { case (preg, ppr) =>
            assert(pstage==ppr.stage)
            if (fpmap.contains(ppr.in)) {
              fpmap(ppr.in).src match {
                case p:PFU => Some((s"r${indexOf(preg)}", "alu"))
                case p:PSI => None
                case p => Some((s"r${indexOf(preg)}", lookUp(pstage, p)))
              }
            } else None
          }
          emitLineMap("fwd", rstrs.toList)
        }
      }
    }
  }

  def emitXbar(outSelect:List[String])(implicit ms:CollectionStatus) = {
    emitMap { implicit ms =>
      emitList("outSelect", outSelect)
    }
  }
  def emitXbar(name:String, outSelect:List[String])(implicit ms:CollectionStatus) = {
    emitMap(name) { implicit ms =>
      emitList("outSelect", outSelect)
    }
  }

  def emitCtrl(pcu:PCU)(implicit ms:CollectionStatus) {
    val pcb = pcu.ctrlBox
    emitMap(s"control") { implicit ms =>
      emitList("tokenDownLUT") { implicit ms =>
        pcb.tokenDownLUTs.foreach { ptdlut =>
          emitMap { implicit ms =>
            if (!lumap.pmap.contains(ptdlut)) {
              CtrlCodegen.lookUpX(ptdlut.numIns)
              emitPair("table", CtrlCodegen.lookUpX(ptdlut.numIns))
              emitPair("syncTokenMux", "x")
            } else {
              val tdlut = lumap.pmap(ptdlut)
              val inits = ListBuffer[CIP]()
              val tos = ListBuffer[COP]()
              val map:Map[COP, Int] = Map.empty
              tdlut.ins.foreach { in =>
                in.from.src match {
                  case t:Top => inits += in
                  case p:PRIM => 
                    if (p.ctrler==tdlut.ctrler.parent) inits += in
                    else tos += in.from.asInstanceOf[COP]
                  case c => emitln(s"${c}") //TODO?
                }
              }
              assert(inits.size <= 1, s"inits:${inits}")
              emitComment("IO", s"tdlut.ins:${tdlut.ins.map(_.from)} init:${inits.head} tos:${tos}")
              if (inits.size==0) {
                emitPair("syncTokenMux", "x")
              } else {
                val init = inits.head
                val pcin = vimap(init.from)
                emitPair("syncTokenMux", s"${indexOf(pcin)}")
                map += (init.from.asInstanceOf[COP] -> 0) // Assume init is the first input
              }
              tos.foreach { to =>
                map += (to -> (indexOf(ucmap(to.src.asInstanceOf[UC]))+1) ) // Assume init is the first input
              }
              val tf:List[Boolean] => Boolean = tdlut.transFunc.tf(map, _)
              emitComment(s"${tdlut}", s"TransferFunction: ${tdlut.transFunc.info}, ${map}")
              val table = CtrlCodegen.lookUp(ptdlut.numIns, tf)
              //CtrlCodegen.printTable(ptdlut.numIns, tdlut.transFunc, map)
              emitList("table", table)
            }
          }
        }
      }
      val doneXbar = ListBuffer[String]()
      emitList("tokenOutLUT") { implicit ms =>
        pcb.tokenOutLUTs.foreach { ptolut =>
          emitMap { implicit ms =>
            if (!lumap.pmap.contains(ptolut)) {
              CtrlCodegen.lookUpX(ptolut.numIns)
              emitPair("table", CtrlCodegen.lookUpX(ptolut.numIns))
              doneXbar ++= List.tabulate(2) { i => s""""x"""" }
            } else {
              val tolut = lumap.pmap(ptolut)
              val ctrs = tolut.ins.map(_.from.src.asInstanceOf[Ctr])
              assert(ctrs.size<=2)
              val map:Map[COP, Int] = Map.empty
              doneXbar ++= List.tabulate(2) { i => // sel for Xbar
                if (i<ctrs.size) s""""${indexOf(ctmap(ctrs(i)))}"""" 
                else s""""x""""
              }
              ctrs.zipWithIndex.foreach { case (ctr,i) =>
                map += (ctr.done -> i)
              }
              val tf:List[Boolean] => Boolean = tolut.transFunc.tf(map, _)
              emitComment(s"${tolut}", s"TransferFunction: ${tolut.transFunc.info}, ${map}")
              val table = CtrlCodegen.lookUp(ptolut.numIns, tf)
              emitList("table", table)
            }
          }
        }
      }
      val tom = pcb.ctrlOuts.map { pto =>
        vomap.pmap.get(pto).fold (s""""x"""") { t =>
          val to = t.asInstanceOf[Port]
          val idx = to.src match {
            case l:FOW => indexOf(smmap(l))
            case l:TokenDownLUT => pcu.srams.size + indexOf(lumap(l)) 
            case l:TokenOutLUT => pcu.srams.size + pcb.tokenDownLUTs.size + indexOf(lumap(l)) 
            case l:EnLUT => pcu.srams.size + pcb.tokenDownLUTs.size + pcb.tokenOutLUTs.size + indexOf(lumap(l))
          }
          s""""$idx""""
        }
      }
      emitXbar("tokenOutXbar", tom)
      emitXbar("doneXbar", doneXbar.toList)
      val incs = ListBuffer[String]() 
      val decs = ListBuffer[String]() 
      val inits = ListBuffer[String]() 
      val initVals = ListBuffer[String]() 
      val udcComment = ListBuffer[String]()
      pcb.udcs.map { pudc =>
        if (ucmap.pmap.contains(pudc)) {
          // inc
          val udc = ucmap.pmap(pudc)
          val inc = if (udc.inc.isConnected) {
            val pcin = vimap(udc.inc.from)
            s""""${indexOf(pcin)}""""
          } else { s""""x"""" }
          incs += inc
          val init = if (udc.init.isConnected) {
            val pcin = vimap(udc.init.from)
            s""""${indexOf(pcin)}""""
          } else { s""""x"""" }
          inits += init
          // dec
          val ctr = udc.dec.from.src.asInstanceOf[Ctr]
          val pctr = ctmap(ctr)
          decs += s""""${indexOf(pctr)}""""
          initVals += s""""${udc.initVal}""""
          udcComment += s"${udc} -> ${pudc}"
        } else {
          incs += s""""x""""
          decs += s""""x""""
          inits += s""""x""""
          initVals += s""""x""""
        }
      }
      emitComment("udc", udcComment.mkString(","))
      emitXbar("incXbar", (incs ++ inits).toList)
      emitXbar("decXbar", decs.toList)
      emitList("udcInit", initVals.toList)
      emitList("enableLUT") { implicit ms =>
        pcb.enLUTs.foreach { penlut => 
          emitMap { implicit ms =>
            if (!lumap.pmap.contains(penlut)) {
              emitPair("table", CtrlCodegen.lookUpX(penlut.numIns))
            } else {
              val enlut = lumap.pmap(penlut)
              val map:Map[COP, Int] = Map.empty
              enlut.ins.map(_.from.src).foreach { 
                case udc:UC => map += (udc.out -> indexOf(ucmap(udc)))
                case at:AT => map += (at.out -> 0) 
              }
              val tf:List[Boolean] => Boolean = enlut.transFunc.tf(map, _)
              emitComment(s"${enlut}", s"TransferFunction: ${enlut.transFunc.info}, ${map}")
              val table = CtrlCodegen.lookUp(penlut.numIns, tf)
              emitList("table", table)
            }
          }
        }
      }
      val tokIns = Array.fill(pcu.ctrs.size)(s""""x"""")
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
                tokIns(i) = s""""${indexOf(vimap(ctr.en.from))}""""
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
      emitXbar(s"tokenInXbar", tokIns.toList)
    }
  }

}
object CtrlCodegen extends DebugLogger {
  def lookUp(numBits:Int, transFunc: List[Boolean] => Boolean):List[String] = {
    val size:Int = Math.pow(2, numBits).toInt
    val table = genTable(numBits, transFunc)
    table.map(b => if (b) s""""1"""" else """"0"""" ).toList
  }
  def lookUpX(numBits:Int):String = {
    "x"
    //val l = List.fill(Math.pow(2, numBits).toInt)(s""""x"""").mkString(",")
    //s"[$l]" 
  }
  def genTable(numBits:Int, transFunc: List[Boolean] => Boolean):List[Boolean] = {
    val size:Int = Math.pow(2, numBits).toInt
    val table = ListBuffer[Boolean]()
    for (i <- 0 until size) {
      var inputs = i.toBinaryString.toList.map(_ == '1') // Boolean inputs
      inputs = List.fill(numBits-inputs.size)(false) ++ inputs
      table += transFunc(inputs)
    }
    table.toList
  }
  def printTable(numBits:Int, transFunc:TransferFunction, map:Map[COP, Int]):Unit = {
    val table = genTable(numBits, transFunc.tf(map, _))
    val size:Int = table.size
    dprintln(s"TransferFunction: ${transFunc.info}, map:${map} numBits:$numBits")
    dprintln(s"----- Start ------")
    for (i <- 0 until size) {
      dprintln(f"${int2Bin(i, numBits)} ${table(i)}")
    }
    dprintln(s"----- End ------")
  }

  def int2Bin(i:Int, width:Int):String = {
    val fmt = s"%${width}s"
    String.format(fmt, Integer.toBinaryString(i)).replace(' ', '0')
  }

  def bool2Bin(i:Boolean):String = if (i) "1" else "0"
}
