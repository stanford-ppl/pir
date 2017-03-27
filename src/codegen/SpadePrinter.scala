package pir.codegen

import pir._
import pir.util._
import pir.util.misc._
import pir.plasticine.util._
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class SpadePrinter(implicit design: Design) extends Codegen {
  def shouldRun = Config.debug
  implicit def spade:Spade = design.arch
  val spademeta: SpadeMetadata = spade

  def quote(n:Any)(implicit design:Design):String = { quote(n) }

  override lazy val stream = newStream(Config.spadeFile) 
  
  def emitIO(pne:GridIO[_<:NetworkElement]):Unit = {
    emitBlock(s"ins") {
      pne.ins.foreach { in =>
        emitln(s"${in.ms}")
      }
    }
    emitBlock(s"outs: ") {
      pne.outs.foreach { out =>
        emitln(s"${out.mt}")
      }
    }
  }

  def emitIO(pne:NetworkElement):Unit = {
    emitBlock(s"${quote(pne)}.vectorIO") { emitIO(pne.vectorIO) } 
    emitBlock(s"${quote(pne)}.scalarIO") { emitIO(pne.scalarIO) } 
    emitBlock(s"${quote(pne)}.ctrlIO") { emitIO(pne.ctrlIO) } 
  }

  override def traverse = {
    design.arch.ctrlers.foreach { ctrler => emitBlock(s"${ctrler}") {
      emitIO(ctrler)
      //emitBlock(s"scalarins") {
        //ctrler.sins.foreach { si => 
          //emitln(s"${si.in.ms}")
          //emitln(s"${si.out.mt}")
        //}
      //}
      //emitBlock(s"scalarouts") {
        //ctrler.souts.foreach { so =>
          //emitln(s"${so.in.ms}")
          //emitln(s"${so.out.mt}")
        //}
      //}
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
                  s.prs.foreach { pr => emitln(s"${pr}(${pr.reg}) ${pr.in.ms} ${pr.out.mt}") }
                }
              }
            }
          }
        }
      }
    }
    design.arch match {
      case sn:SwitchNetwork =>
        sn.sbs.foreach { sb => emitIO(sb) }
      case pn:PointToPointNetwork =>
    }
  }

  override def finPass = {
    close
    endInfo(s"Finishing Spade Config Printing in ${getPath}")
  }

}
