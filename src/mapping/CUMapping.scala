package pir.graph.mapping
import pir.graph._
import pir.graph.{ComputeUnit => CU, MemoryController => MC}
import pir.Design
import pir.Config
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}
import pir.graph.mapping
import pir.graph.mapping.CUMapping.PrimMapping
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

object CUMapping {
  type PrimMapping = (PCU, SRAMMapping, CtrMapping, ScalarMapping)
}
class CUMapping(implicit val design: Design) extends Mapping{
  override type R = PCU
  override type N = CU
  override type V = PrimMapping

  private val pcus = ListBuffer[PCU]()
  private val pmcs = ListBuffer[PMC]()
  private val cus = ListBuffer[CU]()
  private val mcs = ListBuffer[MC]()

  lazy private val arch = design.arch
  lazy private val top = design.top
  lazy private val allNodes = design.allNodes

  override def reset = {
    super.reset
    pcus.clear
    pmcs.clear
    cus.clear
    mcs.clear
  }

  /* Saperate Compute Unit and Memory controller to map saperately later on
   * Check whether design would fit the architecture using a rough count */
  private def setResource = {
    arch.computeUnits.foreach { c => c match {
        case n:PMC => pmcs += n
        case n:PCU => pcus += n
        case n => new Exception(s"TODO: unknown Spade ComputeUnit type: ${n}") 
      }
    }
    allNodes.foreach { c => c match {
        case n:MC => mcs += n
        case n:CU => cus += n
        case n => new Exception(s"TODO: unknown PIR controller type: ${n}") 
      }
    }
   if(cus.size > pcus.size) throw OutOfPCU(arch)
   if(mcs.size > pmcs.size) throw OutOfPMC(arch)
  }

  def checkIntConnct(cu:N, pcu:R, cuMap:Map[N, V]):Map[N, V] = {
    val suc = true
    if (!suc) throw IntConnct(cu, pcu)
    cuMap
  }

  def primMapping(cu:N, pcu:R, cuMap:Map[N, V]):Map[N, V] = {
    val sm = SRAMMapping(cu, pcu, cuMap)
    val cm = CtrMapping(cu, pcu, cuMap)
    val slm = ScalarMapping(cu, pcu, cuMap)
    cuMap + (cu -> (pcu, sm, cm, slm))
  }

  /* Constructor */
  setResource
  val cons = List(checkIntConnct _, primMapping _)
  private val (cuSuc, cm) = simAneal(pcus.toList, cus.toList, Map[N, V](), cons)
  private val (mcSuc, mm) = simAneal(pmcs.toList, mcs.toList, cm, cons)
  override val mapping = mm
  //println("-------- Finish CU Mapping ----------")

  import PIRMapping._
  override def printMap = {
    emitBS("cuMap")
    mapping.foreach{ case (cu, (pcu, sm, cm, slm)) =>
      emitln(s"[$cu -> $pcu]")
      sm.printMap
      cm.printMap
      slm.printMap
    }
    emitBE 
  }

}
