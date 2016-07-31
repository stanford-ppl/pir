package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, Input => I, VecOut => VO, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, InBus => PIB, OutBus => POB}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object VecInMapper extends Mapper {
  type R = PIB
  type N = I

  def map(cl:CL, cuMap:M):M = {
    val pcl = cuMap.getPcu(cl)
   // Assume sin and vin have only one writer
    val cons = List(mapVec(cl, pcl) _) 
    val cmap = simAneal(pcl.vins, cl.vins ++ cl.sins, cuMap, cons, None, OutOfVec(cl, pcl, _, _))
    cl.readers.foldLeft(cmap) { case (pm, reader) =>
      if (cuMap.contains(reader))
        map(reader, pm)
      else
        pm
    }
  }

  def mapVec(cl:CL, pcl:PCL)(n:N, p:R, cuMap:M):M = {
    if (cuMap.getVImap(cl).contains(n))
      return cuMap
    val dep = n match {
      case n:ScalarIn => n.scalar.writer.ctrler
      case n:VecIn => n.vector.writer.ctrler
    }
    if (!cuMap.contains(dep))
      return cuMap
    val pdvout:POB = n match {
      case n:VecIn => dep match {
        //case d:MemoryController => Nil //TODO
        case d:CU => cuMap.getPcu(d).vouts.head //TODO Assume only 1 vout 
        case _ => throw TODOException(s"Not supported vecout mapping")
      }
      case n:ScalarIn =>
        cuMap.getSLmap(dep).getOutBus(n.scalar)
    } 

    /* Find vins that connects to the depended ctrler */
    if (p.mapping.contains(pdvout)) {
      //println(s"suc: dep:${dep} n:${n} p:${p} pdvout:${pdvout}, p.mapping:${p.mapping}")
      val cmap = cuMap.setVI(cl, n, p)
      cmap.setIB(cl, p, pdvout)
    } else {
      //println(s"fail: dep:${dep} n:${n} p:${p} pdvout:${pdvout}, p.mapping:${p.mapping}")
      throw IntConnct(cl, pcl)
    }
  }
}

case class IntConnct(cl:CL, pcl:PCL)(implicit design:Design) extends MappingException {
  override val mapper = VecInMapper 
  override val msg = s"Fail to map ${cl} on ${pcl} due to interconnection constrain"
}
case class OutOfVec(cl:CL, pcl:PCL, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = VecInMapper
  override val msg = s"Not enough InBus IO in ${pcl} to map ${cl}."
}
