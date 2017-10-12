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

  def setRaddrser(mem:OnChipMem) = raddrserOf.getOrElseUpdate(mem) { 
    mem match {
      case mem:SRAM =>
        emitBlock(s"setRaddrser($mem)") {
          val res = (collectIn[GlobalInput](mem.readAddr).map(in => in.from.ctrler)).toList
          if (res.isEmpty) {
            err(s"${mem.ctrler}.$mem does not have raddrser!")
          }
          res
        }
      case _ =>
        readersOf(mem)
    }
  }

  def setWaddrser(mem:OnChipMem) = waddrserOf.getOrElseUpdate(mem) { 
    mem match {
      case mem:SRAM =>
        emitBlock(s"setWaddrser($mem)") {
          val res = (collectIn[GlobalInput](mem.writeAddr).map(in => in.from.ctrler)).toList
          if (res.isEmpty) {
            err(s"${mem.ctrler}.$mem does not have waddrser!")
          }
          res
        }
      case _ =>
        writersOf(mem)
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

  def setAccess = {
    design.top.ctrlers.foreach { cl =>
      emitBlock(s"setAccess($cl)") {
        cl.mems.foreach { mem =>
          setWriter(mem)
          setReader(mem)
          setWaddrser(mem)
          setRaddrser(mem)
          resolveCopy(mem)
        }
      }
    }
  }

  addPass (runCount=2) {
    setAccess
  } 

}
