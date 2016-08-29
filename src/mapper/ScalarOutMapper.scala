package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, MemoryController => MC, 
                  ScalarOut => SO}
import pir.plasticine.graph.{Controller => PCL}
import pir.plasticine.graph.{ScalarOut => PSO}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

class ScalarOutMapper(implicit val design:Design) extends Mapper {
  type N = SO 
  type R = PSO 
  type V = PSO 

  def finPass(cl:CL)(m:M):M = m

  private def mapScalarOuts(cl:CL)(n:N, p:R, cuMap:M):M = {
    cl match {
      case c:MC => cuMap
      case c:TT => cuMap.setSO(n, p).setIP(n.in, p.in) // ScalarOut of TileTransfer is internally connected to MC
      case _ => // CU + Top
        val t = (p.outBus, p.idx)
        val cmap = cuMap.setSL(n.scalar, t)
        cmap.setSO(n, p).setIP(n.in, p.in)
    }
  }

  def map(cl:CL, cuMap:M):M = {
    val pcl = cuMap.clmap(cl)
    val souts = cl.souts
    val psouts = pcl.souts
    val cons = List(mapScalarOuts(cl) _)
    simAneal(psouts, souts, cuMap, cons, finPass(cl) _, OutOfScalarOut(pcl, _, _))
  }
}

case class OutOfScalarOut(pcl:PCL, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcl} to map application."
}
