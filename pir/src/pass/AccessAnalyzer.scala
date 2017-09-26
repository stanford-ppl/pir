package pir.pass

import pir._
import pir.node._
import pir.util._

import pirc._
import pirc.util._

class AccessAnalyzer(implicit design: PIR) extends Pass with Logger {
  def shouldRun = true 
  import pirmeta._

  override lazy val stream = newStream(s"AccessAnalyzer.log")

  def setWriter(mem:OnChipMem) = emitBlock(s"setWriter($mem)") {
    writersOf(mem) = (collectIn[FIFO](mem.writePort).flatMap(fifo => writersOf(fifo)) ++
                      collectIn[Input](mem.writePort).map(in => in.variable.writer.ctrler)).toList
    dprintln(writersOf.info(mem))
    if (writersOf(mem).isEmpty) {
      err(s"${mem.ctrler}.$mem does not have writer!")
    }
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
    if (readersOf(mem).isEmpty) {
      warn(s"${mem.ctrler}.$mem does not have reader!")
    }
  }

  def setAccess = {
    design.top.ctrlers.foreach { cl =>
      emitBlock(s"setAccess($cl)") {
        cl.mems.foreach { mem =>
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
