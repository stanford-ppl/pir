package pir.mapper

import pir._
import pir.node._
import pir.util.typealias.{Seq => _, _}
import pir.pass.{Pass}

import spade._

import pirc._
import pirc.util._
import pirc.codegen._
import pirc.enums._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class ResourcePrescreen(override implicit val design:PIR) extends Pass with Logger {
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
      if (PIRConfig.mapping) {
        errmsg(s"$msg cls=${cls.size} pcls=${pcls.size}")
      } else {
        warn(s"$msg cls=${cls.size} pcls=${pcls.size}")
      }
      false
    } else {
      if (Config.verbose) info(s"$msg cls=${cls.size} pcls=${pcls.size}")
      dprintln(s"$msg cls=${cls.size} pcls=${pcls.size}")
      dprintln(s" - cls=${cls}")
      dprintln(s" - pcls=${pcls.map(quote)}")
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
    if (!pass && PIRConfig.mapping) throw PIRException(s"Not enough controller to map $design")
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
            cons += (("sfifos"	    , (cu.smems, pcu.sfifos)))
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
            cons += (("sfifos"	    , (cu.smems, pocu.sfifos)))
        }
        failureInfo(cl) += prt -> ListBuffer[String]()
        check(cons.toList, failureInfo(cl)(prt))
      }
    }
    val notFit = cls.filter { cl =>
      emitBlock(s"$cl") {
        val info = failureInfo(cl).map{ case (prt, info) => dprintln(s"${quote(prt)}: [${info.mkString(",")}]") }
      }
      map(cl).size==0
    }
    if (notFit.nonEmpty) {
      if (PIRConfig.mapping) throw PIRException(s"[${notFit.mkString(",")}] do not fit in any controller!")
      else return false
    } else {
      return true
    }
  }

  //def combination(list:Iterable[N], size:Int):Set[Set[N]] = emitBlock(s"combination(list=${list.size}, size=$size)"){
    //def recurse(set:Set[N], size:Int) = {
      //if (set.size < size) { Set() } else {
        //if (set.size == size) { Set(set) } else {
          //if (size==1) { set.map { n => Set(n) } } else {
            //set.flatMap { node =>
              //val rest = set - node
              //recurse(rest, size-1).map { comb => comb + node }
            //}
          //}
        //}
      //}
    //}
    //recurse(list.toSet, size)
  //}

  def combination(list:Seq[N], size:Int) = {
    val res = list.combinations(size).toList
    dprintln(s"combination(list=${list.size}, size=$size) = ${res.size}")
    res
  }

  def crossCheck:Boolean = emitBlock(s"crossCheck") {
    val nodes = resMap.keys.toList
    val N = nodes.size
    (2 until N).foreach { n =>
      combination(nodes, n).foreach { window =>
        val intersect = window.map { node => resMap(node) }.reduce { _ intersect _ }
        if (!intersect.isEmpty) {
          val sharedContains = ListBuffer[N]()
          val sharedNotContains = ListBuffer[N]()
          window.foreach { node =>
            val reses = resMap(node) 
            if (reses.size <= intersect.size) sharedContains += node
            else sharedNotContains += node
          }
          if (sharedContains.size > intersect.size) {
            emitBlock(s"window=${quote(window)}") {
              dprintln(s"intersect[${intersect.size}]=${quote(intersect)}")
              dprintln(s"sharedContains[${sharedContains.size}]=${quote(sharedContains)}")
              dprintln(s"sharedNotContains[${sharedNotContains.size}]=${quote(sharedNotContains)}")
            }
            dprintln(s"failed: sharedContains=${sharedContains.size} > intersect=${intersect}.size")
            if (PIRConfig.mapping) err(s"sharedContains=${sharedContains.size} > intersect=${intersect}.size")
            else warn(s"sharedContains=${sharedContains.size} > intersect=${intersect.size}")
            return false
          } else if (sharedContains.size == intersect.size) {
            sharedNotContains.foreach { node =>
              val others = resMap(node) diff intersect
              resMap(node) = others 
              dprintln(s"filter: $node -> others=${quote(others)} intersect=${quote(intersect)}")
            }
          } else {
            //TODO:Optimization opportunity at placement time. Narrow down search range for node
            //during placement. For now just change priority of placement
            sharedNotContains.foreach { node =>
              val others = resMap(node) diff intersect
              resMap(node) =  others ++ intersect
              dprintln(s"shuffle: $node -> others=${quote(others)} ++ intersect=${quote(intersect)}")
            }
          }
        }
      }
    }
    return true
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
    if (pass) pass = crossCheck
    if (pass) logMapping(resMap)
  }

  override def finPass = {
    close
    super.finPass
  }
}
