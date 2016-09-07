package pir.graph.mapper
import pir.graph._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, InnerComputeUnit => ICU}
import pir._
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.codegen.Printer
import pir.graph.traversal.{PIRMapping, MapPrinter}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

class CUMapper(soMapper:ScalarOutMapper, viMapper:VecInMapper)(implicit val design:Design) extends Mapper {
  type R = PCL
  type N = CL
  type V = CLMap.V

  def finPass(m:M):M = m
  /* Saperate Compute Unit and Memory controller to map saperately */
  private def setResource:(List[PCU], List[ICU], List[PTT], List[TT]) = {
    //TODO match memory ctrler
    val inners = design.top.innerCUs
    val arch = design.arch
    val pcus = arch.rcus 
    val ptts = arch.ttcus 
    val cus = ListBuffer[ICU]()
    val tts = ListBuffer[TT]()
    inners.foreach { c => 
      c match {
        case n:TT => tts += n
        case _ => cus += c
      }
    }
    (pcus, cus.toList, ptts, tts.toList)
  }

  def mapCU(cu:N, pcu:R, pirMap:M):M = {
    var cmap = pirMap.setCL(cu, pcu) 
    /* Map CU */
   // Assume sin and vin have only one writer
    cmap = soMapper.map(cu, cmap)
    cmap = viMapper.map(cu, cmap)
    cmap
  }

  val cons = List(mapCU _)
  lazy val (pcus, cus, ptts, tts) = setResource

  def mapRCU(pirMap:M):M = {
    simAneal(pcus, cus, pirMap, cons, finPass _, OutOfPCU(_, _))
  }

  def mapTT(pirMap:M):M = {
    simAneal(ptts, tts, pirMap, cons, mapRCU _, OutOfPTT(_, _))
  }

  def map(m:M):M = {
    simAneal(List(design.arch.top), List(design.top), m, cons, mapTT _, OutOfPCU(_, _))
  }
}

case class OutOfPTT(nres:Int, nnode:Int) (implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough TileTransfers in ${design.arch} to map application."
} 
case class OutOfPCU(nres:Int, nnode:Int) (implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
