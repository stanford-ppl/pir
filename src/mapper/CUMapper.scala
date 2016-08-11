package pir.graph.mapper
import pir.graph._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.codegen.Printer
import pir.graph.traversal.{PIRMapping, MapPrinter}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

object CUMapper extends Mapper {
  type R = PCL
  type N = CL
  type V = CLMap.V

  val finPass:Option[M => M] = Some(mapPrim _)
  /* Saperate Compute Unit and Memory controller to map saperately */
  private def setResource:(List[PCU], List[CU], List[PTT], List[TT]) = {
    //TODO match memory ctrler
    val inners = design.top.innerCUs
    val arch = design.arch
    val pcus = arch.rcus 
    val ptts = arch.ttcus 
    val cus = ListBuffer[CU]()
    val tts = ListBuffer[TT]()
    inners.foreach { c => c match {
        case n:TT => tts += n
        case n:CU => cus += n
        case n => throw TODOException(s"unknown PIR controller type: ${n}") 
      }
    }
    (pcus, cus.toList, ptts, tts.toList)
  }

  private def mapPrim(pirMap:M):M = {
    Try{
      pirMap.clmap.map.foldLeft(pirMap) { case (pm, (ctrler, v)) =>
        var cmap = pm
        cmap = ScalarInMapper.map(ctrler, cmap)
        ctrler match {
          case cu:ComputeUnit =>
            cmap = SRAMMapper.map(cu, cmap)
            cmap = CtrMapper.map(cu, cmap)
          case _ => pm
        }
        cmap
      }
    } match {
      case Success(m) => return m
      // TODO: at the moment if prim failed. stop trying
      case Failure(e) => MapPrinter.emitln(e.toString); System.exit(-1); throw e
    }
  }

  private def mapCU(cu:N, pcu:R, pirMap:M):M = {
    var cmap = pirMap.setCL(cu, pcu) 
    /* Map CU */
   // Assume sin and vin have only one writer
    cmap = ScalarOutMapper.map(cu, cmap)
    cmap = VecInMapper.map(cu, cmap)
    cmap
  }

  val cons = List(mapCU _)
  lazy val (pcus, cus, ptts, tts) = setResource

  def mapRCU(pirMap:M):M = {
    simAneal(pcus, cus, pirMap, cons, finPass, OutOfPCU(_, _))
  }

  def mapTT(pirMap:M):M = {
    simAneal(ptts, tts, pirMap, cons, Some(mapRCU _), OutOfPTT(_, _))
  }

  def map:M = {
    setDesign(design)
    simAneal(List(design.arch.top), List(design.top), PIRMap.empty, cons, Some(mapTT _), OutOfPCU(_, _))
  }
}

case class OutOfPTT(nres:Int, nnode:Int) (implicit design:Design) extends OutOfResource {
  override val mapper = CUMapper
  override val msg = s"Not enough TileTransfers in ${design.arch} to map application."
} 
case class OutOfPCU(nres:Int, nnode:Int) (implicit design:Design) extends OutOfResource {
  override val mapper = CUMapper
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
