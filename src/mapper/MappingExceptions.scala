package pir.graph.mapper
import pir.graph.{ComputeUnit => CU, MemoryController => MC, _}
import pir.Design
import pir.Config
import pir.plasticine.config._
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap

abstract class MappingException(implicit design:Design) extends Exception{
  design.mapExceps += this
  val msg:String
  val mapper:Mapper
  override def toString = s"[$mapper]$msg"
}

case class TODOException(mapper:Mapper, s:String)(implicit design:Design) 
extends MappingException {
  override val msg = s"TODO: ${s}"
}

case class NoSolFound(mapper:Mapper, exceps:List[MappingException])(implicit design:Design) extends MappingException {
  override val msg = s"No solution found to map nodes to resources. Exceptions:\n ${exceps.mkString("\n")}"
}

//TODO: n should be node
case class FailToMapNode(mapper:Mapper, n:Any, exceps:List[MappingException])(implicit design:Design) extends MappingException {
  override val msg = s"No resource can map ${n}. Exceptions:\n ${exceps.mkString("\n")}"
}

trait OutOfResource extends MappingException {
  val nres:Int
  val nnode:Int
  override def toString = s"${super.toString}. numRes:${nres}, numNode:${nnode}."
}
case class OutOfPMC(mapper:Mapper, nres:Int, nnode:Int) (implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough MemoryControllers in ${design.arch} to map application."
} 
case class OutOfPCU(val mapper:Mapper, nres:Int, nnode:Int) (implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough ComputeUnits in ${design.arch} to map application."
} 
case class OutOfCtr(val mapper:Mapper, val pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough Counters in ${pcu} to map application."
}
case class OutOfSram(val mapper:Mapper, val pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough SRAMs in ${pcu} to map application."
}
case class OutOfScalarIn(val mapper:Mapper, val pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Input Buffer in ${pcu} to map application."
}
case class OutOfScalarOut(val mapper:Mapper, val pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcu} to map application."
}

/* Constrain exceptions */
case class IntConnct(mapper:Mapper, cu:CU, pcu:PCU)(implicit design:Design) extends MappingException {
  override val msg = s"Fail to map ${cu} on ${pcu} due to interconnection constrain"
}
