package pir.codegen

import pir.Design
import pir.graph.{AccumPR, Const}
import pir.spade.main._
import pir.util.typealias._
import pir.util.enums._
import pir.Config

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class ConfigCodegen(implicit design: Design) extends Codegen with ScalaCodegen with MultiFileCodegen {
  def shouldRun = design.pirMapping.succeeded && Config.codegen
  import spademeta._
  import pirmeta.{indexOf => _, _}
  import spade.param._

  val appName = s"$design".replace(s"$$", "")
  val traitName = appName + "Trait"
  lazy val dir = sys.env("PLASTICINE_HOME") + s"/src/main/scala/apps/$design"
  override lazy val stream:OutputStream = newStream(dir, s"$traitName.scala")

  def mapping = design.mapping.get
  def fimap = mapping.fimap
  def clmap = mapping.clmap
  def ctmap = mapping.ctmap
  def smmap = mapping.smmap
  def stmap = mapping.stmap
  def pmmap = mapping.pmmap
  def ipmap = mapping.ipmap
  def vimap = mapping.vimap
  def vomap = mapping.vomap

  def top = spade.top
  def sbs = spade.sbArray
  def cus = spade.cuArray
  def ocus = spade.ocuArray
  def scus = spade.scuArray
  def mcs = spade.mcArray

  override implicit lazy val spade = design.arch.asSwitchNetwork

  //val SVT = "SrcValueTuple"
  val SVT = "SVT"

  def emitHeader = {
    emitln(s"package plasticine.apps")
    emitln(s"import plasticine.arch._")
    emitln(s"import chisel3._")
    emitln(s"import plasticine.spade._")
    emitln(s"import plasticine.pisa.PISADesign")
    emitln(s"import plasticine.pisa.ir.{SrcValueTuple => $SVT, _}")
    emitln(s"import chisel3.util._")
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(s"import GeneratedTopParams.plasticineParams._")
    emitln(s"import GeneratedTopParams._")
    emitln(s"import plasticine.templates._")
    emitln(s"import plasticine.pisa.enums._")
    emitln(1)
  }

  def emitAppObject {
    emitln(s"object $appName extends PISADesign with $traitName")
  }

  override def splitPreHeader:Unit = {
    emitHeader
  }

  override def splitPostHeader:Unit = {
    emitln(s"self:$traitName =>")
    emitBSln(s"def config${fileNumber}:Unit =")
  }

  override def splitPreFooter:Unit = {
    emitBEln
  }

  addPass(canRun=true, runCount=1) {
    emitHeader
    emitAppObject
    emitSplit {
      emitCrossbarBits
      emitCUBits
    }
    emitMixed {
      emitPlasticineBits
      emitBlock(s"def config:Unit =") {
        (0 until fileNumber).foreach { i =>
          emitln(s"config${i+1}")
        }
      }
      emitln(s"config")
    }
  }

  def muxIdx(in:PI[PModule]) = {
    fimap.get(in).fold(-1) { out => in.indexOf(out) }
  }
  def muxIdx(out:PGO[PModule]) = {
    fimap.get(out.ic).fold(-1) { _.src.index }
  }

  def emitXbar(name:String, ins:List[PI[PModule]]) = {
    ins.zipWithIndex.foreach { case (in, idx) =>
      val id = muxIdx(in)
      indexOf.get(in).foreach { i => assert(i == idx) }
      if (id != -1) {
        emitln(s"${name}.outSelect(${idx}) = $id")
      }
    }
  }

  def emitCrossbarBits = {
    sbs.foreach {
      _.foreach { sb =>
        emitXbar(s"${qv(sb)}", sb.vouts.map(_.ic))
        emitXbar(s"${qs(sb)}", sb.souts.map(_.ic))
        emitXbar(s"${qc(sb)}", sb.couts.map(_.ic))
      }
    }
  }

  def lookUp(n:IP):String = { lookUp(ipmap(n)) }

  def lookUp(n:PI[PModule]):String = fimap.get(n) match {
    case None => s"$SVT()"
    case Some(o) =>
      val (src, value) = o.propogate.src match {
        case s:PCtr => ("CounterSrc", s.index)
        case s:PSMem => ("ScalarFIFOSrc", s.index)
        case s:PVMem => ("VectorFIFOSrc", s.index)
        case s:PConst =>
          val const = pmmap.pmap(s)
          const match {
            case s:Const[_] if s.isBool => ("ConstSrc", s.value)
            case s:Const[_] if s.isInt => ("ConstSrc", s.value)
            case s:Const[_] if s.isFloat => ("ConstSrc", s.value)
          }
        case s:PPR =>
          n.src match {
            case ts:PPR if ts.stage.isNext(s.stage) => ("PrevStageSrc", s.reg.index)
            case ts:PPR if ts.stage == s.stage => ("CurrStageSrc", s.reg.index)
            case fu:PFU if stmap.pmap(fu.stage).isReduce => ("ReduceTreeSrc", s.reg.index)
            case fu:PFU if fu.stage.isNext(s.stage) => ("PrevStageSrc", s.reg.index)
            case fu:PFU if fu.stage == s.stage => ("CurrStageSrc", s.reg.index)
            case ts:PPR => throw new Exception(s"toStage=${quote(ts.stage)} prev=${ts.stage.prev.map(quote).getOrElse("None")} currStage=${quote(s.stage)}")
          }
        case s:PFU => ("ALUSrc", s.stage.index)
      }
      s"$SVT($src, $value)"
  }

  def lookUp(n:PO[PModule]):List[String] = {
    fimap.pmap(n).toList.map { i =>
      val (src,value) =  i.propogate.src match {
        case s:PPR =>
          n.src match {
            case ts:PFU => ("CurrStageDst", s"${s.reg.index}")
          }
        case s:PGO[_] if networkOf(s).isVectorNetwork => ("VectorOutDst", s.index)
        case s:PGO[_] if networkOf(s).isScalarNetwork => ("ScalarOutDst", s.index)
        case s:PSRAM if i == s.readAddr || s.readAddr.slices.map(_.in).contains(i) =>
          ("ReadAddrDst", -1)
        case s:PSRAM if i == s.writeAddr || s.writeAddr.slices.map(_.in).contains(i) =>
          ("WriteAddrDst", -1)
      }
      s"$SVT($src, $value)"
    }
  }

  def lookUp(n:PAT):List[Int] = {
    //n.ins.map { in => if (ipmap.pmap.contains(in)) 1 else 0 }
    n.ins.map { in => muxIdx(in) }
  }

  def emitCtrBits(pcu:PCU) = {
    val cu = clmap.pmap(pcu)
    pcu.ctrs.foreach { pctr =>
      ctmap.pmap.get(pctr).foreach { ctr =>
        val ctrBit = s"CounterRCBits(max=${lookUp(ctr.max)}, stride=${lookUp(ctr.step)}, min=${lookUp(ctr.min)}, par=${ctr.par})"
        emitln(s"${quote(pctr)} = $ctrBit")
      }
    }
  }

  def emitCChainBis(pcu:PCU) = {
    val cu = clmap.pmap(pcu)
    val pctrs = pcu.ctrs
    val chain = List.tabulate(pctrs.size-1) { i =>
      if (ctmap.pmap.contains(pctrs(i)) && ctmap.pmap.contains(pctrs(i+1))) {
        val ctr = ctmap.pmap(pctrs(i))
        val ctrp1 = ctmap.pmap(pctrs(i+1))
        if (ctrp1.en.from == ctr.done) 1
        else 0
      } else 0
    }
    //emitln(s"val ${q(pcu, "ctrs")} = Array.tabulate(${pcu.ctrs.size}) { i => CounterRCBits.zeroes(${spade.wordWidth})}")
    //emitln(s"val ${q(pcu, "cc")} = CounterChainBits(${quote(chain)}, ${q(pcu, "ctrs")})")
    //emitln(s"${quote(pcu)}.counterChain = CounterChainBits(${quote(chain)}, ${q(pcu, "ctrs")})")
    emitln(s"${quote(pcu)}.counterChain.chain = ${quote(chain)}")
  }

  def emitFwdRegs(pst:PST) = {
    pst.prs.foreach { pr =>
      if (fimap.contains(pr.in)) {
        emitln(s"${quote(pr)} = ${lookUp(pr.in)}")
      }
    }
  }

  def emitAccum(pcu:PCU, fu:FU) = {
    fu.out.to.foreach { _.src match {
        case pr:PR =>
          pr.reg match {
            case AccumPR(Const(init)) => emitln(s"${quote(pcu)}.accumInit = $init")
            case _ =>
          }
        case _ =>
      }
    }
  }

  def emitStageBits(pcu:PCU) = {
    val cu = clmap.pmap(pcu)
    pcu.fustages.foreach { pst =>
      stmap.pmap.get(pst).fold {
        cu match {
          case cu:MP =>
            //emitln(s"${quote(pst)}.enableSelect = XSrc")
          case cu:ICL =>  // by default turns on
        }
      } { st =>
        val pfu = pst.funcUnit.get
        val fu = st.fu.get
        emitln(s"${quote(pst)}.opA = ${lookUp(pfu.operands(0))}")
        emitln(s"${quote(pst)}.opB = ${lookUp(pfu.operands(1))}")
        emitln(s"${quote(pst)}.opC = ${lookUp(pfu.operands(2))}")
        emitln(s"${quote(pst)}.opcode = ${quote(fu.op)}")
        assert(fimap.pmap(pfu.out).size==1)
        if (fimap.pmap(pfu.out).head.isSrcSlice) {
          emitln(s"${quote(pst)}.res = ${quote(lookUp(pfu.out.sliceHead.out))}")
        } else {
          emitln(s"${quote(pst)}.res = ${quote(lookUp(pfu.out))}")
        }
        emitAccum(pcu, fu)
        cu match {
          case cu:MP if forWrite(st) =>
            emitln(s"${quote(pst)}.enableSelect.src = WriteEnSrc")
          case cu:MP if forRead(st) =>
            emitln(s"${quote(pst)}.enableSelect.src = ReadEnSrc")
          case _ =>
        }
      }
      emitFwdRegs(pst)
    }
  }

  def cuTp(pcu:PCU) = pcu match {
    case cu:PMCU => "PMU"
    case cu:PCU => "PCU"
  }

  def emitStreamingMuxSelect(pcu:PCU) = {
    val pcb = pcu.ctrlBox
    pcb match {
      case pcb:PICB =>
        val cu = clmap.pmap(pcu)
        emitComment(s"$cu isPipelining=${isPipelining(cu)} isStreaming=${isStreaming(cu)}")
        emitln(s"${quote(pcb)}.streamingMuxSelect = ${muxIdx(pcb.en.in)}")
      case pcb =>
    }
  }

  def commentUDCs(pcu:PCU) = {
    clmap.pmap.get(pcu).foreach { cu =>
      emitComment(s"$cu.udcounters=[${cu.ctrlBox.udcounters.mkString(",")}]")
    }
    val udcs = pcu.ctrlBox.udcs.map { pudc =>
      pmmap.pmap.get(pudc).map { udc =>
        s"${udc}"
      }
    }
    emitComment(s"${quote(pcu)}.udcs=[${udcs.mkString(",")}]")
  }

  def commentSBufs(pcl:PCL) = {
    pcl.sbufs.foreach { sbuf =>
      smmap.pmap.get(sbuf).foreach {
        case smem:SBuf =>
          val sw = if (smem.enqueueEnable.isConnected) {
            s"enqueueEnable=${smem.enqueueEnable.from}"
          } else {
            s"enqueueEnable=NotConnected"
          }
          emitComment(s"${quote(sbuf)} -> $smem $sw")
        case smem:SFIFO =>
          val eq = if (smem.enqueueEnable.isConnected) {
            s"enqueueEnable=${smem.enqueueEnable.from}"
          } else {
            s"enqueueEnable=${smem.enqueueEnable.from}"
          }
          emitComment(s"${quote(sbuf)} -> $smem ")
      }
    }
  }

  def emitPulserSM(pcu:PCU) = {
    val cu = clmap.pmap(pcu)
    cu match {
      case cu:Seq =>
        emitln(s"${quote(pcu.ctrlBox)}.pulserMax=1")
      case cu:MetaPipe =>
        emitln(s"${quote(pcu.ctrlBox)}.pulserMax=${lengthOf(cu)}")
      case _ =>
    }
  }

  def from(io:Node) = {
    io match {
      case io:I => io.writer
      case io:IP => io.from
    }
  }

  def to(io:Node) = {
    io match {
      case io:O => io.readers
      case io:OP => io.to
    }
  }

  def ctrler(io:Node) = {
    io match {
      case io:IO => io.ctrler
      case io:PT =>
        io.src match {
          case prim:PRIM => prim.ctrler
          case cl:CL => cl
        }
    }
  }

  def commentIO(pios:List[PGIO[PModule]]) = {
    pios.foreach { 
      case pin:PGI[PModule] =>
        vimap.pmap.get(pin).foreach { ins =>
          emitComment(s"${quote(pin)} -> ${ins.map(in => s"${in}(from:${from(in)} at ${ctrler(from(in))})").mkString(",")}")
        }
      case pout:PGO[PModule] =>
        vomap.pmap.get(pout).foreach { out =>
          emitComment(s"${quote(pout)} -> ${out}(to:${to(out).map{ in => s"$in at ${ctrler(in)}"}.mkString(",")})")
        }
    }
  }

  def emitUDCInits(pcu:PCL) = {
    val inits = pcu.ctrlBox.udcs.map { _.initVal(mapping) }
    if (inits.nonEmpty && inits.exists{_.nonEmpty})
    emitln(s"${quote(pcu.ctrlBox)}.udcInit=${quote(inits.map(_.getOrElse(-1)))}")
  }

  def emitXbars(pcl:PCL) = {
    pcl.ctrlBox match {
      case pcb:PICB =>
        emitXbar(s"${quote(pcb)}.incrementXbar", pcb.udcs.map(_.inc))
        emitXbar(s"${quote(pcb)}.swapWriteXbar", pcl.sbufs.map(_.enqueueEnable))
        emitXbar(s"${quote(pcb)}.tokenOutXbar", pcl.couts.map(_.ic))
        emitXbar(s"${quote(pcb)}.doneXbar", List(pcb.done.in))
      case pcb:POCB =>
        emitXbar(s"${quote(pcb)}.incrementXbar", pcb.udcs.map(_.inc))
        emitln(s"${quote(pcb)}.udcDecSelect=${quote(pcb.udcs.map(udc => muxIdx(udc.dec)))}")
        emitXbar(s"${quote(pcb)}.swapWriteXbar", pcl.sbufs.map(_.enqueueEnable))
        emitXbar(s"${quote(pcb)}.tokenOutXbar", pcl.couts.map(_.ic))
        emitXbar(s"${quote(pcb)}.doneXbar", List(pcb.done.in))
      case pcb:PMCB =>
        emitXbar(s"${quote(pcb)}.swapWriteXbar", pcl.sbufs.map(_.enqueueEnable))
        emitXbar(s"${quote(pcb)}.readDoneXbar", List(pcb.readDone.in))
        emitXbar(s"${quote(pcb)}.writeDoneXbar", List(pcb.writeDone.in))
        emitXbar(s"${quote(pcb)}.tokenOutXbar", pcl.couts.map(_.ic))
      case pcb:PMCCB =>
        emitXbar(s"${quote(pcb)}.tokenInXbar", pcb.prt.sbufs.map(_.enqueueEnable))
        emitXbar(s"${quote(pcb)}.tokenOutXbar", pcl.couts.map(_.ic))
    }
  }

  def emitAndTree(pcb:PCB, at:PAT) = {
    val config = lookUp(at)
    if (config.nonEmpty) emitln(s"${quote(pcb)}.${at.name.get} = ${config}")
  }

  def emitAndTrees(pcu:PCU) = {
    val pcb = pcu.ctrlBox
    pcb match {
      case pcb:PICB =>
        emitAndTree(pcb, pcb.tokenInAndTree)
        emitAndTree(pcb, pcb.fifoAndTree)
        emitAndTree(pcb, pcb.siblingAndTree)
      case pcb:POCB =>
        emitAndTree(pcb, pcb.childrenAndTree)
        emitAndTree(pcb, pcb.siblingAndTree)
      case pcb:PMCB =>
        emitAndTree(pcb, pcb.writeFifoAndTree)
        emitAndTree(pcb, pcb.readFifoAndTree)
      case pcb:PTCB =>
      case pcb:PCB =>
    }
  }

  def emitSwapReadSelect(pcu:PCU) = {
    val pcb = pcu.ctrlBox
    pcu match {
      case pcu:PMCU =>
        val idxes = pcu.sbufs.map(sbuf => muxIdx(sbuf.dequeueEnable))
        emitln(s"${quote(pcb)}.scalarSwapReadSelect = ${quote(idxes)}")
      case pcu =>
    }
  }

  def emitControlBits(pcu:PCU) = {
    val pcb = pcu.ctrlBox
    commentIO(pcu.cins)
    commentIO(pcu.couts)
    commentUDCs(pcu)
    emitUDCInits(pcu)
    emitAndTrees(pcu)
    emitStreamingMuxSelect(pcu)
    commentSBufs(pcu)
    emitXbars(pcu)
    emitPulserSM(pcu)
    emitSwapReadSelect(pcu)
  }

  def emitControlBits(pmc:PMC) = {
    val pcb = pmc.ctrlBox
    commentSBufs(pmc)
    emitXbars(pmc)
  }

  def emitScalarInXbar(pcl:PCL) = {
    val sins = pcl.sbufs.map { sbuf => fimap.get(sbuf.writePort).map { po => po.src } }
    emitComment(s"${quote(pcl)}.scalarInXbar=[${sins.mkString(",")}]")
    emitXbar(s"${quote(pcl)}.scalarInXbar", pcl.sbufs.map(_.writePort))
  }

  def emitScalarOutXbar(pcu:PCU) = {
    val souts = pcu.souts.map { sout => fimap.get(sout.ic).map { po => po.propogate.src } }
    emitComment(s"${quote(pcu)}.scalarOutXbar=[${souts.mkString(",")}]")
    val soRegs = pcu.regs.filter{ _.is(ScalarOutReg) }
    val soIdxes = souts.map(_.map(ppr => soRegs.indexOf(ppr.asInstanceOf[PPR].reg) ).getOrElse(-1))
    emitXbar(s"${quote(pcu)}.scalarOutXbar", pcu.souts.map(_.ic))
  }

  def emitSRAM(psram:PSRAM) = {
    val pcu = psram.prt
    smmap.pmap.get(psram).foreach { case sram:SRAM =>
      emitComment(s"$psram -> $sram")
      val stride = sram.banking match {
        case Strided(stride, banks) => stride
        case _ => -1 //TODO
      }
      emitln(s"${quote(psram)}.stride = $stride")
      emitln(s"${quote(psram)}.numBufs = ${sram.buffering}")
      val fifo = sram.writePort.from.src.asInstanceOf[FIFO]
      val pin = vimap(fifo.writePort.from.src)
      emitln(s"${quote(psram.prt)}.wdataSelect = ${pin.index}") //TODO:add mux for selecting scalar/vector input 
      emitln(s"${quote(psram.prt)}.waddrSelect = ${lookUp(psram.writeAddr)}")
      emitln(s"${quote(psram.prt)}.raddrSelect = ${lookUp(psram.readAddr)}")
    }
  }

  def emitScratchpadBits(pcu:PCU) = {
    pcu match {
      case pcu:PMCU => emitSRAM(pcu.sram)
      case _ =>
    }
  }

  def emitScalarNBuffer(pcu:PCU) = {
    val nbufs = pcu.sbufs.map { psbuf =>
      smmap.pmap.get(psbuf).fold(-1) {
        case smem:SBuf => smem.buffering
        case smem:SFIFO => 0
      }
    }
    emitln(s"${quote(pcu)}.fifoNbufConfig=${quote(nbufs)}")
  }

  def emitCUBit(pcu:PCL) = {
    clmap.pmap.get(pcu).foreach { cu =>
      emitComment(s"Configuring ${quote(pcu)} <- $cu")
      pcu match {
        case pcu:POCU =>
          emitCChainBis(pcu)
          emitControlBits(pcu)
          emitCtrBits(pcu)
        case pcu:PCU =>
          emitControlBits(pcu)
          emitScalarNBuffer(pcu)
          emitScalarInXbar(pcu)
          emitScalarOutXbar(pcu)
          emitCChainBis(pcu)
          emitCtrBits(pcu)
          emitStageBits(pcu)
          emitScratchpadBits(pcu)
      }
    }
  }

  def commentMC(pmc:PMC) = {
    val mc = clmap.pmap(pmc)
    emitComment(s"mctpe=${mc.mctpe}")
  }

  def emitMCBit(pmc:PMC) = {
    clmap.pmap.get(pmc).foreach { mc =>
      emitComment(s"Configuring ${quote(pmc)} <- $mc")
      commentMC(pmc)
      emitScalarInXbar(pmc)
      emitControlBits(pmc)
    }
  }

  def emitCUBits = {
    cus.foreach {
      _.foreach { cu => emitCUBit(cu) }
    }
    ocus.foreach {
      _.foreach { cu => emitCUBit(cu) }
    }
    scus.foreach {
      _.foreach { cu => emitCUBit(cu) }
    }
    mcs.foreach {
      _.foreach { cu => emitMCBit(cu) }
    }
  }

  def emitMain {
    emitln("def main(args: String*) = plasticineBits")
  }

  def commentArgIns = {
    top.souts.foreach { psout =>
      vomap.pmap.get(psout).foreach { sout =>
        emitComment(s"${quote(psout)} -> $sout")
      }
    }
  }

  def emitPlasticineBits = {
    val cuArray = spade.cuArray
    emitLambda(s"val cus:Array[Array[CUBits]] = Array.tabulate(${cuArray.size}, ${cuArray.head.size})", "case (i,j)") {
      emitBlock(s"cuParams(i)(j) match") {
        emitln("case p:PCUParams => PCUBits.zeroes(p)")
        emitln("case p:PMUParams => PMUBits.zeroes(p)")
      }
    }
    emitLambda(s"val csbs = Array.tabulate(${sbs.size}, ${sbs.head.size})", "case (i,j)") {
      emitln(s"CrossbarBits.zeroes(controlSwitchParams(i)(j))")
    }
    emitLambda(s"val ssbs = Array.tabulate(${sbs.size}, ${sbs.head.size})", "case (i,j)") {
      emitln(s"CrossbarBits.zeroes(scalarSwitchParams(i)(j))")
    }
    emitLambda(s"val vsbs = Array.tabulate(${sbs.size}, ${sbs.head.size})", "case (i,j)") {
      emitln(s"CrossbarBits.zeroes(vectorSwitchParams(i)(j))")
    }
    emitLambda(s"val lcus = Array.tabulate(${ocus.size}, ${ocus.head.size})", "case (i,j)") {
      emitln(s"SwitchCUBits.zeroes(switchCUParams(i)(j))")
    }
    emitLambda(s"val scus = Array.tabulate(${scus.size}, ${scus.head.size})", "case (i,j)") {
      emitln(s"ScalarCUBits.zeroes(scalarCUParams(i)(j))")
    }
    emitLambda(s"val mcs = Array.tabulate(${mcs.size}, ${mcs.head.size})", "case (i,j)") {
      emitln(s"MemoryChannelBits.zeroes(memoryChannelParams(i)(j))")
    }

    implicit val ms = new CollectionStatus(false)

    emitInst(s"val plasticineBits = PlasticineBits") { implicit ms:CollectionStatus =>
      emitComma(s"cu=cus")
      emitComma(s"vectorSwitch=vsbs")
      emitComma(s"scalarSwitch=ssbs")
      emitComma(s"controlSwitch=csbs")
      emitComma(s"switchCU=lcus")
      emitComma(s"scalarCU=scus")
      emitComma(s"memoryChannel=mcs")
      emitComma(s"argOutMuxSelect=${quote(top.sins.map { in => muxIdx(in) })}")
      assert(top.cins.size==1)
      emitComma(s"doneSelect=${muxIdx(top.cins.head)}")
    }("")
    commentArgIns
    emitMain
  }

  def quote(n:PNode):String = n match {
    //case n:EmptyStage => s"EmptyStage"
    //case n:WAStage => s"WAStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    //case n:RAStage => s"RAStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    //case n:FUStage => s"FUStage(numOprds=${n.fu.numOprds}, ops=${quote(n.fu.ops)})"
    case n:PSCU =>
      val (x, y) = coordOf(n)
      x match {
        case -1 => s"scus(0)($y)"
        case `numCols` => s"scus(1)($y)"
        case _ => s"cus($x)($y).asSCUBits"
      }
    case n:PMC =>
      val (x, y) = coordOf(n)
      x match {
        case -1 => s"mcs(0)($y)"
        case `numCols` => s"mcs(1)($y)"
      }
    case n:POCU =>
      val (x, y) = coordOf(n)
      s"lcus($x)($y)"
    case n:PMCU =>
      val (x, y) = coordOf(n)
      s"cus($x)($y).asPMUBits"
    case n:PCU =>
      val (x, y) = coordOf(n)
      s"cus($x)($y).asPCUBits"
    case n:PST =>
      s"${quote(n.prt)}.stages(${n.index})"
    case n:PCtr =>
      s"${quote(n.prt)}.counterChain.counters(${n.index})"
    case n:PPR =>
      val pcu = n.prt
      val pst = n.stage
      s"${quote(pst)}.fwd(${n.reg.index})"
    case n:PMCCB =>
      s"${quote(n.prt)}"
    case n:PCB =>
      s"${quote(n.prt)}.control"
    case n:PSRAM =>
      s"${quote(n.prt)}.scratchpad"
    case n =>
      pir.spade.util.quote(n)
  }

  def quote(n:Op) = n match {
    case Bypass => s"BypassA"
    case n => s"$n"
  }

  def quote(n:List[_]):String = s"List(${n.mkString(",")})"

  def qv(n:Any):String = n match {
    case n:PSB =>
      val (x, y) = coordOf(n)
      s"vsbs($x)($y)"
    case n => quote(n)
  }

  def qs(n:Any):String = n match {
    case n:PSB =>
      val (x, y) = coordOf(n)
      s"ssbs($x)($y)"
    case n => quote(n)
  }

  def qc(n:Any):String = n match {
    case n:PSB =>
      val (x, y) = coordOf(n)
      s"csbs($x)($y)"
    case n => quote(n)
  }

  def q(n:PCU, pm:String):String = {
    val (x, y) = coordOf(n)
    s"${pm}_${x}_${y}"
  }

}
