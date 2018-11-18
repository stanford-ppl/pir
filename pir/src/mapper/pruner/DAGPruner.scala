package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class DAGPruner(implicit compiler:PIR) extends ConstrainPruner with CUCostUtil {

  val dags = scala.collection.mutable.Set[GlobalContainer]()

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(dags, x) { case (x, k) =>
        val vs = x.freeValuesOf(k)
        val (isDAGs, notDAGs) = vs.partition { _.params.get.isInstanceOf[DramAGParam] }
        val fits = isDAGs.nonEmpty
        dbg(s"$k fits in DramAGParam=$fits")
        if (fits) {
          x.filterNotAtKey(k) { v => notDAGs.contains(v) }
        } else Right(x)
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  override def initPass = {
    super.initPass
    dags.clear
    topMap.foreach { tmap =>
      val globals = tmap.cumap.freeKeys
      val fringes = globals.collect { case global:DRAMFringe => global }
      fringes.foreach { fringe =>
        fringe.collectDown[DRAMCommand]().foreach { 
          case command:DRAMDenseCommand =>
            dags ++= trace(command.offset) ++ trace(command.size)
          case command:DRAMSparseCommand =>
            dags ++= trace(command.addr)
        }
      }
      dbg(s"Find DAGs: $dags")
    }
  }

  def trace(input:Input):Iterable[GlobalContainer] = {
    input.neighbors.flatMap { _.collect[GlobalOutput](visitGlobalIn _).map{ _.global.get } }
  }

}
