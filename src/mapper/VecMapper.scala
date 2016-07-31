package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, Input => I, VecOut => VO, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, InBus => PIB, OutBus => POB}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object VecMapper extends Mapper {
  type R = PIB
  type N = I
  type V = (PIB, POB)

  def map(cl:CL, pcl:PCL, cuMap:M)(implicit design: Design):M = {
   // Assume sin and vin have only one writer
    val cons = List(mapVec(cl, pcl) _) 
    //val cmap = simAneal(pcl.vins, cl.vins ++ cl.sins, cuMap, cons, None, OutOfVec(cl, pcl, _, _))
    val cmap = cuMap
    // Check if need to handle postponed mapping
    cl.readers.foldLeft(cmap) { case (pm, r) =>
      if (pm.contains(r)) {
        val pr = pm.getPcu(r)
        val rcons = List(mapVec(r, pr) _) 
        val pcu = pm.getPcu(r).vins
        simAneal(pcu, r.vins, pm, rcons, None, OutOfVec(r, pr, _, _))
      } else pm
    }
  }

  def mapVec(cl:CL, pcl:PCL)(n:N, p:R, cuMap:M)(implicit design: Design):M = {
    val dep = n match {
      case n:ScalarIn => n.scalar.writers.head
      case n:VecIn => n.vector.writers.head
    }
    val pdvouts = dep match {
      case d:MemoryController => Nil
      case d => List(cuMap.getPcu(d).vouts) 
    }
    var vm = cuMap.getVmap(cl)
    assert(pdvouts.size == 1)
    val pdvout = pdvouts.head
    /* Find vins that connects to the depended ctrler */
    if (p.mapping.contains(pdvout)) {
      //println(s"suc: dep:${dep} n:${n} p:${p} pdvout:${pdvout}, p.mapping:${p.mapping}")
      //TODO:  + (n -> (p, pdvout))
      cuMap.setVmap(cl, cuMap.getVmap(cl))
    } else {
      //println(s"fail: dep:${dep} n:${n} p:${p} pdvout:${pdvout}, p.mapping:${p.mapping}")
      throw IntConnct(cl, pcl)
    }
  }
}

case class IntConnct(cl:CL, pcl:PCL)(implicit design:Design) extends MappingException {
  override val mapper = VecMapper 
  override val msg = s"Fail to map ${cl} on ${pcl} due to interconnection constrain"
}
case class OutOfVec(cl:CL, pcl:PCL, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = VecMapper
  override val msg = s"Not enough InBus IO in ${pcl} to map ${cl}."
}
