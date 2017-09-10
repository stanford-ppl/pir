package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.exceptions._
import pir.util.misc._
import pir.codegen.Logger

import scala.collection.mutable._

class FIFOAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = Config.ctrl
  import spademeta._
  override lazy val stream = newStream(s"FIFOAnalyzer.log")

  addPass(canRun=design.pirMapping.succeeded) {
    design.mapping.foreach { mp =>
      design.top.compUnits.foreach { cu =>
        emitBlock(s"${quote(mp.clmap(cu))} -> $cu") {
          cu.mems.foreach { 
            case mem:SRAM =>
              val pmem = mp.smmap(mem)
              dprintln(s"$mem -> $pmem")
              bufferSizeOf(pmem) = mem.buffering
            case mem =>
              val pmem = mp.smmap(mem)
              dprintln(s"$mem -> $pmem")
              if (mem.notFull.isConnected) {
                val fhop = mp.rtmap(collectIn[Input](mem.writePort).head)
                val bhop = mp.rtmap(mem.notFull.to.head)
                val pipeDepth = mem.writers.map{ writer => mp.clmap(writer.ctrler).asCU.stages.size }.max
                notFullOffset(pmem) = fhop + bhop + pipeDepth
                dprintln(s" - fhop=$fhop bhop=$bhop pipeDepth=$pipeDepth")
                bufferSizeOf(pmem) = mem.size + notFullOffset(pmem) + 2 //TODO a little buffer just in case 
              } else {
                (mem, cu) match {
                  case (mem:FIFO, cu:MemoryPipeline) if mem.readPort.to.exists{ _ == cu.sram.writePort} =>
                    bufferSizeOf(pmem) = mem.size + delayOf(mp.pmmap(cu.ctrlBox.writeEnDelay))
                  case (mem:FIFO, cu) =>
                    bufferSizeOf(pmem) = mem.size
                  case (mem:MultiBuffer, cu) =>
                    bufferSizeOf(pmem) = mem.buffering
                }
              }
              dprintln(s" - notFullOffset=${notFullOffset(pmem)}")
              dprintln(s"- bufferSize=${bufferSizeOf(pmem)}")
          }
        }
      }
    }
  } 

}
