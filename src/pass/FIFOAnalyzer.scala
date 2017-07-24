package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.exceptions._
import pir.util.misc._
import pir.codegen.Logger

import scala.collection.mutable._

class FIFOAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = true
  import spademeta._
  override lazy val stream = newStream(s"FIFOAnalyzer.log")

  addPass(canRun=design.pirMapping.succeeded) {
    design.mapping.foreach { mp =>
      design.top.compUnits.foreach { cu =>
        emitBlock(s"${quote(mp.clmap(cu))} -> $cu") {
          cu.mems.foreach { 
            case mem:FIFO =>
              val pmem = mp.smmap(mem)
              dprintln(s"$mem -> $pmem")
              if (mem.notFull.isConnected) {
                val fhop = mp.rtmap(mem.writePort.from.src)
                val bhop = mp.rtmap(mem.notFull.to.head)
                val pipeDepth = mem.writer.ctrler.asCU.stages.size
                notFullOffset(pmem) = fhop + bhop + pipeDepth
                dprintln(s" - fhop=$fhop bhop=$bhop pipeDepth=$pipeDepth")
              }
              bufferSizeOf(pmem) = mem.size + notFullOffset(pmem) + 2 //TODO a little buffer just in case 
              dprintln(s" - notFullOffset=${notFullOffset(pmem)}")
              dprintln(s"- bufferSize=${bufferSizeOf(pmem)}")
            case mem:MultiBuffer =>
              val pmem = mp.smmap(mem)
              bufferSizeOf(pmem) = mem.buffering
              dprintln(s"$pmem -> $mem")
              dprintln(s"- bufferSize=${bufferSizeOf(pmem)}")
          }
        }
      }
    }
  } 

}
