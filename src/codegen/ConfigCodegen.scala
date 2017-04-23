package pir.codegen

import pir.Design
import pir.plasticine.main._
import pir.plasticine.graph.{ScalarOutReg}
import pir.util.typealias._
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
  
  def top = spade.top
  def sbs = spade.sbArray
  def cus = spade.cuArray
  def ocus = spade.ocuArray
  def scus = spade.scuArray
  def mcs = spade.mcArray

  override implicit lazy val spade = design.arch.asSwitchNetwork
  lazy val numRows = spade.numRows
  lazy val numCols = spade.numCols

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

  def emitAppObject { // Yaqi: Check/fix
    emitln(s"object $appName extends PISADesign with $traitName")
  }

  override def splitPreHeader:Unit = {
    emitHeader
  }

  override def splitPostHeader:Unit = {
    emitln(s"self:$traitName =>")
    emitBSln(s"def config${fileNumber}:Unit = ")
  }

  override def splitPreFooter:Unit = {
    emitBEln
  }

  def traverse = {
    emitHeader
    emitAppObject
    emitSplit {
      emitCrossbarBits
      emitCUBits
    }
    emitMixed {
      emitPlasticineBits
      emitBlock(s"def config:Unit = ") {
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
      val (src, value) = o.src match {
        case s:PCtr => ("CounterSrc", s.index)
        case s:PSMem => ("ScalarFIFOSrc", s.index)
        case s:PVMem => ("VectorFIFOSrc", s.index)
        case s:PConst =>
          val const = pmmap.pmap(s)
          const match {
            case s:Const if s.isBool => ("ConstSrc", s.value)
            case s:Const if s.isInt => ("ConstSrc", s.value)
            case s:Const if s.isFloat => ("ConstSrc", s.value)
          }
        case s:PPR =>
          n.src match {
            case ts:PPR if ts.stage.isNext(s.stage) => ("PrevStageSrc", s.reg.index) 
            case ts:PPR if ts.stage == s.stage => ("CurrStageSrc", s.reg.index)
            case fu:PFU if fu.stage.isNext(s.stage) => ("PrevStageSrc", s.reg.index) 
            case fu:PFU if fu.stage == s.stage => ("CurrStageSrc", s.reg.index) 
            case ts:PPR => throw new Exception(s"toStage=${quote(ts.stage)} prev=${ts.stage.prev.map(quote).getOrElse("None")} currStage=${quote(s.stage)}")
          }
        case s:PFU => ("ALUSrc", 0)
      }
      s"$SVT($src, $value)"
  }

  def lookUp(n:PO[PModule]):List[String] = {
    fimap.pmap(n).toList.map { i =>
      val (src,value) =  i.src match {
        case s:PPR =>
          n.src match {
            case ts:PFU => ("CurrStageDst", s"${s.reg.index}")
          }
        case s:PGO[_] if networkOf(s).isVectorNetwork => ("VectorOutDst", s.index)
        case s:PGO[_] if networkOf(s).isScalarNetwork => ("ScalarOutDst", s.index)
      }
      s"$SVT($src, $value)"
    }
  }

  def lookUp(n:PAT):List[Int] = {
    n.ins.map { in => if (ipmap.pmap.contains(in)) 1 else 0 }
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

  def emitStageBits(pcu:PCU) = {
    val cu = clmap.pmap(pcu)
    pcu.fustages.foreach { pst =>
      stmap.pmap.get(pst).fold {
        cu match {
          case cu:MP => 
            emitln(s"${quote(pst)}.enableSelect = XSrc")
          case cu:ICL =>  // by default turns on
        }
      } { st =>
        val pfu = pst.funcUnit.get
        val fu = st.fu.get
        emitln(s"${quote(pst)}.opA = ${lookUp(pfu.operands(0))}")
        emitln(s"${quote(pst)}.opB = ${lookUp(pfu.operands(1))}")
        emitln(s"${quote(pst)}.opC = ${lookUp(pfu.operands(2))}")
        emitln(s"${quote(pst)}.opcode = ${fu.op}")
        emitln(s"${quote(pst)}.res = ${quote(lookUp(pfu.out))}")
        cu match {
          case cu:MP if forWrite(st) => 
            emitln(s"${quote(pst)}.enableSelect = WriteEn")
          case cu:MP if forRead(st) => 
            emitln(s"${quote(pst)}.enableSelect = ReadEn")
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

  def emitAndTree(pcb:PCB, at:PAT) = {
    val config = lookUp(at)
    if (config.nonEmpty) emitln(s"${quote(pcb)}.${at.name} = ${config}")
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

  def emitXbars(pcu:PCU) = {
    pcu.ctrlBox match {
      case pcb:PICB =>
        emitXbar(s"${quote(pcb)}.incrementXbar", pcb.udcs.map(_.inc))
        emitXbar(s"${quote(pcb)}.swapWriteXbar", pcu.sbufs.map(_.incWritePtr))
        emitXbar(s"${quote(pcb)}.tokenOutXbar", pcu.couts.map(_.ic))
        emitXbar(s"${quote(pcb)}.doneXbar", List(pcb.doneXbar.in))
      case pcb:POCB =>
        emitXbar("incrementXbar", pcb.udcs.map(_.inc))
        emitln(s"${quote(pcb)}.udcDecSelect=${quote(pcb.udcs.map(udc => muxIdx(udc.dec)))}")
        emitXbar("swapWriteXbar", pcu.sbufs.map(_.incWritePtr))
        emitXbar(s"${quote(pcb)}.doneXbar", List(pcb.doneXbar.in))
      case pcb:PMCB =>
        emitXbar("swapWriteXbar", pcu.sbufs.map(_.incWritePtr))
        emitXbar(s"${quote(pcb)}.readDoneXbar", List(pcb.readDoneXbar.in))
        emitXbar(s"${quote(pcb)}.writeDoneXbar", List(pcb.writeDoneXbar.in))
      case pcb =>
    }
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
        val idxes = pcu.sbufs.map(sbuf => muxIdx(sbuf.incReadPtr))
        emitln(s"${quote(pcb)}.scalarSwapReadSelect = ${quote(idxes)}")
      case pcu =>
    }
  }

  def emitControlBits(pcu:PCU) = {
    val pcb = pcu.ctrlBox
    commentUDCs(pcu)
    emitAndTrees(pcu)
    emitStreamingMuxSelect(pcu)
    emitXbars(pcu)
    emitSwapReadSelect(pcu)
  }

  def emitScalarInXbar(pcu:PCU) = {
    val sins = pcu.sbufs.map { sbuf => fimap.get(sbuf.writePort).map { po => po.src } }
    emitComment(s"${quote(pcu)}.scalarInXbar=[${sins.mkString(",")}]")
    emitXbar(s"${quote(pcu)}.scalarInXbar", pcu.sbufs.map(_.writePort))
  }

  def emitScalarOutXbar(pcu:PCU) = {
    val souts = pcu.souts.map { sout => fimap.get(sout.ic).map { po => po.src } }
    emitComment(s"${quote(pcu)}.scalarOutXbar=[${souts.mkString(",")}]")
    val soRegs = pcu.regs.filter{ _.is(ScalarOutReg) }
    val soIdxes = souts.map(_.map(ppr => soRegs.indexOf(ppr.asInstanceOf[PPR].reg) ).getOrElse(-1))
    emitXbar(s"${quote(pcu)}.scalarOutXbar", pcu.souts.map(_.ic))
  }

  def emitCUBit(pcu:PCU) = {
    clmap.pmap.get(pcu).foreach { cu => 
      emitComment(s"Configuring ${quote(pcu)} <- $cu")
      emitControlBits(pcu)
      emitScalarInXbar(pcu)
      emitScalarOutXbar(pcu)
      emitCChainBis(pcu)
      emitCtrBits(pcu)
      emitStageBits(pcu)
    }
  }

  def emitCUBit(pcu:POCU) = {
    clmap.pmap.get(pcu).foreach { cu => 
      emitComment(s"Configuring ${quote(pcu)} <- $cu")
      emitCChainBis(pcu)
      emitControlBits(pcu)
      emitCtrBits(pcu)
    }
  }

  def emitCUBits = {
    cus.foreach { 
      _.foreach { cu => emitCUBit(cu) }
    }
    scus.foreach {
      _.foreach { cu =>
        //TODO
      }
    }
    ocus.foreach {
      _.foreach { cu => emitCUBit(cu) }
    }
    mcs.foreach {
      _.foreach { mc =>
        //TODO
      }
    }
  }

  def emitMain {
    emitln("def main(args: String*) = plasticineBits")
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
    implicit val ms = new CollectionStatus(false)

    emitInst(s"val plasticineBits = PlasticineBits") { implicit ms:CollectionStatus =>
      emitComma(s"cu=cus")
      emitComma(s"vectorSwitch=vsbs")
      emitComma(s"scalarSwitch=ssbs")
      emitComma(s"controlSwitch=csbs")
      emitComma(s"switchCU=lcus")
      emitComma(s"argOutMuxSelect=${quote(top.sins.map { in => muxIdx(in) })}")
    }("")

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
      s"${quote(n.pne)}.stages(${n.index})"
    case n:PCtr =>
      s"${quote(n.pne)}.counterChain.counters(${n.index})"
    case n:PPR =>
      val pcu = n.pne
      val pst = n.stage
      s"${quote(pst)}.fwd(${n.reg.index})"
    case n:PCB =>
      s"${quote(n.pne)}.control"
    case n =>
      pir.plasticine.util.quote(n)
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

  //def q(p:PPRIM, pm:String):String = {
    //val i = p.index
    //val pcu = p.pne
    //s"${quote(pcu)}.${pm}($i)"
  //}
}
