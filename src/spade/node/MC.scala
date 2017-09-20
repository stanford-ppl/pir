package spade.node

import spade.main._
import spade.simulation._
import spade.util._

import pirc.enums._
import pirc.util._
import pirc.exceptions._

import scala.language.reflectiveCalls

case class MCParam (
  sbufSize:Int = 16,
  vbufSize:Int = 16,
  muxSize:Int = 10
) extends ControllerParam {
  def config(mc:MemoryController)(implicit spade:Spade) = {
    import mc.spademeta._
    //assert(sins.size==2)
    //assert(vins.size==1)
    mc.numScalarBufs(5)
    mc.numVecBufs(mc.vins.size)
    mc.mems.foreach(_.writePortMux.addInputs(muxSize))
    nameOf(mc.sbufs(0)) = "roffset"
    nameOf(mc.sbufs(1)) = "woffset"
    nameOf(mc.sbufs(2)) = "rsize"
    nameOf(mc.sbufs(3)) = "wsize"
    nameOf(mc.sbufs(4)) = "sdata"
    nameOf(mc.vbufs(0)) = "vdata"
    mc.genConnections
  }
}

case class MemoryControllerConfig (
  mctpe:MCType
) extends ControllerConfig(Map.empty) // No need to configure output valid

class MemoryController(param:MCParam = MCParam())(implicit spade:Spade) extends Controller(param) 
  with Configurable {
  import spademeta._
  import param._
  type CT = MemoryControllerConfig

  override val typeStr = "mc"
  lazy val ctrlBox:MCCtrlBox = new MCCtrlBox()

  lazy val woffset = sbufs.filter{ sb => nameOf(sb)=="woffset" }.head
  lazy val roffset = sbufs.filter{ sb => nameOf(sb)=="roffset" }.head
  lazy val wsize = sbufs.filter{ sb => nameOf(sb)=="wsize" }.head
  lazy val rsize = sbufs.filter{ sb => nameOf(sb)=="rsize" }.head
  lazy val sdata = sbufs.filter{ vb => nameOf(vb)=="sdata" }.head
  lazy val vdata = vbufs.filter{ vb => nameOf(vb)=="vdata" }.head
  /* Parameters */
  override def config = param.config(this)

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    val dram = spade.dram
    cfmap.get(this).foreach { config => 
      config.mctpe match {
        case TileLoad =>
          vouts.foreach { vout =>
            vout.ic.v.set { v =>
              If (ctrlBox.running.v) {
                val so = roffset.readPort.v.toInt / 4
                val sz = rsize.readPort.v.toInt / 4
                dprintln(s"${quote(this)} TileLoad roffset=$so rsize=$sz ${ctrlBox.count.v.update}")
                dram.updateMemory
                v.foreach { case (ev, i) =>
                  ev <<= dram.memory(so + i + ctrlBox.count.v.toInt)
                }
              }
              v.valid <<= ctrlBox.running.v
            }
          }
        case TileStore =>
          dram.setMem { memory =>
            If (ctrlBox.running.v) {
              vdata.readPort.v.foreach { case (ev, i) =>
                val so = woffset.readPort.v.toInt / 4
                val sz = wsize.readPort.v.toInt / 4
                dprintln(s"${quote(this)} TileStore woffset=$so wsize=$sz ${ctrlBox.count.v.update}")
                memory(so + i + ctrlBox.count.v.toInt) <<= ev
              }
            }
          }
        case Gather =>
        case Scatter =>
      }
    }
    super.register
  }
}
