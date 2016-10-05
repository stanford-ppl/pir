package pir.graph.mapper
import pir.graph._
import pir._
import pir.typealias._
import pir.codegen.Printer
import pir.graph.traversal.{PIRMapping, MapPrinter}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

class CUMapper(soMapper:OutputMapper, viMapper:VecInMapper)(implicit val design:Design) extends Mapper {
  type R = PCL
  type N = CL
  type V = CLMap.V
  val typeStr = "CUMapper"
  override def debug = Config.debugCUMapper

  def finPass(m:M):M = m

  val resMap:MMap[N, List[R]] = MMap.empty

  def resFunc(cu:N, m:M, remainRes:List[R]):List[R] = {
    resMap(cu).filter { pcu => !m.clmap.pmap.contains(pcu) }
  }

  def mapCU(cu:N, pcu:R, pirMap:M):M = {
    val cmap = pirMap.setCL(cu, pcu) 
    /* Map CU */
    Try {
      soMapper.map(cu, cmap)
    }.map { m =>
      viMapper.map(cu, m)
    } match {
      case Success(m) => m
      case Failure(e) => throw e
    }
  }

  val cons = List(mapCU _)

  def mapCUs(pcus:List[PCU], cus:List[ICL], pirMap:M, finPass:M => M):M = {
    CUMapper.qualifyCheck(pcus, cus, resMap)
    // Bind nodes to resources
    bind(pcus, cus, pirMap, cons, resFunc _, finPass)
  }

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
    Try{
      mapCU(design.top, design.arch.top, m) 
    } match {
      case Success(mp) => mapCUs(pcus, cus, mp, finPass _)
      case Failure(e) => throw e
    }
  }
}
object CUMapper {
  def check(info:String, cond:Any):(Boolean,String) = {
      cond match {
        case cond:Boolean => 
          //if (!cond) dprintln(s"$info: pass:$cond numNodes:$nn numRes:$nr")
          (cond, s"$info: false")
        case (nn:Int, nr:Int) => val (pass,_) = check(info, (nn <= nr))
          (pass, s"$info: numNode:$nn numRes:$nr")
        case (ns:Iterable[_], rs:Iterable[_]) => check(info, (ns.size, rs.size))
        case c => 
          println(c)
          throw PIRException(s"Unknown checking format: $cond")
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
  def qualifyCheck(pcus:List[PCU], cus:List[ICL], map:MMap[CL, List[PCL]])(implicit mapper:Mapper, design:Design):Unit = {
    cus.foreach { cu => 
      val failureInfo = MMap[PCU, ListBuffer[String]]()
      map += cu -> pcus.filter { pcu =>
        val cons = ListBuffer[(String, Any)]()
        cons += (("tpe"       ,  cu.isInstanceOf[TT] == pcu.isInstanceOf[PTT]))
        cons += (("reg"	      , (cu.infGraph, pcu.regs)))
        cons += (("sram"	    , (cu.srams, pcu.srams)))
        cons += (("ctr"	      , (cu.cchains.flatMap(_.counters), pcu.ctrs)))
        cons += (("sin"	      , (cu.sins, pcu.sins)))
        cons += (("sout"	    , (cu.souts, pcu.souts)))
        cons += (("vin"	      , (cu.vins, pcu.vins)))
        cons += (("vout"	    , (cu.vouts, pcu.vouts)))
        cons += (("stage"	    , (cu.stages, pcu.stages)))
        cons += (("tokOut"	  , (cu.ctrlOuts, pcu.ctrlBox.ctrlOuts)))
        cons += (("tokIn"	    , (cu.ctrlIns, pcu.ctrlBox.ctrlIns)))
        cons += (("udc"	      , (cu.udcounters, pcu.ctrlBox.udcs)))
        cons += (("enLut"	    , (cu.enLUTs, pcu.ctrlBox.enLUTs)))
        cons += (("tokDownLut", (cu.tokDownLUTs, pcu.ctrlBox.tokenDownLUTs)))
        cons += (("tokOutLut" , (cu.tokOutLUTs, pcu.ctrlBox.tokenOutLUTs)))
        failureInfo += pcu -> ListBuffer[String]()
        check(cons.toList, failureInfo(pcu))
      }
      if (map(cu).size==0) 
        throw CUOutOfSize(cu, 
          failureInfo.map{ case (pcu, info) => s"$pcu: [${info.mkString(",")}] \n"}.mkString(","))
      mapper.dprintln(s"qualified resource: $cu -> ${map(cu)}")
    }
  }
}

case class CUOutOfSize(cu:CU, info:String) (implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"cannot map ${cu} due to resource constrains\n${info}"
} 
case class OutOfPTT(nres:Int, nnode:Int) (implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TileTransfers in ${design.arch} to map application."
} 
case class OutOfPCU(nres:Int, nnode:Int) (implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
