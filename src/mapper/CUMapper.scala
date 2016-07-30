package pir.graph.mapper
import pir.graph._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

trait CLMap {
  type K = CLMap.K
  type V = CLMap.V
  type M = CLMap.M

  val map: M 

  def apply(k:K):V = map(k)
  def getPcu(k:K):PCL = map(k)._1
  def getVmap(k:K):VecMapper.MP = map(k)._2
  def getSmap(k:K):SRAMMapper.MP = map(k)._3
  def getCtmap(k:K):CtrMapper.MP = map(k)._4
  def getSImap(k:K):ScalarInMapper.MP = map(k)._5
  def getSOmap(k:K):ScalarOutMapper.MP = map(k)._6

  def update(k:K, v:V):CLMap = CLMap(map + (k -> v))
  def setPcu(k:K, pcu:PCL):CLMap = CLMap(map + (k -> map.getOrElse(k, CLMap.emptyV).copy(_1=pcu)))
  def setVmap(k:K, mp:VecMapper.MP):CLMap = CLMap(map + (k -> map(k).copy(_2=mp))) 
  def setSmap(k:K, mp:SRAMMapper.MP):CLMap = CLMap(map + (k -> map(k).copy(_3=mp)))
  def setCtmap(k:K, mp:CtrMapper.MP):CLMap = CLMap(map + (k -> map(k).copy(_4=mp)))
  def setSImap(k:K, mp:ScalarInMapper.MP):CLMap = CLMap(map + (k -> map(k).copy(_5=mp)))
  def setSOmap(k:K, mp:ScalarOutMapper.MP):CLMap = CLMap(map + (k -> map(k).copy(_6=mp)))

  def contains(k:K) = map.contains(k)

  def printMap(implicit p:Printer) = {
    p.emitBlock("clMap") {
      map.foreach{ case (cl, (pcl, vm, sm, cm, sim, som)) =>
        p.emitBlock(s"$cl -> $pcl") {
          VecMapper.printMap(vm)
          SRAMMapper.printMap(sm)
          CtrMapper.printMap(cm)
          ScalarInMapper.printMap(sim)
          ScalarOutMapper.printMap(som)
        }
      }
    }
  p.close
  }
}

object CLMap {
  type K = CL
  type V = (PCL, VecMapper.MP,
                 SRAMMapper.MP, 
                 CtrMapper.MP, 
                 ScalarInMapper.MP, 
                 ScalarOutMapper.MP)
  type M = Map[K,V]

  def apply(m:M) = new { override val map = m } with CLMap
  def empty:CLMap = CLMap(Map.empty)
  def emptyV:V = (null, Map.empty, Map.empty, Map.empty, Map.empty, Map.empty)
}

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
    println(s"mapCU: ${cu} -- ${pcu} ")
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
