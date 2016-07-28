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
  type V = (PCU, VM,
                 SRAMMapper.MP, 
                 CtrMapper.MP, 
                 ScalarInMapper.MP, 
                 ScalarOutMapper.MP)

  def getPcu(tup:V):PCU = tup._1
  def getVmap(tup:V):VM = tup._2
  def getSmap(tup:V):SRAMMapper.MP = tup._3
  def getCtmap(tup:V):CtrMapper.MP = tup._4

  def setPcu(tup:V, pcu:PCU):V = tup.copy(_1=pcu)
  def setVmap(tup:V, mp:VM):V = tup.copy(_2=mp) 
  def setSmap(tup:V, mp:SRAMMapper.MP):V = tup.copy(_3=mp) 
  def setCtmap(tup:V, mp:CtrMapper.MP):V = tup.copy(_4=mp) 

  def getPcu(cuMap:M, cu:N):PCU = getPcu(cuMap(cu))
  def getVmap(cuMap:M, cu:N):VM = getVmap(cuMap(cu))
  def getSmap(cuMap:M, cu:N):SRAMMapper.MP = getSmap(cuMap(cu))
  def getCtmap(cuMap:M, cu:N):CtrMapper.MP = getCtmap(cuMap(cu))

  def setPcu(cuMap:M, cu:N, pcu:R):M = 
    if (!cuMap.contains(cu))
      cuMap + (cu -> (pcu, Map.empty, Map.empty, Map.empty, Map.empty, Map.empty))
    else
      cuMap + (cu -> setPcu(cuMap(cu), pcu))
  def setVmap(cuMap:M, cu:N, mp:VM):M = cuMap + (cu -> setVmap(cuMap(cu), mp))
  def setCtmap(cuMap:M, cu:N, mp:CtrMapper.MP):M = cuMap + (cu -> setCtmap(cuMap(cu), mp))
  def setSmap(cuMap:M, cu:N, mp:SRAMMapper.MP):M = cuMap + (cu -> setSmap(cuMap(cu), mp))

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
        //p.emitln(s"mapVins ${cu} -- ${pcu}"); printMap(cmap)(p); p.flush 
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
        pcu.vins.filter{ pvin => pvin.mapping.contains(pdep.vout) && !vm.values.exists(_==pvin) }
      case dep:Top =>
        pcu.vins.filter{ pvin => 
          pvin.mapping.contains(design.arch.argIns(0)) && !vm.values.exists(_==pvin)
        }
      case dep:MemoryController =>
        pcu.vins.filter{ pvin => !vm.values.exists(_==pvin) }
    }
    // pcu have no vin connecting to pdep that's not been used
    vm = if (validVins.size == 0) throw IntConnct(cu, pcu) 
         else vm + (dc -> validVins.head) //Pick vin to the mapping
    setVmap(cmap, cu, vm)
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
    var cmap = setPcu(cuMap, cu, pcu) 
    /* Map CU */
   // Assume sin and vin have only one writer
    val deps:List[Controller] = cu.writers 
    // println(s"cu: ${cu} deps: ${deps.mkString(", ")}")
    cmap = deps.foldLeft(cmap){ case (pm, dep) =>
      mapVins(cu, pcu, pm, dep)
    }
    //println(s"cu:${cu}")
    //printMap(cmap)(this)
    //flush 
    cmap
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
