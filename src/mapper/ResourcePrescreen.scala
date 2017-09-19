package pir.mapper
import pir.node._
import pir.{PIR, Config}
import pir.util.typealias._
import pir.codegen.Printer
import pir.exceptions._
import pir.codegen.{Logger, CSVPrinter}
import pir.pass.{Pass}
import pir.spade.main._
import pir.util.misc._
import pir.util.topoSort
import pir.util.enums._
import pir.spade.arch._
import pir.spade.util._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

class ResourcePrescreen(implicit design:PIR) extends Pass with Logger {
  import pirmeta._
  type N = CL
  type R = PCL
  type M = mutable.Map[N, List[R]]

  val resMap:M = mutable.Map[N,List[R]]()

  override lazy val stream = newStream(s"Prescreen.log")

  override def shouldRun = true

  val summary = new CSVPrinter {
    override lazy val stream = newStream(Config.outDir, s"AppStats.csv", append=true)
  }

  lazy val prts = design.arch.prts
  lazy val cls = design.top.ctrlers
  lazy val mcs = cls.collect { case mc:MC => mc }
  lazy val pmcs = design.arch.mcs 
  lazy val sags = design.top.innerCUs.filter{ cu => sagOf.icontains(cu) } //TODO
  lazy val psags = design.arch.asInstanceOf[SwitchNetwork].sramAGs.flatten
  lazy val dags = design.top.innerCUs.filter{ cu => dagOf.icontains(cu) } 
  lazy val pdags = design.arch.asInstanceOf[SwitchNetwork].dramAGs.flatten
  lazy val scus = design.top.innerCUs.filter{ case cu:PL => (!cu.isMP) && (parOf(cu)==1) case _ => false }.diff(dags)
  lazy val pscus = design.arch.scus.diff(pdags)
  lazy val mps = cls.collect { case mp:MP => mp }
  //lazy val (mcus, mus) = mps.partition { mp => (mp.stages.size>0) && (mp.cchains.nonEmpty) }
  lazy val mcus = mps
  lazy val pmcus = design.arch.mcus 
  //lazy val pmus = design.arch.mus
  lazy val ocus = cls.collect { case ocu:OCL => ocu }
  lazy val pocus = design.arch.ocus 
  lazy val rcus = cls.collect { case cu:PL => cu }.diff(dags).diff(scus).diff(mcus)
  lazy val pcus = design.arch.pcus 


  def check(info:String, cond:Any):(Boolean,String) = {
      cond match {
        case cond:Boolean => 
          //if (!cond) dprintln(s"$info: pass:$cond numNodes:$nn numRes:$nr")
          (cond, s"$info: false")
        case (nn:Int, nr:Int) => val (pass,_) = check(info, (nn <= nr))
          (pass, s"$info: numNode:$nn numRes:$nr")
        case (ns:Iterable[_], rs:Iterable[_]) => check(info, (ns.size, rs.size))
        case c => throw PIRException(s"Unknown checking format: $cond")
      }
  }

  def check(conds:List[(String, Any)], failureInfo:ListBuffer[String]):Boolean = {
    conds.foldLeft(true) { case (qualify, cond) => 
      val (info, c) = cond
      val (pass, fi) = check(info, c) 
      if (!pass) failureInfo += fi 
      qualify && pass 
    }
  }

  def quantityCheck(map:Option[M], cls:List[CL], pcls:List[PCL], msg:String):Boolean = {
    if (cls.size > pcls.size) {
      if (Config.mapping) {
        errmsg(s"$msg cls=${cls.size} pcls=${pcls.size}")
      } else {
        warn(s"$msg cls=${cls.size} pcls=${pcls.size}")
      }
      false
    } else {
      if (Config.verbose) info(s"$msg cls=${cls.size} pcls=${pcls.size}")
      map.foreach { map => cls.foreach { cl => map += cl -> pcls } }
      true
    }
  }

