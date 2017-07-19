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
              if (mem.notFull.isConnected) {
                val fhop = mp.rtmap(mem.writePort.from.src)
                val bhop = mp.rtmap(mem.notFull.to.head)
                notFullOffset(pmem) = fhop + bhop
              }
              bufferSizeOf(pmem) = mem.size + notFullOffset(pmem) + 4 //TODO make this large as a start
              dprintln(s"$mem -> $pmem")
              dprintln(s" - notFullOffset=${notFullOffset(pmem)}")
              dprintln(s"- bufferSize=${bufferSizeOf(pmem)}")
            case mem:MultiBuffering =>
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
