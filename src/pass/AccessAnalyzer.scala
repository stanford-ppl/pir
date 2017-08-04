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

  def setWriter(mem:OnChipMem) = emitBlock(s"setWriter($mem)") {
    writersOf(mem) = (collectIn[FIFO](mem.writePort).flatMap(fifo => writersOf(fifo)) ++
                      collectIn[Input](mem.writePort).map(in => in.variable.writer.ctrler)).toList
    dprintln(writersOf.info(mem))
  }

  def setReader(mem:OnChipMem) = emitBlock(s"setReader($mem)"){
    mem match {
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
    dprintln(readersOf.info(mem))
  }

  def setAccess = {
    design.top.compUnits.foreach { cu =>
      emitBlock(s"setAccess($cu)") {
        cu.mems.foreach { mem =>
          setWriter(mem)
          setReader(mem)
        }
      }
    }
  }

  addPass (runCount=2) {
    setAccess
  } 

}
