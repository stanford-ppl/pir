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

  def setAddrser(mem:OnChipMem):Unit = {
    mem match {
      case mem:SRAM =>
        mem.readAddrMux.inputs.foreach { input =>
          setAddrser(mem, input)
        }
        mem.writeAddrMux.inputs.foreach { input =>
          setAddrser(mem, input)
        }
      case _ => 
    }
  }

  def setAddrser(mem:OnChipMem, addr:Input):Unit = addrserOf.getOrElseUpdate((mem, addr)) { 
    emitBlock(s"setAddrser($mem, $addr)") {
      val res = (collectIn[GlobalInput](addr).map(in => in.from.ctrler)).toList
      if (res.isEmpty) {
        val ctrs = (collectIn[Counter](addr))
        if (ctrs.nonEmpty) mem.ctrler
        else throw PIRException(s"$mem doesn't have addresser addr=$addr")
      } else {
        res.head
      }
    }
  }

  def setWriter(mem:OnChipMem) = writersOf.getOrElseUpdate(mem) { 
    emitBlock(s"setWriter($mem)") {
      val res = (collectIn[GlobalInput](mem.writePort).map(in => in.from.ctrler)).toList
      if (res.isEmpty) {
        err(s"${mem.ctrler}.$mem does not have writer!")
      }
      res
    }
  }

  def setReader(mem:OnChipMem) = readersOf.getOrElseUpdate(mem) {
    emitBlock(s"setReader($mem)"){
      val res = mem match {
        case mem:LocalMem => List(mem.ctrler)
        case mem:RemoteMem =>
          if (!mem.readPort.isConnected)
            warn(s"$mem.readPort in ${mem.ctrler} is not connected")
          mem.readPort.to.head.src match {
            case o:GlobalOutput => o.to.map(_.ctrler)
            case cu:Controller => List(cu)
            case p:Primitive => List(p.ctrler)
            case p => throw new Exception(s"Unknown OnChipMem read port ${p} for $this in ${mem.ctrler}")
          }
      }
      if (res.isEmpty) {
        warn(s"${mem.ctrler}.$mem does not have reader!")
      }
      res
    }
  }

  def resolveCopy(mem:OnChipMem) {
    mem.copy.foreach { orig =>
      mem.writers.foreach { writer =>
        producerOf.get((orig, writer)).foreach { producer =>
          producerOf((mem, writer)) = producer
        }
      }
      mem.readers.foreach { reader =>
        consumerOf.get((orig, reader)).foreach { consumer =>
          // Assume only localMem can be copy
          consumerOf((mem, mem.ctrler)) = consumer
        }
      }
    }
  }

  def setAccess(mem:OnChipMem):Unit = {
    setWriter(mem)
    setReader(mem)
    setAddrser(mem)
    //resolveCopy(mem)
  }

  def setAccess:Unit = {
    design.top.ctrlers.foreach { cl =>
      emitBlock(s"setAccess($cl)") {
        cl.mems.foreach { mem =>
          setAccess(mem)
        }
      }
    }
  }

  addPass (runCount=2) {
    setAccess
  } 

}
