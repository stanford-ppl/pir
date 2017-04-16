package pir.codegen

import pir.Design
import pir.plasticine.main._
//import pir.plasticine.graph._
import pir.util.typealias._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class ConfigCodegen(implicit design: Design) extends Codegen with ScalaCodegen with MultiFileCodegen {
  def shouldRun = true
  import spademeta._

  val traitName = s"$design".replace(s"$$", "")
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

  override implicit def spade = design.arch.asSwitchNetwork
  lazy val numRows = spade.numRows
  lazy val numCols = spade.numCols

  def emitHeader = {
    emitln(s"package plasticine.apps")
    emitln(s"import plasticine.arch._")
    emitln(s"import chisel3._")
    emitln(s"import plasticine.spade._")
    emitln(s"import plasticine.pisa.ir._")
    emitln(s"import chisel3.util._")
    emitln(s"import scala.collection.mutable.ListBuffer")
    emitln(s"import GeneratedTopParams.plasticineParams._")
    emitln(s"import GeneratedTopParams._")
    emitln(s"import plasticine.templates._")
    emitln(s"import plasticine.pisa.enums._")
    emitln(1)
  }

  override def splitPreHeader:Unit = {
    emitHeader
  }

  override def splitPostHeader:Unit = {
    emitln(s"self:InOutArg =>")
  }

  def traverse = {
    emitHeader
    emitSplit {
      emitCrossbarBits
      emitCUBits
    }
    emitMixed(emitPlasticineBits)
  }
  
  def muxIdx(in:PI[PModule]) = {
    fimap.get(in).fold(-1) { out => in.indexOf(out) }
  }
  def muxIdx(out:PGO[PModule]) = {
    fimap.get(out.ic).fold(-1) { _.src.index }
  }

  def emitXbar(name:String, outs:List[PGO[PModule]]) = {
    outs.foreach { out => 
      val id = muxIdx(out)
      if (id != -1) {
        emitln(s"${name}.outSelect(${out.index}) = $id")
      }
    }
  }

  def emitCrossbarBits = {
    sbs.foreach { 
      _.foreach { sb =>
        emitXbar(s"${qv(sb)}", sb.vouts)
        emitXbar(s"${qs(sb)}", sb.souts)
        emitXbar(s"${qc(sb)}", sb.couts)
      }
    }
  }

  def lookUp(n:IP):String = { lookUp(ipmap(n)) }

  def lookUp(n:PI[PModule]):String = fimap.get(n) match {
    case None => s"SrcValueTuple()"
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
            case ts:PPR => throw new Exception(s"toStage=${quote(ts.stage)} prev=${ts.stage.prev.map(quote).getOrElse("None")} currStage=${quote(s.stage)}")
          }
        case s:PFU => ("ALUSrc", 0)
      }
      s"SrcValueTuple($src, $value)"
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
      s"SrcValueTuple($src, $value)"
    }
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
    emitln(s"val ${q(pcu, "ctrs")} = Array.tabulate(${pcu.ctrs.size}) { i => CounterRCBits.zeroes(${spade.wordWidth})}")
    emitln(s"val ${q(pcu, "cc")} = CounterChainBits(${quote(chain)}, ${q(pcu, "ctrs")})")
  }

  def emitFwdRegs(pst:PST) = {
    pst.prs.foreach { pr =>
      if (fimap.contains(pr.in)) {
        emitln(s"${quote(pr)} = ${lookUp(pr.in)}")
      }
    }
  }

  def emitStages(pcu:PCU) = {
    emitln(s"val ${q(pcu, "sts")} = Array.tabulate(${pcu.fustages.size}) { i => PipeStageBits.zeroes(${pcu.regs.size}, ${spade.wordWidth})}")
  }

  def emitStageBits(pcu:PCU) = {
    val cu = clmap.pmap(pcu)
    pcu.fustages.foreach { pst =>
      stmap.pmap.get(pst).fold {
        if (pst.prs.exists{ pr => fimap.contains(pr.in)}) {
          emitFwdRegs(pst)
        }
      } { st =>
        val pfu = pst.funcUnit.get
        val fu = st.fu.get
        val oprds = pfu.operands.map(lookUp)
        val fwd = s"Array.tabulate(${pst.prs.size}) { i => SrcValueTuple() }"
        val stBit = s"PipeStageBits(${oprds.mkString(",")}, ${fu.op}, ${quote(lookUp(pfu.out))}, $fwd)"
        emitln(s"${quote(pst)} = $stBit")
        emitFwdRegs(pst)
      }
    }
  }

  def emitCUBits = {
    cus.foreach { 
      _.foreach { pcu =>
        val (x,y) = pcu.coord
        val bitTp = pcu match {
          case cu:PMCU => "PMU"
          case cu:PCU => "PCU"
        }
        clmap.pmap.get(pcu).foreach { cu => 
          emitCommentBlock(s"Configuring ${quote(pcu)} <- $cu") {
            emitCChainBis(pcu)
            emitStages(pcu)
            emitln(s"${quote(pcu)} = ${bitTp}Bits(counterChain=${q(pcu, "cc")}, stages=${q(pcu, "sts")})")
            emitCtrBits(pcu)
            emitStageBits(pcu)
          }
        }
      }
    }
    scus.foreach {
      _.foreach { cu =>
        //TODO
      }
    }
    ocus.foreach {
      _.foreach { cu =>
        //TODO
      }
    }
    mcs.foreach {
      _.foreach { mc =>
        //TODO
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
    implicit val ms = new CollectionStatus(false)
    emitInst(s"val plasticineBits = PlasticineBits") { implicit ms:CollectionStatus =>
      emitComma(s"cu=cus")
      emitComma(s"vectorSwitch=vsbs")
      emitComma(s"scalarSwitch=ssbs")
      emitComma(s"controlSwitch=csbs")
      emitComma(s"switchCU=lcus")
      emitComma(s"argOutMuxSelect=${quote(top.sins.map { in => muxIdx(in) })}")
    }("")
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
      s"cus($x)($y)"
    case n:PCU =>
      val (x, y) = coordOf(n)
      s"cus($x)($y)"
    case n:PST =>
      s"${quote(n.pne)}.stages(${n.index})"
    case n:PCtr =>
      s"${quote(n.pne)}.counterChain.counters(${n.index})"
    case n:PPR =>
      val pcu = n.pne
      val pst = n.stage
      s"${quote(pst)}.fwd(${n.reg.index})"
    case n =>
      pir.plasticine.util.quote(n)
  }

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
