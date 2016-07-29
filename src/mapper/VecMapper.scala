package pir.graph.mapper
import pir._
import pir.graph.{ComputeUnit => CU, TileTransfer => TT, VecIn => VI, VecOut => VO, _}
import pir.plasticine.graph.{ComputeUnit => PCU, TileTransfer => PTT, InBus => PIB, OutBus => POB}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object VecMapper extends Mapper {
  type R = PIB
  type N = VI
  type V = (PIB, POB)

  def getIB(cuMap:M, cu:CU, vi:N) = {
    CUMapper.getVmap(cuMap, cu)(vi)._1
  }
  def getOB(cuMap:M, cu:CU, vi:N) = {
    CUMapper.getVmap(cuMap, cu)(vi)._2
  }
  override def printMap(m:MP)(implicit p:Printer) = {
    p.emitBS("vecMap")
    m.foreach{ case (k,v) =>
      p.emitln(s"$k -> $v")
    }
    p.emitBE
  }

  def map(cu:CU, pcu:PCU, cuMap:CUMapper.M)(implicit design: Design):M = {
   // Assume sin and vin have only one writer
    val deps:List[Controller] = cu.writers 
    // println(s"cu: ${cu} deps: ${deps.mkString(", ")}")
    var cmap = deps.foldLeft(cuMap){ case (pm, dep) => 
      val cons = List(mapVec(cu, pcu, dep) _) 
      if (dep.isInstanceOf[CU] && !cuMap.contains(dep.asInstanceOf[CU])) {
        pm // dep ctrler haven't been placed. postpone mapping of vins until dep is mapped 
      } else {
        simAneal(pcu.vins, cu.vins, cuMap, cons, None, OutOfVec(cu, pcu, _, _))
      }
    }
    // Check if need to handle postponed mapping
    cu.readers.foldLeft(cmap) { case (pm, reader) =>
      reader match {
        case r:CU =>
          if (pm.contains(r)) {
            val cons = List(mapVec(r, CUMapper.getPcu(pm, r), cu) _) 
            simAneal(CUMapper.getPcu(pm, r).vins, r.vins, pm, cons, None, OutOfVec(cu, pcu, _, _))
          } else pm
        case _ => pm //TODO
      }
    }
  }

  def mapVec(cu:CU, pcu:PCU, dep:Controller)(n:N, p:R, cuMap:M)(implicit design: Design):M = {
    val pdvouts = dep match {
      case d:Top => design.arch.argIns
      case d:MemoryController => Nil
      case d:CU => List(CUMapper.getPcu(cuMap, d).vout) 
    }
    var vm = CUMapper.getVmap(cuMap, cu)
    assert(pdvouts.size == 1)
    val pdvout = pdvouts.head
    /* Find vins that connects to the depended ctrler */
    if (p.mapping.contains(pdvout)) {
      println(s"suc: dep:${dep} n:${n} p:${p} pdvout:${pdvout}, p.mapping:${p.mapping}")
      CUMapper.setVmap(cuMap, cu, CUMapper.getVmap(cuMap, cu) + (n -> (p, pdvout)))
    } else {
      println(s"fail: dep:${dep} n:${n} p:${p} pdvout:${pdvout}, p.mapping:${p.mapping}")
      throw IntConnct(cu, pcu)
    }
  }

}
case class IntConnct(cu:CU, pcu:PCU)(implicit design:Design) extends MappingException {
  override val mapper = VecMapper 
  override val msg = s"Fail to map ${cu} on ${pcu} due to interconnection constrain"
}
case class OutOfVec(cu:CU, pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = VecMapper
  override val msg = s"Not enough InBus IO in ${pcu} to map ${cu}."
}
