package pir.graph.traversal

import pir.graph._
import pir._
import pir.PIRMisc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class SpadePrinter(implicit design: Design) extends Traversal with Printer {

  override val stream = newStream("Spade.txt") 
  
  override def traverse = {
    design.arch.computeUnits.foreach { cu =>
      emitBS(s"${cu}") // {
        emitBS(s"srams") // {
          cu.srams.foreach{ s => 
            emitBS(s"${s}") // {
            emitln(s"${s.writeAddr.ms}")
            emitln(s"${s.readAddr.ms}")
            emitln(s"${s.writePort.ms}")
            emitln(s"${s.readPort.mt}")
            emitBE // }
          }
        emitBE // }
        emitBS(s"ctrs") // {
          cu.ctrs.foreach{ c =>
            emitBS(s"${c}") // {
              emitln(s"${c.min.ms}")
              emitln(s"${c.max.ms}")
              emitln(s"${c.step.ms}")
              emitln(s"${c.out.mt}")
            emitBE // }
          }
        emitBE // }
        emitBS(s"piperegs") // {
          cu.pregs.foreach { pr =>
            emitln(s"(${pr.in.ms}, ${pr.out.mt})")
          }
        emitBE // }
        emitBS(s"scalarins") // {
          cu.sins.foreach { si =>
            emitln(s"${si.in.ms}")
            emitln(s"${si.out.mt}")
          }
        emitBE // }
        emitBS(s"scalarouts") // {
          cu.sins.foreach { so =>
            emitln(s"${so.in.ms}")
            emitln(s"${so.out.mt}")
          }
        emitBE // }
        emitBS(s"vecins") // {
          cu.vins.foreach { vi =>
            emitln(s"${vi.ms}")
            vi.outports.foreach { op =>
              emitln(s"${op.mt}")
            }
          }
        emitBE // }
        emitln(s"vecout: ${cu.vout.mt}")
        cu.vout.inports.foreach { ip =>
          emitln(s"${ip.ms}")
        }
        emitln(s"reduce: ${cu.reduce.mt}")
      emitBE // }
    }
  }

  override def finPass = {
    close
  }

}
