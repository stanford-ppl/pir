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

  override lazy val stream = newStream(Config.spadeFile, design.arch) 
  
  def emitIO(pne:GridIO[_<:PortType, _<:NetworkElement]):Unit = {
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

  addPass {
    design.arch.ctrlers.foreach { ctrler => emitBlock(s"${ctrler}") {
      emitIO(ctrler)
      ctrler.sbufs.foreach { s => emitModule(s) }
      ctrler match {
        case top:Top =>
        case mc:MemoryController =>
        case cu:ComputeUnit =>
          cu.srams.foreach { s => emitModule(s) }
          cu.vbufs.foreach { s => emitModule(s) }
          cu.ctrs.foreach{ c => emitModule(c) }
          cu.stages.foreach { s =>
            emitBlock(s"${quote(s)}") {
              s match {
                //case es:EmptyStage =>
                case fs:FUStage =>
                  emitln(s"ops=[${fs.fu.ops.mkString(",")}]")
                  fs.fu.operands.foreach { oprd =>
                    emitln(s"${oprd.ms}")
                  }
                  val res = fs.fu.out
                  emitln(s"${res.mt}")
              }
              emitBlock(s"prs") {
                s.prs.foreach { pr => emitln(s"${pr.in.ms}"); emitln(s"${pr.out.mt}") }
              }
            }
          }
          emitModule(cu.ctrlBox, emitCtrlBox(cu.ctrlBox))
        }
      }
    }
    design.arch match {
      case sn:SwitchNetwork =>
        sn.sbs.foreach { sb => emitIO(sb) }
      case pn:PointToPointNetwork =>
    }
  }

  def emitCtrlBox(cb:CtrlBox) = cb match {
    case cb:InnerCtrlBox =>
      emitModule(cb.tokenInAndTree)
      emitModule(cb.siblingAndTree)
      emitModule(cb.fifoAndTree)
      cb.udcs.foreach { udc => emitModule(udc) }
      emitModule(cb.en)
      emitModule(cb.doneXbar)
    case cb:OuterCtrlBox =>
      emitModule(cb.childrenAndTree)
      emitModule(cb.siblingAndTree)
      emitModule(cb.pulserSM)
      cb.udcs.foreach { udc => emitModule(udc) }
      emitModule(cb.en)
      emitModule(cb.doneXbar)
    case cb:MemoryCtrlBox =>
      emitModule(cb.writeFifoAndTree)
      emitModule(cb.readFifoAndTree)
      emitModule(cb.readEn)
      emitModule(cb.writeEn)
      emitModule(cb.readDoneXbar)
      emitModule(cb.writeDoneXbar)
    case cb:TopCtrlBox =>
    case cb:CtrlBox =>
  }

  def emitModule(m:Module, block: =>Any = {}) = {
    var name=s"$m"
    m match {
      case m:AndTree => name += s"(${m.name})"
      case _ =>
    }
    emitBlock(name) {
      m.ins.foreach { in =>
        emitln(s"${in.ms}")
      }
      m.outs.foreach { out =>
        emitln(s"${out.mt}")
      }
      block
    }
  }

  override def finPass = {
    close
    endInfo(s"Finishing Spade Config Printing in ${getPath}")
  }

}
