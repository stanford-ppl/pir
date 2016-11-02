package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.codegen.DotCodegen
import pir.misc._
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class SpadePrinter(implicit design: Design) extends Traversal with Printer {

  def quote(n:Any)(implicit design:Design):String = { DotCodegen.quote(n) }

  override val stream = newStream(Config.spadeFile) 
  
  def emitVecIOs(pne:NetworkElement) = {
    emitBlock(s"vecins") {
      pne.vins.foreach { vi =>
        emitln(s"${vi.ms}")
        vi.outports.foreach { op =>
          emitln(s"${op.mt}")
        }
      }
    }
    emitBlock(s"vecouts: ") {
      pne.vouts.foreach { vout =>
        emitln(s"${vout.mt}")
        vout.inports.foreach { ip =>
          emitln(s"${ip.ms}")
        }
      }
    }
  }

  override def traverse = {
    design.arch.ctrlers.foreach { ctrler => emitBlock(s"${ctrler}") {
      emitBlock(s"scalarins") {
        ctrler.sins.foreach { si => 
          emitln(s"${si.in.ms}")
          emitln(s"${si.out.mt}")
        }
      }
      emitBlock(s"scalarouts") {
        ctrler.souts.foreach { so =>
          emitln(s"${so.in.ms}")
          emitln(s"${so.out.mt}")
        }
      }
      emitVecIOs(ctrler)
      ctrler match {
        case top:Top =>
        case cu:ComputeUnit =>
          emitBlock(s"srams") {
            cu.srams.foreach{ s => 
              emitBlock(s"${s}") {
              emitln(s"${s.writeAddr.ms}")
              emitln(s"${s.readAddr.ms}")
              emitln(s"${s.writePort.ms}")
              emitln(s"${s.readPort.mt}")
              }
            }
          }
          emitBlock(s"ctrs") {
            cu.ctrs.foreach{ c =>
              emitBlock(s"${c}") {
                emitln(s"${c.min.ms}")
                emitln(s"${c.max.ms}")
                emitln(s"${c.step.ms}")
                emitln(s"${c.out.mt}")
                emitln(s"${c.en.ms}")
                emitln(s"${c.done.mt}")
              }
            }
          }
          emitln(s"reduce: ${cu.reduce.mt}")
          emitBlock("stages") {
            cu.stages.foreach { s =>
              emitBlock(s"${quote(s)}") {
                s match {
                  case es:EmptyStage =>
                  case fs:FUStage =>
                    fs.fu.operands.foreach { oprd =>
                      emitln(s"${oprd.ms}")
                    }
                    val res = fs.fu.out
                    emitln(s"${res.mt}")
                }
                emitBlock(s"prs") {
                  s.prs.foreach { case (k, v) => emitln(s"${v}($k) ${v.in.ms} ${v.out.mt}") }
                }
              }
            }
          }
        }
      }
    }
    design.arch match {
      case sn:SwitchNetwork =>
        sn.sbs.flatten.foreach { sb => emitBlock(quote(sb)) { emitVecIOs(sb) } }
        sn.csbs.flatten.foreach { sb => emitBlock(quote(sb)) { emitVecIOs(sb) } }
      case pn:PointToPointNetwork =>
    }
  }

  override def finPass = {
    close
    info(s"Finishing Spade Config Printing in ${getPath}")
  }

}