  def controllerCheck:Boolean = {
    val map = resMap 
    var pass = true
    pass &= quantityCheck(Some(map), mcs , pmcs, "MemoryController")
    pass &= quantityCheck(Some(map), ocus , pocus, "OuterComputeUnit")
    //pass &= quantityCheck(Some(map), mus , (pmus ++ pmcus), "MemoryUnit")
    pass &= quantityCheck(Some(map), mcus , pmcus, "MemoryComputeUnit")
    pass &= quantityCheck(Some(map), dags , pdags, "DramAddrGen")
    pass &= quantityCheck(Some(map), sags , psags, "SramAddrGen")
    pass &= quantityCheck(Some(map), scus , (pscus ++ pcus), "ScalarComputeUnit")
    pass &= quantityCheck(Some(map), rcus , pcus, "PatternComputeUnit")
    pass &= quantityCheck(None     , (scus ++ rcus) , (pscus ++ pcus), "ComputeUnit")
    pass &= quantityCheck(Some(map), List(design.top), List(design.arch.top), "Top")
    if (!pass && Config.mapping) throw PIRException(s"Not enough controller to map $design")
    return pass
  }

  def primitiveCheck:Boolean = {
    val map = resMap 
    val failureInfo = mutable.Map[N, mutable.Map[R, ListBuffer[String]]]()
    cls.foreach { cl => 
      failureInfo += cl -> mutable.Map.empty
      map += cl -> map(cl).filter { prt =>
        val cons = ListBuffer[(String, Any)]()
        (cl, prt) match {
          case (cl:Top, pcu:PTop) =>
            cons += (("sin"	      , (cl.sins, pcu.sins.filter(_.isConnected))))
            cons += (("sout"	    , (cl.souts, pcu.souts.filter(_.isConnected))))
          case (mc:MC, pcu:PMC) =>
            cons += (("sin"	      , (cl.sins, pcu.sins.filter(_.isConnected))))
            cons += (("cout"	    , (cl.couts, pcu.couts.filter(_.isConnected))))
            cons += (("vin"	      , (cl.vins.filter(_.isConnected), pcu.vins.filter(_.isConnected))))
            cons += (("vout"	    , (cl.vouts.filter(_.isConnected), pcu.vouts.filter(_.isConnected))))
          case (cu:ICL, pcu:PCU)  =>
            val pcu = prt.asInstanceOf[PCU]
            cons += (("reg"	      , (cu.infGraph, pcu.regs)))
            cons += (("ctr"	      , (cu.cchains.flatMap(_.counters), pcu.ctrs)))
            cons += (("stage"	    , (cu.stages, pcu.stages)))
            cons += (("udc"	      , (cu.ctrlBox.udcounters, pcu.ctrlBox.udcs)))
            cons += (("sin"	      , (cl.sins, pcu.sins.filter(_.isConnected))))
            cons += (("sout"	    , (cl.souts, pcu.souts.filter(_.isConnected))))
            cons += (("vin"	      , (cl.vins.filter(_.isConnected), pcu.vins.filter(_.isConnected))))
            cons += (("vout"	    , (cl.vouts.filter(_.isConnected), pcu.vouts.filter(_.isConnected))))
            cons += (("cin"	      , (cl.cins.filter(_.isConnected).map(_.from).toSet, pcu.cins.filter(_.isConnected))))
            cons += (("cout"	    , (cl.couts, pcu.couts.filter(_.isConnected))))
            cons += (("sbufs"	    , (cu.smems, pcu.sbufs)))
            cons += (("srams"	    , (cu.srams, pcu.srams)))
            cu.srams.zip(pcu.srams).headOption.foreach { case (sram, psram) =>
              cons += (("sramSize"	    , (sram.size, psram.size / sram.buffering)))
              cons += (("sramBanks"	    , (sram.banks, psram.banks)))
            }
            cons += (("scalarInReg"	, (cu.regs.collect{case r@LoadPR(mem:ScalarMem) => r}, pcu.regs.filter(_.is(ScalarInReg)))))
          case (cu:OCL, pocu:POCU) =>
            cons += (("ctr"	      , (cu.cchains.flatMap(_.counters), pocu.ctrs)))
            cons += (("sin"	      , (cl.sins, pocu.scalarIO.ins)))
            cons += (("udc"	      , (cu.ctrlBox.udcounters, pocu.ctrlBox.udcs)))
            cons += (("cin"	      , (cl.cins.filter(_.isConnected).map(_.from).toSet, pocu.ctrlIO.ins.filter(_.fanIns.size>0))))
            cons += (("cout"	    , (cl.couts, pocu.couts.filter(_.isConnected))))
            cons += (("sbufs"	    , (cu.smems, pocu.sbufs)))
        }
        failureInfo(cl) += prt -> ListBuffer[String]()
        check(cons.toList, failureInfo(cl)(prt))
      }
    }
    val notFit = cls.filter { cl =>
      if (map(cl).size==0) {
        emitBlock(s"$cl") {
          val info = failureInfo(cl).map{ case (prt, info) => dprintln(s"${quote(prt)}: [${info.mkString(",")}]") }
        }
      }
      map(cl).size==0
    }
    if (notFit.nonEmpty) {
      if (Config.mapping) throw PIRException(s"[${notFit.mkString(",")}] do not fit in any controller!")
      else return false
    } else {
      return true
    }
  }

