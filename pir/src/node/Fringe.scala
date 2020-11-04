package pir
package node

import prism.graph._

trait Bus extends Serializable {
  def withLastBit = this match {
    case bus:FileEOFBus => true
    case _ => false
  }
}
case object DRAMBus extends Bus
case class BlackBoxBus(name:String) extends Bus
class FileBus(val fileName:String) extends Bus
object FileBus {
  def apply(fileName:String) = new FileBus(fileName)
  def unapply(x:FileBus) = Some(x.fileName)
}
case class FileEOFBus(override val fileName:String) extends FileBus(fileName)

trait BlackBox extends PIRNode
trait GlobalBlackBox extends BlackBox

trait FringeCommand extends BlackBox

trait DRAMCommand extends FringeCommand {
  def dram:DRAM
  val data:NodeField[PIRNode]
  override def compType(n:IR) = n match {
    case `data` => 
      dbg(_logger, s"$dram ${dram.tp.v}")
      dram.tp.v
    case _ => 
      dbg(_logger, s"$dram ${dram.tp.v} ${n} == ${data}")
      super.compType(n)
  }

  val burstSize = 512 // TODO: get from spadeparam
}

trait DRAMDenseCommand extends DRAMCommand {
  val offset = InputField[PIRNode]
  val size = InputField[PIRNode]
}
trait DRAMSparseCommand extends DRAMCommand {
  val addr = InputField[PIRNode].presetVec(1)
}
trait DRAMLoadCommand extends DRAMCommand {
  val data = OutputField[PIRNode]
}
trait DRAMStoreCommand extends DRAMCommand {
  val data = InputField[PIRNode]
  val ack = OutputField[PIRNode].tp(Bool).presetVec(1)
}

trait DRAMCoalCommand extends DRAMStoreCommand {
  val valid = InputField[PIRNode].tp(Bool)
}

trait DRAMDynStoreCommand extends DRAMStoreCommand {
  val done = InputField[PIRNode].tp(Bool)
}

case class FringeDenseLoad(dram:DRAM)(implicit env:Env) extends DRAMDenseCommand with DRAMLoadCommand {
  override def compVec(n:IR) = n match {
    case `data` => Some(burstSize /! data.inferTp.get.nbits.get)
    case _ => super.compVec(n)
  }
}

case class FringeSparseLoad(dram:DRAM)(implicit env:Env) extends DRAMSparseCommand with DRAMLoadCommand {
  data.presetVec(1)
}

case class FringeDenseStore(dram:DRAM)(implicit env:Env) extends DRAMDenseCommand with DRAMStoreCommand {
  val valid = InputField[PIRNode]
  override def compVec(n:IR) = n match {
    case `data` | `valid` => Some(burstSize /! data.inferTp.get.nbits.get)
    case _ => super.compVec(n)
  }
}

case class FringeSparseStore(dram:DRAM)(implicit env:Env) extends DRAMSparseCommand with DRAMStoreCommand {
  data.presetVec(1)
  ack.presetVec(1)
}

case class FringeCoalStore(dram:DRAM, par:Int)(implicit env:Env) extends DRAMDenseCommand with DRAMCoalCommand {
  data.presetVec(par)
  valid.presetVec(par)
  //override def compVec(n:IR) = n match {
    //case `data` | `valid` => Some(par.get)
    //case _ => super.compVec(n)
  //}
}

case class FringeDynStore(dram:DRAM, par:Int)(implicit env:Env) extends DRAMDenseCommand with DRAMDynStoreCommand {
  data.presetVec(par)
  done.presetVec(par)
  //override def compVec(n:IR) = n match {
    //case `data` | `valid` => Some(par.get)
    //case _ => super.compVec(n)
  //}
}

case class FringeStreamLoad(dram:DRAM, par:Int, comp:Boolean)(implicit env:Env) extends DRAMDenseCommand with DRAMLoadCommand {
  data.presetVec(par)
}

case class DRAM(sid:String) extends prism.graph.IR {
  val dims = Metadata[List[Int]]("dims")
  val sname = new Metadata[String]("sname")
  val tp = new Metadata[BitType]("tp")
  val compressed = new Metadata[Boolean]("compressed", default=Some(false))
}

trait StreamCommand extends FringeCommand {
  def streams:List[EN[PIRNode] with Field[PIRNode]]
}

case class FringeStreamWrite(bus:Bus)(implicit env:Env) extends StreamCommand {
  def addStreams(xs:List[Any]) = DynamicOutputFields[PIRNode]("stream", xs)
  override def streams = getDynamicOutputFields[PIRNode]("stream")
}
case class FringeStreamRead(bus:Bus)(implicit env:Env) extends StreamCommand {
  def addStreams(xs:List[Any]) = DynamicInputFields[PIRNode]("stream", xs)
  override def streams = getDynamicInputFields[PIRNode]("stream")
  val lastBit = OutputField[Option[PIRNode]].tp(Bool).presetVec(1)
}
