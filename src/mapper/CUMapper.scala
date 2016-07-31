package pir.graph.mapper
import pir.graph._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object CUMapper extends Mapper {
  type R = PCL
  type N = CL
  type V = CLMap.V

  /* Saperate Compute Unit and Memory controller to map saperately */
  private def setResource:(List[PCU], List[CU], List[PTT], List[TT]) = {
    val ctrlNodes = design.top.ctrlNodes
    val arch = design.arch
    val pcus = arch.rcus 
    val ptts = arch.ttcus 
    val cus = ListBuffer[CU]()
    val tts = ListBuffer[TT]()
    ctrlNodes.foreach { c => c match {
        case n:TT => tts += n
        case n:CU => cus += n
        case n => throw TODOException(s"unknown PIR controller type: ${n}") 
      }
    }
    (pcus, cus.toList, ptts, tts.toList)
  }

  private def mapPrim(cuMap:M):M = {
    cuMap.map.foldLeft(cuMap) { case (pm, (ctrler, v)) => ctrler match {
        case cu:ComputeUnit =>
          val pcu = cuMap.getPcu(cu).asInstanceOf[PCU]
          val m1    = SRAMMapper.map(cu, pcu, pm)
          val m2    = CtrMapper.map(cu, pcu, m1)
          ScalarOutMapper.map(cu, pcu, m2)
          //ScalarInMapper.map(cu, pcu, m3)
        case _ => pm
      }
    }
  }

  private def mapCU(cu:N, pcu:R, cuMap:M):M = {
    //println(s"mapCU: ${cu} -- ${pcu} ")
    val cmap = cuMap.setPcu(cu, pcu) 
    //val p:Printer = new Printer{}; CUMapper.printMap(cmap)(p)
    /* Map CU */
   // Assume sin and vin have only one writer
    VecMapper.map(cu, pcu, cmap)
  }

  val cons = List(mapCU _)
  lazy val (pcus, cus, ptts, tts) = setResource

  def mapRCU(cuMap:M):M = {
    //simAneal(pcus, cus, cuMap, cons, Some(mapPrim _), OutOfPCU(_, _))
    simAneal(pcus, cus, cuMap, cons, None, OutOfPCU(_, _)) //TODO
  }

  def mapTT(cuMap:M):M = {
    simAneal(ptts, tts, cuMap, cons, Some(mapRCU _), OutOfPTT(_, _))
  }

  def map(implicit design: Design):M = {
    setDesign(design)
    simAneal(List(design.arch.top), List(design.top), CLMap.empty, cons, Some(mapTT _), OutOfPCU(_, _))
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
