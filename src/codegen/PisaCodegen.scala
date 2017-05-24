package pir.codegen

import pir._
import pir.codegen._
import pir.util.typealias.{Const => _, _}
import pir.plasticine.main._
import pir.exceptions._
import pir.util.enums._
//import pir.util.{quote => _, _}
import pir.util.misc._
import pir.mapper._
import pir.graph.{EnLUT => _, ScalarInPR, _}
import pir.plasticine.graph.{ GridIO, PortType, Input}
import pir.plasticine.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class PisaCodegen()(implicit design: Design) extends Codegen with JsonCodegen {
  def shouldRun = Config.genPisa && design.mapping.nonEmpty
  lazy val dir = sys.env("PLASTICINE_HOME") + "/apps"
  override lazy val stream = newStream(dir, s"${design}.json") 
  val logger = new Logger {
    override lazy val stream = newStream(s"PisaCodegen.log")
  }
  import logger.{dprint, dprintln}
  import pirmeta.{indexOf => _, _}
  import spademeta._
  
  // Mapping results
  lazy val mapping:PIRMap = design.mapping.get
  lazy val vimap:VIMap = mapping.vimap
  lazy val vomap:VOMap = mapping.vomap
  lazy val opmap:OPMap = mapping.opmap
  lazy val ipmap:IPMap = mapping.ipmap
  lazy val stmap:STMap = mapping.stmap
  lazy val ctmap:CTMap = mapping.ctmap
  lazy val smmap:SMMap = mapping.smmap
  lazy val clmap:CLMap = mapping.clmap
  lazy val fimap:FIMap = mapping.fimap
  lazy val pmmap:PMMap = mapping.pmmap 
  lazy val rtmap:RTMap = mapping.rtmap
  lazy val rcmap:RCMap = mapping.rcmap

  override def traverse:Unit = {
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
    endInfo(s"Finishing PisaCodegen in ${getPath}")
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
      argOuts += s"${sin.scalar} -> ${indexOf(vimap(sin))}"
    }
    val argIns = ListBuffer[String]()
    design.top.souts.foreach { sout =>
      argIns += s"${sout.scalar} -> ${vomap(sout).map(pso => indexOf(pso)).mkString(",")}"
    }
    emitComment("argIns-busIdx", argIns.mkString(","))
    emitComment("argOuts-busIdx", argOuts.mkString(","))
    emitMap("top") { implicit ms =>
      design.arch match {
        case sn:SwitchNetwork =>
          // Status
          val status = fimap(vimap(design.top.ctrlBox.status))
          val bottomRow = sn.sbArray.map{_.head}
          val topRow = sn.sbArray.map{_.last}
          val obs = bottomRow.flatMap{_.scalarIO.outAt("S")} ++ topRow.flatMap{_.scalarIO.outAt("N")}
          val idx = obs.indexOf(status)
          emitPair(s"done", s"$idx")
          // ArgOutBus
          val ao = vimap(design.top.ctrlBox.status)
          assert(design.arch.top.vins.size==1)
          if (!design.top.vins.isEmpty) {
            assert(design.top.vins.size==1)
            val aob = fimap(vimap(design.top.vins.head))
            val bottomRow = sn.sbArray.map{_.head}
            val topRow = sn.sbArray.map{_.last}
            val obs = bottomRow.flatMap{_.scalarIO.outAt("S")} ++ topRow.flatMap{_.scalarIO.outAt("N")}
            val idx = obs.indexOf(aob)
            emitPair(s"argOut", s"$idx")
          }
        case pn:PointToPointNetwork =>
      }
    }
  }

  def emitCUs(implicit ms:CollectionStatus) = {
    emitList("cu") { implicit ms =>
      (design.arch.pcus++design.arch.mcus).foreach { pcu =>
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
          sn.sbs.foreach { sb =>
            emitSwitch(sb.vectorIO)
          }
        }
        emitList("controlSwitch") { implicit ms =>
          sn.sbs.foreach { sb =>
            emitSwitch(sb.ctrlIO)
          }
        }
      case _ =>
    }
  }

  def emitMCs(implicit ms:CollectionStatus) = {
    //emitList("mu") { implicit ms:CollectionStatus =>
      //design.arch.mcs.foreach { pmc =>
        //if (clmap.pmap.contains(pmc)) {
          //val mc = clmap.pmap(pmc).asInstanceOf[MC]
          //emitMap { implicit ms:CollectionStatus =>
            //val isWr = mc.mctpe match {
              //case TileLoad => "0" 
              //case TileStore => "1"
              //case Scatter => throw PIRException(s"Dont know opcode for ${mc.mctpe}") 
              //case Gather => throw PIRException(s"Dont know opcode for ${mc.mctpe}") 
            //}
            //emitPair("isWr", s"${isWr}")
            //emitPair("scatterGather", "0")
            //emitComment("ofsFIFO-enqueueEnable", s"${indexOf(vimap(mc.ofsFIFO.get.enqueueEnable))}")
            //mc.dataFIFO.foreach { dataFIFO =>
              //emitComment("DataFIFO-enqueueEnable", s"${indexOf(vimap(dataFIFO.enqueueEnable))}")
              //emitComment("DataFIFO-notFull", s"${vomap.get(dataFIFO.notFull).fold("x"){ cos => cos.map(co => indexOf(co)).mkString(",")}}")
            //}
            //emitComment("ofsFIFO-notFull", s"${vomap.get(mc.ofsFIFO.get.notFull).fold("x"){ cos => cos.map(co => indexOf(co)).mkString(",") }}")
            //emitCounterChains(pmc)
            //emitCtrl(pmc)
          //}
        //} else {
          //emitElem("x")
        //}
      //}
    //}
  }

  def emitScalar(pcu:PCU)(implicit ms:CollectionStatus) = {
    val siXbar = ListBuffer[String]() 
    val siComment = ListBuffer[String]() 
    //pcu.etstage.prs.sortWith{ case (ppr1, ppr2) => indexOf(ppr1.reg) < indexOf(ppr2.reg)}.foreach { ppr =>
      //val preg = ppr.reg
      //val psins = ppr.in.fanIns.map(_.src).collect {case psi:PSMem => psi}
      //if (psins.size!=0) { // Is scalarIn Register
        //val mpsins = psins.filter { psin =>
          //simap.pmap.get(psin).fold(false) { sin => 
            //val reg = sin.ctrler.asInstanceOf[CU].scalarInPR(sin)
            //rcmap(reg) == ppr.reg 
          //}
        //}
        //if (mpsins.size==0) siXbar += s""""x""""
        //else if(mpsins.size==1) {
          //val psin = mpsins.head
          //siXbar += s""""${indexOf(psin)}""""
            ////println(s"--$pcu $preg $ppr")
          //siComment += s"ppr:$ppr inBus:${quote(busesOf(psin).head)} sin:${psin} -> $preg[${indexOf(preg)}]" 
        //} else throw PIRException(s"ScalarIn Register $ppr is mapped to two scalarIns $mpsins")
      //}
    //}
    emitComment(s"scalarIn", siComment.mkString(","))
    emitXbar("scalarInXbar", siXbar.toList)
    val cu = clmap.pmap(pcu).asInstanceOf[ComputeUnit]
    cu match {
      case cu:UnitPipeline => emitPair("scalarOutMux", "1")
      case cu => emitPair("scalarOutMux", "0")
    }
    val simux = ListBuffer[String]()
    pcu.regs.foreach { reg => 
      //if (pcu.etstage.get(reg).in.fanIns.exists(_.src.isInstanceOf[PSMem])) {
        //simux += s""""0"""" //TODO scalar retiming mux 
        //simux += s"0" //TODO scalar retiming mux 
      //}
    }
    emitXbar("scalarInMux", simux.toList)
  }

  def emitSwitch[P<:PortType](sbio:GridIO[P, PSB])(implicit ms:CollectionStatus) = {
    val ins = ListBuffer[String]()
    val xbarComment = ListBuffer[String]()
    sbio.outs.foreach { pvout =>
      if (pvout.isConnected) {
        if (fimap.contains(pvout.ic)) {
          val vin = fimap(pvout.ic).src.asInstanceOf[Input[P, PSB]]
          xbarComment += s"${quote(pvout)} -> ${quote(vin)}"
          ins += s""""${sbio.io(vin)}""""
        } else {
          ins += s""""x""""
        }
      }
    }
    //emitXbar(ins.toList)
    emitMap { implicit ms =>
      emitComment(s"sb", s"${quote(sbio.src)} ${xbarComment.mkString(",")}")
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
      case FixSra => s">>" 
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
      case Mux => "mux" 
      case _ => throw TODOException(s"Op ${op} is not supported at the moment")
    }
  }

  def lookUp(ip:IP):String = lookUp(ip.from.src)

  def lookUp(node:Node):String = {
    node match {
      case Const(c:Int) => s"c${c}i"
      case Const(c:Float) => s"c${c}f"
      case Const(c:Boolean) => s"c${c}b"
      case fu:FU => 
        val stage = fu.stage
        val pstage = stmap(stage)
        lookUp(pstage)
      case ctr:Ctr => 
        val pctr = ctmap(ctr)
        lookUp(pctr)
      case sm:OCM =>
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
      case c:PConst => lookUp(opmap.pmap(c.out).src)
      case pst:PST => s"s${indexOf(pst)}"
      case pfu:PFU => lookUp(pfu.stage) 
      case pctr:PCtr => s"i${indexOf(pctr)}"
      case pib:PI[_] if pib.isBus => s"bus${indexOf(pib)}"
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
  def startDelay(pcu:PCU, ctr:Ctr)(implicit ms:CollectionStatus):String = {
    if (!ctr.isInner) { "0" }
    else if (!ctr.isInstanceOf[DummyCounter] && ctr.cchain.isLocal) {
      assert(ctr.ctrler.isInstanceOf[ICL])
      val icl = ctr.ctrler.asInstanceOf[ICL]
      val delays = ctr.ctrler.vins.map { vin =>
        //TODO is this needed?
        vin.tokenIn.fold(0) { cin =>
          val dataInterConnectDelay = rtmap(vin) + 1
          val ctrlInterConnectDelay = rtmap(cin)
          assert(dataInterConnectDelay==ctrlInterConnectDelay)
          0 //TODO: assume data delay matches control delay for all inputs for now
        }
      } 
      if (delays.size==0) "0"
      else s"${delays.max}"
    } else if (!ctr.isInstanceOf[DummyCounter] && !ctr.cchain.isCopy) { "0" }
    else { // Write Address Start Delay
      val icl = ctr.ctrler.asInstanceOf[InnerController]
      val srams = icl.srams.filter{_.writeCtr == ctr}
      //val fows = icl.fows.filter(_.dummyCtr==ctr)
      val fows = Nil
      val mems = srams ++ fows
      if (mems.size==0) "0" 
      else {
        assert(mems.size==1)
        val sram = mems.head 
        assert(sram.isRemoteWrite, s"${sram}")
        val vin = sram.writePort.from.src match {
          case vi:VI => vi
          case si:SI => vecOf(si).asInstanceOf[VI]
        }
        val fromCU = vin.writer.ctrler
        val pFromCU = clmap(fromCU).asInstanceOf[PCU]
        val dataInterConnectDelay = rtmap(vin) + 1 // Implicit data delay in hardware
        val ctrlInterConnectDelay = rtmap(ctr.en)
        emitComment(s"numLocalStages", numLocalStages(pFromCU))
        emitComment(s"numDataHop", dataInterConnectDelay)
        emitComment(s"numCtrlHop", ctrlInterConnectDelay)
        val delay = numLocalStages(pFromCU) + dataInterConnectDelay - ctrlInterConnectDelay 
        s"${delay}"
      }
    }
  }

  /* amount of delay to decalre done of the counter */
  def doneDelay(pcu:PCU, ctr:Ctr):String = {
    val cchain = ctr.cchain
    val cu = clmap.pmap(pcu).asInstanceOf[ComputeUnit]
    cu.parent match {
      case sc:SC =>
        if (!cu.isHead) { "0" }
        else if (ctr.isOuter) { s"${numLocalStages(pcu)}" } // Outer most including parent's copy
        else { "0" }
      case _ => 
        if (!cchain.isLocal) { "0" }
        else if (ctr==cu.localCChain.outer) { s"${numLocalStages(pcu)}" } // Local outer most
        else { "0" }
    }
  }

  def emitInterconnect(pcu:PCU)(implicit ms:CollectionStatus) = {
    val inputs = pcu.vins.map { pvin =>
      fimap.get(pvin).fold(s""""x"""") { pob => s""""${indexOf(pob.src)}""""}
    }
    emitList("inputs", inputs)
  }

  def emitOnChipMems(pcu:PCU)(implicit ms:CollectionStatus) = {
    emitList(s"scratchpads") { implicit ms =>
      pcu.srams.foreach{ psram => 
        if (smmap.pmap.contains(psram)) {
          emitMap { implicit ms =>
            val mem = smmap.pmap(psram)
            emitComment(s"$mem", s"$psram")
            mem match {
              case mem:SOR =>
                emitPair("ra", lookUp(mem.readAddr))
                emitPair("deqEn", "x")
                emitPair("isReadFifo", "0")
              case mem:FOR =>
                emitPair("ra", "x")
                val enlut = mem.dequeueEnable.from.src.asInstanceOf[EnLUT]
                emitPair("deqEn", s"${indexOf(pmmap(enlut))}")
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
                if (mem.wtStart.isDefined)
                  emitPair("start", s"${lookUp(mem.wtStart.get.src)}")
                else
                  emitPair("start", s"x")
                if (mem.wtEnd.isDefined)
                  emitPair("end", s"${lookUp(mem.wtEnd.get.src)}")
                else
                  emitPair("end", s"x")
                //emitPair(s"enqEn", s"${pcu.ctrlBox.io(vimap(mem.enqueueEnable.from))}")
                //val pdmCtr = ctmap(mem.dummyCtr)
                //emitPair(s"enqEn", s"${indexOf(pdmCtr)}")
                //emitPair("isWriteFifo", "1")
            }
            val wd = mem.writePort.from.src match {
              case v:VI => "remote" //lookUp(vimap(v))
              case s:PR => "local"
            }
            emitPair("wd", wd)
            emitPair("banking", lookUp(mem.banking))
            //mem.buffering match {
              //case MultiBuffer(n) =>
                //emitPair("numBufs", n)
                ////TODO
                ////emitPair("rswap", lookUp(swapRead))
                ////emitPair("wswap", lookUp(swapWrite))
              //case SingleBuffer() =>
                //emitPair("numBufs", 1)
                //emitPair("rswap", "x")
                //emitPair("wswap", "x")
            //}
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
        if (ctmap.pmap.contains(pctrs(i)) && ctmap.pmap.contains(pctrs(i+1))) {
          val ctr = ctmap.pmap(pctrs(i))
          val ctrp1 = ctmap.pmap(pctrs(i+1)) 
          if (ctrp1.en.from == ctr.done) s""""1""""
          else s""""0""""
        } else s""""0""""
      }
      val cu = clmap.pmap(pcu).asInstanceOf[CU]
      emitList("chain", chain)
      emitList("counters") { implicit ms =>
        pcu.ctrs.foreach { pctr =>
          if (ctmap.pmap.contains(pctr)) {
            val ctr = ctmap.pmap(pctr)
            ctr match {
              case ctr:DummyCounter =>
                emitMap { implicit ms =>
                  emitPair("max", "x")
                  emitPair("min", "x")
                  emitPair("stride", "x")
                  emitPair("startDelay", startDelay(pcu, ctr))
                  emitPair("endDelay",  "x")
                }
              case ctr =>
                emitMap { implicit ms =>
                  cu.parent match {
                    case sc:SC if (cu.isLast && ctr.isOuter) =>
                      //TODO HACK: change last stage of stream controller's counter max to be max-1
                      ctr.max.from.src match {
                        case Const(value:Int) =>
                          val const = Const(value-1)
                          emitComment("ctrMaxHACK", s"original=$value")
                          emitPair("max", lookUp(const))
                        case c@Const(value) => throw PIRException(s"Do not support max of non Int type $c")
                        case _ => throw PIRException(s"HACK: the last stage of the StreamController's counter maximum has to be a constant")
                      }
                      // ---
                    case _ => emitPair("max", lookUp(ctr.max))
                  }
                  emitPair("min", lookUp(ctr.min))
                  emitPair("stride", lookUp(ctr.step))
                  emitPair("startDelay", startDelay(pcu, ctr))
                  emitPair("endDelay",  doneDelay(pcu, ctr))
                }
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
                if (fu.operands.size>3)
                  throw PIRException(s"Don't support any operation with more than 3 operands at the moment ${fu.operands}")
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
                val popA = fimap(pfu.operands.head)
                emitPair("opA", lookUp(pstage, popA.src))
                if (fu.operands.size < 2)
                  emitPair("opB", "x")
                else {
                  val popB = fimap(pfu.operands(1))
                  emitPair("opB", lookUp(pstage, popB.src))
                }
                if (fu.operands.size < 3)
                  emitPair("opC", "x")
                else {
                  val popC = fimap(pfu.operands(2))
                  emitPair("opC", lookUp(pstage, popC.src))
                }
                emitPair("opcode", s"${lookUp(fu.op)}")
                val results = fu.out.to
                val pips = results.map(result => ipmap(result))
                val reses = pips.map(pip => lookUp(pstage, pip.src)) 
                emitList("result", reses.map(r => s""""$r"""").toList)
                val inits = results.map(_.src).collect { 
                  case PipeReg(s,r) => r }.collect {
                    case AccumPR(Const(c)) => c 
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
          val rstrs = pstage.prs.flatMap { ppr =>
            assert(pstage==ppr.stage)
            if (fimap.contains(ppr.in)) {
              fimap(ppr.in).src match {
                case p:PFU => Some((s"r${indexOf(ppr.reg)}", "alu"))
                case p:PSMem => None
                case p => Some((s"r${indexOf(ppr.reg)}", lookUp(pstage, p)))
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
    val cu = clmap.pmap(pcu)
    emitMap(s"control") { implicit ms =>
      emitList("tokenDownLUT") { implicit ms =>
        //pcb.tokenDownLUTs.foreach { ptdlut =>
          //emitMap { implicit ms =>
            //if (!lumap.pmap.contains(ptdlut)) {
              //CtrlCodegen.lookUpX(ptdlut.numIns)
              //emitPair("table", CtrlCodegen.lookUpX(ptdlut.numIns))
              //emitPair("syncTokenMux", "x")
            //} else {
              //val tdlut = lumap.pmap(ptdlut)
              //val inits = ListBuffer[CIP]()
              //val tos = ListBuffer[COP]()
              //val map:Map[COP, Int] = Map.empty
              //tdlut.ins.foreach { in =>
                //in.from.src match {
                  //case t:Top => inits += in
                  //case p:PRIM => 
                    //if (p.ctrler==tdlut.ctrler.asInstanceOf[ComputeUnit].parent) inits += in
                    //else tos += in.from.asInstanceOf[COP]
                  //case c => emitln(s"${c}") //TODO?
                //}
              //}
              //assert(inits.size <= 1, s"inits:${inits}")
              //emitComment("IO", s"tdlut.ins:${tdlut.ins.map(_.from)} init:${inits.head} tos:${tos}")
              //if (inits.size==0) {
                //emitPair("syncTokenMux", "x")
              //} else {
                //val init = inits.head
                //val pcin = vimap(init)
                //emitPair("syncTokenMux", s"${indexOf(pcin)}")
                //map += (init.from.asInstanceOf[COP] -> 0) // Assume init is the first input
              //}
              //tos.foreach { to =>
                //map += (to -> (indexOf(ucmap(to.src.asInstanceOf[UC]))+1) ) // Assume init is the first input
              //}
              //val tf:List[Boolean] => Boolean = tdlut.transFunc.tf(map, _)
              //emitComment(s"${tdlut}", s"TransferFunction: ${tdlut.transFunc.info}, ${map}")
              //val table = CtrlCodegen.lookUp(ptdlut.numIns, tf)
              ////CtrlCodegen.printTable(ptdlut.numIns, tdlut.transFunc, map)
              //emitList("table", table)
            //}
          //}
        //}
      }
      val doneXbar = ListBuffer[String]()
      emitList("tokenOutLUT") { implicit ms =>
        //pcb.tokenOutLUTs.foreach { ptolut =>
          //emitMap { implicit ms =>
            //if (!lumap.pmap.contains(ptolut)) {
              //CtrlCodegen.lookUpX(ptolut.numIns)
              //emitPair("table", CtrlCodegen.lookUpX(ptolut.numIns))
              //doneXbar ++= List.tabulate(2) { i => s""""x"""" }
            //} else {
              //val tolut = lumap.pmap(ptolut)
              //val ctrs = tolut.ins.map(_.from.src.asInstanceOf[Ctr])
              //assert(ctrs.size<=2)
              //val map:Map[COP, Int] = Map.empty
              //doneXbar ++= List.tabulate(2) { i => // sel for Xbar
                //if (i<ctrs.size) {
                //val pct = ctmap(ctrs(i))
                  //s""""${indexOf(pct)}"""" 
                //}
                //else s""""x""""
              //}
              //ctrs.zipWithIndex.foreach { case (ctr,i) =>
                //map += (ctr.done -> i)
              //}
              //val tf:List[Boolean] => Boolean = tolut.transFunc.tf(map, _)
              //emitComment(s"${tolut}", s"TransferFunction: ${tolut.transFunc.info}, ${map}")
              //val table = CtrlCodegen.lookUp(ptolut.numIns, tf)
              //emitList("table", table)
            //}
          //}
        //}
      }
      val tom = pcu.ctrlIO.outs.map { pto =>
        vomap.pmap.get(pto).fold (s""""x"""") { t =>
          val to = t.asInstanceOf[Port]
          val idx = to.src match {
            case l:FOW => 
              cu match {
                case mc:MC =>
                  l match {
                    //case l:CommandFIFO => spade.memCtrlCommandFIFONotFullBusIdx
                    //case l if (mc.dataFIFO==Some(l)) => spade.memCtrlDataFIFONotFullBusIdx
                    case _ => indexOf(smmap(l))
                  }
                case _ => indexOf(smmap(l))
              }
            //case l:TokenDownLUT => pcu.srams.size + indexOf(lumap(l)) 
            //case l:TokenOutLUT => pcu.srams.size + pcb.tokenDownLUTs.size + indexOf(lumap(l)) 
            //case l:EnLUT => pcu.srams.size + pcb.tokenDownLUTs.size + pcb.tokenOutLUTs.size + indexOf(lumap(l))
            //case l if (l.isInstanceOf[MC] && l.asInstanceOf[MC].dataValid==to) => spade.memCtrlDataValidBusIdx
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
      val initOnStart = ListBuffer[String]()
      pcb.udcs.map { pudc =>
        if (pmmap.pmap.contains(pudc)) {
          // inc
          val udc = pmmap.pmap(pudc)
          val inc = if (udc.inc.isConnected) {
            val pcin = vimap(udc.inc)
            s""""${indexOf(pcin)}""""
          } else { s""""x"""" }
          incs += inc
          val init = if (udc.init.isConnected) {
            val pcin = vimap(udc.init)
            s""""${indexOf(pcin)}""""
          } else { s""""x"""" }
          inits += init
          // dec
          val ctr = udc.dec.from.src.asInstanceOf[Ctr]
          val pctr = ctmap(ctr)
          val dec = indexOf(pctr)
          decs += s""""${dec}""""
          initVals += s""""${udc.initVal}""""
          udcComment += s"${udc} -> ${pudc}"
          udcComment += s"${udc}.inc -> ${inc.replace(s""""""","")}"
          udcComment += s"${udc}.dec -> ${dec}"
          udcComment += s"${udc}.init -> ${vimap.get(udc.init)}"
          if (udc.initOnStart) initOnStart += s""""1"""" 
          else initOnStart += s""""0""""
        } else {
          incs += s""""x""""
          decs += s""""x""""
          inits += s""""x""""
          initVals += s""""x""""
          initOnStart += s""""x""""
        }
      }
      emitXbar("incXbar", (incs ++ inits).toList)
      emitXbar("decXbar", decs.toList)
      emitComment("udc", udcComment.mkString(","))
      emitList("udcInit", initVals.toList)
      emitList("udcSet", initOnStart.toList)
      val fifoMux = ListBuffer[String]()
      emitList("enableLUT") { implicit ms =>
        //pcb.enLUTs.foreach { penlut => 
          //emitMap { implicit ms =>
            //if (!lumap.pmap.contains(penlut)) {
              //emitPair("table", CtrlCodegen.lookUpX(penlut.numIns))
              //fifoMux += s""""x""""
            //} else {
              //val enlut = lumap.pmap(penlut)
              //val map:Map[COP, Int] = Map.empty
              //var toAndTree = false
              //enlut.ins.map(_.from.src).foreach { 
                //case udc:UC => 
                  //map += (udc.out -> indexOf(ucmap(udc)))
                //case at:AT => map += (at.out -> 0) 
                //toAndTree = true
              //}
              //if (toAndTree)
                //fifoMux += s""""1""""
              //else
                //fifoMux += s""""0""""
              //assert(map.map { case (op, i) => i}.toSet.size== map.size) // No duplicate in index
              //val tf:List[Boolean] => Boolean = enlut.transFunc.tf(map, _)
              //emitComment(s"${enlut}", s"TransferFunction: ${enlut.transFunc.info}, ${map} idx:${indexOf(penlut)}")
              //val table = CtrlCodegen.lookUp(penlut.numIns, tf)
              //emitList("table", table)
            //}
          //}
        //}
      }
      val tokIns = Array.fill(pcu.ctrs.size)(s""""x"""")
      val emuxs = pcu.ctrs.zipWithIndex.map { case (pctr, i) => 
        if (ctmap.pmap.contains(pctr)) {
          val ctr = ctmap.pmap(pctr)
          if (!ctr.en.isCtrlIn) {
            s""""0""""
          } else {
            tokIns(i) = s""""${indexOf(vimap(ctr.en))}""""
            s""""1""""
          }
          //ctr.en.from.src match {
            //case e:EnLUT =>
              //val penlut = lumap(e).asInstanceOf[PEnLUT]
              //if (e.ctrler==cu) {
                //assert(indexOf(penlut) == i)
                //s""""0""""
              //} else { // from token in
                //tokIns(i) = s""""${indexOf(vimap(ctr.en))}""""
                //s""""1""""
              //}
            //case c:Ctr => //Chained
              //s""""x""""
          //}
        } else {
          s""""x""""
        }
      }
      val fifoAndTree = ListBuffer[String]()
      pcu.srams.foreach { psram =>
        smmap.pmap.get(psram).fold(fifoAndTree += "0"){ sram =>
          val used = sram match {
            case f:FOR => "1"
            case _ => "0"
          }
          fifoAndTree += used 
        }
      }
      val tokInAndTree = Array.fill(pcu.ctrlIO.ins.size)(s""""x"""") 
      pcu.ctrlIO.ins.zipWithIndex.foreach { case (cin, i) =>
        vimap.pmap.get(cin).foreach { cis =>
          // cis should have the same source
          assert(cis.map(_.asInstanceOf[CIP].from).toSet.size==1)
          cis.head.asInstanceOf[CIP].from.src match {
            case f:FOW => tokInAndTree(i) = s""""1""""
            case _ =>
          }
        }
      }
      emitList(s"fifoAndTree", fifoAndTree.toList)
      emitList(s"fifoMux", fifoMux.toList)
      emitList(s"tokInAndTree", tokInAndTree.toList)
      emitList(s"enableMux", emuxs)
      emitXbar(s"tokenInXbar", tokIns.toList)
      val tokInComment = ListBuffer[String]()
      emitMap("comment-tokenIn") { implicit ms:CollectionStatus =>
        cu.cins.foreach { ci =>
          emitPair(s"$ci(${vimap(ci)})", s"from ${ci.from}(${vomap(ci.from)})")
        }
      }
    }
  }

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
