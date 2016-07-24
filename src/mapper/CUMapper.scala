package pir.graph.mapper
import pir.graph._
import pir.graph.{ComputeUnit => CU, MemoryController => MC}
import pir._
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object CUMapper extends Mapper{
  type R = PCU
  type N = CU
  type V = (PCU, SRAMMapper.M, CtrMapper.M, ScalarInMapper.M, ScalarOutMapper.M)

  def printMap(m:M)(implicit p:Printer) = {
    p.emitBS("cuMap")
    m.foreach{ case (cu, (pcu, sm, cm, sim, som)) =>
      p.emitln(s"[$cu -> $pcu]")
      SRAMMapper.printMap(sm)
      CtrMapper.printMap(cm)
      ScalarInMapper.printMap(sim)
      ScalarOutMapper.printMap(som)
    }
    p.emitBE 
  }

  /* Saperate Compute Unit and Memory controller to map saperately */
  private def setResource(implicit design: Design):(List[PCU], List[CU], List[PMC], List[MC]) = {
    val ctrlNodes = design.top.ctrlNodes
    val arch = design.arch
    val pcus = ListBuffer[PCU]()
    val pmcs = ListBuffer[PMC]()
    val cus = ListBuffer[CU]()
    val mcs = ListBuffer[MC]()
    arch.computeUnits.foreach { c => c match {
        case n:PMC => pmcs += n
        case n:PCU => pcus += n
        case n => throw new TODOException(this, s"unknown Spade ComputeUnit type: ${n}") 
      }
    }
    ctrlNodes.foreach { c => c match {
        case n:MC => mcs += n
        case n:CU => cus += n
        case n => throw new TODOException(this, s"unknown PIR controller type: ${n}") 
      }
    }
    (pcus.toList, cus.toList, pmcs.toList, mcs.toList)
  }

  private def checkIntConnct(cu:N, pcu:R, cuMap:M)(implicit design: Design):M = {
    val suc = true
    if (!suc) throw IntConnct(this, cu, pcu)
    else cuMap
  }

  private def primMapping(cu:N, pcu:R, cuMap:M)(implicit design: Design):M = {
    val sm = SRAMMapper.map(cu, pcu, cuMap)
    val cm = CtrMapper.map(cu, pcu, cuMap)
    val som = ScalarOutMapper.map(cu, pcu, cuMap)
    val sim = ScalarInMapper.map(cu, pcu, cuMap)
    cuMap + (cu -> (pcu, sm, cm, sim, som))
  }

  def map(implicit design: Design):M = {
    val (pcus, cus, pmcs, mcs) = setResource
    val cons = List(checkIntConnct _, primMapping _)
    val m = simAneal(pcus, cus, Map[N, V](), cons, OutOfPCU(this, _, _))
    simAneal(pmcs.toList, mcs.toList, m, cons, OutOfPMC(this, _, _))
  }
}
