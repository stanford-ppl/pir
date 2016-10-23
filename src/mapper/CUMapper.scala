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

class CUP2PMapper(outputMapper:OutputMapper, viMapper:VecInMapper)(implicit val design:Design) extends CUMapper {
  type R = PCL
  type N = SCL
  val typeStr = "CUP2PMapper"

  val resMap:MMap[N, List[R]] = MMap.empty

  def mapCU(cu:N, pcu:R, pirMap:M):M = {
    val cmap = pirMap.setCL(cu, pcu) 
    /* Map CU */
    Try {
      outputMapper.map(cu, cmap)
    }.map { m =>
      viMapper.map(cu, m)
    } match {
      case Success(m) => m
      case Failure(e) => throw e
    }
  }

  val cons = List(mapCU _)

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val pcus = design.arch.cus
    val cus = design.top.innerCUs
    val grp = cus.groupBy(_.isInstanceOf[TT]) 
    val pgrp = pcus.groupBy(_.isInstanceOf[PTT])
    val tts = grp.getOrElse(true, Nil)
    val ptts = pgrp.getOrElse(true, Nil)
    val rcus = grp.getOrElse(false, Nil)
    val prcus = pgrp.getOrElse(false, Nil)
    if (tts.size > ptts.size) throw OutOfPTT(ptts.size, tts.size)
    if (rcus.size > prcus.size) throw OutOfPCU(prcus.size, rcus.size)
    val nodes:List[SCL] = design.top::cus
    val reses = design.arch.top::pcus
    CUMapper.qualifyCheck(reses, nodes, resMap)
    def resFunc(cu:N, m:M, triedRes:List[R]):List[R] = {
      (resMap(cu).diff(triedRes)).filter { pcu => !m.clmap.pmap.contains(pcu)}
    }
    bind(nodes, m, cons, resFunc _, finPass _)
  }
}
trait CUMapper extends Mapper {
  override implicit val mapper:CUMapper = this
  def resMap:MMap[SCL, List[PCL]]
  def finPass(m:M):M = m
  def map(m:M):M
  override def debug = Config.debugCUMapper
}
object CUMapper {
  def apply(outputMapper:OutputMapper, viMapper:VecInMapper, ctrlMapper:CtrlMapper, fp:PIRMap => PIRMap)(implicit design:Design):CUMapper = {
    Config.routingAlgorithm match {
      case "P2P" => new CUP2PMapper(outputMapper, viMapper) { override def finPass(m:M):M = fp(m) }
      case "Switch" => new CUSwitchMapper(outputMapper, ctrlMapper) { override def finPass(m:M):M = fp(m) }
      case _ => throw PIRException("Unknown routing algorithm")
    }
  }
  def apply(outputMapper:OutputMapper, viMapper:VecInMapper, ctrlMapper:CtrlMapper)(implicit design:Design):CUMapper = {
    Config.routingAlgorithm match {
      case "P2P" => new CUP2PMapper(outputMapper, viMapper)
      case "Switch" => new CUSwitchMapper(outputMapper, ctrlMapper)
      case _ => throw PIRException("Unknown routing algorithm")
    }
  }
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
  def qualifyCheck(pcls:List[PCL], cls:List[SCL], map:MMap[SCL, List[PCL]])(implicit mapper:CUMapper, design:Design):Unit = {
    cls.foreach { cl => 
      val failureInfo = MMap[PCL, ListBuffer[String]]()
      map += cl -> pcls.filter { pcl =>
        val cons = ListBuffer[(String, Any)]()
        cl match {
          case top:Top if pcl.isInstanceOf[PTop] =>
          case cu:ICL if pcl.isInstanceOf[PCU] =>
            val pcu = pcl.asInstanceOf[PCU]
            cons += (("tttpe"       , cu.isInstanceOf[TT] == pcu.isInstanceOf[PTT]))
            cons += (("reg"	      , (cu.infGraph, pcu.regs)))
            cons += (("sram"	    , (cu.srams, pcu.srams)))
            cons += (("ctr"	      , (cu.cchains.flatMap(_.counters), pcu.ctrs)))
            cons += (("stage"	    , (cu.stages, pcu.stages)))
            cons += (("tokOut"	  , (cu.ctrlOuts, pcu.ctrlBox.ctrlOuts)))
            cons += (("tokIn"	    , (cu.ctrlIns, pcu.ctrlBox.ctrlIns)))
            cons += (("udc"	      , (cu.udcounters, pcu.ctrlBox.udcs)))
            cons += (("enLut"	    , (cu.enLUTs, pcu.ctrlBox.enLUTs)))
            cons += (("tokDownLut", (cu.tokDownLUTs, pcu.ctrlBox.tokenDownLUTs)))
            cons += (("tokOutLut" , (cu.tokOutLUTs, pcu.ctrlBox.tokenOutLUTs)))
          case _ =>
            cons += (("tpe"       , false))
        }
        cons += (("sin"	      , (cl.sins, pcl.sins)))
        cons += (("sout"	    , (cl.souts, pcl.souts)))
        cons += (("vin"	      , (cl.vins.filter(_.isConnected), pcl.vins.filter(_.fanIns.size>0))))
        cons += (("vout"	    , (cl.vouts.filter(_.isConnected), pcl.vouts.filter(_.fanOuts.size>0))))
        design.arch match {
          case sn:SwitchNetwork =>
            cons += (("cin"	      , (cl.ctrlIns.filter(_.isConnected), pcl.cins.filter(_.fanIns.size>0))))
            cons += (("cout"	    , (cl.ctrlOuts.filter(_.isConnected), pcl.couts.filter(_.fanOuts.size>0))))
          case pn:PointToPointNetwork =>
        }
        failureInfo += pcl -> ListBuffer[String]()
        check(cons.toList, failureInfo(pcl))
      }
      if (map(cl).size==0) throw CUOutOfSize(cl, 
          failureInfo.map{ case (pcl, info) => s"$pcl: [${info.mkString(",")}] \n"}.mkString(","))
      mapper.dprintln(s"qualified resource: $cl -> ${map(cl)}")
    }
  }
}

case class CUOutOfSize(cl:CL, info:String) (implicit val mapper:CUMapper, design:Design) extends MappingException {
  override val msg = s"cannot map ${cl} due to resource constrains\n${info}"
} 
case class OutOfPTT(nres:Int, nnode:Int) (implicit val mapper:CUMapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TileTransfers in ${design.arch} to map application."
} 
case class OutOfPCU(nres:Int, nnode:Int) (implicit val mapper:CUMapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
