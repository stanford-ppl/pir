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
    writersOf(mem) = (collect[FIFO](mem.writePort).flatMap(fifo => writersOf(fifo)) ++
                      collect[Input](mem.writePort).map(in => in.variable.writer.ctrler)).toList
  }

  def setReader(mem:OnChipMem) = mem match {
    case mem:LocalMem => readersOf(mem) = List(mem.ctrler)
    case mem:RemoteMem =>
      if (!mem.readPort.isConnected)
        warn(s"$mem.readPort in ${mem.ctrler} is not connected")
      readersOf(mem) = mem.readPort.to.head.src match {
        case vo:VecOut => 
          vo.vector.readers.map(_.ctrler)
        case so:ScalarOut =>
          so.scalar.readers.map(_.ctrler)
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

  addPass (runCount=2) {
    setAccess
    design.top.compUnits.foreach { cu =>
      emitBlock(s"$cu") {
        cu.mems.foreach { mem =>
          emitBlock(s"$mem") {
            dprintln(s"mem.ctrler = ${mem.ctrler}")
            dprintln(readersOf.info(mem))
            dprintln(writersOf.info(mem))
          }
        }
      }
    }
  } 

}
