package pir.graph.mapper
import pir.graph._
import pir._
import pir.typealias._
import pir.codegen.Printer
import pir.graph.traversal.{PIRMapping, MapPrinter}
import pir.plasticine.main._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

trait CUMapper extends Mapper {
  override implicit val mapper:CUMapper = this
  def resMap:MMap[CL, List[PNE]]
  def finPass(m:M):M = m
  def map(m:M):M
  override def debug = Config.debugCUMapper

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

  /* 
   * Filter qualified resource. Create a mapping between cus and qualified pcus for each cu
   * */
  def qualifyCheck(pnes:List[PNE], cls:List[CL], map:MMap[CL, List[PNE]])(implicit mapper:CUMapper, design:Design):Unit = {
    val mcs = cls.collect { case mc:MC => mc }
    val pmcs = pnes.collect { case pmc:PMC => pmc }
    val scus = mcs.filter { _.mctpe.isDense }.map { _.ofs.writer.ctrler.asInstanceOf[StreamPipeline] }
    val pscus = pnes.collect { case pscu:PSCU => pscu }
    val ocus = cls.collect { case ocu:OCL => ocu }
    val psbs = pnes.collect { case sb:PSB => sb }
    val rcus = cls.collect { case pcu:CU => pcu }.diff(scus).diff(mcs).diff(ocus)
    val pcus = pnes.collect { case ppcu:PCU => ppcu }.diff(pscus).diff(psbs)
    if (mcs.size > pmcs.size) throw OutOfPMC(pmcs, mcs)
    if (ocus.size > psbs.size) throw OutOfPSB(psbs, ocus)
    if (scus.size > pscus.size) throw OutOfSCU(pscus, scus)
    if (rcus.size > pcus.size) throw OutOfPCU(pcus, rcus)
    cls.foreach { cl => 
      val failureInfo = MMap[PNE, ListBuffer[String]]()
      map += cl -> pnes.filter { pne =>
        val cons = ListBuffer[(String, Any)]()
        cl match {
          case top:Top if pne.isInstanceOf[PTop] =>
            //cons += (("sin"	      , (cl.sins.size, pne.vins.size))) //TODO
          case mc:MC if pne.isInstanceOf[PMC] =>
          case scu:SP if scus.contains(scu) & pne.isInstanceOf[PSCU] =>
          case cu:ICL if pne.isInstanceOf[PCU] =>
            val pcu = pne.asInstanceOf[PCU]
            cons += (("reg"	      , (cu.infGraph, pcu.regs)))
            cons += (("ctr"	      , (cu.cchains.flatMap(_.counters), pcu.ctrs)))
            cons += (("stage"	    , (cu.stages, pcu.stages)))
            cons += (("tokOut"	  , (cu.ctrlOuts, pcu.ctrlIO.outs)))
            cons += (("tokIn"	    , (cu.ctrlIns, pcu.ctrlIO.ins)))
            cons += (("udc"	      , (cu.udcounters, pcu.ctrlBox.udcs)))
            cons += (("sin"	      , (cl.sins, pcu.sins)))
            cons += (("sout"	    , (cl.souts, pcu.souts)))
            cons += (("vin"	      , (cl.vins.filter(_.isConnected), pcu.vins.filter(_.fanIns.size>0))))
            cons += (("vout"	    , (cl.vouts.filter(_.isConnected), pcu.vouts.filter(_.fanOuts.size>0))))
            cons += (("cin"	      , (cl.ctrlIns.filter(_.isConnected).map(_.from).toSet, pcu.cins.filter(_.fanIns.size>0))))
            cons += (("cout"	    , (cl.ctrlOuts.filter(_.isConnected), pcu.couts.filter(_.fanOuts.size>0))))
            cu match {
              case mc:MemoryController => 
              case _ => 
                cons += (("onchipmem"	, (cu.mems, pcu.srams)))
            }
          case cu:OCL if pne.isInstanceOf[PSB] =>
            val psb = pne.asInstanceOf[PSB]
            cons += (("sin"	      , (cl.sins, psb.scalarIO.ins)))
            cons += (("cin"	      , (cl.ctrlIns.filter(_.isConnected).map(_.from).toSet, psb.ctrlIO.ins.filter(_.fanIns.size>0))))
            cons += (("cout"	    , (cl.ctrlOuts.filter(_.isConnected), psb.ctrlIO.outs.filter(_.fanOuts.size>0))))
          case _ =>
            cons += (("tpe"       , false))
        }
        design.arch match {
          case sn:SwitchNetwork =>
          case pn:PointToPointNetwork =>
        }
        failureInfo += pne -> ListBuffer[String]()
        check(cons.toList, failureInfo(pne))
      }
      if (map(cl).size==0) {
        val info = failureInfo.map{ case (pne, info) => s"$pne: [${info.mkString(",")}] \n"}.mkString(",")
        println(info)
        throw CUOutOfSize(cl, info)
      }
      mapper.dprintln(s"qualified resource: $cl -> ${map(cl)}")
    }
  }
}

object CUMapper {
  def apply(outputMapper:OutputMapper, viMapper:VecInMapper, ctrlMapper:CtrlMapper, fp:PIRMap => PIRMap)(implicit design:Design):CUMapper = {
    design.arch match {
      case sn:SwitchNetwork => new CUSwitchMapper(outputMapper, ctrlMapper) { override def finPass(m:M):M = fp(m) }
      case pn:PointToPointNetwork => new CUP2PMapper(outputMapper, viMapper) { override def finPass(m:M):M = fp(m) }
      case _ => throw PIRException("Unknown network type")
    }
  }
  def apply(outputMapper:OutputMapper, viMapper:VecInMapper, ctrlMapper:CtrlMapper)(implicit design:Design):CUMapper = {
    design.arch match {
      case sn:SwitchNetwork => new CUSwitchMapper(outputMapper, ctrlMapper)
      case pn:PointToPointNetwork => new CUP2PMapper(outputMapper, viMapper)
      case _ => throw PIRException("Unknown network type")
    }
  }
}

case class CUOutOfSize(cl:CL, info:String) (implicit val mapper:CUMapper, design:Design) extends MappingException {
  override val msg = s"cannot map ${cl} due to resource constrains\n${info}"
} 
case class OutOfPMC(pnodes:List[PMC], nodes:List[MC]) (implicit val mapper:CUMapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough MemoryController in ${design.arch} to map application."
} 
case class OutOfPCU(pnodes:List[PCU], nodes:List[CU]) (implicit val mapper:CUMapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
case class OutOfSCU(pnodes:List[PSCU], nodes:List[SP]) (implicit val mapper:CUMapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough ScalarComputeUnits in ${design.arch} to map application."
} 
case class OutOfPSB(pnodes:List[PSB], nodes:List[OCL]) (implicit val mapper:CUMapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough SwitchBox in ${design.arch} to map application."
} 
