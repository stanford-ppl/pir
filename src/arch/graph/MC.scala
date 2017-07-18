package pir.plasticine.graph

import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.config.ConfigFactory
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.exceptions._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set

case class MCParam (
  sbufSize:Int = 16,
  vbufSize:Int = 16
) extends ControllerParam {
}

class MemoryController(param:MCParam = MCParam())(implicit spade:Spade) extends Controller(param) {
  import spademeta._
  import param._
  override val typeStr = "mc"
  lazy val ctrlBox:MCCtrlBox = new MCCtrlBox()

  lazy val woffset = sbufs.filter{ sb => nameOf(sb)=="woffset" }.head
  lazy val roffset = sbufs.filter{ sb => nameOf(sb)=="roffset" }.head
  lazy val wsize = sbufs.filter{ sb => nameOf(sb)=="wsize" }.head
  lazy val rsize = sbufs.filter{ sb => nameOf(sb)=="rsize" }.head
  lazy val data = vbufs.filter{ vb => nameOf(vb)=="data" }.head
  /* Parameters */
  override def config = {
    //assert(sins.size==2)
    //assert(vins.size==1)
    numScalarBufs(4)
    numVecBufs(vins.size)
    nameOf(sbufs(0)) = "roffset"
    nameOf(sbufs(1)) = "woffset"
    nameOf(sbufs(2)) = "rsize"
    nameOf(sbufs(3)) = "wsize"
    nameOf(vbufs(0)) = "data"
    genConnections
  }

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    val dram = spade.dram
    clmap.pmap.get(this).foreach { case mc:pir.graph.MemoryController =>
      mc.mctpe match {
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
              data.readPort.v.foreach { case (ev, i) =>
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
  }
}
