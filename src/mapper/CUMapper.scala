package pir.graph.mapper
import pir.graph._
import pir.graph.{ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object CUMapper extends Mapper{
  type R = PCU
  type N = CU
  type V = (PCU, Map[Controller,PInBus], // Reader (Controler) -> InBus
                 SRAMMapper.M, 
                 CtrMapper.M, 
                 ScalarInMapper.M, 
                 ScalarOutMapper.M)

  def printMap(m:M)(implicit p:Printer) = {
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
    val pmcs = ListBuffer[PTT]()
    val cus = ListBuffer[CU]()
    val mcs = ListBuffer[TT]()
    arch.computeUnits.foreach { c => c match {
        case n:PTT => pmcs += n
        case n:PCU => pcus += n
        case n => throw TODOException(s"unknown Spade ComputeUnit type: ${n}") 
      }
    }
    ctrlNodes.foreach { c => c match {
        case n:TT => mcs += n
        case n:CU => cus += n
        case n => throw TODOException(s"unknown PIR controller type: ${n}") 
      }
    }
    (pcus.toList, cus.toList, pmcs.toList, mcs.toList)
  }

  private def checkIntConnct(cu:N, pcu:R, cuMap:M)(implicit design: Design):M = {
    val suc = true

    def mapInBus(dc:Controller, vmap:Map[Controller,PInBus]):Map[Controller,PInBus] = {
      val validVins = dc match {
        case dep:ComputeUnit =>
          if (!cuMap.contains(dep))   // dep haven't been placed. Successed
            return vmap
          // dep has already been placed
          if (vmap.contains(dep)) return vmap
          val pdep = cuMap(dep)._1
          pcu.vins.filter{ pvin => pvin.src == pdep && !vmap.values.exists(_==pvin) }
        case dep:Top =>
          pcu.vins.filter{ pvin => !vmap.values.exists(_==pvin) }
        case dep:MemoryController =>
          pcu.vins.filter{ pvin => !vmap.values.exists(_==pvin) }
      }
      if (validVins.size == 0)  // pcu have no vin connecting to pdep that's not been used
        throw IntConnct(cu, pcu) 
      else
        vmap + (dc -> validVins.head) //Pick vin to the mapping
    }

    if (cu.vouts.size!=0 && cu.souts.size!=0)
      throw PIRException(s"CU ${cu} cannot have scalarOut and vecOut at the same time!")

    var vmap = cu.sins.foldLeft(Map[Controller,PInBus]()) { case (pm, ScalarIn(_, scalar)) =>
      assert(scalar.writers.size==1)
      val dep = scalar.writers.last
      mapInBus(dep, pm)
    }
    vmap = cu.vins.foldLeft(vmap){ case (pm, VecIn(_, vector)) =>
      assert(vector.writers.size==1)
      val dep = vector.writers.last
      mapInBus(dep, pm)
    }
    cuMap + (cu -> (pcu, vmap, Map.empty, Map.empty, Map.empty, Map.empty))
  }

  private def primMapping(cu:N, pcu:R, cuMap:M)(implicit design: Design):M = {
    val sm     = SRAMMapper.map(cu, pcu, cuMap)
    val cm     = CtrMapper.map(cu, pcu, cuMap)
    val som    = ScalarOutMapper.map(cu, pcu, cuMap)
    val sim    = ScalarInMapper.map(cu, pcu, cuMap)
    val vecMap = cuMap(cu)._2
    cuMap + (cu -> (pcu, vecMap, sm, cm, sim, som))
  }

  def map(implicit design: Design):M = {
    val (pcus, cus, pmcs, mcs) = setResource
    val cons = List(checkIntConnct _, primMapping _)
    val m = simAneal(pcus, cus, Map[N, V](), cons, OutOfPCU(_, _))
    simAneal(pmcs.toList, mcs.toList, m, cons, OutOfPTT(_, _))
  }
}
case class IntConnct(cu:CU, pcu:PCU)(implicit design:Design) extends MappingException {
  override val mapper = CUMapper
  override val msg = s"Fail to map ${cu} on ${pcu} due to interconnection constrain"
}
case class OutOfPTT(nres:Int, nnode:Int) (implicit design:Design) extends OutOfResource {
  override val mapper = CUMapper
  override val msg = s"Not enough TileTransfers in ${design.arch} to map application."
} 
case class OutOfPCU(nres:Int, nnode:Int) (implicit design:Design) extends OutOfResource {
  override val mapper = CUMapper
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
