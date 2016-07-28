package pir.graph.mapper
import pir.graph._
import pir.graph.{ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT, InBus => PInBus}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object CUMapper extends Mapper with Printer{
  type R = PCU
  type N = CU
  type VM = Map[Controller,PInBus] // Reader (Controler) -> InBus
  type V = (PCU, VM,
                 SRAMMapper.M, 
                 CtrMapper.M, 
                 ScalarInMapper.M, 
                 ScalarOutMapper.M)

  def getPcu(tup:V):PCU = tup._1
  def getVmap(tup:V):VM = tup._2
  def getPcu(cuMap:M, cu:N):PCU = getPcu(cuMap(cu))
  def getVmap(cuMap:M, cu:N):VM = getVmap(cuMap(cu))

  def setCmap(cuMap:M, cu:N, pcu:R):M = 
    if (cuMap.contains(cu)) {
      val m = cuMap(cu)
      cuMap + (cu -> (pcu, m._2, m._3, m._4, m._5, m._6))
    } else {
      cuMap + (cu -> (pcu, Map.empty, Map.empty, Map.empty, Map.empty, Map.empty))
    }

  def setCmap(cuMap:M, cu:N, pcu:R, vmap:VM):M = 
    if (cuMap.contains(cu)) {
      val m = cuMap(cu)
      cuMap + (cu -> (pcu, vmap, m._3, m._4, m._5, m._6))
    } else {
      cuMap + (cu -> (pcu, vmap, Map.empty, Map.empty, Map.empty, Map.empty))
    }
  def setCmap(cuMap:M, cu:N, v:V):M = cuMap + (cu -> v)

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

  private def mapVins(cu:N, pcu:R, cuMap:M, dc:Controller) (implicit design: Design):M = {
    var cmap = cuMap
    var vm = getVmap(cmap, cu)
    if (vm.contains(dc)) return cmap 
    val validVins = dc match {
      case dep:ComputeUnit =>
        // dep ctrler haven't been placed. postpone mapping of vins until dep is mapped 
        if (!cmap.contains(dep)) return cmap            
        // map dependent vins if dependent ctrler is placed
        cu match {
          case c:TileTransfer =>
          case c:ComputeUnit => if (cu.vouts.size!=0 && cu.souts.size!=0)
            throw PIRException(s"CU ${cu} cannot have scalarOut and vecOut at the same time!")
        }
        cmap = cu.readers.foldLeft(cmap) { case (pm, reader) =>
          reader match {
            case r:ComputeUnit =>
              if (pm.contains(r)) mapVins(r, getPcu(pm, r), pm, cu)
              else pm
            case _ => pm //TODO
          }
        }
        /* Find vins that connects to the depended ctrler */
        val pdep = getPcu(cmap, dep) 
        pcu.vins.filter{ pvin => pvin.connTo(pdep) && !vm.values.exists(_==pvin) }
      case dep:Top =>
        pcu.vins.filter{ pvin => !vm.values.exists(_==pvin) }
      case dep:MemoryController =>
        pcu.vins.filter{ pvin => !vm.values.exists(_==pvin) }
    }
    // pcu have no vin connecting to pdep that's not been used
    vm = if (validVins.size == 0) throw IntConnct(cu, pcu) 
         else vm + (dc -> validVins.head) //Pick vin to the mapping
    cmap = setCmap(cmap, cu, pcu, vm)
    //println(s"mapVins:---------")
    //printMap(cmap)(this)
    //flush 
    cmap
  }

  private def matchCU(cu:N, pcu:R, cuMap:M)(implicit design: Design):M = {
    var cmap = setCmap(cuMap, cu, pcu) 
    /* Map CU */
    cmap = cu.sins.foldLeft(cmap) { case (pm, ScalarIn(_, scalar)) =>
      assert(scalar.writers.size==1)
      val dep = scalar.writers.last
      mapVins(cu, pcu, pm, dep)
    }
    cmap = cu.vins.foldLeft(cmap){ case (pm, VecIn(_, vector)) =>
      assert(vector.writers.size==1)
      val dep = vector.writers.last
      mapVins(cu, pcu, pm, dep)
    }
    //println(s"cmap: matchCU-------")
    //println(s"cu:${cu}")
    //printMap(cmap)(this)
    //flush 
    cmap
  }

  private def mapPrim(cuMap:M)(implicit design: Design):M = {
    cuMap.foldLeft(cuMap) { case (pm, (cu, v)) =>
    //println(s"cu:${cu}")
    //printMap(cuMap)(this)
    //flush 
      val pcu = getPcu(v)
      val sm     = SRAMMapper.map(cu, pcu, pm)
      val cm     = CtrMapper.map(cu, pcu, pm)
      val som    = ScalarOutMapper.map(cu, pcu, pm)
      val sim    = ScalarInMapper.map(cu, pcu, pm)
      setCmap(pm, cu, (pcu, getVmap(v), sm, cm, sim, som))
    }
  }

  def mapRCU(pcus:List[PCU], cus:List[CU], cuMap:M)(implicit design: Design):M = {
    val cons = List(matchCU _)
    simAneal(pcus, cus, cuMap, cons, Some(mapPrim _), OutOfPCU(_, _))
  }

  def map(implicit design: Design):M = {
    val (pcus, cus, ptts, tts) = setResource
    val cons = List(matchCU _)
    simAneal(ptts.toList, tts.toList, Map.empty, cons,Some(mapRCU(pcus, cus, _)), OutOfPTT(_, _))
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
