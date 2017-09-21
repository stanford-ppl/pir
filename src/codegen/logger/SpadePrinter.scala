package pir.codegen

import pir._
import pir.util._
import spade.util._
import spade.node._
import spade._
import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class SpadePrinter(implicit design: PIR) extends Codegen {
  def shouldRun = Config.debug

  override lazy val stream = newStream(SpadeConfig.spadeFile)(design.arch) 
  
  def emitIO(prt:GridIO[_<:PortType, _<:Routable]):Unit = {
    emitBlock(s"ins") {
      prt.ins.foreach { in =>
        emitln(s"${in.ms}")
      }
    }
    emitBlock(s"outs: ") {
      prt.outs.foreach { out =>
        emitln(s"${out.mt}")
      }
    }
  }

  def emitIO(prt:Routable):Unit = {
    emitBlock(s"${quote(prt)}.vectorIO") { emitIO(prt.vectorIO) } 
    emitBlock(s"${quote(prt)}.scalarIO") { emitIO(prt.scalarIO) } 
    emitBlock(s"${quote(prt)}.ctrlIO") { emitIO(prt.ctrlIO) } 
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
          cu.bufs.foreach { s => emitModule(s) }
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
      emitModule(cb.done)
    case cb:OuterCtrlBox =>
      emitModule(cb.childrenAndTree)
      emitModule(cb.siblingAndTree)
      emitModule(cb.udsm)
      cb.udcs.foreach { udc => emitModule(udc) }
      emitModule(cb.en)
      emitModule(cb.done)
    case cb:MemoryCtrlBox =>
      emitModule(cb.writeFifoAndTree)
      emitModule(cb.readFifoAndTree)
      emitModule(cb.readEn)
      emitModule(cb.writeEn)
      emitModule(cb.readDone)
      emitModule(cb.writeDone)
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
