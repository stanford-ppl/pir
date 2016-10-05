package pir.graph.mapper
import pir._
import pir.typealias._
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

class VecInMapper(implicit val design:Design) extends Mapper {
  type R = PIB
  type N = VI
  val typeStr = "VIMapper"

  def finPass(cl:CL)(m:M):M = m
  override def debug = Config.debugVIMapper

  private def getOB(sin:SI, pirMap:M):POB = {
    pirMap.somap(sin.scalar.writer).outBus
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
   // Assume sin and vin have only one writer
    val cons = List(mapVec(cl, pcl) _) 

    val ins = cl match {
      case cl:TT => Nil // Assume tile transfer vin internallly connected
      case cl:MC => Nil
      case _ => cl.vins
    }

    val pvins = pcl.vins
    val pmap = bind(pvins, ins, pirMap, cons, finPass(cl) _)
    //TODO: OutOfVec(cl, pcl, _, _)

    cl.readers.foldLeft(pmap) { case (pm, reader) =>
      if (pm.clmap.contains(reader)) {
        if (reader.vins.exists( rin => !pm.vimap.contains(rin) )) map(reader, pm) else pm
      } else pm
    }
  }

  def mapVec(cl:CL, pcl:PCL)(n:N, p:R, pirMap:M):M = {
    if (pirMap.vimap.contains(n)) throw ResourceNotUsed(this, n, p, pirMap) 
    if (pirMap.vimap.pmap.contains(p)) throw UsedInBus(p)
    val dep = n.vector.writer.ctrler // ctrler that writes n
    // If reader ctrler dep haven't been placed, postpone mapping
    if (!pirMap.clmap.contains(dep)) throw ResourceNotUsed(this, n, p, pirMap)
    // Get dep's output bus 
    val pdvout:POB = pirMap.vomap(n.vector.writer)

    /* Find vins that connects to the depended ctrler */
    if (p.canFrom(pdvout)) {
      dprintln(s"n:$n ctrler:${n.ctrler}, p:$p, po:$pdvout ${pirMap.vimap.pmap.get(p)}")
      val pmap = pirMap.setVI(n, p).setFB(p, pdvout)
      pmap.setOP(n.out, p.viport)
    } else {
      throw InterConnct(cl, pcl)
    }
  }
}

case class UsedInBus(pib:PIB)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Resource ${pib} has already been used"
}
case class InterConnct(cl:CL, pcl:PCL)(implicit val mapper:Mapper, design:Design) extends MappingException {
  override val msg = s"Fail to map ${cl} on ${pcl} due to interconnection constrain"
}
case class OutOfVec(cl:CL, pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough InBus IO in ${pcl} to map ${cl}."
}
