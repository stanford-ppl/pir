package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.util.misc._
import pir.exceptions._
import pir.codegen.Logger

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map

class AccessAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = true 
  import pirmeta._

  override lazy val stream = newStream(s"AccessAnalyzer.log")

  def setWriter(mem:OnChipMem) = {
    writerOf(mem) = mem.writePort.from.src match {
      case fifo:FIFO => fifo.writer
      case VecIn(_, vector) => vector.writer.ctrler
      case ScalarIn(_, scalar) => scalar.writer.ctrler
      case p => 
        throw PIRException(s"Unknown OnChipMem write port ${p} for $mem in ${mem.ctrler}")
    }
  }

  def setReader(mem:OnChipMem) = mem match {
    case mem:LocalMem => readersOf(mem) = List(mem.ctrler)
    case mem:RemoteMem =>
      if (!mem.readPort.isConnected)
        warn(s"$mem.readPort in ${mem.ctrler} is not connected")
      readersOf(mem) = mem.readPort.to.head.src match {
        case vo:VecOut => 
          vo.vector.readers.map(_.ctrler)
          //val vrds = vo.vector.readers
          //if(vrds.size!=1)
            //warn(s"OnChipMem=$mem in ${mem.ctrler} has not exactly 1 remote reader [${vrds.mkString(",")}], [${vrds.map(_.ctrler).mkString(",")}]")
          //val rds = vrds.filterNot{_.ctrler.isInstanceOf[MemoryPipeline]}
          //if (rds.size>1)
            //err(s"Currently assume each OnChipMem=$mem in ${mem.ctrler} can only have 1 non-MCU remote reader [${rds.mkString(",")}], [${rds.map(_.ctrler).mkString(",")}]")
          //else if (rds.size==1)
            //rds.head.ctrler
          //else
            //vrds.head.ctrler
        case so:ScalarOut =>
          so.scalar.readers.map(_.ctrler)
          //assert(so.scalar.readers.size==1, s"Currently assume each OnChipMem=$mem in ${mem.ctrler} can only have 1 remote reader ${so.scalar.readers}")
          //so.scalar.readers.head.ctrler
        case cu:Controller => List(cu)
        case p:Primitive => List(p.ctrler)
        case p => throw new Exception(s"Unknown OnChipMem read port ${p} for $this in ${mem.ctrler}")
      }
  }

  def setAccess = {
    design.top.compUnits.foreach { cu =>
      cu.mems.foreach { mem =>
        setWriter(mem)
        setReader(mem)
      }
    }
  }

  addPass(canRun=design.livenessAnalyzer.hasRun) {
    setAccess
    design.top.compUnits.foreach { cu =>
      emitBlock(s"$cu") {
        cu.mems.foreach { mem =>
          emitBlock(s"$mem") {
            dprintln(s"reader = ${readersOf(mem)} = ${mem.ctrler}")
            dprintln(s"writer = ${writerOf(mem)} = ${mem.ctrler}")
          }
        }
      }
    }
  } 

}
