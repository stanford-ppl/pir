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
  type N = I
  val typeStr = "VIMapper"

  def finPass(cl:CL)(m:M):M = m

  private def getOB(sin:SI, pirMap:M):POB = {
    pirMap.somap(sin.scalar.writer).outBus
  }

  def map(cl:CL, pirMap:M):M = {
    val pcl = pirMap.clmap(cl)
   // Assume sin and vin have only one writer
    val cons = List(mapVec(cl, pcl) _) 

    val ins = cl match {
      case cl:TT => cl.sins // Assume tile transfer vin internallly connected
      case _ => cl.vins ++ cl.sins
    }

    val pvins = pcl.vins
    val pmap = bind(pvins, ins, pirMap, cons, finPass(cl) _)
    //TODO: OutOfVec(cl, pcl, _, _)

    cl.readers.foldLeft(pmap) { case (pm, reader) =>
      if (pm.clmap.contains(reader)) {
        val rins = reader.vins ++ reader.sins
        if (rins.exists( rin => !pm.vimap.contains(rin) )) map(reader, pm) else pm
      } else pm
    }
  }

  def mapVec(cl:CL, pcl:PCL)(n:N, p:R, pirMap:M):M = {
    if (pirMap.vimap.contains(n)) throw ResourceNotUsed(this, n, p, pirMap) 
    val dep = n match { // ctrler that writes n
      case n:SI => n.scalar.writer.ctrler
      case n:VI => n.vector.writer.ctrler
    }
    // If reader ctrler dep haven't been placed, postpone mapping
    if (!pirMap.clmap.contains(dep)) throw ResourceNotUsed(this, n, p, pirMap)
    // Get dep's output bus 
    val pdvout:POB = n match {
      case n:VI => dep match {
        //case d:MemoryController => Nil //TODO
        case d:CU => pirMap.clmap(d).vouts.head //TODO Assume only 1 vout 
        case _ => throw TODOException(s"Not supported vecout mapping")
      }
      case n:SI => getOB(n, pirMap)
    } 

    /* Find vins that connects to the depended ctrler */
    if (p.canFrom(pdvout)) {
      val pmap = pirMap.setVI(n, p).setFB(p, pdvout)
      n match {
        case n:VI => pmap.setOP(n.out, p.viport)
        case n:SI => 
          cl.sins.foldLeft(pmap) { case (pmap, sin) =>
            if (sin.scalar.writer.ctrler==n.scalar.writer.ctrler) {
              if (getOB(sin, pmap) == pdvout && 
                !pmap.vimap.contains(sin)) {
                pmap.setVI(sin, p)
              } else pmap
            } else pmap
          } // opmap set at scalarIn mapper
      }
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
