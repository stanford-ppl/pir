package pir.graph.mapper
import pir.graph._
import pir.graph.{ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object CUMapper extends Mapper {
  type R = PCU
  type N = CU
  type VM = Map[Controller,PInBus] // Reader (Controler) -> InBus
  type V = (PCU, VecMapper.MP,
                 SRAMMapper.MP, 
                 CtrMapper.MP, 
                 ScalarInMapper.MP, 
                 ScalarOutMapper.MP)

  def getPcu(tup:V):PCU = tup._1
  def getVmap(tup:V):VecMapper.MP = tup._2
  def getSmap(tup:V):SRAMMapper.MP = tup._3
  def getCtmap(tup:V):CtrMapper.MP = tup._4
  def getSImap(tup:V):ScalarInMapper.MP = tup._5
  def getSOmap(tup:V):ScalarOutMapper.MP = tup._6

  def setPcu(tup:V, pcu:PCU):V = tup.copy(_1=pcu)
  def setVmap(tup:V, mp:VecMapper.MP):V = tup.copy(_2=mp) 
  def setSmap(tup:V, mp:SRAMMapper.MP):V = tup.copy(_3=mp) 
  def setCtmap(tup:V, mp:CtrMapper.MP):V = tup.copy(_4=mp) 
  def setSImap(tup:V, mp:ScalarInMapper.MP):V = tup.copy(_5=mp) 
  def setSOmap(tup:V, mp:ScalarOutMapper.MP):V = tup.copy(_6=mp) 

  def getPcu(cuMap:M, cu:N):PCU = getPcu(cuMap(cu))
  def getVmap(cuMap:M, cu:N):VecMapper.MP = getVmap(cuMap(cu))
  def getSmap(cuMap:M, cu:N):SRAMMapper.MP = getSmap(cuMap(cu))
  def getCtmap(cuMap:M, cu:N):CtrMapper.MP = getCtmap(cuMap(cu))
  def getSImap(cuMap:M, cu:N):ScalarInMapper.MP = getSImap(cuMap(cu))
  def getSOmap(cuMap:M, cu:N):ScalarOutMapper.MP = getSOmap(cuMap(cu))

  def setPcu(cuMap:M, cu:N, pcu:R):M = 
    if (!cuMap.contains(cu))
      cuMap + (cu -> (pcu, Map.empty, Map.empty, Map.empty, Map.empty, Map.empty))
    else
      cuMap + (cu -> setPcu(cuMap(cu), pcu))
  def setVmap(cuMap:M, cu:N, mp:VecMapper.MP):M = cuMap + (cu -> setVmap(cuMap(cu), mp))
  def setSmap(cuMap:M, cu:N, mp:SRAMMapper.MP):M = cuMap + (cu -> setSmap(cuMap(cu), mp))
  def setCtmap(cuMap:M, cu:N, mp:CtrMapper.MP):M = cuMap + (cu -> setCtmap(cuMap(cu), mp))
  def setSImap(cuMap:M, cu:N, mp:ScalarInMapper.MP):M = cuMap + (cu -> setSImap(cuMap(cu), mp))
  def setSOmap(cuMap:M, cu:N, mp:ScalarOutMapper.MP):M = cuMap + (cu -> setSOmap(cuMap(cu), mp))

  def printMap(m:MP)(implicit p:Printer) = {
    p.emitBS("cuMap")
    m.foreach{ case (cu, (pcu, vm, sm, cm, sim, som)) =>
      p.emitBS(s"$cu -> $pcu")
        p.emitBS("vecMap")
        vm.foreach{ case (k, v) => p.emitln(s"$k -> $v")}
        p.emitBE
        SRAMMapper.printMap(sm)
        CtrMapper.printMap(cm)
        ScalarInMapper.printMap(sim)
        ScalarOutMapper.printMap(som)
      p.emitBE
    }
    p.emitBE 
  }

  /* Saperate Compute Unit and Memory controller to map saperately */
  private def setResource(implicit design: Design):(List[PCU], List[CU], List[PTT], List[TT]) = {
    val ctrlNodes = design.top.ctrlNodes
    val arch = design.arch
    val pcus = ListBuffer[PCU]()
    val ptts = ListBuffer[PTT]()
    val cus = ListBuffer[CU]()
    val tts = ListBuffer[TT]()
    arch.computeUnits.foreach { c => c match {
        case n:PTT => ptts += n
        case n:PCU => pcus += n
        case n => throw TODOException(s"unknown Spade ComputeUnit type: ${n}") 
      }
    }
    ctrlNodes.foreach { c => c match {
        case n:TT => tts += n
        case n:CU => cus += n
        case n => throw TODOException(s"unknown PIR controller type: ${n}") 
      }
    }
    (pcus.toList, cus.toList, ptts.toList, tts.toList)
  }

  private def mapPrim(cuMap:M)(implicit design: Design):M = {
    cuMap.foldLeft(cuMap) { case (pm, (cu, v)) =>
    //println(s"cu:${cu}")
    //printMap(cuMap)(this)
    //flush 
      val pcu = getPcu(v)
      val m1    = SRAMMapper.map(cu, pcu, pm)
      val m2    = CtrMapper.map(cu, pcu, m1)
      val m3    = ScalarOutMapper.map(cu, pcu, m2)
      ScalarInMapper.map(cu, pcu, m3)
    }
  }

  private def mapCU(cu:N, pcu:R, cuMap:M)(implicit design: Design):M = {
    println(s"mapCU: ${cu} -- ${pcu} ")
    val cmap = setPcu(cuMap, cu, pcu) 
    //val p:Printer = new Printer{}; CUMapper.printMap(cmap)(p)
    /* Map CU */
   // Assume sin and vin have only one writer
    VecMapper.map(cu, pcu, cmap)
  }

  def mapRCU(pcus:List[PCU], cus:List[CU], cuMap:M)(implicit design: Design):M = {
    val cons = List(mapCU _)
    simAneal(pcus, cus, cuMap, cons, Some(mapPrim _), OutOfPCU(_, _))
  }

  def map(implicit design: Design):M = {
    val (pcus, cus, ptts, tts) = setResource
    val cons = List(mapCU _)
    simAneal(ptts.toList, tts.toList, Map.empty, cons,Some(mapRCU(pcus, cus, _)), OutOfPTT(_, _))
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
