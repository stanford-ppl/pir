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

trait ControllerParam extends SpadeParam {
  val sbufSize:Int
  val vbufSize:Int
}

/* Controller */
abstract class Controller(val param:ControllerParam)(implicit spade:Spade) extends NetworkElement {
  import spademeta._
  import param._

  lazy val scalarIO:ScalarIO[this.type] = ScalarIO(this)
  lazy val vectorIO:VectorIO[this.type] = VectorIO(this)
  lazy val ctrlIO:ControlIO[this.type] = ControlIO(this)

  var vbufs:List[VectorMem] = Nil
  var sbufs:List[ScalarMem] = Nil
  def bufs:List[LocalBuffer] = sbufs ++ vbufs
  def numScalarBufs(num:Int):this.type = { sbufs = List.tabulate(num)  { i => ScalarMem(sbufSize).index(i) }; this }
  def numScalarBufs:Int = sbufs.size
  def numVecBufs(num:Int):this.type = { vbufs = List.tabulate(num) { i => VectorMem(vbufSize).index(i) }; this }
  def numVecBufs:Int = vbufs.size

  def ctrlBox:CtrlBox
  def config:Unit
}
