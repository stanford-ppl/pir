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
  override def toString = s"MappingException(${msg})"
}
case class TODOException(s:String)(implicit design:Design) extends MappingException {
  override val msg = s"TODO: ${s}"
}
case class IntConnct(cu:CU, pcu:PCU)(implicit design:Design) extends MappingException {
  override val msg = s"Fail to map ${cu} on ${pcu} due to interconnection constrain"
}
case class NoSolFound(n:Any)(implicit design:Design) extends MappingException {
  override val msg = s"No solution found to map node ${n}"
}
trait OutOfResource extends MappingException 
case class OutOfPMC(arch:Spade)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough MemoryControllers in ${arch} to map application"
} 
case class OutOfPCU(arch:Spade)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough ComputeUnits in ${arch} to map application"
} 
case class OutOfCtr(pcu:PCU)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough Counters in ${pcu} to map application"
}
case class OutOfSram(pcu:PCU)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough SRAMs in ${pcu} to map application"
}
case class OutOfScalarIns(pcu:PCU)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Input Buffer in ${pcu} to map application"
}
case class OutOfScalarOuts(pcu:PCU)(implicit design:Design) extends OutOfResource {
  override val msg = s"Not enough Scalar Outputs Buffer in ${pcu} to map application"
}