  def logMapping(map:mutable.Map[N, List[R]]) = {
    emitBlock(s"qualified resouce") {
      design.top.ctrlers.foreach { cl =>
        dprintln(s"$cl -> [${map(cl).map{ pcl => quote(pcl)}.reduce(_ + "," + _)}]")
      }
    }
  }

  def avgCnt[E](list:List[E], cnt: E => Float) = if (list.size==0) 0 else list.map { e => cnt(e) }.sum.toFloat / list.size

  def maxCnt[E](list:List[E], cnt: E => Float) = if (list.size==0) 0 else list.map { e => cnt(e) }.max

  def avgLanes(cus:List[CU]) = avgCnt(cus, (cu:CU) => parOf(cu) ) 

  def avgStages(cus:List[CU]) = avgCnt(cus, (cu:CU) => cu.stages.size ) 

  def avgRegs(cus:List[CU]) = avgCnt(cus, (cu:CU) => avgCnt(cu.stages, (st:ST) => st.prs.size))

  def maxStages(cus:List[CU]) = maxCnt(cus, (cu:CU) => cu.stages.size ) 

  def maxRegs(cus:List[CU]) = maxCnt(cus, (cu:CU) => maxCnt(cu.stages, (st:ST) => st.prs.size))

  addPass {
    val row = summary.addRow
    row += "App"           -> design.name
    row += "cls" -> cls.size
    row += "mcs" -> mcs.size
    row += "pcus" -> rcus.size
    row += "scus" -> scus.size
    row += "scus(MC)" -> dags.size
    row += "mcus" -> mps.size
    row += "ocus" -> ocus.size
    row += "pcuAvgStages" -> avgStages(rcus)
    row += "pcuMaxStages" -> maxStages(rcus)
    row += "scuAvgStages" -> avgStages(dags)
    row += "scuMaxStages" -> maxStages(dags)
    row += "scuAvgStages(MC)" -> avgStages(scus)
    row += "scuMaxStages(MC)" -> maxStages(scus)
    row += "mcuAvgStages" -> avgStages(mps) 
    row += "mcuMaxStages" -> maxStages(mps) 
    row += "pcuAvgRegs" -> avgRegs(rcus)
    row += "pcuMaxRegs" -> maxRegs(rcus)
    row += "scuAvgRegs" -> avgRegs(scus)
    row += "scuMaxRegs" -> maxRegs(scus)
    row += "scuAvgRegs(MC)" -> avgRegs(dags)
    row += "scuMaxRegs(MC)" -> maxRegs(dags)
    row += "mcuAvgRegs(MC)" -> avgRegs(mcus)
    row += "mcuMaxRegs(MC)" -> maxRegs(mcus)
    row += "cuAvgLane(MC)" -> avgLanes(rcus ++ scus) 
    summary.emitFile
    summary.close
  }

  /* 
   * Filter qualified resource. Create a mapping between cus and qualified pcus for each cu
   * */
  addPass {
    var pass = controllerCheck
    if (pass) pass = primitiveCheck
    if (pass) logMapping(resMap)
  }

  override def finPass = {
    close
    super.finPass
  }
}
